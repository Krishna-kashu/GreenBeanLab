<%@ page isELIgnored = "false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Audit History for User</h2>
<table border="1">
    <tr>
        <th>Audit ID</th>
        <th>Updated By</th>
        <th>Updated At</th>
    </tr>
    <c:forEach var="audit" items="${audits}">
        <tr>
            <td>${audit.auditId}</td>
            <td>${audit.updatedBy}</td>
            <td>${audit.updatedAt}</td>
        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/getAll"><h3>Back to Users</h3></a>
</body>
</html>
