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
import models.ProductModel;

@WebServlet("/AdminProductlistServlet")
public class AdminProductlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private DatabaseController databaseController = new DatabaseController(); // Creating an instance of DatabaseController
    
    public AdminProductlistServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // Retrieving username from session
        String user = (String) request.getSession().getAttribute("username");
        System.out.println("Getting all products!");
        
        // Getting list of products for admin
        List<ProductModel> productsList = databaseController.getproductInfobyAdmin(user);
        
        // Setting attribute for admin product list and forwarding to admin product page
        request.setAttribute("adminproductList", productsList);
        request.getRequestDispatcher("/pages/admin_product.jsp").forward(request, response);
    }
}
