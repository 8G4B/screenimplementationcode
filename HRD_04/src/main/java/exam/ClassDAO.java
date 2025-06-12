package exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClassDAO {
	
	private Connection conn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
    public Connection getConn() {
    	try{
    		 Class.forName("oracle.jdbc.OracleDriver");
    		 Connection con = DriverManager.getConnection
    		 ("jdbc:oracle:thin:@//localhost:1521/xe", "system", "123456");
    		 if(con != null) {
    			 System.out.print("Database Connect : [ "+" <b>success</b> ] <br>");
    		 }
    		 else {
    			 System.out.print("Database Connect : [ "+" <b>fail</b> ] <br>");
    		 }
    		 conn = con;
    		 } catch(Exception e){
    	    	System.err.println("getConn 에러 발생!");
    			 e.printStackTrace();
    	}
		return conn;
    }
    
    public void dbClose() {
        try {
            if(rs != null) rs.close();
            if(st != null) st.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        } catch(Exception e) {
            System.err.println("dbClose 에러 발생!");
            e.printStackTrace();
        }
    }
    
    public void sub2Action(ClassDTO dto) {
    	getConn();
		String sql = "INSERT INTO TBL_CLASS_202201 VALUES(?, ?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.get수강월());
			ps.setString(2, dto.get회원번호());
			ps.setString(3, dto.get강의장소());
			ps.setString(4, dto.get수강료());
			ps.setString(5, dto.get강사코드());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sub2Action() Exception");
		} finally {
			dbClose();
		}
    }
}
    
