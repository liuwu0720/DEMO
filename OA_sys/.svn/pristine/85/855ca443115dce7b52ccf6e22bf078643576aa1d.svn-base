<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    
    <title>账户管理--修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script language="javascript" type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		var xmlHttp;
		function createXMLHttpRequest() 
		{
			//表示当前浏览器不是ie,如ns,firefox
			if(window.XMLHttpRequest) 
			{
				xmlHttp = new XMLHttpRequest();
			} else if (window.ActiveXObject) 
			{
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}	
		}
	function pass(){

		if(document.getElementById("p1").value =="" ){
			alert("原密码不能为空");

		}
		else if(document.getElementById("p2").value == ""){
			alert("新密码不能为空");

		} else if (!/^\w{6,20}$/.test(document.getElementById("p2").value)){
			alert("新密码请输入6 ~ 20 字符【0-9】【a-z-A-Z】");
			
		}else if(document.getElementById("p3").value != document.getElementById("p2").value){
			alert("两次密码不一样");
	
		}  else {
			var name=document.getElementById("uname").value;
			var pass=document.getElementById("p1").value;
			createXMLHttpRequest();
			var url = "UserServlet/checkPass?uname="+name+"&upass="+pass+"&timestampt=" + new Date().getTime();
			xmlHttp.open("POST", url, true);
			//给ajax引擎一个联系方式
			xmlHttp.onreadystatechange=callback;
			xmlHttp.send(null);
		}

	}
	function callback() 
		{
	    if (xmlHttp.readyState == 4) {//ajax引擎准备完毕
		  if (xmlHttp.status == 200) {//请求servlet成功		  			  		
		  		var result = xmlHttp.responseText;
		  		if(result=="error")
		  		{
		  			alert("原密码错误!");
		  		}
		  		else if(result=="ok")
		  		{
		  			var lineid=document.getElementById("lineid").value;
		  			URL="${pageContext.request.contextPath }/UserServlet/updateUser?uid="+lineid;
		  			with(document.getElementById("updapass"))
					{
						action=URL;
						method="post";
						submit();
					}
		  		}
		  }
		}
	}	
		
		function ajaxSubmit()
		{
			var lineid=$.trim($("#lineid").val());
			var uname=$.trim($("#uname").val());
			var upass=$.trim($("#p1").val());
			var upass2=$.trim($("#p2").val());
			var upass3=$.trim($("#p3").val());
			
			if(upass=="")
			{
				alert("原密码不能为空");
				$("#p1").focus();
				return;
			}else if(upass2=="")
			{
				alert("新密码不能为空");
				$("#p2").focus();
				return;
			}else if(upass3=="")
			{
				alert("确认新密码不能为空");
				$("#p3").focus();
				return;
			}else if(upass2 != upass3)
			{
				alert("两次新密码不一致");
				$("#p2").focus();
				return;
			}
			
			
			
			$.ajax({
				type:"post",
				url:"<%=basePath%>UserServlet/checkPass",
				data:"uname="+uname+"&upass="+upass,
				dataType : "text",
				success:function(data)
				{
					if(data=="ok"){
						var newpass=$.trim($("#p2").val());
						if(newpass!="")
						{
							$("#updapass").submit();
						}
							
					}else
					{
						alert("该用户原密码错误,请重新输入");
						$("#p1").focus();
						
					}
				}	
			});
		}
</script>
  </head>
  
  <body>
   <div class="rightsider">

<div class="info_tabs clearfix">
  <a href="UserServlet/getPasswordPage" class="hover">修改密码</a> 
  <a href="EmployrecordServlet/getEmrById?id=${sessionScope.recordid}">个人资料</a> 

</div>


<dl class="notice clearfix">
      
  <dd>帐号管理 &gt; 修改密码 </dd>   
             
</dl>
<form id="updapass" name="myForm" method="post" action="${pageContext.request.contextPath }/UserServlet/updateUser">
   			<ul class="qy_info">
   			<li><span>* &nbsp;&nbsp;用&nbsp;&nbsp;户&nbsp;&nbsp;名：</span>${user.userno }</li>
   			<li><span>* &nbsp;&nbsp;原&nbsp;&nbsp;密&nbsp;&nbsp;码：</span><input type="password" id="p1" name="p1"></li>
   			<li><span>* &nbsp;&nbsp;新&nbsp;&nbsp;密&nbsp;&nbsp;码：</span><input type="password" id="p2" name="p2"></li>
   			<li><span>* &nbsp;&nbsp;确认新密码：</span><input name="password" type="password" id="p3"></li>
   			<li><span>* 上次修改时间：</span>${fn:substring(user.lastupdatedate,0,19) }</li>
			<li><span>* 密码过期类型：</span>密码永不过期</li>
			
   			
   			</ul>
   			<br>
   			<button style="margin-left: 20px;height: 40px;width: 80px;" class="tj add_btn" onClick="ajaxSubmit()">修改</button>
   			<button type="button" style="margin-left: 60px;height: 40px;width: 80px;" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
   			
   			<input type="hidden" value="${user.lineid }" id="lineid" name="lineid">
   			<input type="hidden" value="${user.userno }" id="uname" name="uname">
   			<input type="hidden" value="${user.admin }" id="admin" name="admin">
   			<input type="hidden" value="${user.roleid }" id="roleid" name="roleid">
   			<input type="hidden" value="${user.active }" id="active" name="active">
   		</form>
</div>
  </body>
</html>
