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
	src="${pageContext.request.contextPath}/static/js/legtruckinfo.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/validator.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/easyui-lang-zh_CN.js"></script>

<title>My JSP 'legTruckInfo.jsp' starting page</title>

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
	<!-- 商品车与线路关联表 -->
	<div id="legtruck_tabs">
		<div title="线路与商品车" style="padding:0px;overflow:hidden; color:red; ">
			<input id="legtruck" />
			<div id="legtruck3"></div>
		</div>
	</div>


	<!-- 点编辑时弹出的表单 -->
	<div id="legtruckdiv" class="easyui-dialog"
		style="width:500px;height:420px;padding:10px 60px 20px 60px;"
		closed="true" buttons="#dlg-buttons3">
		<form id="legtruckfm" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table class="table table-bordered table-condensed table-hover">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="ID"></input>
					</td>
				</tr>

				<tr>
					<td>每公里满载成本：</td>
					<td><input class="easyui-validatebox" type="text"
						data-options="required:true,validType:'number'" name="FULLCOST"></input>
					</td>
				</tr>
				<tr>
					<td>每公里空载成本：</td>
					<td><input class="easyui-validatebox" type="text"
						name="EMPTCOST" data-options="required:true,validType:'number'"></input>
					</td>
				</tr>

			</table>
		</form>
	</div>
	<div id="dlg-buttons3">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" id="saveBtn"
			iconCls="icon-ok" onclick="editTruckInfoSave()" style="width:90px">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#legtruckdiv').dialog('close')"
			style="width:90px">Cancel</a>
	</div>

	<!-- 点新增时弹出的表单 -->
	<div id="legtruckdiv2" class="easyui-dialog"
		style="width:500px;height:420px;padding:10px 60px 20px 60px;"
		closed="true" buttons="#dlg-buttons33">
		<form id="legtruckfm2" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table class="table table-bordered table-condensed table-hover">
				<tr>
					<td>选择线路</td>
					<td><input id="bbb"  value="请选择" name="LEGID">  
					</td>
				</tr>
				<tr>
					<td>每公里满载成本：</td>
					<td><input class="easyui-validatebox" type="text" data-options="required:true,validType:'number'"
						name="FULLCOST"></input></td>
				</tr>
				<tr>
					<td>每公里空载成本：</td>
					<td><input class="easyui-validatebox" type="text" data-options="required:true,validType:'number'"
						name="EMPTCOST"></input></td>
				</tr>
			
			</table>
		</form>
	</div>
	<div id="dlg-buttons33">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" id="saveBtn"
			iconCls="icon-ok" onclick="addTruckInfoSave()" style="width:90px">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#legtruckdiv2').dialog('close')"
			style="width:90px">Cancel</a>
	</div>
</body>
</html>
