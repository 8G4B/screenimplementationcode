package exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "1234";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getConn() Exception");
		}
		
		return conn;
	}
	
	public void dbClose() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
			System.out.println("DB 접속 해제");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dbClose e");
		}
	}
	
	public List<StudentDTO> selectSub1() {
		conn = getConn();
		String sql = "select * from TBL_STUDENT_202210 order by stuid asc";;
		List<StudentDTO> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setStuid(rs.getString(1));
				dto.setSname(rs.getString(2));
				
				String jumin = rs.getString(4);
				jumin = jumin.substring(0, 6) + "-" + jumin.substring(6);
				dto.setJumin(jumin);
				
				dto.setDeptname(rs.getString(3));
				
				
				String gender = jumin.substring(7, 8);
				if(gender.equals("3")) gender = "남자";
				if (gender.equals("4")) gender = "여자";
				dto.setGender(gender);
				dto.setPhone(rs.getString(5));
				dto.setEmail(rs.getString(6));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectSub1() exception");
		} finally {
			dbClose();
		}
		
		return list;
	}
	
	public List<ScoreDTO> selectSub3() {
		conn = getConn();
		String sql = "SELECT student.stuid, student.sname, \r\n"
				+ "	subject.subname, subject.subcode, subject.proname, \r\n"
				+ "	score.midscore, score.finalscore, score.attend, score.report, score.etc \r\n"
				+ "FROM TBL_STUDENT_202210 student, TBL_SCORE_202210 score, TBL_SUBJECT_202210 subject\r\n"
				+ "WHERE student.stuid = score.stuid AND score.subcode = subject.subcode\r\n"
				+ "ORDER BY student.sname ASC";
		List<ScoreDTO> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ScoreDTO dto = new ScoreDTO();
				dto.setStuid(rs.getString(1));
				dto.setSname(rs.getString(2));
				dto.setSubname(rs.getString(3));
				dto.setSubcode(rs.getString(4));
				dto.setProname(rs.getString(5));
				dto.setMidscore(rs.getInt(6));
				dto.setFinalscore(rs.getInt(7));
				dto.setAttend(rs.getInt(8));
				dto.setReport(rs.getInt(9));
				dto.setEtc(rs.getInt(10));
				int avg = (rs.getInt(6) + rs.getInt(7) + rs.getInt(8) + rs.getInt(9) + rs.getInt(10)) / 5;
				dto.setAvg(avg);
				double total = rs.getInt(6) * 0.3 + rs.getInt(7) * 0.3 + rs.getInt(8) * 0.2 +  rs.getInt(9) * 0.1 + rs.getInt(10) * 0.1;
				dto.setTotal(total);
				String grade;
				if (total >= 95) grade = "A+";
				else if (total >= 90) grade = "A";
				else if (total >= 85) grade = "B+";
				else if (total >= 80) grade = "B";
				else if (total >= 75) grade = "C+";
				else if (total >= 70) grade = "C";
				else if (total >= 65) grade = "D+";
				else if (total >= 60) grade = "D";
				else grade = "F";
				dto.setGrade(grade);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectSub3() exception");
		} finally {
			dbClose();
		}		
		return list;
	}
	
	public List<ScoreDTO> selectSub4(String searchSubcode) {
		conn = getConn();
		String sql = "SELECT student.stuid, student.sname, " +
				"subject.subname, subject.subcode, subject.proname, " +
				"score.midscore, score.finalscore, score.attend, score.report, score.etc " +
				"FROM TBL_STUDENT_202210 student, TBL_SCORE_202210 score, TBL_SUBJECT_202210 subject " +
				"WHERE student.stuid = score.stuid AND score.subcode = subject.subcode AND subject.subcode = ? " +
				"ORDER BY student.sname ASC";
		List<ScoreDTO> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, searchSubcode);
			rs = ps.executeQuery();
			while(rs.next()) {
				ScoreDTO dto = new ScoreDTO();
				dto.setStuid(rs.getString(1));
				dto.setSname(rs.getString(2));
				dto.setSubname(rs.getString(3));
				dto.setSubcode(rs.getString(4));
				dto.setProname(rs.getString(5));
				dto.setMidscore(rs.getInt(6));
				dto.setFinalscore(rs.getInt(7));
				dto.setAttend(rs.getInt(8));
				dto.setReport(rs.getInt(9));
				dto.setEtc(rs.getInt(10));
				int avg = (rs.getInt(6) + rs.getInt(7) + rs.getInt(8) + rs.getInt(9) + rs.getInt(10)) / 5;
				dto.setAvg(avg);
				double total = rs.getInt(6) * 0.3 + rs.getInt(7) * 0.3 + rs.getInt(8) * 0.2 +  rs.getInt(9) * 0.1 + rs.getInt(10) * 0.1;
				dto.setTotal(total);
				String grade;
				if (total >= 95) grade = "A+";
				else if (total >= 90) grade = "A";
				else if (total >= 85) grade = "B+";
				else if (total >= 80) grade = "B";
				else if (total >= 75) grade = "C+";
				else if (total >= 70) grade = "C";
				else if (total >= 65) grade = "D+";
				else if (total >= 60) grade = "D";
				else grade = "F";
				dto.setGrade(grade);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectSub4() exception");
		} finally {
			dbClose();
		}
		return list;
	}
}
