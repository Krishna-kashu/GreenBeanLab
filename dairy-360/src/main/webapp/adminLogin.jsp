<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>


<html>
<head>
    <meta charset="UTF-8">
    <title>Dairy-360 | Admin Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .navbar {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
            position: fixed; top:0; width:100%; z-index:1000;
        }
        body {
            background: url('images/dairy-bg1.jpg') center/cover no-repeat fixed;
            padding-top:80px;
            color:white;
        }
        .card {
            border:none; border-radius:15px;
            box-shadow:0 4px 15px rgba(0,0,0,0.3);
            background:rgba(255,255,255,0.95); color:black;
        }
        footer { background:linear-gradient(90deg,#2196F3,#4CAF50); }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <body style="background: url('${pageContext.request.contextPath}/images/dairy-bg1.jpg') center/cover no-repeat fixed; padding-top:80px; color:white;">

        <a class="navbar-brand fw-bold text-white" href="${pageContext.request.contextPath}/index">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" width="60" height="40" class="me-2"> Dairy-360
        </a>


        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="index">Home</a></li>
                <li class="nav-item"><a class="nav-link active" href="admin-register">Admin Register</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Login Section -->
<div class="container d-flex justify-content-center align-items-center" style="min-height:80vh;">
    <div class="col-md-5">
        <div class="card p-4">
            <h3 class="text-center mb-4">Admin Login</h3>

            <form action="${pageContext.request.contextPath}/admin-login" method="post">
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="text" class="form-control" name="email" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" required>
                </div>
                <button type="submit" class="btn btn-success w-100">Login</button>
            </form>

            <c:if test="${not empty successMessage}">
                <div class="alert alert-success mt-3">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mt-3">${errorMessage}</div>
            </c:if>
            <!-- Reset Option -->

                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/admin/forgot-password" class="text-decoration-none">Forgot Password?</a>

                </div>

        </div>
    </div>
</div>

<!-- Footer -->
<footer class="text-lg-start py-4" style="color: #fff;">
    <div class="container">
        <div class="row text-center text-md-start">

            <!-- Address -->
            <div class="col-md-3 mb-3">
                <h6 class="fw-bold">Address</h6>
                <p class="mb-1"><i class="bi bi-geo-alt-fill me-2"></i>123 Dairy Street</p>
                <p> India</p>
            </div>

            <!-- Contact -->
            <div class="col-md-3 mb-3">
                <h6 class="fw-bold">Contact</h6>
                <p class="mb-1"><i class="bi bi-telephone-fill me-2"></i>+91-9876543210</p>
                <small>Mon-Sat: 7am - 9pm</small>
            </div>

            <!-- Email -->
            <div class="col-md-3 mb-3">
                <h6 class="fw-bold">Email</h6>
                <p><i class="bi bi-envelope-fill me-2"></i>support@dairy360.com</p>
            </div>

            <!-- Social Links -->
            <div class="col-md-3 mb-3">
                <h6 class="fw-bold">Follow Us</h6>
                <a href="#" class="text-white me-3"><i class="bi bi-facebook fs-4"></i></a>
                <a href="#" class="text-white me-3"><i class="bi bi-instagram fs-4"></i></a>
                <a href="#" class="text-white"><i class="bi bi-twitter-x fs-4"></i></a>
            </div>
        </div>

        <hr class="border-light">

        <div class="text-center">
            &copy; 2025 Dairy-360. All rights reserved.
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
