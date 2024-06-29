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

@WebServlet("/CartListServlet")
public class CartListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    private DatabaseController databaseController = new DatabaseController(); // Creating an instance of DatabaseController
    
    public CartListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Getting all cart products!");
        HttpSession session = request.getSession(false);
        
        // Retrieving username from session
        String username = (String) request.getSession().getAttribute("username");
        
        // Getting list of products in cart for the user
        List<ProductModel> cartList = databaseController.getproductInfobyCart(username);
        
        // Setting attribute for cart list and forwarding to cart page
        request.setAttribute("cartList", cartList);
        request.getSession().setAttribute("cartList", cartList); // Setting cart list in session
        request.getRequestDispatcher("/pages/cart.jsp").forward(request, response);   
    }

}
