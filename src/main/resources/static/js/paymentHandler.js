var seatsPrice = document.getElementById("seats-price");

function updatePrice(event) {
  let totalPrice = parseFloat(
    seatsPrice.innerText.replace("Rp ", "").replace(",", "")
  );

  let price = parseFloat(event.target.getAttribute("data-price"));

  if (event.target.checked) {
    totalPrice += price;
  } else {
    totalPrice -= price;
  }

  seatsPrice.innerText = "Rp " + totalPrice.toFixed(2);
}
