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
<div class="rightsider" style="width: 900px;">

<div class="info_tabs clearfix">
  
  <a href="UploadServlet/getUplodShareFile" class="hover">文件下载</a>
  

</div>



<dl class="notice clearfix">
      
  <dd>文件管理 &gt; 文件下载</dd>   
             
</dl>

   <div class="usr_info_cols clearfix">
   <table  style="overflow: auto;"  class="gridtable">
	   <tr >
	  		 
		  	 <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 260px;" >文件名称</td> 
		   	 <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 150px;">文件说明</td>    
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 200px;">共享类型</td>      
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 200px;">共享时间</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 150px;">共享者</td>
	     
	    	<td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 100px;"></td>
	    
	  	</tr>
  


<c:forEach items="${list}" var="list">
 <tr>
		   	 <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 260px;">${list[2] }</td> 
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 150px;"  title="${list[4] }">${list[4] }</td>      
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 200px;">
		     
		     <c:if test="${list[1]==0 }">全部共享</c:if>
     		<c:if test="${list[1]==1 }">部门：${list[5] }</c:if>
     		<c:if test="${list[1]==2 }">个人：${list[3] }</c:if>
     		
     		</td>
     		
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 200px;">${fn:substring(list[6],0,19) } </td> 
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 150px;">${list[3] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 100px;"> <a style="background-color: transparent;" href="${pageContext.request.contextPath }/UploadServlet/dowfilebyname?filename=${list[2] }"> 下载</a></td>
		    
	   </tr>
	   
  
</c:forEach>
 </table>
  
   
   </div>
<%@include file="../page.jsp"  %>
</div>
</body>
</html>
