let div1 = document.getElementById("div1");
let div2 = document.getElementById("div2");
let button1 = document.getElementById("dashboard");
let button2 = document.getElementById("staffmanager");

function open_staffmanager() {
    div2.style.display =  "block";
    div1.style.display = "none";
    button1.style.backgroundColor = "inherit";
    button2.style.backgroundColor = "#d400ff";
}
function open_dashboard() {
    div1.style.display =  "block";
    div2.style.display = "none";
    button2.style.backgroundColor = "inherit";
    button1.style.backgroundColor = "#d400ff";
}