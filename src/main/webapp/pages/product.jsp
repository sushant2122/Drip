<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page import="java.util.List"%>
<%@ page import="models.ProductModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product</title>
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/Header.css" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/Footer.css" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/product.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
<jsp:include page="Header.jsp"/>
<h2 class="category"><span class="span">Our</span> Products</h2>
<div class="container">
<%
// Accessing the attribute using scriptlet
List<ProductModel> productList = (List<ProductModel>) request.getAttribute("productList");
// Use productList as needed
%>
<% if (productList == null || productList.isEmpty()) { %>
    <div class="middle">
        <h1>No products found.</h1>
        <hr style="Margin-bottom:240px;">
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br>
<% } else { %>
    <c:forEach var="product" items="${productList}">
        <a class="desc">
            <div class="card" >
                <img src="${pageContext.request.contextPath}/resources/images/product/${product.imageUrlFromPart}"  style="width:100%" class="img_holder">
                <h1>${product.productname}</h1>
                <p class="price">$${product.price}</p>
                <form method="post" action="/Drip/AddCartServlet">
                	<input type="hidden" name="productid" value="${product.productid}" />
                    <input type="hidden" name="quantity" value="1" />
                    <button type="submit">Add to cart</button>
                </form>
                <br>
                <form method="get" action="/Drip/DescriptionServlet">
                	<input type="hidden" name="prod_id" value="${product.productid}" />
                    <button type="submit">View product details</button>
                </form>
            </div>
        </a>
    </c:forEach>
<% } %>
</div>
<jsp:include page="Footer.jsp"/>
</body>
</html>
