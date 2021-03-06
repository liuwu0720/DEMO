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
     <li class="house" style="width: 15%">部门邮箱</li>
     <li class="job" style="width: 15%">所属公司</li>
     <li class="shop" >是否有效</li>
    	 
 
     <li class="btn_turn"><span>${page }/${pages }页</span><div><a href="DeptServlet/getAllDeptByPage&page=${pages }"><i class="icon_next"></i></a><a href="DeptServlet/getAllDeptByPage&page=1"><i class="icon_pre"></i></a></div></li>
    
   </ul>
   
   </div>


<c:forEach items="${dept}" var="users">
   <ul class="usr_info_lit clearfix">
     <li class="name">${users[0] }</li>      
     <li class="phone">${users[1] }</li>   
     <li class="area">${users[2] }</li>
     <li class="limit">${users[3] }</li>        
     <li class="house" style="width: 15%">${users[4] }</li>
     <li class="job" style="width: 15%">${users[5]}</li>
     <li class="shop" style="width: 5%">
     	<c:if test="${users[6]==0 }">无效</c:if>
     	<c:if test="${users[6]==1 }">有效</c:if>
     </li>
    <li class="deals" style="width: 15%"><a href="${pageContext.request.contextPath }/DeptServlet/getUpdateDeptPage?uid=${users[0] }"><i class="icon_corr"></i>修改</a>
    <a href="javascript:delete_add(${users[0] })"><i class="icon_del"></i>删除</a></li>
    
   </ul>
</c:forEach>

<%@include file="../page.jsp"  %>
</div>
</body>
</html>
