<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Dairy-360</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Navbar */
        .navbar {
            background: linear-gradient(90deg, #38d3ff, #4CAF50);
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            z-index: 1000;
        }
        body {
            background: url('images/dairy-bg1.jpg') center/cover no-repeat;
            padding-top: 90px;
            color: white;
        }
        /* Card */
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0px 4px 20px rgba(0,0,0,0.3);
        }
        .card-header {
            background: linear-gradient(90deg, #38d3ff, #4CAF50);
        }
        .nav-link { color: #fff !important; }
        .nav-link:hover { color: #ffe082 !important; }
        footer {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
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
                <li class="nav-item"><a class="nav-link" href="index">Home</a></li>
                <li class="nav-item"><a class="nav-link active" href="login">Login</a></li>
                <li class="nav-item"><a class="nav-link" href="register">Register</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Login Form -->
<div class="container d-flex justify-content-center align-items-center" style="min-height:80vh;">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header text-center text-white">
                <h4>Login</h4>
            </div>
            <div class="card-body">
                <form action="LoginServlet" method="post">
                    <div class="mb-3">
                        <label class="form-label">Email / Username</label>
                        <input type="text" class="form-control" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="password" class="form-control" name="password" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Role</label>
                        <select class="form-select" name="role" required>
                            <option value="">Select Role</option>
                            <option value="Seller">Seller</option>
                            <option value="Buyer">Buyer</option>
                            <option value="Admin">Admin</option>
                            <option value="Delivery">Delivery</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-success w-100">Login</button>
                </form>
            </div>
            <div class="card-footer text-center">
                <small>Don’t have an account? <a href="register" class="text-success">Register here</a></small>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="text-white text-center py-3">
    &copy; 2025 Dairy-360. All rights reserved.
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
