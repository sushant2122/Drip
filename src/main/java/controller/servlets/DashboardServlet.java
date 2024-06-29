package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import models.DashboardModel;
import models.ProductModel;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController databaseController = new DatabaseController(); // Creating an instance of DatabaseController
    
    public DashboardServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // Retrieving username from session
        String username = (String) request.getSession().getAttribute("username");
        
        // Getting dashboard details for the admin user
        DashboardModel dashList = databaseController.getdashboarddetails(username);
        
        // Setting attribute for dashboard details and forwarding to admin dashboard page
        request.setAttribute("dashList", dashList);
        request.getRequestDispatcher("/pages/admin.jsp").forward(request, response);
    }
}
