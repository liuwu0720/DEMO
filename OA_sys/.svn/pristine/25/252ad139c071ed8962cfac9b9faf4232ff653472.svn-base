<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object obj = request.getSession().getAttribute( "lineid" );
if(obj==null)
{
	response.sendRedirect("login.jsp");
}

%>


<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<base href="<%=basePath%>">
<title>欢迎使用中联物流OA办公系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<frameset rows="80,*" cols="*" frameborder="no" border="0"
	framespacing="0">

	<frame src="<%=basePath%>top.jsp" name="topFrame" scrolling="No"
		noresize="noresize" id="topFrame" title="topFrame" />

	<frameset cols="250,*" frameborder="no" border="0" framespacing="0">
		<frame src="<%=basePath%>ModuleServlet/goLef" name="leftFrame"
			scrolling="auto" noresize="" id="leftFrame" title="leftFrame" />
		<frame src="<%=basePath%>ModuleServlet/gorightBefore" name="mainFrame" id="mainFrame"
			title="mainFrame" scrolling="auto"/>
	</frameset>


</frameset>

<noframes>
	<body>
	</body>
</noframes>
</html>
