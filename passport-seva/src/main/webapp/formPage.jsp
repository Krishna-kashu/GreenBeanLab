<html>
<head>
    <title></title>
    <style>
        #emailerror {
          color: red;
        }
        #numbererror{
        color: red;
        }
    </style>
</head>
<body>
<h2>Bug Report Submission</h2>
<br><br>
<span id="emailerror" ></span><br>
<span id ="numbererror"> </span>
<br><br>
<form action="submit" method="post">



    <label for="passportOffice"> Passport office: </label>
    <select id="passportOffice" name="passportOffice" required>
        <option value="" selected disabled>Select passportOffice</option>
        <option value="India">India</option>
        <option value="Ahmedabad">Ahmedabad</option>
        <option value="Bhopal">Bhopal</option>
        <option value="Delhi">Delhi</option>
        <option value="Pune">Pune</option>
        <option value="Surat">Surat</option>
        <option value="Other">Other</option>
    </select><br/><br/>

    <label for="givenName">Given Name:</label><br/>
    <input type="text" id="givenName" name="givenName" required /><br/><br/>

    <label for="surName">Surname:</label><br/>
    <input type="text" id="surName" name="surName" required /><br/><br/>

    <label for="dob">Date of Birth:</label><br/>
    <input type="date" id="dob" name="dob" required /><br/><br/>

    <label for="email"> Email:</label>
    <input type="email" id="email" name="email"  onblur="checkMail()" required/><br/><br/>

    <label for="phone"> Phone: </label>
    <input  type="tel" id="phone" name="phone" onblur="checkNumber()" required/><br/><br/>

    <label for="sameLoginId"> same Login Id as email:</label>
    <input type="checkbox" id="sameLoginId" name="sameLoginId" required/><br/><br/>

    <label for="loginId">Login ID:</label>
    <input type="text" id="loginId" name="loginId" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="confirmPassword">Confirm Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword"><br><br>

    <label for="hintQuestion">Hint Question:</label>
    <select id="hintQuestion" name="hintQuestion" required>
        <option value="" selected disabled>Select Question</option>
        <option value="What is your pet name">What is your pet name</option>
        <option value="What is your mother name">What is your mother name</option>
        <option value="What is your favorite pet">What is your favorite pet</option>
    </select><br/><br/>

    <label for="hintAnswer">Hint Answer:</label>
    <input type="text" id="hintAnswer" name="hintAnswer" required><br><br>

    <input type="submit" value="Submit">

</form>

<script>
    function checkMail(){
    const email = document.getElementById('email').value;
    console.log(email)

        if(email != ""){
            var xhttp = new XMLHttpRequest();
                xhttp.open("GET", "http://localhost:8080/passport-seva/mailCheck?email=" + email);

            xhttp.send();

            xhttp.onload = function(){
                document.getElementById("emailerror").innerHTML = this.responseText;
            }
        }
    }

    function checkNumber(){
    const number = document.getElementById('phone').value;
    console.log(number)

        if(number != ""){
            var xhttp = new XMLHttpRequest();
                xhttp.open("GET", "http://localhost:8080/passport-seva/numberCheck?phone=" + number);

            xhttp.send();

            xhttp.onload = function(){
                document.getElementById("numbererror").innerHTML = this.responseText;
            }
        }
    }

</script>

<a href="index"> back to home</a>
</body>
</html>