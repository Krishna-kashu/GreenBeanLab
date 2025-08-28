<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Product Inquiry Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background: #f9f9f9;
        }
        h2 {
            color: #2c3e50;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            max-width: 600px;
            margin-top: 20px;
            background: white;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px 15px;
            text-align: left;
        }
        th {
            background-color: #3498db;
            color: white;
            width: 35%;
        }
        td {
            background-color: #fdfdfd;
        }
        a {
            color: #3498db;
            text-decoration: none;
            font-weight: bold;
            margin-right: 15px;
        }
        a:hover {
            text-decoration: underline;
        }
        .links {
            margin-top: 25px;
        }
    </style>
</head>
<body>

<h2>Product Inquiry Details</h2>

<p>Details for Inquiry ID: <strong>${id}</strong></p>

<table>
    <tr>
        <th>ID</th>
        <td>${dto.id}</td>
    </tr>
    <tr>
        <th>Full Name</th>
        <td>${dto.fullName}</td>
    </tr>
    <tr>
        <th>Email</th>
        <td>${dto.email}</td>
    </tr>
    <tr>
        <th>Phone</th>
        <td>${dto.phone}</td>
    </tr>
    <tr>
        <th>Product Name</th>
        <td>${dto.productName}</td>
    </tr>
    <tr>
        <th>Inquiry Type</th>
        <td>${dto.inquiryType}</td>
    </tr>
    <tr>
        <th>Message</th>
        <td>${dto.message}</td>
    </tr>
</table>

<div class="links">
    <a href="get">View All Products</a>
    <a href="index">Back to Home</a>
</div>

</body>
</html>
