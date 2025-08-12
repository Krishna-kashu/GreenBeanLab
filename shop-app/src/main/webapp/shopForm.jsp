<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shop Form</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            color: #333;
        }
        form {
            margin-top: 15px;
            background-color: #f9f9f9;
            padding: 15px;
            border-radius: 6px;
            max-width: 400px;
            box-shadow: 0 0 5px rgba(0,0,0,0.1);
        }
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"],
        input[type="number"],
        input[type="email"] {
            width: 100%;
            padding: 6px;
            margin-top: 4px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #0066cc;
            color: white;
            padding: 8px 14px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 15px;
        }
        input[type="submit"]:hover {
            background-color: #004d99;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            color: #0066cc;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<h2>Enter Shop Details</h2>

<p> <strong> ${dto}</strong> </p>
<form action="submit" method="post">
    Shop Name:
    <input type="text" id="name" name="shopName" required><br><br>

    Shop Owner:
    <input type="text" id="owner" name="shopOwner" required><br><br>

    Total Branch:
    <input type="number" id="branch" name="totalBranch" required><br><br>

    Shop Type:
    <input type="text" id="type" name="ShopType"><br><br>

    Email:
    <input type="text" id="mail" name="email"><br><br>

    Contact:
    <input type="number" id="contact" name="contact"><br><br>

    <input type="submit" value="Submit">
</form>
<br>
<a href="index">Back to Home</a>
</body>
</html>
