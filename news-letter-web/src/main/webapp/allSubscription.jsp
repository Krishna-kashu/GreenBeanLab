<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>

<html>
<head>
    <title> NewsLetter Subscription details</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            color: #333;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 15px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px 10px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        a {
            color: #0066cc;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h2>All the NewsLetter Subscription details</h2>

<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Age</th>
        <th>Gender</th>
        <th>Topic</th>
        <th>View</th>
    </tr>

    <c:forEach var="d" items="${allDto}">
    <tr>
        <td>${d.id}</td>
        <td>${d.firstName}</td>
        <td>${d.lastName}</td>
        <td>${d.email}</td>
        <td>${d.age}</td>
        <td>${d.gender}</td>
        <td>${d.topic}</td>
        <td><a href="getById?newsId=${d.id}">View</a></td>
    </tr>
    </c:forEach>
</table>
<a href="index.jsp">Back to Home</a>
</body>

</html>