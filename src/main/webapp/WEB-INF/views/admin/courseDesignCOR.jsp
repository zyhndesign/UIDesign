<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
    <!DOCTYPE html>
<html>
<head>
    <jsp:include  page="head.jsp"/>

    <link type="text/css" rel="stylesheet" href="resources/css/lib/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="resources/css/src/main.css">
    <link type="text/css" rel="stylesheet" href="resources/css/lib/jquery.toastmessage.css">
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
                <h1 class="panel-title">新建/修改课程</h1>
            </div>
            <div class="panel-body">

    <c:choose>
    <c:when test="${empty courseDesign}">
    <form class="form-horizontal" id="myForm" action="coursedesign/insert" method="post">
    </c:when>
    <c:otherwise>
    <form class="form-horizontal" id="myForm" action="coursedesign/update/${courseDesign.id}" method="post">
    </c:otherwise>
    </c:choose>

                    <div class="form-group">
                        <label  class="control-label col-md-2">标题*</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="${courseDesign.title}" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">教师*</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="${courseDesign.teacher}" name="teacher">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">是否精选*</label>
                        <div class="col-md-8">
                            <select class="form-control" name="topTag">
        <%
            List<HashMap> topTags=new ArrayList<HashMap>();
            topTags.add(new HashMap(Integer,String){
                {
                put(0,"否");
                put(1,"是");
                }
            });
            pageContext.setAttribute("topTags", topTags);
        %>
                            <c:forEach items="${topTags}" var="topTag">
                                <c:choose>
                                <c:when test="${courseDesign.topTag==topTag.key}">
                                <option value="${topTag.key}" selected="selected">${topTag.value}</option>
                                </c:when>
                                <c:otherwise>
                                <option value="${topTag.key}">${topTag.value}</option>
                                </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">标签*</label>
                        <div class="col-md-8">
                            <div id="tags" class="tags">
                                <c:forEach items="${courseDesign.courseTagList}" var="tag">
                                    <span class="tag" data-tag-id="${tag.id}">${tag.tagName}</span>
                                </c:forEach>
                            </div>
                            <input type="text" class="form-control" id="tagInput">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">创建时间*</label>
                        <div class="col-md-8">
                            <input type="date" class="form-control" value="${courseDesign.createTime}" name="createTime">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">图钉墙ID*</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="${courseDesign.courseDetailId}" name="courseDetailId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="control-label col-md-2">描述*</label>
                        <div class="col-md-8">
                            <textarea class="form-control"  name="abstract_" rows="3" id="abstract">${courseDesign.abstract_}</textarea>
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
<script src="resources/js/src/courseDesignCOR.js"></script>

</body>
</html>