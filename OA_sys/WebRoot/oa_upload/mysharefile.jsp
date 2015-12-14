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
    
    <title>文件信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./css/style.css" />

<script type="text/javascript">
	function delete_add(uid)
		{
		 msg='确认要删除该文件么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/UploadServlet/delShareFile?id="+uid;
		  window.location=URL;
		 }
		}

</script>

</head>
<body>
<div class="rightsider" style="width: 1200px;">

<div class="info_tabs clearfix">
  

  <a href="oa_upload/upload.jsp" >文件上传</a>
   <!--  <a href="UploadServlet/getSelfFile">文件共享</a> -->
  <a href="UploadServlet/getShareFile" class="hover">共享管理</a>
  

</div>



<dl class="notice clearfix">
      
  <dd>文件管理 &gt; 个人文件管理</dd>   
             
</dl>

   <div class="usr_info_cols clearfix">
   
   <ul>
   
     <li class="" style="width: 300px;text-align: center;">文件名称</li>      
     <li class="date2" style="">文件说明 </li> 
	<li class="date3" style="">共享类型 </li>
    <li class="date3" style="">共享时间 </li>
	<li class="date2" style="">共享状态 </li>
    
	</ul>
   
   </div>


<c:forEach items="${list}" var="list">
   <ul class="usr_info_lit clearfix">

     <li class="" style="width: 300px;text-align: left;">${list[1] }</li>      
     <li class="date2" style="">${list[7] } </li>
     <li class="date3" style="">
     	<c:if test="${list[2]==0 }">全部共享</c:if>
     	<c:if test="${list[2]==1 }">部门：${list[5] }</c:if>
     	<c:if test="${list[2]==2 }">人员：${list[9] }</c:if>
     </li>
     <li class="date3" style="">${fn:substring(list[6],0,19) } </li>
     <li class="date2" style="">
     	<c:if test="${list[8]==0 }">终止</c:if>
     	<c:if test="${list[8]==1 }">生效</c:if>
      </li>
    <li  style="width: 230px;text-align: center;">
    <a href="javascript:delete_add(${list[0] })"><i class="icon_del"></i>删除</a>
    <a href="${pageContext.request.contextPath }/UploadServlet/dowfilebyname?filename=${list[1] }"><i class="icon_read"></i>下载</a>
    <c:if test="${list[8]==0 }"><a href="${pageContext.request.contextPath }/UploadServlet/getShareFileById?lineid=${list[0]}&status=1&page=${page }"><i class="icon_corr"></i>生效</a></c:if>
    <c:if test="${list[8]==1 }"><a href="${pageContext.request.contextPath }/UploadServlet/getShareFileById?lineid=${list[0]}&status=0&page=${page }"><i class="icon_corr"></i>终止</a></c:if>
   </ul>
</c:forEach>

<%@include file="../page.jsp"  %>
</div>
</body>
</html>
