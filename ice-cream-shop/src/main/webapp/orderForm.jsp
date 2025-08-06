<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Ice Cream</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
<h2 class="text-info mb-4">It's Ice Cream World</h2>

<form action="order" method="post" enctype="multipart/form-data" class="row g-3">
    <div class="col-md-6">
        <label for="name" class="form-label">Name:</label>
        <input type="text" class="form-control" name="name" id="name">
    </div>

    <div class="col-md-6">
        <label for="flavour" class="form-label">Flavour:</label>
        <select name="flavour" class="form-select" id="flavour">
            <option>Vanilla</option>
            <option>Chocolate</option>
            <option>Strawberry</option>
            <option>BlackCurrent</option>
            <option>Pineapple</option>
        </select>
    </div>

    <div class="col-md-4">
        <label for="quantity" class="form-label">Quantity:</label>
        <input type="number" class="form-control" name="quantity" id="quantity">
    </div>

    <div class="col-md-4">
        <div class="form-check mt-4">
            <input type="checkbox" class="form-check-input" name="takeAway" id="takeAway">
            <label class="form-check-label" for="takeAway">Take Away</label>
        </div>
    </div>

    <div class="col-md-4">
        <div class="form-check mt-4">
            <input type="checkbox" class="form-check-input" name="addOns" id="addOns">
            <label class="form-check-label" for="addOns">Add Ons</label>
        </div>
    </div>

    <div class="col-md-6">
        <label for="coupon" class="form-label">Coupon Code:</label>
        <input type="text" class="form-control" name="coupon" id="coupon">
    </div>

    <div class="col-md-6">
        <label for="profile" class="form-label">Upload Profile:</label>
        <input type="file" class="form-control" name="multipartFile" id="profile">
    </div>

    <div class="col-12">
        <button type="submit" class="btn btn-warning mt-3">ORDER</button>
    </div>
</form>
</body>
</html>
