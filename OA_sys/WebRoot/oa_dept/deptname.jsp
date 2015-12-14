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
    
    <title>部门信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
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
		 msg='确认要删除该部门么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/DeptServlet/del?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
 
  <a href="DeptServlet/getAllDeptByPage" class="hover">部门管理</a> 
  
  <a href="DeptServlet/getAddpage" class="usr_add"><i></i>添加</a>
  
</div>
<div class="htsp">
<span>
	<a href="DeptServlet/getDeptTree" >部门信息</a>&nbsp;&nbsp;
	<a href="DeptServlet/getAllDeptByPage" class="hover">全部部门信息</a>
   <div class="search_box clearfix" style="margin-top: -35px">
<form action="DeptServlet/getDeptByName" method="post"><button type="submit">搜索<i></i></button><input style="width:150px " name="deptname" type="text" value="搜索信息（部门名称）" onFocus="if(this.value=='搜索信息（部门名称）'){this.value=''}" onblur="if(this.value==''){this.value='搜索信息（部门名称）'}"/></form>
</div>
 </span>
</div>
  
   <div class="usr_info_cols clearfix">
   
  <ul>
   
     <li class="name">部门编号</li>      
     <li class="phone">部门名称</li>    
     <li class="area">联系人</li>
     <li class="limit">联系电话</li>        
     <li class="house" style="width: 14%">部门邮箱</li>
      
   </ul>
   
   </div>


<c:forEach items="${dept}" var="dept">
   <ul class="usr_info_lit clearfix">
   <li class="name">${dept.lineid }</li>      
     <li class="phone">${dept.deptname }</li>   
     <li class="area">${dept.manageruser.employrecord.employname }</li>
     <li class="limit">${dept.manageruser.employrecord.mobile }</li>        
     <li class="house" style="width: 15%">${dept.manageruser.employrecord.email}</li>
   
    <li class="deals" style="width: 15%"><a href="${pageContext.request.contextPath }/DeptServlet/getUpdateDeptPage?uid=${dept.lineid }"><i class="icon_corr"></i>修改</a>
    <a href="javascript:delete_add(${dept.lineid })"><i class="icon_del"></i>删除</a></li>
    </ul>
</c:forEach>

</div>
</body>
</html>
