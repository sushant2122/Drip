package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.StringUtils;
import controller.DatabaseController;
import models.ProductModel;

@WebServlet("/ModifyProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10, // 10MB
                 maxRequestSize = 1024 * 1024 * 50)
public class ModifyProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController databaseController = new DatabaseController(); // Database controller instance

    public ModifyProductServlet() {
        super();
    }

    // Handle GET requests for modifying product details
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String getId = request.getParameter(StringUtils.PRODUCTUPDATEID);
        // Fetch product details for update
        ProductModel products = databaseController.getproductInforupdate(getId);
        request.setAttribute("product", products);
        request.getRequestDispatcher("/pages/updateproduct.jsp").forward(request, response);
    }

    // Handle POST requests for modifying or deleting products
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteId = request.getParameter(StringUtils.PRODUCTDELETE_ID);
        String updateId = request.getParameter("updateid");
        System.out.println(updateId);
        if (updateId != null && !updateId.isEmpty()) {
            // If updateId is provided, invoke doPut method for updating product
            doPut(request, response);
        }
        if (deleteId != null && !deleteId.isEmpty()) {
            // If deleteId is provided, invoke doDelete method for deleting product
            doDelete(request, response);
        }
    }

    // Handle PUT requests for updating product details
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("put triggered");
        // Get parameters for updating product
        String id = req.getParameter("updateid");
        String model = req.getParameter(StringUtils.PRODUCT_MODEL);
        String productName = req.getParameter(StringUtils.PRODUCT_NAME);
        String price = req.getParameter(StringUtils.PRODUCT_PRICE);
        String stock = req.getParameter(StringUtils.PRODUCT_STOCK);
        String description = req.getParameter(StringUtils.PRODUCT_DESCRIPTION);
        String category = req.getParameter(StringUtils.PRODUCT_CATEGORY);
        Part imagePart = req.getPart(StringUtils.PRODUCT_IMAGE);

        // Create ProductModel instance with updated details
        ProductModel productModel = new ProductModel(id, model, productName, price, stock, description, category, null, imagePart);

        // Save uploaded image and update product details
        String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
        String fileName = productModel.getimageUrlFromPart();
        if (!fileName.isEmpty() && fileName != null)
            imagePart.write(savePath + fileName);
        databaseController.updateProductDetails(productModel);
        // Redirect to admin product list page after update
        resp.sendRedirect("/Drip/AdminProductlistServlet");
    }

    // Handle DELETE requests for deleting products
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("product delete triggered");
        // Attempt to delete product
        if (databaseController.deleteProduct(req.getParameter(StringUtils.PRODUCTDELETE_ID))) {
            // If deletion successful, set success message and redirect to admin product list page
            req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
            resp.sendRedirect(req.getContextPath() + StringUtils.ADMIN_PRODUCTLIST_SERVLET);
        } else {
            // If deletion fails, set error message and forward to error page
            req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
            req.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(req, resp);
        }
    }
}
