<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page import="java.util.List"%>
<%@ page import="models.ProductModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" type="text/css"
        href="${pageContent.request.contextPath}/Drip/stylesheets/user_orderproductlist.css" />
    <link rel="stylesheet" type="text/css"
        href="${pageContent.request.contextPath}/Drip/stylesheets/Header.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <jsp:include page="Header.jsp"/>

    <div class="wrapper">
        <h1 style="color:#fff;">Ordered <span style="color:orange;">Product details</span></h1>
        <div class="project">
            <div class="shop">
                <%
                // Accessing the attribute using scriptlet
                List<ProductModel> opList = (List<ProductModel>) request.getAttribute("opList");
                // Use productList as needed
                %>
                <% if (opList == null || opList.isEmpty()) { %>
                    <div class="middle">
                        <h1>The Ordered Products might have been removed.</h1>
                        <h2 style="text-align:center;">Sorry for the inconvenience.</h2>
                        <hr style="Margin-bottom:240px;">
                    </div>
                <% } else { %>			
                    <c:forEach var="product" items="${opList}">
                        <div class="box">
                            <img src="${pageContext.request.contextPath}/resources/images/product/${product.imageUrlFromPart}">
                            <div class="content">
                                <h3>${product.productname}</h3>
                                <h4>Model no.:${product.modelno}</h4>
                                <h4>Paid amount:$${product.price}</h4>
                                <h4>Ordered Quantity: ${product.stock}</h4>
                            </div>
                        </div>
                    </c:forEach>
                <% } %>	
            </div>
        </div>
    </div>
</body>
</html>
