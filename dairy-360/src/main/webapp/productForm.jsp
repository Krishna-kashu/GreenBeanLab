<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Product Registration | Admin Dashboard</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url('images/dairy-bg1.jpg') center/cover no-repeat ;
            color: white;
            padding-top: 80px;
        }
         /* Navbar color */
        .navbar {
                background: linear-gradient(90deg, #2196F3, #4CAF50);
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                z-index: 1000;
        }

        .nav-link {
            color: #f8f9fa !important;
            transition: color 0.3s ease;
        }
        .nav-link:hover {
            color: #ffe082 !important;
        }
        .card {
            border-radius: 12px;
            border: none;
        }
        .btn-success {
            background: #198754;
            border: none;
        }
        .btn-success:hover {
            background: #157347;
        }

        /* Floating menu button */
        .menu-container {
            position: fixed;
            top: 90px;   /* just below navbar */
            left: 15px;
            z-index: 1100;
        }
        .menu-btn {
            font-size: 26px;
            background: #4CAF50;
            color: white;
            border: none;
            border-radius: 8px;
            padding: 6px 12px;
            cursor: pointer;
            box-shadow: 0px 4px 8px rgba(0,0,0,0.3);
        }
        .menu-btn:hover {
            background: #388E3C;
        }

        .drawer ul {
            padding: 15px;
        }

        .drawer-link {
            display: block;
            padding: 12px 15px;
            margin-bottom: 10px;
            background: rgba(255, 255, 255, 0.15); /* semi-transparent box */
            border-radius: 8px;
            font-weight: 500;
            color: white;
            text-decoration: none;
            transition: background 0.3s, transform 0.2s;
        }

        .drawer-link:hover {
            background: rgb(48 204 56 / 38%);
            transform: translateX(5px); /* small slide effect on hover */
        }

        .drawer.active {
            left: 0;
        }
        .drawer h5 {
            background: #3abd71;
            margin: 0;
        }
        .drawer {
            position: fixed;
            top: 80px;   /* height of your navbar (so it starts just below) */
            left: -250px;  /* hidden initially */
            width: 250px;
            height: calc(100% - 80px); /* full height minus navbar */
            transition: all 0.3s ease;
            z-index: 1200;
            overflow-y: auto;
            background: linear-gradient(90deg, #38d3ff, #4CAF50); /* match navbar style */
        }
    </style>
</head>
<body>

<!-- Navbar -->

<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand fw-bold text-white" href="index">
            <img src="images/logo.png" alt="Logo" width="70" height="50" class="me-2">
            Dairy-360
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin?email=${dto.email}">Dashboard</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/product">Product info</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin-logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Floating Hamburger Menu -->
<div class="menu-container">
    <button class="menu-btn" id="menu-toggle">&#9776;</button>
</div>

<!-- Drawer Menu -->
<div id="drawer" class="drawer text-white">
    <h5 class="p-3 border-bottom">Dashboard Menu</h5>
    <ul class="list-unstyled">
        <li><a href="${pageContext.request.contextPath}/list" class="drawer-link">Seller Info</a></li>
        <li><a href="${pageContext.request.contextPath}/audit" class="drawer-link">View Seller Audit Logs</a></li>
        <li><a href="${pageContext.request.contextPath}/product" class="text-white d-block py-2 px-3 drawer-link">Product Info</a></li>
        <li><a href="OrderServlet" class="drawer-link">Orders</a></li>
        <li><a href="#" class="drawer-link">Reports</a></li>
    </ul>
</div>

<div class="container py-5">
    <h3 class="mb-4 text-success text-center">Add New Product</h3>

    <!-- Alerts -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success text-center">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger text-center">${errorMessage}</div>
    </c:if>

    <!-- Registration Form -->
    <div class="card shadow p-4 mx-auto" style="max-width: 750px;">
        <form id="productForm" action="${pageContext.request.contextPath}/saveProduct" method="post" enctype="multipart/form-data" novalidate>
            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label" >Product Name <span class="text-danger">*</span> </label>
                    <input name="productName" type="text" class="form-control" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Price (₹) <span class="text-danger">*</span></label>
                    <input name="price" type="number" step="0.01" min="0" class="form-control" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Product Type <span class="text-danger">*</span></label><br>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="type" value="Buy" id="typeBuy">
                        <label class="form-check-label" for="typeBuy">Buy</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="type" value="Sell" id="typeSell">
                        <label class="form-check-label" for="typeSell">Sell</label>
                    </div>
                    <small class="text-danger" id="typeError"></small>

                </div>

                <div class="col-12 text-end mt-3">
                    <button type="submit" class="btn btn-success">Save Product</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    $(document).ready(function(){

        function validateField(field, validator, errorMsg){
            var value = field.val().trim();
            if(!validator(value)){
                field.addClass("is-invalid");
                field.next(".text-danger").text(errorMsg);
                return false;
            } else {
                field.removeClass("is-invalid");
                field.next(".text-danger").text("");
                return true;
            }
        }

        // Product Name validation
        $("input[name='productName']").after('<small class="text-danger"></small>')
          .on("input blur", function(){
              validateField($(this), val => /^[A-Za-z0-9\s]{3,}$/.test(val),
                  "Product name must be at least 3 characters");
          });

        // Price validation
        $("input[name='price']").after('<small class="text-danger"></small>')
          .on("input blur", function(){
              validateField($(this), val => parseFloat(val) > 0, "Price must be greater than 0");
          });

        // On Submit Validation
        $("#productForm").on("submit", function(e){
            var validProduct = validateField($("input[name='productName']"),
                val => /^[A-Za-z0-9\s\-()]{3,}$/.test(val), "Product name must be at least 3 characters");
            var validPrice = validateField($("input[name='price']"),
                val => parseFloat(val) > 0, "Price must be greater than 0");

            if(!$("input[name='type']:checked").length){
                alert("Please select Buy or Sell");
                e.preventDefault();
            }

            if(!validProduct || !validPrice){
                e.preventDefault();
            }
        });
    });

    const drawer = document.getElementById("drawer");
    const toggleBtn = document.getElementById("menu-toggle");

    toggleBtn.addEventListener("click", function() {
        drawer.classList.toggle("active");
    });

    // Close drawer when clicking outside
    document.addEventListener("click", function(e) {
        if (!drawer.contains(e.target) && !toggleBtn.contains(e.target)) {
            drawer.classList.remove("active");
        }
    });


</script>

</body>
</html>
