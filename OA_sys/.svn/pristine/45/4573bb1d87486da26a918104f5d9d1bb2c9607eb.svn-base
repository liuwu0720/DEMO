<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>文件上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	<script type="text/javascript">
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
		function fun(){
			if(document.getElementById("file").value==""){
				alert("上传文件不能为空！");
				return false;
			}else{
				return true;
			}
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
	</script>
  </head>
  
  <body>
   
<div class="rightsider">

<div class="info_tabs clearfix">
  
  
  <a href="oa_upload/upload.jsp" >文件上传</a> 
  <a href="UploadServlet/getSelfFile" class="hover">文件共享</a>
    <a href="UploadServlet/getShareFile">共享管理</a>

  

</div>



<dl class="notice clearfix">
      
  <dd>文件管理 &gt; 文件上传 </dd>   
             
</dl>
<form action="UploadServlet/shareFile" method="post" onsubmit="return fun()">
<ul class="qy_info">

<li><span>* 共享文件：</span><%String filename=request.getParameter("filename");
							filename = new String(filename.getBytes("iso8859-1"),"utf-8"); 
							out.println(filename);
							%>
							</li>
<li><span>* 共享类型：</span><select id="sharetype" name="sharetype" style="width: 225px" oninput="fswitch();" onchange="javascript:fswitch()"><option value="0">全部共享</option>
		<option value="1">共享部门</option><option value="2">共享人员</option>
	</select></li>
<li id="adddept" style="display:none;"><span>* 部门共享：</span><input type="text" id="deptname" style="width: 225px;" readonly="readonly">&nbsp;<input type="button" value="添加部门"  onClick="LoadWindow()" style="width: 70px"></li>
<li id="adduser" style="display:none;"><span>* 共享人员：</span><input type="text" id="username" style="width: 225px;" readonly="readonly">&nbsp;<input type="button" value="添加人员"  onClick="LoadUser()" style="width: 70px"></li>
<li><span>* 共享说明：</span></li>
</ul>
<textarea name="memo" cols="5" rows="5" style="width: 250;margin-left: 120" id="reason"></textarea>
<br><br>
<input type="hidden" id="deptid" name="deptid">
<input type="hidden" id="userid" name="userid">
<input type="hidden" id="fileid" name="fileid" value="${param.fileid }">
<button type="submit" class="tj" style="width: 150px;height: 30px">共享</button>
</form>
  </body>
</html>
