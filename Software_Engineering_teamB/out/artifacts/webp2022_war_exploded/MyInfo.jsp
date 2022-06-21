<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="user.UserDAO" %> <!--우리가 만든 클래스를 그래도 가져오기-->
<%@ page import="java.io.PrintWriter" %> <!--자바에서 PrintWriter로 자바스크립트를 쓰기위한 것-->
<% request.setCharacterEncoding("UTF-8"); %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-05-17
  Time: 오후 3:26
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="user" class = "user.User" scope="page"/>

<!DOCTYPE html>
<html class="wide wow-animation" lang="en">
<head>
    <title>KGU reservation</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="icon" href="images/KGU.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Poppins:300,300i,400,500,600,700,800,900,900i%7CPT+Serif:400,700">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/fonts.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
        #dd {
            text-align: center;
            margin: 0 auto;
        }
        #info {
            margin-top: 40px;
            min-height: 200px;
            display: inline-block;
        }
        table {border-collapse: collapse; margin-top: 30px; width: 100px;}
        table thead tr { background-color: #333; color:#fff; }
        table > thead > tr > th { padding: 8px; }
        table > tbody > tr > td { padding: 4px; }
        #un {
            font-size: 16px;
            text-decoration: underline;
            margin-right: 10px;
            padding-left: 200px;
        }
        a:hover {color: #41A451; text-decoration: none;}
    </style>
</head>
<body>
<%
    UserDAO userDAO = new UserDAO();
    String userName = userDAO.info();
    String lecture = userDAO.info2();
    String sitNum = userDAO.info3();
    String day = userDAO.info4();
%>
<div class="preloader">
    <div class="preloader-body">
        <div class="cssload-container">
            <div class="cssload-speeding-wheel"></div>
        </div>
        <p>Loading...</p>
    </div>
</div>
<div class="page">
    <header class="section page-header">
        <div class="rd-navbar-wrap">
            <nav class="rd-navbar rd-navbar-classic" data-layout="rd-navbar-fixed" data-sm-layout="rd-navbar-fixed" data-md-layout="rd-navbar-fixed" data-md-device-layout="rd-navbar-fixed" data-lg-layout="rd-navbar-static" data-lg-device-layout="rd-navbar-static" data-xl-layout="rd-navbar-static" data-xl-device-layout="rd-navbar-static" data-lg-stick-up-offset="46px" data-xl-stick-up-offset="46px" data-xxl-stick-up-offset="46px" data-lg-stick-up="true" data-xl-stick-up="true" data-xxl-stick-up="true">
                <div class="rd-navbar-main-outer">
                    <div class="rd-navbar-main">
                        <div class="rd-navbar-panel">
                            <div class="rd-navbar-brand"></div>
                        </div>
                        <div class="rd-navbar-panel">
                            <div class="rd-navbar-brand" style="width: 150px;"></div>
                        </div>
                        <div class="rd-navbar-main-element">
                            <div class="rd-navbar-nav-wrap">
                                <ul class="rd-navbar-nav">
                                    <li class="rd-nav-item"><a class="rd-nav-link" href="Lectures.jsp">Lectures</a></li>
                                    <li class="rd-nav-item"><a class="rd-nav-link" href="reservation.jsp">Reservation</a></li>
                                </ul>
                                <button class="button button-white button-sm" onclick="location.href='login.jsp'">LOGOUT</button>
                            </div>
                        </div>
                        <div>
                            <a class="rd-nav-link" id="un" href="MyInfo.html"><%=userName%></a>
                            <button class="button button-white button-sm" onclick="location.href='login.jsp'">LOGOUT</button>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </header>
    <section class="section section-lg section-main-bunner section-main-bunner-filter text-center">
        <div class="main-bunner-img" style="background-image: url(./images/main.jpg); background-size: cover;"></div>
        <div class="main-bunner-inner">
            <div class="container">
                <div class="box-default">
                    <h1 class="box-default-title">KGU</h1>
                    <h2 class="box-default-text">Reservation Information</h2>
                    <div class="box-default-decor"></div>
                </div>
            </div>
        </div>
    </section>
    <div class="bg-gray-1">
        <section class="section-transform-top">
            <div class="container">
                <div class="box-booking">
                    <div class="booking-form">
                        <div id="dd">
                            <p style="font-size: 30px; font-weight: bold;">< 예약 정보 ></p>

                            <%--                        임시 테이블 구성!! 날짜/강의/좌석/예약취소버튼 순서로 들어가도록? 할 것 같습니다. --%>
                            <%--                        지금 테이블 너비 설정이 적용이 잘 안 되는데 감안해주세요!--%>
                            <div id="info">
                                <table id="info_table">
                                    <thead>
                                    <tr>
                                        <th>날짜</th>
                                        <th>강의</th>
                                        <th>좌석</th>
                                        <th>취소</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td><%=day%></td>
                                        <td><%=lecture%></td>
                                        <td><%=sitNum%></td>
                                        <td><button type="button" class="btn btn-primary form-control">예약 취소</button></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><button type="button" class="btn btn-primary form-control">예약 취소</button></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
</div>
<script src="js/core.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>