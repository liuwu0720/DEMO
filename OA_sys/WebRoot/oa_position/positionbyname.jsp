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
    
    <title>职位信息</title>
    
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
	function delete_add(uid)
		{
		 msg='确认要删除该职位么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/PositionServlet/del?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
 
  <a href="PositionServlet/getAllPoByPage" class="hover">职位管理</a> 
  
  <a href="oa_position/addposition.jsp" class="usr_add"><i></i>添加</a>
  
</div>
<div class="usr_info_t"><h3>职位信息:</h3>  <div class="search_box clearfix">
<form action="PositionServlet/getInfoByName" method="post"><button type="submit">搜索<i></i></button><input name="positionname" type="text" value="搜索信息（职位名称）" onFocus="if(this.value=='搜索信息（职位名称）'){this.value=''}" onblur="if(this.value==''){this.value='搜索信息（职位名称）'}"/></form></div></div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   	 <li class="name" style="width: 10%">&nbsp;</li>    
     <li class="name" style="width: 20%">职位编号</li>      
     <li class="phone" style="width: 20%">职位名称</li>    

 	<li class="btn_turn"><span>${page }/${pages }页</span><div><a href="PositionServlet/getInfoByName?page=${pages }&positionname=${positionname}"><i class="icon_next"></i></a><a href="PositionServlet/getInfoByName?page=1&positionname=${positionname}"><i class="icon_pre"></i></a></div></li>
    
   </ul>
   
   </div>


   <c:forEach items="${position}" var="position">
   <ul class="usr_info_lit clearfix">
   	 <li class="name" style="width: 10%">&nbsp;</li> 
     <li class="name" style="width: 20%" >${position.lineid }</li>      
     <li class="phone" style="width: 20%">${position.positionname }</li>   

    <li class="deals"><a href="${pageContext.request.contextPath }/PositionServlet/getUpdatePage?id=${position.lineid }"><i class="icon_corr"></i>修改</a>
    <a href="javascript:delete_add(${position.lineid })"><i class="icon_del"></i>删除</a></li>
   </ul>
</c:forEach>


<%@include file="../page.jsp"  %>

</div>
</body>
</html>
