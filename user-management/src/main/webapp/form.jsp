<html>
<head>
    <title>User Management Home</title>
</head>
<body>

<form action="save" method="post">
    User Name:
    <input type="text" name="userName" id="userName" required><br><br>

    Gender:
    <select name="gender" id="gender" required>
        <option value="">Select Gender </option>
        <option value="Male"> Male </option>
        <option value="Female"> Female </option>
        <option value="Other"> Other </option>
    </select><br><br>


    Age:
    <input type="number" name="age" id="age" required><br><br>

    Phone Number:
    <input type="tel" name="phoneNumber" id="phoneNumber" required><br><br>

    Email:
    <input type="email" name="email" id="email" required><br><br>

    <input type="submit" value="Submit">
<!--    Submit </input>-->

</form>

<h3> Go <a href="index"> Home</a></h3>
</body>
</html>