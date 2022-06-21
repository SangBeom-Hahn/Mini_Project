<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sit.SitDAO" %> <!--우리가 만든 클래스를 그래도 가져오기-->
<%@ page import="java.io.PrintWriter" %>
<%@ page import="user.UserDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%--
  Created by IntelliJ IDEA.
  User: hsb99
  Date: 2022-05-13
  Time: 오후 7:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="sit" class = "sit.Sit" scope="page"/>
<jsp:useBean id="user" class = "user.User" scope="page"/>
<jsp:setProperty name="sit" property="sitID"/>
<jsp:setProperty name="sit" property="sitCount"/>
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
        .ie-panel{display: none;background: #212121;padding: 10px 0;box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3);clear: both;text-align:center;position: relative;z-index: 1;}
        html.ie-10 .ie-panel, html.lt-ie-10 .ie-panel {display: block;}
        #rsv { padding-top: 20px; padding-bottom: 80px;}
        #rsv button {
            padding: 15px;
            font-size: 30px;
            width: 300px;
        }
        #search { width: 100%; padding-left: 80px;}
        #lecture-table tbody tr td button {
            padding: 4px 10px;
            font-size: 15px;
            line-height: 1.2;
        }
        .details {
            border-radius: 3px;
            border: 0px;
        }
        .center {
            text-align: center;
            margin: 0 auto;
        }
        #lecture {display: inline-block;}
        #lecture-table {border-collapse: collapse; margin-top: 30px;}
        #lecture-table > thead > tr { background-color: #333; color:#fff; }
        #lecture-table > thead > tr > th { padding: 8px; width: 80px; }
        #lecture-table #th-1 { width: 180px; }
        #lecture-table #th-2 { width: 210px; }
        #lecture-table > tbody > tr > td { padding:4px; }
        .section-transform-top {top: 230px;}
        #un {
            font-size: 16px;
            text-decoration: underline;
            margin-right: 10px;
            padding-left: 200px;
        }
        a:hover {color: #41A451; text-decoration: none;}
        #keyword {border: none; border-bottom: 1px solid rgba(0,0,0,.3)}
    </style>
</head>
<body>
<%
    SitDAO sitDAO = new SitDAO();
    String result1579 = sitDAO.su("1579");
    String result1580 = sitDAO.su("1580");
    String result1581 = sitDAO.su("1581");
    String result1582 = sitDAO.su("1582");
    String result1583 = sitDAO.su("1583");
    String result1585 = sitDAO.su("1585");
    String result1586 = sitDAO.su("1586");
    String result1587 = sitDAO.su("1587");
    String result1588 = sitDAO.su("1588");
    String result1589 = sitDAO.su("1589");
    String result1590 = sitDAO.su("1590");
    String result1591 = sitDAO.su("1591");
    String result1592 = sitDAO.su("1592");
    String result1593 = sitDAO.su("1593");

    UserDAO userDAO = new UserDAO();
    String userName = userDAO.infoLecture();
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
                                    <li class="rd-nav-item active"><a class="rd-nav-link" href="Lectures.jsp">Lectures</a></li>
                                    <li class="rd-nav-item"><a class="rd-nav-link" href="reservation.jsp">Reservation</a></li>
                                </ul>
                                <button class="button button-white button-sm" onclick="location.href='login.jsp'">LOGOUT</button>
                            </div>
                        </div>
                        <div>
                            <a class="rd-nav-link" id="un" href="MyInfo.jsp"><%=userName%></a>
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
                    <h2 class="box-default-text">Lectures</h2>
                    <div class="box-default-decor"></div>
                </div>
            </div>
        </div>
    </section>
    <div class="bg-gray-1">
        <section class="section-transform-top">
            <div class="container">
                <div class="box-booking center">
                    <%--                    검색 부분 어떻게 꾸미면 좋을지 간단하게라도 아이디어 주시면 감사하겠습니다--%>
                    <div id="search">
                        검색 <input type="text" id="keyword">
                    </div>
                    <div id="lecture">
                        <table id="lecture-table">
                            <thead>
                            <tr>
                                <th>과목번호</th>
                                <th style="width: 210px;">과목명</th>
                                <th></th>
                                <th>담당교수</th>
                                <th>교시</th>
                                <th style="width: 230px;">강의실</th>
                                <th>최대인원</th>
                                <th>남은자리</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>1579</td>
                                <td>인공지능</td>
                                <td><button type="button" class="details" onclick="location.href=''">보기</button></td>
                                <td>김인철</td>
                                <td>월678</td>
                                <td>8509 공대세미나실(8동5층)</td>
                                <td>176</td>
                                <td><%=result1579%></td>


                            </tr>
                            <tr>
                                <td>1580</td>
                                <td>인공지능</td>
                                <td><button type="button" class="details" onclick="location.href='http://kutis.kyonggi.ac.kr/webkutis/view/hs/wssu5/wssu511prt_NoSession.jsp?year=2022&hakgi=10&jojik=A1000&gwamok_no=1580&gyosu_no=19960004&lang_type=1'">보기</button></td>
                                <td>김인철</td>
                                <td>화678</td>
                                <td>8509 공대세미나실(8동5층)</td>
                                <td>176</td>
                                <td><%=result1580%></td>
                            </tr>
                            <tr>
                                <td>1581</td>
                                <td>인공지능</td>
                                <td><button type="button" class="details" onclick="location.href=''">보기</button></td>
                                <td>김인철</td>
                                <td>목678</td>
                                <td>8509 공대세미나실(8동5층)</td>
                                <td>176</td>
                                <td><%=result1581%></td>
                            </tr>
                            <tr>
                                <td>1582</td>
                                <td>소프트웨어공학</td>
                                <td><button type="button" class="details" onclick="location.href=''">보기</button></td>
                                <td>권기현</td>
                                <td>수678</td>
                                <td>8001 컴퓨터과학전산실습실</td>
                                <td>50</td>
                                <td><%=result1582%></td>
                            </tr>
                            <tr>
                                <td>1583</td>
                                <td>소프트웨어공학</td>
                                <td><button type="button" class="details" onclick="location.href='http://kutis.kyonggi.ac.kr/webkutis/view/hs/wssu5/wssu511prt_NoSession.jsp?year=2022&hakgi=10&jojik=A1000&gwamok_no=1583&gyosu_no=19910042&lang_type=1'">보기</button></td>
                                <td>권기현</td>
                                <td>화123</td>
                                <td>8308 컴퓨터과학전공실습실</td>
                                <td>50</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>1585</td>
                                <td>운영체제</td>
                                <td><button type="button" class="details" onclick="location.href=''">보기</button></td>
                                <td>이병대</td>
                                <td>월123</td>
                                <td>8001 컴퓨터과학전산실습실</td>
                                <td>50</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>1586</td>
                                <td>운영체제</td>
                                <td><button type="button" class="details" onclick="location.href=''">보기</button></td>
                                <td>이병대</td>
                                <td>화123</td>
                                <td>8001 컴퓨터과학전산실습실</td>
                                <td>50</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>1587</td>
                                <td>운영체제</td>
                                <td><button type="button" class="details" onclick="location.href='http://kutis.kyonggi.ac.kr/webkutis/view/hs/wssu5/wssu511prt_NoSession.jsp?year=2022&hakgi=10&jojik=A1000&gwamok_no=1587&gyosu_no=20100118&lang_type=1'">보기</button></td>
                                <td>이병대</td>
                                <td>수123</td>
                                <td>5407 강의실</td>
                                <td>50</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>1588</td>
                                <td>알고리듬</td>
                                <td><button type="button" class="details" onclick="location.href='http://kutis.kyonggi.ac.kr/webkutis/view/hs/wssu5/wssu511prt_NoSession.jsp?year=2022&hakgi=10&jojik=A1000&gwamok_no=1588&gyosu_no=20100120&lang_type=1'">보기</button></td>
                                <td>배상원</td>
                                <td>금123</td>
                                <td>종합308 강의실</td>
                                <td>60</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>1589</td>
                                <td>알고리듬</td>
                                <td><button type="button" class="details" onclick="location.href=''">보기</button></td>
                                <td>배상원</td>
                                <td>수123</td>
                                <td>종합203 강의실</td>
                                <td>60</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>1590</td>
                                <td>알고리듬</td>
                                <td><button type="button" class="details" onclick="location.href=''">보기</button></td>
                                <td>배상원</td>
                                <td>목123</td>
                                <td>종합301 강의실</td>
                                <td>60</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>1591</td>
                                <td>정보보호개론</td>
                                <td><button type="button" class="details" onclick="location.href=''">보기</button></td>
                                <td>김희열</td>
                                <td>화678</td>
                                <td>5006 소경대전공실습</td>
                                <td>50</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>1592</td>
                                <td>정보보호개론</td>
                                <td><button type="button" class="details" onclick="location.href='http://kutis.kyonggi.ac.kr/webkutis/view/hs/wssu5/wssu511prt_NoSession.jsp?year=2022&hakgi=10&jojik=A1000&gwamok_no=1592&gyosu_no=20090180&lang_type=1'">보기</button></td>
                                <td>김희열</td>
                                <td>수678</td>
                                <td>8008 공용강의실</td>
                                <td>50</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>1593</td>
                                <td>정보보호개론</td>
                                <td><button type="button" class="details" onclick="location.href=''">보기</button></td>
                                <td>김희열</td>
                                <td>목678</td>
                                <td>6401 비대면교육지원강의실</td>
                                <td>50</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>2348</td>
                                <td>컴퓨터공학기초캡스톤디자인</td>
                                <td><button type="button" class="details" onclick="location.href='http://kutis.kyonggi.ac.kr/webkutis/view/hs/wssu5/wssu511prt_NoSession.jsp?year=2022&hakgi=10&jojik=A1000&gwamok_no=2348&gyosu_no=20200311&lang_type=1'">보기</button></td>
                                <td>임현기</td>
                                <td>금567</td>
                                <td></td>
                                <td>164</td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
<script>
    $("#keyword").keyup(function() {
        var searchText = $(this).val();
        $("#lecture-table > tbody > tr").hide();
        var temp = $("#lecture-table > tbody > tr > td:contains('"+ searchText +"')");
        $(temp).parent().show();
    })
    // HY: 틀린 부분은 못 찾겠는데 작동이 안 됩니다ㅠ. 일단 나중에 고치도록 하겠습니다.
</script>
<script src="js/core.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>
