<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<HTML>
<HEAD>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<base href="<%=basePath%>">
	<TITLE> ZTREE DEMO - checkbox</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href=css/demo.css" type="text/css">
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script>
	
	<SCRIPT type="text/javascript">
		
		var setting = {
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
				
			},
			data: {
				simpleData: {
					enable:true,
					idKey:"lineid",
					pIdKey:"pid",
					rootPId:0
				},
				key: {
					
					name:"privilegename",
					title:"title"
					
				}
			}
		};

		var alllist =${modulelist};
		
		var prlist = ${prlist};
		
		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			py = $("#py").attr("checked")? "p":"",
			sy = $("#sy").attr("checked")? "s":"",
			pn = $("#pn").attr("checked")? "p":"",
			sn = $("#sn").attr("checked")? "s":"",
			type = { "Y":py + sy, "N":pn + sn};
			zTree.setting.check.chkboxType = type;
			showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
		}
		
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, alllist);
			
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			for(var i=0;i<prlist.length-1;i++)
			{
				var nodes = prlist[i];
				for(var j=0;j<alllist.length-1;j++)
				{
					if(nodes.lineid == alllist[j].lineid)
					{
						//zTree.expandNode(false,false,false)
						//var n = zTree.getNodeByTId("3");
						var n1 =zTree.getNodeByParam("lineid", nodes.lineid, null);
					
						zTree.checkNode(n1, true, true);
						
					}
				}
			}
			
			
			
			
		});
		function getAllSelNodes()
		{
		
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = zTree.getCheckedNodes();
			alert(">>>"+nodes.length);
			for(var i=0;i<nodes.length;i++)
			{
				var n =  nodes[i];
				alert("id "+n.lineid+" >> "+n.title);
			} 
		}
	</SCRIPT>
</HEAD>

<BODY>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	
</div>
<input type="button" value="getAll ." onclick="getAllSelNodes();">
</BODY>
</HTML>