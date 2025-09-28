<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dairy-360 | Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">


    <style>
        .navbar {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            z-index: 1000;
        }

        body {
            background: url('images/dairy-bg1.jpg') center/cover no-repeat;
            color: white;
            padding-top: 80px;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .card {
            border: none;
            border-radius: 15px;
            transition: transform 0.3s ease, box-shadow 0.3s;
            box-shadow: 0px 4px 15px rgba(0,0,0,0.1);
            height: 100%;
        }
        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0px 8px 25px rgba(0, 150, 136, 0.4);
        }
        .card img {
            border-radius: 15px 15px 0 0;
            height: 200px;
            object-fit: cover;
        }
        .text-success {
            color: rgb(43 198 166) !important;
        }
        footer {
            margin-top: auto;
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
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="login">Login</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="register">Register</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold active" href="products">Products</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Products Section -->
<div class="container py-5">
    <h2 class="text-center mb-4 text-success fw-bold">Available Products</h2>
    <div class="row g-4" id="products-container">



        </div>

    </div>
    <div id="error-message" class="text-center text-danger fw-bold mt-3" style="display:none;">
        Failed to load products. Please try again later.
    </div>

<!-- Footer -->
<footer class="text-lg-start py-4" style="background: linear-gradient(90deg, #2196F3, #4CAF50); color: #fff;">
    <div class="container">
        <div class="row text-center text-md-start">

            <!-- Address -->
            <div class="col-md-3 mb-3">
                <h6 class="fw-bold">Address</h6>
                <p class="mb-1"><i class="bi bi-geo-alt-fill me-2"></i>123 Dairy Street</p>
                <p>India</p>
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


<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<!-- Load Products from JSON -->
<script>
    fetch("data/products.json")
        .then(response => response.json())
        .then(products => {
            let container = document.getElementById("products-container");
            products.forEach(product => {
                let card = `
                    <div class="col-md-4 d-flex">
                        <div class="card w-100">
                            <img src="${product.image}" alt="${product.title}">
                            <div class="card-body text-center d-flex flex-column">
                                <h5 class="card-title text-success">${product.title}</h5>
                                <p class="card-text">${product.description}</p>
                                <p class="fw-bold">₹${product.price}</p>
                                <a href="#" class="btn btn-success mt-auto">Buy Now</a>
<!--                                <a href="buyProduct?id=${product.id}" class="btn btn-success mt-auto">Buy Now</a>-->
                            </div>
                        </div>
                    </div>
                `;
                container.innerHTML += card;
            });
        })
        .catch(err => {
            console.error("Error loading products:", err);
            document.getElementById("error-message").style.display = "block";
        });
</script>


</body>
</html>
