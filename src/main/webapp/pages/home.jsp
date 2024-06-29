<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/Header.css" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/Footer.css" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/home.css" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/js/script.js" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="container1">
    <div class="content">
        <br>
        <br>
        <br>
        <h1>Discover the <br>Coolest Gadgets at Drip.</h1>
        <p style="font-family: 'Open Sans', sans-serif;  word-spacing: 2px; font-size: 20px;">Experience innovation like never before. Drip, your premier destination<br> for cutting-edge technology,
            ensures the highest quality products across <br>Nepal. As the leading gadget shop in the nation, we
            pride
            ourselves on <br>delivering top-notch customer service and unbeatable selection.</p>
        <br>
        <br>
        <br>
        &ensp;&ensp;&ensp;&ensp;<a href="${pageContent.request.contextPath}/Drip/ProductlistServlet" class="action_btn">Explore Our Collection</a>
    </div>
</div>
<main>
    <article>
        <section class="section category">
            <div class="container">
                <h2 class="category">
                    <span class="span">Top</span> Categories
                </h2>
                <ul class="options">
                    <li class="opt_item">
                        <div class="category_card">
                            <div class="card_banner">
                                <img src="${pageContent.request.contextPath}/Drip/images/mob_cat.png" width="330" height="300" alt="Smart phones" class="cat_img">
                            </div>
                            <form method="get" action="/Drip/ProductCategoryServlet" class="category_form">
                                <input type="hidden" name="category" value="2" />
                                <button type="submit" style="background-color:white; color:balck; border:none; font-size:20px;"><h3>Smart phones</h3></button>
                            </form>
                        </div>
                    </li>
                    <li class="opt_item">
                        <div class="category_card">
                            <figure class="card_banner">
                                <img src="${pageContent.request.contextPath}/Drip/images/lap_cat.png" width="330" height="300" alt="Laptops" class="cat_img">
                            </figure>
                            <form method="get" action="/Drip/ProductCategoryServlet" class="category_form">
                                <input type="hidden" name="category" value="3" />
                                <button type="submit" style="background-color:white; color:balck; border:none; font-size:20px;"><h3>Laptops</h3></button>
                            </form>
                        </div>
                    </li>
                    <li class="opt_item">
                        <div class="category_card">
                            <figure class="card_banner">
                                <img src="${pageContent.request.contextPath}/Drip/images/wat_cat.png" width="330" height="300" alt="Smart watch" class="cat_img">
                            </figure>
                            <form method="get" action="/Drip/ProductCategoryServlet" class="category_form">
                                <input type="hidden" name="category" value="5" />
                                <button type="submit" style="background-color:white; color:balck; border:none; font-size:20px;"><h3>Smart watches</h3></button>
                            </form>
                        </div>
                    </li>
                    <li class="opt_item">
                        <div class="category_card" id="camera_category">
                            <figure class="card_banner">
                                <img src="${pageContent.request.contextPath}/Drip/images/cam_cat.png" width="330" height="300" alt="Camera" class="cat_img">
                            </figure>
                            <form method="get" action="/Drip/ProductCategoryServlet" class="category_form">
                                <input type="hidden" name="category" value="1" />
                                <button type="submit" style="background-color:white; color:balck; border:none; font-size:20px;"><h3>Cameras</h3></button>
                            </form>
                        </div>
                    </li>
                </ul>
            </div>
        </section>
        <section class="service">
            <div class="container">
                <h2 class="service">
                    Our <span class="span">Services</span>
                </h2>
                <ul class="service_list">
                    <li>
                        <div class="service_card">
                            <figure class="card_icon">
                                <img src="${pageContent.request.contextPath}/Drip/images/guarantee.png" width="100" height="100" alt="service icon">
                            </figure>
                            <h3 class="card_title">2 Years Guarantee</h3>
                            <p class="card_text">
                                2 years of guarantee on every product.
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="service_card">
                            <figure class="card_icon">
                                <img src="${pageContent.request.contextPath}/Drip/images/return.png" width="100" height="100" alt="service icon">
                            </figure>
                            <h3 class="card_title">30 Day Return</h3>
                            <p class="card_text">
                                you can return the product at 0% shipping cost.
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="service_card">
                            <figure class="card_icon">
                                <img src="${pageContent.request.contextPath}/Drip/images/delivery.png" width="100" height="100" alt="service icon">
                            </figure>
                            <h3 class="card_title">Fast Delivery</h3>
                            <p class="card_text">
                                We try to deliver our products under 24 hours.
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="service_card">
                            <figure class="card_icon">
                                <img src="${pageContent.request.contextPath}/Drip/images/flash.png" width="100" height="100" alt="service icon">
                            </figure>
                            <h3 class="card_title">Flash Sales</h3>
                            <p class="card_text">
                                Flash sales on different occasions.
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
        </section>
        <section class="brand">
            <div class="container">
                <h2 class="brand">
                    <span class="span">Popular</span> Brands
                </h2>
                <ul class="options">
                    <li class="opt_item">
                        <div class="brand_card">
                            <img src="${pageContent.request.contextPath}/Drip/images/apple.png" width="180" height="180" alt="brand logo" class="brand_img">
                        </div>
                    </li>
                    <li class="opt_item">
                        <div class="brand_card">
                            <img src="${pageContent.request.contextPath}/Drip/images/asus.png" width="180" height="180" alt="brand logo" class="brand_img">
                        </div>
                    </li>
                    <li class="opt_item">
                        <div class="brand_card">
                            <img src="${pageContent.request.contextPath}/Drip/images/msi.png" width="180" height="180" alt="brand logo" class="brand_img">
                        </div>
                    </li>
                    <li class="opt_item">
                        <div class="brand_card">
                            <img src="${pageContent.request.contextPath}/Drip/images/razer.png" width="180" height="180" alt="brand logo" class="brand_img">
                        </div>
                    </li>
                </ul>
            </div>
        </section>
    </article>
</main>
<jsp:include page="Footer.jsp"/>
</body>
</html>
