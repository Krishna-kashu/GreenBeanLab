<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Success - Online Learning Platform</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          crossorigin="anonymous">
</head>
<body>

<c:set var="profilePath"
       value="${empty dto.profileImage ? 'images/default-avatar.jpg' : dto.profileImage}" />

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">

        <a class="navbar-brand" href="#">
            <img src="images/logo.png" alt="Logo" width="80" height="60">
            Online Learning
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link active" href="index">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="dashboard?email=${dto.email}">Dashboard</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Courses</a></li>
                <li class="nav-item"><a class="nav-link" href="#">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Contact</a></li>

                <c:choose>
                    <c:when test="${empty dto}">
                        <li class="nav-item"><a class="btn btn-outline-light me-2" href="registerPage">Register</a></li>
                        <li class="nav-item"><a class="btn btn-warning" href="loginPage">Login</a></li>
                    </c:when>
                    <c:otherwise>
                        <div class="dropdown">
                            <a class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
                               href="#" id="dropdownUser1" data-bs-toggle="dropdown">
                                <img src="${profilePath}" alt="Profile" class="rounded-circle" width="40" height="40">
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownUser1">
                                <li><a class="dropdown-item" href="dashboard?email=${dto.email}">Profile</a></li>
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="loginPage">Logout</a></li>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<div class="container my-5">
    <div class="text-center">
        <h1 class="mb-4">Success</h1>

        <p class="text-success">${msg}</p>

        <c:if test="${not empty dto}">
            <div class="card text-start mx-auto my-4" style="max-width: 500px;">
                <div class="card-body">
                    <h3 class="card-title">Welcome, ${dto.name}!</h3>
                    <p class="card-text"><strong>Email:</strong> ${dto.email}</p>
                    <p class="card-text"><strong>Phone:</strong> ${dto.phone}</p>
                    <p class="card-text"><strong>State:</strong> ${dto.state}</p>
                    <p class="card-text"><strong>Address:</strong> ${dto.address}</p>
                </div>
            </div>
        </c:if>

        <a href="dashboard?email=${dto.email}" class="btn btn-info mt-3">Edit Profile</a>
        <a href="loginPage" class="btn btn-warning mt-3">Logout</a>
    </div>
</div>

<footer class="bg-dark text-white text-center py-3">
    <p class="mb-0">&copy; 2025 Online Learning Platform | All Rights Reserved</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
</body>
</html>