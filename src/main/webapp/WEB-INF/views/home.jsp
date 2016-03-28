<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../resources/javascript/libs/jquery-2.2.0.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#selectCourseDesign').click(function(){
			 $.ajax({
	                type:'GET',
	                url:'/json',
	                dataType:'json',
	                data: {},
	                success:function(data){
	                   
	                }
	            });
		});
		
		$('#updateCourseDesign').click(function(){
			$.ajax({ 
		           type: "post", 
		           url: "Default.aspx", 
		           dataType: "json", 
		           data: {},
		           success: function (data) { 
		                 
		           }, 
		           error: function (XMLHttpRequest, textStatus, errorThrown) { 
		                alert(errorThrown); 
		           } 
		     });
		});
		
		$('#deleteCourseDesign').click(function(){
			 $.ajax({
	                type:'DELETE',
	                url:'/json',
	                dataType:'json',
	                data: {id:6},
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

<input type="button" id="selectCourseDesign" name="Select Course Design">
<br>
<input type="button" id="updateCourseDesign" name="Update Course Design">
<br>
<input type="button" id="deleteCourseDesign" name="Delete Course Design">

</body>
</html>
