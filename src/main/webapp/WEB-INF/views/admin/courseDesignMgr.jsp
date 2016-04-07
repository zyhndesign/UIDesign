<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="webkit" name="renderer">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <title>xxxx后台管理</title>

    <link href="../../../resources/css/lib/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../resources/css/lib/jquery.dataTables.css" rel="stylesheet" type="text/css">
    <link href="../../../resources/css/lib/jquery.toastmessage.css" rel="stylesheet" type="text/css">
    <link href="../../../resources/css/src/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<script src="header.js"></script>

<nav class="left">

    <script src="menu.js"></script>

</nav>

<section class="right">
    <article class="main">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title">课程管理</h1>
            </div>
            <div class="panel-body" id="opt-body">
                <a class="btn btn-success" href="courseCOR.html">
                    <span class="glyphicon glyphicon-plus"></span> 新建
                </a>
                <div class="input-group tableSearchContainer col-md-6">
                    <input type="text" id="searchContent" class="form-control" placeholder="内容">
                    <span class="input-group-btn">
                        <button id="searchBtn" class="btn btn-default" type="button">搜索</button>
                    </span>
                </div>
                <table id="myTable" class="dataTable">
                    <thead>
                    <tr>
                        <th>标题</th>
                        <th>教师</th>
                        <th>创建时间</th>
                        <th>土钉墙ID</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </article>
</section>



<div class="loading hidden" id="loading">
    <span class="text">Loading...</span>
</div>


<script src="../../../resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="../../../resources/js/lib/bootstrap.min.js"></script>
<script src="../../../resources/js/lib/jquery.dataTables.min.js"></script>
<script src="../../../resources/js/lib/jquery.validate.min.js"></script>
<script src="../../../resources/js/lib/jquery.toastmessage.js"></script>
<script src="../../../resources/js/src/config.js"></script>
<script src="../../../resources/js/src/functions.js"></script>
<script src="../../../resources/js/src/courseMgr.js"></script>

</body>
</html>