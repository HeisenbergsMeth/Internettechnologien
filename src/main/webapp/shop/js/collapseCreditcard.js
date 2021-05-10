function collapse() {
    let cardInfos = document.getElementById("creditInfo");
    let creditCard = document.getElementById("creditcard");
    let invoice = document.getElementById("invoice");

    let classes = cardInfos.className;

    if (classes.includes("collapse")) {
        cardInfos.classList.remove("collapse");
    } else {
        cardInfos.classList.add("collapse");
    }
}