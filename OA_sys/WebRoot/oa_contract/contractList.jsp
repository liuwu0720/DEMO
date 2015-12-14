<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<base href="<%=basePath%>">

<title>合同信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function del(){
		var isTrue=confirm("确认删除？");
		if(isTrue){
			
		}
	}
</script>

</head>
<body>
	<div class="rightsider" style="width: 1100px;">

		<div class="info_tabs clearfix">


			<a class="hover">合同管理</a> <a
				href="<%=basePath%>contractController/toSave" class="usr_add"><i></i>添加</a>

		</div>
		<div class="usr_info_t">
			<form action="contractController/findAll" method="post"
				id="myform">

				<table style="width:100%">
					<tr>
						<td width="120px;"></td>
						<td>合同名称 :<input type="text" name="vcContractname" value="${vcContractname}"/>
						</td>
						<td>合同类型 :<input type="text" name="vcType" value="${vcType}"/>
						</td>
						<td>甲方: <input type="text" name="vcPartya" value="${vcPartya}"/>
						</td>
						<td>乙方: <input type="text" name="vcPartyb" value="${vcPartyb}"/>
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
				<td>合同编号</td>
				<td>合同名称</td>
				<td>合同类型</td>
				<td>甲方</td>
				<td>乙方</td>
				<td>生效日期</td>
				<td>失效日期</td>
				<td>创建时间</td>
				<td>合同状态</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${contracts}" var="co">
				<tr>
					<td style="width: 100px;word-break:break-all;">${co[1]}</td>
					<td  style="width: 100px;word-break:break-all;"><a
						href="<%=basePath%>contractController/viewContract?id=${co[0]}">${co[2]}</a></td>
					<td>${co[8]}</td>
					<td  style="width: 100px;word-break:break-all;">${co[3]}</td>
					<td style="width: 100px;word-break:break-all;">${co[4]}</td>
					<td><fmt:formatDate value="${co[5]}" pattern="yyyy/MM/dd" /></td>
					<td><fmt:formatDate value="${co[6]}" pattern="yyyy/MM/dd" /></td>
					<td><fmt:formatDate value="${co[9]}" pattern="yyyy/MM/dd" /></td>
					<td><c:if test="${co[7]==0}">初始录入</c:if> <c:if
							test="${co[7]==1}">审核中</c:if> <c:if test="${co[7]==2}">审核通过</c:if>
						<c:if test="${co[7]==3}">驳回</c:if></td>
					<td><c:if test="${co[7]==0}">
							<a href="<%=basePath%>contractController/del?id=${co[0]}" onclick="return confirm('确认删除？')">删除</a>
							<a href="<%=basePath%>contractController/toSave?id=${co[0]}">修改</a>
							<a href="<%=basePath%>contractController/viewContract?id=${co[0]}">查看</a>
							<a href="<%=basePath%>workFlowController/startProgress?id=${co[0]}">申请审批</a>
						</c:if> <c:if test="${co[7]==1}">
							<%-- <a href="<%=basePath%>contractController/toSave?id=${co[0]}">修改</a> --%>
							<a
								href="<%=basePath%>workFlowController/viewHisComment?id=${co[0]}">查看审核记录</a>

						</c:if> <c:if test="${co[7]==2}">
							<a
								href="<%=basePath%>workFlowController/viewHisComment?id=${co[0]}">查看审核记录</a>
						</c:if> <c:if test="${co[7]==3}">
							<a href="<%=basePath%>contractController/toSave?id=${co[0]}">修改</a>
							<a
								href="<%=basePath%>workFlowController/viewHisComment?id=${co[0]}">查看审核记录</a>
						</c:if></td>
				</tr>
				
			</c:forEach>
		</table>
		

		<%@include file="../page.jsp"%>
	</div>
</body>
</html>