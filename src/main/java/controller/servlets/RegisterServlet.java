package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DatabaseController;
import models.UserModel;
import utils.StringUtils;

@WebServlet("/RegisterServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    DatabaseController dbController = new DatabaseController();

    public RegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve user registration form parameters
        String username = request.getParameter(StringUtils.USER_NAME);
        String firstName = request.getParameter(StringUtils.FIRST_NAME);
        String lastName = request.getParameter(StringUtils.LAST_NAME);
        String dobString = request.getParameter(StringUtils.BIRTHDAY);
        LocalDate dob = LocalDate.parse(dobString);
        String gender = request.getParameter(StringUtils.GENDER);
        String email = request.getParameter(StringUtils.EMAIL);
        String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
        String role = request.getParameter(StringUtils.ROLE);
        String password = request.getParameter(StringUtils.PASSWORD);
        String retypePassword = request.getParameter("retypePassword");
        Part imagePart = request.getPart(StringUtils.USER_IMAGE);

        // Validate user input
        // Username validation using regex
        String usernameRegex = "^[a-zA-Z0-9]{6,}$";
        Pattern usernamePattern = Pattern.compile(usernameRegex);
        Matcher usernameMatcher = usernamePattern.matcher(username.trim());
        boolean isUsernameValid = usernameMatcher.matches();

        if (!isUsernameValid) {
            request.setAttribute("errorMessage", StringUtils.USERNAME_ERROR);
            request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
            return;
        }

        // First name validation using regex
        String firstNameRegex = "^[a-zA-Z]+$";
        Pattern firstNamePattern = Pattern.compile(firstNameRegex);
        Matcher firstNameMatcher = firstNamePattern.matcher(firstName.trim());
        boolean isFirstNameValid = firstNameMatcher.matches();

        if (!isFirstNameValid) {
            request.setAttribute("errorMessage", StringUtils.FIRST_NAME_ERROR);
            request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
            return;
        }

        // Last name validation using regex
        String lastNameRegex = "^[a-zA-Z]+$";
        Pattern lastNamePattern = Pattern.compile(lastNameRegex);
        Matcher lastNameMatcher = lastNamePattern.matcher(lastName.trim());
        boolean isLastNameValid = lastNameMatcher.matches();

        if (!isLastNameValid) {
            request.setAttribute("errorMessage", StringUtils.LAST_NAME_ERROR);
            request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
            return;
        }

        // Gender validation
        if (gender == null) {
            request.setAttribute("errorMessage", StringUtils.GENDER_ERROR);
            request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
            return;
        }

        // Phone number validation using regex
        String phoneRegex = "^[0-9]{10,10}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher phoneMatcher = phonePattern.matcher(phoneNumber);
        boolean isPhoneValid = phoneMatcher.matches();

        if (!isPhoneValid) {
            request.setAttribute("errorMessage", StringUtils.PHONE_ERROR);
            request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
            return;
        }

        // Date of birth validation
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob, currentDate);
        int age = period.getYears();

        if (!dob.isBefore(currentDate) || age <= 6) {
            request.setAttribute("errorMessage", StringUtils.BIRTHDAY_ERROR);
            request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
            return;
        }

        // Password validation using regex
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        boolean isPasswordValid = passwordMatcher.matches();

        if (!isPasswordValid) {
            request.setAttribute("errorMessage", StringUtils.PASSWORD_ERROR);
            request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
            return;
        }

        // Check if passwords match
        if (!password.equals(retypePassword)) {
            request.setAttribute("errorMessage", StringUtils.PASSWORD_UNMATCHED_ERROR);
            request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
            return;
        }

        // Create a UserModel object with user details
        UserModel userModel = new UserModel(firstName, lastName, dob, gender, email, phoneNumber, role, username, password, imagePart);

        // Save user's profile picture to the server
        String savePath = StringUtils.IMAGE_DIR_SAVE_PATH_2;
        String fileName = userModel.getimageUrlFromPart();
        if (!fileName.isEmpty() && fileName != null)
            imagePart.write(savePath + fileName);

        // Add user to the database
        int result = dbController.addUser(userModel);

        // Handle registration result
        switch (result) {
            case 1:
                // Successful registration, redirect to login page
                request.setAttribute("errorMessage", StringUtils.SUCESS_REGISTER_MESSAGE);
                response.sendRedirect(request.getContextPath() + StringUtils.LOGIN_PAGE);
                break;
            case 0:
                // Registration failed, show error message on register page
                request.setAttribute("errorMessage", StringUtils.REGISTER_ERROR_MESSAGE);
                request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
                break;
            case -1:
                // Server error
                request.setAttribute("errorMessage", StringUtils.SERVER_ERROR_MESSAGE);
                request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
                System.out.println("Returned -11111");
                break;
            case -2:
                // Username already exists
                request.setAttribute("errorMessage", StringUtils.USERNAME_EXIST_ERROR);
                request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
                System.out.println("Username exists");
                break;
            case -3:
                // Email already exists
                request.setAttribute("errorMessage", StringUtils.EMAIL_EXIST_ERROR);
                request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
                System.out.println("Email exists");
                break;
            case -4:
                // Phone number already exists
                request.setAttribute("errorMessage", StringUtils.PHONE_EXIST_ERROR);
                request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
                System.out.println("Phone exists");
                break;
            default:
                // Unexpected error
                request.setAttribute("errorMessage", StringUtils.SERVER_ERROR_MESSAGE);
                request.getRequestDispatcher(StringUtils.ERROR_PAGE).forward(request, response);
                System.out.println("Unexpected default");
                break;
        }
    }
}
