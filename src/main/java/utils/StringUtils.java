package utils;

import java.io.File;

public class StringUtils {
	
	
		//Start: SQL queries
		public static final String INSERT_USER = "INSERT INTO user_info"
				+"(user_name, first_name,last_name, dob, gender, email, phone_number, role, password,images) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		
		public static final String INSERT_CART = "INSERT INTO cart (cart_id,user_name) VALUES (?,?)";
		public static final String GET_ALL_USER_INFO = "SELECT * FROM user_info";
		public static final String GET_LOGIN_USER_INFO = "SELECT * FROM user_info WHERE user_name = ?";
		public static final String GET_USERNAME = "SELECT COUNT(*) FROM user_info WHERE user_name = ?";
		public static final String GET_EMAIL = "SELECT * FROM user_info WHERE email = ?";
		public static final String GET_PHONE = "SELECT * FROM user_info WHERE phone_number = ?";
		public static final String GET_ROLE = "SELECT role FROM user_info WHERE user_name= ?";
		public static final String GET_ALL_PRODUCT_INFO = "SELECT  product_id,model_no,name,price,stock,description,user_name,category_id,images FROM product_info";
		public static final String GET_ALL_PRODUCT_INFO_BY_ADMIN = "SELECT p.product_id, p.model_no, p.name, p.price, p.stock, p.description, p.user_name, pc.Category_name, p.images "+ "		FROM product_info p "
				+ "		INNER JOIN prod_category pc ON p.category_id = pc.category_id "+ "		WHERE p.user_name = ?";
	

		public static final String GET_CART_INFO = "SELECT p.product_id,p.model_no,p.name,p.price,p.description,p.images,ci.quantity\n"
				+ "FROM cart_info ci JOIN product_info p ON ci.product_id = p.product_id JOIN cart c ON ci.cart_id = c.cart_id JOIN user_info u ON c.user_name = u.user_name\n"
				+ "WHERE  c.user_name = ?";
		
		public static final String ADD_TO_CART = "INSERT INTO cart_info (cart_id, product_id, quantity) "
                + "VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE quantity = VALUES(quantity)";
		
		public static final String INSERT_PRODUCT = "INSERT INTO product_info"
				+"(product_id,model_no, name, price, stock,description,user_name,category_id,images) "
				+ "VALUES (?,?, ?, ?, ?, ?, ?, ?,?)";
		
		public static final String SEARCH_PRODUCT = "SELECT * FROM product_info WHERE name LIKE ? OR price LIKE ?";
		public static final String CATEGORY_PRODUCT = "SELECT * FROM product_info WHERE category_id = ?";
		public static final String DESCRIPTION_PRODUCT = "SELECT * FROM product_info WHERE product_id = ?";

		// order list 
		public static final String ADMIN_ORDER_LIST = "SELECT order_info.order_id, order_info.user_name,order_info.order_date, order_info.order_status, order_info.total_amt,  payment_info.payment_type\n"
                + "FROM order_info\n"
                + "JOIN order_product_info ON order_info.order_id = order_product_info.order_id\n"
                + "JOIN product_info ON order_product_info.product_id = product_info.product_id\n"
                + "JOIN user_info ON product_info.user_name = user_info.user_name\n"
                + "JOIN payment_info ON order_info.payment_id = payment_info.payment_id\n"
                + "WHERE user_info.user_name = ?";

		
		
		public static final String USER_ORDER_LIST = "SELECT order_product_info.*\n"
				+ "FROM order_product_info\n"
				+ "JOIN order_info ON order_product_info.order_id = order_info.order_id\n"
				+ "JOIN product_info ON order_product_info.product_id = product_info.product_id\n"
				+ "JOIN user_info ON order_info.user_name = user_info.user_name\n"
				+ "WHERE user_info.user_name = ?";
		
		
		public static final String GET_IMAGE_OF_USER = "SELECT images FROM user_info WHERE user_name=?";
		public static final String GET_CART_ID = "SELECT cart_id FROM cart WHERE user_name=?";
		public static final String GET_ORDER_ID = "SELECT order_id FROM order_info WHERE user_name=?";
		public static final String QUERY_DELETE_CART_ITEM = "DELETE FROM cart_info WHERE product_id = ? AND cart_id = ?";

