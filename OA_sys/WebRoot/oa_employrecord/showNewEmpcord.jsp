<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>新员工确认</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./css/style.css" />

<!--[if IE 6]><script src="http://stjscn.s-msn.com/portal/wp/DD_belatedPNG.js" type="text/javascript"></script><![endif]-->
<!--[if lt IE 6]>
<link href="skins/ie.css" type="text/css" rel="stylesheet" />
<![endif]-->
<!--[if lt IE 7]>
<link href="skins/ie.css" type="text/css" rel="stylesheet" />
<![endif]-->
<!--[if lt IE 8]>
<link href="skins/ie.css" type="text/css" rel="stylesheet" />
<![endif]-->
<script type="text/javascript">
	function sures(uid)
	{
		var params = document.getElementById("params").value;
		 msg='员工是否确认入职';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmployrecordServlet/updateEmpStaByID?id="+uid;
		  window.location=URL;
		 }
	}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
 
  
</div>
<form action="" method="post"></form></div></div>

   <table  style="margin-top: 100px;">
		<tr align="center">
			<td colspan="4" align="center"> --新员工入职--</td>
		</tr>
		<tr>
			<td width="100px;" bgcolor="#F0F0F0" ><span>姓名</span></td>
			<td width="100px;" bgcolor="#F0F0F0"><span>部门</span></td>
			<td width="100px;" bgcolor="#F0F0F0"><span>岗位</span></td>
			<td width="200px;" bgcolor="#F0F0F0"><span>所做准备</span></td>
			<td width="100px;" bgcolor="#F0F0F0"><span>操作</span></td>
		</tr>
		<c:forEach items="${emplist}" var="emp">
			<tr>
				<td>${emp[1]}</td>
				<td>${emp[2]}</td>
				<td>${emp[3]}</td>
				<td><a href="javascript:sures(${emp[0]});">确认</a></td>
			</tr>
		</c:forEach>
	</table>

	<input type="hidden" value="${params}" id="params" name="params">
</body>
</html>
