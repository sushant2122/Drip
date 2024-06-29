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

<!Doctype HTML>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="style.css" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/admin_header.css" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/admin_order.css" />
</head>

<body>
    <jsp:include page="admin_header.jsp"/>

    <h1 class="title">Order <span class="">Status</span></h1>
    <div class="container">

        <!-- ========================= Main ==================== -->
        <div class="main">

            <!-- ================ Order Details List ================= -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>All Orders</h2>
                        <a></a>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <td>Id</td>
                                <td>Price</td>
                                <td>Ordered date</td>
                                <td>Payment</td>
                                <td>Status</td>
                                <td>Update</td>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="order" items="${adminorderList}">
                                <tr>
                                    <td>${order.order_id}</td>
                                    <td>$${order.total_amt}</td>
                                    <td>${order.order_date}</td>
                                    <td>${order.payment}</td>
                                    <td><span class="status">${order.order_status}</span></td>
                                    <td>
                                        <form action="/Drip/UpdateOrderServlet" method="post">
                                            <select id="status" name="status">
                                                <option value="Delivered">Delivered</option>
                                                <option value="Return">Return</option>
                                            </select><br>
                                            <input type="hidden" name="order_id" value="${order.order_id}" />
                                            <button type="submit" class="status">Update</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
