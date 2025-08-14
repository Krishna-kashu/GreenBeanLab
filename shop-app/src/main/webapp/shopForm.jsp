<html>
<head>
    <title>Shop Form</title>
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

<h2>Enter Shop Details</h2>


<form action="submit" method="post">
    <span id="emailerror"> </span><br>

    Shop Name:
    <input type="text" id="name" name="shopName" required><br><br>

    Shop Owner:
    <input type="text" id="owner" name="shopOwner" required><br><br>

    Total Branch:
    <input type="number" id="branch" name="totalBranch" required><br><br>

    Shop Type:
    <input type="text" id="type" name="ShopType"><br><br>

    Email:
    <input type="text" id="mail" name="email" onblur="loginEmail()"><br><br>

    Contact:
    <input type="number" id="contact" name="contact"><br><br>

    <input type="submit" value="Submit">
</form>



<script>
    function loginEmail(){
    const email = document.getElementById('mail').value;
    console.log(email);

    if(email != ""){
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8089/shop_app/checkEmail?mail=" + email);

    xhttp.send();

    xhttp.onload = function(){
    document.getElementById("emailerror").innerHTML = this.responseText;
    }
    }
    }
</script>


<br>
<a href="index"> Back to Home</a>



</body>
</html>
