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
<script type="text/javascript">
		function delete_add(uid)
		{
		 msg='确认要删除该员工么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmployrecordServlet/del?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
 
  <a href="EmployrecordServlet/getAllEmrUser" class="hover">员工账户管理</a> 
  
</div>
<div class="usr_info_t"><h3>职位信息:</h3>  <div class="search_box clearfix" >
<form action="EmployrecordServlet/addUserByName" method="post"><button type="submit">搜索<i></i></button><input name="employname" type="text" value="搜索信息（员工姓名）"  onFocus="if(this.value=='搜索信息（员工姓名）'){this.value=''}" onblur="if(this.value==''){this.value='搜索信息（员工姓名）'}" />
</form></div></div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   <li class="name" style="width: 10%">档案编号</li> 
   	 <li class="name" style="width: 12%">员工编号</li>    
     <li class="job" style="width: 12%">员工姓名</li>      
     <li class="phone" style="width: 37%">所属部门</li>
     <li class="area" style="width: 12%">职位</li>
     
     <li class="btn_turn"><span>${page }/${pages }页</span><div><a href="EmployrecordServlet/getAllEmrUser?page=${pages }"><i class="icon_next"></i></a><a href="EmployrecordServlet/getAllEmrUser?page=1"><i class="icon_pre"></i></a></div></li>
    
   </ul>
   
   </div>


   <c:forEach items="${list}" var="emr">
   <ul class="usr_info_lit clearfix">
   <li class="name" style="width: 10%">${emr[0] }</li> 
   	 <li class="name" style="width: 12%">${emr[1] }</li> 
     <li class="job"  style="width: 12%">${emr[2] }</li>      
     <li class="phone" style="width: 37%">${emr[3] }</li>
     <li class="area" style="width: 12%">${emr[4] }</li> 

    <li class="deals" style="width: 15%">
    <a href="addusers.jsp?recordid=${emr[0] }&userno=${emr[1] }"><i class="icon_corr"></i>新增账户</a>
    </li>
   </ul>
</c:forEach>

<%@include file="../page.jsp"  %>
</div>
</body>
</html>
