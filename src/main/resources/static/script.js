const form = document.getElementById('decision-form');
const resultDiv = document.getElementById('result');

form.addEventListener('submit', (event) => {
    event.preventDefault();
    const personalCode = document.getElementById('personal-code').value;
    const loanAmount = document.getElementById('loan-amount').value;
    const loanPeriod = document.getElementById('loan-period').value;
    const requestData = { personalCode, loanAmount, loanPeriod };

    fetch('/decision-engine', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(requestData)
    })
        .then(response => response.json())
        .then(data => {
            const { decision, amount, period } = data;
            const decisionText = decision === 'POSITIVE' ? 'Positive' : 'Negative';
            resultDiv.innerHTML = `<p>The decision is: <strong>${decisionText}</strong></p>
                           <p>The approved amount is: <strong>${amount} EUR</strong></p>
                           <p>The approved period is: <strong>${period} months</strong></p>`;
        })
        .catch(error => {
            resultDiv.innerHTML = `<p>Error: ${error.message}</p>`;
        });
});