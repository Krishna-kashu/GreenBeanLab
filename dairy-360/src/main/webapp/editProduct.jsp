<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Dairy-360 | Edit Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        /* Match index.jsp theme */
        .navbar {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            z-index: 1000;
        }
        .nav-link { color: #f8f9fa !important; }
        .nav-link:hover { color: #ffe082 !important; }
        body {
            background: url('${pageContext.request.contextPath}/images/dairy-bg1.jpg') center/cover no-repeat;
            color: #333;
            padding-top: 90px;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0px 6px 20px rgba(0,0,0,0.15);
        }
        .btn-success {
            background: linear-gradient(90deg, #38d3ff, #185a9d);
            border: none;
        }
        footer {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
            color: #fff;
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
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" width="70" height="50" class="me-2">
            Dairy-360
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/admin?email=${dto.email}">Admin Dashboard</a></li>
                <li class="nav-item"><a class="nav-link fw-semibold" href="products">Products</a></li>
                <li class="nav-item"><a class="nav-link fw-semibold" href="about">About Us</a></li>
                <li class="nav-item"><a class="nav-link fw-semibold" href="contact">Contact</a></li>
                <li class="nav-item"><a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/admin-logout">Logout</a></li>
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
        <li><a href="${pageContext.request.contextPath}/product" class="drawer-link">Product Info</a></li>
        <li><a href="OrderServlet" class="drawer-link">Orders</a></li>
        <li><a href="#" class="drawer-link">Reports</a></li>
    </ul>
</div>


<!-- Content -->
<div class="container py-5">
    <h3 class="mb-4 text-success fw-bold text-center">Edit Products</h3>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <div class="card p-4 bg-light">
        <form id="editProductForm" action="${pageContext.request.contextPath}/updateProduct" method="post" enctype="multipart/form-data" novalidate>
            <input type="hidden" name="productId" value="${product.productId}"/>

            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Product Name <span class="text-danger">*</span></label>
                    <input name="productName" value="${product.productName}" type="text" class="form-control" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Price (₹) <span class="text-danger">*</span></label>
                    <input name="price" value="${product.price}" type="number" step="0.01" min="0" class="form-control" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Product Type <span class="text-danger">*</span></label><br>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="type" value="Buy" id="typeBuy"
                        <c:if test="${product.type eq 'Buy'}">checked</c:if> >
                        <label class="form-check-label" for="typeBuy">Buy</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="type" value="Sell" id="typeSell"
                        <c:if test="${product.type eq 'Sell'}">checked</c:if> >
                        <label class="form-check-label" for="typeSell">Sell</label>
                    </div>
                    <small class="text-danger" id="typeError"></small>
                </div>

                <div class="col-12 text-end mt-3">
                    <button type="submit" class="btn btn-warning">Update Product</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- Footer -->
<footer class="text-center py-3 mt-5">
    <div class="container">
        <p class="mb-0">&copy; 2025 Dairy-360. All rights reserved.</p>
    </div>
</footer>
<script>
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

        $("input[name='productName']").after('<small class="text-danger"></small>')
            .on("input blur", function(){
                validateField($(this), val => /^[A-Za-z0-9\s]{3,}$/.test(val),
                    "Product name must be at least 3 characters");
            });

        $("input[name='price']").after('<small class="text-danger"></small>')
            .on("input blur", function(){
                validateField($(this), val => parseFloat(val) > 0, "Price must be greater than 0");
            });

        $("#editProductForm").on("submit", function(e){
            var validProduct = validateField($("input[name='productName']"),
                val => /^[A-Za-z0-9\s]{3,}$/.test(val), "Product name must be at least 3 characters");
            var validPrice = validateField($("input[name='price']"),
                val => parseFloat(val) > 0, "Price must be greater than 0");

            if(!$("input[name='type']:checked").length){
                $("#typeError").text("Please select Buy or Sell");
                e.preventDefault();
            } else {
                $("#typeError").text("");
            }

            if(!validProduct || !validPrice){
                e.preventDefault();
            }
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
