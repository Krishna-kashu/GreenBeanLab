<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dairy-360 | Admin Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .navbar {
            background: linear-gradient(90deg,#38d3ff,#4CAF50);
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
            background:rgba(255,255,255,0.9); color:black;
        }
        footer { background:linear-gradient(90deg,#2196F3,#4CAF50); }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand fw-bold text-white" href="index">
            <img src="images/logo.png" alt="Logo" width="60" height="40" class="me-2"> Dairy-360
        </a>
    </div>
</nav>

<!-- Login Form -->
<div class="container d-flex justify-content-center align-items-center" style="min-height:80vh;">
    <div class="card p-4 col-md-4">
        <h3 class="text-center text-success fw-bold mb-3">Admin Login</h3>
        <form action="AdminLoginServlet" method="post">
            <div class="mb-3">
                <label class="form-label">Username/Email</label>
                <input type="text" class="form-control" name="username" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" class="form-control" name="password" required>
            </div>
            <button type="submit" class="btn btn-success w-100">Login</button>
        </form>
    </div>
</div>

<!-- Footer -->
<footer class="text-white text-center py-3">
    &copy; 2025 Dairy-360. All rights reserved.
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
