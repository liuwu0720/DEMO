<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="clt.com.cn.model.entity.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	<link rel="stylesheet" type="text/css" href="./css/dtree.css">
	<script type="text/javascript" src="<%=path %>/js/dtree.js"></script>
	</head>
	<body>
	<div class="rightsider">

<div class="info_tabs clearfix">
  
 
  <a href="DeptServlet/getAllDeptByPage" class="hover">部门管理</a> 
  
  <a href="DeptServlet/getAddpage" class="usr_add"><i></i>添加</a>
  
</div>
<br><br>
  <div class="htsp">
	 <span><a href="DeptServlet/getDeptTree" class="hover">部门信息</a> <a href="DeptServlet/getAllDeptByPage?page">全部部门信息</a> </span>
  </div>


 <br><br>
	<div class="dtree" style="margin-left: 50px">
		<p>
			<a href="javascript: d.openAll();"><font size="2px">全部展开</font></a> |
			<a href="javascript: d.closeAll();"><font size="2px">全部收缩</font></a>
		</p>
		<br>
		<script type="text/javascript">
			d = new dTree('d');
			d.add(0,-1,'中联物流');
			<%
			List<Dept> list=(List)request.getAttribute("dept");
			for( Iterator<Dept> iter = list.iterator();iter.hasNext();)
			{	
				
				Dept dept =iter.next();
				if(dept.getLineid()!=0){
					%>
					d.add(<%=dept.getLineid()%>,<%=dept.getPid()%>,'<%=dept.getDeptname()%>','DeptServlet/getDeptByName2?deptname=<%=dept.getDeptname()%>');
					<%
					}
			}
			%>
			document.write(d);
	</script>

		</div>
		</div>
	</body>
</html>
