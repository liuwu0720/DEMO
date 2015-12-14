<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="clt.com.cn.model.entity.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<HTML>
<HEAD>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<base href="<%=basePath%>">
	<TITLE>  人员档案映射  </TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="js/jquery.autocomplete.js"></script>
	<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />

	<link rel="stylesheet" href="css/demo.css" type="text/css">
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	
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
					rootPId:-1
				},
				key: {
					
					name:"deptname",
					title:"deptname"
					
				}
			}
		};

		var alllist =${deptlist};
		
		//var prlist = ${prlist};
		
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
		
		var zTree ;
		$(document).ready(function(){
		
			$.fn.zTree.init($("#treeDemo"), setting, alllist);
			
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			
			
			
			
			
		});
		
		
		
	function sub()
	{
			var deptids = "";
			var deptnames = "";
				var nodes = zTree.getCheckedNodes();
				
				
					for(var i=0;i<nodes.length;i++)
					{
						var n =  nodes[i];
						//判断该节点是否是全选状态    半选状态是表示  当父节点有2个子节点 只有一个子节点选中  则父节点处于半选状态 
						 if(!n.getCheckStatus().half)
						 {
							 deptids += n.lineid+",";
							 deptnames += n.deptname+" ; ";
						 }
					}
				var ob = new Object();
				ob.id = deptids;
				ob.name = deptnames;
				window.returnValue=ob;
				window.close();
	}
								
			
		
	
	
	
	</SCRIPT>
</HEAD>

<BODY style="background-color: #FFFFFF;">
<form action="EmployrecordServlet/saveEmployManager" method="post" id="myform">
	<table   style="width: 90%;margin-top: 50px;margin-left: 50px; "  >
		<tr>
			<td width="200px;">
				&nbsp;
			</td>
			<td width="50px;"></td>
			<td>
				<input type="button" onclick="sub();" value=" 确定  " >
			</td>
		</tr>
		<tr>
			<td width="200px;">
				
			</td>
			<td width="50px;"></td>
			<td>
					<div class="content_wrap" style="background-color: #FFFFFF;">
						<div class="zTreeDemoBackground left"  >
							<ul id="treeDemo" class="ztree" style="background-color: #FFFFFF;"></ul>
						</div>
				
					</div>
			</td>
		</tr>
	</table>

<input type="hidden" id="deptmappids" name="deptmappids">
</form>
</BODY>
</HTML>
