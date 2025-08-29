<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>

<html>
<head>
    <title> User Details</title>

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

<h3> ${msg}</h3>
<h2>All the user details</h2>

<table>
    <tr>
        <th>User ID</th>
        <th>Passport Office</th>
        <th>Given Name</th>
        <th>Surname</th>
        <th>DOB</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Same Login Id as Email</th>
        <th>login Id</th>
        <th>Password</th>
        <th>Hint Question</th>
        <th>Hint Answer</th>
        <th>View</th>
    </tr>

    <c:forEach var="dto" items="${dtos}">
        <tr>
            <td>${dto.id}</td>
            <td>${dto.passportOffice}</td>
            <td>${dto.givenName}</td>
            <td>${dto.surName}</td>
            <td>${dto.dob}</td>
            <td>${dto.email}</td>
            <td>${dto.phone}</td>
            <td>${dto.sameLoginId}</td>
            <td>${dto.loginId}</td>
            <td>${dto.password}</td>
            <td>${dto.hintQuestion}</td>
            <td>${dto.hintAnswer}</td>

            <td>
                <a href="getById?id=${dto.id}"> View  </a> /
                <a href="update?id=${dto.id}"> Edit </a> /
                <a href="delete/${dto.id}"> Delete </a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="index">Back to Home</a>
</body>

</html>