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
    
    <title>部门信息</title>
    
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

<script>
	function delete_add(uid)
		{
		 msg='确认要删除该新闻么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/NotifyServlet/delNotify?id="+uid;
		  window.location=URL;
		 }
		}
</script>
</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
  <a href="NotifyServlet/getAllNotify" class="hover">新闻管理</a>
  <a href="oa_news/addnews.jsp">新建新闻</a>

</div>



<div class="usr_info_t"><h3>新闻筛选：<a href="NotifyServlet/getNotifyByType?type=0">通&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;知</a>  <a href="NotifyServlet/getNotifyByType?type=1">公司新闻</a>  <a href="NotifyServlet/getNotifyByType?type=2">行业动态</a></h3>  <!--<div class="search_box clearfix">
		<form action="UserServlet/getUserByOther" method="post">
		<button type="submit">搜索<i></i></button><input name="id" type="text" value="搜索信息（员工编号）" onFocus="if(this.value=='搜索信息（员工编号）'){this.value=''}" onblur="if(this.value==''){this.value='搜索信息（员工编号）'}"/>
		</form></div>--></div>
<br><br>

   <div class="usr_info_cols clearfix">
   
   <ul>
        <li class="job" style="width: 12%">标题</li>  
   <li class="name" style="width: 10%">发布范围</li> 
   	 <li class="name" style="width: 10%">类型</li>    
    
     <li class="phone" style="width: 12%">通知时间</li>
     <li class="area" style="width: 10%">状态</li>
     
     <li class="btn_turn"><span>${page }/${pages }页</span><div><a href="NotifyServlet/getNotifyByType?page=${pages }&type=${type}"><i class="icon_next"></i></a><a href="NotifyServlet/getNotifyByType?page=1&type=${type}"><i class="icon_pre"></i></a></div></li>

   </ul>
   
   </div>


   <c:forEach items="${no}" var="no">
   <ul class="usr_info_lit clearfix">
     <li class="job"  style="width: 12%"><a href="${pageContext.request.contextPath }/NotifyServlet/getNotifyById?id=${no[0] }" style="float:left;">${no[3] }</a></li>
   	 <li class="name" style="width: 10%">
   	 	<c:if test="${no[7] ==0}">全部共享</c:if>
     	<c:if test="${no[7] ==1}">部门:${no[8]}</c:if>
     	<c:if test="${no[7] ==2}">人员:${no[10]}</c:if>
   	 
   	 </li> 
   	 <li class="name" style="width: 10%">
   		<c:if test="${no[1] ==0}">通知</c:if>
     	<c:if test="${no[1] ==1}">公司新闻</c:if>
     	<c:if test="${no[1] ==2}">行业动态</c:if>
   	 </li> 
      
     <li class="phone" style="width: 12%">${fn:substring(no[2] ,0,19)  }</li>
     <li class="area" style="width: 10%">
     	<c:if test="${no[6] ==0}">未发布</c:if>
     	<c:if test="${no[6] ==1}">已发布</c:if>
     </li> 

    <li class="deals" style="width: 35%">
    <a href="javascript:delete_add(${no[0] })"><i class="icon_del"></i>删除</a>
    <a href="${pageContext.request.contextPath }/NotifyServlet/getupdatepage?lineid=${no[0]  }"><i class="icon_corr"></i>修改</a>
    <a href="${pageContext.request.contextPath }/NotifyServlet/getNotifyById?id=${no[0] }"><i class="icon_read"></i>查看</a>
    	<c:if test="${no[6] ==0}"> <a href="${pageContext.request.contextPath }/NotifyServlet/updateNStatus?lineid=${no[9]  }&status=1"><i class="icon_corr"></i>发布</a></c:if>
     	<c:if test="${no[6] ==1}"> <a href="${pageContext.request.contextPath }/NotifyServlet/updateNStatus?lineid=${no[9]  }&status=0"><i class="icon_corr"></i>停用</a></c:if>
   
    </li>
   </ul>
</c:forEach>

<%@include file="../page.jsp"  %>
</div>
</body>
</html>
