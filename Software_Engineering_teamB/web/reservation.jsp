<%@ page import="sit.SitDAO" %> <!--우리가 만든 클래스를 그래도 가져오기-->
<%@ page import="java.io.PrintWriter" %>
<%@ page import="user.UserDAO" %>
<%@ page import="sit.SitDAO" %> <!--어떻게든 sql을 쓰기 위한 데이터 넘겨받으려고 한 흔적-->
<% request.setCharacterEncoding("UTF-8"); %>

<%--
  Created by IntelliJ IDEA.
  User: hsb99
  Date: 2022-05-16
  Time: 오전 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        .ie-panel{display: none;background: #212121;padding: 10px 0;box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3);clear: both;text-align:center;position: relative;z-index: 1;}
        html.ie-10 .ie-panel, html.lt-ie-10 .ie-panel {display: block;}
        .box-booking {min-height: 1000px;}
        .center {
            text-align: center;
            margin: 0 auto;
        }
        button{
            border-radius: 3px;
            border-style: none;
            background-color: gainsboro;
        }
        .clicked {
            color: white;
            background-color: black;
        }
        .section-transform-top {top: 340px;}
        #select_lectures {width: 400px;}
        .date_and_lecture { height: 120px; }
        #dl {width: 50%;}
        .container div {margin: auto;}
        .seat {
            display: none;
            padding: 30px 30px 80px 30px;
            text-align: center;
            border-bottom: 1px solid rgba(0, 0, 0, 0.2);
        }
        .seat button {
            width: 50px;
            height: 50px;
            margin: 5px;
        }
        #seat176 button {
            width: 35px;
            height: 35px;
            margin: 5px;
        }
        #rsv_div {
            padding-top: 60px;
            padding-bottom: 50px;
            min-height: 180px;
        }
        #rsvInfo_div {
            width: 300px;
            left: 50%;
            padding-left: 5px;
            text-align: left;
        }
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
                                    <li class="rd-nav-item active"><a class="rd-nav-link" href="reservation.jsp">Reservation</a></li>
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
                    <h2 class="box-default-text">Reservation</h2>
                    <div class="box-default-decor"></div>
                </div>
            </div>
        </div>
    </section>

    <div class="bg-gray-1">
        <section class="section-transform-top">
            <div class="container">
                <div class="box-booking">
                    <div class="date_and_lecture">
                        <div id="dl" style="float: left;">
                            <p class="booking-title">Date</p>
                            <div class="form-wrap">
                                <input class="form-input" style="width: 95%" id="booking-date" type="date">
                                <%--                                익스플로러 말고 크롬으로 실행!--%>
                            </div>
                        </div>
                        <div id="dl" style="float:left;">
                            <p class="booking-title">Lecture</p>
                            <div>
                                <select id="select_lectures" onchange="ChangeValue()">
                                    <option value="" disabled selected>강의 선택</option>
                                    <option>1579/인공지능(176)/김인철/월678</option>
                                    <option>1580/인공지능(176)/김인철/화678</option>
                                    <option>1581/인공지능(176)/김인철/목678</option>
                                    <option>1582/소프트웨어공학(50)/권기현/수678</option>
                                    <option>1583/소프트웨어공학(50)/권기현/화123</option>
                                    <option>1585/운영체제(50)/이병대/월123</option>
                                    <option>1586/운영체제(50)/이병대/화123</option>
                                    <option>1587/운영체제(50)/이병대/수123</option>
                                    <option>1588/알고리듬(60)/배상원/금123</option>
                                    <option>1589/알고리듬(60)/배상원/수123</option>
                                    <option>1590/알고리듬(60)/배상원/목123</option>
                                    <option>1591/정보보호개론(50)/김희열/화678</option>
                                    <option>1592/정보보호개론(50)/김희열/수678</option>
                                    <option>1593/정보보호개론(50)/김희열/목678</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="center">
                        <div class="seat" id="seat50">
                            <table>
                                <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>칠판</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td><button id="target1" class="b" onclick="reply_click(this.id)">1</button></td>
                                    <td><button id="target2" class="b" onclick="reply_click(this.id)">2</button></td>
                                    <td><button id="target3" class="b" onclick="reply_click(this.id)">3</button></td>
                                    <td><button id="target4" class="b" onclick="reply_click(this.id)">4</button></td>
                                    <td><button id="target5" class="b" onclick="reply_click(this.id)">5</button></td>
                                    <td></td>
                                    <td><button id="target6" class="b" onclick="reply_click(this.id)">6</button></td>
                                    <td><button id="target7" class="b" onclick="reply_click(this.id)">7</button></td>
                                    <td><button id="target8" class="b" onclick="reply_click(this.id)">8</button></td>
                                    <td><button id="target9" class="b" onclick="reply_click(this.id)">9</button></td>
                                    <td><button id="target10" class="b" onclick="reply_click(this.id)">10</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target11" class="b" onclick="reply_click(this.id)">11</button></td>
                                    <td><button id="target12" class="b" onclick="reply_click(this.id)">12</button></td>
                                    <td><button id="target13" class="b" onclick="reply_click(this.id)">13</button></td>
                                    <td><button id="target14" class="b" onclick="reply_click(this.id)">14</button></td>
                                    <td><button id="target15" class="b" onclick="reply_click(this.id)">15</button></td>
                                    <td></td>
                                    <td><button id="target16" class="b" onclick="reply_click(this.id)">16</button></td>
                                    <td><button id="target17" class="b" onclick="reply_click(this.id)">17</button></td>
                                    <td><button id="target18" class="b" onclick="reply_click(this.id)">18</button></td>
                                    <td><button id="target19" class="b" onclick="reply_click(this.id)">19</button></td>
                                    <td><button id="target20" class="b" onclick="reply_click(this.id)">20</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target21" class="b" onclick="reply_click(this.id)">21</button></td>
                                    <td><button id="target22" class="b" onclick="reply_click(this.id)">22</button></td>
                                    <td><button id="target23" class="b" onclick="reply_click(this.id)">23</button></td>
                                    <td><button id="target24" class="b" onclick="reply_click(this.id)">24</button></td>
                                    <td><button id="target25" class="b" onclick="reply_click(this.id)">25</button></td>
                                    <td></td>
                                    <td><button id="target26" class="b" onclick="reply_click(this.id)">26</button></td>
                                    <td><button id="target27" class="b" onclick="reply_click(this.id)">27</button></td>
                                    <td><button id="target28" class="b" onclick="reply_click(this.id)">28</button></td>
                                    <td><button id="target29" class="b" onclick="reply_click(this.id)">29</button></td>
                                    <td><button id="target30" class="b" onclick="reply_click(this.id)">30</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target31" class="b" onclick="reply_click(this.id)">31</button></td>
                                    <td><button id="target32" class="b" onclick="reply_click(this.id)">32</button></td>
                                    <td><button id="target33" class="b" onclick="reply_click(this.id)">33</button></td>
                                    <td><button id="target34" class="b" onclick="reply_click(this.id)">34</button></td>
                                    <td><button id="target35" class="b" onclick="reply_click(this.id)">35</button></td>
                                    <td></td>
                                    <td><button id="target36" class="b" onclick="reply_click(this.id)">36</button></td>
                                    <td><button id="target37" class="b" onclick="reply_click(this.id)">37</button></td>
                                    <td><button id="target38" class="b" onclick="reply_click(this.id)">38</button></td>
                                    <td><button id="target39" class="b" onclick="reply_click(this.id)">39</button></td>
                                    <td><button id="target40" class="b" onclick="reply_click(this.id)">40</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target41" class="b" onclick="reply_click(this.id)">41</button></td>
                                    <td><button id="target42" class="b" onclick="reply_click(this.id)">42</button></td>
                                    <td><button id="target43" class="b" onclick="reply_click(this.id)">43</button></td>
                                    <td><button id="target44" class="b" onclick="reply_click(this.id)">44</button></td>
                                    <td><button id="target45" class="b" onclick="reply_click(this.id)">45</button></td>
                                    <td></td>
                                    <td><button id="target46" class="b" onclick="reply_click(this.id)">46</button></td>
                                    <td><button id="target47" class="b" onclick="reply_click(this.id)">47</button></td>
                                    <td><button id="target48" class="b" onclick="reply_click(this.id)">48</button></td>
                                    <td><button id="target49" class="b" onclick="reply_click(this.id)">49</button></td>
                                    <td><button id="target50" class="b" onclick="reply_click(this.id)">50</button></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="seat" id="seat60">
                            <table>
                                <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>칠판</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td><button id="target1" class="b" onclick="reply_click(this.id)">1</button></td>
                                    <td><button id="target2" class="b" onclick="reply_click(this.id)">2</button></td>
                                    <td><button id="target3" class="b" onclick="reply_click(this.id)">3</button></td>
                                    <td><button id="target4" class="b" onclick="reply_click(this.id)">4</button></td>
                                    <td><button id="target5" class="b" onclick="reply_click(this.id)">5</button></td>
                                    <td></td>
                                    <td><button id="target6" class="b" onclick="reply_click(this.id)">6</button></td>
                                    <td><button id="target7" class="b" onclick="reply_click(this.id)">7</button></td>
                                    <td><button id="target8" class="b" onclick="reply_click(this.id)">8</button></td>
                                    <td><button id="target9" class="b" onclick="reply_click(this.id)">9</button></td>
                                    <td><button id="target10" class="b" onclick="reply_click(this.id)">10</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target11" class="b" onclick="reply_click(this.id)">11</button></td>
                                    <td><button id="target12" class="b" onclick="reply_click(this.id)">12</button></td>
                                    <td><button id="target13" class="b" onclick="reply_click(this.id)">13</button></td>
                                    <td><button id="target14" class="b" onclick="reply_click(this.id)">14</button></td>
                                    <td><button id="target15" class="b" onclick="reply_click(this.id)">15</button></td>
                                    <td></td>
                                    <td><button id="target16" class="b" onclick="reply_click(this.id)">16</button></td>
                                    <td><button id="target17" class="b" onclick="reply_click(this.id)">17</button></td>
                                    <td><button id="target18" class="b" onclick="reply_click(this.id)">18</button></td>
                                    <td><button id="target19" class="b" onclick="reply_click(this.id)">19</button></td>
                                    <td><button id="target20" class="b" onclick="reply_click(this.id)">20</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target21" class="b" onclick="reply_click(this.id)">21</button></td>
                                    <td><button id="target22" class="b" onclick="reply_click(this.id)">22</button></td>
                                    <td><button id="target23" class="b" onclick="reply_click(this.id)">23</button></td>
                                    <td><button id="target24" class="b" onclick="reply_click(this.id)">24</button></td>
                                    <td><button id="target25" class="b" onclick="reply_click(this.id)">25</button></td>
                                    <td></td>
                                    <td><button id="target26" class="b" onclick="reply_click(this.id)">26</button></td>
                                    <td><button id="target27" class="b" onclick="reply_click(this.id)">27</button></td>
                                    <td><button id="target28" class="b" onclick="reply_click(this.id)">28</button></td>
                                    <td><button id="target29" class="b" onclick="reply_click(this.id)">29</button></td>
                                    <td><button id="target30" class="b" onclick="reply_click(this.id)">30</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target31" class="b" onclick="reply_click(this.id)">31</button></td>
                                    <td><button id="target32" class="b" onclick="reply_click(this.id)">32</button></td>
                                    <td><button id="target33" class="b" onclick="reply_click(this.id)">33</button></td>
                                    <td><button id="target34" class="b" onclick="reply_click(this.id)">34</button></td>
                                    <td><button id="target35" class="b" onclick="reply_click(this.id)">35</button></td>
                                    <td></td>
                                    <td><button id="target36" class="b" onclick="reply_click(this.id)">36</button></td>
                                    <td><button id="target37" class="b" onclick="reply_click(this.id)">37</button></td>
                                    <td><button id="target38" class="b" onclick="reply_click(this.id)">38</button></td>
                                    <td><button id="target39" class="b" onclick="reply_click(this.id)">39</button></td>
                                    <td><button id="target40" class="b" onclick="reply_click(this.id)">40</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target41" class="b" onclick="reply_click(this.id)">41</button></td>
                                    <td><button id="target42" class="b" onclick="reply_click(this.id)">42</button></td>
                                    <td><button id="target43" class="b" onclick="reply_click(this.id)">43</button></td>
                                    <td><button id="target44" class="b" onclick="reply_click(this.id)">44</button></td>
                                    <td><button id="target45" class="b" onclick="reply_click(this.id)">45</button></td>
                                    <td></td>
                                    <td><button id="target46" class="b" onclick="reply_click(this.id)">46</button></td>
                                    <td><button id="target47" class="b" onclick="reply_click(this.id)">47</button></td>
                                    <td><button id="target48" class="b" onclick="reply_click(this.id)">48</button></td>
                                    <td><button id="target49" class="b" onclick="reply_click(this.id)">49</button></td>
                                    <td><button id="target50" class="b" onclick="reply_click(this.id)">50</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target51" class="b" onclick="reply_click(this.id)">51</button></td>
                                    <td><button id="target52" class="b" onclick="reply_click(this.id)">52</button></td>
                                    <td><button id="target53" class="b" onclick="reply_click(this.id)">53</button></td>
                                    <td><button id="target54" class="b" onclick="reply_click(this.id)">54</button></td>
                                    <td><button id="target55" class="b" onclick="reply_click(this.id)">55</button></td>
                                    <td></td>
                                    <td><button id="target56" class="b" onclick="reply_click(this.id)">56</button></td>
                                    <td><button id="target57" class="b" onclick="reply_click(this.id)">57</button></td>
                                    <td><button id="target58" class="b" onclick="reply_click(this.id)">58</button></td>
                                    <td><button id="target59" class="b" onclick="reply_click(this.id)">59</button></td>
                                    <td><button id="target60" class="b" onclick="reply_click(this.id)">60</button></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="seat" id="seat176">
                            <table>
                                <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td style="width: 30px;"></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>칠판</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td style="width: 30px;"></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td><button id="target1" class="b" onclick="reply_click(this.id)">1</button></td>
                                    <td><button id="target2" class="b" onclick="reply_click(this.id)">2</button></td>
                                    <td><button id="target3" class="b" onclick="reply_click(this.id)">3</button></td>
                                    <td><button id="target4" class="b" onclick="reply_click(this.id)">4</button></td>
                                    <td></td>
                                    <td><button id="target5" class="b" onclick="reply_click(this.id)">5</button></td>
                                    <td><button id="target6" class="b" onclick="reply_click(this.id)">6</button></td>
                                    <td><button id="target7" class="b" onclick="reply_click(this.id)">7</button></td>
                                    <td><button id="target8" class="b" onclick="reply_click(this.id)">8</button></td>
                                    <td></td>
                                    <td><button id="target9" class="b" onclick="reply_click(this.id)">9</button></td>
                                    <td><button id="target10" class="b" onclick="reply_click(this.id)">10</button></td>
                                    <td><button id="target11" class="b" onclick="reply_click(this.id)">11</button></td>
                                    <td><button id="target12" class="b" onclick="reply_click(this.id)">12</button></td>
                                    <td></td>
                                    <td><button id="target13" class="b" onclick="reply_click(this.id)">13</button></td>
                                    <td><button id="target14" class="b" onclick="reply_click(this.id)">14</button></td>
                                    <td><button id="target15" class="b" onclick="reply_click(this.id)">15</button></td>
                                    <td><button id="target16" class="b" onclick="reply_click(this.id)">16</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target17" class="b" onclick="reply_click(this.id)">17</button></td>
                                    <td><button id="target18" class="b" onclick="reply_click(this.id)">18</button></td>
                                    <td><button id="target19" class="b" onclick="reply_click(this.id)">19</button></td>
                                    <td><button id="target20" class="b" onclick="reply_click(this.id)">20</button></td>
                                    <td></td>
                                    <td><button id="target21" class="b" onclick="reply_click(this.id)">21</button></td>
                                    <td><button id="target22" class="b" onclick="reply_click(this.id)">22</button></td>
                                    <td><button id="target23" class="b" onclick="reply_click(this.id)">23</button></td>
                                    <td><button id="target24" class="b" onclick="reply_click(this.id)">24</button></td>
                                    <td></td>
                                    <td><button id="target25" class="b" onclick="reply_click(this.id)">25</button></td>
                                    <td><button id="target26" class="b" onclick="reply_click(this.id)">26</button></td>
                                    <td><button id="target27" class="b" onclick="reply_click(this.id)">27</button></td>
                                    <td><button id="target28" class="b" onclick="reply_click(this.id)">28</button></td>
                                    <td></td>
                                    <td><button id="target29" class="b" onclick="reply_click(this.id)">29</button></td>
                                    <td><button id="target30" class="b" onclick="reply_click(this.id)">30</button></td>
                                    <td><button id="target31" class="b" onclick="reply_click(this.id)">31</button></td>
                                    <td><button id="target32" class="b" onclick="reply_click(this.id)">32</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target33" class="b" onclick="reply_click(this.id)">33</button></td>
                                    <td><button id="target34" class="b" onclick="reply_click(this.id)">34</button></td>
                                    <td><button id="target35" class="b" onclick="reply_click(this.id)">35</button></td>
                                    <td><button id="target36" class="b" onclick="reply_click(this.id)">36</button></td>
                                    <td></td>
                                    <td><button id="target37" class="b" onclick="reply_click(this.id)">37</button></td>
                                    <td><button id="target38" class="b" onclick="reply_click(this.id)">38</button></td>
                                    <td><button id="target39" class="b" onclick="reply_click(this.id)">39</button></td>
                                    <td><button id="target40" class="b" onclick="reply_click(this.id)">40</button></td>
                                    <td></td>
                                    <td><button id="target41" class="b" onclick="reply_click(this.id)">41</button></td>
                                    <td><button id="target42" class="b" onclick="reply_click(this.id)">42</button></td>
                                    <td><button id="target43" class="b" onclick="reply_click(this.id)">43</button></td>
                                    <td><button id="target44" class="b" onclick="reply_click(this.id)">44</button></td>
                                    <td></td>
                                    <td><button id="target45" class="b" onclick="reply_click(this.id)">45</button></td>
                                    <td><button id="target46" class="b" onclick="reply_click(this.id)">46</button></td>
                                    <td><button id="target47" class="b" onclick="reply_click(this.id)">47</button></td>
                                    <td><button id="target48" class="b" onclick="reply_click(this.id)">48</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target49" class="b" onclick="reply_click(this.id)">49</button></td>
                                    <td><button id="target50" class="b" onclick="reply_click(this.id)">50</button></td>
                                    <td><button id="target51" class="b" onclick="reply_click(this.id)">51</button></td>
                                    <td><button id="target52" class="b" onclick="reply_click(this.id)">52</button></td>
                                    <td></td>
                                    <td><button id="target53" class="b" onclick="reply_click(this.id)">53</button></td>
                                    <td><button id="target54" class="b" onclick="reply_click(this.id)">54</button></td>
                                    <td><button id="target55" class="b" onclick="reply_click(this.id)">55</button></td>
                                    <td><button id="target56" class="b" onclick="reply_click(this.id)">56</button></td>
                                    <td></td>
                                    <td><button id="target57" class="b" onclick="reply_click(this.id)">57</button></td>
                                    <td><button id="target58" class="b" onclick="reply_click(this.id)">58</button></td>
                                    <td><button id="target59" class="b" onclick="reply_click(this.id)">59</button></td>
                                    <td><button id="target60" class="b" onclick="reply_click(this.id)">60</button></td>
                                    <td></td>
                                    <td><button id="target61" class="b" onclick="reply_click(this.id)">61</button></td>
                                    <td><button id="target62" class="b" onclick="reply_click(this.id)">62</button></td>
                                    <td><button id="target63" class="b" onclick="reply_click(this.id)">63</button></td>
                                    <td><button id="target64" class="b" onclick="reply_click(this.id)">64</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target65" class="b" onclick="reply_click(this.id)">65</button></td>
                                    <td><button id="target66" class="b" onclick="reply_click(this.id)">66</button></td>
                                    <td><button id="target67" class="b" onclick="reply_click(this.id)">67</button></td>
                                    <td><button id="target68" class="b" onclick="reply_click(this.id)">68</button></td>
                                    <td></td>
                                    <td><button id="target69" class="b" onclick="reply_click(this.id)">69</button></td>
                                    <td><button id="target70" class="b" onclick="reply_click(this.id)">70</button></td>
                                    <td><button id="target71" class="b" onclick="reply_click(this.id)">71</button></td>
                                    <td><button id="target72" class="b" onclick="reply_click(this.id)">72</button></td>
                                    <td></td>
                                    <td><button id="target73" class="b" onclick="reply_click(this.id)">73</button></td>
                                    <td><button id="target74" class="b" onclick="reply_click(this.id)">74</button></td>
                                    <td><button id="target75" class="b" onclick="reply_click(this.id)">75</button></td>
                                    <td><button id="target76" class="b" onclick="reply_click(this.id)">76</button></td>
                                    <td></td>
                                    <td><button id="target77" class="b" onclick="reply_click(this.id)">77</button></td>
                                    <td><button id="target78" class="b" onclick="reply_click(this.id)">78</button></td>
                                    <td><button id="target79" class="b" onclick="reply_click(this.id)">79</button></td>
                                    <td><button id="target80" class="b" onclick="reply_click(this.id)">80</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target81" class="b" onclick="reply_click(this.id)">81</button></td>
                                    <td><button id="target82" class="b" onclick="reply_click(this.id)">82</button></td>
                                    <td><button id="target83" class="b" onclick="reply_click(this.id)">83</button></td>
                                    <td><button id="target84" class="b" onclick="reply_click(this.id)">84</button></td>
                                    <td></td>
                                    <td><button id="target85" class="b" onclick="reply_click(this.id)">85</button></td>
                                    <td><button id="target86" class="b" onclick="reply_click(this.id)">86</button></td>
                                    <td><button id="target87" class="b" onclick="reply_click(this.id)">87</button></td>
                                    <td><button id="target88" class="b" onclick="reply_click(this.id)">88</button></td>
                                    <td></td>
                                    <td><button id="target89" class="b" onclick="reply_click(this.id)">89</button></td>
                                    <td><button id="target90" class="b" onclick="reply_click(this.id)">90</button></td>
                                    <td><button id="target91" class="b" onclick="reply_click(this.id)">91</button></td>
                                    <td><button id="target92" class="b" onclick="reply_click(this.id)">92</button></td>
                                    <td></td>
                                    <td><button id="target93" class="b" onclick="reply_click(this.id)">93</button></td>
                                    <td><button id="target94" class="b" onclick="reply_click(this.id)">94</button></td>
                                    <td><button id="target95" class="b" onclick="reply_click(this.id)">95</button></td>
                                    <td><button id="target96" class="b" onclick="reply_click(this.id)">96</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target97" class="b" onclick="reply_click(this.id)">97</button></td>
                                    <td><button id="target98" class="b" onclick="reply_click(this.id)">98</button></td>
                                    <td><button id="target99" class="b" onclick="reply_click(this.id)">99</button></td>
                                    <td><button id="target100" class="b" onclick="reply_click(this.id)">100</button></td>
                                    <td></td>
                                    <td><button id="target101" class="b" onclick="reply_click(this.id)">101</button></td>
                                    <td><button id="target102" class="b" onclick="reply_click(this.id)">102</button></td>
                                    <td><button id="target103" class="b" onclick="reply_click(this.id)">103</button></td>
                                    <td><button id="target104" class="b" onclick="reply_click(this.id)">104</button></td>
                                    <td></td>
                                    <td><button id="target105" class="b" onclick="reply_click(this.id)">105</button></td>
                                    <td><button id="target106" class="b" onclick="reply_click(this.id)">106</button></td>
                                    <td><button id="target107" class="b" onclick="reply_click(this.id)">107</button></td>
                                    <td><button id="target108" class="b" onclick="reply_click(this.id)">108</button></td>
                                    <td></td>
                                    <td><button id="target109" class="b" onclick="reply_click(this.id)">109</button></td>
                                    <td><button id="target110" class="b" onclick="reply_click(this.id)">110</button></td>
                                    <td><button id="target111" class="b" onclick="reply_click(this.id)">111</button></td>
                                    <td><button id="target112" class="b" onclick="reply_click(this.id)">112</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target113" class="b" onclick="reply_click(this.id)">113</button></td>
                                    <td><button id="target114" class="b" onclick="reply_click(this.id)">114</button></td>
                                    <td><button id="target115" class="b" onclick="reply_click(this.id)">115</button></td>
                                    <td><button id="target116" class="b" onclick="reply_click(this.id)">116</button></td>
                                    <td></td>
                                    <td><button id="target117" class="b" onclick="reply_click(this.id)">117</button></td>
                                    <td><button id="target118" class="b" onclick="reply_click(this.id)">118</button></td>
                                    <td><button id="target119" class="b" onclick="reply_click(this.id)">119</button></td>
                                    <td><button id="target120" class="b" onclick="reply_click(this.id)">120</button></td>
                                    <td></td>
                                    <td><button id="target121" class="b" onclick="reply_click(this.id)">121</button></td>
                                    <td><button id="target122" class="b" onclick="reply_click(this.id)">122</button></td>
                                    <td><button id="target123" class="b" onclick="reply_click(this.id)">123</button></td>
                                    <td><button id="target124" class="b" onclick="reply_click(this.id)">124</button></td>
                                    <td></td>
                                    <td><button id="target125" class="b" onclick="reply_click(this.id)">125</button></td>
                                    <td><button id="target126" class="b" onclick="reply_click(this.id)">126</button></td>
                                    <td><button id="target127" class="b" onclick="reply_click(this.id)">127</button></td>
                                    <td><button id="target128" class="b" onclick="reply_click(this.id)">128</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target129" class="b" onclick="reply_click(this.id)">129</button></td>
                                    <td><button id="target130" class="b" onclick="reply_click(this.id)">130</button></td>
                                    <td><button id="target131" class="b" onclick="reply_click(this.id)">131</button></td>
                                    <td><button id="target132" class="b" onclick="reply_click(this.id)">132</button></td>
                                    <td></td>
                                    <td><button id="target133" class="b" onclick="reply_click(this.id)">133</button></td>
                                    <td><button id="target134" class="b" onclick="reply_click(this.id)">134</button></td>
                                    <td><button id="target135" class="b" onclick="reply_click(this.id)">135</button></td>
                                    <td><button id="target136" class="b" onclick="reply_click(this.id)">136</button></td>
                                    <td></td>
                                    <td><button id="target137" class="b" onclick="reply_click(this.id)">137</button></td>
                                    <td><button id="target138" class="b" onclick="reply_click(this.id)">138</button></td>
                                    <td><button id="target139" class="b" onclick="reply_click(this.id)">139</button></td>
                                    <td><button id="target140" class="b" onclick="reply_click(this.id)">140</button></td>
                                    <td></td>
                                    <td><button id="target141" class="b" onclick="reply_click(this.id)">141</button></td>
                                    <td><button id="target142" class="b" onclick="reply_click(this.id)">142</button></td>
                                    <td><button id="target143" class="b" onclick="reply_click(this.id)">143</button></td>
                                    <td><button id="target144" class="b" onclick="reply_click(this.id)">144</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target145" class="b" onclick="reply_click(this.id)">145</button></td>
                                    <td><button id="target146" class="b" onclick="reply_click(this.id)">146</button></td>
                                    <td><button id="target147" class="b" onclick="reply_click(this.id)">147</button></td>
                                    <td><button id="target148" class="b" onclick="reply_click(this.id)">148</button></td>
                                    <td></td>
                                    <td><button id="target149" class="b" onclick="reply_click(this.id)">149</button></td>
                                    <td><button id="target150" class="b" onclick="reply_click(this.id)">150</button></td>
                                    <td><button id="target151" class="b" onclick="reply_click(this.id)">151</button></td>
                                    <td><button id="target152" class="b" onclick="reply_click(this.id)">152</button></td>
                                    <td></td>
                                    <td><button id="target153" class="b" onclick="reply_click(this.id)">153</button></td>
                                    <td><button id="target154" class="b" onclick="reply_click(this.id)">154</button></td>
                                    <td><button id="target155" class="b" onclick="reply_click(this.id)">155</button></td>
                                    <td><button id="target156" class="b" onclick="reply_click(this.id)">156</button></td>
                                    <td></td>
                                    <td><button id="target157" class="b" onclick="reply_click(this.id)">157</button></td>
                                    <td><button id="target158" class="b" onclick="reply_click(this.id)">158</button></td>
                                    <td><button id="target159" class="b" onclick="reply_click(this.id)">159</button></td>
                                    <td><button id="target160" class="b" onclick="reply_click(this.id)">160</button></td>
                                </tr>
                                <tr>
                                    <td><button id="target161" class="b" onclick="reply_click(this.id)">161</button></td>
                                    <td><button id="target162" class="b" onclick="reply_click(this.id)">162</button></td>
                                    <td><button id="target163" class="b" onclick="reply_click(this.id)">163</button></td>
                                    <td><button id="target164" class="b" onclick="reply_click(this.id)">164</button></td>
                                    <td></td>
                                    <td><button id="target165" class="b" onclick="reply_click(this.id)">165</button></td>
                                    <td><button id="target166" class="b" onclick="reply_click(this.id)">166</button></td>
                                    <td><button id="target167" class="b" onclick="reply_click(this.id)">167</button></td>
                                    <td><button id="target168" class="b" onclick="reply_click(this.id)">168</button></td>
                                    <td></td>
                                    <td><button id="target169" class="b" onclick="reply_click(this.id)">169</button></td>
                                    <td><button id="target170" class="b" onclick="reply_click(this.id)">170</button></td>
                                    <td><button id="target171" class="b" onclick="reply_click(this.id)">171</button></td>
                                    <td><button id="target172" class="b" onclick="reply_click(this.id)">172</button></td>
                                    <td></td>
                                    <td><button id="target173" class="b" onclick="reply_click(this.id)">173</button></td>
                                    <td><button id="target174" class="b" onclick="reply_click(this.id)">174</button></td>
                                    <td><button id="target175" class="b" onclick="reply_click(this.id)">175</button></td>
                                    <td><button id="target176" class="b" onclick="reply_click(this.id)">176</button></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>


                        <div id="rsv_div">
                            <p class="booking-title" style="font-size: 20px; font-weight: bold;">< 예약정보 ></p>
                            <div id="rsvInfo_div">
                                <div class="r_info" id="r_info_1"></div>
                                <div class="r_info" id="r_info_2"></div>
                                <div class="r_info" id="r_info_3"></div>
                            </div>
                        </div>
                        <div style="padding-top: 10px;">
                            <button class="button button-lg button-gray-600" type="submit" onclick="btnDisabled()">Reservation</button>
                        </div>
                    </div>
                </div>

                <script>
                    /* 날짜 선택 - 예약정보 출력 */
                    document.getElementById('booking-date').onchange = function(){
                        document.getElementById("r_info_1").innerText = "날짜: " + this.value ;
                    }
                    /* 강의 선택 -  예약정보 출력, 테이블 변경 */
                    var strArray;
                    function ChangeValue() {
                        var value_str = document.getElementById("select_lectures");
                        var select_str = value_str.options[value_str.selectedIndex].text;
                        strArray=select_str.split('/')[0];
                        alert(strArray)

                        document.getElementById("r_info_2").innerText = "강의: " + select_str;

                        if (select_str.includes("(50)")) {
                            document.getElementById("seat60").style.display = "none";
                            document.getElementById("seat176").style.display = "none";
                            document.getElementById("seat50").style.display = "inline-block";
                        }
                        else if (select_str.includes("(60)")) {
                            document.getElementById("seat50").style.display = "none";
                            document.getElementById("seat176").style.display = "none";
                            document.getElementById("seat60").style.display = "inline-block";
                        }
                        else if (select_str.includes("(176)")) {
                            document.getElementById("seat50").style.display = "none";
                            document.getElementById("seat60").style.display = "none";
                            document.getElementById("seat176").style.display = "inline-block";
                        }


                    }
                    /* 좌석 선택 - 색상 변경, 예약정보 출력 */
                    var b = document.getElementsByClassName("b");
                    function handleClick(event) {
                        if (event.target.classList[1] === "clicked") {
                            event.target.classList.remove("clicked");
                        } else {
                            for (var i = 0; i < b.length; i++) {
                                b[i].classList.remove("clicked");
                            }
                            event.target.classList.add("clicked");
                        }
                    }
                    function info(event) {
                        document.getElementById("r_info_3").innerText = "좌석: " + event.target.innerText;
                    }
                    function init() {
                        for (var i = 0; i < b.length; i++) {
                            b[i].addEventListener("click", handleClick);
                            b[i].addEventListener("click", info);
                        }
                    }
                    init();
                    /* 예약 */
                    var bb;
                    var sig = 0;
                    function btnDisabled() {
                        if (sig == 0) {
                            const target = document.getElementById(bb);
                            target.disabled = true;
                            sig = 1;

                            callDAO(strArray)
                        } else {
                            alert("이미 예약이 완료되었습니다.");
                        }
                    }
                    function reply_click(clicked_id){
                        bb = clicked_id;
                    }
                    <%--function callDAO(strArray){--%>
                    <%--    var hsb = <%=strArray%>;--%>
                    <%--    <%--%>
                    <%--        SitDAO sitDAO = new SitDAO();--%>
                    <%--        //이 안에 strArray를 넣고 싶다--%>
                    <%--        sitDAO.minus(hsb);--%>
                    <%--    %>--%>
                    <%--}--%>
                </script>
            </div>
        </section>
    </div>
</div>
<script src="js/core.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>
