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
	<TITLE>  部门负责人审批管理 </TITLE>
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
			},
			callback:{
				onClick: onClick
			}
		};

		var alllist =${deptlist};
		
		//var prlist = ${prlist};
		
		
		var zTree ;
		$(document).ready(function(){
			inits();
			$.fn.zTree.init($("#treeDemo"), setting, alllist);
			
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			
			
			
		});
	
		
		function onClick(event, treeId, treeNode, clickFlag)
		{
			var deptid = treeNode.lineid;
			var deptname = treeNode.deptname;
			$("#deptids").val(deptid);
		
			selchange(deptid);
		}
		
	function sub()
	{
			var userid = $("#userID").val();
			var deptids = $("#deptids").val();
			if(userid=="" || deptids=="")
			{
				alert("审批人或者部门未选中.");
				return false;
			}else
			{
				$("#myform").submit();
			}
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
					   			$("#userID").val(item.id);
					   			
					   		}else
					   		{
					   			$("#userID").val("");
					   		}
						   
					   });
								   

			
			
	}
	
	function selchange(deptid)
		{
			
			
			$.ajax({
				type:"post",
				url:"<%=basePath%>DeptServlet/getManagerApproveByID",
				data:"deptid="+deptid,
				dataType : "json",
				success:function(data)
				{
					
					if(typeof(data.uname)!='undefined')
					{
						//alert(data.uname);
						$("#usname").val(data.uname);	
						$("#userID").val(data.id);
					}else
					{
						$("#usname").val("无");
						$("#userID").val("");
					}
				}	
			});
		}
	</SCRIPT>
</HEAD>

<BODY>
<form action="DeptServlet/saveManagerApprove" method="post" id="myform">
	<table   style="width: 90%;margin-top: 50px;margin-left: 50px; "  >
		<tr>
			<td width="500px;">
			<div style="float: left;width: 500px;">
					人员:	<input type="text" id="usname" name="usname" size="30">
						<input type="hidden" id="userID" name="userID"  >
						<input type="button" onclick="sub();" value=" 保 存   " >
			</div>
				
			</td>
			<td width="50px;"></td>
			<td>
				
			</td>
		</tr>
		<tr>
			
			<td>
					<div class="content_wrap">
						<div class="zTreeDemoBackground left">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
				
					</div>
			</td>
			<td width="200px;">
				
			</td>
			<td width="50px;"></td>
		</tr>
	</table>

<input type="hidden" id="deptids" name="deptids">
</form>
</BODY>
</HTML>