document.getElementById("btn").addEventListener("click", function () {
  const form = document.getElementById("employeeForm");
  const data = {
    firstName: form.firstName.value,
    lastName: form.lastName.value,
    address: form.address.value,
    nic: form.nic.value,
    mobile: form.mobile.value,
    dob: form.dob.value,
    password: form.password.value,
  };

  fetch("http://localhost:8080/api/v1/addemployee", {
    // Change to your actual endpoint
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) throw new Error("Failed to add employee");
      return response.json();
    })
    .then((employee) => {
      alert("Employee added successfully: ");
      form.reset();
      fetchEmployeeData();
    })
    .catch((error) => {
      alert("Error: " + error.message);
    });
});

document.addEventListener("DOMContentLoaded", function () {
  fetchEmployeeData();
});

async function fetchEmployeeData() {
  try {
    const response = await fetch("http://localhost:8080/api/v1/getemp");
    if (!response.ok) {
      throw new Error("Failed to fetch employee data");
    }
    const employees = await response.json();
    populateEmployeeTable(employees);
  } catch (error) {
    console.error("Error:", error);
    showErrorNotification("Failed to load employee data. Please try again.");
  }
}

function populateEmployeeTable(employees) {
  const tableBody = document.querySelector(".employee-table tbody");
  tableBody.innerHTML = ""; // Clear existing rows

  if (employees.length === 0) {
    const emptyRow = document.createElement("tr");
    emptyRow.innerHTML =
      '<td colspan="9" class="no-data">No employee data available</td>';
    tableBody.appendChild(emptyRow);
    return;
  }

  employees.forEach((employee) => {
    const row = document.createElement("tr");

    row.innerHTML = `
            <td>${employee.id || "N/A"}</td>
            <td>${employee.firstName || "N/A"}</td>
            <td>${employee.lastName || "N/A"}</td>
            <td>${employee.nic || "N/A"}</td>
            <td>${formatMobileNumber(employee.mobile)}</td>
            <td>${employee.address || "N/A"}</td>
            <td>${formatDate(employee.dob)}</td>
            <td>${employee.username || "N/A"}</td>
            <td class="action-buttons">
                <button class="action-btn edit" data-id="${
                  employee.id
                }">✏️</button>
                <button class="action-btn delete" data-id="${
                  employee.id
                }">🗑️</button>
            </td>
        `;

    tableBody.appendChild(row);
  });

  // Add event listeners to action buttons
  addActionButtonListeners();
}

function formatDate(dateString) {
  if (!dateString) return "N/A";
  try {
    const date = new Date(dateString);
    return date.toISOString().split("T")[0]; // YYYY-MM-DD format
  } catch (e) {
    return "Invalid Date";
  }
}

function formatMobileNumber(mobile) {
  if (!mobile) return "N/A";
  // Format as 077-123-4567 if it's a 10-digit number
  return mobile.replace(/(\d{3})(\d{3})(\d{4})/, "$1-$2-$3");
}
//Disable Add employee Button
function disableButton() {
  const addEmployeeButton = document.getElementById("btn");
  addEmployeeButton.setAttribute("disabled", true);
  addEmployeeButton.style.color = "#dbdbdb";
  addEmployeeButton.style.backgroundColor = "#385237";
}
//Enable Add employee Button
function enableButton() {
    const addEmployeeButton = document.getElementById("btn");
    addEmployeeButton.removeAttribute("disabled");
    addEmployeeButton.style.color = "";
    addEmployeeButton.style.backgroundColor = ""; 
}

function addActionButtonListeners() {
  document.querySelectorAll(".action-btn.edit").forEach((btn) => {
    btn.addEventListener("click", (e) => {
      disableButton();
      const empId = e.target.getAttribute("data-id");
      editEmployee(empId);
    });
  });

  document.querySelectorAll(".action-btn.delete").forEach((btn) => {
    btn.addEventListener("click", (e) => {
      const empId = e.target.getAttribute("data-id");
      deleteEmployee(empId);
      console.log("empID"+empId)
    });
  });
}

