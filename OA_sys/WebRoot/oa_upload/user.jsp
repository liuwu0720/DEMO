<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<base href="<%=basePath%>">

<title>上传文件 员工信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="js/jquery.autocomplete.js"></script>
	<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />

	<link rel="stylesheet" href="css/demo.css" type="text/css">
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	
<script type="text/javascript">

var partypes = [];

$(document).ready(function(){
			inits();
			
		});
		
function inits()
	{
		//var data;
		var uslist = ${uslist};
		$("#inpusname").autocomplete(uslist,{
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
								return row.deptname+" > "+ row.usname;
							}
					   }).result(function(event, item, formatted){
					   		//alert("item"+item.positionName+" id "+item.lineid+" formatted "+formatted);
					   		if(typeof(item) != "undefined")
					   		{
					   			$("#usid").val(item.lineid);
					   			//$("#inpusname").attr("readonly","readonly");
					   		}
						   
					   });
								   

			
			
	}

function adduser()
{
	//$("#ustextarea").trigger("click");
	var usid = $("#usid").val(); 
	var usname = $("#inpusname").val();
	//alert( usid+" >>　"+usname); 
	
	 
	 if(usid!="")
	 {
	 	
		var pt = new Object();
		pt.lineid = usid;
		pt.usname =  usname+" ;";
		partypes.push(pt);
		$("#usid").val("");
		$("#inpusname").val("");
		showusnames();
		//$("#inpusname").attr("readonly",false);
	 }

}

function showusnames()
{
	var ns = "";
	for(var i=0;i<partypes.length;i++)
	{
		ns += partypes[i].usname ;
		
	}
		
	$("#ustextarea").val(ns);
}

function saveClose()
{
	window.returnValue=partypes;
	window.close();
}
function aa()
{

}

</script>
</head>
<body>
		<form action="UploadServlet/getUserInfo" method="post">
		<table style="margin-top: 20px;">
		<tr align="center">
					 <td width="60%">
					 	 人员 ：<input id="inpusname" name="inpusname"  />
						<input id="usid" name="usid" type="hidden" />
					</td>
					<td width="10%"><input type="button" id="sub" onclick="adduser();" value="  添加  " /> </td> 
					<td width="2%">&nbsp;</td>
					<td width="10%"><input type="button"  style="margin-right: 10px;" id="sub" onclick="saveClose();" value="  确定并关闭  " /> </td>
		</tr>
		</table>
		</br>
		</br>
			
			<div style="width: 300px;">
				<span style="margin-left: 30px;">已选人员:</span>
				<textarea rows="5" cols="6" id="ustextarea" style="width: 360px;margin-left: 30px;height: 120px; " readonly="readonly" onclick="aa();"></textarea>
			</div>
		</form>
	
</body>
</html>
