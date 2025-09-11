<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register - Online Learning Platform</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <style>
        .form-container {
            max-width: 600px;
            margin: auto;
            padding: 30px;
            border-radius: 10px;
            background-color: #f8f9fa;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
    </style>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">

        <a class="navbar-brand" href="#">
                        <img src="images/logo.png" alt="Logo" width="80" height="60">

            Online Learning
        </a>


        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link active" href="index">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Courses</a></li>
                <li class="nav-item"><a class="nav-link" href="#">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Contact</a></li>

                <li class="nav-item"><a class="btn btn-outline-light me-2" href="registerPage">Register</a></li>
                <li class="nav-item"><a class="btn btn-warning" href="loginPage">Login</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/audit/all">Audit History</a></li>

            </ul>
        </div>
    </div>
</nav>

<div class="container my-5">
    <div class="form-container">
        <h2 class="text-center mb-4">Register</h2>

        <form id="registerForm" action="register" method="post" onsubmit="return validateForm()">

            <!-- Name -->
            <div class="mb-3">
                <label for="name" class="form-label">Full Name</label>
                <input type="text" id="name" name="name" class="form-control" value="${dto.name}" oninput="checkName()" required>
                <div id="nameMsg" class="text-danger mt-1"></div>
            </div>

            <!-- Gender & DOB -->
            <div class="row mb-3">
                <div class="col">
                    <label class="form-label">Gender</label>
                    <select name="gender" class="form-select" required>
                        <option value="" disabled selected>Select</option>
                        <option value="Male" ${dto.gender=='Male'?'selected':''}>Male</option>
                        <option value="Female" ${dto.gender=='Female'?'selected':''}>Female</option>
                        <option value="Other" ${dto.gender=='Other'?'selected':''}>Other</option>
                    </select>
                </div>
                <div class="col">
                    <label class="form-label">Date of Birth</label>
                    <input type="date" name="dob" class="form-control" value="${dto.dob}" required>
                </div>
            </div>

            <!-- Email -->
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" id="email" name="email" class="form-control" value="${dto.email}" onblur="checkMail()">
                <div id="emailMsg" class="text-danger mt-1"></div>
            </div>

            <!-- Phone -->
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="tel" id="phone" name="phone" class="form-control" value="${dto.phone}" maxlength="10" onblur="checkPhone()">
                <div id="phoneMsg" class="text-danger mt-1"></div>
            </div>

            <!-- State -->
            <div class="mb-3">
                <label for="state" class="form-label">State</label>
                <select id="state" name="state" class="form-select" required>
                    <option value="" disabled selected>Select</option>
                    <option value="Karnataka" ${dto.state=='Karnataka'?'selected':''}>Karnataka</option>
                    <option value="Maharashtra" ${dto.state=='Maharashtra'?'selected':''}>Maharashtra</option>
                    <option value="Goa" ${dto.state=='Goa'?'selected':''}>Goa</option>
                    <option value="Telangana" ${dto.state=='Telangana'?'selected':''}>Telangana</option>
                    <option value="TamilNadu" ${dto.state=='TamilNadu'?'selected':''}>Tamil Nadu</option>
                    <option value="Kerala" ${dto.state=='Kerala'?'selected':''}>Kerala</option>
                    <option value="AndhraPradesh" ${dto.state=='AndhraPradesh'?'selected':''}>Andhra Pradesh</option>
                </select>
            </div>

            <!-- City -->
            <div class="mb-3">
                <label for="city" class="form-label">City</label>
                <select id="city" name="city" class="form-select" required>
                    <option value="" disabled selected>Select State first</option>
                </select>
            </div>

            <!-- Pincode -->
            <div class="mb-3">
                <label for="pincode" class="form-label">Pincode</label>
                <select id="pincode" name="pincode" class="form-select" required>
                    <option value="" disabled selected>Select City first</option>
                </select>
            </div>


            <!-- Address -->
            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <textarea id="address" name="address" class="form-control" rows="3" onblur="checkAddress()">${dto.address}</textarea>
                <div id="addressMsg" class="text-danger mt-1"></div>
            </div>

            <div class="text-center">
                <input type="submit" id="registerBtn" class="btn btn-success" value="Register" disabled>
            </div>

        </form>

        <p class="mt-3 text-center" style="color:red">${msg}</p>
        <p class="text-center">Already have an account? <a href="loginPage">Login</a></p>
    </div>
</div>

<footer class="bg-dark text-white text-center py-3">
    <p class="mb-0">&copy; 2025 Online Learning Platform | All Rights Reserved</p>
</footer>

<script>
    const registerBtn = document.getElementById("registerBtn");
    const stateSelect = document.getElementById("state");
    const citySelect = document.getElementById("city");
    const pincodeSelect = document.getElementById("pincode");

    function checkPhone() {
    const phone = document.getElementById('phone').value;
    const phoneMsg = document.getElementById("phoneMsg");

    const phonePattern = /^[6-9]\d{9}$/;

    console.log("checkPhone() called with:", phone);

    if (phone === "") {
        phoneMsg.innerHTML = "Phone number is required";
        console.log("Phone empty → error set");
        enableRegister();
        return;
    }

    if (!phonePattern.test(phone)) {
        phoneMsg.innerHTML = "Invalid phone number (must start with 6-9 and be 10 digits)";
        console.log("Phone invalid → error set");
        enableRegister();
        return;
    }

    console.log("Sending AJAX for phone:", phone);

    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/online-learning-platform/phoneCheck?phone=" + phone);
    xhttp.send();

    xhttp.onload = function() {
        phoneMsg.innerHTML = this.responseText;
        console.log("Phone AJAX success → response:", this.responseText);
        enableRegister();
    }

    xhttp.onerror = function () {
        phoneMsg.innerHTML = "Error checking phone, try again";
        console.error("Phone AJAX error");
        enableRegister();
    };
}


function checkMail() {
    const email = document.getElementById('email').value;
    const emailMsg = document.getElementById("emailMsg");

    const emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}(?:\.[a-z]{2,})*$/i;

    console.log("checkMail() called with:", email);

    if (email === "") {
        emailMsg.innerHTML = "email id is required";
        console.log("Email empty → error set");
        enableRegister();
        return;
    }

    if (!emailPattern.test(email)) {
        emailMsg.innerHTML = "Invalid email id Enter a valid email (e.g. example@gmail.com)";
        console.log("Email invalid format → error set");
        enableRegister();
        return;
    }

    console.log("Sending AJAX for email:", email);

    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/online-learning-platform/mailCheck?email=" + email);
    xhttp.send();

    xhttp.onload = function() {
        emailMsg.innerHTML = this.responseText;
        console.log("Email AJAX success → response:", this.responseText);
        enableRegister();
    }
    xhttp.onerror = function () {
        emailMsg.innerHTML = "Error checking email, try again";
        console.error("Email AJAX error");
        enableRegister();
    };
}

 // Name validation
    function checkName() {
        const nameInput = document.getElementById('name');
        const nameMsg = document.getElementById("nameMsg");
        const name = nameInput.value.trim();

        console.log("checkName() called with:", name);

       if (name.length === 0) {
        nameMsg.innerHTML = "Name is required";
    } else if (name.length <= 3) {
        nameMsg.innerHTML = "Name must be more than 3 characters";
        console.log("Name too short");
    } else {
        nameMsg.innerHTML = "";
        console.log("Name valid");
    }

    enableRegister();
}

    // Address validation
    function checkAddress() {
        const addressInput = document.getElementById('address');
        const addressMsg = document.getElementById("addressMsg");
        const address = addressInput.value.trim();

        console.log("checkAddress() called with:", address);

        if (address.length === 0) {
        addressMsg.innerHTML = "Address is required";
                console.log("Address empty");

        } else if (address.length <= 10) {
            addressMsg.innerHTML = "Address must be more than 10 characters";
            console.log("Address too short");
        } else {
            addressMsg.innerHTML = "";
            console.log("Address valid");
        }

    enableRegister();
}

  function enableRegister() {
    const nameOk = document.getElementById('nameMsg').innerHTML === "";
    const addressOk = document.getElementById('addressMsg').innerHTML === "";
    const stateOk = stateSelect.value.trim() !== "";
    const genderOk = document.querySelector('select[name="gender"]').value.trim() !== "";
    const dobInput = document.querySelector('input[name="dob"]');
    const dobOk = dobInput.value && dobInput.value.trim() !== "";
    const cityOk = citySelect.value.trim() !== "";
const pinOk = pincodeSelect.value && pincodeSelect.value !== "" && pincodeSelect.value !== "Select Pincode";


    const email = document.getElementById('email').value.trim();
    const emailMsg = document.getElementById('emailMsg').innerHTML;
    const emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}(?:\.[a-z]{2,})*$/i;
    const emailOk = emailPattern.test(email) && emailMsg === "";

    const phone = document.getElementById('phone').value.trim();
    const phoneMsg = document.getElementById('phoneMsg').innerHTML;
    const phonePattern = /^[6-9]\d{9}$/;
    const phoneOk = phonePattern.test(phone) && phoneMsg === "";

    console.log("enableRegister() check:", {nameOk, pinOk, cityOk, addressOk, stateOk, genderOk, dobOk});

     if (nameOk && addressOk && stateOk && genderOk && dobOk && emailOk && phoneOk) {
        registerBtn.disabled = false;
        console.log(" Register button ENABLED");
    } else {
         registerBtn.disabled = true;
         console.log(" Register button DISABLED");
    }
}

    function validateForm() {

    checkName();
    checkAddress();
    checkMail();
    checkPhone();
    enableRegister();

    return !registerBtn.disabled;
}

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

   document.querySelector('select[name="gender"]').addEventListener('change', enableRegister);
