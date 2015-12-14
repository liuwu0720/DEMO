<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="clt.com.cn.model.entity.*"%>

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
	<title>企业管理-添加部门</title>

<link rel="stylesheet" type="text/css" href="./css/style.css" />
<link rel="stylesheet" type="text/css" href="./css/dtree.css">
	<script type="text/javascript" src="<%=path %>/js/dtree.js"></script>
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
			if($.trim($("#deptname").val())=="")
			{
				alert("部门名称不能为空！");
				$("#deptname").focus();
				return false;
			}else
			{
				return true;
			}
			
		});
	});

</script>
</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
 <a href="DeptServlet/getAddpage" class="hover">部门添加</a> 
</div>

<dl class="notice clearfix">
      
  <dd>部门资料 </dd>   
             
</dl>
<form  action="DeptServlet/add" method="post" id="myform">

<ul class="qy_info">
	<li><span>* 部门名称：</span><input name="deptname" type="text"  id="deptname"></li>
	
	<li><span>* 负责人：</span> 
		<select style="width: 120" name="managerSel" id="managerSel">
			<option value="0" selected="selected">---暂无---</option>
			<c:forEach items="${users}" var="us">
				<option value="${us[0]}">${us[1]}</option>
			</c:forEach>
		</select>
	 </li>
	
	
	
	<li><span>* 上级部门：</span>（默认为“中联物流”）<input name="pid" type="hidden" id="pid" value="0"></li>
	</ul>
	<div class="dtree" style="margin-left: 150px">
	<script type="text/javascript">
	function delete_add(uid)
		{	
			document.getElementById("pid").value=uid;	
		}
			d = new dTree('d');
			d.add(0,-1,'中联物流');
			<%
			List<Dept> list=(List)request.getAttribute("dept");
			for( Iterator<Dept> iter = list.iterator();iter.hasNext();)
			{	
				
				Dept dept =iter.next();
				if(dept.getLineid()!=0){
					%>
					d.add(<%=dept.getLineid()%>,<%=dept.getPid()%>,'<%=dept.getDeptname()%>','javascript:delete_add(<%=dept.getLineid()%>)');
					<%
					}
			}
			%>
			document.write(d);
	</script>
	</div>

	<br>
	<button type="submit" style="margin-left: 40px;width: 100px" class="tj add_btn">添 加</button>
	<button type="button" style="margin-left: 50px;width: 100px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		

</form>
</div>
</body>
</html>

