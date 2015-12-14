<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../taglib.jsp"%>
<!DOCTYPE HTML >
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/stylesheets/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/stylesheets/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/stylesheets/admin.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/leginfo.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/validator.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/easyui-lang-zh_CN.js"></script>
<title>My JSP 'legInfolist.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
body {
	margin: 0;
	padding: 0;
}
</style>
</head>

<body>
	<!-- 线路表 -->
	<div id="leginfo_tabs">
		<div title="线路列表" style="padding:0px;overflow:hidden; color:red; ">
			<div id="test"></div>
		</div>
	</div>

	<div id="leg" class="easyui-dialog"
		style="width:500px;height:420px;padding:10px 60px 20px 60px;"
		closed="true" buttons="#dlg-buttons5">
		<form id="legfm" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table class="table table-bordered table-condensed table-hover">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="id"></input>
					</td>
				</tr>
				<tr>
					<td>出发地：</td>
					<td><input class="easyui-validatebox" type="text"
						name="origin" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>目的地：</td>
					<td><input class="easyui-validatebox" type="text"
						name="destination" data-options="required:true"></input></td>
				</tr>

				<tr>
					<td>收入里程：</td>
					<td><input class="easyui-validatebox" type="text"
						name="incomeDistance"
						data-options="required:true,validType:'number'"
						missingMessage="不能为负数"></input></td>
				</tr>
				<tr>
					<td>实际里程：</td>
					<td><input class="easyui-validatebox" type="text"
						name="actualDistance"
						data-options="required:true,validType:'number'"
						missingMessage="不能为负数"></input></td>
				</tr>
				<tr>
					<td>空载里程：</td>
					<td><input class="easyui-validatebox" type="text"
						name="emptyDistance"
						data-options="required:true,validType:'number'"
						missingMessage="不能为负数"></input></td>
				</tr>

			</table>
		</form>
	</div>
	<div id="dlg-buttons5">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" id="saveBtn"
			iconCls="icon-ok" onclick="saveLeg()" style="width:90px">Save</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#leg').dialog('close')"
			style="width:90px">Cancel</a>
	</div>
</body>

</html>
