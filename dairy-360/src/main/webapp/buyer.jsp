<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Dairy-360 | Buyer Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: url('images/dairy-bg1.jpg') center/cover no-repeat fixed; padding-top:80px; color:white; }
        .navbar { background: linear-gradient(90deg,#38d3ff,#4CAF50); position:fixed;top:0;width:100%; }
        .card { border:none;border-radius:15px;box-shadow:0 4px 15px rgba(0,0,0,0.2);background:white;color:black; }
        footer { background:linear-gradient(90deg,#2196F3,#4CAF50); }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand fw-bold text-white" href="index">Dairy-360</a>
        <ul class="navbar-nav ms-auto">
            <li class="nav-item"><a href="logout" class="nav-link">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container py-5">
    <h1 class="text-center fw-bold">Buyer Dashboard</h1>
    <p class="text-center lead">Purchase, Resell & Track Products</p>

    <div class="row g-4">
        <!-- Buy Milk/Products -->
        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h5 class="text-success">Buy Products</h5>
                <p>Browse & purchase milk or by-products directly from the dairy.</p>
                <a href="products" class="btn btn-success">Shop Now</a>
            </div>
        </div>

        <!-- Resell By-Products -->
        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h5 class="text-success">Resell By-Products</h5>
                <form action="ResellServlet" method="post">
                    <select name="batchId" class="form-control mb-2" required>
                        <option value="">Select Purchased Milk Batch</option>
                        <!-- Populate from backend -->
                    </select>
                    <input type="text" name="productName" class="form-control mb-2" placeholder="By-product Name" required>
                    <input type="number" name="quantity" class="form-control mb-2" placeholder="Quantity" required>
                    <button type="submit" class="btn btn-success">List for Resale</button>
                </form>
            </div>
        </div>

        <!-- Track Orders -->
        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h5 class="text-success">My Orders</h5>
                <p>Track your placed orders & customers who bought your by-products.</p>
                <a href="BuyerOrdersServlet" class="btn btn-success">View Orders</a>
            </div>
        </div>
    </div>
</div>

<footer class="text-white text-center py-3">&copy; 2025 Dairy-360</footer>
</body>
</html>
