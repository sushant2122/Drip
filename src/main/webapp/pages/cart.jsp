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
	href="${pageContent.request.contextPath}/Drip/stylesheets/cart.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContent.request.contextPath}/Drip/stylesheets/Header.css" />
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<jsp:include page="Header.jsp"/>
  <div class="wrapper">
        <h1 style="color:#fff;">Shopping <span style="color:orange;">Cart</span></h1>
        <div class="project">
            <div class="shop">
            
               
                
<%
// Accessing the attribute using scriptlet
List<ProductModel> cartList = (List<ProductModel>) request.getAttribute("cartList");
// Use productList as needed
%>
<% if (cartList == null || cartList.isEmpty()) { %>
<div class="middle">

    <h1>Add something to your cart.</h1>
    <hr style="Margin-bottom:240px;">
  </div>
<%}else{ %>
			
  <c:set var="totalPrice" value="0" />
 <c:forEach var="product" items="${cartList}">
				 <div class="box">
                    <img src="${pageContext.request.contextPath}/resources/images/product/${product.imageUrlFromPart}">
                    <div class="content">
                        <h3>${product.productname}</h3>
                        <c:set var="productTotalPrice">
							    <c:out value="${product.stock * product.price}" />
							</c:set>
							
                        <h4>Price:$${productTotalPrice}</h4>
                      	
                        
                        <p class="unit">Quantity: ${product.stock}</p>
           
                      
										   
					     <c:set var="totalPrice" value="${totalPrice + productTotalPrice}" />            
                 
					<form method="post" action="${pageContext.request.contextPath}/DeleteCartServlet">
					 <input type="hidden" name="cartdeleteId" value="${product.productid}" />
					  <button type="submit" class="btn-area" style="margin-left:30%;"><i aria-hidden="true" class="fa fa-trash" style="margin-right: 5px;"></i>Remove</button>
					</form>

                    </div>
                </div>

			</c:forEach>
			
			<%} %>
               
            </div>
        		<div class="right-bar">
    <p><span>Subtotal</span> <span>$${totalPrice == null ? '0' : totalPrice}</span></p>
    <hr>
  <form method="post" action="${pageContext.request.contextPath}/OrderServlet">
    <label for="payment" style="margin-right:10px;
    display: ${totalPrice == null || totalPrice == '0' ? 'none' : 'block'};">Payment:</label>
    <select id="payment" name="payment" style="width:50%;
        width: 100%;
        padding: 10px;
        border:none;
        border-radius: 15px;
        box-sizing: border-box;
        display: ${totalPrice == null || totalPrice == '0' ? 'none' : 'block'};"> <!-- Hide the select if totalPrice is null or 0 -->
        <option value="1">E-sewa</option>
        <option value="2">Khalti</option>
        <option value="3">Fonepay</option>
    </select><br>
    <hr style="display: ${totalPrice == null || totalPrice == '0' ? 'none' : 'block'};">
    <input type="hidden" id="tot_amt" name="tot_amt" value="${totalPrice}" />
    
    <button type="submit" class="btn-area2" style="display: ${totalPrice == null || totalPrice == '0' ? 'none' : 'block'};"> <!-- Hide the button if totalPrice is null or 0 -->
        <i class="fa fa-shopping-cart"></i>Checkout
    </button>
</form>
<br><br><br><br>
<a href="${pageContent.request.contextPath}/Drip/UserOrderListServlet" class="btn-area2">View Order Details</a>
  
</div>
        		
			

        
        </div>
    </div>
</body>




</html>