<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update product</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/updateproduct.css" />
</head>
<body>
    <div class="container">
        <h1>Update <span class="head_ing">Product</span></h1>
        <form action="/Drip/ModifyProductServlet" method="post" enctype="multipart/form-data" style="color:white;">
            <div class="product_img">
                <div class="product">
                    <img src="${pageContext.request.contextPath}/resources/images/product//${product.imageUrlFromPart}" alt="Product Picture" class="img_holder">
                    <div class="upload_img">
                        <label class="upload_product">
                            Upload new photo
                            <input type="file" class="upload_file" name="productimg" accept="image/*" required>
                        </label>
                        <p class="upload_desc">Allowed JPG, GIF, or PNG.</p>
                    </div>
                </div>
                <hr>
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            <label for="model">Model no:</label>
                            <input type="text" id="model" name="pmodel" value="${product.modelno}">
                        </div>
                        <div class="col">
                            <label for="productname">Product Name:</label>
                            <input type="text" id="productname" name="pname" value="${product.productname}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label for="price">Price:</label>
                            <input type="text" name="price" pattern="[0-9]+" placeholder="Only numeric digits" value="${product.price}">
                        </div>
                        <div class="col">
                            <label for="stock">Stock:</label>
                            <input type="text" name="stock" pattern="[0-9]+" placeholder="Only numeric digits" value="${product.stock}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label for="category">Category:</label>
                            <select name="category" value="${product.category}">
                                <option value="1">Camera</option>
                                <option value="2">Smartphone</option>
                                <option value="3">Laptop</option>
                                <option value="5">Smartwatch</option>
                            </select>
                        </div>
                    </div>
                    <div class="col">
                        <label for="description">Description:</label>
                        <textarea name="description" placeholder="Write Specification of product" style="height:200px">${product.description}</textarea>
                    
                    <input type="hidden" name="updateid" value="${product.productid}" />
                    <div class="Save_button">
                        <button type="submit" class="sv_btn">Save changes</button>
                        <a href="${pageContent.request.contextPath}/Drip/DashboardServlet"><button type="button">Cancel</button></a>
                    </div>
                    
                </div>
            </div>
        </form>

    </div>
</body>
</html>
