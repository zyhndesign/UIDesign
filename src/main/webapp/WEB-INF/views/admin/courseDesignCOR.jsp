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
    <title>xxxx管理系统</title>

    <link type="text/css" rel="stylesheet" href="../../../resources/css/lib/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="../../../resources/css/src/main.css">
    <link type="text/css" rel="stylesheet" href="../../../resources/css/lib/jquery.toastmessage.css">
</head>
<body>

<script src="header.js"></script>

<div class="left">
    <script src="menu.js"></script>
</div>

<div class="right">
    <div class="main">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title">新建/修改课程</h1>
            </div>
            <div class="panel-body">

                <form class="form-horizontal" id="myForm" action="coursedesign/insert" method="post">

                    <div class="form-group">
                        <label  class="control-label col-md-2">标题*</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">教师*</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="" name="teacher">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">是否精选*</label>
                        <div class="col-md-8">
                            <select class="form-control" name="topTag">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">标签*</label>
                        <div class="col-md-8">
                            <div style="margin-bottom: 5px" id="tags">

                            </div>
                            <input type="text" class="form-control" id="tagInput">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">创建时间*</label>
                        <div class="col-md-8">
                            <input type="date" class="form-control" value="" name="createTime">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">图钉墙ID*</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="" name="courseDetailId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">描述*</label>
                        <div class="col-md-8">
                            <textarea class="form-control"  name="abstract_" rows="3" id="abstract"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-8">
                            <button type="submit" class="btn btn-success form-control">确定</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="loading hidden" id="loading">
    <span class="text">Loading...</span>
</div>

<script src="../../../resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="../../../resources/js/lib/jquery.form.js"></script>
<script src="../../../resources/js/lib/jquery.validate.min.js"></script>
<script src="../../../resources/js/lib/plupload.full.min.js"></script>
<script src="../../../resources/js/lib/qiniu.js"></script>
<script src="../../../resources/js/lib/tinymce.min.js"></script>
<script src="../../../resources/js/lib/jquery.toastmessage.js"></script>
<script src="../../../resources/js/src/config.js"></script>
<script src="../../../resources/js/src/functions.js"></script>
<script src="../../../resources/js/src/courseCOR.js"></script>

</body>
</html>