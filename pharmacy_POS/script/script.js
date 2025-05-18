document
  .getElementById("loginForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    const response = await fetch("http://localhost:8080/api/v1/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username, password }),
    });

    if (response.ok) {
      window.location.href = "/pages/adminOption.html";
    } else {
      const errorText = await response.text();
      alert("Login failed: " + errorText);
    }
  });

const form = document.getElementById("loginForm");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  const username = form.username.value.trim();
  const password = form.password.value;

  if (!username || !password) {
    alert("Please fill in both username/email and password.");
    return;
  }

  // Placeholder action for login submission
  alert("Logging in as " + username);
  form.reset();
});

document.addEventListener("input", function (e) {
  if (
    e.target.classList.contains("qty-input") ||
    e.target.classList.contains("type-select")
  ) {
    const row = e.target.closest("tr");
    const qtyInput = row.querySelector(".qty-input");
    const typeSelect = row.querySelector(".type-select");
    const unitPrice = parseFloat(row.cells[3].textContent);

    let quantity = parseInt(qtyInput.value) || 0;
    const type = typeSelect.value;

    // Handle conversions
    if (type === "card") {
      quantity *= 10; // 1 card = 10 tablets
    } else if (type === "bottle") {
      quantity = 1; // typically sold per bottle
      qtyInput.value = 1;
      qtyInput.disabled = true;
    } else {
      qtyInput.disabled = false;
    }

    const subtotal = quantity * unitPrice;
    row.querySelector(".row-subtotal").textContent = subtotal.toFixed(2);

    updateTotals();
  }
});

function updateTotals() {
  let total = 0;
  document.querySelectorAll(".row-subtotal").forEach((sub) => {
    total += parseFloat(sub.textContent);
  });

  const tax = total * 0.05;
  const discount = 0;
  const final = total + tax - discount;

  document.getElementById("subtotal").textContent = total.toFixed(2);
  document.getElementById("tax").textContent = tax.toFixed(2);
  document.getElementById("discount").textContent = discount.toFixed(2);
  document.getElementById("total").textContent = final.toFixed(2);
}
