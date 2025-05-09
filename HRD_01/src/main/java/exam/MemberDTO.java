package exam;

//여러개의 변수를 하나로 묶는다-> DTO(VO) CLASS
public class MemberDTO {
	//1. 멤버변수 선언: 정보은닉
	private int custno;
	private String custname, phone, address, joindate, grade, city;
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
	
	//2. 멤버변수 접근: Getters & Setters Methods -> 자동완성
	//source > generate Getters and Setters > select all > generate 
	
}//class
