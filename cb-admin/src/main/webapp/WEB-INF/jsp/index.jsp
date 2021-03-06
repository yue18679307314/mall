<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: gonglei
  Date: 16/1/14
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if lt IE 7]>  <html class="ie ie6 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 7]>     <html class="ie ie7 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 8]>     <html class="ie ie8 lte9 lte8 no-js">      <![endif]-->
<!--[if IE 9]>     <html class="ie ie9 lte9 no-js">           <![endif]-->
<!--[if gt IE 9]>  <html class="no-js">                       <![endif]-->
<!--[if !IE]><!--> <html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>云信后台管理</title>

  <!-- // IOS webapp icons // -->

  <meta name="apple-mobile-web-app-title" content="Karma Webapp">
  <link rel="apple-touch-icon-precomposed" sizes="152x152" href="images/mobile/apple-touch-icon-152x152.png" />
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/mobile/apple-touch-icon-144x144.png">
  <link rel="apple-touch-icon-precomposed" sizes="120x120" href="images/mobile/apple-touch-icon-120x120.png" />
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/mobile/apple-touch-icon-114x114.png" />
  <link rel="apple-touch-icon-precomposed" sizes="76x76" href="images/mobile/apple-touch-icon-76x76.png" />
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/mobile/apple-touch-icon-72x72.png" />
  <link rel="apple-touch-icon-precomposed" href="images/mobile/apple-touch-icon.png" />
  <link rel="shortcut icon" href="images/favicons/favicon.ico" />

  <!-- // IOS webapp splash screens // -->

  <link rel="apple-touch-startup-image" media="(device-width: 768px) and (device-height: 1024px) and (orientation: portrait) and (-webkit-device-pixel-ratio: 2)"
        href="/images/mobile/apple-touch-startup-image-1536x2008.png"/>
  <link rel="apple-touch-startup-image" media="(device-width: 768px) and (device-height: 1024px) and (orientation: landscape) and (-webkit-device-pixel-ratio: 2)"
        href="/images/mobile/apple-touch-startup-image-1496x2048.png"/>
  <link rel="apple-touch-startup-image" media="(device-width: 768px) and (device-height: 1024px) and (orientation: portrait) and (-webkit-device-pixel-ratio: 1)"
        href="/images/mobile/apple-touch-startup-image-768x1004.png"/>
  <link rel="apple-touch-startup-image" media="(device-width: 768px) and (device-height: 1024px) and (orientation: landscape) and (-webkit-device-pixel-ratio: 1)"
        href="/images/mobile/apple-touch-startup-image-748x1024.png"/>
  <link rel="apple-touch-startup-image" media="(device-width: 320px) and (device-height: 568px) and (-webkit-device-pixel-ratio: 2)"
        href="/images/mobile/apple-touch-startup-image-640x1096.png"/>
  <link rel="apple-touch-startup-image" media="(device-width: 320px) and (device-height: 480px) and (-webkit-device-pixel-ratio: 2)"
        href="/images/mobile/apple-touch-startup-image-640x920.png"/>
  <link rel="apple-touch-startup-image" media="(device-width: 320px) and (device-height: 480px) and (-webkit-device-pixel-ratio: 1)"
        href="/images/mobile/apple-touch-startup-image-320x460.png"/>

  <!-- // Windows 8 tile // -->

  <meta name="application-name" content="Karma Webapp">
  <meta name="msapplication-TileColor" content="#333333" />
  <meta name="msapplication-TileImage" content="images/mobile/windows8-icon.png" />

  <!-- // Handheld devices misc // -->

  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="HandheldFriendly" content="true"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

  <!-- // Stylesheets // -->

  <link rel="stylesheet" href="bootstrap/core/dist/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="css/bootstrap-custom.css"/>
  <link rel="stylesheet" href="css/bootstrap-extended.css"/>
  <link rel="stylesheet" href="css/login.css"/>
  <link rel="stylesheet" href="css/light-theme.css"/>

  <!-- // Helpers // -->

  <script src="js/plugins/modernizr.min.js"></script>
  <script src="js/plugins/mobiledevices.js"></script>

  <!-- // jQuery core // -->

  <script src="js/libs/jquery-1.11.0.min.js"></script>
  <script src="js/libs/jquery-ui-1.10.4.min.js"></script>

  <!-- // Bootstrap // -->

  <script src="bootstrap/core/dist/js/bootstrap.min.js"></script>

  <!-- // Custom/premium plugins // -->

  <script src="js/plugins/showpassword.1.0.min.js"></script>
  <script src="js/plugins/nanogress.1.0.min.js"></script>
  <script src="js/plugins/powerwizard.1.0.min.js"></script>

  <!-- // Third-party plugins // -->

  <script src="js/plugins/jquery.pwstrength.min.js"></script>

  <!-- // Custom //-->

  <script src="js/plugins/login.js"></script>





