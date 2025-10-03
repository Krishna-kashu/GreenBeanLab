<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Dairy-360 | Edit Seller</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        /* Match index.jsp theme */
        .navbar {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            z-index: 1000;
        }
        .nav-link { color: #f8f9fa !important; }
        .nav-link:hover { color: #ffe082 !important; }
        body {
            background: url('${pageContext.request.contextPath}/images/dairy-bg1.jpg') center/cover no-repeat;
            color: #333;
            padding-top: 90px;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0px 6px 20px rgba(0,0,0,0.15);
        }
        .btn-success {
            background: linear-gradient(90deg, #38d3ff, #185a9d);
            border: none;
        }
        footer {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
            color: #fff;
        }
        /* Floating menu button */
.menu-container {
    position: fixed;
    top: 90px;   /* just below navbar */
    left: 15px;
    z-index: 1100;
}
.menu-btn {
    font-size: 26px;
    background: #4CAF50;
    color: white;
    border: none;
    border-radius: 8px;
    padding: 6px 12px;
    cursor: pointer;
    box-shadow: 0px 4px 8px rgba(0,0,0,0.3);
}
.menu-btn:hover {
    background: #388E3C;
}

.drawer ul {
    padding: 15px;
}

.drawer-link {
    display: block;
    padding: 12px 15px;
    margin-bottom: 10px;
    background: rgba(255, 255, 255, 0.15); /* semi-transparent box */
    border-radius: 8px;
    font-weight: 500;
    color: white;
    text-decoration: none;
    transition: background 0.3s, transform 0.2s;
}

.drawer-link:hover {
    background: rgb(48 204 56 / 38%);
    transform: translateX(5px); /* small slide effect on hover */
}

.drawer.active {
    left: 0;
}
.drawer h5 {
    background: #3abd71;
    margin: 0;
}
.drawer {
    position: fixed;
    top: 80px;   /* height of your navbar (so it starts just below) */
    left: -250px;  /* hidden initially */
    width: 250px;
    height: calc(100% - 80px); /* full height minus navbar */
    transition: all 0.3s ease;
    z-index: 1200;
    overflow-y: auto;
    background: linear-gradient(90deg, #38d3ff, #4CAF50); /* match navbar style */
}
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand fw-bold text-white" href="index">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" width="70" height="50" class="me-2">
            Dairy-360
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/admin?email=${dto.email}">Admin Dashboard</a></li>
                <li class="nav-item"><a class="nav-link fw-semibold" href="products">Products</a></li>
                <li class="nav-item"><a class="nav-link fw-semibold" href="about">About Us</a></li>
                <li class="nav-item"><a class="nav-link fw-semibold" href="contact">Contact</a></li>
                <li class="nav-item"><a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/admin-logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Floating Hamburger Menu -->
<div class="menu-container">
    <button class="menu-btn" id="menu-toggle">&#9776;</button>
</div>

<!-- Drawer Menu -->
<div id="drawer" class="drawer text-white">
    <h5 class="p-3 border-bottom">Dashboard Menu</h5>
    <ul class="list-unstyled">
        <li><a href="${pageContext.request.contextPath}/list" class="drawer-link">Seller Info</a></li>
        <li><a href="${pageContext.request.contextPath}/audit" class="drawer-link">View Seller Audit Logs</a></li>
        <li><a href="${pageContext.request.contextPath}/product" class="drawer-link">Product Info</a></li>
        <li><a href="OrderServlet" class="drawer-link">Orders</a></li>
        <li><a href="#" class="drawer-link">Reports</a></li>
    </ul>
</div>


<!-- Content -->
<div class="container py-5">
    <h3 class="mb-4 text-success fw-bold text-center">Edit Seller</h3>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <div class="card p-4 bg-light">
        <form id="sellerForm" action="${pageContext.request.contextPath}/save" method="post" enctype="multipart/form-data" novalidate>
            <input type="hidden" name="sellerId" value="${seller.sellerId}"/>

            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">First Name <span class="text-danger">*</span></label>
                    <input name="firstName" type="text" class="form-control"
                           value="${seller.firstName}" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Last Name <span class="text-danger">*</span></label>
                    <input name="lastName" type="text" class="form-control"
                           value="${seller.lastName}" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Email (cannot edit)</label>
                    <input name="email" type="email" class="form-control"
                           value="${seller.email}" readonly>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Phone Number <span class="text-danger">*</span></label>
                    <input name="phone" type="tel" class="form-control"
                           pattern="[0-9]{10}" value="${seller.phone}" required>
                </div>
                <div class="col-md-12">
                    <label class="form-label">Address</label>
                    <textarea name="address" class="form-control" rows="2">${seller.address}</textarea>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Milk Type</label>
                    <select name="milkType" class="form-select">
                        <option value="">Choose...</option>
                        <option ${seller.milkType=='Cow Milk'?'selected':''}>Cow Milk</option>
                        <option ${seller.milkType=='Buffalo Milk'?'selected':''}>Buffalo Milk</option>
                        <option ${seller.milkType=='Goat Milk'?'selected':''}>Goat Milk</option>
                        <option ${seller.milkType=='Organic A2 Milk'?'selected':''}>Organic A2 Milk</option>
                    </select>
                </div>

                <div class="col-12 text-end mt-3">
                    <a href="${pageContext.request.contextPath}/list" class="btn btn-secondary">Back</a>
                    <button type="submit" class="btn btn-success">Save</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- Footer -->
<footer class="text-center py-3 mt-5">
    <div class="container">
        <p class="mb-0">&copy; 2025 Dairy-360. All rights reserved.</p>
    </div>
</footer>
<script>
    const drawer = document.getElementById("drawer");
    const toggleBtn = document.getElementById("menu-toggle");

    toggleBtn.addEventListener("click", function() {
        drawer.classList.toggle("active");
    });

    // Close drawer when clicking outside
    document.addEventListener("click", function(e) {
        if (!drawer.contains(e.target) && !toggleBtn.contains(e.target)) {
            drawer.classList.remove("active");
        }
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
