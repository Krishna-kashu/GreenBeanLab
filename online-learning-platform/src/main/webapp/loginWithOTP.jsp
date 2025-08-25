<%@ page isELIgnored = "false" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>OTP Verify - Online Learning Platform</title>
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
            </ul>
        </div>
    </div>
</nav>

<div class="container my-5">
    <div class="card mx-auto" style="max-width: 400px; padding: 30px; box-shadow: 0 4px 10px rgba(0,0,0,0.1);">
        <h3 class="text-center mb-4">Verify Your Email</h3>

        <p class="text-center text-muted mb-3">
            We've sent a One-Time Password (OTP) to <strong>${email}</strong>. Please enter it below to verify your account.
        </p>

        <form action="loginWithOtp" method="post">
            <input type="hidden" name="email" value="${email}">

            <div class="mb-3">
                <label for="otp" class="form-label">Enter OTP</label>
                <input type="text" id="otp" name="otp" class="form-control" required>
            </div>

            <div class="text-center">
                <input type="submit" class="btn btn-primary w-100" value="Verify OTP">
            </div>
        </form>

        <div class="d-flex justify-content-between align-items-center mt-3">
            <p id="timer" class="text-danger mb-0"></p>
            <button id="resendBtn" class="btn btn-primary btn-sm" onclick="resendOtp()" disabled>Resend OTP</button>
        </div>
        <p class="mt-3 text-center text-danger">${msg}</p>

    </div>
</div>

<footer class="bg-dark text-white text-center py-3">
    <p class="mb-0">&copy; 2025 Online Learning Platform | All Rights Reserved</p>
</footer>

<script>
    let countdown;

    function startTimer(duration) {
        let timer = duration, minutes, seconds;
        document.getElementById("resendBtn").disabled = true;

        countdown = setInterval(function () {
            minutes = parseInt(timer / 60, 10);
            seconds = parseInt(timer % 60, 10);

            document.getElementById("timer").textContent =
                "You can resend OTP in " + minutes + ":" + (seconds < 10 ? "0" : "") + seconds;

            if (--timer < 0) {
                clearInterval(countdown);
                document.getElementById("resendBtn").disabled = false;
                document.getElementById("timer").textContent = "You can resend OTP now.";
            }
        }, 1000);
    }

    function resendOtp() {
        let email = document.getElementById("email").value;

        fetch("sendOtp?email=" + email, { method: "POST" })
            .then(res => res.text())
            .then(msg => {
                alert(msg);
                if (msg.includes("OTP sent")) {
                    startTimer(120);
                }
            });
    }
    window.onload = function () {
        startTimer(120);
    };

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>