
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
		String url = "jdbc:oracle:thin:@127.0.0.1:xe";
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
		String sql = "select * TBL_STUDENT_202210 order by stuid asc";
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
				
				dto.setPhone(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectSub1() exception");
		} finally {
			dbClose();
		}
		
		return list;
	}
	
}
