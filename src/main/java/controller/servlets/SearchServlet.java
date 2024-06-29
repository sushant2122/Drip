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
import utils.StringUtils;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DatabaseController databasecontroller = new DatabaseController();

    public SearchServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the search query from the request parameter
        String search_bar = request.getParameter(StringUtils.SEARCH);
        // Prepare the search string for database query
        String search = "%" + search_bar + "%";
        System.out.println("Getting all products!");
        System.out.println(search);
        // Retrieve products matching the search query
        List<ProductModel> productslist = databasecontroller.getproductInfobySearch(search);
        // Set the search results as an attribute to be displayed in the view
        request.setAttribute("productList", productslist);
        // Forward the request to the product.jsp page for displaying search results
        request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This method is currently not used
        // You may want to implement search functionality for POST requests as well
        String search = request.getParameter(StringUtils.SEARCH);
        // You need to define what to do with the search query received via POST request
    }
}
