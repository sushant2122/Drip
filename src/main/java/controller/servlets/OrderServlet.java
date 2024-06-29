package controller.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import models.OrderModel;
import models.OrderProductModel;
import models.ProductModel;
import utils.StringUtils;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController databaseController = new DatabaseController();

    public OrderServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // Get the current date
        Date currentDate = new Date();
        
        // Format the date using SimpleDateFormat
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       
        // Get user name from session
        String username = (String) request.getSession().getAttribute("username");
        
        // Format the current date
        String formattedDate = formatter.format(currentDate);
        LocalDate order_date = LocalDate.parse(formattedDate);
        
        // Set order status, total amount, and payment from request parameters
        String orderStatus = "pending";
        String totalAmount = request.getParameter(StringUtils.TOT_AMT);
        String payment = request.getParameter(StringUtils.PAYMENT);
        String cartId = (String) session.getAttribute("cartid");
        
        // Create an OrderModel object
        OrderModel orderModel = new OrderModel(null, username, order_date, orderStatus, totalAmount, payment);
        
        // Get cart items from session
        ArrayList<ProductModel> cartList = (ArrayList<ProductModel>) request.getSession().getAttribute("cartList");
        ArrayList<OrderProductModel> orderProducts = new ArrayList<>();
        
        // Create OrderProductModel objects for each product in the cart
        for (ProductModel p : cartList) {
            OrderProductModel op = new OrderProductModel();
            op.setProductId(p.getProductid());
            op.setQuantity(p.getStock());
            int quantity = Integer.parseInt(p.getStock());
            double price = Double.parseDouble(p.getPrice());
            double subtotal = quantity * price;
            op.setSubtotal(String.valueOf(subtotal));
            orderProducts.add(op);
        }
        
        // Insert order and order products into the database
        int result = databaseController.insertOrderAndProduct(orderModel, orderProducts, cartId);
        
        // Handle the result and redirect accordingly
        switch (result) {
            case 1:
                // Order successful, redirect to success page
                response.sendRedirect(request.getContextPath() + StringUtils.THANK_PAGE);
                break;
            case 0:
                // Order failed, redirect to error page
                request.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(request, response);
                break;
            default:
                // Unexpected error
                request.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(request, response);
                System.out.println("unexpected default");
                break;
        }
    }
}
