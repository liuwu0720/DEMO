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
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	
</script>
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
					<li><a href="<%=basePath%>workFlowController/deployHome" class="active"><h2>流程部署</h2></a></li>
					<li><a href="<%=basePath%>workFlowController/processDesign"><h2>流程设计</h2></a></li>
				</ul>
			</nav>
		</div>

		<table class="table">
			<tr>
				<td>ID</td>
				<td>流程名称</td>
				<td>发布时间</td>
			</tr>
			<c:forEach items="${depList}" var="de">
				<tr>
					<td>${de.id}</td>
					<td>${de.name}</td>
					<td><fmt:formatDate value="${de.deploymentTime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</table>

		<table class="table">
			<tr>
				<td>ID</td>
				<td>流程名称</td>
				<td>流程定义的KEY</td>
				<td>流程定义的版本</td>
				<td>流程定义的规则文件名称</td>
				<td>流程定义的规则图片名称</td>
				<td>部署ID</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${pdList}" var="de">
				<tr>
					<td>${de.id}</td>
					<td>${de.name}</td>
					<td>${de.key}</td>
					<td>${de.version}</td>
					<td>${de.resourceName}</td>
					<td>${de.diagramResourceName}</td>
					<td>${de.deploymentId}</td>
					<td><a target="_blank" href="<%=basePath%>workFlowController/viewImage?deploymentId=${de.deploymentId}&imageName=${de.diagramResourceName}" />查看</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<form action="workFlowController/newdeploy"
			enctype="multipart/form-data" method="POST">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="30"><table width="100%" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td height="24" bgcolor="#353c44"><table width="100%"
										border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td><table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="6%" height="19" valign="bottom"><div
																align="center">																
															</div></td>
														<td width="94%" valign="bottom"><span>部署流程定义</span></td>
													</tr>
												</table></td>
											<td><div align="right">
													<span ></span>
												</div></td>
										</tr>
									</table></td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td>
						<div align="left">
							流程名称：<input type="text" name="filename" style="width:200px;"><br />
							流程文件:<input type="file" name="file" style="width:200px;"><br />
							 <button type="submit" >上传流程</button>
						</div>
					</td>
				</tr>
			</table>


		</form>
	</div>
</body>
</html>
