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
    
    <title>用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="css/style.css" />

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
		 msg='确认要删除该用户么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/UserServlet/deleteUserById?uid="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
 
  <a href="UserServlet/getAllUsers" class="hover">用户管理</a> 
  
  <a href="EmployrecordServlet/getAllEmrUser" class="usr_add"><i></i>添加</a>
  
</div>
<div class="usr_info_t"><h3>员工筛选：<a href="UserServlet/getUserInfo?admin=0">普通员工</a>  <a href="UserServlet/getUserInfo?admin=1">部门领导</a>  <a href="UserServlet/getUserInfo?admin=2">公司领导</a></h3>  <div class="search_box clearfix">
		<form action="UserServlet/getUserByOther" method="post">
		<button type="submit">搜索<i></i></button><input name="id" type="text" value="搜索信息（员工编号）" onclick="this.value=''"/>
		</form></div></div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   
     <li class="name">档案号</li>      
     <li class="phone">员工编号</li>    
     <li class="area">职位</li>
     <li class="limit" style="width: 10%">角色ID</li>        
     <li class="shop">是否有效</li>
     <li class="house" style="width: 19%">密码最后修改时间</li>
 
     <li class="btn_turn"><span>${page }/${pages }页</span><div><a href="UserServlet/getUserInfo?page=${pages }&admin=${admin}"><i class="icon_next"></i></a><a href="UserServlet/getUserInfo?page=1&admin=${admin}"><i class="icon_pre"></i></a></div></li>
    
   </ul>
   </div>


<c:forEach items="${user}" var="users">
   <ul class="usr_info_lit clearfix">
   
     <li class="name">${users[1]}</li>      
     <li class="phone">${users[2] }</li>   
     <li class="area">
     	<c:if test="${users[3]==0 }">普通员工</c:if>
     	<c:if test="${users[3]==1 }">部门领导</c:if>
     	<c:if test="${users[3]==2 }">公司领导</c:if>
     </li>
     <li class="limit" style="width: 11%">${users[6] }</li>        
     <li class="shop">
     	<c:if test="${users[4]==0 }">无效</c:if>
     	<c:if test="${users[4]==1 }">有效</c:if>
     </li>
     <li class="house">${users[5] }</li>
    <li class="deals" style="width: 15%"><a href="${pageContext.request.contextPath }/UserServlet/getUpdateUserPage?uid=${users[0] }"><i class="icon_corr"></i>修改</a><a href="javascript:delete_add(${users[0] })"><i class="icon_del"></i>删除</a></li>
    
   </ul>
</c:forEach>

<%@include file="page.jsp"  %>
</div>
</body>
</html>
