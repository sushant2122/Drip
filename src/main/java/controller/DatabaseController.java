package controller;
import java.util.List;

import javax.servlet.http.Part;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.CartModel;
import models.DashboardModel;
import models.OrderModel;
import models.OrderProductModel;
import models.PasswordEncryptionWithAes;
import models.ProductModel;
import models.UserModel;
import utils.StringUtils;




public class DatabaseController {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/Drip";
        String user = "root";
        String pass = "";
        return DriverManager.getConnection(url, user, pass);
    }

    public int addUser(UserModel userModel) {
        try (Connection con = getConnection()) {
            // Check if username already exists
            PreparedStatement checkUsernameSt = con.prepareStatement(StringUtils.GET_USERNAME);
            checkUsernameSt.setString(1, userModel.getUsername());
            ResultSet checkUsernameRs = checkUsernameSt.executeQuery();
            if (checkUsernameRs.next()) {
                if (checkUsernameRs.getInt(1) > 0) {
                    return -2; // username already exists -2
                }
            }

            // Check if phone number already exists
            PreparedStatement checkPhoneSt = con.prepareStatement(StringUtils.GET_PHONE);
            checkPhoneSt.setString(1, userModel.getPhoneNumber());
            ResultSet checkPhoneRs = checkPhoneSt.executeQuery();
            if (checkPhoneRs.next()) {
                if (checkPhoneRs.getInt(1) > 0) {
                    return -4; // phone number already exists -4
                }
            }

            // Check if email already exists
            PreparedStatement checkEmailSt = con.prepareStatement(StringUtils.GET_EMAIL);
            checkEmailSt.setString(1, userModel.getEmail());
            ResultSet checkEmailRs = checkEmailSt.executeQuery();
            if (checkEmailRs.next()) {
                if (checkEmailRs.getInt(1) > 0) {
                    return -3; // email already exists -3
                }
            }
            con.setAutoCommit(false);

            // Insert new user
            PreparedStatement userInsertStatement = con.prepareStatement(StringUtils.INSERT_USER);
            userInsertStatement.setString(1, userModel.getUsername());
            userInsertStatement.setString(2, userModel.getFirstName());
            userInsertStatement.setString(3, userModel.getLastName());
            Date dob = Date.valueOf(userModel.getDob());
            userInsertStatement.setDate(4, dob);
            userInsertStatement.setString(5, userModel.getGender());
            userInsertStatement.setString(6, userModel.getEmail());
            userInsertStatement.setString(7, userModel.getPhoneNumber());
            userInsertStatement.setString(8, userModel.getRole());
            userInsertStatement.setString(9, PasswordEncryptionWithAes.encrypt(userModel.getUsername(), userModel.getPassword()));
            userInsertStatement.setString(10, userModel.getimageUrlFromPart());

            // Execute the user insert statement
            int userInsertResult = userInsertStatement.executeUpdate();

            // Check if the user insertion was successful
            if (userInsertResult > 0) {
                // If the role is "user", insert into the cart table
                if (userModel.getRole().equals("user")) {
                    // Insert into the cart table
                    PreparedStatement cartInsertStatement = con.prepareStatement(StringUtils.INSERT_CART);
                    cartInsertStatement.setString(1, null);
                    cartInsertStatement.setString(2, userModel.getUsername());
                    cartInsertStatement.executeUpdate();
                }
                // Commit the transaction
                con.commit();
                // Return 1 for successful insertion
                return 1;
            } else {
                // If user insertion failed, return 0
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1; // Handle SQL exception
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return -1; // Handle class not found exception
        }
    }

    public int getUserLoginInfo(String username, String password) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
            st.setString(1, username);
            ResultSet result = st.executeQuery();

            if (result.next()) {
                String userDb = result.getString("user_name");

                String passwordDb = result.getString("password");
                String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, username);

                if (decryptedPwd != null && userDb.equals(username) && decryptedPwd.equals(password)) {
                    return 1; // Username and password match
                } else {
                    return 0; // Username and password don't match
                }
            } else {
                return -2; // Username not found in the database
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return -1; // Some error occurred while executing SQL query or connecting to the database
        }
    }

    public String getUserRole(String username) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.GET_ROLE);
            st.setString(1, username); // Assuming the first parameter should be the username
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString("role"); // Assuming the column name in the database is "first_name"
            } else {
                return null; // No records found for the given username and password
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null; // Exception occurred
        }
    }

    public ArrayList<ProductModel> getAllproductInfo() {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.GET_ALL_PRODUCT_INFO);
            ResultSet result = stmt.executeQuery();

            ArrayList<ProductModel> products = new ArrayList<ProductModel>();

            while (result.next()) {
                ProductModel product = new ProductModel();
                product.setProductid(result.getString("product_id"));
                product.setModelno(result.getString("model_no"));
                product.setProductname(result.getString("name"));
                product.setPrice(result.getString("price"));
                product.setStock(result.getString("stock"));
                product.setDescription(result.getString("description"));
                product.setUsername(result.getString("user_name"));
                product.setCategory(result.getString("category_id"));
                product.setImageUrlFromDB(result.getString("images"));

                products.add(product);
            }
            return products;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int addProduct(ProductModel productModel) {
        try (Connection con = getConnection()) {
            // Insert new product
            PreparedStatement st = con.prepareStatement(StringUtils.INSERT_PRODUCT);
            st.setString(1, productModel.getProductid());
            st.setString(2, productModel.getModelno());
            st.setString(3, productModel.getProductname());
            st.setString(4, productModel.getPrice());
            st.setString(5, productModel.getStock());
            st.setString(6, productModel.getDescription());
            st.setString(7, productModel.getUsername());
            st.setString(8, productModel.getCategory());
            st.setString(9, productModel.getimageUrlFromPart());

            int result = st.executeUpdate(); // Insert, update, delete
            return result > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1; // Handle SQL exception
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return -1; // Handle class not found exception
        }
    }
    
    public ArrayList<ProductModel> getproductInfobyAdmin(String username) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.GET_ALL_PRODUCT_INFO_BY_ADMIN);
            stmt.setString(1, username); // Set the username parameter
            ResultSet result = stmt.executeQuery();

            ArrayList<ProductModel> adminproducts = new ArrayList<ProductModel>();

            while (result.next()) {
                ProductModel product = new ProductModel();
                product.setProductid(result.getString("product_id"));
                product.setModelno(result.getString("model_no"));
                product.setProductname(result.getString("name"));
                product.setPrice(result.getString("price"));
                product.setStock(result.getString("stock"));
                product.setDescription(result.getString("description"));
                product.setUsername(result.getString("user_name"));
                product.setCategory(result.getString("Category_name")); // Assuming Category_name is retrieved from a join
                product.setImageUrlFromDB(result.getString("images"));

                adminproducts.add(product);
            }
            return adminproducts;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<ProductModel> getproductInfobySearch(String search) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.SEARCH_PRODUCT);
            stmt.setString(1, search);
            stmt.setString(2, search);
            ResultSet result = stmt.executeQuery();

            ArrayList<ProductModel> searchproducts = new ArrayList<ProductModel>();

            while (result.next()) {
                ProductModel product = new ProductModel();
                product.setProductid(result.getString("product_id"));
                product.setModelno(result.getString("model_no"));
                product.setProductname(result.getString("name"));
                product.setPrice(result.getString("price"));
                product.setStock(result.getString("stock"));
                product.setDescription(result.getString("description"));
                product.setUsername(result.getString("user_name"));
                product.setCategory(result.getString("category_id"));
                product.setImageUrlFromDB(result.getString("images"));

                searchproducts.add(product);
            }

            return searchproducts;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<OrderModel> getOrderInfobyAdmin(String username) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.ADMIN_ORDER_LIST);
            stmt.setString(1, username); // Set the username parameter
            ResultSet result = stmt.executeQuery();

            ArrayList<OrderModel> adminorders = new ArrayList<OrderModel>();

            while (result.next()) {
                OrderModel order = new OrderModel();
                order.setOrder_id(result.getString("order_id"));
                order.setUsername(result.getString("user_name"));
                order.setOrder_date(result.getDate("order_date").toLocalDate());
                order.setOrder_status(result.getString("order_status"));
                order.setTotal_amt(result.getString("total_amt"));
                order.setPayment(result.getString("payment_type"));

                adminorders.add(order);
            }
            return adminorders;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<ProductModel> getproductInfobyCategory(String category) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.CATEGORY_PRODUCT);
            stmt.setString(1, category);

            ResultSet result = stmt.executeQuery();

            ArrayList<ProductModel> categoryproducts = new ArrayList<ProductModel>();

            while (result.next()) {
                ProductModel product = new ProductModel();
                product.setProductid(result.getString("product_id"));
                product.setModelno(result.getString("model_no"));
                product.setProductname(result.getString("name"));
                product.setPrice(result.getString("price"));
                product.setStock(result.getString("stock"));
                product.setDescription(result.getString("description"));
                product.setUsername(result.getString("user_name"));
                product.setCategory(result.getString("category_id"));
                product.setImageUrlFromDB(result.getString("images"));

                categoryproducts.add(product);
            }

            return categoryproducts;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ProductModel getproductInfobyId(String productid) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.DESCRIPTION_PRODUCT);
            stmt.setString(1, productid);

            ResultSet result = stmt.executeQuery();

            ProductModel descriptionproducts = new ProductModel();

            if (result.next()) {
                ProductModel product = new ProductModel();
                product.setProductid(result.getString("product_id"));
                product.setModelno(result.getString("model_no"));
                product.setProductname(result.getString("name"));
                product.setPrice(result.getString("price"));
                product.setStock(result.getString("stock"));
                product.setDescription(result.getString("description"));
                product.setUsername(result.getString("user_name"));
                product.setCategory(result.getString("category_id"));
                product.setImageUrlFromDB(result.getString("images"));

                return product;
            } else {
                return null;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getcartid(String username) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.GET_CART_ID);
            st.setString(1, username); // Assuming the first parameter should be the username
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString("cart_id");
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //cart
    public int addtoCart(CartModel cartModel) {
        try (Connection con = getConnection()) {
            // Prepare the SQL statement using ON DUPLICATE KEY UPDATE
            PreparedStatement st = con.prepareStatement(StringUtils.ADD_TO_CART);
            st.setString(1, cartModel.getCartId());
            st.setString(2, cartModel.getProductId());
            st.setString(3, cartModel.getQuantity());

            int result = st.executeUpdate(); // Insert, update, delete
            return result > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1; // Handle SQL exception
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return -1; // Handle class not found exception
        }
    }

  //cart product item
    public ArrayList<ProductModel> getproductInfobyCart(String username) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.GET_CART_INFO);
            stmt.setString(1, username);

            ResultSet result = stmt.executeQuery();

            ArrayList<ProductModel> cartproducts = new ArrayList<ProductModel>();

            while (result.next()) {

                ProductModel product = new ProductModel();
                product.setProductid(result.getString("product_id"));
                product.setModelno(result.getString("model_no"));
                product.setProductname(result.getString("name"));
                product.setPrice(result.getString("price"));
                product.setDescription(result.getString("description"));
                product.setImageUrlFromDB(result.getString("images"));
                product.setStock(result.getString("quantity"));

                cartproducts.add(product);
            }

            return cartproducts;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //remove cart item
    public int deleteCartItem(String productid, String cartid) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_CART_ITEM);
            st.setString(1, productid);
            st.setString(2, cartid);
            return st.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return -1;
        }
    }

    //order
    public int insertOrderAndProduct(OrderModel ordermodel, ArrayList<OrderProductModel> orderProducts, String cart_id) {
        try (Connection con = getConnection()) {
            con.setAutoCommit(false); // Start transaction

            // Prepare the SQL statement to insert into orders table
            PreparedStatement orderStatement = con.prepareStatement(StringUtils.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            orderStatement.setString(1, ordermodel.getUsername());
            Date orderdate = Date.valueOf(ordermodel.getOrder_date());
            orderStatement.setDate(2, orderdate);
            orderStatement.setString(3, ordermodel.getOrder_status());
            orderStatement.setString(4, ordermodel.getTotal_amt());
            orderStatement.setString(5, ordermodel.getPayment());

            int orderResult = orderStatement.executeUpdate();
            if (orderResult == 0) {
                con.rollback(); // Rollback if order insertion fails
                return 0;
            }

            // Retrieve the generated order ID
            int orderId = -1;
            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            } else {
                con.rollback(); // Rollback if order ID retrieval fails
                return 0;
            }

            // Prepare the SQL statement to insert into order_products table
            PreparedStatement orderProductStatement = con.prepareStatement(StringUtils.INSERT_ORDERPRODUCT);
            for (OrderProductModel opmodel : orderProducts) {
                orderProductStatement.setInt(1, orderId);
                orderProductStatement.setString(2, opmodel.getProductId());
                orderProductStatement.setString(3, opmodel.getQuantity());
                orderProductStatement.setString(4, opmodel.getSubtotal());

                int productResult = orderProductStatement.executeUpdate();
                if (productResult == 0) {
                    con.rollback(); // Rollback if order product insertion fails
                    return 0;
                }
            }
            // Prepare the SQL statement to delete cart items
            PreparedStatement deleteCartStatement = con.prepareStatement(StringUtils.DELETE_CART_ITEMS);
            deleteCartStatement.setString(1, cart_id);
            deleteCartStatement.executeUpdate();


            con.commit(); // Commit transaction if everything is successful
            return 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1; // Handle SQL exception
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return -1; // Handle class not found exception
        }
    }

    public boolean updateOrderStatus(String orderId, String newStatus) {
        try (Connection con = getConnection()) {
         
            PreparedStatement updateStatement = con.prepareStatement(StringUtils.UPDATE_ORDER);
            updateStatement.setString(1, newStatus);
            updateStatement.setString(2, orderId);

            // Execute the update statement
            int rowsUpdated = updateStatement.executeUpdate();
            
            // Check if any rows were updated
            if (rowsUpdated > 0) {
                return true; // Update successful
            } else {
                return false; // No rows were updated
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Handle SQL exception
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false; // Handle class not found exception
        }
    }

    public boolean deleteProduct(String productId) {
        try (Connection con = getConnection()) {
            con.setAutoCommit(false); // Start transaction

            // Delete from cart_info
            PreparedStatement deleteFromCart = con.prepareStatement(StringUtils.DELETE_FROM_CART);
            deleteFromCart.setString(1, productId);
            deleteFromCart.executeUpdate();

            // Delete from order_info
            PreparedStatement deleteFromOrder = con.prepareStatement(StringUtils.DELETE_FROM_ORDER);
            deleteFromOrder.setString(1, productId);
            deleteFromOrder.executeUpdate();

            // Delete from product_info
            PreparedStatement deleteFromProduct = con.prepareStatement(StringUtils.DELETE_FROM_PRODUCT);
            deleteFromProduct.setString(1, productId);
            deleteFromProduct.executeUpdate();

            con.commit(); // Commit transaction
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //order detail
    public ArrayList<OrderModel> getorderInfobyuser(String username) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.GET_ORDER_INFO);
            stmt.setString(1, username);

            ResultSet result = stmt.executeQuery();

            ArrayList<OrderModel> orderdetail = new ArrayList<OrderModel>();

            while (result.next()) {
                OrderModel order = new OrderModel();
                order.setOrder_id(result.getString("order_id"));
                order.setUsername(result.getString("user_name"));
                // Convert the String representation of order date to LocalDate
                LocalDate orderDate = LocalDate.parse(result.getString("order_date"));
                order.setOrder_date(orderDate);
                order.setOrder_status(result.getString("order_status"));
                order.setTotal_amt(result.getString("total_amt"));
                order.setPayment(result.getString("payment_type"));

                orderdetail.add(order);
            }

            return orderdetail;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    //order product item
    public ArrayList<ProductModel> getorderproductInfo(String orderid) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.GET_ORDER_PRODUCT_INFO);
            stmt.setString(1, orderid);

            ResultSet result = stmt.executeQuery();

            ArrayList<ProductModel> orderproducts = new ArrayList<ProductModel>();

            while (result.next()) {

                ProductModel product = new ProductModel();
                product.setProductid(result.getString("product_id"));
                product.setModelno(result.getString("model_no"));
                product.setProductname(result.getString("name"));
                product.setPrice(result.getString("sub_total"));
                product.setDescription(result.getString("description"));
                product.setImageUrlFromDB(result.getString("images"));
                product.setStock(result.getString("quantity"));

                orderproducts.add(product);
            }

            return orderproducts;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
  //dashboard
    public DashboardModel getdashboarddetails(String adminUsername) {
        try (Connection con = getConnection()) {
            PreparedStatement productCountStmt = con.prepareStatement(StringUtils.PRODUCT_COUNT);
            productCountStmt.setString(1, adminUsername);
            ResultSet productCountRs = productCountStmt.executeQuery();
            String productCount = "0";
            if (productCountRs.next()) {
                productCount = String.valueOf(productCountRs.getInt("product_count"));
            }

            PreparedStatement totalSalesStmt = con.prepareStatement(StringUtils.SALES);
            totalSalesStmt.setString(1, adminUsername);
            ResultSet totalSalesRs = totalSalesStmt.executeQuery();
            String totalSales = "0.0";
            if (totalSalesRs.next()) {
                totalSales = String.valueOf(totalSalesRs.getDouble("total_sales"));
            }

            PreparedStatement orderCountStmt = con.prepareStatement(StringUtils.ORDER_COUNT);
            orderCountStmt.setString(1, adminUsername);
            ResultSet orderCountRs = orderCountStmt.executeQuery();
            String orderCount = "0";
            if (orderCountRs.next()) {
                orderCount = String.valueOf(orderCountRs.getInt("order_count"));
            }

            // Create a new DashboardModel object and set its properties
            DashboardModel dashboardModel = new DashboardModel(productCount, orderCount, totalSales);

            return dashboardModel; // Return the populated DashboardModel
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public UserModel getuserInfobyId(String username){
    	
    	try {
    		PreparedStatement stmt = getConnection().prepareStatement(StringUtils.USER_DETAIL);
    		stmt.setString(1, username);

    		ResultSet result = stmt.executeQuery();
    		
    		
    		if(result.next()) {

    			
    			UserModel user = new UserModel();
    			
    			user.setFirstName(result.getString("first_name"));
    			user.setLastName(result.getString("last_name"));
    			user.setEmail(result.getString("email"));
    			user.setDob(result.getDate("dob").toLocalDate());
    			user.setPhoneNumber(result.getString("phone_number"));
    			user.setImageUrlFromDB(result.getString("images"));	
    			
    			return user;
    		}
    		else {
    			return null;
    		}
    		
    	}catch (SQLException | ClassNotFoundException ex) {
    		ex.printStackTrace();
    		return null;
    	}
    }
    public boolean updateUserDetails(UserModel userModel) {
        boolean f = false;
        try {
            PreparedStatement updateStatement = getConnection().prepareStatement(StringUtils.UPDATE_USER);
            updateStatement.setString(1, userModel.getFirstName());
            updateStatement.setString(2, userModel.getLastName());
            updateStatement.setString(3, userModel.getEmail());
            updateStatement.setDate(4, java.sql.Date.valueOf(userModel.getDob())); // Convert LocalDate to java.sql.Date
            updateStatement.setString(5, userModel.getPhoneNumber());
            updateStatement.setString(6, userModel.getimageUrlFromPart());
            updateStatement.setString(7, userModel.getUsername());
            updateStatement.executeUpdate();
            f=true;
            
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the SQL exception
            // You may want to handle specific SQL errors here, such as constraint violations or connection issues
            return false;
        }
        return f;
    }


    public ProductModel getproductInforupdate(String productid){
    	
    	try {
    		PreparedStatement stmt = getConnection().prepareStatement(StringUtils.DESCRIPTION_PRODUCT);
    		stmt.setString(1, productid);

    		ResultSet result = stmt.executeQuery();
    		

    		
    		if(result.next()) {

    			
    			ProductModel product = new ProductModel();
    			
    			product.setProductid(result.getString("product_id"));
    			product.setModelno(result.getString("model_no"));
    			product.setProductname(result.getString("name"));
    			product.setPrice(result.getString("price"));
    			product.setStock(result.getString("stock"));
    			product.setDescription(result.getString("description"));
    			product.setCategory(result.getString("category_id"));
    			product.setImageUrlFromDB(result.getString("images"));	
    			
    			return product;
    		}
    		else {
    			return null;
    		}
    		
    	}catch (SQLException | ClassNotFoundException ex) {
    		ex.printStackTrace();
    		return null;
    	}
    }



    public boolean updateProductDetails(ProductModel productModel) {
        boolean f = false;
        try {
            PreparedStatement updateStatement = getConnection().prepareStatement(StringUtils.UPDATE_PRODUCT);
            updateStatement.setString(1, productModel.getModelno());
            updateStatement.setString(2, productModel.getProductname());
            updateStatement.setString(3, productModel.getPrice());
            updateStatement.setString(4, productModel.getStock());
            updateStatement.setString(5, productModel.getDescription());
            updateStatement.setString(6, productModel.getCategory());
            updateStatement.setString(7, productModel.getimageUrlFromPart());
            updateStatement.setString(8, productModel.getProductid());

            updateStatement.executeUpdate();
            f=true;
            
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the SQL exception
            // You may want to handle specific SQL errors here, such as constraint violations or connection issues
            return false;
        }
        return f;
    }
}
