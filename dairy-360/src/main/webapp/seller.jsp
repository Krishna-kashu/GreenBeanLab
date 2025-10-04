<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Seller Info | Admin Dashboard</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>

            .pagination .page-link {
                border-radius: 8px;
                font-weight: 500;
            }
            .pagination .page-item.active .page-link {
                background: #ff9800 !important;  /* orange active page */
                color: white !important;
                border: none;
            }
<!--                .text-white {-->
<!--                &#45;&#45;bs-text-opacity: 1;-->
<!--                color: rgb(4 128 234) !important;-->
<!--            }-->

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
            border-radius: 12px;
            border: none;
        }
        .btn-success {
            background: #198754;
            border: none;
        }
        .btn-success:hover {
            background: #157347;
        }
        .table thead {
            background: #d1e7dd;
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
<!--nav bar-->
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
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin?email=${dto.email}">Dashboard</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/add">Add Seller</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin-logout">Logout</a></li>
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

<!-- Alerts -->
<div class="container mt-3">
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success text-center">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger text-center">${errorMessage}</div>
    </c:if>
</div>

<!-- Seller Info Table -->
<div class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="text-success">Seller Information</h3>
        <div>
            <input list="sellerList" id="sellerSearch" class="form-control me-2" placeholder="Search sellers...">
            <datalist id="sellerList">
                <c:forEach var="seller" items="${sellers}">
                    <option value="${seller.firstName} ${seller.lastName}"></option>
                </c:forEach>
            </datalist>

        </div>
        <div>
            <a href="${pageContext.request.contextPath}/add" class="btn btn-success">+ Add Seller</a>
<!--            <a href="${pageContext.request.contextPath}/admin?email=${dto.email}" class="btn btn-secondary">Back</a>-->
        </div>
    </div>

    <div class="card shadow-sm p-3">
        <div class="table-responsive">
            <table class="table table-hover align-middle text-center">
                <thead class="table-success">
                <tr>
                    <th>ID</th>
                    <th>Seller Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Shop/Company</th>
                    <th>City</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="seller" items="${sellers}">
                    <tr>
                        <td>${seller.sellerId}</td>
                        <td>${seller.firstName} ${seller.lastName}</td>
                        <td>${seller.email}</td>
                        <td>${seller.phone}</td>
                        <td>${seller.address}</td>
                        <td>${seller.milkType}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/edit/${seller.sellerId}"
                               class="btn btn-sm btn-primary">Edit</a>
                            <a href="${pageContext.request.contextPath}/delete/${seller.sellerId}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('Are you sure you want to delete this seller?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- Pagination controls -->
<div class="d-flex justify-content-between align-items-center mt-3">
    <div>
        <c:if test="${currentPage > 1}">
            <a class="btn btn-outline-secondary me-2"
               href="${pageContext.request.contextPath}/list?page=${currentPage - 1}&size=${pageSize}">Previous</a>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <a class="btn btn-primary btn-sm mx-1"
                       href="${pageContext.request.contextPath}/list?page=${i}&size=${pageSize}">${i}</a>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-outline-secondary btn-sm mx-1"
                       href="${pageContext.request.contextPath}/list?page=${i}&size=${pageSize}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <a class="btn btn-outline-secondary ms-2"
               href="${pageContext.request.contextPath}/list?page=${currentPage + 1}&size=${pageSize}">Next</a>
        </c:if>
    </div>

    <div>
        <small class="text-white">Page ${currentPage} of ${totalPages} — Total ${total} sellers</small>
    </div>
</div>


<script>

const searchInput = document.getElementById('sellerSearch');
const tableRows = document.querySelectorAll('table tbody tr');

searchInput.addEventListener('input', function() {
    const query = this.value.toLowerCase();

    tableRows.forEach(row => {
        const sellerName = row.cells[1].textContent.toLowerCase(); // 2nd column = Seller Name
        if(sellerName.includes(query)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
});

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
