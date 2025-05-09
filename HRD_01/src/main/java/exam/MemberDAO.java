package exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	// 멤버변수 선언 : 정보은닉 ▶ 접근제어자를 private 선언
	private Connection conn; // 연결객체
	private PreparedStatement ps; // 전송개체
	private ResultSet rs; // 결과객체 : select

	// DB접속
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "system";
		String password = "1234";

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB 접속 완료!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getConn() Exception!!!");
		}
		return conn;
	}// getConn()
		// db접속해제

	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			System.out.println("db접속해제");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dbClose() exception!!!");
		}
	}// dbclose()

	// 회원번호 자동발생
	public int getCustno() {
		conn = getConn();
		String sql = "select max(custno) from member_tbl_02";
		int custno = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				custno = rs.getInt(1) + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getCustno() Exception!!!");
		} finally {
			dbClose();
		}
		return custno;
	}// getCustno()

	// 회원등록처리
	public void insertSub1(MemberDTO dto) {
		conn = getConn();
		String sql = "insert into member_tbl_02 values(?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getCustno());
			ps.setString(2, dto.getCustname());
			ps.setString(3, dto.getPhone());
			ps.setString(4, dto.getAddress());
			ps.setString(5, dto.getJoindate());
			ps.setString(6, dto.getGrade());
			ps.setString(7, dto.getCity());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertSub1() Exception!!!");
		} finally {
			dbClose();
		}
	}// insertSub1()

	// 전체회원목록조회
	public List<MemberDTO> selectSub2() {
		conn = getConn();
		String sql = "select * from member_tbl_02 order by custno asc";
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setCustno(rs.getInt(1)); // dto.setCustno(rs.getInt("custno"));
				dto.setCustname(rs.getString(2));
				dto.setPhone(rs.getString(3));
				dto.setAddress(rs.getString(4));
				dto.setJoindate(rs.getString(5).substring(0, 10));
				String grade = rs.getString(6);
				if (grade.equalsIgnoreCase("A")) {
					grade = "VIP";
				} else if (grade.equalsIgnoreCase("B")) {
					grade = "일반";
				} else if (grade.equalsIgnoreCase("C")) {
					grade = "직원";
				}
				dto.setGrade(rs.getString(6));
				
				dto.setCity(rs.getString(7));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectSub2() Exception!!!");
		} finally {
			dbClose();
		}
		return list;
	}// selectSub2()
	
	//회원매출조회
	 public List<TotalDTO> selectSub3() {
		 conn = getConn();
		 String sql = "select member.custno, member.custname, ";
		 sql += "member.grade, sum(money.price) as total ";
		 sql += "from member_tbl_02 member, money_tbl_02 money ";
		 sql += "where member.custno = money.custno ";
		 sql += "group by member.custno, member.custname, member.grade ";
		 sql += "order by total desc";
		 List<TotalDTO> list = new ArrayList<TotalDTO>();
		 try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				TotalDTO dto = new TotalDTO();
				dto.setCustno(rs.getInt(1));
				dto.setCustname(rs.getString(2));
				
				String grade = rs.getString(3);
				if (grade.equalsIgnoreCase("A")) {
					grade = "VIP";
				} else if (grade.equalsIgnoreCase("B")) {
					grade = "일반";
				} else if (grade.equalsIgnoreCase("C")) {
					grade = "직원";
				}
				dto.setGrade(grade);
				
				dto.setTotal(rs.getInt(4));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectSub3() Exception!!!");
		}finally {
			dbClose();
		}
		 return list;
		 
	 }//selectSub3
	

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}// class
