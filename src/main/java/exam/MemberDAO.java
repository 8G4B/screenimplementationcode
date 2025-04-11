package exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    //db접속해제
    public void dbClose() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();    
            if (conn != null) conn.close();
            System.out.println("db접속해제");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("dbClose() exception!!!");
        }
    }//dbclose()
    
    //회원번호 자동발생
    public int getCustno() {
        conn = getConn();
        String sql = "select max(custno) from member_tbl_02";
        int custno = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                custno = rs.getInt(1) + 1;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getCustno() Exception!!!");
        } finally {
            dbClose();
        }
        return custno;
    }//getCustno()
    
    public void insertSub1(MemberDTO dto) {
        conn = getConn();
        String sql = "insert into member_tbl_02_values(?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dto.getCustno());
            ps.setString(2, dto.getCustname());
            ps.setString(3, dto.getPhone());
            ps.setString(4, dto.getAddress());
            ps.setString(5, dto.getJoindate());
            ps.setString(6, dto.getGrade());
            ps.setString(7, dto.getCity());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insertSub1() Exception!!!");
        } finally {
            dbClose();
        }
    }//insertSub1()
    
}// class