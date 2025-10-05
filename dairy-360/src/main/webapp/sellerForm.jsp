<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Seller Registration | Admin Dashboard</title>
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
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/list">Seller info</a></li>
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
    <h3 class="mb-4 text-success text-center">Register New Seller</h3>

    <!-- Alerts -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success text-center">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger text-center">${errorMessage}</div>
    </c:if>

    <!-- Registration Form -->
    <div class="card shadow p-4 mx-auto" style="max-width: 750px;">
        <form id="sellerForm" action="${pageContext.request.contextPath}/save" method="post" enctype="multipart/form-data" novalidate>
            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label" >First Name <span class="text-danger">*</span> </label>
                    <input name="firstName" type="text" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Last Name <span class="text-danger">*</span></label>
                    <input name="lastName" type="text" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Email <span class="text-danger">*</span></label>
                    <input id="emailField" name="email" type="email" class="form-control" required>
                    <small id="emailError" class="text-danger"></small>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Phone Number <span class="text-danger">*</span></label>
                    <input name="phone" type="tel" pattern="[0-9]{10}" class="form-control" placeholder="10 digits" required>
                </div>
                <div class="col-md-12">
                    <label class="form-label">Address</label>
                    <textarea name="address" class="form-control" rows="2"></textarea>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Milk Type</label>
                    <select name="milkType" class="form-select" required>
                        <option value="">Choose...</option>
                        <c:forEach var="product" items="${sellProducts}">
                            <option value="${product.productName}"
                            <c:if test="${seller.milkType == product.productName}">selected</c:if>>
                            ${product.productName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-12 text-end mt-3">
                    <button type="submit" class="btn btn-success">Register Seller</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    $(document).ready(function(){

        // Email AJAX check (your existing code)
        $("#emailField").on("blur", function(){
            var email = $(this).val();
            if(email.length > 0){
                $.ajax({
                    url: "${pageContext.request.contextPath}/adminMailCheck",
                    type: "GET",
                    data: { email: email },
                    success: function(response){
                        $("#emailError").text("");
                        $("#emailField").removeClass("is-invalid");
                        $("button[type=submit]").prop("disabled", false);
                    },
                    error: function(xhr){
                        if(xhr.status === 409){
                            $("#emailError").text(xhr.responseText);
                            $("#emailField").addClass("is-invalid");
                            $("button[type=submit]").prop("disabled", true);
                        } else {
                            console.error("Unexpected error", xhr);
                        }
                    }
                });
            }
        });

        // Real-time validations
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

        // First Name validation
        $("input[name='firstName']").after('<small class="text-danger"></small>').on("input blur", function(){
            validateField($(this), val => /^[A-Za-z]{3,}$/.test(val), "First name must be letters and at least 3 characters");
        });

        // Last Name validation
        $("input[name='lastName']").after('<small class="text-danger"></small>').on("input blur", function(){
            validateField($(this), val => /^[A-Za-z]+$/.test(val), "Last name must contain only letters");
        });

        // Phone validation
        $("input[name='phone']").after('<small class="text-danger"></small>').on("input blur", function(){
            validateField($(this), val => /^[6-9][0-9]{9}$/.test(val), "Phone must start with 6-9 and be 10 digits");
        });

        // Address validation
        $("textarea[name='address']").after('<small class="text-danger"></small>').on("input blur", function(){
            validateField($(this), val => val.length >= 6, "Address must be at least 6 characters");
        });

        // Email format validation (before AJAX)
        $("#emailField").on("input", function(){
            var emailVal = $(this).val();
            var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if(emailVal.length > 0 && !emailRegex.test(emailVal)){
                $("#emailError").text("Invalid email format").addClass("is-invalid");
                $(this).addClass("is-invalid");
                $("button[type=submit]").prop("disabled", true);
            } else {
                $("#emailError").text("");
                $(this).removeClass("is-invalid");
                $("button[type=submit]").prop("disabled", false);
            }
        });

        // Form submission check
        $("#sellerForm").on("submit", function(e){
            var validFirst = validateField($("input[name='firstName']"), val => /^[A-Za-z]{3,}$/.test(val), "First name must be letters and at least 3 characters");
            var validLast = validateField($("input[name='lastName']"), val => /^[A-Za-z]+$/.test(val), "Last name must contain only letters");
            var validPhone = validateField($("input[name='phone']"), val => /^[6-9][0-9]{9}$/.test(val), "Phone must start with 6-9 and be 10 digits");
            var validAddress = validateField($("textarea[name='address']"), val => val.length >= 6, "Address must be at least 6 characters");
            var validEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test($("#emailField").val());

            if(!validFirst || !validLast || !validPhone || !validAddress || !validEmail){
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
