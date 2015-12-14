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
     <li class="phone" style="width: 12%">所属部门</li>
     <li class="area" style="width: 12%">职位</li>
     
     <li class="btn_turn"><span>${page }/${pages }页</span><div><a href="EmployrecordServlet/addUserByName?page=${pages }&employname='${employname}'"><i class="icon_next"></i></a><a href="EmployrecordServlet/addUserByName?page=1&employname='${employname}'"><i class="icon_pre"></i></a></div></li>
    
   </ul>
   
   </div>


   <c:forEach items="${emr}" var="emr">
   <ul class="usr_info_lit clearfix">
   <li class="name" style="width: 10%">${emr[0] }</li> 
   	 <li class="name" style="width: 12%">${emr[1] }</li> 
     <li class="job"  style="width: 12%">${emr[2] }</li>      
     <li class="phone" style="width: 12%">${emr[3] }</li>
     <li class="area" style="width: 12%">${emr[4] }</li> 

    <li class="deals" style="width: 40%">
    <a href="addusers.jsp?recordid=${emr[0] }&userno=${emr[1] }"><i class="icon_corr"></i>新增账户</a>
    </li>
   </ul>
</c:forEach>


<div class="fy_btns clearfix">
    <form action="EmployrecordServlet/addUserByName?employname='${employname}'" method="post">
   
      <span style="float:right;">
      <c:if test="${page!=1}">
      <a href="EmployrecordServlet/addUserByName?page=${page-1 }&employname='${employname}'"><i class="icon_pre"></i>上一页</a>  
   		</c:if>
   		
     <c:if test="${pages<=5}">
     <c:forEach begin="1" end="${pages}" var="a">
     <a href="EmployrecordServlet/addUserByName?page=${a }&employname='${employname}'">${a }</a>
     </c:forEach>
     </c:if>
     
     <c:if test="${pages>5}">
	     <c:if test="${page<3}">
	     <c:forEach begin="1" end="5" var="a">
	     <a href="EmployrecordServlet/addUserByName?page=${a }&employname='${employname}'">${a }</a>
	     </c:forEach>
	     </c:if>
     	<c:if test="${page<pages-1}">
     		<c:if test="${page>=3}">
		     <c:forEach begin="${page-2}" end="${page+2}" var="a">
		     <a href="EmployrecordServlet/addUserByName?page=${a }&employname='${employname}'">${a }</a>
		     </c:forEach>
     		</c:if>
     	</c:if>
     	<c:if test="${page==pages-1}">
     		<c:if test="${page>3}">
		     <c:forEach begin="${page-3}" end="${pages}" var="a">
		     <a href="EmployrecordServlet/addUserByName?page=${a }&employname='${employname}'">${a }</a>
		     </c:forEach>
     		</c:if>
     	</c:if>
     	<c:if test="${page==pages}">
     		<c:if test="${page>4}">
		     <c:forEach begin="${page-4}" end="${pages}" var="a">
		     <a href="EmployrecordServlet/addUserByName?page=${a }&employname='${employname}'">${a }</a>
		     </c:forEach>
     		</c:if>
     	</c:if>
     
     </c:if>
     <c:if test="${page!=pages}">
      <a href="EmployrecordServlet/addUserByName?page=${page+1 }&employname='${employname}'" style="width: 100"><i class="icon_next"></i>下一页</a>
     </c:if>
      <input name="page" type="text" class="fynum"><button type="submit" class="turnto">GO</button>
      
      </span>
      </form>
    
</div>
</div>
</body>
</html>
