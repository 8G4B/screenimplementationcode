function fnFormChecking() {
	var form = document.Sub2Form;
	if(form.수강월.value === null || form.수강월.value === "") {
		alert("수강월이 입력되지 않았습니다");
		form.수강월.focus();
		return false;
	}
	if(form.회원명.value === null || form.회원명.value === "") {
		alert("회원명이 입력되지 않았습니다");
		form.회원명.focus();
		return false;
	}
	if(form.강의장소.value === null || form.강의장소.value === ""){
		alert("강의장소가 선택되지 않았습니다");
		form.강의장소.focus();
		return false;
	}
	if(form.강의명.value === null || form.강의명.value === ""){
		alert("강의명이 선택되지 않았습니다");
		form.강의명.focus();
		return false;
	}
	var classSelect = form.강의명;
	if(classSelect.selectedIndex > 0) {
	    var selectedOption = classSelect.options[classSelect.selectedIndex];
	    var teacherCode = selectedOption.getAttribute('teacher-code');
	    form.강사코드.value = teacherCode;
	}

	var priceValue = form.수강료.value.replace(/,/g, '');
	form.수강료.value = priceValue;
	form.submit();
}

function updateMemberCode() {
    var select = document.Sub2Form.회원명;
    var memberCodeField = document.Sub2Form.회원번호;
    if(select.selectedIndex > 0) {
        var selectedOption = select.options[select.selectedIndex];
        memberCodeField.value = selectedOption.getAttribute('data-code');
        updatePrice();
    } else {
        memberCodeField.value = "";
        updatePrice();
    }
}

function updatePrice() {
    var classSelect = document.Sub2Form.강의명;
    var memberCode = document.Sub2Form.회원번호.value;
    var priceField = document.Sub2Form.수강료;
    if(classSelect.selectedIndex > 0) {
        var selectedOption = classSelect.options[classSelect.selectedIndex];
        var originalPrice = parseInt(selectedOption.getAttribute('data-price'));
        var finalPrice = originalPrice;
        if(memberCode && parseInt(memberCode) >= 20000) {
            finalPrice = originalPrice * 0.5;
        }
        priceField.value = finalPrice.toLocaleString();
    } else {
        priceField.value = "";
    }
}

function fnClear() {
	alert("정보를 지우고 다시 입력합니다!");
	document.Sub2Form.reset();
}