<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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

    <c:if test="${not empty msg}">
        <div class="alert alert-success text-center">${msg}</div>
    </c:if>

    <div class="row">
        <!-- Profile Picture Section -->
        <div class="col-md-4">
            <div class="card shadow-sm p-3 text-center">
                <h5 class="mb-3">Profile Picture</h5>
<!--                <img src="${dto.profileImage != null ? dto.profileImage : 'images/default-avatar.jpg'}"-->
<!--                     alt="Profile" width="100" height="100">-->
                <img src="${pageContext.request.contextPath}/${dto.profileImage}"
                     alt="Profile" width="100" height="100">

                <form action="uploadProfileImage" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="email" value="${dto.email}" />
                    <div class="mb-2">
                        <input type="file" name="profileImage" class="form-control form-control-sm" required />
                    </div>
                    <button type="submit" class="btn btn-sm btn-primary">Upload</button>
                </form>

                <a href="success.jsp" class="btn btn-warning mt-3">Back</a>
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
                        <label class="form-label">Address</label>
                        <textarea name="address" class="form-control" required>${dto.address}</textarea>
                    </div>

                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Update Profile</button>
                        <a href="index" class="btn btn-warning">Logout</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
