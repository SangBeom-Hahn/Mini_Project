<%--
  Created by IntelliJ IDEA.
  User: hsb99
  Date: 2022-05-11
  Time: 오후 5:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    </style>
</head>
<body>
<div class="preloader">
    <div class="preloader-body">
        <div class="cssload-container">
            <div class="cssload-speeding-wheel"></div>
        </div>
        <p>Loading...</p>
    </div>
</div>
<div class="page">
    <section class="section section-lg section-main-bunner section-main-bunner-filter text-center">
        <div class="main-bunner-img" style="background-image: url(./images/main.jpg); background-size: cover;"></div>
        <div class="main-bunner-inner">
            <div class="container">
                <div class="box-default">
                    <h1 class="box-default-title">KGU</h1>
                    <h2 class="box-default-text">Reservation System</h2>
                    <div class="box-default-decor"></div>
                </div>
            </div>
        </div>
    </section>
    <div class="bg-gray-1">


        <section class="section-transform-top">


            <div class="container">
                <div class="box-booking">
                    <form class="rd-form rd-mailform booking-form">

                        <%--                        로그인--%>
                        <div class="container container-main">
                            <h3>LOG IN</h3><br><br>
                            <from>
                                <div class="form-wrap">
                                    <p>학번</p>
                                    <input class="form-input" type="text" name="userID">
                                </div>

                                <div class="form-wrap">
                                    <p>비밀번호</p>
                                    <input class="form-input" id="booking-name" type="password" name="userPassword" >
                                </div>
                                <input type="button" class="btn btn-primary form-control" value="ENTER" onclick="hello(this.form);">

                                <%--                                <div> --%>
                                <%--                                    <button class="button button-lg button-gray-600" type="submit" onclick="location.href='Lectures.jsp'">ENTER</button>--%>
                                <%--                                </div>--%>
                            </from>

                            <script>
                                function  hello(cherry){
                                    hello3(cherry)
                                    cherry.action = "loginAction.jsp";
                                    cherry.method = "get";
                                    cherry.submit();
                                }
                                function  hello2(cherry){
                                    cherry.action = "joinAction.jsp";
                                    cherry.method = "get";
                                    cherry.submit();
                                }
                                function  hello3(cherry){
                                    cherry.action = "MyInfo.jsp";
                                    cherry.method = "get";
                                    cherry.submit();
                                }
                            </script>

                            <%--                            <div class="form-wrap">--%>
                            <%--                                <p>학번</p>--%>
                            <%--                                <input class="form-input" type="text" name="userID" data-constraints="@Required">--%>
                            <%--                            </div>--%>

                            <%--                            <div class="form-wrap">--%>
                            <%--                                <p>비밀번호</p>--%>
                            <%--                                <input class="form-input" id="booking-name" type="password" name="userPassword" data-constraints="@Required">--%>
                            <%--                            </div>--%>

                            <%--                            <div>--%>
                            <%--                                <button class="button button-lg button-gray-600" type="submit" onclick="location.href='Lectures.jsp'">ENTER</button>--%>
                            <%--                            </div>--%>

                        </div>

                        <%--                        회원가입--%>
                        <div class="container">
                            <h3>SIGH UP</h3><br><br>
                            <div class="form-wrap">
                                <p>이름</p>
                                <input class="form-input" type="text" name="userName2" >
                            </div>
                            <div class="form-wrap">
                                <p>이메일</p>
                                <input class="form-input" type="text" name="userEmail" >
                            </div>
                            <div class="form-wrap">
                                <p>학번</p>
                                <input class="form-input" type="text" name="userID2" >
                            </div>
                            <div class="form-wrap">
                                <p>비밀번호</p>
                                <input class="form-input" id="booking-phone" type="password" name="userPassword2">
                            </div>
                            <input type="button" class="button button-lg button-gray-600" value="ENTER" onclick="hello2(this.form);">
                        </div>


                    </form>
                </div>
            </div>
        </section>


    </div>
</div>
<script src="js/core.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>