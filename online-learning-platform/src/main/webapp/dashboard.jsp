<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mywork.onlinelearning.dto.LearnerDTO" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body class="bg-light">
<div class="container my-5">
    <h2 class="mb-4 text-center">User Dashboard</h2>

    <!-- Success Message -->
    <c:if test="${not empty msg}">
        <div class="alert alert-success text-center">${msg}</div>
    </c:if>

    <div class="row">
        <!-- Profile Picture Section -->
        <div class="col-md-4">
            <div class="card shadow-sm p-3 text-center">
                <h5 class="mb-3">Profile Picture</h5>
                <img src="${pageContext.request.contextPath}/${dto.profileImage}"
                     alt="Profile" width="100" height="100" class="rounded-circle mb-3">

                <form action="uploadProfileImage" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="email" value="${dto.email}" />
                    <div class="mb-2">
                        <input type="file" name="profileImage" class="form-control form-control-sm" required />
                    </div>
                    <button type="submit" class="btn btn-sm btn-primary">Upload</button>
                </form>
            </div>
        </div>

        <!-- Profile Details Section -->
        <div class="col-md-8">
            <div class="card shadow-sm p-4">
                <h5 class="mb-3">Update Profile</h5>
                <form action="update" method="post">
                    <input type="hidden" name="learnerId" value="${dto.learnerId}" />
                    <input type="hidden" name="email" value="${dto.email}" />

                    <div class="mb-3">
                        <label class="form-label">Name</label>
                        <input type="text" name="name" class="form-control" value="${dto.name}" required/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email (cannot edit)</label>
                        <input type="email" class="form-control" value="${dto.email}" disabled/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Gender</label>
                        <select name="gender" class="form-select" required>
                            <option value="" disabled>Select</option>
                            <option value="Male" ${dto.gender=='Male'?'selected':''}>Male</option>
                            <option value="Female" ${dto.gender=='Female'?'selected':''}>Female</option>
                            <option value="Other" ${dto.gender=='Other'?'selected':''}>Other</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Date of Birth</label>
                        <input type="date" name="dob" class="form-control" value="${dto.dob}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Phone</label>
                        <input type="text" name="phone" class="form-control" value="${dto.phone}" required/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">State</label>
                        <select id="state" name="state" class="form-select" required>
                            <option value="" disabled>Select</option>
                            <option value="Karnataka" ${dto.state=='Karnataka'?'selected':''}>Karnataka</option>
                            <option value="Maharashtra" ${dto.state=='Maharashtra'?'selected':''}>Maharashtra</option>
                            <option value="Goa" ${dto.state=='Goa'?'selected':''}>Goa</option>
                            <option value="Telangana" ${dto.state=='Telangana'?'selected':''}>Telangana</option>
                            <option value="TamilNadu" ${dto.state=='TamilNadu'?'selected':''}>Tamil Nadu</option>
                            <option value="Kerala" ${dto.state=='Kerala'?'selected':''}>Kerala</option>
                            <option value="AndhraPradesh" ${dto.state=='AndhraPradesh'?'selected':''}>Andhra Pradesh</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">City</label>
                        <select id="city" name="city" class="form-select" required>
                            <c:forEach var="city" items="${locationData[dto.state].keySet()}">
                                <option value="${city}" ${city == dto.city ? 'selected' : ''}>${city}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Pincode</label>
                        <select id="pincode" name="pincode" class="form-select" required>
                            <c:forEach var="pin" items="${locationData[dto.state][dto.city]}">
                                <option value="${pin}" ${pin == dto.pincode ? 'selected' : ''}>${pin}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Address</label>
                        <textarea name="address" class="form-control" required>${dto.address}</textarea>
                    </div>

                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Update Profile</button>
                        <a href="success?email=${dto.email}" class="btn btn-warning mt-3">Back</a>
                        <a href="index" class="btn btn-danger">Logout</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- JavaScript -->
<script>
    const locationData = {
        "Karnataka": {
            "Bangalore": ["560001","560002","560003"],
            "Mysore": ["570001","570002"],
            "Mangalore": ["575001","575002"]
        },
        "Maharashtra": {
            "Mumbai": ["400001","400002","400003"],
            "Pune": ["411001","411002"]
        },
        "Goa": {
            "Panaji": ["403001","403002"],
            "Margao": ["403601","403602"]
        },
        "Telangana": {
            "Hyderabad": ["500001","500002"],
            "Warangal": ["506001","506002"]
        },
        "TamilNadu": {
            "Chennai": ["600001","600002"],
            "Coimbatore": ["641001","641002"]
        },
        "Kerala": {
            "Kochi": ["682001","682002"],
            "Thiruvananthapuram": ["695001","695002"]
        },
        "AndhraPradesh": {
            "Vijayawada": ["520001","520002"],
            "Visakhapatnam": ["530001","530002"]
        }
    };

    const stateSelect = document.getElementById("state");
    const citySelect = document.getElementById("city");
    const pincodeSelect = document.getElementById("pincode");

    stateSelect.addEventListener("change", function () {
        const state = this.value;
        citySelect.innerHTML = '<option value="" disabled selected>Select City</option>';
        pincodeSelect.innerHTML = '<option value="" disabled selected>Select Pincode</option>';

        if (locationData[state]) {
            Object.keys(locationData[state]).forEach(city => {
                let opt = document.createElement("option");
                opt.value = city;
                opt.textContent = city;
                citySelect.appendChild(opt);
            });
        }
    });

    citySelect.addEventListener("change", function () {
        const city = this.value;
        const state = stateSelect.value;
        pincodeSelect.innerHTML = '<option value="" disabled selected>Select Pincode</option>';

        if (locationData[state] && locationData[state][city]) {
            locationData[state][city].forEach(pin => {
                let opt = document.createElement("option");
                opt.value = pin;
                opt.textContent = pin;
                pincodeSelect.appendChild(opt);
            });
        }
    });

    // Preselect values on page load
    window.addEventListener("DOMContentLoaded", function () {
        if ("${dto.state}") {
            stateSelect.value = "${dto.state}";
            stateSelect.dispatchEvent(new Event('change'));
        }
        if ("${dto.city}") {
            citySelect.value = "${dto.city}";
            citySelect.dispatchEvent(new Event('change'));
        }
        if ("${dto.pincode}") {
            pincodeSelect.value = "${dto.pincode}";
        }
    });
</script>
</body>
</html>
