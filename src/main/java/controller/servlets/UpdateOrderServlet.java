package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DatabaseController;
import models.ProductModel;
import utils.StringUtils;

@WebServlet("/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    private DatabaseController databasecontroller = new DatabaseController();
    
    public UpdateOrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Currently, there's no implementation for handling GET requests
        // You may want to add functionality here if needed
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String orderId = request.getParameter(StringUtils.ORDERID);
    	if (orderId != null && !orderId.isEmpty()) {
            // If updateId is provided, invoke doPut method for updating product
            doPut(request, response);
        }
    }
    

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("put triggered");
        // Get the order ID and new status from request parameters
        String orderId = request.getParameter(StringUtils.ORDERID); // Replace 123 with the actual order ID
        String newStatus = request.getParameter(StringUtils.STATUS); // Replace "shipped" with the new status
        
        // Call the method to update the order status in the database
        boolean success = databasecontroller.updateOrderStatus(orderId, newStatus);
        
        // Redirect the user based on the success of the operation
        if (success) {
            // If the update was successful, redirect to the admin order servlet
            request.getRequestDispatcher(StringUtils.ADMIN_ORDER_SERVLET).forward(request, response);
        } else {
            // If there was an error, forward to the error page
            request.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(request, response);
            System.out.println("unexpected defaultt");
        }
    }

}