		public static final String INSERT_ORDER="INSERT INTO order_info"
				+"( user_name,order_date, order_status, total_amt, payment_id) "
				+ "VALUES (?, ?, ?, ?, ?)";
		public static final String INSERT_ORDERPRODUCT="INSERT INTO order_product_info"
				+"(order_id, product_id,quantity, sub_total) "
				+ "VALUES (?, ?, ?, ?)";
		public static final String UPDATE_ORDER="UPDATE order_info SET order_status = ? WHERE order_id = ?";
		
		public static final String DELETE_CART_ITEMS = "DELETE FROM cart_info WHERE cart_id = ?";
		public static final String DELETE_FROM_CART = "DELETE FROM cart_info WHERE product_id = ?";
		public static final String DELETE_FROM_ORDER = "DELETE FROM order_product_info WHERE product_id = ?";
		public static final String DELETE_FROM_PRODUCT = "DELETE FROM product_info WHERE product_id = ?";
		public static final String GET_ORDER_INFO = "SELECT oi.order_id,oi.user_name,oi.order_date,oi.order_status,oi.total_amt, p.payment_type " +
                "FROM order_info oi " +
                "JOIN payment_info p ON oi.payment_id = p.payment_id " +
                "WHERE oi.user_name=?";
		public static final String GET_ORDER_PRODUCT_INFO = "SELECT product_info.product_id, product_info.model_no, product_info.name, order_product_info.sub_total, product_info.description, product_info.images, order_product_info.quantity " +
	            "FROM order_product_info " +
	            "JOIN product_info ON order_product_info.product_id = product_info.product_id " +
	            "WHERE order_product_info.order_id = ?";

		
		public static final String USER_DETAIL="SELECT * FROM user_info WHERE user_name=?";
	
		
		public static final String UPDATE_USER="UPDATE user_info SET first_name=?, last_name=?, email=?, dob=?, phone_number=?, images=? WHERE user_name=?";
		public static final String UPDATE_PRODUCT = "UPDATE product_info SET model_no=?, name=?, price=?, stock=?, description=?, category_id=?, images=? WHERE product_id=?";
		
		public static final String PRODUCT_COUNT ="SELECT COUNT(*) AS product_count FROM product_info WHERE user_name = ?";
		
		public static final String ORDER_COUNT = "SELECT COUNT(*) AS order_count " +
                "FROM order_product_info op " +
                "JOIN product_info p ON op.product_id = p.product_id " +
                "WHERE p.user_name = ?";
		public static final String SALES = "SELECT SUM(op.sub_total) AS total_sales " +
                "FROM order_product_info op " +
                "JOIN product_info p ON op.product_id = p.product_id " +
                "WHERE p.user_name = ?";
		
		//End: SQL queries
		
		//Start: Normal text
		public static final String USER_NAME="username";
		public static final String FIRST_NAME="firstName";
		public static final String LAST_NAME="lastName";
		public static final String BIRTHDAY="birthday";
		public static final String GENDER="gender";
		public static final String EMAIL="email";
		public static final String PHONE_NUMBER="phoneNumber";
		public static final String ROLE="role";
		public static final String PASSWORD="password";
		public static final String USER_IMAGE="userimg";
		
		//for add product the parameters are from the form 
		public static final String PRODUCT_MODEL="pmodel";
		public static final String PRODUCT_NAME="pname";
		public static final String PRODUCT_PRICE="price";
		public static final String PRODUCT_STOCK="stock";
		public static final String PRODUCT_DESCRIPTION="description";
		public static final String PRODUCT_CATEGORY="category";
		public static final String PRODUCT_IMAGE="productimg";
	
		
		//search 
		public static final String SEARCH="search";
		
		//login/logout
		public static final String USER = "user";
		public static final String SUCCESS = "success";
		public static final String TRUE = "true";
		public static final String LOGIN = "Login";
		public static final String LOGOUT = "Logout";
		public static final String STUDENT_MODEL = "studentModel";
		//payment
		public static final String TOT_AMT="tot_amt";
		public static final String PAYMENT="payment";
		public static final String DELETE_CART_ID ="cartdeleteId";
		public static final String PRODUCTDELETE_ID ="productdeleteId";
		public static final String USERUPDATEID = "user_name";
		//order
		public static final String ORDERID = "order_id";
		public static final String STATUS= "status";
		public static final String ORDER_ID="order_id";
		//update
		public static final String FIRST_NAME1="firstName";
		public static final String LAST_NAME1="lastName";
		public static final String BIRTHDAY1="birthday";
		public static final String EMAIL1="email";
		public static final String PHONE_NUMBER1="phoneNumber";
		public static final String USER_IMAGE1="user_img";
		
