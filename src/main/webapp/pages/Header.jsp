<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<%
    // Get the session and request objects
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute("username");
    String contextPath = request.getContextPath();
%>
<style>
    .action_btn1 {
        background-color: rgb(178, 178, 178);
        color: rgb(233, 233, 233);
        padding: 8px 15px;
        border-radius: 20px;
        font-size: 14px;
        font-weight: bold;
        cursor: pointer;
        transition: background-color 0.3s ease;
        text-decoration: none;
    }

    .action_btn1:hover {
        background-color: orange;
        color: #fff;
    }

    /* Styles for the modal */
    .modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.4);
    }

    /* Modal content */
    .modal-content {
        background-color: white;
        margin: 15%;
        padding: 20px;
        border: 1px solid #888;
        width: 70%;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        text-align: center;
        color: orange;
        cursor: pointer;
        border-radius: 25px;
    }

    /* Close button */
    .close {
        color: rgb(137, 181, 180);
        float: right;
        text-align: center;
        font-size: 28px;
        font-weight: bold;
        margin-top: 0px;
    }

    .close:hover,
    .close:focus {
        color: orange;
        text-decoration: none;
        cursor: pointer;
    }
</style>
<header>
    <div class="navbar">
        <div class="logo">
            <a href="${pageContent.request.contextPath}/Drip/pages/home.jsp"><img src="/Drip/images/drip_logo.png"
                    alt="Drip clothing" class="imo"></a>
        </div>
        <ul class="links">
            <li><a href="${pageContent.request.contextPath}/Drip/pages/home.jsp">Home</a></li>
            <li><a href="${pageContent.request.contextPath}/Drip/ProductlistServlet">Products </a></li>
            <li><a href="${pageContent.request.contextPath}/Drip/pages/about.jsp">About us</a></li>
        </ul>
        <div class="search">
            <form action="/Drip/SearchServlet" method="get">
                <input type="text" placeholder="Search..." name="search">
                <button type="submit" class="action_btn1">Go</button>
            </form>
        </div>
        <form action="<%
                    // Conditionally set the action URL based on user session
                    if (currentUser != null) {
                        out.print(contextPath + "/LogoutServlet");
                    } else {
                        out.print(contextPath + "/pages/login.jsp");
                    }
                %>" method="post">
            <input type="submit" class="action_btn1" value="<%
                        // Conditionally set the button label based on user session
                        if (currentUser != null) {
                            out.print("Logout");
                        } else {
                            out.print("Login");
                        }
                    %>">
        </form>
        <div class="icons">
            <ul>
                <li><a href="/Drip/CartListServlet" id="add-to-cart"><i class="fas fa-shopping-cart"></i></a></li>
            </ul>
        </div>
        <div class="icons">
            <ul>
                <% if (currentUser != null) { %>
                <li><a href="${pageContent.request.contextPath}/Drip/UserUpdateServlet"><i
                            class="fas fa-user"></i></a></li>
                <% } %>
            </ul>
        </div>
    </div>
</header>
<!-- The Modal -->
<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <p>Please log in to add items to your cart!</p>
    </div>
</div>

<script>
    // Check if the current session is null
    var currentSession = "<%= currentUser %>"; // Use JSP syntax to include dynamic Java value

    // Function to show modal if session is null
    function addToCartClicked() {
        if (currentSession === null) {
            // Display the modal
            document.getElementById("myModal").style.display = "block";
        } else {
            // Perform the add to cart action here
            window.location.href = "/about.jsp";
            // Hide the modal
            document.getElementById("myModal").style.display = "none";
        }
    }

    // Close the modal when the close button is clicked
    document.getElementsByClassName("close")[0].addEventListener("click", function () {
        document.getElementById("myModal").style.display = "none";
    });

    // Add event listener to the add to cart button
    document.getElementById("add-to-cart").addEventListener("click", addToCartClicked);
</script>
