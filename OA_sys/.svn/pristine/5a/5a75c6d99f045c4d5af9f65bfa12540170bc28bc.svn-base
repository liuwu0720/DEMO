<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>修改信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

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
			
		})
	});

</script>
  </head>
  
  <body>
<div class="rightsider">

<div class="info_tabs clearfix">
 <a href="DeptServlet/getAllDeptByPage" class="hover">部门修改</a> 
</div>

<dl class="notice clearfix">
      
  <dd>部门资料 </dd>   
             
</dl>
    	<form action="${pageContext.request.contextPath }/DeptServlet/updateDept?lineid=${dept.lineid }" method="post" id="myform">
	    	<ul class="qy_info">
			
			<li><span>* 部门编号：</span><input name="lineid" id="lineid" value="${dept.lineid }" disabled="true"></li>
			
			<li><span>* 部门名称：</span><input name="deptname" id="deptname" value="${dept.deptname }"></li>
			
			<li><span>* 负责人：</span>
				<select style="width: 120" name="managerSel" id="managerSel">
					
					<option value="0" selected="selected">---暂无---</option>
					<c:forEach items="${users}" var="us">
						<c:choose>
							<c:when test="${us[0]==dept.manageruser.lineid}">
								<option value="${us[0]}" selected="selected">${us[1]}</option>
							</c:when>
							<c:otherwise>
								<option value="${us[0]}" >${us[1]}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</li>
			
			<li><span>* 是否有效：</span>
			<select name="active" style="width: 200px">
			<c:if test="${dept.active==0 }">
			<option value="0">--无效--</option>
			<option value="1">--有效--</option>
			</c:if>
			<c:if test="${dept.active==1 }">
			<option value="1">--有效--</option>
			<option value="0">--无效--</option>
			</c:if> 
			</select>
			</li>
			
			<li><span>* 上级部门：</span><input name="pid" id="pid" value="${parDept.deptname }" disabled="disabled">
			<input type="hidden" id="ppid" name="ppid" value="${parDept.lineid }">
			</li>
			<!-- 
			<div class="dtree" style="margin-left: 150px">
	<script type="text/javascript">
	function delete_add(uid)
		{	
			$("#pid").val(uid);
			$("#ppid").val(uid);
			alert($("#ppid").val());	
		}
			d = new dTree('d');
			d.add(0,-1,'中联物流','javascript:delete_add(0)');
		
			document.write(d);
	</script>
	</div>
			 -->
			
			
		</ul>
		<br>
		<button type="submit" style="margin-left: 50px;width:100px; height: 40px" class="tj add_btn">修改</button>
		<button type="button" style="margin-left: 30px;width:100px;height: 40px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
    	</form>
</div>
</body>
</html>
