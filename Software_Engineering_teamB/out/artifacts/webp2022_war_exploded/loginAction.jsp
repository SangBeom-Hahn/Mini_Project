<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="user.UserDAO" %> <!--우리가 만든 클래스를 그래도 가져오기-->
<%@ page import="java.io.PrintWriter" %> <!--자바에서 PrintWriter로 자바스크립트를 쓰기위한 것-->
<% request.setCharacterEncoding("UTF-8"); %>
<%--
  Created by IntelliJ IDEA.
  User: hsb99
  Date: 2022-05-14
  Time: 오후 5:59
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean id="user" class = "user.User" scope="page"/>
<jsp:setProperty name="user" property="userID"/> <!--로그인 창에서 입력한 거 받아서 user 객체에 넣음-->
<jsp:setProperty name="user" property="userPassword"/>
<!DOCTYPE html>
<html class="wide wow-animation" lang="en">
<head>
    <title>KGU reservation</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


</head>
<body>
<%
    UserDAO userDAO = new UserDAO();
    int result = userDAO.login(user.getUserID(), user.getUserPassword()); //그래서 user 객체의 필드 값이 들어감

    if(result == 1){
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("location.href = 'Lectures.jsp'");
        script.println("</script>");
    }
    else if(result == 0){
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('비밀번호가 틀립니다');");
        script.println("history.back()");
        script.println("</script>");
    }
    else if(result == -1){
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('존재하지 않는 아이디입니다.');");
        script.println("history.back()");
        script.println("</script>");
    }
    else if(result == -2){
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('데이터베이스 오류가 발생.');");
        script.println("history.back()");
        script.println("</script>");
    }
%>
</body>
</html>

