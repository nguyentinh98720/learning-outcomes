
function valform() {
    let name = document.getElementById("username").value;
    let pass = document.getElementById("userpass").value;
    if(name == ""){
        nameNote();
        return false;
    } else if(pass == ""){
        passNote();
        return false;
    } else {
        console.log("that's alright.");
        return true;
    }
}
function writename() {
    onWriteName();
    okName();
}
function writepass() {
    onWritePass();
    okPass();
}
function writedname() {
    let p_name = document.getElementById("forname");
    p_name.style.fontWeight = "bold";
    let input_name = document.getElementById("username");
    input_name.style.border = "3px solid #fff";
}
function writedpass() {
    let p_pass = document.getElementById("forpass");
    p_pass.style.fontWeight = "bold";
    let input_pass = document.getElementById("userpass");
    input_pass.style.border = "3px solid #fff";
}



function nameNote() {
    let p_name = document.getElementById("forname");
    p_name.innerHTML = "Hãy nhập trường tên";
    p_name.style.fontStyle = "italic";
    p_name.style.fontWeight = "initial";
    p_name.style.color = "red";
    let input_name = document.getElementById("username");
    input_name.style.border = "3px solid #f00";
}
function passNote() {
    let p_pass = document.getElementById("forpass");
    p_pass.innerHTML = "Chưa nhập mật khẩu";
    p_pass.style.fontStyle = "italic";
    p_pass.style.fontWeight = "initial";
    p_pass.style.color = "red";
    let input_pass = document.getElementById("userpass");
    input_pass.style.border = "3px solid #f00";
}
function okName() {
    let p_name = document.getElementById("forname");
    p_name.innerHTML = "Tên đăng nhập";
    p_name.style.fontStyle = "initial";
    p_name.style.fontWeight = "initial";
    p_name.style.color = "#000055";
}
function okPass() {    
    let p_pass = document.getElementById("forpass");
    p_pass.innerHTML = "Mật khẩu";
    p_pass.style.fontStyle = "initial";
    p_pass.style.fontWeight = "initial";
    p_pass.style.color = "#000055";
}
function onWriteName() {
    let input_name = document.getElementById("username");
    input_name.style.border = "3px solid #000";
}
function onWritePass() {
    let input_pass = document.getElementById("userpass");
    input_pass.style.border = "3px solid #000";
}