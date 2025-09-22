<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Register - Dairy-360</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .navbar {
            background: linear-gradient(90deg, #38d3ff, #4CAF50);
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
        }
        body {
            background: url('images/dairy-bg1.jpg') center/cover no-repeat;
            padding-top: 90px;
            color: white;
        }
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0px 4px 20px rgba(0,0,0,0.3);
        }
        .card-header {
            background: linear-gradient(90deg, #38d3ff, #4CAF50);
        }
        .nav-link { color: #fff !important; }
        .nav-link:hover { color: #ffe082 !important; }
        footer {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
        }
        #accessDenied {
            display: none;
            text-align: center;
            padding: 50px;
            color: red;
            font-weight: bold;
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
                <li class="nav-item"><a class="nav-link" href="index">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="admin-login">Admin Login</a></li>
                <li class="nav-item"><a class="nav-link active" href="admin-register">Admin Register</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Admin Register Section -->
<div class="container d-flex justify-content-center align-items-center" style="min-height:80vh;">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header text-center text-white">
                <h4>Admin Register</h4>
            </div>
            <div class="card-body">

                <!-- Access Key Input -->
                <div id="accessKeyDiv">
                    <p class="text-center text-dark">Enter the Admin Access Key to continue:</p>
                    <input type="password" id="accessKey" class="form-control mb-3" placeholder="Enter Access Key">
                    <button class="btn btn-primary w-100" onclick="checkAccessKey()">Submit Key</button>
                </div>

                <!-- Admin Register Form (Initially Hidden) -->
                <div id="adminForm" style="display:none;">
                    <form action="admin-register" method="post" onsubmit="return validatePasswords()">
                        <div class="mb-3">
                            <label class="form-label">Admin Name</label>
                            <input type="text" class="form-control" name="adminName" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Phone</label>
                            <input type="text" class="form-control" name="phoneNumber" required pattern="[0-9]{10}" placeholder="10-digit number">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required minlength="6">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Confirm Password</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required minlength="6">
                        </div>
                        <button type="submit" class="btn btn-success w-100">Register Admin</button>
                    </form>
                </div>

                <!-- Access Denied Message -->
                <div id="accessDenied">
                    Access Denied! Only Super Admin can register new Admins.
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="text-lg-start py-4" style="background: linear-gradient(90deg, #2196F3, #4CAF50); color: #fff;">
    <div class="container text-center">
        &copy; 2025 Dairy-360. All rights reserved.
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    const SUPER_ADMIN_KEY = "SUPER123";

    function checkAccessKey(){
        var enteredKey = document.getElementById("accessKey").value;
        if(enteredKey === SUPER_ADMIN_KEY){
            document.getElementById("accessKeyDiv").style.display = "none";
            document.getElementById("adminForm").style.display = "block";
        } else {
            document.getElementById("accessKeyDiv").style.display = "none";
            document.getElementById("accessDenied").style.display = "block";
        }
    }

    function validatePasswords() {
    var pass = document.getElementById("password").value;
    var confirm = document.getElementById("confirmPassword").value;

    if (pass !== confirm) {
        alert(" Password and Confirm Password do not match!");
        return false;
    }
    return true;
}
$(document).ready(function(){
    $("input[name='email']").on("blur", function(){
        var email = $(this).val();
        if(email.length > 0){
            $.ajax({
                url: "/dairy-360/api/admin/mailCheck",
                type: "GET",
                data: { email: email },
                success: function(response){
                    if(response === "Available"){
                        console.log("Email available");
                    }
                },
                error: function(xhr){
                    if(xhr.status === 409){
                        alert("This email is already registered!");
                    } else {
                        console.error("Error while checking email:", xhr);
                    }
                }
            });
        }
    });
});


</script>

</body>
</html>
