<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dairy-360 | Seller Audit Logs</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
           background: url('images/dairy-bg1.jpg') center/cover no-repeat ;
           color: white;
           padding-top: 80px;
       }
        /* Navbar color */
       .navbar {
               background: linear-gradient(90deg, #2196F3, #4CAF50);
               position: fixed;
               top: 0;
               left: 0;
               width: 100%;
               z-index: 1000;
       }

       .nav-link {
           color: #f8f9fa !important;
           transition: color 0.3s ease;
       }
       .nav-link:hover {
           color: #ffe082 !important;
       }
       .card {
           border-radius: 15px;
           box-shadow: 0px 6px 20px rgba(0,0,0,0.15);
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
            <img src="images/logo.png" alt="Logo" width="70" height="50" class="me-2">
            Dairy-360
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="${pageContext.request.contextPath}/admin?email=${dto.email}">Admin Dashboard</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="products">Products</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="about">About Us</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="contact">Contact</a></li>
                <li class="nav-item"><a class="nav-link text-white fw-semibold" href="${pageContext.request.contextPath}/admin-logout">Logout</a></li>
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
        <li><a href="${pageContext.request.contextPath}/product" class="text-white d-block py-2 px-3 drawer-link">Product Info</a></li>
        <li><a href="OrderServlet" class="drawer-link">Orders</a></li>
        <li><a href="#" class="drawer-link">Reports</a></li>
    </ul>
</div>


<!-- Seller Audit Section -->
<div class="container py-5">
    <div class="card p-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h3 class="text-success fw-bold mb-0">
                <i class="bi bi-journal-text me-2"></i> Seller Audit Logs
            </h3>
<!--            <a href="${pageContext.request.contextPath}/admin?email=${dto.email}" class="btn btn-secondary btn-sm">-->
<!--                <i class="bi bi-arrow-left me-1"></i> Back to Dashboard-->
<!--            </a>-->
        </div>

        <div class="table-responsive">
            <table class="table table-hover table-bordered align-middle">
                <thead class="table-success">
                <tr>
                    <th>Audit ID</th>
                    <th>Seller ID</th>
                    <th>Seller Name</th>
                    <th>Created By</th>
                    <th>Created On</th>
                    <th>Updated By</th>
                    <th>Updated On</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="audit" items="${auditList}">
                    <tr>
                        <td>${audit.sellerAuditId}</td>
                        <td>${audit.sellerId}</td>
                        <td>${audit.sellerName}</td>
                        <td>${audit.createdBy}</td>
                        <td>${audit.createdOn}</td>
                        <td>${audit.updatedBy}</td>
                        <td>${audit.updatedOn}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="text-center py-3 mt-5">
    &copy; 2025 Dairy-360. All rights reserved.
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
