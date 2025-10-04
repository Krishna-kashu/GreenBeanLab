<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dairy-360 | Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Navbar */
        .navbar {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            z-index: 1000;
        }

        body {
            background: url('${pageContext.request.contextPath}/images/dairy-bg1.jpg') center/cover no-repeat fixed;
            color: white;
            padding-top: 80px;
        }

        /* Dashboard Cards */
        .card {
            border: none;
            border-radius: 15px;
            transition: transform 0.3s ease, box-shadow 0.3s;
            box-shadow: 0px 4px 15px rgba(0,0,0,0.2);
            background-color: rgba(255, 255, 255, 0.9);
            color: black;
        }
        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0px 8px 25px rgba(0, 150, 136, 0.6);
        }

        .dashboard-header {
            text-align: center;
            margin-bottom: 30px;
        }

        footer {
            background: linear-gradient(90deg, #2196F3, #4CAF50);
        }

        .navbar .nav-link {
            color: #ffffff !important;
            font-weight: 500;
        }
        .navbar .nav-link:hover {
            color: #ffe082 !important;
        }

        .modal .nav-tabs .nav-link {
            background-color: #e9f7ef;
            color: #4CAF50;
        }
        .modal .nav-tabs .nav-link.active {
            background-color: #4CAF50;
            color: #fff !important;
        }
        .modal .nav-tabs .nav-link:hover {
            background-color: #d4edda;
            color: #2e7d32;
        }

        .nav-tabs .nav-link {
            background-color: #e9f7ef;
            color: #4CAF50;
            border-radius: 10px 10px 0 0;
            margin-right: 5px;
        }

        .nav-tabs .nav-link:hover {
            background-color: #d4edda;
            color: #2e7d32;
        }

        .nav-tabs .nav-link.active {
            background-color: #4CAF50;
            color: #fff !important;
            border: none;
        }

        .nav-link {
            color:  #4CAF50
        }
        .nav-link:hover {
            color: #1e3e1f !important;
        }

        .dropdown-menu {
            border-radius: 15px;
            box-shadow: 0px 6px 20px rgba(0,0,0,0.2);
            padding: 10px;
            animation: fadeIn 0.3s ease;
        }

        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(10px);}
            to {opacity: 1; transform: translateY(0);}
        }
        .modal-content {
            border-radius: 20px;
            border: none;
            box-shadow: 0px 10px 40px rgba(0,0,0,0.3);
            animation: scaleIn 0.3s ease;
        }

        @keyframes scaleIn {
            from {transform: scale(0.8); opacity: 0;}
            to {transform: scale(1); opacity: 1;}
        }

        .modal-title {
            font-weight: bold;
            text-align: center;
            margin-bottom: 10px;
            color: #4CAF50;
        }

        .modal-body img {
            border: 4px solid #4CAF50;
            padding: 5px;
            background: #fff;
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
/* Seller form box */
.form-box .card-body { padding: 1.5rem; }

/* make the panel feel like part of the navbar (optional) */
.form-box .card-header h5 { font-weight:700; }

/* small responsive tweak */
@media (max-width: 576px) {
    .form-box { margin: 0 10px; }
}


    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand fw-bold text-white" href="${pageContext.request.contextPath}/index">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" width="70" height="50" class="me-2">
            Dairy-360
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/products">Products</a></li>
                <li class="nav-item"><a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/audit-info">Audit Info</a></li>

                    <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="adminDropdown"
                       role="button" data-bs-toggle="dropdown" aria-expanded="false">

                        <img src="${pageContext.request.contextPath}/${dto.profileImagePath != null ? dto.profileImagePath : 'images/default-avatar.jpeg'}"
                             alt="Profile" width="40" height="40" class="rounded-circle me-2">

                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="adminDropdown">
                        <li>
                            <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#profileModal">
                                View Profile
                            </a>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin-logout">Logout</a></li>
                    </ul>
                </li>
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
<!--        <li><a href="#" id="seller-info-link" class="text-white d-block py-2 px-3 drawer-link">Seller Info</a></li>-->
        <li><a href="${pageContext.request.contextPath}/list" class="text-white d-block py-2 px-3 drawer-link">Seller Info</a></li>

        <li><a href="${pageContext.request.contextPath}/audit" class="text-white d-block py-2 px-3 drawer-link">View Seller Audit Logs</a></li>

        <li><a href="${pageContext.request.contextPath}/product" class="text-white d-block py-2 px-3 drawer-link">Product Info</a></li>

        <li><a href="OrderServlet" class="drawer-link">Orders</a></li>

        <li><a href="#" class="drawer-link">Reports</a></li>
    </ul>
</div>

<div class="container mt-3">
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${successMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
</div>

<div class="modal fade" id="profileModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content p-3">
            <h5 class="modal-title text-center">Admin Profile</h5>
            <div class="modal-body text-center">

                <!-- Profile Picture (clickable upload) -->
                <form action="${pageContext.request.contextPath}/admin/uploadProfileImage"
                      method="post" enctype="multipart/form-data" id="imageUploadForm">
                    <input type="hidden" name="email" value="${dto.email}">

                    <label for="profileImageInput" style="cursor:pointer;">
                        <img src="${pageContext.request.contextPath}/${dto.profileImagePath != null ? dto.profileImagePath : 'images/default-avatar.jpeg'}"
                             width="120" height="120"
                             class="rounded-circle mb-3 shadow"
                             alt="Profile">
                    </label>
                    <input type="file" id="profileImageInput" name="profileImage"
                           class="d-none" onchange="document.getElementById('imageUploadForm').submit();">
                </form>


                <!-- Edit Info Form -->
                <form action="${pageContext.request.contextPath}/updateAdminProfile" method="post" class="text-start mt-3">
                    <input type="hidden" name="email" value="${dto.email}">

                    <div class="mb-3">
                        <label>Email</label>
                        <input type="text" value="${dto.email}" class="form-control" readonly>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Name</label>
                        <input type="text" name="adminName" value="${dto.adminName}" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Phone</label>
                        <input type="text" name="phoneNumber" value="${dto.phoneNumber}" class="form-control" required>
                    </div>
                    <div class="text-end">
                        <button type="submit" class="btn btn-success rounded-pill">Update</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<!-- Dashboard -->
<div class="container py-5">
    <div class="dashboard-header">
        <h1 class="fw-bold">Admin Dashboard</h1>
        <p class="lead">Manage Users, Products & Orders</p>
    </div>

    <div class="row g-4">
        <!-- Users Management -->
        <div class="col-md-4">
            <div class="card text-center p-3">
                <img src="#" class="card-img-top" alt="Users" style="height:180px; object-fit:contain;">
                <div class="card-body">
                    <h5 class="card-title text-success">Manage Users</h5>
                    <p class="card-text">View, approve or remove Buyers, Sellers, and Delivery Staff.</p>
                    <a href="UserServlet" class="btn btn-success">Go</a>
                </div>
            </div>
        </div>

        <!-- Products Management -->
        <div class="col-md-4">
            <div class="card text-center p-3">
                <img src="#" class="card-img-top" alt="Products" style="height:180px; object-fit:contain;">
                <div class="card-body">
                    <h5 class="card-title text-success">Manage Products</h5>
                    <p class="card-text">Add, update, or delete milk & by-products from the system.</p>
                    <a href="ProductServlet" class="btn btn-success">Go</a>
                </div>
            </div>
        </div>

        <!-- Orders Management -->
        <div class="col-md-4">
            <div class="card text-center p-3">
                <img src="#" class="card-img-top" alt="Orders" style="height:180px; object-fit:contain;">
                <div class="card-body">
                    <h5 class="card-title text-success">Manage Orders</h5>
                    <p class="card-text">Track, assign delivery, and update order status.</p>
                    <a href="OrderServlet" class="btn btn-success">Go</a>
                </div>
            </div>
        </div>


    </div>
</div>

<!-- Footer -->
<footer class="text-lg-start py-4" style="background: linear-gradient(90deg, #2196F3, #4CAF50); color: #fff;">
    <div class="container">
        <div class="row text-center text-md-start">

            <!-- Address -->
            <div class="col-md-3 mb-3">
                <h6 class="fw-bold">Address</h6>
                <p class="mb-1"><i class="bi bi-geo-alt-fill me-2"></i>123 Dairy Street</p>
                <p>Hyderabad, India</p>
            </div>

            <!-- Contact -->
            <div class="col-md-3 mb-3">
                <h6 class="fw-bold">Contact</h6>
                <p class="mb-1"><i class="bi bi-telephone-fill me-2"></i>+91-9876543210</p>
                <small>Mon-Sat: 7am - 9pm</small>
            </div>

            <!-- Email -->
            <div class="col-md-3 mb-3">
                <h6 class="fw-bold">Email</h6>
                <p><i class="bi bi-envelope-fill me-2"></i>support@dairy360.com</p>
            </div>

            <!-- Social Links -->
            <div class="col-md-3 mb-3">
                <h6 class="fw-bold">Follow Us</h6>
                <a href="#" class="text-white me-3"><i class="bi bi-facebook fs-4"></i></a>
                <a href="#" class="text-white me-3"><i class="bi bi-instagram fs-4"></i></a>
                <a href="#" class="text-white"><i class="bi bi-twitter-x fs-4"></i></a>
            </div>
        </div>

        <hr class="border-light">

        <div class="text-center">
            &copy; 2025 Dairy-360. All rights reserved.
        </div>
    </div>
</footer>

<script>
    const drawer = document.getElementById("drawer");
    const toggleBtn = document.getElementById("menu-toggle");

    toggleBtn.addEventListener("click", function() {
        drawer.classList.toggle("active");
    });

    const sellerPanel = document.getElementById("seller-panel");
    const sellerClose = document.getElementById("seller-close");
    const sellerCancel = document.getElementById("seller-cancel");

    // Close drawer if user clicks outside
    document.addEventListener("click", function(e) {
        if (!drawer.contains(e.target) && !toggleBtn.contains(e.target)) {
            drawer.classList.remove("active");
        }
    });
    function openSellerPanel() {
        // close drawer
        if (drawer) drawer.classList.remove("active");

        // reveal panel
        sellerPanel.classList.remove("d-none");

        // small delay then scroll to form
        setTimeout(() => {
            sellerPanel.scrollIntoView({behavior: "smooth", block: "start"});
            // focus first input
            const firstInput = sellerPanel.querySelector('input, textarea, select');
            if (firstInput) firstInput.focus();
        }, 120);
    }

    function closeSellerPanel() {
        sellerPanel.classList.add("d-none");
        // optional: return focus to menu button
        if (toggleBtn) toggleBtn.focus();
    }

    if (sellerClose) sellerClose.addEventListener("click", closeSellerPanel);
    if (sellerCancel) sellerCancel.addEventListener("click", closeSellerPanel);

    // keep your outside-click drawer close handler (already present)
    document.addEventListener("click", function(e) {
        if (!drawer.contains(e.target) && !toggleBtn.contains(e.target)) {
            drawer.classList.remove("active");
        }
    });

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>