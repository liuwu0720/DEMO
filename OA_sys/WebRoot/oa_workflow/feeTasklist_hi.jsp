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
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />

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
					<li><a href="<%=basePath%>workFlowController/listFeeTask"><h2>当前任务</h2></a></li>
					<li><a href="<%=basePath%>workFlowController/listHisFeeTask"
						class="active"><h2>历史任务</h2></a></li>
				</ul>
			</nav>
		</div>
		<div class="usr_info_t">
			<form action="workFlowController/listHisFeeTask" method="post"
				id="myform">

				<table style="width:100%">
					<tr>
						<td width="120px;"></td>
						<td>申请部门 :<input type="text" name="vcDept" />
						</td>
						<td>申请人 :<input type="text" name="vcName" />
						</td>
						
						<td><input type="submit" value="查询"
							style="width:100px;background-color:#ff6600" /></td>
					</tr>
				</table>
			</form>

		</div>
		<br />
		<div>
			<table class="table">
				<tr>
					<td>费用类型</td>
					<td>报销单编号</td>
					<td>费用金额</td>
					<td>申请人</td>
					<td>申请部门</td>
					<td>申请时间</td>
					<!-- <td>申请备注</td> -->
					<td>状态</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${costApplys}" var="co">
					<tr>
						<td>${co[5]}</td>
						<td><a href="<%=basePath%>feeController/viewFeeContent?id=${co[0]}">${co[21]}</a></td>
						<td>${co[6]}</td>
						<td>${co[2]}</td>
						<td>${co[11]}</td>
						<td><fmt:formatDate value="${co[3]}" pattern="yyyy/MM/dd" /></td>
						<%-- <td>${co[7]}</td> --%>
						<td><c:if test="${co[9]==0}">初始录入</c:if> <c:if
								test="${co[9]==1}">审核中</c:if> <c:if test="${co[9]==2}">审核通过</c:if>
							<c:if test="${co[9]==3}">审核中(驳回)</c:if>
							<c:if test="${co[9]==4}">已删除</c:if>
							</td>
						<td><c:if test="${co[9]==1}">
								<a target="_blank"
									href="<%=basePath%>workFlowController/viewImageByFeeid?id=${co[0]}">查看流程图</a>
							</c:if> 
							<c:if test="${co[9]==3}">
								<a target="_blank"
									href="<%=basePath%>workFlowController/viewImageByFeeid?id=${co[0]}">查看流程图</a>
							</c:if> 
							<a href="<%=basePath%>workFlowController/viewFeeHisComment?id=${co[0]}">查看审核记录</a>
						</td>
					</tr>
				</c:forEach>
			</table>

		</div>
		<%@include file="../page.jsp"%>
	</div>
</body>
</html>
