package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import models.OrderModel;

@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private DatabaseController databaseController = new DatabaseController(); // Creating an instance of DatabaseController
    
    public AdminOrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // Retrieving username from session
        String user = (String) request.getSession().getAttribute("username");
        System.out.println("Getting all orders!");
        
        // Getting list of orders for admin
        List<OrderModel> ordersList = databaseController.getOrderInfobyAdmin(user);
        
        // Setting attribute for admin order list and forwarding to admin order page
        request.setAttribute("adminorderList", ordersList);
        request.getRequestDispatcher("/pages/admin_order.jsp").forward(request, response);
    }
}
