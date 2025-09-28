<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Dairy-360 | Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        /* Navbar color */
        .navbar {
                background: linear-gradient(90deg, #4CAF50, #2196F3);
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

        /* Hero Section */
        .hero {
            height: 100vh;
            background: url('images/dairy-bg1.jpg') center/cover no-repeat fixed;
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            text-shadow: 2px 2px 10px rgba(0,0,0,0.6);
            position: relative;
        }

        body {
            background: url('images/dairy-bg1.jpg') center/cover no-repeat ;
            color: white;
            padding-top: 80px;
        }

        .hero::before {
            content: "";
            position: absolute;
            top: 1px; left: 0; right: 0; bottom: 0;
            background: rgba(0,0,0,0.5);
            z-index: 1;
        }
        .hero h1, .hero p, .hero a {
            position: relative;
            z-index: 2;
        }

        .hero h1 {
            font-size: 3.5rem;
            font-weight: bold;
        }

        @media (max-width: 768px) {
            .hero h1 {
                font-size: 2rem;
            }
            .hero p {
                font-size: 1rem;
            }
        }

        /* Cards Section */
        .card {
            border: none;
            border-radius: 15px;
            transition: transform 0.3s ease, box-shadow 0.3s;;
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
            --bs-text-opacity: 1;
            color: rgb(43 198 166) !important;
        }

        /* Footer */
        footer {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
        }
        .btn-success {
            background: linear-gradient(90deg, #38d3ff, #185a9d);
            border: none;
        }

        .review-card {
            border-radius: 15px;
            padding: 25px;
            box-shadow: 0px 6px 20px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
            color: #333;
        }
        .review-card:hover {
            transform: translateY(-8px);
        }
        .review-card blockquote {
            font-style: italic;
            font-size: 1.1rem;
            margin-bottom: 15px;
        }
        .review-card .author {
            font-weight: bold;
            color: #185a9d;
        }
        .review-1 { background: linear-gradient(135deg, #e0f7fa, #b2ebf2); }
        .review-2 { background: linear-gradient(135deg, #f1f8e9, #c8e6c9); }
        .review-3 { background: linear-gradient(135deg, #fff3e0, #ffe0b2); }

        .scrolling {
            --bs-bg-opacity: 1;
            background-color: rgb(30 204 160 / 87%) !important
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
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="reg">Register</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="products">Products</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="admin-register">Admin Register</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="admin-login">Admin Login</a></li>
<!--                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="seller">Seller</a></li>-->
<!--                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="buyer">Buyer</a></li>-->
<!--                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="delivery">Delivery</a></li>-->

                <li class="nav-item"><a class="nav-link" href="about"><i class="bi bi-info-circle me-1"></i>About Us</a></li>
                <li class="nav-item"><a class="nav-link" href="contact"><i class="bi bi-telephone me-1"></i>Contact</a></li>
                <li class="nav-item ms-3">
                    <a class="btn btn-warning btn-sm text-dark" href="#"><i class="bi bi-cart3 me-1"></i>Cart</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<!-- Offers Scrolling Strip -->
<div class="scrolling py-2">
    <marquee behavior="scroll" direction="left" scrollamount="8" class="fw-bold text-dark">
        🥛 Fresh Cow Milk @ ₹50/L | 🧈 15% Off on Ghee | 📦 Free Delivery above ₹500 | 🧀 Paneer + Milk Combo – Save ₹40 | 🎉 Monthly Subscription – Extra 10% Off
    </marquee>
</div>


<!-- Hero Section -->
<section class="hero text-center">
    <h1>Welcome to Dairy-360</h1>

    <h1>Fresh Dairy at Your Doorstep</h1>
    <p class="lead">Farm-fresh milk & by-products with guaranteed quality</p>
    <div>
        <a href="products" class="btn btn-success btn-lg m-2">Shop Now</a>
        <a href="reg" class="btn btn-light btn-lg m-2">Join Us</a>
    </div>
    <div class="mt-3">
        <span class="badge bg-success">100% Organic</span>
        <span class="badge bg-warning text-dark">Trusted by 10,000+ Families</span>
    </div>

</section>

<!-- Why Choose Us -->
<div class="container py-5 text-center">
    <h2 class="fw-bold text-success mb-4">Why Choose Dairy-360?</h2>
    <div class="row g-4">
        <div class="col-md-4">
            <i class="bi bi-shield-check fs-1 text-success"></i>
            <h5 class="mt-2">100% Organic</h5>
            <p>No chemicals, no preservatives. Just pure goodness.</p>
        </div>
        <div class="col-md-4">
            <i class="bi bi-truck fs-1 text-success"></i>
            <h5 class="mt-2">Fast Delivery</h5>
            <p>Fresh milk delivered within hours of milking.</p>
        </div>
        <div class="col-md-4">
            <i class="bi bi-heart-fill fs-1 text-success"></i>
            <h5 class="mt-2">Trusted by Families</h5>
            <p>Serving thousands of happy customers daily.</p>
        </div>
    </div>
</div>

<!-- Service Section -->
<div class="container py-5">
    <h2 class="text-center mb-4 text-success fw-bold">Our Services</h2>
    <div class="row g-4" id="services-container"></div>
</div>

<!-- Featured Products -->
<div class="container py-5">
    <h2 class="text-center mb-4 text-success fw-bold">Featured Products</h2>
    <div class="row g-4" id="featured-products"></div>
    <div class="text-center mt-3">
        <a href="products" class="btn btn-success">View All Products</a>
    </div>
</div>

<!-- Customer Reviews -->
<div class="bg-light text-dark py-5">
    <div class="container text-center">
        <h2 class="fw-bold text-success mb-4">What Our Customers Say</h2>
        <div class="row g-4" id="reviews-container"></div>
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


<script>
    fetch("data/services.json")
        .then(response => response.json())
        .then(services => {
            let container = document.getElementById("services-container");
            services.forEach(service => {
                let card = `
                    <div class="col-md-4">
                        <div class="card">
                            <img src="${service.image}" alt="${service.title}">
                            <div class="card-body text-center">
                                <h5 class="card-title text-success">${service.title}</h5>
                                <p class="card-text">${service.description}</p>
                            </div>
                        </div>
                    </div>
                `;
                container.innerHTML += card;
            });
        })
        .catch(err => console.error("Error loading services:", err));

    // Load Customer Reviews
        fetch("data/reviews.json")
            .then(res => res.json())
            .then(reviews => {
                let container = document.getElementById("reviews-container");
                reviews.forEach((r, index) => {
                    let colorClass = `review-${(index % 3) + 1}`;
                    container.innerHTML += `
                        <div class="col-md-4">
                            <div class="review-card ${colorClass} h-100">
                                <blockquote>"${r.review}"</blockquote>
                                <p class="author">- ${r.name}, ${r.location}</p>
                            </div>
                        </div>
                    `;
                });
            });

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>