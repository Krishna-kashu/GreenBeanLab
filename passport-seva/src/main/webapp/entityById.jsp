<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Passport Details</title>
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

<h2>Passport User Details</h2>
<p>DTO for ID <strong>${id}</strong> is: <code>${dto}</code></p>

<table>
    <tr>
        <th>ID</th>
        <td>${dto.id}</td>
    </tr>
    <tr>
        <th>Passport Office</th>
        <td>${dto.passportOffice}</td>
    </tr>
    <tr>
        <th>Given Name</th>
        <td>${dto.givenName}</td>
    </tr>
    <tr>
        <th>Surname</th>
        <td>${dto.surName}</td>
    </tr>
    <tr>
        <th>Date of Birth</th>
        <td>${dto.dob}</td>
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
        <th>Same Login ID</th>
        <td>${dto.sameLoginId}</td>
    </tr>
    <tr>
        <th>Login ID</th>
        <td>${dto.loginId}</td>
    </tr>
    <tr>
        <th>Password</th>
        <td>${dto.password}</td>
    </tr>
    <tr>
        <th>Hint Question</th>
        <td>${dto.hintQuestion}</td>
    </tr>
    <tr>
        <th>Hint Answer</th>
        <td>${dto.hintAnswer}</td>
    </tr>
</table>

<a href="allDetails">Get All Passports</a>
<br><br>
<a href="index">Back to Home</a>

</body>
</html>
