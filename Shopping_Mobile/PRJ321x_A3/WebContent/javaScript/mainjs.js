function togglelist() {
    document.getElementById("productlist").style.display = "block";
}
function togglelist1() {
    document.getElementById("productlist").style.display = "none";
}
function togglelist2() {
    document.getElementById("productlist").style.display = "none";
}

function searchsomething() {
	let text = document.getElementById("sinput");
	if(text.value == "") {
		text.innerValue = "Bạn chưa nhập từ khóa nào!...";
		text.style.border = "1px solid #f00";
		return false;
	}
	return true;
}
function whenInput() {
	let text = document.getElementById("sinput");
	text.style.border = "none";
}

// for order information
//user choose making an order
let action = document.getElementById("makeorder")
if(action != null) {
	action.onclick = function() {
	    document.getElementById("userAction").style.display = "none";
	    document.getElementById("orderinfo").style.display = "initial";
	}
}
//input
let inputname = document.getElementById("inputname");
let inputaddress = document.getElementById("inputaddress");
//note
let notename = document.getElementById("notename");
let noteaddress = document.getElementById("noteaddress");
//validate
function makeorder() {
    if(inputname.value == "") {
        inputname.style.border = "1px solid #f00";
        notename.innerHTML = "Trường tên là bắt buộc : )";
        notename.style.color = "#f00";
        return false;
    }
    if(inputaddress.value == "") {
        inputaddress.style.border = "1px solid #f00";
        noteaddress.innerHTML = "Chưa nhập địa chỉ : v";
        noteaddress.style.color = "#f00";
        return false;
    }
    return true;
}
//typing name
if(inputname != null) {
	inputname.onfocus  = function() {
	    inputname.style.border = "1px solid #000";
	    notename.style.color = "#000";
	    notename.innerHTML = "Tên hoặc email: ";
	}
}
//typing address
if(inputaddress != null) {
	inputaddress.onfocus = function() {
    inputaddress.style.border = "1px solid #000";
    noteaddress.style.color = "#000";
    noteaddress.innerHTML = "Địa chỉ: ";
	}
}

function comebacksoon() {
	alert("Tính năng đang được phát triển.");
}