package user;

public class User {
    private String userID;
    private String userPassword;
    private String userName;

    private String userID2;
    private String userPassword2;
    private String userName2;
    private String userEmail;

    private String lecture;
    private String sitNum;

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getSitNum() {
        return sitNum;
    }

    public void setSitNum(String sitNum) {
        this.sitNum = sitNum;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    private String day;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID2() {
        return userID2;
    }

    public void setUserID2(String userID2) {
        this.userID2 = userID2;
    }

    public String getUserPassword2() {
        return userPassword2;
    }

    public void setUserPassword2(String userPassword2) {
        this.userPassword2 = userPassword2;
    }

    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
