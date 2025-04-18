package exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private Connection conn; // 연결객체
	private PreparedStatement ps; // 전송객체
	private ResultSet rs; // 결과객체

	// DB 접속
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "system";
		String password = "1234";

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB 접속 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getConn() Exception");
		}

		return conn;
	}

	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			System.out.println("DB 접속 해제");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dbClose() Exception");
		}
	}

	public int getCustno() {
		conn = getConn();
		String sql = "SELECT MAX(CUSTNO) FROM MEMBER_TBL_02";
		int custno = 0;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next())
				custno = rs.getInt(1) + 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getCustno() Exception");
		} finally {
			dbClose();
		}

		return custno;
	}

	public void insertSub1(MemberDTO dto) {
		conn = getConn();
		String sql = "INSERT INTO MEMBER_TBL_02 VALUES(?, ?, ?, ?, ?, ?, ?)";

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
			System.out.println("insertSub1() Exception");
		} finally {
			dbClose();
		}
	}

	public List<MemberDTO> selectSub2() {
		conn = getConn();
		String sql = "SELECT * FROM MEMBER_TBL_02 ORDER BY CUSTNO ASC";
		List<MemberDTO> list = new ArrayList<MemberDTO>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setCustno(rs.getInt(1));
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

				dto.setGrade(grade);

				dto.setCity(rs.getString(7));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectSub2 Exception");
		} finally {
			dbClose();
		}

		return list;
	}

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
			rs= ps.executeQuery();
			
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
			System.out.println("selectSub3() Exception");
		} finally {
			dbClose();
		}
		return list;

	}
}
