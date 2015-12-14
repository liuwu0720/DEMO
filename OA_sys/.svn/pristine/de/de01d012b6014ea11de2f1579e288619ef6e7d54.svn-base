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
    
    <title>My JSP 'getbyid.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
  </head>
  
  <body>
  <form action="EmployrecordCheckServlet/updateEhd" method="post">
  <div class="rightsider">

<div class="info_tabs clearfix">
 <a href="" class="hover">档案修改信息</a> 
</div>

<dl class="notice clearfix">
      
  <dd>详细信息 </dd>   
             
</dl>
    	
	    	<ul class="qy_info">
			
			<li><span>* 用户姓名：</span>${ehd[1]}</li>
			<li><span>* 原公司：</span>${ehd[2]}</li>
			
			<li><span>* 原部门：</span>${ehd[3]}</li>
			
			<li><span>* 原岗位：</span>${ehd[4]}</li>
			<li><span>* 原办公公司：</span>${ehd[5]}</li>
			<li><span>* 原办公地址：</span>${ehd[6]}</li>
			
			
			<li><span>* 公司：</span>${ehd[7]}</li>
			<li><span>* 部门：</span>${ehd[8]}</li>
			
			<li><span>* 岗位：</span>${ehd[9]}</li>
			
			<li><span>* 办公公司：</span>${ehd[10]}</li>
			<li><span>* 办公地址：</span>${ehd[11]}</li>
			
		<li><span><font color="red">* 进行审核：</font></span>
					<c:choose>
						<c:when test="${ehd[12]==0}">
							<select name="ischeck" id="ischeck">
							<option value="2">同意</option>
							<option value="1">不同意</option>
							</select>
						</c:when>
						<c:otherwise>
							<script type="text/javascript">
								$(document).ready(function(){
									$("#sub").attr("disabled",true);
									});
							</script>	
							已审批
						</c:otherwise>
					</c:choose>
					
			</li>
			
			<li><span>* 审核用户：</span>
				${ehd[14]}
			</li>
			
			<li><span>* 审核说明：</span>${ehd[13]}</li>
			
			<br>
		
		
		</ul>
		<input type="hidden" id="id" name="id" value="${ehd[0] }">
		 <button type="submit" id="sub" style="margin-left: 10px;height: 30px;width: 60px" class="tj add_btn" onclick="">审批</button>
		<button type="button" style="margin-left: 10px;height: 30px;width: 60px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
</div>
   </form>
  </body>
</html>
