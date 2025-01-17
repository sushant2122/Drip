package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.DatabaseController;
import models.ProductModel;
import models.UserModel;
import utils.StringUtils;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,    // 10MB
                 maxRequestSize = 1024 * 1024 * 50)
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Creating an instance of DatabaseController for database operations
    DatabaseController dbController = new DatabaseController();
    
    public AddProductServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // Retrieving parameters from the request
        String productid = null; // Assuming product ID is generated by the system
        String model = request.getParameter(StringUtils.PRODUCT_MODEL);
        String productName = request.getParameter(StringUtils.PRODUCT_NAME);
        String price = request.getParameter(StringUtils.PRODUCT_PRICE);
        String stock = request.getParameter(StringUtils.PRODUCT_STOCK);
        String description = request.getParameter(StringUtils.PRODUCT_DESCRIPTION);
        String category = request.getParameter(StringUtils.PRODUCT_CATEGORY);
        String username = (String) request.getSession().getAttribute("username");
        Part imagePart = request.getPart(StringUtils.PRODUCT_IMAGE);
        
        // Creating a ProductModel instance with retrieved parameters
        ProductModel productModel = new ProductModel(productid, model, productName, price, stock, description, category, username, imagePart);
        
        // Saving the image file
        String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
        String fileName = productModel.getimageUrlFromPart();
        if (!fileName.isEmpty() && fileName != null)
            imagePart.write(savePath + fileName);
        
        // Adding the product to the database and getting result
        int result = dbController.addProduct(productModel);
        
        // Handling different results
        switch (result) {
            case 1 -> {
                // If successful, redirecting to admin product list page
                response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_PRODUCTLIST_SERVLET);
            }
            case 0 -> {
                // If failed, forwarding to admin add page
                request.getRequestDispatcher(StringUtils.ADMIN_ADD_PAGE).forward(request, response);
            }
            default -> {
                // If unexpected result, forwarding to error page
                request.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(request, response);
                System.out.println("unexpected default");
            }
        }
    }
}
