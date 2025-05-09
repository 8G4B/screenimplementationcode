//회원정보 입력화면 유효성 검사
function fnSub1FormCheck() {
	var custname = document.sub1Form.custname.value;
	var phone = document.sub1Form.phone.value;
	var address = document.sub1Form.address.value;
	var joindate = document.sub1Form.joindate.value;
	var grade = document.sub1Form.grade.value;
	var city = document.sub1Form.city.value;

	if (custname == "" || custname.trim().length == 0) {
		alert("회원이름이 입력되지 않았습니다!");
		document.sub1Form.custname.focus();
		return false;
	}
	else if (phone == "" || phone.trim().length == 0) {
		alert("회원전화가 입력되지 않았습니다!");
		document.sub1Form.phone.focus();
		return false;
	}
	else if (address == "" || address.trim().length == 0) {
		alert("회원주소가 입력되지 않았습니다!");
		document.sub1Form.address.focus();
		return false;
	}
	else if (joindate == "" || joindate.trim().length == 0) {
			alert("가입일자가 입력되지 않았습니다!");
			document.sub1Form.joindate.focus();
			return false;
		}
	else if (grade == "" || grade.trim().length == 0) {
		alert("고객등급이 입력되지 않았습니다!");
		document.sub1Form.grade.focus();
		return false;
	}
	else if (city == "" || city.trim().length == 0) {
		alert("도시코드가 입력되지 않았습니다!");
		document.sub1Form.city.focus();
		return false;
	}
	else{
		alert("회원등록이 완료 되었습니다!");
		document.sub1Form.submit();
	}
}//fnSub1FormCheck()