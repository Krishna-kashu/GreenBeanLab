<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>

    <title>Product Inquiry Form</title>
    <style>
        body {
          font-family: Arial, sans-serif;
          background-color: #f8f9fa;
          margin: 30px;
          color: #333;
        }

        h1, h2 {
          color: #2c3e50;
        }

        form {
          background: #fff;
          padding: 20px 30px;
          border: 1px solid #ddd;
          border-radius: 8px;
          max-width: 400px;
        }

        label {
          font-weight: bold;
          display: block;
          margin-bottom: 6px;
        }

        input[type="text"],
        input[type="email"],
        input[type="number"],
        select,
        textarea {
          width: 100%;
          padding: 8px 10px;
          margin-bottom: 15px;
          border: 1px solid #ccc;
          border-radius: 4px;
          box-sizing: border-box;
          font-size: 14px;
        }

        input[type="submit"] {
          background-color: #007bff;
          border: none;
          color: white;
          padding: 10px 18px;
          cursor: pointer;
          font-size: 16px;
          border-radius: 4px;
          transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
          background-color: #0056b3;
        }

        a {
          color: #007bff;
          text-decoration: none;
          font-weight: 600;
        }

        a:hover {
          text-decoration: underline;
        }
    </style>
</head>
<body>

<h2>Product Inquiry Form</h2>

<form action="update" method="POST">

    <label for="id">ID </label>
    <input type="hidden" id = "id" name="id" value="${dto.id}" />

    <label for="fullName">Full Name</label>
    <input type="text" id="fullName" name="fullName" value="${dto.fullName}" required />

    <label for="email">Email</label>
    <input type="email" id="email" name="email" value="${dto.email}" required />

    <label for="phone">Phone Number</label>
    <input type="tel" id="phone" name="phone"  value="${dto.phone}" placeholder="Digits only" required />

    <label for="productName">Product Name</label>
    <input type="text" id="productName" name="productName" value="${dto.productName}" required />

    <label for="inquiryType">Inquiry Type</label>
    <select id="inquiryType" name="inquiryType">
        <option value="" >${dto.inquiryType}</option>
        <option value="Warranty">Warranty</option>
        <option value="General Question">General Question</option>
        <option value="Pricing">Pricing</option>
        <option value="Technical Support">Technical Support</option>
        <option value="Other">Other</option>
    </select>

    <label for="message">Message</label>
    <textarea id="message" name="message" rows="4" required>${dto.message}</textarea>

    <button type="submit">Send Inquiry</button>
</form>
<br>
<br>
<h2>
    <a href="index">Back to Home</a>
</h2>
</body>
</html>
