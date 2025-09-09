<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Learning Platform</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">

        <a class="navbar-brand" href="#">
            <img src="images/logo.png" alt="Logo" width="80" height="60">

            Online Learning
        </a>


        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link active" href="index">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Courses</a></li>
                <li class="nav-item"><a class="nav-link" href="#">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Contact</a></li>

                <li class="nav-item"><a class="btn btn-outline-light me-2" href="registerPage">Register</a></li>
                <li class="nav-item"><a class="btn btn-warning" href="loginPage">Login</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/allAudit">Audit History</a></li>

            </ul>
        </div>
    </div>
</nav>

<div class="container my-5">
    <div class="text-center">
        <h1 class="mb-4">Welcome to Our Online Learning Platform</h1>
        <p style="color:green">${msg}</p>
        <p class="lead">
            Learn from the best instructors anytime, anywhere. Our platform offers
            a wide range of courses in programming, design, business, and more.
        </p>
        <p>
            Join thousands of learners who are upgrading their skills and advancing
            their careers with flexible, interactive, and affordable learning.
        </p>
        <a href="registerPage" class="btn btn-primary btn-lg mt-3">Explore Courses</a>

        </div>
</div>

<footer class="bg-dark text-white text-center py-3">
    <p class="mb-0">&copy; 2025 Online Learning Platform | All Rights Reserved</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>