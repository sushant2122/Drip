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

@WebServlet("/UserOrderListServlet")
public class UserOrderListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController databasecontroller = new DatabaseController();

    public UserOrderListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user's session
        HttpSession session = request.getSession(false);
        
        // Get the username from the session
        String user = (String) request.getSession().getAttribute("username");
        
        // Retrieve the orders associated with the user from the database
        List<OrderModel> orderslist = databasecontroller.getorderInfobyuser(user);
        
        // Set the list of orders as an attribute in the request
        request.setAttribute("userorderList", orderslist);
        
        // Forward the request to the user_orderlist.jsp page to display the orders
        request.getRequestDispatcher("/pages/user_orderlist.jsp").forward(request, response);
    }
}
