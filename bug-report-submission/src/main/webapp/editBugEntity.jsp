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
<form action="editBug" method="post">

    <label for="id"> ID: </label>
    <input type="number" id="id" name="id" value="${dto.id}" readonly>

    <label for="reporterName">Reporter Name:</label><br/>
    <input type="text" id="reporterName" name="reporterName" value="${dto.reporterName}" /><br/><br/>

    <label for="email">Email:</label><br/>
    <input type="email" id="email" name="email" value="${dto.email}" /><br/><br/>

    <label for="title">Title:</label><br/>
    <input type="text" id="title" name="title" value="${dto.title}" /><br/><br/>

    <label for="description">Description:</label><br/>
    <input type="text" id="description" name="description" value="${dto.description}"/><br/><br/>

    <label for="stepsCount">Steps Count:</label><br/>
    <input type="number" id="stepsCount" name="stepsCount" min="0" value="${dto.stepsCount}" /><br/><br/>

    <label for="isCritical">Is Critical:</label>
    <input type="checkbox" id="isCritical" name="isCritical" value="true" /><br/><br/>

    <input type="submit" value="Submit Bug" />
</form>

<a href="index"> back to home</a>
</body>
</html>
