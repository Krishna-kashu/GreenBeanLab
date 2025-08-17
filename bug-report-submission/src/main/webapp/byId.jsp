<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Bug IDs</title>
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

<h2>Bug Details</h2>
<p>DTO for ID <strong>${i}</strong> is: <code>${dto}</code></p>

<table>
    <tr>
        <th>ID</th>
        <td>${dto.id}</td>
    </tr>
    <tr>
        <th>Reporter Name</th>
        <td>${dto.reporterName}</td>
    </tr>
    <tr>
        <th>Email</th>
        <td>${dto.email}</td>
    </tr>
    <tr>
        <th>Title</th>
        <td>${dto.title}</td>
    </tr>
    <tr>
        <th>Description</th>
        <td>${dto.description}</td>
    </tr>
    <tr>
        <th>StepsCount</th>
        <td>${dto.stepsCount}</td>
    </tr>
     <tr>
            <th>Is critical</th>
            <td>${dto.isCritical}</td>
        </tr>
</table>

<a href="getAll">get all</a>
<br><br>
<a href="index">Back to Home</a>

</body>
</html>
