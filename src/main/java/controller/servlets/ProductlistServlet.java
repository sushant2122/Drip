package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import models.ProductModel;

@WebServlet("/ProductlistServlet")
public class ProductlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController databaseController = new DatabaseController(); // Creating an instance of DatabaseController

    public ProductlistServlet() {
        super();
    }

    // Handle GET requests for fetching all products
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Getting all products!");
        
        // Retrieve all products from the database
        List<ProductModel> productList = databaseController.getAllproductInfo();
        
        // Set productList attribute and forward to product.jsp for display
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
    }
}
