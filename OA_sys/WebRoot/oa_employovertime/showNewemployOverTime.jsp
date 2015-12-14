<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>新信息确认</title>
    
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
	function hoysures(id)
		{
			
			if(confirm("加班是否确认")) 
			{
            	window.location="${pageContext.request.contextPath }/EmployovertimeServlet/updateEmpOverTimeSatByID?id="+id;
		  		
        	}
		}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
  
</div>
<div class="usr_info_t"><h3></h3>  <div class="search_box clearfix">
<form action="" method="post"></form></div></div>

   <table style="margin-top: 100px;">
						<tr align="center">
							<td colspan="4" align="center"> --外出--</td>
						</tr>
						<tr>
							<td width="100px;" bgcolor="#F0F0F0"><span>姓名</span></td>
							<td width="100px;" bgcolor="#F0F0F0"><span>部门</span></td>
							<td width="100px;" bgcolor="#F0F0F0"><span>岗位</span></td>
							<td width="100px;" bgcolor="#F0F0F0"><span>原因</span></td>
							<td width="100px;" bgcolor="#F0F0F0"><span>内容</span></td>
							<td width="100px;" bgcolor="#F0F0F0"><span>类型</span></td>
							<td width="200px;" bgcolor="#F0F0F0"><span>开始时间</span></td>
							<td width="200px;" bgcolor="#F0F0F0"><span>结束时间</span></td>
							<td width="100px;" bgcolor="#F0F0F0"><span>操作</span></td>
						</tr>
						<c:forEach items="${holidaylist}" var="hoday">
							<tr>
								<td>${hoday[1]}</td>
								<td>${hoday[2]}</td>
								<td>${hoday[3]}</td>
								<td>${hoday[6]}</td>
								<td>${hoday[7]}</td>
								<td>${hoday[8]}</td>
								<td>${hoday[4]}</td>
								<td>${hoday[5]}</td>
								<td><a href="javascript:hoysures(${hoday[0]});">确认</a></td>
							</tr>
						</c:forEach>
				</table>

</div>
</body>
</html>
