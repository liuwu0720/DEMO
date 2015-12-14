<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../taglib.jsp"%>
<!DOCTYPE HTML >
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascripts/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascripts/easyui-lang-zh_CN.js" ></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/stylesheets/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/stylesheets/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/stylesheets/admin.css">

<style>
article,aside,figure,footer,header,hgroup,menu,nav,section {
	display: block;
}

.west {
	width: 200px;
	padding: 10px;
}

.north {
	height: 100px;
}

</style>
<script type="text/javascript">


	$(function() {
		//动态菜单数据
		var treeData = [ {
			text : "菜单",
			children : [ {
				text : "线路基本信息",
				attributes : {
					url : "<%=basePath%>legInfo.do"
				}
			}, {
				text : "商品车基本信息",
				attributes : {
					url : "<%=basePath%>car.do"
				}
			}, {
				text : "拖车基本信息",
				attributes : {
					url : "<%=basePath%>truck.do"
				}
			}, {
				text : "线路与商品车",
				attributes : {
					url : "<%=basePath%>legCarInfo.do"
				}
			}, {
				text : "线路与拖车",
				attributes : {
					url : "<%=basePath%>legTruckInfo.do"
				}
			} ]
		} ];

		//实例化树形菜单
		$("#tree").tree({
			data : treeData,
			lines : true,
			onClick : function(node) {

				if (node.attributes) {
					Open(node.text, node.attributes.url);
				}
			}

		});
		//在右边center区域打开菜单，新增tab
		function Open(text, url) {
			if ($("#tabs").tabs('exists', text)) {
				$('#tabs').tabs('select', text);
				
			} else {
				if (url){  
		            var content = '<iframe  scrolling="yes" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';  
		        } else {  
		            var content = '未实现';  
		        }  
				
				$('#tabs').tabs('add', {
					title : text,
					closable : true,
					selected:true,
					content:content
				});
			}
		}
		
		
		/**     
		 * 刷新tab 
		 * @cfg  
		 *example: {tabTitle:'tabTitle',url:'refreshUrl'} 
		 *如果tabTitle为空，则默认刷新当前选中的tab 
		 *如果url为空，则默认以原来的url进行reload 
		 */  
		function refreshTab(cfg){  
		    var refresh_tab = cfg.tabTitle?$('#tabs').tabs('getTab',cfg.tabTitle):$('#tabs').tabs('getSelected');  
		    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
		    var _refresh_ifram = refresh_tab.find('iframe')[0];  
		    var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;  
		    //_refresh_ifram.src = refresh_url;  
		    _refresh_ifram.contentWindow.location.href=refresh_url;  
		    }  
		}  
		
		//绑定tabs的右键菜单
		$("#tabs").tabs({
			onContextMenu : function(e, title) {
				e.preventDefault();
				$('#tabsMenu').menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data("tabTitle", title);
			}
		});

		//实例化menu的onClick事件
		$("#tabsMenu").menu({
			onClick : function(item) {
				CloseTab(this, item.name);
			}
		});

		//几个关闭事件的实现
		function CloseTab(menu, type) {
			var curTabTitle = $(menu).data("tabTitle");
			var tabs = $("#tabs");

			if (type === "close") {
				tabs.tabs("close", curTabTitle);
				return;
			}

			var allTabs = tabs.tabs("tabs");
			var closeTabsTitle = [];

			$.each(allTabs, function() {
				var opt = $(this).panel("options");
				if (opt.closable && opt.title != curTabTitle
						&& type === "Other") {
					closeTabsTitle.push(opt.title);
				} else if (opt.closable && type === "All") {
					closeTabsTitle.push(opt.title);
				}
			});

			for ( var i = 0; i < closeTabsTitle.length; i++) {
				tabs.tabs("close", closeTabsTitle[i]);
			}
		}
	});
</script>
</head>

<body class="easyui-layout">
	<div region="north" class="north">
		<h1>后台管理</h1>
	</div>

	<div id="main" region="center" title="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页"></div>

		</div>
	</div>

	<div region="west" class="west" title="menu">
		<ul id="tree"></ul>
	</div>

	<div id="tabsMenu" class="easyui-menu" style="width:120px;">
		<div name="close">关闭</div>
		<div name="Other">关闭其他</div>
		<div name="All">关闭所有</div>
	</div>
</body>
</html>
