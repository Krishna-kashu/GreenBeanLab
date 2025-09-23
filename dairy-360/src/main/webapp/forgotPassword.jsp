<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>


<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password - Dairy-360</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center" style="min-height:100vh;">
    <div class="col-md-5">
        <div class="card shadow-lg">
            <div class="card-header bg-primary text-white text-center">
                <h4>Forgot Password</h4>
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/forgot-password" method="post">
                    <div class="mb-3">
                        <label>Email</label>
                        <input type="email" class="form-control" name="email" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Send Reset Link</button>
                </form>

                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger mt-3">${errorMessage}</div>
                </c:if>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success mt-3">${successMessage}</div>
                </c:if>
            </div>

<!--            <div class="card p-4 col-md-4">-->
<!--                <h3 class="text-center text-success fw-bold mb-3">Forgot Password</h3>-->
<!--                <form action="sendResetLink" method="post">-->
<!--                    <div class="mb-3">-->
<!--                        <label class="form-label">Enter your email</label>-->
<!--                        <input type="email" class="form-control" name="email" required>-->
<!--                    </div>-->
<!--                    <button type="submit" class="btn btn-primary w-100">Send Reset Link</button>-->
<!--                </form>-->
<!--                <c:if test="${not empty errorMessage}">-->
<!--                    <div class="alert alert-danger mt-2">${errorMessage}</div>-->
<!--                </c:if>-->
<!--                <c:if test="${not empty successMessage}">-->
<!--                    <div class="alert alert-success mt-2">${successMessage}</div>-->
<!--                </c:if>-->
<!--            </div>-->
        </div>
    </div>
</div>

</body>
</html>
