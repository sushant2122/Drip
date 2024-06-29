<%@page import="utils.StringUtils"  %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register User</title>
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/register.css" />
</head>
<body>
    <div class="container">
        <form action="/Drip/RegisterServlet" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col">
                    <h1>Sign up</h1>
                </div>
            </div>
            <%-- Display error message if it exists --%>
            <%
            String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
            if (errorMessage != null && !errorMessage.isEmpty()) {
            %>
            <p class="error-message" style="text-align: center; color: red;"><%=errorMessage%></p>
            <% } %>

            <div class="row">
                <div class="col">
                    <label for="username">User Name:</label>
                    <input type="text" id="username" name="username">
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName">
                </div>
                <div class="col">
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName">
                </div>
            </div>

            <div class="gen">
                <label for="gender">Gender:</label>&ensp;&ensp;
                <input type="radio" id="male" name="gender" value="male">
                <label for="male">Male</label>&ensp;&ensp;&ensp;&ensp;
                <input type="radio" id="female" name="gender" value="female">
                <label for="female">Female</label>
            </div>

            <div class="row">
                <div class="col">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email">
                </div>
                <div class="col">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="tel" id="phoneNumber" name="phoneNumber">
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <label for="birthday">Birthday:</label>
                    <input type="date" id="birthday" name="birthday"><br>
                </div>
                <div class="col">
                    <label for="role">Role:</label>
                    <select id="role" name="role">
                        <option value="user">User</option>
                        <option value="admin">Admin</option>
                    </select><br>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="col">
                    <label for="retypePassword">Retype Password:</label>
                    <input type="password" id="retypePassword" name="retypePassword" required>
                </div>
            </div>

            <div class="col">
                <p><span class="pw">Use at least one letter, one numeral, one special character, and seven characters.</span></p>
            </div>

            <div class="col">
                <label for="image">Profile Picture</label>
                <input type="file" id="image" name="userimg" style="color:white" accept="image/*" required><br>
            </div>

            <button type="submit">Submit</button>

            <div class="row">
                <div class="col">
                    <br>
                    <a href="${pageContent.request.contextPath}/Drip/pages/login.jsp">Already have an account? Login Here.</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
