<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored = "false" %>

<html>
<head>
    <title>All Learner Audit Logs</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        h2 { color: #333; }
    </style>
</head>
<body>
<h2>All Learner Audit History</h2>

<c:if test="${empty audits}">
    <p>No audit history available.</p>
</c:if>

<c:if test="${not empty audits}">
    <table border="1">
        <tr>
            <th>Audit ID</th>
            <th>Learner Name</th>
            <th>Created By</th>
            <th>Created On</th>
            <th>Updated By</th>
            <th>Updated On</th>
            <th> View </th>
        </tr>
        <c:forEach var="audit" items="${audits}">
            <tr>
                <td>${audit.auditId}</td>
                <td>${audit.learner.name}</td>
                <td>${audit.createdBy}</td>
                <td>${audit.createdOn}</td>
                <td>${audit.updatedBy}</td>
                <td>${audit.updatedOn}</td>
                <td>
                    <a href="edit?id=${audit.auditId}"> Edit </a> /
                    <a href="delete/${audit.auditId}"> Delete </a> /
                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>
</body>
</html>