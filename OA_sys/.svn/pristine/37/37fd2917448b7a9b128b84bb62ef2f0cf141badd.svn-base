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
				href="<%=basePath%>feeController/toSave" class="usr_add"><i></i>添加</a>
		</div>
		<div class="usr_info_t">
			<form action="<%=basePath%>feeController/findAll" method="post" id="myform">
				
				<table style="width:100%">
					<tr>
						<td width="750px;"></td>
						<td>费用类型 :<input type="text" name="vcCosttypename"
							value="${vcCosttypename}" />
						</td>
						
						<td><input type="submit" value="查询"
							style="width:100px;background-color:#ff6600" />
						</td>
					</tr>
				</table>
			</form>

		
		<br />

		<table class="table">
			<tr>
				<td>费用类型</td>
				<td>报销单编号</td>
				<td>费用金额</td>
				<td>申请人</td>
				<td>申请日期</td>
				<!-- <td>备注</td> -->
				<td>状态</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${feeList}" var="fee">
				<tr>
					<td>${fee.vcCosttypename}</td>
					<td><a href="<%=basePath%>feeController/viewFeeContent?id=${fee.id}">${fee.vcApplyNo}</a></td>
					<td>${fee.nCost}</td>
					<td>${fee.vcName}</td>
					<td>
						<fmt:formatDate value="${fee.dtApply}" pattern="yyyy-MM-dd"/>
					</td>
					<%-- <td>${fee.vcNote}</td> --%>
					<td>
					<c:if test="${fee.nState==0}">初始录入</c:if>
					<c:if test="${fee.nState==1}">审核中</c:if>
					<c:if test="${fee.nState==2}">审核中(驳回)</c:if>
					<c:if test="${fee.nState==3}">完成</c:if>
					</td>
					<td>
					
					<c:if test="${fee.nState==0}">
					<a href="<%=basePath%>feeController/toSave?id=${fee.id}">修改</a>
					<a href="<%=basePath%>feeController/del?id=${fee.id}">删除</a>
					<a href="<%=basePath%>workFlowController/startCostFeeProgress?id=${fee.id}">申请审批</a>
					</c:if>
					<c:if test="${fee.nState != 0}">
					<a href="<%=basePath%>workFlowController/viewFeeHisComment?id=${fee.id}">查看审核记录</a>
					</c:if>
					</td>
				</tr>

			</c:forEach>
		</table>
	<%@include file="../page.jsp"%>
	</div>
	</div>
</body>
</html>
