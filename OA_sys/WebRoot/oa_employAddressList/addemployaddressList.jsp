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
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>企业管理-用户管理</title>

<link rel="stylesheet" type="text/css" href="./css/style.css" />

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
<script type="text/javascript">
	$(document).ready(function(){
		$("#myform").submit(function(){
			if($.trim($("#name").val())==""){
				alert("姓名不能为空！");
				$("#name").focus();
				return false;
			}
			if($.trim($("#deptid").val())=="0"){
				alert("所属公司不能为空！");
				$("#deptid").focus();
				return false;
			}
			
			if($.trim($("#mobile").val())!="")
	 		{
	 			 if(isNaN($("#mobile").val())){
	 				 alert("手机号码必须为纯数字");
	 				 $("#mobile").focus();
	 				 return false;
	 			}
	 			if($("#mobile").val().length!=11){
	 				 alert("手机号码位数不正确");
	 				$("#mobile").focus();
	 				 return false;
	 			}
			
			}
			if($.trim($("#email").val())!="")
			{
				
	 			    var strReg="";
					var r;
					var str=$("#email").val();
					//strReg=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/i;
					strReg=/^\w+((-\w+)|(\.\w+))*\@{1}\w+\.{1}\w{2,4}(\.{0,1}\w{2}){0,1}/ig;
					r=str.search(strReg);
					if(r==-1) {
						alert("邮箱格式错误!");
						$("#email").focus();
						return false;
					}
			
			}
			
			return true;
		});
	});
	
	function initss()
	{
		var paramsType = "${paramType}";
		if(paramsType=="add")
		{
			$("#myform").attr("action","EmpAddressListServlet/add");
		}else if(paramsType=="update")
		{
			$("#myform").attr("action","EmpAddressListServlet/update");
		}
	
	}
</script>

</head>
<body onload="javascript:initss();">
<div class="rightsider">

<div class="info_tabs clearfix">
 <a href="EmpAddressListServlet/addbefore" class="hover">公共通讯录添加</a> 
</div>

<dl class="notice clearfix">
      
  <dd>公共通讯录资料 </dd>   
             
</dl>
<form  action="" method="post" id="myform">
<table width="92%">
					<tr>
						<td><font color="#FF4500" size="4"><strong>基本信息</strong>
						</font>
						</td>
						<td width="50px"></td>
						<td></td>
					</tr>
					<tr height="30px">
						<td>* 姓名：<input type="text" name="name" id="name" value="${emp.name}">
						</td>
						<td width="50px"></td>
						<td>* 所属公司：
							<select style="width: 150px;margin-top: 3px;" id="deptid" name="deptid" >
								<option value="0">---未选择---</option>
							
								<c:forEach items="${deptlist}" var="dept">
									<c:choose>
										<c:when test="${emp.dept.lineid == dept.lineid}">
											<option value="${dept.lineid}" selected="selected">${dept.deptname}</option>	
										</c:when>
										<c:otherwise>
											<option value="${dept.lineid}">${dept.deptname}</option>		
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr height="30px">
						<td> &nbsp;&nbsp;&nbsp;地址：<input type="text" name="address" id="address" value="${emp.address}">
						</td>
						<td width="50px"></td>
						<td> &nbsp;公司名称：<input type="text" name="companyName"
							id="companyName"value="${emp.companyName}" />
						</td>
					</tr>
					<tr height="30px">
						<td> 手机号：<input type="text" name="mobile" id="mobile" value="${emp.mobile}">
						</td>
						<td width="50px"></td>
						
						<td> &nbsp;&nbsp;职位名称：<input type="text" name="positionName"
							id="positionName" value="${emp.positionName}"/>
						</td>
					</tr>
					<tr height="30px">
						<td>&nbsp;&nbsp; 邮箱：<input type="text" name="email" id="email" value="${emp.email}">
						</td>
						<td width="50px"></td>
						
						<td> &nbsp;&nbsp;固定电话：<input type="text" name="tel"
							id="tel" value="${emp.tel}" />
						</td>
					</tr>
	</table>
<br>
	<button type="submit" style="margin-left: 50px;width: 100px;height: 30px;" class="tj add_btn">提交</button>
	<button type="button" style="margin-left: 50px;width: 100px;height: 30px;" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		<input type="hidden" id="paramType" name="paramType" value="${paramType}"> 
		<input type="hidden" id="id" name="id" value="${emp.lineid}"> 
</form>
</div>
</body>
</html>

