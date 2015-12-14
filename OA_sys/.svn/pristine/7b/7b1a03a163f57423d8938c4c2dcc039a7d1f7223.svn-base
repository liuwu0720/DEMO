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
		 msg='确认要删除该条请假条么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmployrecruitmentServlet/del?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
 
  <a href="EmployrecruitmentServlet/getEhdByIscheck?ischeck=0">招聘管理</a> 
  
  
</div>
  <div class="htsp">

	 <span><a href="EmployrecruitmentServlet/getEhdByIscheck?ischeck=0" >未审批</a> <a href="EmployrecruitmentServlet/getEhdByIscheck?ischeck=1">未通过</a>  <a href="EmployrecruitmentServlet/getEhdByIscheck?ischeck=2">已通过</a></span>
     

  </div>

 <div class="htban_info_cols clearfix">
   
   <ul>
       
     <li class="dealer" style="width: 10%">申请人 </li>     
     <li class="dealer" style="width: 10%">申请人部门 </li>     
     <li class="price" style="width: 12%">申请岗位</li>    
     <li class="price" style="width: 6%">人数</li> 
     <li class="date" style="width: 10%">申请理由</li> 
     <li class="pass" style="width: 6%">状态</li>  
     <li class="pass" style="width: 10%">审核备注</li>  
     
     
     <li class="btn_turn"><span>${page }/${pages }页</span><div><a href="EmployholidayServlet/getAllEhd?page=${pages }"><i class="icon_next"></i></a><a href="EmployholidayServlet/getAllEhd?page=1"><i class="icon_pre"></i></a></div></li>
    
   </ul>
  
   </div>


   <c:forEach items="${emplist}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
       
     <li class="ht_name" style="width: 10%">${ehd[1]} </li>  
     <li class="ht_name" style="width: 10%">${ehd[2]} </li>  
     <li class="dealer" style="width: 12%">${ehd[3]} </li>   
     <li class="price" style="width: 6%">${ehd[4]}</li> 
     <li class="price" style="width: 10%">${ehd[5]}</li>
     
     <li class="pass" style="width: 6%">
     		<c:forEach items="${stamap}" var="sta">
     		<c:if test="${ehd[6]==sta.key}">
     			<span class="unpassing">${sta.value }</span>
     		</c:if>
     	</c:forEach>
     </li> 
      <li class="price" style="width: 10%">${ehd[7]}</li>
      <li class="deals" style="margin-top: 1%;margin-left: 5%">
     
    	<a href="javascript:delete_add(${ehd[0]})"><i class="icon_del"></i>删除</a><br>
    	<a href="${pageContext.request.contextPath }/EmployrecruitmentServlet/getUpdatePage?id=${ehd[0]}"><i class="icon_corr"></i>审批</a><br>
    	<a href="${pageContext.request.contextPath }/EmployrecruitmentServlet/getEhdByid?id=${ehd[0]}"><i class="icon_read"></i>查看</a>
     
     </li>
     
     </ul>
        </c:forEach>
 
 <%@include file="../page.jsp"  %>
</div>

</body>
</html>
