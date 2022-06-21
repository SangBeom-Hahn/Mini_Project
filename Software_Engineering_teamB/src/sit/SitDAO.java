package sit;
import user.UserDAO;

import java.sql.*;

public class SitDAO {
    public SitDAO(){}
    private static class Singleton{
        private static final SitDAO instance = new SitDAO();
    }
    public static SitDAO getInstance() {
        return SitDAO.Singleton.instance;
    }

    private Connection conn = null;
    //동빈나    PreparedStatement pstmt = null;
    Statement statement = null;
    ResultSet rs = null;

    public Connection sqlLogin()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/BBS";
            String dbID = "root";
            String dbPassword = "root";

            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn; //DB 사용을 요구했던 메소드에게 로그인 정보를 돌려줌.
    }

    public String su(String sitID) throws SQLException {
        conn = sqlLogin();
        statement = conn.createStatement();
        rs = statement.executeQuery("select * from sit");

        while(rs.next()){
            if(rs.getString(1).equals(sitID))
                return rs.getString(2);
        }
        return "noop";
    }

    public void minus(String sitID) throws SQLException {
        conn = sqlLogin();
        String SQL = "UPDATE sit SET sitCount = ? WHERE sitID = ?";
        String sitCount = null;

        PreparedStatement pstmt;
        statement = conn.createStatement();
        pstmt = conn.prepareStatement(SQL);
        rs = statement.executeQuery("select * from sit");
        while(rs.next()){
            if(rs.getString(1).equals(sitID))
                sitCount = rs.getString(2);
        }

        pstmt.setString(1, sitCount);
        pstmt.setString(2, sitID);
        pstmt.executeUpdate();
    }
}
