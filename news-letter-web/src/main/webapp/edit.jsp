<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
    <title>Edit Bug Report</title>

    <style>
        body {
          font-family: Arial, sans-serif;
          background-color: #f8f9fa;
          margin: 30px;
          color: #333;
        }

        h1, h2 {
          color: #2c3e50;
        }

        form {
          background: #fff;
          padding: 20px 30px;
          border: 1px solid #ddd;
          border-radius: 8px;
          max-width: 400px;
        }

        label {
          font-weight: bold;
          display: block;
          margin-bottom: 6px;
        }

        input[type="text"],
        input[type="email"],
        input[type="number"],
        select,
        textarea {
          width: 100%;
          padding: 8px 10px;
          margin-bottom: 15px;
          border: 1px solid #ccc;
          border-radius: 4px;
          box-sizing: border-box;
          font-size: 14px;
        }

        input[type="submit"] {
          background-color: #007bff;
          border: none;
          color: white;
          padding: 10px 18px;
          cursor: pointer;
          font-size: 16px;
          border-radius: 4px;
          transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
          background-color: #0056b3;
        }

        a {
          color: #007bff;
          text-decoration: none;
          font-weight: 600;
        }

        a:hover {
          text-decoration: underline;
        }
    </style>
</head>
<body>
<h2>Edit Bug Details</h2>
<br>
<h2> ${msg}</h2><br>
<form action="update" method="post">

    <label for="id"> ID: </label>
    <input type="number" id="id" name="id" value="${dto.id}" readonly>

    <label for="firstName">First Name:</label><br/>
    <input type="text" id="firstName" name="firstName" value="${dto.firstName}" /><br/><br/>

    <label for="lastName">Last Name:</label><br/>
    <input type="text" id="lastName" name="lastName" value="${dto.lastName}" /><br/><br/>

    <label for="email">Email:</label><br/>
    <input type="email" id="email" name="email" value="${dto.email}" /><br/><br/>

    <label for="age">Age:</label><br/>
    <input type="number" id="age" name="age" value="${dto.age}" /><br/><br/>

    <label for="gender">Gender:</label><br/>
    <select id="gender" name="gender">
        <option value="" >${dto.gender}</option>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
    </select><br/><br/>

    <label for="topic">Topic of Interest:</label>
    <input type="text" id="topic" name="topic" value="${dto.topic}" /><br/><br/>

    <input type="submit" value="Submit" />
</form>

<a href="index"> back to home</a>
</body>
</html>
