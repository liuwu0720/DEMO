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


			<a class="hover">费用审批管理</a> <a
				href="<%=basePath%>costfeeController/toSave" class="usr_add"><i></i>添加</a>

		</div>
		<div class="usr_info_t">
			<form action="costfeeController/findAll" method="post" id="myform">

				<table style="width:100%">
					<tr>
						<td width="120px;"></td>
						<td>费用类型 :<input type="text" name="vcType"
							value="${vcCosttypename}" />
						</td>

						<td><input type="submit" value="查询"
							style="width:100px;background-color:#ff6600" /></td>
					</tr>
				</table>
			</form>

		</div>
		<br />

		<table class="table">
			<tr>
				<td>类型</td>
				<td>费用金额</td>
				<td>申请日期</td>
				<td>申请备注</td>
				<td>申请人</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${costFeeList}" var="co">
				<tr>
					<td>${co[5]}</td>
					<td>${co[6]}</td>
					<td>${co[3]}</td>
					<td>${co[7]}</td>
					<td>${co[2]}</td>
					<td>
					<c:if test="${co[9]==0}">初始录入</c:if>
					</td>
					<td>
					<c:if test="${co[9]==0}">
					<a href="<%=basePath%>workFlowController/startCostFeeProgress?id=${co[0]}">申请审批</a>
					</c:if>
					</td>
				</tr>

			</c:forEach>
		</table>



		<%@include file="../page.jsp"%>
	</div>
</body>
</html>
