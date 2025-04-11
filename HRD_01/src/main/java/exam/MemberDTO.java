package exam;

// 여러개의 변수를 하나로 묶는다 -> DTO
public class MemberDTO {
	// 멤버 변수 선언: 정보은닉, DB 컬럼 ID와 일치
	private int custno;
	private String custname, phone, address, joindate, grade, city;

	// 멤버 변수에 접근: Getters & Setters 메소드 -> 자동완성
	// 우클 -> 소스 -> 게터 앤드 세터 -> 셀렉트 올 -> 제네레이트
	public int getCustno() {
		return custno;
	}

	public void setCustno(int custno) {
		this.custno = custno;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}