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

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<title>企业管理-用户管理</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />

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
		function pass(){

		if(document.getElementById("p1").value == ""){
			alert("密码不能为空");
			document.getElementById("p1").focus();
			return false;

		}else if (!/^\w{6,50}$/.test(document.getElementById("p1").value)){
			alert("请输入6 ~ 20 字符【0-9】【a-z-A-Z】");
			document.getElementById("p1").focus();
			return false;
		}
		else {
			return true;
		}
	}
	</script>
  </head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
 <a href="UserServlet/getAllUsers" class="hover">用户修改</a> 
</div>

<dl class="notice clearfix">
      
  <dd>用户资料 </dd>   
             
</dl>
<form action="${pageContext.request.contextPath }/UserServlet/updateUser" method="post" onsubmit="return pass()">
	    	<ul class="qy_info">
			
			<li><span>* 档案号：</span><input name="recordid" value="${user.employrecord.lineid }" readonly="readonly"></li>
			
			<li><span>* 员工编号：</span><input name="userno" value="${user.userno }" readonly="readonly"></li>
			
			<li><span>* 密码：</span><input name="password" type="password" value="${user.password }" id="p1"></li>
			
			<li><span>* 职位：</span><select name="admin" style="width: 200px;">
   				<c:if test="${user.admin==0}">
   				<option value="0">普通员工</option>		
   				<option value="1">部门领导</option>
   				<option value="2">公司领导</option>
   				</c:if>
   				<c:if test="${user.admin==1}">
   				<option value="1">部门领导</option>
   				<option value="0">普通员工</option>
   				<option value="2">公司领导</option>
   				</c:if>
   				<c:if test="${user.admin==2}">
   				<option value="2">公司领导</option>
   				<option value="0">普通员工</option>		
   				<option value="1">部门领导</option>
   				</c:if>
   				</select>
			</li>
			<li><span>* 角色：</span><select name="roleid" style="width: 200px;"><c:forEach items="${ro}" var="ro"><option value="${ro.lineid }" <c:if test="${ro.lineid==user.roleid}"><c:out value="selected"/></c:if>>${ro.rolename }</option></c:forEach></select></li>
			<li><span>* 账户是否有效：</span><select name="active" style="width: 200px;">
			<c:if test="${user.active==0 }">
   				<option value="0">无效</option>		
   				<option value="1">有效</option>
   				</c:if>
   				<c:if test="${user.active==1 }">
   				<option value="1">有效</option>
   				<option value="0">无效</option>		
   				</c:if>
			</select>
			</li>
			</ul>
			<br>
				<input type="hidden" name="upass" value="${user.password }"/>
				<input type="hidden" name="lineid" value="${user.lineid}">
		<button type="submit" style="margin-left: 10px;height: 40px" class="tj add_btn">修改</button>
		<button type="button" style="margin-left: 10px;height: 40px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		
		
	    	
    	</form>
</div>
</body>
</html>

