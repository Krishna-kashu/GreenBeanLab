<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dairy-360 | Delivery Dashboard</title>
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
        <a class="navbar-brand fw-bold text-white" href="index">
            <img src="images/logo.png" alt="Logo" width="60" height="40" class="me-2"> Dairy-360
        </a>
        <ul class="navbar-nav ms-auto">
            <li class="nav-item"><a href="logout" class="nav-link">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container py-5">
    <h1 class="text-center fw-bold">Delivery Dashboard</h1>
    <p class="text-center lead">Assigned Collections & Deliveries</p>

    <div class="row g-4">
        <!-- Collection Tasks -->
        <div class="col-md-6">
            <div class="card p-3">
                <h5 class="text-success">Milk Collection</h5>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Farmer</th><th>Location</th><th>Qty (L)</th><th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Ramesh</td><td>Village A</td><td>50</td>
                        <td><button class="btn btn-sm btn-success">Collected</button></td>
                    </tr>
                    <!-- More rows from DB -->
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Delivery Tasks -->
        <div class="col-md-6">
            <div class="card p-3">
                <h5 class="text-success">Product Delivery</h5>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Order ID</th><th>Customer</th><th>Address</th><th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>#102</td><td>Sita</td><td>City B</td>
                        <td>
                            <select class="form-select form-select-sm">
                                <option>Assigned</option>
                                <option>Collected</option>
                                <option>In-Transit</option>
                                <option>Delivered</option>
                            </select>
                        </td>
                    </tr>
                    <!-- More rows from DB -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="text-lg-start py-4" style="background: linear-gradient(90deg, #2196F3, #4CAF50); color: #fff;">
    <div class="container">
        <div class="row text-center text-md-start">

            <!-- Address -->
            <div class="col-md-3 mb-3">
                <h6 class="fw-bold">Address</h6>
                <p class="mb-1"><i class="bi bi-geo-alt-fill me-2"></i>123 Dairy Street</p>
                <p>Hyderabad, India</p>
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
