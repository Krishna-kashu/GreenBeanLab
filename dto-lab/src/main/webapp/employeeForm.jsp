<!DOCTYPE html>
<html>
<head>
    <title>Employee Form</title>
</head>
<body>
<h2>Enter Employee Details</h2>
<form action="submitEmployee" method="post">

    <label>Name:</label>
    <input type="text" name="name">

    <label>Department:</label>
    <input type="text" name="department">

    <label>Employee ID:</label>
    <input type="text" name="employeeId">

    <label>Designation:</label>
    <input type="text" name="designation">

    <label>Salary:</label>
    <input type="number" step="0.01" name="salary">

    <label>Permanent:</label>
    <input type="checkbox" name="isPermanent">

    <input type="submit" value="Submit Employee">
</form>
</body>
</html>
