<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
    <title>Edit Passport User</title>

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
          max-width: 500px;
        }

        label {
          font-weight: bold;
          display: block;
          margin-bottom: 6px;
        }

        input[type="text"],
        input[type="email"],
        input[type="number"],
        input[type="password"],
        input[type="date"],
        select {
          width: 100%;
          padding: 8px 10px;
          margin-bottom: 15px;
          border: 1px solid #ccc;
          border-radius: 4px;
          box-sizing: border-box;
          font-size: 14px;
        }

        input[type="checkbox"] {
          margin-top: 8px;
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
<h2>Edit Passport Details</h2>
<br>
<h2>${msg}</h2><br>

<form action="editPassport" method="post">

    <label for="id">User ID:</label>
    <input type="number" id="id" name="id" value="${dto.id}" readonly />

    <label for="passportOffice">Passport Office:</label>
    <select id="passportOffice" name="passportOffice"  required>
        <option value="">${dto.passportOffice}</option>
        <option value="India">India</option>
        <option value="Ahmedabad">Ahmedabad</option>
        <option value="Bhopal">Bhopal</option>
        <option value="Delhi">Delhi</option>
        <option value="Pune">Pune</option>
        <option value="Surat">Surat</option>
        <option value="Other">Other</option>
    </select><br/><br/>
    <label for="givenName">Given Name:</label>
    <input type="text" id="givenName" name="givenName" value="${dto.givenName}" />

    <label for="surName">Surname:</label>
    <input type="text" id="surName" name="surName" value="${dto.surName}" />

    <label for="dob">Date of Birth:</label>
    <input type="date" id="dob" name="dob" value="${dto.dob}" />

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${dto.email}" />

    <label for="phone">Phone:</label>
    <input type="number" id="phone" name="phone" value="${dto.phone}" />

    <label for="sameLoginId">Same as Login ID:</label>
    <input type="checkbox" id="sameLoginId" name="sameLoginId" value="true" ${dto.sameLoginId ? "checked" : ""} />

    <label for="loginId">Login ID:</label>
    <input type="text" id="loginId" name="loginId" value="${dto.loginId}" />

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${dto.password}" />

    <label for="hintQuestion">Hint Question:</label>
    <input type="text" id="hintQuestion" name="hintQuestion" value="${dto.hintQuestion}" />

    <label for="hintAnswer">Hint Answer:</label>
    <input type="text" id="hintAnswer" name="hintAnswer" value="${dto.hintAnswer}" />

    <input type="submit" value="Update Passport" />
</form>

<br>
<a href="index">Back to Home</a>
</body>
</html>
