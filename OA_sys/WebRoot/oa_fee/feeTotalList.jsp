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

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.7.2.min.js"></script>

</head>

<body>
	<div class="rightsider" style="width: 1100px;">

		<div class="info_tabs clearfix">
			<a class="hover">部门费用预算总额管理</a>
			<!--<a
				href="<%=basePath%>feeTotalController/toSave" class="usr_add"><i></i>添加</a>-->
		</div>
		<div class="usr_info_t">
			<form action="feeTotalController/findAll" method="post" id="myform">

				<table style="width:100%">
					<tr>
						<td width="200px;"></td>
						<td>公司:<input type="text" name="vcCompany"
							value="${vcCompany}" />
						</td>
						<td>所属部门:<input type="text" name="vcDept" value="${vcDept}" />
						</td>
						<td>月份: <select name="nMonth">
								<option value="0">--请选择--</option>
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
			</form>

		</div>
		<br />

		<table class="table">
			<tr>
				<td>公司</td>
				<td>所属部门</td>
				<td>费用项目</td>
				<td>月份</td>
				<td>总额</td>
				<!-- <td>余额</td> -->
				<td>预估余额</td>
				
			</tr>
			<c:forEach items="${totalCosts}" var="totalCost">
				<tr>
					<td>${totalCost.vcCompany}</td>
					<td>${totalCost.vcDept}</td>
					<c:if test="${totalCost.iCosttype == 1}">
						<td>日常费用</td>
					</c:if>
					<c:if test="${totalCost.iCosttype == 2}">
						<td>特有费用</td>
					</c:if>
					<td>${totalCost.nMonth}</td>
					<td><input disabled="disabled" size="8" style="background:transparent"
						value=" ${totalCost.nTotalcost}"></td>
					<%-- <td><input disabled="disabled" size="8" style="background:transparent" value="${totalCost.nLastcost}"></td> --%>
					<td><input disabled="disabled" size="8" style="background:transparent" value="${totalCost.nLastcost2}"></td>	
				</tr>
			</c:forEach>
		</table>
		<%@include file="../page.jsp"%>
	</div>
</body>
</html>
