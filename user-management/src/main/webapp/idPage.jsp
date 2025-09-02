<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Details By Id</title>

</head>
<body>

<h2>User Details</h2>

<p>User Details for ID: <strong>${id}</strong></p>

<table>
    <tr>
        <th>ID</th>
        <td>${user.userId}</td>
    </tr>
    <tr>
        <th>User Name</th>
        <td>${user.userName}</td>
    </tr>
    <tr>
        <th>Gender</th>
        <td>${user.gender}</td>
    </tr>
    <tr>
        <th>Age</th>
        <td>${user.age}</td>
    </tr>
    <tr>
        <th>Email</th>
        <td>${user.email}</td>
    </tr>
    <tr>
        <th>Phone Number</th>
        <td>${user.phoneNumber}</td>
    </tr>
</table>

    <a href="get">View All </a>
    <a href="index">Back to Home</a>

</body>
</html>