		public static final String PRODUCTUPDATEID = "updateproductid";
		//End: Normal text
		
		
		//Start: IMAGE paths
		public static final String IMAGE_DIR_PRODUCT = "/Users/sushant/eclipse/Drip/src/main/webapp/resources/images/product/";
		public static final String IMAGE_DIR_SAVE_PATH =  File.separator + IMAGE_DIR_PRODUCT;
		
		public static final String IMAGE_DIR_USER = "/Users/sushant/eclipse/Drip/src/main/webapp/resources/images/user/";
		public static final String IMAGE_DIR_SAVE_PATH_2 =  File.separator + IMAGE_DIR_USER;
		//End: IMAGE paths
		
		//Start: ERROR messages
		public static final String SUCESS_REGISTER_MESSAGE="Sucessfully Registered";
		public static final String REGISTER_ERROR_MESSAGE="please correct the form data.";
		public static final String SERVER_ERROR_MESSAGE="An unexpected error occured.";
		public static final String SUCESS_MESSAGE="sucessMessage";
		public static final String ERROR_MESSAGE = "errorMessage";
		public static final String WELCOME_MESSAGE = "welcomeMessage";
		public static final String FIRST_NAME_ERROR="firstName not valid";	
		public static final String LAST_NAME_ERROR="LastName not valid";
		public static final String PASSWORD_UNMATCHED_ERROR="Password unmatched!";
		public static final String USERNAME_ERROR="userName not valid";	
		public static final String PASSWORD_ERROR="password not valid";	
		public static final String BIRTHDAY_ERROR="birthday not valid";	
		public static final String PHONE_ERROR="Phonenumber not valid";	
		public static final String LOGIN_ERROR=" username's, password is incorrect!";	
		public static final String USERNAME_EXIST_ERROR="Username already exist!";	
		public static final String USERNAME_DONT_EXIST_ERROR="username dont exist!";	
		public static final String EMAIL_EXIST_ERROR="Email already exist!";
		public static final String PHONE_EXIST_ERROR="Phone already exist!";	
		public static final String GENDER_ERROR="Gender required.";	
		public static final String MESSAGE_SUCCESS = "successMessage";
		public static final String MESSAGE_ERROR = "errorMessage";
		public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
		public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
		public static final String MESSAGE_ERROR_DELETE = "Cannot delete the product!";
		//End: ERROR messages
		
		// Start: JSP route
		public static final String THANK_PAGE = "/pages/thanks.jsp";
		public static final String LOGIN_PAGE = "/pages/login.jsp";
		public static final String REGISTER_PAGE = "/pages/register.jsp";
		public static final String HOME_PAGE= "/pages/home.jsp";
		public static final String ADMIN_PAGE= "/pages/admin.jsp";
		public static final String ERROR_PAGE= "/pages/error.jsp";
		public static final String FOOTER_PAGE = "pages/footer.jsp";
		public static final String HEADER_PAGE = "pages/header.jsp";
		public static final String ADMIN_PRODUCT_PAGE= "/pages/admin_product.jsp";
		public static final String ADMIN_ADD_PAGE= "/pages/admin_add.jsp";
		public static final String ADMIN_ORDER_PAGE= "/pages/admin_order.jsp";
		public static final String USER_PROFILE_PAGE= "/pages/userprofile.jsp";
		public static final String USER_CART_PAGE= "/pages/cart.jsp";
		public static final String ADMIN_UPDATE_PAGE="/pages/updateproduct.jsp";
		// End: JSP Route		
		

		//Start: Servlet route
		public static final String ADMIN_ADD_SERVLET= "/AddProductServlet";
		public static final String ADMIN_ORDER_SERVLET= "/AdminOrderServlet";
		public static final String ADMIN_PRODUCTLIST_SERVLET= "/AdminProductlistServlet";
		public static final String REGISTER_SERVLET = "/RegisterServlet";
		public static final String SERVLET_URL_LOGIN = "/LoginServlet";
		public static final String SERVLET_URL_LOGOUT = "/LogoutServlet";
		public static final String SERVLET_URL_CART = "/AddCartServlet";
		public static final String SERVLET_URL_MODIFYPRODUCT="/ModifyProductServlet";
		public static final String SERVLET_URL_CARTLIST = "/CartListServlet";
		public static final String SERVLET_URL_UPDATEORDER = "/UpdateOrderServlet";
		public static final String SERVLET_URL_DASHBOARD = "/DashboardServlet";
		public static final String URL_ADMIN_UPDATE="/UserUpdateServlet";	
		// End: Servlet Route


	
}
