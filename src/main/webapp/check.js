const Sub1FormCheck = () => {
	const form = document.sub1Form;
	const custname = form.custname.value;
	const phone = form.phone.value;
	const adress = form.adress.value;
	const joindate = form.joindate.value;
	const grade = form.grade.value;
	const city = form.city.value;

	if (custname === "" || custname.trim().length === 0) {
		alert("회원 이름이 입력되지 않았습니다.");
		form.custname.focus();
		return false;
	} else if (phone === "" || phone.trim().length === 0) {
		alert("회원 전화번호가 입력되지 않았습니다.");
		form.phone.focus();
		return false;
	} else if (adress === "" || adress.trim().length === 0) {
		alert("회원 주소가 입력되지 않았습니다.");
		form.adress.focus();
		return false;
	} else if (joindate === "" || joindate.trim().length === 0) {
		alert("가입일이 입력되지 않았습니다.");
		form.joindate.focus();
		return false;
	} else if (grade === "" || grade.trim().length === 0) {
		alert("회원 등급이 선택되지 않았습니다.");
		form.grade.focus();
		return false;
	} else if (city === "" || city.trim().length === 0) {
		alert("도시 정보가 선택되지 않았습니다.");
		form.city.focus();
		return false;
	} else return true;
};
