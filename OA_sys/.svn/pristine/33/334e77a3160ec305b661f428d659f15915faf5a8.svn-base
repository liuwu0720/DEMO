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
	<title>印章申请</title>

<link rel="stylesheet" type="text/css" href="./css/style.css" />
<script language="javascript" type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	

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
$(document).ready(function(){
	$("#myform").submit(function(){
		if($("#notice").val()=="")
		{
			alert("印章类型不能为空！");
			$("#notice").focus();
			return false;
		}
			
			return true;
			
	});
});
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">

<a href="EmployholidayServlet/getEhdByUncheck">印章申请</a>
 
</div>

<dl class="notice clearfix">
      
  <dd>印章申请单</dd>   
             
</dl>
<form  action="EmploytripServlet/add" method="post" id="myform">

<table>
		<tr>
			<td>审批模式</td>
			<td>
				 <select id="" name="" style="width: 150px;">
					<option value="-1">---请选择---</option>
					<c:forEach items="${Approvallist}" var="app">
						<option value="${app.lineid}">${app.approvalName}</option>
					</c:forEach>		
				</select>
			 </td>  
		</tr>
		<tr>
			<td>印章类型</td>
			<td><input type="text" id="notice" name="notice" > </td>  
		</tr>
	<tr>
		<td>
			<span> 用章事项：</span>
		</td>
		<td> <input type="text" value="" id="address" name="address"></td>
	</tr>
	<tr><td>备注</td><td><textarea name="remarks"  cols="5" rows="5" style="width: 200px;" id="remarks"></textarea></td></tr>
	
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	
</table>
<br>
	<button type="submit" style="margin-left: 10px;height: 40px;width: 100px;" class="tj add_btn">申请</button>
	<button type="button" style="margin-left: 10px;height: 40px;width: 100px;" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		


</form>
</div>
</body>
</html>

