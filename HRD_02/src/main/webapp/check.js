function fnSub4FormCheck(){
	var searchSubCode = document.sub4Form.searchSubcode.value;
	if(fnSub4FormCheck === "" || searchSubCode.trim().length === 0){
		alert("과목코드가 입력되지 않았습니다!");
		document.sub4Form.searchSubcode.focus();
		return false;
	}
	document.sub4Form.submit();	
}