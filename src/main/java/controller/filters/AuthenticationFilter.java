package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import utils.StringUtils;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    DatabaseController dbController = new DatabaseController();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code goes here, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Cast request and response objects to HttpServletRequest and HttpServletResponse for type safety
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Get the requested URI
        String uri = req.getRequestURI();
        System.out.println("Running filter in " + uri);
        boolean ishome = uri.endsWith(StringUtils.HOME_PAGE);

        // Allow access to static resources (CSS) and the index page without checking login
        if (uri.endsWith(".css") || uri.endsWith(".js") ||  uri.endsWith(".png") ||  uri.endsWith(".jpeg") ||  uri.endsWith("https://smtpjs.com/v3/smtp.js") ) {
            chain.doFilter(request, response);
            return;
        }

        // Separate flags for login, login/logout servlets, and register page/servlet for better readability
        
        boolean isLogin = uri.endsWith(StringUtils.LOGIN_PAGE);
        boolean isLoginServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGIN);
        boolean isLogoutServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGOUT);
        boolean isRegisterPage = uri.endsWith(StringUtils.REGISTER_PAGE);
        boolean isRegisterServlet = uri.endsWith(StringUtils.REGISTER_SERVLET);
        boolean isadmin = uri.endsWith(StringUtils.ADMIN_PAGE); 
        boolean isadminproduct = uri.endsWith(StringUtils.ADMIN_PRODUCT_PAGE);
        boolean isadminadd = uri.endsWith(StringUtils.ADMIN_ADD_PAGE);
        boolean isadminorder = uri.endsWith(StringUtils.ADMIN_ORDER_PAGE);
        boolean isadminaddServlet = uri.endsWith(StringUtils.ADMIN_ADD_SERVLET);
        boolean isadminproductlistServlet = uri.endsWith(StringUtils.ADMIN_PRODUCTLIST_SERVLET);
        boolean isadminorderlistServlet = uri.endsWith(StringUtils.ADMIN_ORDER_SERVLET);
        boolean iserrorpage = uri.endsWith(StringUtils.ERROR_PAGE);
        boolean isuserprofile = uri.endsWith(StringUtils.USER_PROFILE_PAGE);
        boolean isupdateorder = uri.endsWith(StringUtils.SERVLET_URL_UPDATEORDER);
        boolean ismodifyproduct = uri.endsWith(StringUtils.SERVLET_URL_MODIFYPRODUCT);
        boolean isdashboard = uri.endsWith(StringUtils.SERVLET_URL_DASHBOARD);
        boolean isadminupdate = uri.endsWith(StringUtils.ADMIN_UPDATE_PAGE);
        boolean isuserupdate = uri.endsWith(StringUtils.URL_ADMIN_UPDATE);
        // Check if a session exists and if the username attribute is set to determine login status
        HttpSession session = req.getSession(false); // Don't create a new session if one doesn't exist
        boolean isLoggedIn = session != null && session.getAttribute(StringUtils.USER_NAME) != null;
        
        if (session == null) {
        	if (ishome || isLoginServlet|| isLogin || isRegisterPage || isRegisterServlet || iserrorpage) {
                chain.doFilter(request, response);
                return;
        	}
                else {
                	res.sendRedirect(req.getContextPath() + StringUtils.HOME_PAGE);
                }
        	}
        else {

        // Redirect to login if user is not logged in and trying to access a protected resource
        if (!isLoggedIn && !(isLogin || isLoginServlet || isRegisterPage || isRegisterServlet || ishome)) {
            res.sendRedirect(req.getContextPath() + StringUtils.LOGIN_PAGE);
        } else {
            // Allow access to the logout servlet regardless of user's role or page being accessed
            if (isLogoutServlet) {
                chain.doFilter(request, response);
                return;
            }

            if (session != null) {
                String userRole = (String) session.getAttribute("userrole");
                if (userRole != null) {
                    if (userRole.equals("admin")) {
                    	if (isadmin || isadminadd|| isadminorder || isadminproduct || isadminaddServlet || isadminproductlistServlet || isadminorderlistServlet || isuserprofile || isupdateorder || ismodifyproduct || isdashboard || isadminupdate || isuserupdate) {
                            chain.doFilter(request, response);
                            return;
                        }
                    	else {
                        // Allow access to admin panel only for users with the role "admin"
                        if (!isadmin) {
                            // Redirect admin users to the admin panel if they're not already there
                            res.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_DASHBOARD);
                            return;
                        }
                    } 
                    }else {
                        // Users with role "user" cannot visit the admin page
                        if (isLoggedIn && isadmin || isadminadd|| isadminorder || isadminproduct || isadminaddServlet || isadminproductlistServlet || isadminorderlistServlet || isupdateorder || ismodifyproduct|| isdashboard || isRegisterPage || isRegisterServlet || isLogin || isLoginServlet || isadminupdate) {
                            // Redirect user from admin page to home page
                            res.sendRedirect(req.getContextPath() + StringUtils.HOME_PAGE);
                            return;
                        }
                    }
                }
            }

            // Allow access to the requested resource if user is logged in or accessing unprotected resources
            chain.doFilter(request, response);
        }
    }
    }

    @Override
    public void destroy() {
     
    }
}
