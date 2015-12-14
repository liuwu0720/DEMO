<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" href="css/style.css"  type="text/css">
		<link rel="stylesheet" href="css/demo.css"   type="text/css">
		<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
		
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
	
	
		
	
<SCRIPT type="text/javascript">
		var  mode=document.compatMode;  //浏览器解析模式   <!DOCTYPE html>返回有两个值BackCompat代表触发的是诡异模式，CSS1Compat代表触发的是标准模式。

		var curMenu = null, zTree_Menu = null;
		var setting = {
			view: {
				showLine: false,			//设置 zTree 是否显示节点之间的连线
				showIcon: false,		//设置 zTree 是否显示节点的图标
				selectedMulti: false,  // 设置是否允许同时选中多个节点
				dblClickExpand: false,
				addDiyDom: addDiyDom
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			},
			callback: {
				beforeClick: beforeClick
			}
		};

	var zNodes = ${meaujson};
		function addDiyDom(treeId, treeNode) {
			var spaceWidth = 5;
			var switchObj = $("#" + treeNode.tId + "_switch"),
			icoObj = $("#" + treeNode.tId + "_ico");
			switchObj.remove();
			icoObj.before(switchObj);
			
			if (treeNode.level > 0) {
				var spaceStr = "<span style='display: inline-block;width:" + (treeNode.level * 10)+ "px'></span>";
				switchObj.before(spaceStr);
			}
		}

		function beforeClick(treeId, treeNode) {
		//如果该节点是父节点   展开该节点
			var ispar = treeNode.isParent;
			if (ispar ) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.expandNode(treeNode);
				return false;
			}
			return true;
		}

		$(document).ready(function(){
			var treeObj = $("#treeDemo");
			$.fn.zTree.init(treeObj, setting, zNodes);
			zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
			
			curMenu = zTree_Menu.getNodes()[0].children[0];
			zTree_Menu.selectNode(curMenu);

			treeObj.hover(function () {
				if (!treeObj.hasClass("showIcon")) {
					treeObj.addClass("showIcon");
				}
			}, function() {
				treeObj.removeClass("showIcon");
			});
		});
	</SCRIPT>
	
	<style type="text/css">
			.ztree * {font-size: 10pt;font-family:"Microsoft Yahei",Verdana,Simsun,"Segoe UI Web Light","Segoe UI Light","Segoe UI Web Regular","Segoe UI","Segoe UI Symbol","Helvetica Neue",Arial}
			.ztree li ul{ margin:0; padding:0;}
			.ztree li {line-height:30px;}
			.ztree li a {width:200px;height:30px;padding-top: 0px;}
			.ztree li a:hover {text-decoration:none; background-color: #E7E7E7;}
			.ztree li a span.button.switch {visibility:hidden}
			.ztree.showIcon li a span.button.switch {visibility:visible}
			.ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
			.ztree li span {line-height:30px;}
			.ztree li span.button {margin-top: -7px;}
			.ztree li span.button.switch {width: 16px;height: 16px;}
			
			.ztree li a.level0 span {font-size: 110%;font-weight: bold;}
			.ztree li span.button {background-image:url("css/zTreeStyle/img/left_menuForOutLook.png"); *background-image:url("css/zTreeStyle/img/left_menuForOutLook.gif")}
			.ztree li span.button.switch.level0 {width: 20px; height:20px}
			.ztree li span.button.switch.level1 {width: 20px; height:20px}
			.ztree li span.button.noline_open {background-position: 0 0;}
			.ztree li span.button.noline_close {background-position: -18px 0;}
			.ztree li span.button.noline_open.level0 {background-position: 0 -18px;}
			.ztree li span.button.noline_close.level0 {background-position: -18px -18px;}
	</style>
	</head>
	<body style="overflow: hidden;" >

  <div class="leftsider">
<div class="top">
   <ul class="usr_does clearfix">
   
      <li>
      <!-- <a href="NotifyServlet/getSelNotify" target="mainFrame"><i class="icon_news"></i><span id="count"></span></a> -->
      	<a href="ModuleServlet/gorightBefore" target="mainFrame"><i class="icon_news"></i><span id="count"></span></a>新信息</li>
      <li><a href="UserServlet/getPasswordPage" target="mainFrame"><i class="icon_account"></i></a>账号管理</li>
      <li><a href="UserServlet/logOutUser" target="_parent"><i class="icon_logout"></i></a>安全退出</li>
  
   </ul>
 </div>
   
   
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	
</div>

</div>

<script type="text/javascript" >
	
	
	$(document).ready(fun);

	function fun() {
	    $.get("<%=basePath%>GetCountServlet/getCount?time=" + new Date().getTime(),{
			
		},function(count){
			//var jsonObj = eval("(" + count + ")");
			//var strCount = jsonObj.count;
			$("#count").html(count);
		},false);
	    setTimeout(fun, 1000000);
	}
	
	
</script>
</body>
</html>
