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
			inits();
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
		
		
		
		
	function sub()
	{
				var deptmappids = "";
					var nodes = zTree.getCheckedNodes();
					if($.trim($("#usnameID").val()) == "")
					{
						alert("请选择人员.");
						$("#usname").focus();
						return false;
					}
					else
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
					$("#myform").submit();
	}
								
			
		
	function inits()
	{
		//var data;
		var uslist = ${uslist};
					
		$("#usname").autocomplete(uslist,{
					   		minChars: 0,  			//在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
					   		//width:200,			//指定下拉框的宽度. Default: input元素的宽度
					   		max:10,					//autoComplete下拉显示项目的个数.Default: 10
							//delay:20,				//击键后激活autoComplete的延迟时间(单位毫秒).Default: 远程为400 本地10
							//autoFill:true,		//要不要在用户选择时自动将用户当前鼠标所在的值填入到input框. Default: false
							mustMatch:true,		// 如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
							matchContains:true,	//决定比较时是否要在字符串内部查看匹配,如ba是否与foo bar中的ba匹配.使用缓存时比较重要.不要和autofill混用.Default: false
							selectFirst:true,	//如果设置成true,在用户键入tab或return键时autoComplete下拉列表的第一个值将被自动选择,尽管它没被手工选中(用键盘或鼠标).当然如果用户选中某个项目,那么就用用户选中的值. Default: true
							scroll:true,			//当结果集大于默认高度时是否使用卷轴显示 Default: true
							scrollHeight:200,		//自动完成提示的卷轴高度用像素大小表示 Default: 180
							formatItem:function(row, i, n){
								//return "哈哈 "+row.positionName;
								return row.uname;
							}
					   }).result(function(event, item, formatted){
					   		//alert("item"+item.positionName+" id "+item.lineid+" formatted "+formatted);
					   		if(typeof(item) != "undefined")
					   		{
					   			$("#usnameID").val(item.id);
					   			 selchange(item.id);
					   			
					   		}else
					   		{
					   			$("#usnameID").val("");
					   			selchange(-1);
					   		}
						   
					   });
								   

			
			
	}
	
	function selchange(usid)
		{
			zTree.checkAllNodes(false);
			zTree.expandAll(false);
			
			$.ajax({
				type:"post",
				url:"<%=basePath%>EmployrecordServlet/getDeptByusID",
				data:"usid="+usid,
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
	</SCRIPT>
</HEAD>

<BODY>
<form action="EmployrecordServlet/saveEmployManager" method="post" id="myform">
	<table   style="width: 90%;margin-top: 50px;margin-left: 50px; "  >
		
		
		<tr>
			<td width="200px;">
					人员:	<input type="text" id="usname" name="usname" size="30">
						<input type="hidden" id="usnameID" name="usnameID"  >
			</td>
			<td width="50px;"></td>
			<td>
				<input type="button" onclick="sub();" value=" 保 存   " >
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