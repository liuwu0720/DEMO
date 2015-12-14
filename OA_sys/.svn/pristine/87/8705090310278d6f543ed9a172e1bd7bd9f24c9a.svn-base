<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>新增新闻</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />
	
	<script language="javascript" type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>

	<script charset="utf-8" src="<%=path %>/kindeditor/kindeditor.js"></script> 

	<script charset="utf-8" src="<%=path %>/kindeditor/lang/zh_CN.js"></script>
<script>  
//--------------------KindEditor
        var editor;  

        KindEditor.ready(function(K) {  

                editor = K.create('#editor_id',{
                
                resizeType:0
                });
               

        }); 
        var editor = K.create('textarea[name="content"]', options); 
        // 取得HTML内容
        html=editor.html();
        //同步数据后可以直接取得textarea的value 
		editor.sync();
		html = document.getElementById('editor_id').value; // 原生API
		html = K('#editor_id').val(); // KindEditor Node API
		html = $('#editor_id').val(); // jQuery 
		// 设置HTML内容
		editor.html('HTML内容');
		
//------------------------------
		function LoadWindow()
		{
		  URL="<%=basePath%>DeptServlet/getAllDeptUpload";
		  loc_x=document.body.scrollLeft+event.clientX-event.offsetX-100;
		  loc_y=document.body.scrollTop+event.clientY-event.offsetY+170;
		  window.showModalDialog(URL,window,"edge:raised;scroll:1;status:0;help:0;resizable:1;dialogWidth:300px;dialogHeight:230px;dialogTop:"+loc_y+"px;dialogLeft:"+loc_x+"px");
		}
		function LoadUser()
		{
		   URL="<%=basePath%>UploadServlet/getUserInfo";
		  loc_x=document.body.scrollLeft+event.clientX-event.offsetX-100;
		  loc_y=document.body.scrollTop+event.clientY-event.offsetY+170;
		  window.showModalDialog(URL,window,"edge:raised;scroll:1;status:0;help:0;resizable:1;dialogWidth:300px;dialogHeight:230px;dialogTop:"+loc_y+"px;dialogLeft:"+loc_x+"px");
		}

	function fswitch(){
	var a=document.getElementById("sharetype").value;
	if(a==1){
		var o = document.getElementById("adddept");
		if (o){
			if (o.style.display == "none"){ o.style.display = "block"; }
		}
		var o1 = document.getElementById("adduser");
		if (o1){
			if (o1.style.display == "block"){ o1.style.display = "none"; }
		}
	}else if(a==2){
		var o = document.getElementById("adduser");
		if (o){
			if (o.style.display == "none"){ o.style.display = "block"; }
		}
		var o1 = document.getElementById("adddept");
		if (o1){
			if (o1.style.display == "block"){ o1.style.display = "none"; }
		}
	}
	else{
		var o = document.getElementById("adddept");
		if (o){
			if (o.style.display == "block"){ o.style.display = "none"; }
		}
		var o1 = document.getElementById("adduser");
		if (o1){
			if (o1.style.display == "block"){ o1.style.display = "none"; }
		}
	}
}

//-------------------------------------------
function login(){
	if(document.getElementById("title").value==""){
		alert("新闻标题不能为空！");
		document.getElementById("title").focus();
		return false;
	}else if(document.getElementById("d11").value==""){
		alert("通知时间不能为空！");
		document.getElementById("d11").focus();
		return false;
	}else if(document.getElementsByName("content").value==""){
		alert("新闻内容不能为空！");
		document.getElementById("content").focus();
		return false;
	}else{
		return true;
	}

}

</script> 


</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
  <a href="NotifyServlet/getAllNotify" >新闻管理</a>
  <a href="oa_news/addnews.jsp" class="hover">新建新闻</a>

</div>



<dl class="notice clearfix">
      
  <dd>新闻管理 &gt; 新建新闻</dd>   
             
</dl>

 <form action="NotifyServlet/addNotify" enctype="multipart/form-data" method="post" onsubmit="return login()">
<ul class="qy_info">
	<li><span>* 标题：</span><input name="title" type="text"  id="title"></li>
	
	<li><span>* 类型：</span><select name="type" style="width: 200px"><option value="0">通&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;知</option><option value="1">公司新闻</option><option value="2">行业动态</option></select></li>
	<li><span>* 附件上传：</span><input type="file" name="file"></li>
	<li><span>* 发布范围：</span>
		<select id="sharetype" name="sharetype" style="width: 200px" oninput="fswitch();" onchange="javascript:fswitch()">
			<option value="0">全部共享</option>
				<!-- 
			<option value="1">共享部门</option>
			<option value="2">共享人员</option>
			 -->
	</select></li>
<li id="adddept" style="display:none;"><span>* 部门共享：</span><input type="text" id="deptname" style="width: 225px;" readonly="readonly">&nbsp;<input type="button" value="添加部门"  onClick="LoadWindow()" style="width: 70px"></li>
<li id="adduser" style="display:none;"><span>* 共享人员：</span><input type="text" id="username" style="width: 225px;" readonly="readonly">&nbsp;<input type="button" value="添加人员"  onClick="LoadUser()" style="width: 70px"></li>
	
	<li><span>* 通知时间：</span><input name="notifydate" type="text" id="d11" style="width: 200px" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"><img src="./My97DatePicker/skin/datePicker.gif" width="24" height="33" align="absmiddle" style="cursor:pointer" onClick="WdatePicker({el:'d11',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/></li>
	
</ul>
<input type="hidden" id="deptid" name="deptid">
<input type="hidden" id="userid" name="userid">
<br>
<textarea id="editor_id" name="content" style="width:700px;height:300px;"></textarea>
<br><br>
<button type="submit" class="tj" style="width: 150px;height: 30px">发布</button>
</form>
</div>
</body>
</html>
