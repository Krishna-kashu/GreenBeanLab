<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us | Dairy-360</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f9f9f9;
            padding-top: 80px; /* space for fixed navbar */
        }
        .navbar {
            background: linear-gradient(90deg, #38d3ff, #4CAF50);
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
        .about-header {
            background: linear-gradient(90deg, #53bbd9, #4cb871);
            color: white;
            padding: 70px 20px;
            text-align: center;
            border-radius: 0 0 30px 30px;
        }
        .about-header h1 {
            font-weight: bold;
            font-size: 2.8rem;
        }
        .about-section {
            padding: 60px 20px;
        }
        .about-section h2 {
            color: #25a599;
            font-weight: bold;
        }
        .about-section p {
            font-size: 1.1rem;
            line-height: 1.8;
            color: #444;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
            border: none;
        }
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
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="register">Register</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="products">Products</a></li>
                <li class="nav-item"><a class="nav-link active" href="about"><i class="bi bi-info-circle me-1"></i>About Us</a></li>
                <li class="nav-item"><a class="nav-link" href="contact"><i class="bi bi-telephone me-1"></i>Contact</a></li>
                <li class="nav-item ms-3">
                    <a class="btn btn-warning btn-sm text-dark" href="#"><i class="bi bi-cart3 me-1"></i>Cart</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- About Header -->
<div class="about-header">
    <h1>About Dairy-360</h1>
    <p>Freshness, Purity, and Trust – Delivered to Your Doorstep</p>
</div>

<!-- About Section -->
<div class="container about-section">
    <div class="row align-items-center">
        <div class="col-md-6">
            <img src="images/logo.png" alt="Dairy Farm" class="img-fluid rounded shadow">
        </div>
        <div class="col-md-6">
            <h2>Who We Are</h2>
            <p>
                Dairy-360 is committed to bringing farm-fresh milk and dairy products straight to your home.
                With a strong focus on quality, purity, and customer satisfaction, we ensure that every product
                you consume is 100% organic and chemical-free.
            </p>
            <p>
                From cow’s milk to paneer, ghee, and other by-products, we follow traditional farming practices
                combined with modern hygiene standards. We are trusted by over <b>10,000+ families</b> who believe
                in our promise of freshness.
            </p>
        </div>
    </div>
</div>

<!-- Mission & Vision -->
<div class="container py-5">
    <div class="row g-4">
        <div class="col-md-6">
            <div class="card p-4 h-100">
                <h3 class="text-success"><i class="bi bi-bullseye me-2"></i>Our Mission</h3>
                <p>
                    To make organic and fresh dairy accessible to every household, while supporting local farmers
                    and promoting a healthier lifestyle for our customers.
                </p>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card p-4 h-100">
                <h3 class="text-success"><i class="bi bi-lightbulb me-2"></i>Our Vision</h3>
                <p>
                    To become the most trusted dairy brand across India by delivering high-quality, sustainable,
                    and ethically sourced dairy products to every doorstep.
                </p>
            </div>
        </div>
    </div>
</div>

<!-- Team Section --><div class="container py-5 text-center">
    <h2 class="fw-bold text-success mb-4">Meet Our Team</h2>
    <div class="row g-4" id="team-container"></div>
</div>


<!-- Footer -->
<footer class="text-white text-center py-3">
    &copy; 2025 Dairy-360. All rights reserved.
</footer>
<!-- Team Section -->

<script>
    // Load Team Members from team.json
    fetch("data/team.json")
        .then(response => response.json())
        .then(team => {
            let container = document.getElementById("team-container");
            team.forEach(member => {
                container.innerHTML += `
                    <div class="col-md-4">
                        <div class="card p-3 h-100">
                            <img src="${member.image}" class="rounded-circle mx-auto" width="120" height="120" alt="${member.name}">
                            <h5 class="mt-3">${member.name}</h5>
                            <p class="text-muted">${member.role}</p>
                        </div>
                    </div>
                `;
            });
        })
        .catch(err => console.error("Error loading team:", err));
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
