<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password - Dairy-360</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center" style="min-height:100vh;">
    <div class="col-md-6">
        <div class="card shadow-lg rounded-3">
            <div class="card-header bg-success text-white text-center">
                <h4>Reset Password</h4>
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/reset-password" method="post">
                    <input type="hidden" name="token" value="${token}">

                    <div class="mb-3">
                        <label>New Password</label>
                        <input type="password" class="form-control" name="password" required minlength="6">
                    </div>
                    <div class="mb-3">
                        <label>Confirm Password</label>
                        <input type="password" class="form-control" name="confirmPassword" required minlength="6">
                    </div>
                    <button type="submit" class="btn btn-success w-100">Reset Password</button>
                </form>

                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger mt-3">${errorMessage}</div>
                </c:if>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success mt-3">${successMessage}</div>
                    <div class="text-center mt-3">
                        <a href="admin-login" class="btn btn-link">Back to Login</a>
                    </div>
                </c:if>

                <p class="text-center text-danger mt-3">${msg}</p>
            </div>
            <div class="card-footer text-center">
                <a href="${pageContext.request.contextPath}/admin-login" class="text-success">Back to Login</a>
            </div>
        </div>
    </div>
</div>

<!--<div class="card p-4 col-md-4">-->
<!--    <h3 class="text-center text-success fw-bold mb-3">Reset Password</h3>-->
<!--    <form action="reset-password" method="post">-->
<!--        <input type="hidden" name="token" value="${token}">-->
<!--        <div class="mb-3">-->
<!--            <label class="form-label">New Password</label>-->
<!--            <input type="password" class="form-control" name="password" required>-->
<!--        </div>-->
<!--        <div class="mb-3">-->
<!--            <label class="form-label">Confirm Password</label>-->
<!--            <input type="password" class="form-control" name="confirmPassword" required>-->
<!--        </div>-->
<!--        <button type="submit" class="btn btn-success w-100">Reset Password</button>-->
<!--    </form>-->
<!--</div>-->

</body>
</html>