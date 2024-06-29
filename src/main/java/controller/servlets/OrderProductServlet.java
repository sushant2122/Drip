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
import utils.StringUtils;

@WebServlet("/OrderProductServlet")
public class OrderProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DatabaseController databaseController = new DatabaseController(); // Creating an instance of DatabaseController

    public OrderProductServlet() {
        super();
    }

    // Handle GET requests for fetching ordered products
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Getting all ordered products!");
        // Get the order ID from request parameter
        String orderId = request.getParameter(StringUtils.ORDER_ID);
        // Fetch ordered product details based on order ID
        List<ProductModel> orderProductList = databaseController.getorderproductInfo(orderId);
        // Set attribute for ordered product list and forward to user order product list page
        request.setAttribute("opList", orderProductList);
        request.getRequestDispatcher("/pages/user_orderproductlist.jsp").forward(request, response);
    }
}
