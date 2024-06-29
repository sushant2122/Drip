<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page import="java.util.List"%>
<%@ page import="models.DashboardModel" %>
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
    <link rel="stylesheet" href="style.css" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/admin_header.css" />
</head>

<body>
   <jsp:include page="admin_header.jsp"/>

    <div id="main">
        <div class="head">
            <div class="col-div-6">
                <!-- Add any content for col-div-6 -->
            </div>
            <div class="clearfix"></div>
        </div>
     
        <div class="col-div-3">
            <div class="box">
                <p>${dashList.productcount != null ? dashList.productcount : '0'}</br><span>Products</span></p>
                <i class="fa fa-list box-icon"></i>
            </div>
        </div>
        
        <div class="col-div-3">
            <div class="box">
                <p>${dashList.ordercount != null ? dashList.ordercount : '0'}</br><span>Orders</span></p>
                <i class="fa fa-shopping-bag box-icon"></i>
            </div>
        </div>
        
        <div class="col-div-3">
            <div class="box">
                <p>${dashList.sales != null ? dashList.sales : '0'}</br><span>Sales</span></p>
                <i class="fa fa-money box-icon"></i>                 
            </div>
        </div>
        
        <div class="clearfix"></div>
        <br/><br/>
    </div>
</body>
</html>
