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
	 
		 
		function checkPosName()
		{
			var posName = $.trim($("#po").val());
			if(posName =="" ){
				alert("职位名称不能为空");
				$("#po").focus();
				return false;
			}else {
				
				$.ajax({
					type:"post",
					url:"<%=basePath%>PositionServlet/checkPosName",
					data:"posName="+posName,
					dataType : "json",
					success:function(data)
					{
						
						if(data.result!="ok"){
							alert("该岗位已存在.");
							$("#po").focus();
						}else
						{
							 
							$("#myform").submit(); 
						}
					}	
				});
				
			}
			 
		}
		 
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
 <a href="oa_position/addposition.jsp" class="hover">职位添加</a> 
</div>

<dl class="notice clearfix">
      
  <dd>职位资料 </dd>   
             
</dl>
<form  action="PositionServlet/add" method="post" id="myform">

<ul class="qy_info">
	<li><span>* 职位名称：</span><input name="positionname" type="text"  id="po"></li>
</ul>
<br>
	<button type="button" onclick="checkPosName();" style="margin-left: 50px;width: 100px" class="tj add_btn">添 加</button>
	<button type="button" style="margin-left: 50px;width: 100px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		

</form>
</div>
</body>
</html>

