<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us | Dairy 360</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background: #f9f9f9;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            padding-top: 80px;
        }
        .navbar {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
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

        .contact-header {
            background: linear-gradient(90deg,  #53bbd9, #4cb871);
            color: white;
            padding: 60px 20px;
            text-align: center;
            border-radius: 0 0 30px 30px;
        }
        .contact-header h1 {
            font-weight: bold;
            font-size: 2.5rem;
        }
        .contact-card {
            background: #fff;
            border-radius: 15px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.1);
            padding: 30px;
            margin-top: -40px;
        }
        .form-control, .btn {
            border-radius: 10px;
        }
        .btn-primary {
            background: linear-gradient(90deg, #53bbd9, #4cb871);
            border: none;
        }
        .contact-info {
            margin-top: 30px;
        }
        .contact-info h5 {
            color: #25a599
        }
    </style>
</head>
<body>

<!-- Navbar (copied from index.jsp) -->
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
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="reg">Register</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="products">Products</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="bi bi-info-circle me-1"></i>About Us</a></li>
                <li class="nav-item"><a class="nav-link" href="contact"><i class="bi bi-telephone me-1"></i>Contact</a></li>
                <li class="nav-item ms-3">
                    <a class="btn btn-warning btn-sm text-dark" href="#"><i class="bi bi-cart3 me-1"></i>Cart</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Contact Header -->
<div class="contact-header">
    <h1>📞 Contact Us</h1>
    <p>We’d love to hear from you! Get in touch with Dairy 360</p>
</div>

<!-- Contact Form -->
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="contact-card">
                <form>
                    <div class="mb-3">
                        <label class="form-label">Your Name</label>
                        <input type="text" class="form-control" placeholder="Enter your full name" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Email Address</label>
                        <input type="email" class="form-control" placeholder="example@email.com" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Phone Number</label>
                        <input type="tel" class="form-control" placeholder="Enter valid Phone number" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Message</label>
                        <textarea class="form-control" rows="4" placeholder="Type your message here..." required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Send Message</button>
                </form>

                <!-- Contact Details -->
                <div class="contact-info mt-4">
                    <h5>📍 Address:</h5>
                    <p>BTM Layout 2nd Stage, Bangalore, India</p>
                    <h5>📧 Email:</h5>
                    <p>support@dairy360.com</p>
                    <h5>📱 Phone:</h5>
                    <p>+91 78978 78978</p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Map -->
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3519.2183521100314!2d77.6001885745458!3d12.91652661608442!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3bae15e2df6c9ccf%3A0xf1dc47ea7a6a133!2sX-workz%20BTM!5e1!3m2!1sen!2sin!4v1757664941698!5m2!1sen!2sin"
                    width="100%" height="350" style="border-radius:15px;" allowfullscreen="" loading="lazy"
                    referrerpolicy="no-referrer-when-downgrade">
            </iframe>
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


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
