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

<link rel="stylesheet" type="text/css" href="./css/style.css" />

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

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
	$(document).ready(function(){
		$("#myform").submit(function(){
			if($.trim($("#ussel").val()) ==0 ){
				alert("用户不能为空");
				$("#ussel").focus();
				return false;
			}else {
				return true;
			}
			
		});
	});
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
 <a href="EmployAccredit/getauthorizerByadd" class="hover">考勤授权</a> 
</div>

<dl class="notice clearfix">
      
  <dd>用户资料 </dd>   
             
</dl>
<form  action="EmployAccredit/addauthorizer" method="post" id="myform">

<ul class="qy_info">
	<li><span>* 被授权人：</span> 
	
	<select id="ussel" name="ussel" >
		<option value="0"> ---请选择---</option>
		<c:forEach items="${uslist}" var="us">
			<option value="${us[0]}">&nbsp;&nbsp;&nbsp; ${us[1]} </option>	
		</c:forEach>
	</select>
	 </li>
</ul>
<br>
	<button type="submit" style="margin-left: 50px;width: 100px" class="tj add_btn">确定</button>
	<button type="button" style="margin-left: 50px;width: 100px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		

</form>
</div>
</body>
</html>

