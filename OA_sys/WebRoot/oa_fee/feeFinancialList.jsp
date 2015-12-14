<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
	        + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'costfeeList.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
</head>

<body>
	<div class="rightsider" style="width: 1100px;">

		<div class="info_tabs clearfix">
			<a class="hover">费用审批领导层级管理</a> <a
				href="<%=basePath%>feeFinancialController/toSave" class="usr_add"><i></i>添加</a>
		</div>
		<div class="usr_info_t">
			<form action="feeFinancialController/findAll" method="post" id="myform">
				
				<table style="width:100%">
					<tr>
						<td style="width:400px"></td>
						<td >
							公司:<input type="text" name="vcDeptname" value="${vcDeptname}"/>
						</td>
						<td>
							姓名:<input type="text" name="vcUserName" value="${vcUserName}"/>
						</td>
						<td>
							等级：<input type="text" name="nLevel" value="${nLevel}"/>
						</td>
						<td><input type="submit" value="查询"
							style="width:100px;background-color:#ff6600" />
						</td>
					</tr>
				</table>
			</form>

		</div>
		<br />

		<table class="table">
			<tr>
				<td>公司</td>
				<td>姓名</td>
				<td>职位</td>
				<td>等级</td>
				<td>邮箱</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${financials}" var="financial">
				<tr>
					<td>${financial.vcDeptname}</td>
					<td>${financial.vcUserName}</td>
					<td>${financial.vcPosition}</td>
					<td>${financial.nLevel}</td>
					<td>${financial.vcEmail}</td>
					<td>
					<a href="<%=basePath%>feeFinancialController/toSave?id=${financial.lineid}">修改</a>
					<a href="<%=basePath%>feeFinancialController/del?id=${financial.lineid}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	<%@include file="../page.jsp"%>
	</div>
</body>
</html>
