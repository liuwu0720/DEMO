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
    
    <title>新闻查看</title>
    
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

	function DowFile(filename)
		{
	
		  URL="${pageContext.request.contextPath }/NotifyServlet/downsfile?filename="+filename;
		  window.location=URL;
		 
		}
		
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
  <a href="NotifyServlet/getSelNotify" class="hover">新闻查看</a>

</div>
</div>
<table width="96%" style="margin-left: 25;">
<tr  height="30px">
<td>标题：${no[0][3] }</td>
<td></td>
</tr>
<tr bgcolor="" height="30px">
<td  ><font size="4">发布于：${fn:substring(no[0][2] ,0,19)  }</font></td>
</tr>


 <tr bgcolor="#F0FFF0"><td colspan="2" height="560" valign="top">
${no[0][4] }
</td></tr>
  <tr bgcolor="#F0FFF0">
      <td >附件文件:<br>
      	<c:if test="${no[0][5]!=null }">
      			 <a href="NotifyServlet/downsfile?filename=${no[0][5] }" >${no[0][5] }</a>
      			 <input type="button" value="下载" onClick="DowFile('${no[0][5] }');" style="background-color: #ff6600"><br>
      	</c:if>
      
     	 
      </td>
    </tr>
</table>
</body>
</html>
