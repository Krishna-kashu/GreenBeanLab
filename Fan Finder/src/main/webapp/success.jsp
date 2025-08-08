<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Order Success</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
<h2 class="text-success">Fan Order Successfully Placed!</h2>
<p class="lead">
    Fan Model: <strong>${modelName}</strong><br>
    Total Price: <strong>₹${price}</strong>
</p>
<a href="fan.jsp" class="btn btn-primary mt-3">Place Another Order</a>
</body>
</html>
