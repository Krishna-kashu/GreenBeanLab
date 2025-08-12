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
<h2>All the bug details</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Shop Name</th>
        <th>Shop Owner</th>
        <th>Total Branch</th>
        <th>Shop Type</th>
        <th>Email</th>
        <th>Contact</th>
        <th>View</th>
    </tr>

    <c:forEach var="shop" items="${shops}">
        <tr>
            <td>${shop.shopId}</td>
            <td>${shop.shopName}</td>
            <td>${shop.shopOwner}</td>
            <td>${shop.totalBranch}</td>
            <td>${shop.shopType}</td>
            <td>${shop.email}</td>
            <td>${shop.contact}</td>
            <td>  <a href="getId?id=${shop.shopId}"> View  </a></td>

        </tr>
    </c:forEach>


<!--    <tr>-->

<!--        <td>${dto.shopId}</td>-->
<!--        <td>${dto.shopName}</td>-->
<!--        <td>${dto.shopOwner}</td>-->
<!--        <td>${dto.totalBranch}</td>-->
<!--        <td>${dto.shopType}</td>-->
<!--        <td>${dto.email}</td>-->
<!--        <td>${dto.contact}</td>-->
<!--        <td><a href="allShop"> View All </a></td>-->
<!--    </tr>-->
</table>

<a href="index">Back to Home</a>
</body>
</html>
