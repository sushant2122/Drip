<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/error.css" />
</head>
<body>
    <div class="error-page">
        <div class="content">
            <h1>404</h1>
            <h4>Oops! Page not found</h4>
            <p>
                Sorry, the page you're looking for doesn't exist or has been removed.
            </p>
            <div class="btns">
                <a href="${pageContent.request.contextPath}/Drip/pages/home.jsp">Return home</a>
            </div>
        </div>
    </div>
</body>
</html>
