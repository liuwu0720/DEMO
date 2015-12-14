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
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>企业管理-用户管理</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />

<!--[if IE 6]><script src="http://stjscn.s-msn.com/portal/wp/DD_belatedPNG.js" type="text/javascript"></script><![endif]-->
<!--[if lt IE 6]>
<link href="skins/ie.css" type="text/css" rel="stylesheet" />
<![endif]-->
<!--[if lt IE 7]>
<link href="skins/ie.css" type="text/css" rel="stylesheet" />
<![endif]-->
<!--[if lt IE 8]>
<link href="skins/ie.css" type="text/css" rel="stylesheet" />
<![endif]-->

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

		if(document.getElementById("p1").value == ""){
			alert("密码不能为空");
			document.getElementById("p1").focus();
		}else if (!/^\w{6,20}$/.test(document.getElementById("p1").value)){
			alert("请输入6 ~ 20 字符【0-9】【a-z-A-Z】");
			document.getElementById("p1").focus();
		}
		 else if(document.getElementById("p2").value != document.getElementById("p1").value){
			alert("两次密码不一样");
			document.getElementById("p2").focus();
		}
		else {
			var name=document.getElementById("uname").value;
			createXMLHttpRequest();
			var url = "UserServlet/checkUser?uname="+name+"&timestampt=" + new Date().getTime();
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
		  			alert("该员工已分配用户!");
		  			addUser.upass.value="";
		  			addUser.upass1.value="";
		  		}
		  		else if(result=="ok")
		  		{
		  			URL="${pageContext.request.contextPath }/UserServlet/addUser";
  					with(document.getElementById("addUser"))
					{
						action=URL;
						method="post";
						submit();
					}
		  		}
		  }
		}
	}	
	
		
</script>
</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
 <a href="addusers.jsp" class="hover">用户添加</a> 
</div>

<dl class="notice clearfix">
      
  <dd>用户资料 </dd>   
             
</dl>
<form id="addUser">

<ul class="qy_info">
<input type="hidden" name="recordid" value="${param.recordid }" >
<input type="hidden" name="uname" value="${param.userno }" id="uname">
<li><span>* 用户名：</span>${param.userno }
			</li>
			<li><span>* 密  码：</span><input name="upass" type="password" id="p1">
			</li>
			<li><span>* 确认密码：</span><input name="upass1" type="password" id="p2">
			</li>
	<br>
	<button type="button" style="margin-left: 10px;height: 40px" class="tj add_btn" onClick="pass()">添 加</button>
	<button type="button" style="margin-left: 10px;height: 40px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		
</ul>
</form>
</div>
</body>
</html>

