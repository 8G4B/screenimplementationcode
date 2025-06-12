package exam;

public class TeacherDTO {
	public String getTeacherCode() {
		return teacherCode;
	}
	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}
	public Integer getTeacingPrice() {
		return teacingPrice;
	}
	public void setTeacingPrice(Integer teacingPrice) {
		this.teacingPrice = teacingPrice;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getLincesDate() {
		return lincesDate;
	}
	public void setLincesDate(String lincesDate) {
		this.lincesDate = lincesDate;
	}
	public Integer teacingPrice;
	public String  teacherCode,teacherName,className,lincesDate;
}
