package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import utils.StringUtils;

@WebServlet("/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DatabaseController dbController;

    public DeleteCartServlet() {
        this.dbController = new DatabaseController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String deleteId = request.getParameter(StringUtils.DELETE_CART_ID);
        
        if (deleteId != null && !deleteId.isEmpty()) {
            // If deleteId is provided, invoke doDelete method
            doDelete(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("delete triggered");
        String deleteId = req.getParameter(StringUtils.DELETE_CART_ID);
        HttpSession session = req.getSession();
        String cartId = (String) session.getAttribute("cartid");
        
        if (deleteId != null && !deleteId.isEmpty()) {
            // If deleteId is provided and not empty, proceed with deletion
            if (dbController.deleteCartItem(deleteId, cartId) == 1) {
                // If deletion is successful, redirect to cart list page
                System.out.println(deleteId);
                resp.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_CARTLIST);
            } else {
                // If deletion fails, forward to error page
                req.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(req, resp);
            }
        } else {
            // If no deleteId is provided, set error message and forward to error page
            req.setAttribute(StringUtils.MESSAGE_ERROR, "No deleteId provided.");
            req.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(req, resp);
        }
    }
}
