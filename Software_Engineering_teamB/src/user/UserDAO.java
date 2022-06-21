package user;

import java.sql.*;

public class UserDAO {
//    Connection conn;
//    PreparedStatement pstmt;
//    ResultSet rs;

//    public  UserDAO() throws SQLException {
//        try{
//            String dbURL = "jdbc:mysql://localhost:3306/BBS";
//            String dbID = "root";
//            String dbPassword = "root";
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
//            System.out.println("hi");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public int login(String userID, String userPassword) throws SQLException {
//        String SQL = "SELECT userPassword FROM user WHERE userID = ?";
//        try{
//            pstmt = conn.prepareStatement(SQL);
//            pstmt.setString(1, userID);
//            rs = pstmt.executeQuery();
//
//            if(rs.next()){
//                if(rs.getString(1).equals(userPassword))
//                    return 1; //로그인 성공
//                else
//                    return 0; // 비밀번호 불일치
//            }
//            return -1; //아이디 없음
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return -2;
//    }

    public UserDAO(){}
    private static class Singleton{
        private static final UserDAO instance = new UserDAO();
    }
    public static UserDAO getInstance() {
        return Singleton.instance;
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

    public int login(String userID, String userPassword) throws SQLException {
        conn = sqlLogin();
//        String SQL = "SELECT userPassword FROM user WHERE userID = ?";
//
//        try{
//            pstmt = conn.prepareStatement(SQL);
//            pstmt.setString(1, userID);
//            rs = pstmt.executeQuery();
//
//            if(rs.next()){
//                if(rs.getString(1).equals(userPassword))
//                    return 1; //로그인 성공
//                else
//                    return 0; // 비밀번호 불일치
//            }
//            return -1; //아이디 없음
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return -2;

        statement = conn.createStatement();
        rs = statement.executeQuery("select * from user");

//        rs.next();
//        String name = rs.getString("userID");
//        System.out.println(name);
//
//        rs.close();
//        statement.close();
//        conn.close();

        while (rs.next()){
            if(rs.getString(1).equals(userID)) {
                if(rs.getString(2).equals(userPassword))
                    return 1;
                else
                    return 0;
            }
            else{ //아이디가 다르면 다음 꺼 봐
                continue;
            }
        }
        return -1;
    }

    public int join(User user) throws SQLException {
        PreparedStatement pstmt;
        conn = sqlLogin();

        String SQL = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?)";

        pstmt = conn.prepareStatement(SQL);
        try{
            System.out.println("hi");
            pstmt.setString(1, user.getUserID2());
            pstmt.setString(2, user.getUserPassword2());
            pstmt.setString(3, user.getUserName2());
            pstmt.setString(4, user.getUserEmail());
            pstmt.setString(5, "1583");
            pstmt.setString(6, "22");
            pstmt.setString(7, "2022-05-23");
            return pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    public static int sig2 = 0;
    public String info() throws SQLException {
        conn = sqlLogin();
        statement = conn.createStatement();
        rs = statement.executeQuery("select * from user");

        if(sig2 == 0){
            while(rs.next()){
                if(rs.getString(1).equals("1234"))
                    sig2 = 1;
                return rs.getString(3);
            }
        }
        else{
            while(rs.next()){
                if(rs.getString(1).equals("2018"))
                    return rs.getString(3);
            }
        }
        return "hi";
    }
    public String info2() throws SQLException {
        conn = sqlLogin();
        statement = conn.createStatement();
        rs = statement.executeQuery("select * from user");

        while(rs.next()){
            if(rs.getString(1).equals("2018"))
                return rs.getString(5);
        }

        return rs.getString(3);
    }
    public String info3() throws SQLException {
        conn = sqlLogin();
        statement = conn.createStatement();
        rs = statement.executeQuery("select * from user");

        while(rs.next()){
            if(rs.getString(1).equals("2018"))
                return rs.getString(6);
        }
        return "hi";
    }
    public String info4() throws SQLException {
        conn = sqlLogin();
        statement = conn.createStatement();
        rs = statement.executeQuery("select * from user");

        while(rs.next()){
            if(rs.getString(1).equals("2018"))
                return rs.getString(7);
        }
        return "hi";
    }
    public static int sig = 0;
    public String infoLecture() throws SQLException {
        conn = sqlLogin();
        statement = conn.createStatement();
        rs = statement.executeQuery("select * from user");
        if(sig == 0){
            while(rs.next()){
                if(rs.getString(1).equals("1234"))
                    sig = 1;
                    return rs.getString(3);
            }
        }
        else{
            while(rs.next()){
                if(rs.getString(1).equals("2018"))
                    return rs.getString(3);
            }
        }
        return "hi";
    }

}
