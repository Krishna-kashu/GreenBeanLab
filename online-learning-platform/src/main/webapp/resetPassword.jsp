<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .reset-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 30px;
            border-radius: 10px;
            background-color: #f8f9fa;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

<div class="reset-container">
    <h3 class="text-center mb-4">Set Your New Password</h3>
    <form id="resetForm" action="reset-password" method="post">
        <input type="hidden" name="email" value="${email}"/>
        <div class="mb-3">
            <label class="form-label">New Password</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Confirm Password</label>
            <input type="password" name="confirmPassword" class="form-control" required>
        </div>
        <div class="text-center">
            <input type="submit" value="Save Password" class="btn btn-success">
        </div>
    </form>
    <p class="text-center text-danger mt-3">${msg}</p>
</div>
<p class="text-center mt-3">
    <a href="loginPage">Back to Login</a>
</p>

<script>
    document.getElementById('resetForm').addEventListener('submit', function(e) {
    const pwd = this.password.value;
    const confirm = this.confirmPassword.value;
    if(pwd !== confirm){
        e.preventDefault();
        alert("Passwords do not match!");
    }
});
</script>
</body>
</html>