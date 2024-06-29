<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%
    // Get the session and request objects
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute("username");
    String contextPath = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>

<head>
    <title></title>
    <link rel="stylesheet" href="style.css" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/admin_product.css" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/admin_header.css" />
</head>

<body>
    <jsp:include page="admin_header.jsp"/>

    <div id="main">
        <h1 class="title">My <span class="">Inventory</span></h1>
        <div class="card_container">
            <c:forEach var="product" items="${adminproductList}">
                <div class="card">
                    <img src="${pageContext.request.contextPath}/resources/images/product/${product.imageUrlFromPart}" alt="">
                    <div class="card_content">
                        <h3>Name:${product.productname}</h3>
                        <h4>Model:${product.modelno}</h4>
                        <h4>Price:${product.price}</h4>
                        <h4>Stock:${product.stock}</h4>
                        <h4>Category:${product.category}</h4>
                        <p>${product.description}</p>

                        <br>
                        <form  method="get" action="${pageContext.request.contextPath}/ModifyProductServlet">
                            <input type="hidden" name="updateproductid" value="${product.productid}" />
                            <button type="submit">Edit</button>
                        </form>
                        <form id="deleteForm-${product.productid}" method="post" action="${pageContext.request.contextPath}/ModifyProductServlet">
                            <input type="hidden" name="productdeleteId" value="${product.productid}" />
                            <button type="button" onclick="confirmDelete('${product.productname}', '${product.productid}')">Delete</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>

<script>
    function confirmDelete(productname, productId) {
        if (confirm("Are you sure you want to delete this product: " + productname + "?")) {
            document.getElementById("deleteForm-" + productId).submit();
        }
    }
</script>

</html>
