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
    <title>User Order List</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/Header.css" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/user_orderlist.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
    <jsp:include page="Header.jsp"/>

    <h1 class="title">Order <span class="">Details</span></h1>
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
                                <td>Paid Amount</td>
                                <td>Ordered date</td>
                                <td>payment</td>
                                <td>Status</td>
                                <td>Details</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${userorderList}">
                                <tr>
                                    <td>${order.order_id}</td>
                                    <td>$${order.total_amt}</td>
                                    <td>${order.order_date}</td>
                                    <td>${order.payment}</td>
                                    <td>${order.order_status}</td>
                                    <td>
                                        <form action="/Drip/OrderProductServlet" method="GET">
                                            <input type="hidden" name="order_id" value="${order.order_id}" />
                                            <button type="submit" class="status">View</button>
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
