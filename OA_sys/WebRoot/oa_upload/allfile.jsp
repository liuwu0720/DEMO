<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>部门信息</title>
    
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
	function delete_add(uid,uname,path)
		{
		 msg='确认要删除该文件么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/UploadServlet/del?id="+uid+"&filename="+uname+"&filepath="+path;
		  window.location=URL;
		 }
		}

</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
  <a href="UploadServlet/getAllFile" class="hover">文件下载</a>
  <a href="oa_upload/upload.jsp" >文件上传</a> 
 
  

</div>



<dl class="notice clearfix">
      
  <dd>文件管理 &gt; 文件下载</dd>   
             
</dl>

   <div class="usr_info_cols clearfix">
   
   <ul>
   
     <li class="name" style="width: 40%">文件名</li>      
     <li class="phone" style="width: 20%">上传时间 </li>  

    	 
 
     <li class="btn_turn"><span>${page }/${pages }页</span><div><a href="UploadServlet/getAllFile?page=${pages }"><i class="icon_next"></i></a><a href="UploadServlet/getAllFile?page=1"><i class="icon_pre"></i></a></div></li>
    
   </ul>
   
   </div>


<c:forEach items="${sm}" var="sm">
   <ul class="usr_info_lit clearfix">

     <li class="name" style="width: 40%">${sm.filename }</li>      
     <li class="phone" style="width: 20%">${fn:substring(sm.currdate,0,19) } </li> 
    <li class="deals" >
    <a href="javascript:delete_add(${sm.lineid },'${sm.filename }','${sm.filepath }')"><i class="icon_del"></i>删除</a>
    <a href="${pageContext.request.contextPath }/UploadServlet/dowfilebyname?filename=${sm.filename }"><i class="icon_read"></i>下载</a>
     
   </ul>
</c:forEach>

<%@include file="../page.jsp"  %>
</div>
</body>
</html>
