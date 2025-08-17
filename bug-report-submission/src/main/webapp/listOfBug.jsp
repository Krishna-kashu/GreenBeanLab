<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
    <title>shop Details</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            color: #68b2e4;
        }
        h3{
            color: #ffc76f;
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
            background-color: #68b2e4;
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
<h2>All the bug details</h2>

<form action="search" method="get">
    <input type="text" name="reporterName" placeholder="Search by reporter name" required>
    <button type="submit">Search</button>
</form>

<table>
    <tr>
        <th>ID</th>
        <th>reporterName</th>
        <th>email</th>
        <th>title</th>
        <th>Description</th>
        <th>stepsCount</th>
        <th>isCritical</th>
        <th>View</th>
    </tr>

    <c:forEach var="bug" items="${bugs}">
        <tr>
            <td>${bug.id}</td>
            <td>${bug.reporterName}</td>
            <td>${bug.email}</td>
            <td>${bug.title}</td>
            <td>${bug.description}</td>
            <td>${bug.stepsCount}</td>
            <td>${bug.isCritical}</td>
            <td>
                <a href="gettingById?bugId=${bug.id}"> View  </a> /
                <a href="update?id=${bug.id}"> Edit </a> /
                <a href="delete/${bug.id}"> Delete </a>
            </td>

        </tr>
    </c:forEach>


</table>

<a href="index">Back to Home</a>
</body>
</html>
