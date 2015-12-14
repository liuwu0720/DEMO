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
  <div class="rightsider">

<div class="info_tabs clearfix">
 <a href="" class="hover">请假条审批</a> 
</div>

<dl class="notice clearfix">
      
  <dd>详细信息 </dd>   
             
</dl>
    	<form action="EmployholidayServlet/updateEhd" method="post">
	    	<ul class="qy_info">
			
			<li><span>* 用户姓名：</span>${ehd.opuser.employrecord.employname}</li>
			<li><span>* 部门：</span>${ehd.dept.deptname}</li>
			
			<li><span>* 请假原因：</span>${ehd.reason}</li>
			
			<li><span>* 请假开始时间：</span>${fn:substring(ehd.date1 ,0,19)  }</li>
			
			<li><span>* 请假结束时间：</span>${fn:substring(ehd.date2 ,0,19)  }</li>
			
			<li><span>* 天数：</span>${ehd.days }</li>
			
			<li><span>* 申请日期：</span>${fn:substring(ehd.currdate ,0,10)  }</li>
			
			<li><span><font color="red">* 进行审核：</font></span>
					<c:choose>
						<c:when test="${ehd.ischeck==0}">
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
			
			<li><span>* 审核备注：</span><br>
				<textarea style="width: 250;margin-left: 125px" name="checkremarks" id="checkremarks">${ehd.checkremarks }
				</textarea>
			</li>
			
			<br>
		</ul>
		<input type="hidden" id="empholidayid" name="empholidayid" value="${ehd.lineid }">
		<button type="button" id="sub" style="margin-left: 10px;height: 40px" class="tj add_btn" onclick="form.submit()">提交</button>
		<button type="button" style="margin-left: 10px;height: 40px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		</form>
</div>
   
  </body>
</html>
