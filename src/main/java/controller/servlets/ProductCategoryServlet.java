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

@WebServlet("/ProductCategoryServlet")
public class ProductCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController databaseController = new DatabaseController();

    public ProductCategoryServlet() {
        super();
    }

    // Handle GET requests for fetching products by category
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the category parameter from the request
        String category = request.getParameter("category");
        
        // Retrieve products based on the specified category
        List<ProductModel> productList = databaseController.getproductInfobyCategory(category);	
        
        // Set the productList attribute and forward to the product.jsp page for display
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/pages/product.jsp").forward(request, response);   
    }
}
