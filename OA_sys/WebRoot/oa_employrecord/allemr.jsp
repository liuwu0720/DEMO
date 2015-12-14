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
<body style="overflow: auto;overflow-y:hidden;overflow-x:scroll;">
<div class="rightsider" style="width: 1100px;">

<div class="info_tabs clearfix">
  
 
  <a href="EmployrecordServlet/getAllEmr" class="hover">员工档案管理</a> 
  
  <a href="EmployrecordServlet/loadDeptPosition" class="usr_add"><i></i>添加</a>
  
</div>
<div class="usr_info_t"><h3>职位信息:</h3>  <div class="search_box clearfix" >

<form action="EmployrecordServlet/getUserByName" method="post" onsubmit=" return checkSub();">
	 <label >员工编号/姓名/岗位</label> <button type="submit">搜索<i></i></button><input name="employname" id="employname" type="text" /><br/>

</form>
			</div></div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   <li class="name"  style="width: 100px;">用户名</li> 
   	 <li class="name" style="width: 100px;">员工编号</li>    
     <li class="job" style="width: 100px;">员工姓名</li>    
     <li class="name" style="width: 150px;">所属公司</li>      
     <li class="phone" style="width: 150px;">所属部门</li>
     <li class="name" style="width: 100px;">职位</li>
      <li class="name" style="width: 260px;"></li>
     
   
   </ul>
   
   </div>


   <c:forEach items="${list}" var="emr">
   <ul class="usr_info_lit clearfix">
   <li class="name" style="width: 100px;">${emr[1] }</li> 
   	 <li class="name" style="width: 100px;">${emr[2] }</li> 
     <li class="job"  style="width: 100px;">${emr[3] }</li>  
     <li class="name"  style="width: 150px;">${emr[4] }</li>      
     <li class="phone" style="width: 150px;">${emr[5] }</li>
     <li class="name" style="width: 100px;">${emr[6] }</li> 

    <li class="name" style="width: 260px;float: left;margin-left: 20px;">
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
