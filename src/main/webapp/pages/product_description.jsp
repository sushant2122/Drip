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
<title>Home</title>
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/Header.css" />
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/Footer.css" />
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/product_description.css" />
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>

<jsp:include page="Header.jsp"/>
        <section>
            <div class="container flex" style="height:auto;">
                <div class="left">
                    <div class="main_image">
                        <img src="${pageContext.request.contextPath}/resources/images/product/${productDesc.imageUrlFromPart}" class="slide">
                    </div>
                </div>
                <div class="right">
                    <h3>${productDesc.productname}</h3>
                    <h4><small>$</small>${productDesc.price}</h4>
                    <p>${productDesc.description}</p>
                    <h5>Number</h5>
                    <form id="addToBagForm" action="/Drip/AddCartServlet" method="POST">
                        <div class="add flex1">
                            <span class="decrement">-</span>
                            <label id="quantity">1</label>
                            <input type="hidden" id="quantityInput" name="quantity" value="1">
                            <input type="hidden" id="quantityInput" name="productid" value="${productDesc.productid}">
                            <span class="increment">+</span>
                        </div>
                        <button type="submit">Add to Bag</button>
                    </form>
                </div>
            </div>
        </section>
    <hr style="background-color:orange;">
    <jsp:include page="Footer.jsp"/>
    
    <script>
        // Function to handle incrementing the quantity
        document.querySelector('.increment').addEventListener('click', function () {
            let quantityLabel = document.getElementById('quantity');
            let quantityInput = document.getElementById('quantityInput');
            let quantity = parseInt(quantityLabel.textContent);
            quantityLabel.textContent = quantity + 1;
            quantityInput.value = quantity + 1; // Update the hidden input value
        });

        // Function to handle decrementing the quantity
        document.querySelector('.decrement').addEventListener('click', function () {
            let quantityLabel = document.getElementById('quantity');
            let quantityInput = document.getElementById('quantityInput');
            let quantity = parseInt(quantityLabel.textContent);
            if (quantity > 1) {
                quantityLabel.textContent = quantity - 1;
                quantityInput.value = quantity - 1; // Update the hidden input value
            }
        });
    </script>
</body>
</html>