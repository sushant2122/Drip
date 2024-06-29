<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
        
    <% 
        // Check the user role to determine the appropriate stylesheet
        String role = (String) session.getAttribute("userrole");
        if ("admin".equals(role)) {
    %>
        <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/adminprofile.css" />
    <% } else { %>
        <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/userprofile.css" />
    <% } %>
</head>

<body>
    <div class="container">
        <h1>Account <span class="head_ing">Settings</span> </h1>
        <form action="/Drip/UserUpdateServlet" method="post" enctype="multipart/form-data">
            <div class="prfo_img">
                <div class="profile">
                    <img src="${pageContext.request.contextPath}/resources/images/user/${user.imageUrlFromPart}" alt="Profile Picture" class="img_holder">
                    <div class="upload_img">
                        <label class="upload_profile">
                            Upload new photo
                            <input type="file" class="upload_file" id="user_img" name="user_img" accept="image/*" required>
                        </label>
                        <p class="upload_desc">Allowed JPG, GIF, or PNG.</p>
                    </div>
                </div>
                <hr>
                <form action="/Drip/UserUpdateServlet" method="post">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <label for="firstName">First Name:</label>
                                <input type="text" id="firstName" name="firstName" value="${user.firstName}">
                            </div>
                            <div class="col">
                                <label for="lastName">Last Name:</label>
                                <input type="text" id="lastName" name="lastName" value="${user.lastName}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label for="email">Email:</label>
                                <input type="email" id="email" name="email" class="form-control" value="${user.email}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label for="birthday">Birthday:</label>
                                <input type="date" id="birthday" name="birthday" value="${user.dob}"><br>
                            </div>
                            <div class="col">
                                <label for="phoneNumber">Phone Number:</label>
                                <input type="tel" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}">
                            </div>
                        </div>
                        <div class="Save_button">
                            <button type="submit" class="sv_btn">Save changes</button>
                            <a href="${pageContent.request.contextPath}/Drip/pages/home.jsp"><button type="button">Cancel</button></a>
                        </div>
                    </div>
                </form>
            </div>
        </form>
    </div>
</body>
</html>
