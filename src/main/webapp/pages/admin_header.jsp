<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<%
    // Get the session and request objects
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute("username");
    String contextPath = request.getContextPath();
%>
  
<div id="mysidenav" class="sidenav">
    <p class="logo"><span>D</span>-RIP</p>
    <a href="${pageContent.request.contextPath}/Drip/DashboardServlet" class="icon-a">
        <i class="fa fa-dashboard icons"></i>&nbsp;&nbsp; Dashboard
    </a>
    <a href="${pageContent.request.contextPath}/Drip/pages/admin_add.jsp" class="icon-a">
        <i class="fa fa-list icons"></i>&nbsp;&nbsp; Add products
    </a>
    <a href="${pageContent.request.contextPath}/Drip/AdminProductlistServlet" class="icon-a">
        <i class="fa fa-tasks icons"></i>&nbsp;&nbsp; Inventory
    </a>
    <a href="${pageContent.request.contextPath}/Drip/AdminOrderServlet" class="icon-a">
        <i class="fa fa-shopping-bag icons"></i>&nbsp;&nbsp; Orders
    </a>
    <a href="${pageContent.request.contextPath}/Drip/UserUpdateServlet" class="icon-a">
        <i class="fa fa-user icons"></i>&nbsp;&nbsp; Accounts
    </a>
    <a>
        <form action="<%
            // Conditionally set the action URL based on user session
            if (currentUser != null) {
                out.print(contextPath + "/LogoutServlet");
            } else {
                out.print(contextPath + "/pages/login.jsp");
            }
        %>" method="post">
            <input type="submit" class="action_btn" value="<%
                // Conditionally set the button label based on user session
                if (currentUser != null) {
                    out.print("Logout");
                } else {
                    out.print("Login");
                }
            %>"/>
        </form>
    </a>
</div>
