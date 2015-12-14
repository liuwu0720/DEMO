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
    
    <title>员工档案信息</title>
    
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


</head>
<body>
<div class="rightsider">

	<div class="info_tabs clearfix">
	 <a href="EmployrecordServlet/getAllEmr" class="hover">修改信息</a> 
	</div>

	<dl class="notice clearfix">
	      
	  <dd>修改照片  </dd>   
	             
	</dl>


<ul class="qy_info"></ul>
您可以在此上传您的照片，将它转成图形格式（目前只支持jpg格式），我们暂时不支持其他类型的附件，大小请不要超过500K，建议您使用一寸证件照70*100像素。（*为必填项）
<br><br><br>
<form action="EmployrecordServlet/addphoto?id=${param.id }" enctype="multipart/form-data" method="post">
	    	上传照片：<input type="file" name="file"/><br><br><br>
	    	<button type="submit" style="width: 100px;height: 30px" class="tj add_btn">上传</button>
	    	</form>
	    	</div>
</body>
</html>
