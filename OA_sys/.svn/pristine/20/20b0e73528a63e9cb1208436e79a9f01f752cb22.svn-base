<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
  <head>
    
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
			window.location ="${pageContext.request.contextPath }/"+url+"?op=1&id="+id;
		}
	</script>
  </head>
  
	<a  id="a1"  onclick="changeTab('a1','EmployholidayServlet/getEhdByUncheck')" class="hover" style="cursor:pointer; ">请假信息</a>
	 <a id="a2"   onclick="changeTab('a2','EmployoutServlet/getEmoByUncheck')" style="cursor:pointer; ">外出信息</a> 
	  <a id="a3"  onclick="changeTab('a3','EmployovertimeServlet/getEhdByUncheck')" style="cursor:pointer; ">加班信息</a>
	 <a  id="a4"  onclick="changeTab('a4','EmployattendanceServlet/getEhdByUncheck')"  style="cursor:pointer; ">考勤信息</a> 
	 <a  id="a5"  onclick="changeTab('a5','EmploytripServlet/getEhdByUncheck')" style="cursor:pointer; ">出差信息</a> 
   <a  id="a6"  onclick="changeTab('a6','EmployrecordCheckServlet/getEhdByChecked')" style="cursor:pointer; ">档案信息</a> 
  
  

</html>
