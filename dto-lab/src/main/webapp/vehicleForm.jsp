<!DOCTYPE html>
<html>
<head>
    <title>Vehicle Form</title>
</head>
<body>
<h2>Vehicle Information</h2>
<form action="submitVehicle" method="post">
    <label>Model:</label>
    <input type="text" name="model" />

    <label>Color:</label>
    <input type="text" name="color" />

    <label>VIN:</label>
    <input type="text" name="vin" />

    <label>Electric:</label>
    <input type="checkbox" name="isElectric" />

    <label>Type:</label>
    <select name="type">
        <option value="Sedan">Car</option>
        <option value="SUV">Bus</option>
        <option value="Truck">Scooter</option>
        <option value="Coupe">Bike</option>
    </select>

    <label>Year:</label>
    <input type="number" name="year" />

    <input type="submit" value="Submit" />
</form>
</body>
</html>
