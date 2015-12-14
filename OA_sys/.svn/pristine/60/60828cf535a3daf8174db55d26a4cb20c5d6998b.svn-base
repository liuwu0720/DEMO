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
	<title>查看单条信息</title>

<link rel="stylesheet" type="text/css" href="./css/style.css" />

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
	
</script>

</head>
<body >
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
					<br>
					<tr height="30px">
						<td> &nbsp;&nbsp;&nbsp;姓名：${emp.name}
						</td>
						<td width="50px"></td>
						<td> 
							所属公司：${emp.dept.deptname}
							
						</td>
						
					</tr>
					<tr height="30px">
						<td> &nbsp;&nbsp;&nbsp;地址：${emp.address}
						</td>
						<td width="50px"></td>
						<td> &nbsp;公司名称：${emp.companyName}
						</td>
					</tr>
					<tr height="30px">
						<td> 手机号：${emp.mobile}
						</td>
						<td width="50px"></td>
						
						<td> &nbsp;&nbsp;职位名称：${emp.positionName}
						</td>
					</tr>
					<tr height="30px">
						<td>&nbsp;&nbsp; 邮箱：${emp.email}
						</td>
						<td width="50px"></td>
						
						<td> &nbsp;&nbsp;固定电话：${emp.tel}
						</td>
					</tr>
	</table>
<br>
	<button type="button" style="margin-left: 50px;width: 100px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
</form>
</div>
</body>
</html>

