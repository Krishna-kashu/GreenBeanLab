<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
    <title>  All Users  </title>
</head>
<body>
<h2> All the users</h2>

<table>
    <tr>
        <th>Id</th>
        <th>User Name</th>
        <th>Gender</th>
        <th>Age</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>View</th>
    </tr>

    <c:forEach var="u" items="${user}">

        <tr>
            <td>${u.userId}</td>
            <td>${u.userName}</td>
            <td>${u.gender}</td>
            <td>${u.age}</td>
            <td>${u.email}</td>
            <td>${u.phoneNumber}</td>
            <td>
                <a href="getById?userId=${u.userId}"> View </a> /
                <a href="edit?id=${u.userId}"> Edit </a> /
                <a href="delete/${u.userId}"> Delete </a> /

            </td>
        </tr>

    </c:forEach>

</table>
<a href="index">Back to Home</a>
</body>
</html>