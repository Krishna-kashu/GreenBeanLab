<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>

<!DOCTYPE html>
<html>
<head>
    <title> Product Inquiry details</title>
</head>
<body>
<h2>All the product inquiry details</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Product Name</th>
        <th>Inquiry Type</th>
        <th>Message</th>
    </tr>

    <c:forEach var="product" items="${products}">
    <tr>
        <td>${product.id}</td>
        <td>${product.fullName}</td>
        <td>${product.email}</td>
        <td>${product.phone}</td>
        <td>${product.productName}</td>
        <td>${product.inquiryType}</td>
        <td>${product.message}</td>
    </tr>
    </c:forEach>
</table>
<a href="index.jsp">Back to Home</a>
</body>

</html>