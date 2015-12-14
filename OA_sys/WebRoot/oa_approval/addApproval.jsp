<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<title>企业管理-用户管理</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.autocomplete.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />


<script type="text/javascript">
	$(document).ready(function(){
		
		inits();
	});
	
	function inits()
	{
		var datestr = ${uslist};
		$("#usName").autocomplete(datestr,{
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
												return row.employname;
											}
									   }).result(function(event, item, formatted){
									   		//alert("item"+item.positionName+" id "+item.lineid+" formatted "+formatted);
									   		if(typeof(item) != "undefined")
									   		{
									   			$("#usid").val(item.lineid);
									   		}
										   
									   });
								   
	
	}
	
				
				
	function addRow()
	{
		var tab2 = $("#tab2");
		var usid = $("#usid").val();
		var usName = $("#usName").val();
		var lengt = tab2.find("tr").length;
		var usstr = $("input[id='usids']");
		if(usstr.length>0)
		{
			for(var i=0 ; i < usstr.length;i++)
			{
				if(usstr[i].value == usid)
				{
					alert("重复添加人员.");
					return false;
				} 
			}
		}
		
		var rowstr = "<tr id="+usid+"> "+
						"<td>"+lengt+"</td> "+
						"<td>"+usName+"</td> "+
						"<td><a style='cursor: pointer;' onclick='javascript:del("+usid+")'>  删除 </a></td> "+
						"<td><input type='hidden' id='usids' name='usids' value="+usid+"></td> "+
					"</tr>";
		tab2.append(rowstr);
		
	}
	function del(obj)
	{
		  $("#tab2 tr[id="+obj+"]").remove(); 
		  var rows = $("#tab2 tr");
		  for(var i=1 ; i < rows.length;i++)
		  {
		  	   var row = rows[i];
		  	   row.cells[0].innerText=i;
		  }
	}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
 <a   class="hover">审批模式添加</a> 
</div>

<dl class="notice clearfix">
      
  <dd>审批模式 </dd>   
             
</dl>
<form  action="ApprovalServlet/add" method="post" id="myform">
<div style="width: 90%;">
<table id="tab1" style="width: 300px;border: 1;float: left;margin-top: 10px;">
	<tr>
		<td> 模式名称：<input name="approvalName" type="text"  id="approvalName"></td>
		<td>
			
		</td>
	</tr>
	<tr >
		<td>
			 审 批 人： <input type="text" id="usName" name="usName"><input type="hidden" id="usid" name="usid">
		</td>
		<td>
			<input type="button" id="sub" onclick="addRow();" value=" 添加 ">
		</td>
	</tr>
</table>

<table id="tab2" style="width: 400px;float:left; margin-left: 50px;margin-top: 10px;">
<tbody>
	<tr>
		<td>序号</td>
		<td>姓名</td>
		<td>操作</td>
		<td></td>
	</tr>
</tbody>
</table>
</div>
<br>
	<button type="submit" style="margin-left: 50px;width: 100px" class="tj add_btn">  保  存 </button>
	<button type="button" style="margin-left: 50px;width: 100px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		

</form>
</div>
</body>
</html>

