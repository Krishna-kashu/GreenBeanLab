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

<div class="container d-flex justify-content-center align-items-center" style="min-height:80vh;">
    <div class="card p-4 col-md-5">
        <h3 class="text-center text-success fw-bold mb-3">Update Profile</h3>
        <form action="updateAdminProfile" method="post">
            <div class="mb-3">
                <label class="form-label">Admin Name</label>
                <input type="text" class="form-control" name="adminName" value="${dto.adminName}" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Email (readonly)</label>
                <input type="email" class="form-control" name="email" value="${dto.email}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Phone Number</label>
                <input type="text" class="form-control" name="phoneNumber" value="${dto.phoneNumber}" required>
            </div>
            <button type="submit" class="btn btn-success w-100">Update</button>
        </form>
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
