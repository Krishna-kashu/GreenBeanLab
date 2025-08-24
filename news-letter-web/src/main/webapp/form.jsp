<html>
<head>
    <title>Newsletter Subscription</title>

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
<h2>Subscribe to Newsletter</h2>
<form action="submitNewsletter" method="post">

    <label for="firstName">First Name:</label><br/>
    <input type="text" id="firstName" name="firstName" required /><br/><br/>

    <label for="lastName">Last Name:</label><br/>
    <input type="text" id="lastName" name="lastName" required /><br/><br/>

    <span id="emailerror" ></span><br>
    <label for="email">Email:</label><br/>
    <input type="email" id="email" name="email" onblur="checkMail()" required /><br/><br/>

    <label for="age">Age:</label><br/>
    <input type="number" id="age" name="age" /><br/><br/>

    <label for="gender">Gender:</label><br/>
    <select id="gender" name="gender">
        <option value="" selected disabled>Select Gender</option>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
    </select><br/><br/>

    <label for="topic">Topic of Interest:</label><br/>
    <input type="text" id="topic" name="topic" /><br/><br/>

    <input type="submit" value="Subscribe" />
</form>

<script>
    function checkMail(){
    const email = document.getElementById('email').value;
    console.log(email)

    if(email != ""){
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8081/news-letter-web/check?email=" + email);

    xhttp.send();

    xhttp.onload = function(){
    document.getElementById("emailerror").innerHTML = this.responseText;
    }
    }
    }
</script>
<br><br>
<a href="index">Back to Home</a>

</body>
</html>
