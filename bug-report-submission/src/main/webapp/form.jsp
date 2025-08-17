<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Submit Bug Report</title>

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
<h2>Bug Report Submission</h2>
<span id="emailerror" ></span><br>
<form action="submitBug" method="post">
    <label for="reporterName">Reporter Name:</label><br/>
    <input type="text" id="reporterName" name="reporterName" required /><br/><br/>

    <label for="email">Email:</label><br/>
    <input type="email" id="email" name="email" onblur="checkMail()" required /><br/><br/>

    <label for="title">Title:</label><br/>
    <input type="text" id="title" name="title" required /><br/><br/>

    <label for="description">Description:</label><br/>
    <textarea id="description" name="description" required></textarea><br/><br/>

    <label for="stepsCount">Steps Count:</label><br/>
    <input type="number" id="stepsCount" name="stepsCount" min="0" /><br/><br/>

    <label for="isCritical">Is Critical:</label>
    <input type="checkbox" id="isCritical" name="isCritical" value="true" /><br/><br/>

    <input type="submit" value="Submit Bug" />
</form>
<script>
function checkMail(){
const email = document.getElementById('email').value;
console.log(email)

if(email != ""){
var xhttp = new XMLHttpRequest();
xhttp.open("GET", "http://localhost:8080/bug-report-submission/emailCheck?email=" + email);

xhttp.send();

xhttp.onload = function(){
document.getElementById("emailerror").innerHTML = this.responseText;
}
}
}
</script>
<a href="index"> back to home</a>
</body>
</html>
