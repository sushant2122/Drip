package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.DatabaseController;
import utils.StringUtils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController dbController = new DatabaseController(); // Creating an instance of DatabaseController

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Attempting user login
        int loginResult = dbController.getUserLoginInfo(userName, password);

        switch(loginResult) {
            case 1 -> {
                // Successful login
                String userRole = dbController.getUserRole(userName);
                String cartId = dbController.getcartid(userName);
                
                // Creating session and setting session attributes
                HttpSession userSession = request.getSession();
                userSession.setAttribute("username", userName);
                userSession.setAttribute("userrole", userRole);
                userSession.setAttribute("cartid", cartId);
                userSession.setMaxInactiveInterval(30*60); // Setting session timeout
                
                // Creating cookies
                Cookie userCookie= new Cookie("user", userName);
                Cookie roleCookie= new Cookie("role", userRole);
                Cookie cartCookie= new Cookie("cart", cartId);
                userCookie.setMaxAge(30*60); // Setting cookie expiration
                
                // Adding cookies to response
                response.addCookie(cartCookie);
                response.addCookie(userCookie);
                response.addCookie(roleCookie);
                
                // Redirecting based on user role
                if (userRole.equals("admin")) {
                    response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_DASHBOARD);
                } else {
                    response.sendRedirect(request.getContextPath() + StringUtils.HOME_PAGE);
                }
            }
            case -2 -> {
                // Username does not exist
                request.setAttribute("errorMessage", StringUtils.USERNAME_DONT_EXIST_ERROR);
                request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
            }
            case 0 -> {
                // Invalid password
                request.setAttribute("errorMessage", userName + StringUtils.LOGIN_ERROR);
                request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
            }
            case -1 -> {
                // Server error
                request.setAttribute("errorMessage", StringUtils.SERVER_ERROR_MESSAGE);
                request.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(request, response);
            }
            default -> {
                // Unexpected error
                request.setAttribute("errorMessage", StringUtils.SERVER_ERROR_MESSAGE);
                request.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(request, response);
            }
        }
    }
}
