<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fan Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
<h2 class="text-primary mb-4">Order Your Fan</h2>

<form action="fan" method="post" enctype="multipart/form-data" class="row g-3">
    <div class="col-md-6">
        <label for="modelName" class="form-label">Model Name:</label>
        <input type="text" class="form-control" name="modelName" id="modelName">
    </div>

    <div class="col-md-6">
        <label for="brand" class="form-label">Brand:</label>
        <select name="brand" class="form-select" id="brand">
            <option>Usha</option>
            <option>Havells</option>
            <option>Crompton</option>
            <option>Orient</option>
            <option>Bajaj</option>
        </select>
    </div>

    <div class="col-md-6">
        <label for="speedLevels" class="form-label">Speed Levels (20 - 200):</label>
        <input type="number" class="form-control" name="speedLevels" id="speedLevels" required>
    </div>

    <div class="col-md-3">
        <div class="form-check mt-4">
            <input type="checkbox" class="form-check-input" name="remoteControl" id="remoteControl">
            <label class="form-check-label" for="remoteControl">Remote Control</label>
        </div>
    </div>

    <div class="col-md-3">
        <div class="form-check mt-4">
            <input type="checkbox" class="form-check-input" name="energyEfficient" id="energyEfficient">
            <label class="form-check-label" for="energyEfficient">Energy Efficient</label>
        </div>
    </div>


    <div class="col-md-6">
        <label for="upload" class="form-label">Upload Receipt:</label>
        <input type="file" class="form-control" name="multipartFile" id="upload">
    </div>

    <div class="col-12">
        <button type="submit" class="btn btn-success mt-3">Place Order</button>
    </div>
</form>
</body>
</html>
