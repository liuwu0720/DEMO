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
  
  <a href="NotifyServlet/getAllNotify" class="hover">新闻管理</a>
  <a href="oa_news/addnews.jsp">新建新闻</a>

</div>
</div>
  <c:forEach items="${no}" var="no">
<table width="96%" style="margin-left: 25;">
<tr bgcolor="#ff6600" height="30px">
<td align="center"><font size="5" color="white">${no[3] }</font></td>
</tr>
<tr bgcolor="#c6c6c6" height="30px">
<td align="right"><font size="4">发布于：${fn:substring(no[2] ,0,19)  }</font></td>
</tr>


 <tr bgcolor="#F0FFF0"><td colspan="2" height="560" valign="top">
${no[4] }
</td></tr>
  <tr bgcolor="#F0FFF0">
      <td >附件文件:<br>
       <a href="NotifyServlet/downsfile?filename=${no[5] }" >${no[5] }</a>
     	 <input type="button" value="下载" onClick="DowFile('${no[5] }');" style="background-color: #ff6600"><br>
      </td>
    </tr>
</table>
 </c:forEach>
</body>
</html>
