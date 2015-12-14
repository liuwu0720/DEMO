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
	
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	</head>
	<script type="text/javascript">
		function showAllperson(divname,deptid)
		{
			if(divname=="left")
			{
				$("#leftid").val(deptid);	
			}
			if(divname=="righ")
			{
				$("#rightid").val(deptid);	
			}
				
			$.ajax({
				type:"post",
				url:"<%=basePath%>DeptServlet/getDeptPersonByID",
				data:"deptid="+deptid,
				dataType : "json",
				success:function(data)
				{
					$("#"+divname)[0].innerText=data.names;
				}	
			});
			
		}
		function sub()
		{
			var lid = $("#leftid").val();
			var rid = $("#rightid").val();
			if(lid==0)
			{
				alert("左边未选择部门.");
				return false;
			}
			if(rid==0)
			{
				alert("右边未选择部门.");
				return false;
			}
			if(lid==rid)
			{
				alert("所选部门不能一样.");
				return false;
			}
			$.ajax({
				type:"post",
				url:"<%=basePath%>DeptServlet/saveDeptPerson",
				data:"lid="+lid+"&rid="+rid,
				dataType : "json",
				success:function(data)
				{
					if(data.result=="ok")
					{
						alert("修改成功.");
					}else
					{
						alert("修改失败"+data.result);	
					}
					window.location.reload(); 
				}	
			});
			
		}
	</script>
	<body>
	<div class="rightsider">

<div class="info_tabs clearfix">
  
 
  <a href="javascript:void();" class="hover">部门人员调整</a> 
  
  
</div>
<br><br>
<div style="float: right;">
	<span style="margin-left: 10px;">从左边选择部门，将人员调整到右边部门. </span> &nbsp;&nbsp;&nbsp;<button type="button" onclick="sub();" style="margin-right:100px;width: 100px;height: 30px;" class="tj add_btn">保存</button>
</div>


 <br><br>
 <table style="width: 90%;border: 1px solid red;">
 	<tr>
 		<td>
 			<div class="dtree" style="margin-left: 50px;float: left;">
		<p>
			<input type="hidden" name="leftid"  id="leftid" value="0">
			<a href="javascript: d.openAll();"><font size="2px">全部展开</font></a> |
			<a href="javascript: d.closeAll();"><font size="2px">全部收缩</font></a>
		</p>
		<br>
	<script type="text/javascript">
			d = new dTree('d');
			d.add(0,-1,'中联物流');
			<%
			List<Dept> list=(List)request.getAttribute("deptlist");
			for( Iterator<Dept> iter = list.iterator();iter.hasNext();)
			{	
				
				Dept dept =iter.next();
				if(dept.getLineid()!=0){
					%>
					d.add(<%=dept.getLineid()%>,<%=dept.getPid()%>,"<%=dept.getDeptname()%>","javascript:showAllperson('left',<%=dept.getLineid()%>)");
					
					<%
					}
			}
			%>
			document.write(d);
	</script>
		
		</div>
 		</td>
 		<td style="width: 50px;">
 			<div style="width: 50px;"> </div>
 		
 		</td>
 		
 		<td>
 			<div class="dtree" style="margin-left: 200px;float: left;">
		<p>
			<input type="hidden" name="rightid"  id="rightid" value="0">
			<a href="javascript: d1.openAll();"><font size="2px">全部展开</font></a> |
			<a href="javascript: d1.closeAll();"><font size="2px">全部收缩</font></a>
		</p>
		<br>
	<script type="text/javascript">
			d1 = new dTree('d1');
			d1.add(0,-1,'中联物流');
			<%
			List<Dept> list2=(List)request.getAttribute("deptlist");
			for( Iterator<Dept> iter = list2.iterator();iter.hasNext();)
			{	
				
				Dept dept =iter.next();
				if(dept.getLineid()!=0){
					%>
					d1.add(<%=dept.getLineid()%>,<%=dept.getPid()%>,"<%=dept.getDeptname()%>","javascript:showAllperson('righ',<%=dept.getLineid()%>)");
					<%
					}
			}
			%>
			document.write(d1);
	</script>

		</div>
 		
 		</td>
 		
 	</tr>
 	
 	<tr>
 		<td>
 			<br/>
 			该部门人员:
 			<div id="left" style="text-align: center;"> </div>
 		</td>
 		<td>
 			<div style="width: 50px;">  </div>
 		</td>
 		<td >
 			<br/>
 			该部门人员:
 			<div id="righ" style="text-align: center;"> </div>
 		</td>
 	</tr>
 </table>
 
		</div>
	</body>
</html>
