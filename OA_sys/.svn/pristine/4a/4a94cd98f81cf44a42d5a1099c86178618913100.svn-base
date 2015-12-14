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
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>企业管理-档案管理</title>

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
<script language="javascript" type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
	
</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
 <a href="EmployrecordServlet/getEmrBook" class="hover">详细信息</a> 
</div>

<dl class="notice clearfix">
      
  <dd>通讯录 </dd>   
             
</dl>


<ul class="qy_info">
	<table width="92%">
			<tr><td><font color="#FF4500" size="4"><strong>* 基本信息</strong></font></td><td width="50px"></td><td></td></tr>
    		<tr height="30px"><td >* 员工姓名：${emr.employname}</td><td width="50px"></td><td rowspan="5"><c:if test="${emr.filename!=null }"><img src="EmployrecordServlet/readphoto?photourl=/${emr.filename }" width="110" height="150"/><br>员工编号：${emr.fileno }</c:if>
    				<c:if test="${emr.filename==null }"><img src="css/nophoto.gif" width="110" height="150"/></c:if></td></tr>
    		<tr height="30px"><td >* 所属部门：${dept.deptname }</td><td width="50px"></td></tr>
    		<tr height="30px"><td >* 职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：${po.positionname }</td><td width="50px"></td></tr>
    		<tr height="30px"><td ></td><td width="50px"></td></tr>
    		<tr height="30px"><td ></td><td width="50px"></td><td></td></tr>
			<tr height="30px"><td colspan="3"><hr color="#A9A9A9" size="2"></td></tr>
    		<tr><td><font color="#FF4500" size="4"><strong>* 联系方式</strong></font></td><td width="50px"></td><td></td></tr>
    		<tr height="30px"><td >* 手机号：${emr.mobile }</td><td width="50px"></td><td>* 固定电话：${emr.tel1 }</td></tr>
    		<tr height="30px"><td >* 个人邮箱：${emr.email }</td><td width="50px"></td><td>* 分机号：${emr.tel2 }</td></tr>
    		<tr height="30px"><td >* 家庭电话：${emr.hometel }</td><td width="50px"></td><td>* 直线号码：${emr.telline }</td></tr>
    		<tr height="30px"><td >* 紧急联系人：${emr.emergencycontact }</td><td width="50px"></td><td>* 其他联系方式：${emr.contactno }</td></tr>
    		<tr height="30px"><td colspan="3">* 家庭住址：${emr.address }</td>
    			<td width="50px"></td>
    			<td>* 紧急联系电话：${emr.emergencytel }</td>
    		</tr>
    		<tr height="30px"><td colspan="3"><hr color="#A9A9A9" size="2"></td></tr>
    		
    		</table>
    	
	<button type="button" style="height: 40px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		
</ul>

</div>
</body>
</html>

