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
		
		$(document).ready(function(){
			initleft();
			initright();
		
			
		});
		
		
		
		
		
	function sub()
	{
			var usid = $.trim($("#usnameid").val());
			var usid1 = $.trim($("#usnameid1").val());
			//alert(usid+" >>"+usid1);
			if(usid=="" || usid1 =="")
			{
				alert("选择的用户为空");
				return;
			}else
			{
				$("#myform").submit();
			}
				
	}
	
								
			
		
	function initleft()
	{
		//var data;
		var uslist = ${uslist};
					
		$("#usname").autocomplete(uslist,{
					   		minChars: 0,  			//在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
					   		//width:200,			//指定下拉框的宽度. Default: input元素的宽度
					   		max:10,					//autoComplete下拉显示项目的个数.Default: 10
							mustMatch:true,		// 如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
							matchContains:true,	//决定比较时是否要在字符串内部查看匹配,如ba是否与foo bar中的ba匹配.使用缓存时比较重要.不要和autofill混用.Default: false
							selectFirst:true,	//如果设置成true,在用户键入tab或return键时autoComplete下拉列表的第一个值将被自动选择,尽管它没被手工选中(用键盘或鼠标).当然如果用户选中某个项目,那么就用用户选中的值. Default: true
							scroll:true,			//当结果集大于默认高度时是否使用卷轴显示 Default: true
							scrollHeight:200,		//自动完成提示的卷轴高度用像素大小表示 Default: 180
							formatItem:function(row, i, n){
								//return "哈哈 "+row.positionName;
								return row.usname;
							}
					   }).result(function(event, item, formatted){
					   		//alert("item"+item.positionName+" id "+item.lineid+" formatted "+formatted);
					   		if(typeof(item) != "undefined")
					   		{
					   			$("#usnameid").val(item.lineid);
					   			
					   			selchange(item.lineid);
					   		}
					   });
								   

			
			
	}
	function initright()
	{
		//var data;
		var uslist = ${uslist};
					
		$("#usname1").autocomplete(uslist,{
					   		minChars: 0,  			//在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
					   		//width:200,			//指定下拉框的宽度. Default: input元素的宽度
					   		max:10,					//autoComplete下拉显示项目的个数.Default: 10
							mustMatch:true,		// 如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
							matchContains:true,	//决定比较时是否要在字符串内部查看匹配,如ba是否与foo bar中的ba匹配.使用缓存时比较重要.不要和autofill混用.Default: false
							selectFirst:true,	//如果设置成true,在用户键入tab或return键时autoComplete下拉列表的第一个值将被自动选择,尽管它没被手工选中(用键盘或鼠标).当然如果用户选中某个项目,那么就用用户选中的值. Default: true
							scroll:true,			//当结果集大于默认高度时是否使用卷轴显示 Default: true
							scrollHeight:200,		//自动完成提示的卷轴高度用像素大小表示 Default: 180
							formatItem:function(row, i, n){
								//return "哈哈 "+row.positionName;
								return row.usname;
							}
					   }).result(function(event, item, formatted){
					   		//alert("item"+item.positionName+" id "+item.lineid+" formatted "+formatted);
					   		if(typeof(item) != "undefined")
					   		{
					   			$("#usnameid1").val(item.lineid);
					   			
					   			
					   		}
					   });
								   

			
			
	}
	
	function selchange(usid)
	{
			
			$.ajax({
				type:"post",
				url:"<%=basePath%>UserServlet/getUserApproveByusID",
				data:"usid="+usid,
				dataType : "json",
				success:function(data)
				{
					
					if(typeof(data.usid)!='undefined')
					{
						//alert(data.uname);
						$("#usname1").val(data.usname);	
						$("#usnameid1").val(data.usid);
					}else
					{
						$("#usname1").val("无");
						$("#usnameid1").val("");
					}
				}	
			});
		}
		
	</SCRIPT>
</HEAD>

<BODY>
<form action="UserServlet/saveUserApprove" method="post" id="myform">
	<table   style="width: 90%;margin-top: 50px;margin-left: 50px; "  >
		<tr>
			<td width="200px;">
					用户:	<input type="text" id="usname" name="usname" size="30">
						<input type="hidden" id="usnameid" name="usnameid"  >
			</td>
			<td width="50px;"></td>
			<td width="200px;">
					审批人:	<input type="text" id="usname1" name="usname1" size="30">
						<input type="hidden" id="usnameid1" name="usnameid1"  >
			</td>
			<td>
				<input type="button" onclick="sub();" value=" 保 存   " >
			</td>
		</tr>
		
	</table>

<input type="hidden" id="deptmappids" name="deptmappids">
</form>
</BODY>
</HTML>