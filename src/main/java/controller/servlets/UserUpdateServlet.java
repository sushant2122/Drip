package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.DatabaseController;
import models.UserModel;
import utils.StringUtils;

@WebServlet("/UserUpdateServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                maxFileSize = 1024 * 1024 * 10, // 10MB
                maxRequestSize = 1024 * 1024 * 50)
public class UserUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController databasecontroller = new DatabaseController();

    public UserUpdateServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the current user's session
        HttpSession session = request.getSession(false);
        String user = (String) request.getSession().getAttribute("username");
        
        // Retrieve user details from the database
        UserModel usersdet = databasecontroller.getuserInfobyId(user);
        
        // Set user details as an attribute in the request
        request.setAttribute("user", usersdet);
        
        // Forward the request to the userprofile.jsp page for rendering
        request.getRequestDispatcher("/pages/userprofile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invoke the doPut method to handle the update operation
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PUT triggered");
        // Retrieve the current user's session
        HttpSession session = req.getSession(false);
        String user = (String) req.getSession().getAttribute("username");

        // Retrieve user details from the request parameters
        String firstName = req.getParameter(StringUtils.FIRST_NAME1);
        String lastName = req.getParameter(StringUtils.LAST_NAME1);
        String dobString = req.getParameter(StringUtils.BIRTHDAY1);
        LocalDate dob = null;
        if (dobString != null && !dobString.isEmpty()) {
            dob = LocalDate.parse(dobString);
        }
        String email = req.getParameter(StringUtils.EMAIL1);
        String phoneNumber = req.getParameter(StringUtils.PHONE_NUMBER1);

        // Retrieve the image part from the request
        Part imagePart = req.getPart(StringUtils.USER_IMAGE1);

        // Create a UserModel object with the updated details
        UserModel usermodel = new UserModel(firstName, lastName, dob, null, email, phoneNumber, null, user, null, imagePart);

        // Define the save path for the image
        String savePath = StringUtils.IMAGE_DIR_SAVE_PATH_2;
        String fileName = usermodel.getimageUrlFromPart();
        if (!fileName.isEmpty() && fileName != null)
            imagePart.write(savePath + fileName);

        // Update the user details in the database
        databasecontroller.updateUserDetails(usermodel);

        // Redirect back to the UserUpdateServlet to display the updated user profile
        resp.sendRedirect("/Drip/UserUpdateServlet");
    }
}
