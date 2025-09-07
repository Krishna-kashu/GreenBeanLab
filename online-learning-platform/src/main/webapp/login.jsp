<%@ page isELIgnored = "false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Online Learning Platform</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>

<style>
    .login-container {
        max-width: 450px;
        margin: 50px auto;
        padding: 30px;
        background-color: #f8f9fa;
        border-radius: 10px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }
</style>

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
            </ul>
        </div>
    </div>
</nav>

<div class="container my-5">
    <div class="login-container">

        <h2 class="text-center mb-4">Login to Your Account</h2>

        <form action="login" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" id="email" name="email" value="${email}"  class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password / OTP</label>
                <input type="password" id="password" name="password" class="form-control" required>
                <small class="text-muted">Use the OTP sent to your email for first login</small>
            </div>

            <div class="text-center">
                <input type="submit" class="btn btn-success w-100" value="Login">
            </div>

            <c:if test="${not empty email}">
                <div class="mb-3 text-center">
                    <button type="button" id="resendBtn" class="btn btn-secondary w-100" disabled>
                        Resend OTP (120s)
                    </button>
                </div>
            </c:if>

        </form>

        <p class="mt-3 text-center text-danger">${msg}</p>
        <p class="text-center">New here? <a href="registerPage">Register an account</a></p>
    </div>
</div>

<footer class="bg-dark text-white text-center py-3">
    <p class="mb-0">&copy; 2025 Online Learning Platform | All Rights Reserved</p>
</footer>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        let timeLeft = 120;
        let btn = document.getElementById('resendBtn');

        if (btn) {
            // Set initial text before countdown starts
            btn.innerText = "Resend OTP (" + timeLeft + "s)";
            btn.disabled = true;

            let timer = setInterval(function () {
                timeLeft--;
                if (timeLeft <= 0) {
                    clearInterval(timer);
                    btn.innerText = "Resend OTP";
                    btn.disabled = false;

                    btn.addEventListener('click', function () {
                        const email = document.getElementById('email').value;
                        if (email) {
                            window.location.href = 'resend-otp?email=' + encodeURIComponent(email);
                        } else {
                            alert("Email is missing");
                        }
                    });

                } else {
                    btn.innerText = "Resend OTP (" + timeLeft + "s)";
                }
            }, 1000);
        }
    });
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
