<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	
	<script type="text/javascript">
		function LoadWindow()
		{
		  URL="<%=basePath%>DeptServlet/getAllDeptUpload";
		  loc_x=document.body.scrollLeft+event.clientX-event.offsetX-100;
		  loc_y=document.body.scrollTop+event.clientY-event.offsetY+170;
		  var retvalue =  window.showModalDialog(URL,window,"edge:raised;scroll:1;status:0;help:0;resizable:1;dialogWidth:500px;dialogHeight:500px;dialogTop:"+loc_y+"px;dialogLeft:"+loc_x+"px");
			if(typeof(retvalue) != "undefined")
			{
				// alert("ret "+retvalue.id+" "+retvalue.name);
				  // 部门名称    部门id
				  $("#deptname").val(retvalue.name);
				  $("#deptids").val(retvalue.id);
				
			}
		 
		  
		}
		function LoadUser()
		{
		  URL="<%=basePath%>UploadServlet/getUserInfo";
		  loc_x=document.body.scrollLeft+event.clientX-event.offsetX-100;
		  loc_y=document.body.scrollTop+event.clientY-event.offsetY+170;
		  var retvalue = window.showModalDialog(URL,window,"edge:raised;scroll:1;status:0;help:0;resizable:1;dialogWidth:430px;dialogHeight:300px;dialogTop:"+loc_y+"px;dialogLeft:"+loc_x+"px");
		  var unames = "";
		  var usids = "";
		  
		  if(typeof(retvalue) != "undefined")
		  {
			  for(var i=0;i<retvalue.length;i++)
			   {
				  var obj= retvalue[i];
				  unames += obj.usname;
				  usids += obj.lineid+",";
				}
			 //  alert(usids);
			  $("#username").val(unames);
			  $("#userids").val(usids);
			  
		  }
		 
		}
		
		function fun()
		{
			if(document.getElementById("file").value==""){
				alert("上传文件不能为空！");
				return false;
			}else{
				return true;
			}
		}
		
	function fswitch()
	{
		var a=$("#sharetype").val();
		if(a==1){
			$("#adddept").css("display","inline");
			$("#adduser").css("display","none");
		}else if(a==2){
			$("#adddept").css("display","none");
			$("#adduser").css("display","inline");
		}
		else{
			$("#adddept").css("display","none");
			$("#adduser").css("display","none");
		}
	}
	
	function fun()
	{
		if(document.getElementById("file").value==""){
			alert("上传文件不能为空！");
			return false;
		} 
		
		var shartype = $("#sharetype").val();
		if(shartype==1)
		{
			var deptids =  $.trim($("#deptids").val());
			if(deptids=="")
			{
				alert("共享部门不能为空.");
				return false;
			}
		}else if(shartype==2)
		{
			var userids =  $.trim($("#userids").val());
			if(userids=="")
			{
				alert("共享人员不能为空.");
				return false;
			}
		
		}else
		{
			return true;
			
		}
		
		
		
	}
	
	  function keyevent(){
		  alert(event.keyCode);
	      if(event.keyCode==8)
	      	return false;
	      }
	     // document.onkeydown = keyevent;
	</script>
  </head>
  
  <body>
   
<div class="rightsider">

<div class="info_tabs clearfix">
  
  
  <a href="oa_upload/upload.jsp" class="hover">文件上传</a> 
  <!-- <a href="UploadServlet/getSelfFile">文件共享</a> --> 
 <a href="UploadServlet/getShareFile">共享管理</a>
  

</div>



<dl class="notice clearfix">
      
  <dd>文件管理 &gt; 文件上传 </dd>   
             
</dl>
<form action="UploadServlet/upload" enctype="multipart/form-data" method="post" onsubmit="return fun()">
<ul class="qy_info">

<li><span>* 文件①：</span><input name="file[0]" type="file" style="width: 300px" id="file"></li>
<li><span>* 文件②：</span><input name="file[1]" type="file" style="width: 300px" id="file"></li>
<li><span>* 文件③：</span><input name="file[2]" type="file" style="width: 300px" id="file"></li>
<li><span>* 共享类型：</span><select id="sharetype" name="sharetype" style="width: 225px"  onchange="javascript:fswitch()">
		<option value="0">全部共享</option>
		<option value="1">共享部门</option><!-- <option value="2">共享人员</option> --> 
		<option value="2">共享人员</option>
	</select></li>
<li id="adddept" style="display:none;" >
	<span style="margin-bottom: 40px;">* 部门共享：</span>
	<textarea name="deptname" cols="5" rows="2" style="width: 300px;" id="deptname" readonly="readonly"></textarea>
	<input type="button" value="添加部门"  onClick="LoadWindow();" style="width: 70px;margin-bottom: 40px;">
</li>
<li id="adduser" style="display:none;">
	<span  style="margin-bottom: 40px;">* 共享人员：</span>
	<textarea name="username" cols="5" rows="2" style="width: 300px;" id="username" readonly="readonly"></textarea>
	<input type="button"  value="添加人员"  onClick="LoadUser();" style="width: 70px;margin-bottom: 40px;">
</li>

<li><span>* 共享说明：</span></li>
</ul>
<textarea name="memo" cols="5" rows="5" style="margin-left: 120px;width: 300px;" id="reason"></textarea>
<br><br>
<input type="hidden" id="deptid" name="deptid">
<input type="hidden" id="userid" name="userid">
<input type="hidden" id="deptids" name="deptids">
<input type="hidden" id="userids" name="userids">

<button type="submit" class="tj" style="width: 150px;height: 30px">上传</button>
</form>
  </body>
</html>
