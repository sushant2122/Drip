<!DOCTYPE HTML>
<html>

<head>
    <title></title>
    <link rel="stylesheet" href="style.css" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/admin_header.css" />
    <link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath}/Drip/stylesheets/admin_add.css" />
</head>

<body>
    <jsp:include page="admin_header.jsp" />

    <div id="main">
        <h1 class="title">Add <span class="">Products</span></h1>

        <div class="container">
            <form action="/Drip/AddProductServlet" method="post" class="add_prod" enctype="multipart/form-data">
                <label for="pmodel">Product Model:</label>
                <input type="text" name="pmodel" placeholder="Product model" required>
                <label for="pname">Product Name:</label>
                <input type="text" name="pname" placeholder="Product full name" required>

                <label for="price">Product price:</label>
                <input type="text" name="price" pattern="[0-9]+" placeholder="Only numeric digits" required>
                <label for="stock">Stock quantity:</label>
                <input type="text" name="stock" pattern="[0-9]+" placeholder="Product quantity you have.." required>

                <label for="category">Product category:</label>
                <select name="category" required>
                    <option value="1">Camera</option>
                    <option value="2">Smartphone</option>
                    <option value="3">Laptop</option>
                    <option value="5">Smartwatch</option>
                </select>
                <label for="description">Description</label>
                <textarea name="description" placeholder="Write Specification of product" style="height:200px"></textarea>
                <label for="image">Product Picture</label><br><br>
                <input type="file" id="image" name="productimg" style="color:white" accept="image/*" required><br>
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>
</body>

</html>
