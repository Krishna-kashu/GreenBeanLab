<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Management Home</title>
</head>
<body>

<form action="update" method="post">

    <label for="id">ID </label>
    <input type="hidden" id = "id" name="userId" value="${dto.userId}" />

    User Name:
    <input type="text" name="userName" id="userName" value="${dto.userName}" required><br><br>

    Gender:
    <select name="gender" id="gender" >
        <option value="">${dto.gender}</option>
        <option value="Male"> Male </option>
        <option value="Female"> Female </option>
        <option value="Other"> Other </option>
    </select><br><br>

    Age:
    <input type="number" name="age" id="age" value="${dto.age}"  required><br><br>

    Phone Number:
    <input type="tel" name="phoneNumber" id="phoneNumber" value="${dto.phoneNumber}"  required><br><br>

    Email:
    <input type="email" name="email" id="email" value="${dto.email}" ><br><br>

    Address:
    <input type="submit" value="Submit">


</form>
<a href="audit/${dto.userId}">Check Audit History</a>

<h3> Go <a href="index"> Home</a></h3>
</body>
</html>