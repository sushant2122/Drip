<%@page import="utils.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/login.css" />
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
    if (errorMessage != null && errorMessage.equals(StringUtils.USERNAME_DONT_EXIST_ERROR)){
%>
    <div class="alert">
        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
        <strong>Don't have an account?</strong>
        <ol>
            <li>Click on the don't have account link, Register now. It will redirect you to the sign-up page.</li>
            <li>Give a unique username.</li>
            <li>Then fill in your first name and last name.</li>
            <li>Click on the gender male or female radio box.</li>
            <li>Enter your number and email which haven't been used yet.</li>
            <li>Enter your age, should be at least 6+ years old from the current date.</li>
            <li>Select your role as user if normal user, if you are a seller then select admin.</li>
            <li>Enter a strong password with at least one special character, one number, one capital letter, and at least 7 digits of code.</li>
            <li>At last, click on the sign-up button.</li>
        </ol>
    </div>
<%
    }
%>
	<form action="/Drip/LoginServlet" method="get">
	    <h1>Login</h1>
	    <%-- Display error message if it exists --%>
	    <%
	        if (errorMessage != null && !errorMessage.isEmpty()) {
	    %>
	        <p class="error-message" style="text-align: center; color: red;">${errorMessage}</p>
	    <%
	        }
	    %>
	    <label for="username">Username:</label><br>
	    <input type="text" name="username"><br>
	    <label for="password">Password:</label><br>
	    <input type="password" id="password" name="password" required><br><br>
	    <button type="submit">Login</button>
	    <br><br>
	    <a href="${pageContent.request.contextPath}/Drip/pages/register.jsp">Don't have an account? Register now!</a>
	</form>
</body>
</html>
