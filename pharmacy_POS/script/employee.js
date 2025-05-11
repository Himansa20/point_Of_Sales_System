// //sale Page
// // Toggle option box on employee name click
// const employee = document.getElementById('emp');
// const optionBox = document.querySelector('.option');

// employee.addEventListener('click', function (e) {
//   e.stopPropagation(); // Prevent click from bubbling to document
//   optionBox.style.display = (optionBox.style.display === 'block') ? 'none' : 'block';
// });

// // Hide option box when clicking outside
// document.addEventListener('click', function () {
//   optionBox.style.display = 'none';
// });

// // Prevent closing when clicking inside the option box itself
// optionBox.addEventListener('click', function (e) {
//   e.stopPropagation();
// });


document.getElementById("loginForm").addEventListener("submit", async function (event) {
    event.preventDefault();
  
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    console.log(password)
    const response = await fetch("http://localhost:8080/api/v1/loginemp", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),

    });

    if (response.ok) {
        window.location.href = "/pages/cashier.html";
    } else {
        const errorText = await response.text();
        alert("Login failed: " + errorText);
    }
  });
  




 