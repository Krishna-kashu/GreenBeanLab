<%@ page isELIgnored = "false" %>

<html>
<head>
    <title>Register Toaster</title>
</head>
<body>
<h2>Register Toaster</h2>

<form action="toaster" method="post" enctype="multipart/form-data">
    <p>
        Model Name: <input type="text" name="modelName"/>
    </p>
    <p>
        Brand: <input type="text" name="brand"/>
    </p>
    <p>
        Slices: <input type="number" name="slices"/>
    </p>
    <p>
        Price: <input type="number" step="0.01" name="price"/>
    </p>
    <p>
        Image: <input type="file" name="multipartFile"/>
    </p>
    <input type="submit" value="Register"/>
</form>

<p style="color:red">${message}</p>
</body>
</html>
