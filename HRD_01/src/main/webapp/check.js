// 회원정보 유효성 검사
const fnSub1FormCheck = () => {
	const custname = document.sub1Form.custname.value;
	const phone = document.sub1Form.phone.value;
	const address = document.sub1Form.address.value;
	const joindate = document.sub1Form.joindate.value;
	const grade = document.sub1Form.grade.value;
	const city = document.sub1Form.city.value;

	if (custname === "" || custname.trim().length === 0) {
		alert("회원성명이 입력되지 않았습니다.");
		document.sub1Form.custname.focus();
		return false
	} else if (phone === "" || phone.trim().length === 0) {
		alert("회원전화가 입력되지 않았습니다.");
		document.sub1Form.phone.focus();
		return false
	} else if (address === "" || address.trim().length === 0) {
		alert("회원주소가 입력되지 않았습니다.");
		document.sub1Form.address.focus();
		return false
	} else if (joindate === "" || joindate.trim().length === 0) {
		alert("가입일자가 입력되지 않았습니다.");
		document.sub1Form.joindate.focus();
		return false
	} else if (grade === "" || grade.trim().length === 0) {
		alert("고객등급이 입력되지 않았습니다.");
		document.sub1Form.grade.focus();
		return false
	} else if (city === "" || city.trim().length === 0) {
		alert("도시코드 입력되지 않았습니다.");
		document.sub1Form.city.focus();
		return false
	} else {
		alert("회원등록이 완료 되었습니다.")
		document.sub1Form.submit();
	}
}