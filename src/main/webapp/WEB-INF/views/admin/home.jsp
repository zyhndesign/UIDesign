<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理</title>
    <link type="text/css" rel="stylesheet" href="../resources/css/lib/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="../resources/css/src/main.css">


</head>
<body>

<jsp:include  page="header.jsp"/>

<div class="left">
    <jsp:include  page="menu.jsp"/>
</div>

<div class="right">
    <div class="main">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title">欢迎</h1>
            </div>
            <div class="panel-body">
                <h1 class="text-success text-center">欢迎使用管理系统</h1>
            </div>
        </div>
    </div>
</div>


<div class="loading hidden" id="loading">
    <span class="text">Loading...</span>
</div>

</body>
</html>

