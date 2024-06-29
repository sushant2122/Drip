<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About us</title>
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/Header.css" />
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/about.css" />
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/Footer.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="heading">
    <h1>About Us</h1>
    <p>Bringing innovation to your fingertips, our curated selection of gadgets ensures you stay ahead in the
        digital age.</p>
</div>
<div class="container">
    <section class="about">
        <div class="about-image">
            <img src="${pageContent.request.contextPath}/Drip/images/about.png" alt="ecommerce platform Image">
        </div>
        <div class="about-content">
            <h2>Welcome to <span style="color:red;">D</span>-RIP</h2>
            <p>We're dedicated to providing tech enthusiasts with an exceptional shopping experience characterized by meticulous product selection, personalized support, and seamless delivery. Our commitment to excellence begins with the careful curation of top-quality products from trusted brands, ensuring that every item meets our rigorous standards for quality and innovation. Our team of knowledgeable experts is always available to offer personalized recommendations and assistance, guiding customers through their purchase journey with expertise and care. From carefully packaged shipments to reliable shipping methods, we ensure that every order arrives safely and promptly, backed by a dedicated customer support team ready to address any concerns. In essence, we're more than just a store—we're a trusted partner for tech enthusiasts, providing the ultimate destination for quality products and unparalleled service.
            </p>
        </div>
    </section>
</div>
<section>
    <div class="container">
        <h1 class="heading">Meet The Team</h1>
        <div class="card-wrapper">
            <div class="card">
                <img src="${pageContent.request.contextPath}/Drip/images/bg.png" alt="card background" class="card-img">
                <img src="${pageContent.request.contextPath}/Drip/images/sus.png" alt="profile image" class="profile-img">
                <h2 class="name">Sushant Paudyal</h2>
                <p class="job-title">Backend Developer</p>
                <p class="about">Experienced backend developer with a solid track record of delivering high-performance solutions. Proficient in designing and implementing scalable architectures to support complex business needs. Skilled in database management and API development. Dedicated to refining skills and improving quality to exceed client expectations.</p>
                <a href="#" class="btn">Contact</a>
                <ul class="social-media">
                    <li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
                    <li><a href="#"><i class="fab fa-twitter-square"></i></a></li>
                    <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                    <li><a href="#"><i class="fab fa-google-square"></i></a></li>
                </ul>
            </div>     
      
        </div>
    </div>
    <div class="contact-heading">
        <h1>Connect with Us</h1>
        <p>Reach out to us with your inquiries or feedback</p>
    </div>
    <div class="contact-form">
         <form  action="https://api.web3forms.com/submit" method="POST">
             <input type="hidden" name="access_key" value="6197c0ed-edeb-4dee-a664-83f77ac61c2a">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="message">Message:</label>
                    <textarea id="message" name="message" rows="5" required></textarea>
                </div>
                <button type="submit" class="submit-btn">Send</button>
            </form>
    </div>
    <div class="reach-us">
        <h2>Reach Us</h2>
        <div class="contact-info">
            <a href="tel:016632456" style="text-decoration:none; color:gray;"><p><i class="fa-solid fa-phone"></i> &ensp;+01 6630003</p></a>
              <br>
             <a href="mailto:drip@gmail.com" style="text-decoration:none; color:gray;"><p><i class="fa-solid fa-envelope"></i>&ensp;drip@gmail.com</p></a>
             <br>
              <p style="margin-bottom:20px;"><i class="fa-solid fa-location-dot"></i>&ensp;21 - lokanthali, bhaktapur</p>
            
        </div>
    </div>
</section>
<jsp:include page="Footer.jsp"/>
</body>
</html>
