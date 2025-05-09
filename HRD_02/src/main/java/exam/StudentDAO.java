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
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "system";
		String pw = "1234";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("DB 접속 완료!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getConn() Exception!!!");
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
			System.out.println("DB 접속 해제!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dbClose() Exception!!!");
		}
	}

	public List<StudentDTO> selectSub1() {
		conn = getConn();
		String sql = "select * from tbl_student_202210 order by stuid asc";
		List<StudentDTO> list = new ArrayList<StudentDTO>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setStuid(rs.getString("stuid"));
				dto.setSname(rs.getString("sname"));

				String jumin = rs.getString("jumin");
				jumin = jumin.substring(0, 6) + "-" + jumin.substring(6);
				dto.setJumin(jumin);

				dto.setDeptname(rs.getString("deptname"));

				String gender = jumin.substring(7, 8);
				if (gender.equals("3"))
					gender = "남자";
				else if (gender.equals("4"))
					gender = "여자";
				dto.setGender(gender);

				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));

				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectSub1() Exception!!!");
		} finally {
			dbClose();
		}

		return list;
	}

	public List<ScoreDTO> selectSub3() {
		conn = getConn();
		String sql = "SELECT student.stuid, student.sname, subject.subname, subject.subcode, subject.proname, score.midscore, score.finalscore, score.attend, score.report, score.etc ";
		sql += "FROM TBL_STUDENT_202210 student, TBL_SCORE_202210 score, TBL_SUBJECT_202210 subject ";
		sql += "where  student.stuid = score.stuid and subject.subcode = score.subcode ";
		sql += "order by student.sname asc";
		List<ScoreDTO> list = new ArrayList<ScoreDTO>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ScoreDTO dto = new ScoreDTO();
				dto.setStuid(rs.getString(1));
				dto.setSname(rs.getString(2));
				dto.setSubname(rs.getString(3));
				dto.setSubcode(rs.getString(4));
				dto.setProname(rs.getString(5));

				int midScore = rs.getInt(6);
				int finalScore = rs.getInt(7);
				int attend = rs.getInt(8);
				int report = rs.getInt(9);
				int etc = rs.getInt(10);

				dto.setMidscore(midScore);
				dto.setFinalscore(finalScore);
				dto.setAttend(attend);
				dto.setReport(report);
				dto.setEtc(etc);

				int avg = (midScore + finalScore + attend + report + etc) / 5;
				dto.setAvg(avg);

				double total = midScore * 0.3 + finalScore * 0.3 + attend * 0.2 + report * 0.1 + etc * 0.1;
				dto.setTotal(total);

				String grade = "";
				if (total >= 95)
					grade = "A+";
				else if (total >= 90)
					grade = "A";
				else if (total >= 85)
					grade = "B+";
				else if (total >= 80)
					grade = "B";
				else if (total >= 75)
					grade = "C+";
				else if (total >= 70)
					grade = "C";
				else if (total >= 65)
					grade = "D+";
				else if (total >= 60)
					grade = "D";
				else
					grade = "F";
				dto.setGrade(grade);

				list.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectSub3() Exception!!!");

		} finally {
			dbClose();
		}
		return list;
	}

}