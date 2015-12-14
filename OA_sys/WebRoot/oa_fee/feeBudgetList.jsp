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
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/style.css" />

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
</head>

<body>
	<div class="rightsider" style="width: 1100px;">

		<div class="info_tabs clearfix">
			<a class="hover">单项预算费用管理</a> <a
				href="<%=basePath%>feeBudgetController/toSave" class="usr_add"><i></i>添加</a>
			<a href="<%=basePath%>feeBudgetController/intoAddByExcel"
				class="usr_add"><i></i>EXCEL导入</a>
		</div>
		<div class="usr_info_t">
			<form action="feeBudgetController/findAll" method="post" id="myform">

				<table style="width:100%">
					<tr>
						<td style="width:100px"></td>
						<td>公司:<input type="text" name="vcCompany"
							value="${vcCompany}" />
						</td>
						<td>所属部门:<input type="text" name="vcDeptname"
							value="${vcDeptname}" />
						</td>
						<td>费用类别：<input type="text" name="vcCosttype"
							value="${vcCosttype}" />
						</td>
						<td>月份： <select name="nMonth">
								<option value="">--请选择--</option>
								<option value="1">1月份</option>
								<option value="2">2月份</option>
								<option value="3">3月份</option>
								<option value="4">4月份</option>
								<option value="5">5月份</option>
								<option value="6">6月份</option>
								<option value="7">7月份</option>
								<option value="8">8月份</option>
								<option value="9">9月份</option>
								<option value="10">10月份</option>
								<option value="11">11月份</option>
								<option value="12">12月份</option>
						</select>
						</td>
						<td><input type="submit" value="查询"
							style="width:100px;background-color:#ff6600" /></td>
					</tr>
				</table>

				<br />

				<table class="table">
					<tr>
						<td>公司</td>
						<td>所属部门</td>
						<td>费用类别</td>
						<td>月份</td>
						<td>单项费用预算总额</td>
						<td>余额</td>
						<td>预估余额</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${costBudgets}" var="budget">
						<tr>
							<td>${budget.vcCompany}</td>
							<td>${budget.vcDeptname}</td>
							<td>${budget.vcCosttype}</td>
							<td>${budget.nMonth}</td>
							<td>${budget.nTotalcost}</td>
							<td>${budget.nLastcost}</td>
							<td>${budget.nLastcost2}</td>
							<td><a
								href="<%=basePath%>feeBudgetController/toSave?id=${budget.lineid}">修改</a>
								<a
								href="<%=basePath%>feeBudgetController/del?id=${budget.lineid}">删除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@include file="../page.jsp"%>
			</form>

		</div>
	</div>
</body>
</html>
