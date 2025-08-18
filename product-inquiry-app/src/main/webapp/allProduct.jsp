<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>

<html>
<head>
    <title> Product Inquiry details</title>

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
<h2>All the product inquiry details</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Product Name</th>
        <th>Inquiry Type</th>
        <th>Message</th>
        <th>View</th>
    </tr>

    <c:forEach var="product" items="${products}">
    <tr>
        <td>${product.id}</td>
        <td>${product.fullName}</td>
        <td>${product.email}</td>
        <td>${product.phone}</td>
        <td>${product.productName}</td>
        <td>${product.inquiryType}</td>
        <td>${product.message}</td>
        <td>
            <a href="getById?productId=${product.id}"> View </a> /
            <a href="edit?id=${product.id}"> Edit </a> /
            <a href="delete/${product.id}"> Delete </a>
        </td>
    </tr>
    </c:forEach>
</table>
<a href="index">Back to Home</a>
</body>

</html>