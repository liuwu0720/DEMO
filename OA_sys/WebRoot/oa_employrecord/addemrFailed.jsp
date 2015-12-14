<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>员工档案添加</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
  </head>
  <body>
    <div class="rightsider">
		<div class="info_tabs clearfix">
 			<a href="EmployrecordServlet/loadDeptPosition" class="hover">员工档案添加</a> 
 		</div>
 		<div style="width:50%;height:300px;text-align:center;color:red;font-weight:bold;font-size:18px">
 			<h2>${error }</h2>
 		</div>
		<input type="button" value="返回" style="background-color:#ff6600;width:80px" onclick="location.href='<%=basePath%>EmployrecordServlet/getAllEmr'"/>
	</div>
  </body>
</html>