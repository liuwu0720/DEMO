<%@ page language="java" import="java.util.*,clt.com.cn.model.entity.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>My JSP 'privilege.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="./themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="./themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="<%=path %>/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=path %>/js/jquery.validate.js" type="text/javascript"></script>



<script src="<%=path %>/js/dwz.core.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.tree.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.ui.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.theme.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.navTab.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.ajax.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.panel.js" type="text/javascript"></script>
<script src="<%=path %>/js/dwz.checkbox.js" type="text/javascript"></script>


<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:true,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>

  </head>
  
  <body>
  <div class="rightsider">

<div class="info_tabs clearfix">
  
 
 <a href="UserServlet/getAllUsers" class="hover">用户管理</a> 
  
</div>

<div class="usr_info_t"><h3>权限分配:</h3>  <div class="search_box clearfix" >
</div><br><br><br>
<hr>
    <div id="layout">
		
		<div id="container" style="margin-left:0px;">
			<div id="navTab" class="tabsPage">
			
				<div id="resultBox"></div>

<div style="background:#FFF;">
<form action="PrivilegeServlet/add" method="post"  >
<input type="hidden" value="${userid }" name="userid">
<ul class="tree treeFolder treeCheck expand">

<c:forEach items="${module}" var="module">
 	<c:if test="${module.pid==0}">
 		
	<li><a tname="name" id="pr" tvalue="${module.lineid }" 
		<c:forEach items="${upr}" var="upr">
			<c:if test="${module.lineid == upr.privilegeid }">checked="true" </c:if>
	</c:forEach >
	>${module.privilegename }</a></li>
		
	</c:if>
		<ul>
			<c:if test="${module.pid!=0}">
			
			<li><a tname="name" id="pr" tvalue="${module.lineid }"
				<c:forEach items="${upr}" var="upr">
				<c:if test="${module.lineid == upr.privilegeid }">checked="true" </c:if>
				</c:forEach >
			>${module.privilegename }</a></li>
				
			</c:if>
		</ul>
		
</c:forEach>
</ul>
<br><br>
<button type="submit" style="width: 100px" class="tj add_btn">确定</button>
	<button type="button" style="margin-left: 50px;width: 100px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
</form>
</div>

</div>
</div>
</div>
</div>
  </body>
</html>
