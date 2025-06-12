package exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {

	private Connection conn;
	private ResultSet rs;
	private Statement st;
	
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
            if(conn != null) conn.close();
        } catch(Exception e) {
            System.err.println("dbClose 에러 발생!");
            e.printStackTrace();
        }
    }
    
    public List<TeacherDTO> sub1Action() {
    	getConn();
    	String sql = "SELECT * FROM TBL_TEACHER_202201";
		List<TeacherDTO> list = new ArrayList<TeacherDTO>();
    	try {
    		st = conn.createStatement();
    		rs = st.executeQuery(sql);
    		while(rs.next()) {
    			TeacherDTO obj = new TeacherDTO();
    			obj.setTeacherCode(rs.getString("TEACHER_CODE"));
    			obj.setTeacherName(rs.getString("TEACHER_NAME"));
    			obj.setClassName(rs.getString("CLASS_NAME"));
    			obj.setLincesDate(rs.getString("TEACH_RESIST_DATE"));
    			obj.setTeacingPrice(rs.getInt("CLASS_PRICE"));
    			list.add(obj);
    		}
    	} catch(Exception e) {
    		System.err.println("sub1Action 에러 발생!");
    		e.printStackTrace();
    	}
    	dbClose();
    	return list;
    }
}