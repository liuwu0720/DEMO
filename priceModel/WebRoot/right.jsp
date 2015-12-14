<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>


<title>My JSP 'right.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<script type="text/javascript">
	function showDemo() {
		parent.show();
	}
	function hideDemo(){
		parent.hide();
	}
</script>
</head>

<body>
	<!-- 商品车详细信息 -->
	<div class="panel panel-primary"
		style="margin: 10px; display: block;height: 80%;">
		<div class="panel-heading">
			<h3 class="panel-title">操作步骤说明</h3>
		</div>
		<div class="panel-body">
			<blockquote>
				<p>一、选择输入线路方式：1、导入文件方式；2、用户自己输入（最多6条）</p>
				<p>二、修改线路判断某些线路是否加入空载</p>
				<p>三、非空载线路请选择商品车并保存</p>
				<p>四、选择计算方式、选择拖车、填写单公里空载成本、计算值</p>
				<p>五、点击【开始计算】按钮</p>
			</blockquote>
			<button id="caculate" class="btn btn-primary btn-sm" type="button"
				onclick="showDemo()">开始演示</button>
			<button id="caculate" class="btn btn-primary btn-sm" type="button"
				onclick="hideDemo()">结束演示</button>	
		</div>
	</div>
</body>
</html>
