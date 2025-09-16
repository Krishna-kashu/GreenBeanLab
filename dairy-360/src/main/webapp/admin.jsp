<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dairy-360 | Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Navbar */
        .navbar {
            background: linear-gradient(90deg, #38d3ff, #4CAF50);
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            z-index: 1000;
        }

        body {
            background: url('images/dairy-bg1.jpg') center/cover no-repeat fixed;
            color: white;
            padding-top: 80px;
        }

        /* Dashboard Cards */
        .card {
            border: none;
            border-radius: 15px;
            transition: transform 0.3s ease, box-shadow 0.3s;
            box-shadow: 0px 4px 15px rgba(0,0,0,0.2);
            background-color: rgba(255, 255, 255, 0.9);
            color: black;
        }
        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0px 8px 25px rgba(0, 150, 136, 0.6);
        }

        .dashboard-header {
            text-align: center;
            margin-bottom: 30px;
        }

        footer {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
        }

        .nav-link {
            color: #f8f9fa !important;
        }
        .nav-link:hover {
            color: #ffe082 !important;
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
                <li class="nav-item"><a class="nav-link fw-semibold" href="products">Products</a></li>
                <li class="nav-item"><a class="nav-link fw-semibold" href="logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Dashboard -->
<div class="container py-5">
    <h1 class="text-center fw-bold mb-4">Admin Dashboard</h1>
    <div class="row g-4">

        <div class="col-md-4">
            <div class="card text-center p-3">
                <img src="images/users.png" class="card-img-top" style="height:150px;object-fit:contain;">
                <h5 class="mt-3 text-success">Manage Users</h5>
                <p>View or update buyers, sellers & delivery staff.</p>
                <a href="UserServlet" class="btn btn-success">Go</a>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card text-center p-3">
                <img src="images/products.png" class="card-img-top" style="height:150px;object-fit:contain;">
                <h5 class="mt-3 text-success">Manage Products</h5>
                <p>Add, update or delete products.</p>
                <a href="ProductServlet" class="btn btn-success">Go</a>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card text-center p-3">
                <img src="images/orders.png" class="card-img-top" style="height:150px;object-fit:contain;">
                <h5 class="mt-3 text-success">Manage Orders</h5>
                <p>Track and update customer orders.</p>
                <a href="OrderServlet" class="btn btn-success">Go</a>
            </div>
        </div>
    </div>
</div>

<!-- Dashboard -->
<div class="container py-5">
    <div class="dashboard-header">
        <h1 class="fw-bold">Admin Dashboard</h1>
        <p class="lead">Manage Users, Products & Orders</p>
    </div>

    <div class="row g-4">
        <!-- Users Management -->
        <div class="col-md-4">
            <div class="card text-center p-3">
                <img src="images/users.png" class="card-img-top" alt="Users" style="height:180px; object-fit:contain;">
                <div class="card-body">
                    <h5 class="card-title text-success">Manage Users</h5>
                    <p class="card-text">View, approve or remove Buyers, Sellers, and Delivery Staff.</p>
                    <a href="UserServlet" class="btn btn-success">Go</a>
                </div>
            </div>
        </div>

        <!-- Products Management -->
        <div class="col-md-4">
            <div class="card text-center p-3">
                <img src="images/products.png" class="card-img-top" alt="Products" style="height:180px; object-fit:contain;">
                <div class="card-body">
                    <h5 class="card-title text-success">Manage Products</h5>
                    <p class="card-text">Add, update, or delete milk & by-products from the system.</p>
                    <a href="ProductServlet" class="btn btn-success">Go</a>
                </div>
            </div>
        </div>

        <!-- Orders Management -->
        <div class="col-md-4">
            <div class="card text-center p-3">
                <img src="images/orders.png" class="card-img-top" alt="Orders" style="height:180px; object-fit:contain;">
                <div class="card-body">
                    <h5 class="card-title text-success">Manage Orders</h5>
                    <p class="card-text">Track, assign delivery, and update order status.</p>
                    <a href="OrderServlet" class="btn btn-success">Go</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
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
