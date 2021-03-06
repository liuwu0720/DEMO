<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
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
	
	function checksub()
	{
		var usname = $("#usname").val();
		if($.trim(usname)=="")
		{
			alert("请输入姓名.");
			return false;
		}else
		{
			$("#myform").submit();	
		}
		
	}
</script>

</head>
<body>
<div class="rightsider" style="width: 1100px;">

<div class="info_tabs clearfix">
  
 
  <a href="UserServlet/getAllUsers" class="hover">用户管理</a> 
  
  <a href="EmployrecordServlet/getAllEmrUser" class="usr_add"><i></i>添加</a>
  
</div>
<div class="usr_info_t">
	<!-- <h3>员工筛选：<a href="UserServlet/getUserInfo?admin=0">普通员工</a>  <a href="UserServlet/getUserInfo?admin=1">部门领导</a>  <a href="UserServlet/getUserInfo?admin=2">公司领导</a></h3> -->
	 <div class="search_box clearfix"> 
		<form action="UserServlet/getUserByOther" method="post" id="myform">
		<button type="button" onclick="checksub();">搜索<i></i></button> <input name="usname" type="text" style="width: 150px;" id="usname" /> &nbsp;姓名:&nbsp;
		</form></div></div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   
     <li class="date1">档案号</li>      
     <li class="date1">员工编号</li>    
     <li class="date2">姓名</li>    
     <li class="date3">部门</li>      
     <li class="date2">岗位</li>    
     <li class="date2">是否有效</li>
     <li class="date3" >密码最后修改时间</li>
 	 <li class="date3" >操作</li>
 	
    
   </ul>
   </div>


<c:forEach items="${user}" var="users">
   <ul class="usr_info_lit clearfix">
   
     <li class="date1">${users[1]}</li>      
     <li class="date1">${users[2]}</li>     
     <li class="date2">${users[3]}</li>   
     <li class="date2" >${users[4] }</li>        
     <li class="date3">
	     	${users[5] }
     </li>
      <li class="date2">
	     	
	     	<c:choose>
	     		<c:when test="${users[7]==0 }">
	     			无效
	     		</c:when>
	     		<c:otherwise>
	     			有效
	     		</c:otherwise>
	     	</c:choose>
     </li>
     <li class="date3"> <fmt:formatDate value="${users[6] }" pattern="yyyy/MM/dd  HH:mm:ss"/> </li>
    <li class="date3" style="width: 160px;">
    <a href="${pageContext.request.contextPath }/UserServlet/getUpdateUserPage?uid=${users[0] }"><i class="icon_corr"></i>修改</a>
     <a href="javascript:delete_add(${users[0] })"><i class="icon_del"></i>删除</a>
   <!--  <a href="${pageContext.request.contextPath }/PrivilegeServlet/getAllPr?id=${users[0] }"><i class="icon_read"></i>分配权限</a>-->
    </li>
    
   </ul>
</c:forEach>

<%@include file="page.jsp"  %>
</div>
</body>
</html>
