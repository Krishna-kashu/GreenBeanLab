<!DOCTYPE html>
<html>
<head>
    <title>Book Form</title>
</head>
<body>
<h2>Enter Book Details</h2>
<form action="submitBook" method="post">
    <label>Title:</label>
    <input type="text" name="title" />

    <label>Author:</label>
    <input type="text" name="author" />

    <label>Genre:</label>
    <input type="text" name="genre" />

    <label>Year Published:</label>
    <input type="number" name="yearPublished" />

    <label>Price:</label>
    <input type="number" step="0.01" name="price" />

    <input type="submit" value="Submit Book" />
</form>

</body>
</html>
