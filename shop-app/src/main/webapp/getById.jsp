<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shop ID Details</title>
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

<h2>Shop Details</h2>
<p>DTO for ID <strong>${i}</strong> is: <code>${dto}</code></p>

<table>
    <tr>
        <th>ID</th>
        <td>${dto.shopId}</td>
    </tr>
    <tr>
        <th>Shop Name</th>
        <td>${dto.shopName}</td>
    </tr>
    <tr>
        <th>Shop Owner</th>
        <td>${dto.shopOwner}</td>
    </tr>
    <tr>
        <th>Total Branch</th>
        <td>${dto.totalBranch}</td>
    </tr>
    <tr>
        <th>Shop Type</th>
        <td>${dto.shopType}</td>
    </tr>
    <tr>
        <th>Email</th>
        <td>${dto.email}</td>
    </tr>
    <tr>
        <th>Contact</th>
        <td>${dto.contact}</td>
    </tr>
</table>

<a href="fetchAll">get all</a>
<br><br>
<a href="index">Back to Home</a>

</body>
</html>
