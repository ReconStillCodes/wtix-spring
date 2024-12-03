var ddBtn = document.querySelector(".dd-btn");
var ddMenu = document.querySelector(".dd-menu");
var items = document.querySelectorAll(".item");
var selectedItem = document.getElementById("selected-item");
var paymentMethod = document.getElementById("payment-method");

ddBtn.addEventListener("click", toggleMenu);

function toggleMenu() {
  ddMenu.classList.toggle("open");
}

function closeMenu() {
  ddMenu.classList.remove("open");
}

console.log("test");

items.forEach((item) =>
  item.addEventListener("click", (event) => {
    selectedItem.innerText = event.target.innerText;

    paymentMethod.value = event.target.getAttribute("data-value");

    items.forEach((item) => item.classList.remove("active"));

    event.target.classList.add("active");

    closeMenu();
  })
);

window.onload = () => {
  paymentMethod.value = 1;
};
