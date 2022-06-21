<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%--
  Created by IntelliJ IDEA.
  User: hsb99
  Date: 2022-05-14
  Time: 오후 5:59
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean id="user" class = "user.User" scope="page"/>
<jsp:setProperty name="user" property="userID2"/>
<jsp:setProperty name="user" property="userPassword2"/>
<jsp:setProperty name="user" property="userEmail"/>
<jsp:setProperty name="user" property="userName2"/>
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

  if(user.getUserID2() == null || user.getUserEmail() == null || user.getUserPassword2() == null || user.getUserName2() == null){
    PrintWriter script = response.getWriter();
    script.println("<script>");
    script.println("alert('입력이 안 된 사항이 있습니다.');");
    script.println("history.back()");
    script.println("</script>");
  }
  else{
    UserDAO userDAO = new UserDAO();

    int result = userDAO.join(user); //내가 입력한 ID가 들옴 여기까지 확인됨

    if(result == -1){
      PrintWriter script = response.getWriter();
      script.println("<script>");
      script.println("alert('이미 존재하는 아이디입니다.');");
      script.println("history.back()");
      script.println("</script>");
    }
    else {
      PrintWriter script = response.getWriter();
      script.println("<script>");
      script.println("location.href = 'Lectures.jsp'");
      script.println("</script>");
    }
  }



%>
</body>
</html>

