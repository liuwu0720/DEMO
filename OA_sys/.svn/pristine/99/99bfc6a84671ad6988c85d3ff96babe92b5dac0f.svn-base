<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>考勤报表</title>
    
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

</head>
<body>
<div class="rightsider">
<div class="info_tabs clearfix">
  <a  class="hover">详细信息</a> 
</div>
 <div class="htban_info_cols clearfix">
   <ul>
	   <c:forEach items="${titlist}" var="tit" varStatus="ids">
	   		<c:choose>
	   			<c:when test="${ids.index ==2 || ids.index ==3 }">
	   				<li class="date" style="width: 150px;text-align: center;">${tit}</li>	
	   			</c:when>
	   			<c:otherwise>
	   				<li class="date" style="width: 100px;text-align: center;">${tit}</li>
	   			</c:otherwise>
	   		</c:choose>
	   		
	   </c:forEach>
   </ul>
   </div>
   <c:forEach items="${datelist}" var="dates" varStatus="ind">
   
	     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
		     
	   		<c:forEach items="${dates}" var="da" begin="1" varStatus="ids">
	   			<c:choose>
	   				<c:when test="${ids.index==2 || ids.index==3}">
			   			<li class="date" style="width: 150px;text-align: center;">
						     ${da}
					     </li> 	
	   				</c:when>
	   				<c:otherwise>
			   			<li class="date" style="width: 100px;text-align: center;">
						     ${da}
					     </li> 
	   				</c:otherwise>
	   			</c:choose>
	   			
	   		</c:forEach>
		     
	     </ul>
  </c:forEach>
 

</div>
<button type="button" style="margin-left: 50px;height: 30px;width: 80px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
</body>
</html>
