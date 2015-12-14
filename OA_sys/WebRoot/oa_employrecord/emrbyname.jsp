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
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

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
		
		function checkSub()
		{
			var empname = $.trim($("#employname").val());
			if(empname=="")
			{
				alert("请输入要查询的关键词");
				$("#employname").focus();
				return false;
			}
			return true;
		}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
 
  <a href="EmployrecordServlet/getAllEmr" class="hover">员工档案管理</a> 
  
  <a href="EmployrecordServlet/loadDeptPosition" class="usr_add"><i></i>添加</a>
  
</div>
<div class="usr_info_t"><h3>职位信息:</h3>  <div class="search_box clearfix" >
<form action="EmployrecordServlet/getUserByName" method="post" onsubmit=" return checkSub();">
	<button type="submit">搜索<i></i></button>
	<input name="employname" id="employname" type="text" />

</form></div></div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   <li class="name" style="width: 5%">档案编号</li> 
   	 <li class="name" style="width: 10%">员工编号</li>    
     <li class="job" style="width: 10%">员工姓名</li>    
     <li class="name" style="width: 15%">所属公司</li>      
     <li class="phone" style="width: 15%">所属部门</li>
     <li class="area" style="width: 12%">职位</li>
     
     <li class="btn_turn"><span>${page }/${pages }页</span><div><a href="EmployrecordServlet/getAllEmr?page=${pages }&employname='${employname}'"><i class="icon_next"></i></a><a href="EmployrecordServlet/getAllEmr?page=1&employname='${employname}'"><i class="icon_pre"></i></a></div></li>
    
   </ul>
   
   </div>


 <c:forEach items="${list}" var="emr">
   <ul class="usr_info_lit clearfix">
   <li class="name" style="width: 5%">${emr[0] }</li> 
   	 <li class="name" style="width: 10%">${emr[1] }</li> 
     <li class="job"  style="width: 10%">${emr[2] }</li>  
     <li class="name"  style="width: 15%">${emr[3] }</li>      
     <li class="phone" style="width: 15%">${emr[4] }</li>
     <li class="area" style="width: 12%">${emr[5] }</li> 

    <li class="deals" style="width: 28%">
    <a href="javascript:delete_add(${emr[0] })"><i class="icon_del"></i>删除</a>
    <a href="${pageContext.request.contextPath }/EmployrecordServlet/getUpdatePage?id=${emr[0]  }"><i class="icon_corr"></i>修改</a>
    <a href="${pageContext.request.contextPath }/EmployrecordServlet/getEmrById?id=${emr[0] }"><i class="icon_read"></i>查看</a>
    </li>
   </ul>
</c:forEach>

<%@include file="../page.jsp"  %>
</div>
</body>
</html>
