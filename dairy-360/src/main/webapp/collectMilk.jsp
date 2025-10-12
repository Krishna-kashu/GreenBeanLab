<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Collect Milk</title>
</head>
<body>
<h2>Collect Milk Entry</h2>

<form:form action="collectMilk" method="post" modelAttribute="milkDTO">
    <table>
        <tr>
            <td>Seller:</td>
            <td>
                <form:select path="sellerId">
                    <form:options items="${sellers}" itemValue="id" itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>Admin:</td>
            <td>
                <form:select path="adminId">
                    <form:options items="${admins}" itemValue="id" itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>Phone Number:</td>
            <td><form:input path="phoneNumber" id="phoneNumber" /></td>
        </tr>
        <tr>
            <td>Type of Milk:</td>
            <td>
                <form:select path="typeOfMilk">
                    <form:options items="${milkTypes}" itemValue="name" itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>Quantity (L):</td>
            <td><form:input path="quantity" /></td>
        </tr>
        <tr>
            <td>Price per Litre:</td>
            <td><form:input path="price" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>

<c:if test="${not empty successMsg}">
    <p style="color:green;">${successMsg}</p>
</c:if>
<c:if test="${not empty errorMsg}">
    <p style="color:red;">${errorMsg}</p>
</c:if>

<script>
    document.getElementById("phoneNumber").addEventListener("blur", () => {
        let phone = document.getElementById("phoneNumber").value;
        fetch(`/milk/checkPhone/${phone}`)
            .then(resp => resp.text())
            .then(data => {
                if (data === "exist") alert("Phone number already used!");
            });
    });
</script>

</body>
</html>
