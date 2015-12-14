<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<HTML>
<HEAD>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<base href="<%=basePath%>">
	<TITLE>  部门映射  </TITLE>
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
					rootPId:-1
				},
				key: {
					
					name:"deptname",
					title:"deptname"
					
				}
			}
		};

		var alllist =${alldeptlist};
		
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
		function getAllSelNodes()
		{
		
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = zTree.getCheckedNodes();
			//alert(">>>"+nodes.length);
			for(var i=0;i<nodes.length;i++)
			{
				var n =  nodes[i];
				alert("id "+n.lineid+" >> "+n.deptname);
				//判断该节点是否是全选状态    半选状态是表示  当父节点有2个子节点 只有一个子节点选中  则父节点处于半选状态 
				 if(!n.getCheckStatus().half)
				 {
				 	alert("全选");
				 }else
				 {
				 	alert("半选");
				 }
			} 
		}
		
		
		function selchange(sel)
		{
			zTree.checkAllNodes(false);
			zTree.expandAll(false);
			
			$.ajax({
				type:"post",
				url:"<%=basePath%>DeptServlet/getLowerByDeptID",
				data:"deptid="+sel.value,
				dataType : "json",
				success:function(data)
				{
				if(data.length == 0)
				{
					//zTree.expandAll(true);
				}else
				{
					 for(var i=0;i<data.length;i++)
						 {
							var nodes = data[i];
							for(var j=0;j<alllist.length-1;j++)
							{
							
								if(nodes.lineid == alllist[j].lineid)
								{
									var n1 =zTree.getNodeByParam("lineid", nodes.lineid, null);
									//  1 是否選中  2 是否和父节点联动 
									zTree.checkNode(n1, true, true);
									var re = zTree.expandNode(n1,true,true,true);
									if(re == null)
									{
										zTree.expandNode(n1.getParentNode(),true,true,true);
									}
									
								}
							}
						 } 
					}
					
				}	
			});
		}
		
		$(document).ready(function(){
			$("#myform").submit(function(){
					var deptmappids = "";
					var nodes = zTree.getCheckedNodes();
					if($("#deptid").val() == "-1")
					{
						alert("请选择部门.");
						$("#deptid").focus();
						return false;
					}
					else if(nodes.length == 0)
					{
						alert("未选择 对应部门");
						return false;
					}else
					{
						for(var i=0;i<nodes.length;i++)
						{
							var n =  nodes[i];
							//判断该节点是否是全选状态    半选状态是表示  当父节点有2个子节点 只有一个子节点选中  则父节点处于半选状态 
							 if(!n.getCheckStatus().half)
							 {
							 	deptmappids += n.lineid+",";
							 }
						}
					}
					$("#deptmappids").val(deptmappids);
					//alert($("#deptmappids").val());				
			});
		});
		
	</SCRIPT>
</HEAD>

<BODY>
<form action="DeptServlet/saveDeptMapping" method="post" id="myform">
	<table   style="width: 90%;margin-top: 50px;margin-left: 50px; "  >
		<tr>
			<td width="200px;">
				<select id="deptid" name="deptid" onchange="selchange(this);">
				<option value="-1">---请选择--- </option>
					<c:forEach items="${hqlist}" var="str">
						<option value="${str[0]}">${str[1]}</option>
					</c:forEach>
				</select>
			</td>
			<td width="50px;"></td>
			<td>
				<input type="submit" value=" 保 存   " >
			</td>
		</tr>
		<tr>
			<td width="200px;">
				
			</td>
			<td width="50px;"></td>
			<td>
					<div class="content_wrap">
						<div class="zTreeDemoBackground left">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
				
					</div>
			</td>
		</tr>
	</table>

<input type="hidden" id="deptmappids" name="deptmappids">
</form>
</BODY>
</HTML>