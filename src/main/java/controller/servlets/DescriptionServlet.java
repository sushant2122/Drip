package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import models.ProductModel;

@WebServlet("/DescriptionServlet")
public class DescriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController databaseController = new DatabaseController(); // Creating an instance of DatabaseController
    
    public DescriptionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieving product ID from request parameter
        String prodId = request.getParameter("prod_id");
        System.out.println(request.getParameter("prod_id"));
        
        // Getting product details by ID
        ProductModel product = databaseController.getproductInfobyId(prodId);
        System.out.println(product);
        
        // Setting attribute for product description and forwarding to product description page
        request.setAttribute("productDesc", product);
        request.getRequestDispatcher("/pages/product_description.jsp").forward(request, response);
    }
}
