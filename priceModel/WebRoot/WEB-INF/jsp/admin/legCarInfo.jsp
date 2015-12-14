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
	src="${pageContext.request.contextPath}/static/js/legcarinfo.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/validator.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/easyui-lang-zh_CN.js"></script>

<title>My JSP 'legCarInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body{
margin: 0;
padding: 0;
}

</style>
</head>

<body>
	<!-- 商品车与线路关联表 -->
	<div id="legcar_tabs">
		<div title="线路与商品车" style="padding:0px;overflow:hidden; color:red; ">
		  <input id="legcar" type="text"/>  
			<div id="legcar3"></div>
		</div>
	</div>
	
	<!-- 点编辑时弹出的表单 -->
	<div id="legcardiv" class="easyui-dialog"
		style="width:500px;height:420px;padding:10px 60px 20px 60px;"
		closed="true" buttons="#dlg-buttons2">
		<form id="legcarfm" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table class="table table-bordered table-condensed table-hover">
				<tr style="display: none">
					<td>id</td>
					<td><input class="easyui-validatebox" type="text" name="ID"></input>
					</td>
				</tr>
				<tr>
					<td>收入：</td>
					<td><input class="easyui-validatebox" type="text" data-options="required:true,validType:'number'"
						
						name="INCOMEPRICE"></input></td>
				</tr>
				<tr>
					<td>当前采购支出：</td>
					<td><input class="easyui-validatebox" type="text" data-options="required:true,validType:'number'"
						name="VENDORCOST"></input></td>
				</tr>
				<tr>
					<td>出现概率：</td>
					<td><input class="easyui-validatebox" type="text" name="RATIO" data-options="required:true,validType:'number'"></input>
					</td>
				</tr>

			</table>
		</form>
	</div>
	<div id="dlg-buttons2">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" id="saveBtn"
			iconCls="icon-ok" onclick="editCarInfoSave()" style="width:90px">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#legcardiv').dialog('close')"
			style="width:90px">Cancel</a>
	</div>

	<!-- 点新增时弹出的表单 -->
	<div id="legcardiv2" class="easyui-dialog"
		style="width:500px;height:420px;padding:10px 60px 20px 60px;"
		closed="true" buttons="#dlg-buttons22">
		<form id="legcarfm2" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table class="table table-bordered table-condensed table-hover">
				<tr>
					<td>选择商品车</td>
					<td><input id="ccc"  value="请选择" name="CARID">  
					</td>
				</tr>
				<tr>
					<td>收入：</td>
					<td><input class="easyui-validatebox" type="text" data-options="required:true,validType:'number'"
						name="INCOMEPRICE"></input></td>
				</tr>
				<tr>
					<td>当前采购支出：</td>
					<td><input class="easyui-validatebox" type="text" data-options="required:true,validType:'number'"
						name="VENDORCOST"></input></td>
				</tr>
				<tr>
					<td>出现概率：</td>
					<td><input class="easyui-validatebox" type="text" data-options="required:true,validType:'number'" name="RATIO" ></input>
					</td>
				</tr>

			</table>
		</form>
	</div>
	<div id="dlg-buttons22">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" id="saveBtn"
			iconCls="icon-ok" onclick="addCarInfoSave()" style="width:90px">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#legcardiv2').dialog('close')"
			style="width:90px">Cancel</a>
	</div>
</body>
</html>
