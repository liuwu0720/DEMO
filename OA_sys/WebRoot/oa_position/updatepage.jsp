<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>修改信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

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
			if($.trim($("#po").val()) =="" ){
				alert("职位名称不能为空");
				$("#po").focus();
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
 <a href="PositionServlet/getAllPosition" class="hover">职位修改</a> 
</div>

<dl class="notice clearfix">
      
  <dd>职位资料 </dd>   
             
</dl>
    	<form action="${pageContext.request.contextPath }/PositionServlet/updatePosition?lineid=${po.lineid }" method="post"  id="myform">
	    	<ul class="qy_info">
			<li><span>* 职位名称：</span><input name="positionname" value="${po.positionname}" id="po"></li>
			<br>
		<button type="submit" style="margin-left: 10px;height: 40px" class="tj add_btn">修改</button>
		<button type="button" style="margin-left: 10px;height: 40px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		</ul>
		
    	</form>
</div>
</body>
</html>
