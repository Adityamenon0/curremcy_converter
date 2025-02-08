document.addEventListener("DOMContentLoaded", function() {
    loadCurrencies();
});

async function loadCurrencies() {
    const response = await fetch("/api/rates?base=USD");
    const data = await response.json();

    let fromCurrency = document.getElementById("fromCurrency");
    let toCurrency = document.getElementById("toCurrency");

    for (let currency in data.conversion_rates) {
        let option1 = document.createElement("option");
        option1.value = currency;
        option1.textContent = currency;
        fromCurrency.appendChild(option1);

        let option2 = document.createElement("option");
        option2.value = currency;
        option2.textContent = currency;
        toCurrency.appendChild(option2);
    }
}

async function convertCurrency() {
    let from = document.getElementById("fromCurrency").value;
    let to = document.getElementById("toCurrency").value;
    let amount = document.getElementById("amount").value;

    if (!amount || amount <= 0) {
        alert("Please enter a valid amount");
        return;
    }

    const response = await fetch(`/api/convert?from=${from}&to=${to}&amount=${amount}`);
    const data = await response.json();

    document.getElementById("result").innerText = `${amount} ${from} = ${data.convertedAmount.toFixed(2)} ${to}`;
}
