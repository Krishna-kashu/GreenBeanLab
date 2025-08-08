<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shop Form</title>
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

</body>
</html>
