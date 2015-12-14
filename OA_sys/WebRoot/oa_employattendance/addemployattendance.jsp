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
	<title>企业管理-请假管理</title>

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
		if($("#reason").val()=="")
		{
			alert("原因不能为空！");
			$("#reason").focus();
			return false;
		}else if($("#typeid").val()==0){
			alert("请选择类别！");
			$("#typeid").focus();
			return false;
		}else if($("#d11").val()==""){
			alert("请假开始时间不能为空！");
			$("#d11").focus();
			return false;
		}else if($("#d12").val()==""){
			alert("请假结束时间不能为空！");
			$("#d12").focus();
			return false;
		}else if($.trim($("#checkusid")) == "")
		{
			alert("未设置审批人,请联系管理员");
			return false;
		}else{
		
			var time1= $("#d11").val();
			var time2= $("#d12").val();
			time1 = new Date(time1.replace(/-/g, "/"));
			time2 = new Date(time2.replace(/-/g, "/"));
			if(time1>time2){
				alert("结束时间不能早于开始时间！");
				$("#d12").focus();
				return false;
			}
			
			return true;
		}	
	});
});
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
<%@include file="../employutil.jsp"  %>
</div>

<dl class="notice clearfix">
      
  <dd>考勤单 </dd>   
             
</dl>
<form  action="EmployattendanceServlet/add" method="post" id="myform">

<table>
		<tr>
			<td><span>* 考勤类别：</span></td>
			<td>&nbsp;
				<select id="typeid" name="typeid">
					<option value="0">---未选择---</option>
					<option value="1">&nbsp;上班</option>
					<option value="2">&nbsp;下班</option>
				</select>
			</td>
		</tr>
		<tr>
		<td>
			
		</td>
		<td> </td>
		</tr>
	<tr><td>异常原因</td><td><textarea name="reason" cols="5" rows="5" style="width: 200px;" id="reason"></textarea></td></tr>
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr><td><span>* 异常时间：</span></td><td><input name="date1" type="text" id="d11" style="width: 230px" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"><img src="./My97DatePicker/skin/datePicker.gif" width="24" height="33" align="absmiddle" style="cursor:pointer" onClick="WdatePicker({el:'d11',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/></td></tr>
	<tr><td></td><td></td></tr>
	<tr>
		<td style="text-align:center;">审批人:</td>
		<td>
		 <div style="display: inline;float: left;" >
		 	<font style="font-size: 20px;color: #FF6600;">${usobj.usname} </font> &nbsp;<input type="checkbox" id="issend" name="issend" value="yes" style="vertical-align: middle;margin-top: 0;"> 是否发送邮件通知
		 </div></td>
	</tr>
</table>
<br>
	<button type="submit" style="margin-left: 10px;height: 40px;width: 100px;" class="tj add_btn">申请</button>
	<button type="button" style="margin-left: 10px;height: 40px;width: 100px;" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		

<input type="hidden" id="checkusid" name="checkusid" value="${usobj.usid}">

</form>
</div>
</body>
</html>