</head>
<body style="background: url('images/indexBg/background.png') no-repeat;  background-size: cover; background-position: center">
<div id="container-loginmin" class="clearfix">


  <div id="demo-overview" align="center" style="margin-top: 40%; margin-bottom: 40px" >
    <img style="width: 180px" src="images/indexBg/logo.png" alt=""/>
  <!--
    <a href="index.html">Dashboard</a>
    |
    <a href="login.html">Login 1</a>
    |
    <a href="reset.html">Reset</a>
    |
    <a href="signup.html">SignUp</a>
    |
    <a href="forgot.html">Forgot</a>
    -->
  </div>


  <form:errors>
    <div class="alert alert-block alert-dismissable alert-danger">
      <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
      <h4>Notice:</h4>
      <p>Minimalistic form with an extra avatar image,bigger input fields and a bigger submit button.</p>
    </div>
  </form:errors>

  <div style="position: relative">
    <img style="width: 100%; position: absolute" src="images/indexBg/loginBg.png" alt=""/>

    <form id="form-login" style="width: 100%;margin-top: 50px;position: absolute;" action="login" method="post">
      <%--<div class="login-avatar">--%>
        <%--<img src="images/users/logo.png" alt="" id="login-avatar"/>--%>
      <%--</div>--%>
      <div class="login-fields-wrapper" style="float: none; width: 100%">
        <div class="row" style="">
          <div class="col-lg-12">
            <input class="form-control input-lg" style="width: 85%; height: 50px; margin: 0 7.5%; border: 2px solid #e5e5e5; border-radius: 6px" id="j_username" name="username" type="text" placeholder="请输入用户名" tabindex="1" value="" />
          </div>
        </div>
        <div class="spacer-10" style="height: 20px"></div>
        <div class="row">
          <div class="col-lg-12">
            <input class="form-control input-lg" style="width: 85%; height: 50px; margin: 0 7.5%; border: 2px solid #e5e5e5; border-radius: 6px" id="j_password" name="password" type="password" placeholder="请输入密码" tabindex="2" />
          </div>
        </div>
        <div class="spacer-20"><div  style="width: 100%;position:relative;  font-size: 14px;color: red;margin-left: 30px"> ${SPRING_SECURITY_LAST_EXCEPTION.message}</div></div>
        <div class="row">

          <div class="col-lg-12">
            <!-- this needs to be a button/input element -->
            <button type="submit" class="btn btn-default btn-lg" style="width: 85%; margin: 0 7.5%; height: 50px; color: #fff; background: #f5ca1d; font-size: 20px; border: 0; font-weight: normal">登录</button>
          </div>
        </div>
      </div>
    </form>
  </div>

  <%--<div id="login-box" style="position: relative; background-image: url('images/indexBg/loginBg.png')">--%>
    <%--<div class="login-box-inner clearfix">--%>
      <%--<header id="login-header">--%>
        <%--<a href="#" id="login-logo">--%>
          <%--<h1>云信管理后台</h1>--%>
          <%--<h4> ${SPRING_SECURITY_LAST_EXCEPTION.message}</h4>--%>
        <%--</a>--%>
      <%--</header>--%>
      <%--<div class="spacer-40"></div>--%>
      <%--<form id="form-login" action="j_spring_security_check" method="post">--%>
        <%--<div class="login-avatar">--%>
          <%--&lt;%&ndash;<img src="images/users/logo.png" alt="" id="login-avatar"/>&ndash;%&gt;--%>
        <%--</div>--%>
        <%--<div class="login-fields-wrapper">--%>
          <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
              <%--<input class="form-control input-lg" id="j_username" name="j_username" type="text" placeholder="Username" tabindex="1" value="admin" />--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--<div class="spacer-10"></div>--%>
          <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
              <%--<input class="form-control input-lg" id="j_password" name="j_password" type="password" placeholder="Password" tabindex="2" />--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--<div class="spacer-20"></div>--%>
          <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
              <%--<!-- this needs to be a button/input element -->--%>
              <%--<button type="submit" class="btn btn-default btn-lg">登录</button>--%>
            <%--</div>--%>
          <%--</div>--%>
        <%--</div>--%>
      <%--</form>--%>
    <%--</div>--%>
  <%--</div>--%>
  <!--
  <footer id="login-footer">
    <strong>Copyright © 2013 3333.net</strong>
    <div class="spacer-5"></div>
    <small>. All rights reserved.</small>
  </footer>-->
</div><!-- End #container -->
</body>
</html>
