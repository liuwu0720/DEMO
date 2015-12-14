<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
			var id = "${param.id}";
			if(null!=id  && ""!=id){
			
				var temp = $("#"+id);
				temp.siblings().removeAttr("class");
				temp.attr("class","hover");
			}
	});
	
		function changeTab(id,url)
		{
			window.location ="${pageContext.request.contextPath }/"+url+"&id="+id;
		}
	</script>
  </head>
  
  
    <a  id="a1" onclick="changeTab('a1','EmployholidayServlet/getAllEhd?op=1')" class="hover" style="cursor:pointer; ">请假管理</a> 
  <a  id="a2" onclick="changeTab('a2','EmployoutServlet/getAllEmo?op=1')" style="cursor:pointer; ">外出管理</a>
  <a id="a3" onclick="changeTab('a3','EmployovertimeServlet/getEhdByIscheck?ischeck=0')" style="cursor:pointer; ">加班管理</a>
   <a id="a4" onclick="changeTab('a4','EmployattendanceServlet/getEhdByIscheck?ischeck=0')" style="cursor:pointer; " >考勤管理</a> 
    <a id="a5" onclick="changeTab('a5','EmploytripServlet/getEhdByIscheck?ischeck=0')" style="cursor:pointer; ">出差管理</a> 
  <a id="a6" onclick="changeTab('a6','EmployrecordCheckServlet/getEhdByIscheck?ischeck=0')"  style="cursor:pointer; ">档案管理</a> 

</html>