/*
// Attach click handlers to edit buttons once the DOM is loaded
document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll(".edit-btn").forEach((button) => {
    button.addEventListener("click", function () {
      const empId = this.getAttribute("data-id"); // Get employee ID
      if (empId) {
        editEmployee(empId);
      } else {
        console.error("Employee ID not found on button.");
      }
    });
  });
});
*/

function editEmployee(empId) {
  // Make sure empId is not undefined

  if (!empId) {
    console.error("Invalid employee ID");
    showErrorNotification("Invalid employee ID");
    return;
  }

  // Fetch employee data using query parameter (?id=empId)
  fetch(`http://localhost:8080/api/v1/getone?id=${empId}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to fetch employee data");
      }
      return response.json();
    })
    .then((employee) => {
      // Populate the form with employee data
      const form = document.getElementById("employeeForm");

      if (!form) {
        console.error("Employee form not found");
        return;
      }

      // Set form field values
      form.querySelector("#emp-id").value = employee.emp_id || "";
      form.querySelector("#first-name").value = employee.firstName || "";
      form.querySelector("#last-name").value = employee.lastName || "";
      form.querySelector("#address").value = employee.address || "";
      form.querySelector("#nic").value = employee.nic || "";
      form.querySelector("#mobile").value = employee.mobile || "";

      // Format date (YYYY-MM-DD)
      if (employee.dob) {
        const dobDate = new Date(employee.dob);
        const formattedDate = dobDate.toISOString().split("T")[0];
        form.querySelector("#dob").value = formattedDate;
      } else {
        form.querySelector("#dob").value = "";
      }

      // Hide or reset password field
      const passwordInput = form.querySelector("#password");
      if (passwordInput) {
        passwordInput.value = "";
        passwordInput.placeholder = "Enter new password to update";
      }

      // Set a custom attribute to store ID
      form.setAttribute("data-employee-id", employee.emp_id);

      // Scroll to the form
      document
        .querySelector(".employee-form")
        .scrollIntoView({ behavior: "smooth" });

      // Optional: update header
      const header = document.querySelector(".form-header");
      if (header) {
        header.textContent = `Edit Employee: ${employee.firstName} ${employee.lastName}`;
      }

      // Show save button
      const saveBtn = document.querySelector(".btn.save");
      if (saveBtn) {
        saveBtn.style.display = "block";
      }
    })
    .catch((error) => {
      console.error("Error:", error);
      showErrorNotification("Could not load employee data");
    });
}

function deleteEmployee(empId) {
  if (confirm("Are you sure you want to delete this employee?")) {
    fetch(`http://localhost:8080/api/v1/delete?id=${empId}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (response.ok) {
          showSuccessNotification("Employee deleted successfully");
          fetchEmployeeData(); // Refresh the table
        } else {
          throw new Error("Failed to delete employee");
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        showErrorNotification("Failed to delete employee");
      });
  }
}

function showSuccessNotification(message) {
  // Implement your notification system
  alert("Success: " + message);
}

function showErrorNotification(message) {
  // Implement your notification system
  alert("Error: " + message);
}



document.querySelector('.btn.update').addEventListener('click', function(e) {
    e.preventDefault(); // Prevent default form submission
    enableButton();
    // Collect form data
    const form = document.getElementById('employeeForm');
    const formData = {
        emp_id: form.querySelector('#emp-id').value,
        firstName: form.querySelector('#first-name').value,
        lastName: form.querySelector('#last-name').value,
        address: form.querySelector('#address').value,
        nic: form.querySelector('#nic').value,
        mobile: form.querySelector('#mobile').value,
        dob: form.querySelector('#dob').value,
        password: form.querySelector('#password').value
    };

    // Basic validation
    if (!formData.firstName || !formData.lastName || !formData.emp_id) {
        alert('Please fill all required fields');
        return;
    }

    // Send PUT request to update employee
    fetch('http://localhost:8080/api/v1/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        if (response.status === 404) {
            throw new Error('Employee not found');
        }
        if (!response.ok) {
            throw new Error('Failed to update employee');
        }
        return response.text(); // or response.json() if you change return type
    })
    .then(message => {
        alert('Employee updated successfully!');
        console.log('Success:', message);
        
        // Optional: Reset form or refresh employee list
        form.reset();
        fetchEmployeeData(); // If you have this function to refresh the table
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error: ' + error.message);
    });
});