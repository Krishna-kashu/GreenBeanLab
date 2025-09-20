<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Audit Info</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <h2 class="text-center mb-4">Admin Login/Logout Audit</h2>
    <div class="table-responsive" style="max-height:500px; overflow-y:auto;">
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
            <tr>
                <th>Audit ID</th>
                <th>Admin ID</th>
                <th>Admin Name</th>
                <th>Login Time</th>
                <th>Logout Time</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="audit" items="${auditList}">
                <tr>
                    <td>${audit.id}</td>
                    <td>${audit.admin.adminId}</td>
                    <td>${audit.adminName}</td>
                    <td>
                        <fmt:formatDate value="${audit.loginTimeAsDate}" pattern="dd-MM-yyyy HH:mm:ss"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${audit.logoutTimeAsDate != null}">
                                <fmt:formatDate value="${audit.logoutTimeAsDate}" pattern="dd-MM-yyyy HH:mm:ss"/>
                            </c:when>
                            <c:otherwise>
                                Still Logged In
                            </c:otherwise>
                        </c:choose>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
