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
	<title>企业管理-招聘需求</title>

<link rel="stylesheet" type="text/css" href="./css/style.css" />
<script language="javascript" type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
	

<script type="text/javascript">
$(document).ready(function(){

	$("#myform").submit(function(){
		 
		if($("#possid").val()==0)
		{
			alert("岗位不能为空！");
			$("#possid").focus();
			return false;
		}else if($.trim($("#numbers").val()) =="" ){
			alert("请填写人数");
			$("#numbers").focus();
			return false;
		}else if($("#typeid").val()==0){
			alert("请选择申请理由");
			$("#typeid").focus();
			return false;
		}else if($("#d11").val()==""){
			alert("到岗时间不能为空！");
			$("#d11").focus();
			return false;
		}
		
		
	});
});
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">


 
</div>

<dl class="notice clearfix">
      
  <dd>招聘申请单 </dd>   
             
</dl>
<form  action="EmployrecruitmentServlet/add" method="post" id="myform" >

<table>
	<tr>
		<td>
			<span>* 申请职位：</span>
		</td>
		<td> 
			<select id="possid" name="possid">
					<option value="0">---未选择---</option>
					<c:forEach items="${poslist}" var="pos">
						<option value="${pos.lineid}">&nbsp;&nbsp;${pos.positionname}</option>
					</c:forEach>
				</select>
			</td>
		<td>
			<span>* 人数：</span>
		</td>
		<td> <input type="text" value="" id="numbers" name="numbers" size="10"></td>
	</tr>
	<tr>
		<td><span>* 申请理由：</span></td>
			<td>&nbsp;
				<select id="typeid" name="typeid">
					<option value="0">---未选择---</option>
					<c:forEach items="${typelist}" var="types">
						<option value="${types.lineid}">&nbsp;&nbsp;${types.typename}</option>
					</c:forEach>
				</select>
			</td>
		<td>
			<span>* 到职时间：</span>
		</td>
		<td><input name="date1" type="text" id="d11" style="width: 230px" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"><img src="./My97DatePicker/skin/datePicker.gif" width="24" height="33" align="absmiddle" style="cursor:pointer" onClick="WdatePicker({el:'d11',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/></td>
	</tr>
		<tr>
			
		</tr>
	<tr>
		<td>
			<span>* 性别：</span>
		</td>
		<td> 
			<select id="sexID" name="sexID">
				<option value="1">男</option>
				<option value="2">女</option>
			</select>
		<td>
			<span>* 婚否：</span>
		</td>
		<td> 
			<select id="maritalsta" name="maritalsta">
				<option value="0">--未选择--</option>
				<option value="1">--未婚--</option>
				<option value="2">--已婚--</option>
			</select>
		<td>
			<span>* 年龄：</span>
		</td>
		<td>
			 <select id="ageID" name="ageID">
				<c:forEach items="${agemap}" var="keys">
					<option value="${keys.key}">${keys.value}</option>				
				</c:forEach>
			</select>
	</tr>
	<tr>
		<td>
			<span>* 学历：</span>
		</td>
		<td> 
			<select id="educationID" name="educationID">
				<c:forEach items="${educationlist}" var="education">
						<option value="${education.lineid}">&nbsp;&nbsp;${education.levelname}</option>
					</c:forEach>
			</select>
		<td>
			<span>* 外语：</span>
		</td>
		<td> <input type="text" value="" id="englevel" name="englevel"></td><td>
			<span>* 工作年限：</span>
		</td>
		<td> 
			<select id="workyear" name="workyear">
				<c:forEach items="${workmap}" var="worksta">
					<option value="${worksta.key}">${worksta.value}</option>				
				</c:forEach>
			</select>
	</tr>
	<tr>
		<td>
			<span>* 专业：</span>
			
		</td>
		<td> <input type="text" value="" id="professional" name="professional"></td>
	</tr>
	<tr><td>专长/技能</td><td><textarea name="expertise" cols="5" rows="5" style="width: 250px;" id="expertise"></textarea></td></tr>
	<tr><td>工作内容</td><td><textarea name="workcontents" cols="5" rows="5" style="width: 250px;" id="workcontents"></textarea></td></tr>
	
</table>
<br>
	<button type="submit" style="margin-left: 10px;height: 40px;width: 100px;" class="tj add_btn">申请</button>
	<button type="button" style="margin-left: 10px;height: 40px;width: 100px;" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		


</form>
</div>
</body>
</html>

