<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Order Success</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
<h2 class="text-success">Order Submitted Successfully</h2>
<p class="lead">Order Complete for <strong>${name}</strong>, total amount is: <strong>₹${price}</strong>, <img src="download?profile=${file}" alt="name"></p>

<a href="orderForm.jsp" class="btn btn-primary mt-3">Place Another Order</a>
</body>
</html>
