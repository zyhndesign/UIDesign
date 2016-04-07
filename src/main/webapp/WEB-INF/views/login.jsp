<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta  charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="webkit" name="renderer">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <meta name="keywords" content="YaLuoYe"/>
    <meta name="description" content="YaLuoYe">
    <title>后台登陆</title>
    <link type="text/css" rel="stylesheet" href="resources/css/src/login.css">
</head>
<body>

<div class="header">
    <a href="" class="logout">
        <span class="span icon-gui"></span><h2 class="logo">GUI</h2>
    </a>
</div>

<form class="pCenter" id="myForm" method="post" action="login">
    <h2 class="title">系统登陆</h2>
    <div class="row">
        <div><label class="ctrlLabel icon-portrait"></label></div>
        <input class="ctrlInput" type="text" name="username" placeholder="请输入你的账号">
    </div>
    <div class="row">
        <div><label  class="ctrlLabel icon-lock"></label></div>
        <input class="ctrlInput" type="password" name="password" placeholder="请输入你的密码"/>
    </div>
    <div class="row">
        <input type="checkbox" checked="checked" id="rememberMe" class="ctrlMemory" value="记住我">记住我
    </div>
    <div class="row">
        <input type="submit" class="ctrlBtn" value="登陆">
    </div>
    <c:if test="${error}">
        <label class="error" style="text-align: center">用户名或者密码错误</label>
    </c:if>
</form>
</body>

<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="resources/js/lib/jquery.form.js"></script>
<script src="resources/js/lib/jquery.validate.min.js"></script>
<script src="resources/js/src/config.js"></script>
<script src="resources/js/src/functions.js"></script>
<script src="resources/js/src/login.js"></script>
</html>