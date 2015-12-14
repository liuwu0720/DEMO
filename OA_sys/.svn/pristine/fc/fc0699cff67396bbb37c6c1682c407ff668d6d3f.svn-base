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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<base href="<%=basePath%>">
<title>任务列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/style.css" />

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.nave {
	margin-bottom: 20px;
	margin-left: 0;
	list-style: none;
}

.nave li {
	float: left;
	margin: 10px;
	cursor: pointer;
}

.nave>li>a {
	display: block;
	height: 35px;
}

a:link {
	color: red;
	text-decoration: underline;
}

.active {
	color: red;
}
</style>
</head>

<body>
	<div class="rightsider" style="width: 1100px;">
		<div class="nave" id="tab">
			<nav>
				<ul>
					<li><a href="<%=basePath%>workFlowController/listFeeTask"
						class="active"><h2>当前任务</h2></a></li>
					<li><a href="<%=basePath%>workFlowController/listHisFeeTask"><h2>历史任务</h2></a></li>
				</ul>
			</nav>
		</div>
		<div>
			<table class="table">
				<tr>
					<td>任务名称</td>
					<td>报销单编号</td>
					<td>单据类型</td>
					<td>申请人</td>
					<td>申请部门</td>
					<td>金额</td>
					<td>报销申请时间</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${costApplies}" var="t">
					<tr>
						<td>${t.vcTaskName }</td>
						<td><a href="<%=basePath%>feeController/viewFeeContent?id=${t.id }">${t.vcApplyNo }</a></td>
						<td>${t.vcCosttypename }</td>
						<td>${t.vcName }</td>
						<td>${t.vcDeptName }</td>
						<td>${t.nCost }</td>
						<td><fmt:formatDate value="${t.dtApply}"
								pattern="yyyy/MM/dd" /></td>
						<td><a
							href="${pageContext.request.contextPath }/workFlowController/viewFeeTaskForm?taskId=${t.vcTaskId }">办理任务</a>
							<a target="_blank"
							href="${pageContext.request.contextPath }/workFlowController/viewFeeCurrentImage?taskId=${t.vcTaskId }">查看当前流程图</a>
							<a
							href="${pageContext.request.contextPath }/workFlowController/delete?taskId=${t.vcTaskId }">删除任务</a>
							
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
