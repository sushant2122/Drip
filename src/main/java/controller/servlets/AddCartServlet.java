package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.DatabaseController;
import models.CartModel;
import models.ProductModel;
import utils.StringUtils;


@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Creating an instance of DatabaseController for database operations
    DatabaseController dbController = new DatabaseController();
    
    public AddCartServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // Retrieving parameters from the request
        String productid = request.getParameter("productid");
        String cartid = (String) request.getSession().getAttribute("cartid");
        String quantity = request.getParameter("quantity");
        
        if (cartid == null) {
            // If cart ID is null, forwarding request to error page
            request.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(request, response);
            System.out.println("cart_id null");
        } else {
            // If cart ID is not null, creating CartModel instance
            CartModel cartModel = new CartModel(cartid, productid, quantity);
            // Adding cart item to the database and getting result
            int result = dbController.addtoCart(cartModel);
            
            // Handling different results
            switch(result) {
                case 1 -> {
                    // If successful, redirecting to cart list page
                    response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_CARTLIST);
                }
                default -> {
                    // If unexpected result, setting error message and forwarding to error page
                    request.setAttribute("errorMessage", StringUtils.SERVER_ERROR_MESSAGE);
                    request.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(request, response);
                    System.out.println("unexpected defaultt");
                }
            }
        }
    }
}
