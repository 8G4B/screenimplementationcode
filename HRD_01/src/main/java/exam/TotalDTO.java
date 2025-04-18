package exam;

public class TotalDTO {
  int custno, total;
  String custname, grade;

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

  public String getGrade() {
    return grade;
  }
  
  public void setGrade(String grade) {
    this.grade = grade;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
