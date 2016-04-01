<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/javascript/libs/jquery-2.2.0.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#selectCourseDesign').click(function(){
			 $.ajax({
	                type:'GET',
	                url:'coursedesign/select/3',
	                dataType:'json',
	                data: {},
	                success:function(data){
	                   alert(data);
	                }
	            });
		});
		
		$('#updateCourseDesign').click(function(){
			$.ajax({ 
		           type: 'post', 
		           url: 'coursedesign/update/3',
		           dataType:'json',
		           data: {title:'图形界面设计',abstract_:'湖南大学设计艺术学院',teacher:'jack'},
		           success: function (data) { 
		                 
		           }, 
		           error: function (XMLHttpRequest, textStatus, errorThrown) { 
		        	   alert(textStatus);
		               alert(errorThrown); 
		           } 
		     });
		});
		
		$('#deleteCourseDesign').click(function(){
			 $.ajax({
	                type:'DELETE',
	                url:'/delete/5',
	                dataType:'json',
	                data: {},
	                success:function(data){
	                   
	                }
	            });
		});
	});
</script>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<input type="button" id="selectCourseDesign" value="Select Course Design">
<br/>
<br/>
<input type="button" id="updateCourseDesign" value="Update Course Design">
<br/>
<br/>
<input type="button" id="deleteCourseDesign" value="Delete Course Design">

</body>
</html>