document.querySelector('input[name="dob"]').addEventListener('change', enableRegister);
stateSelect.addEventListener('change', function() {
    const selectedState = this.value;

    citySelect.innerHTML = '<option value="" disabled selected>Select City</option>';
    pincodeSelect.innerHTML = '<option value="" disabled selected>Select City first</option>';

    if (selectedState && locationData[selectedState]) {
        Object.keys(locationData[selectedState]).forEach(city => {
            const opt = document.createElement("option");
            opt.value = city;
            opt.text = city;
            citySelect.appendChild(opt);
        });
    }

    enableRegister();
});

citySelect.addEventListener("change", function() {
    const selectedState = stateSelect.value;
    const selectedCity = this.value;

    pincodeSelect.innerHTML = '<option value="" disabled selected>Select Pincode</option>';

   if (selectedState && selectedCity && locationData[selectedState][selectedCity]) {
        locationData[selectedState][selectedCity].forEach(pin => {
            const opt = document.createElement("option");
            opt.value = pin;
            opt.text = pin;
            pincodeSelect.appendChild(opt);
        });
    }

    enableRegister();
});
if (citySelect.value) {
    citySelect.dispatchEvent(new Event('change'));
    if (pincodeSelect.value) {
        pincodeSelect.dispatchEvent(new Event('change'));
    }
}
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>