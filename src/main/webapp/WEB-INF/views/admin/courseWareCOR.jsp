<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include  page="head.jsp"/>

    <link type="text/css" rel="stylesheet" href="../../../resources/css/lib/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="../../../resources/css/lib/jquery.toastmessage.css">
    <link type="text/css" rel="stylesheet" href="../../../resources/css/src/main.css">
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
                <h1 class="panel-title">新建/修改课件</h1>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" id="myForm" action="#" method="post">

                    <input type="hidden" name="id" value="1">
                    <div class="form-group">
                        <label class="control-label col-md-2">封面图*</label>
                        <div class="col-md-10" id="uploadContainer">
                            <a href="#" class="btn btn-success" id="uploadBtn">上传</a>
                            <p class="help-block">请上传500x500的jpg，png</p>
                            <img  id="image"  style="width:100px"
                                  src="../../../resources/images/app/defaultPeopleImage.jpg"/>
                            <input type="hidden" id="imageUrl" name="thumbnail">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">标题*</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">作者*</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="" name="author">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">创建时间*</label>
                        <div class="col-md-8">
                            <input type="date" class="form-control" value="" name="create_time">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">内容*</label>
                        <div class="col-md-8">
                            <textarea class="form-control"  name="content" rows="3" id="content"></textarea>
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

<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="resources/js/lib/jquery.form.js"></script>
<script src="resources/js/lib/jquery.validate.min.js"></script>
<script src="resources/js/lib/plupload.full.min.js"></script>
<script src="resources/js/lib/qiniu.js"></script>
<script src="resources/js/lib/tinymce.min.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/js/src/config.js"></script>
<script src="resources/js/src/functions.js"></script>
<script src="resources/js/src/courseWareCOR.js"></script>

</body>
</html>