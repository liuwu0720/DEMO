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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
  </head>
  
  <body>
  <div class="rightsider">

<div class="info_tabs clearfix">
 <a href="" class="hover">外出审批</a> 
</div>

<dl class="notice clearfix">
      
  <dd>详细信息 </dd>   
             
</dl>
    	<form action="EmployoutServlet/updateEmo" method="post">
	    	<ul class="qy_info">
			
			<li><span>* 用户姓名：</span>${emo.opuser.employrecord.employname}</li>
			<li><span>* 部门：</span>${emo.dept.deptname}</li>
			
			<li><span>* 请假原因：</span>${emo.reason}</li>
			
			<li><span>* 请假开始时间：</span>${fn:substring(emo.date1 ,0,10)  }</li>
			
			<li><span>* 请假结束时间：</span>${fn:substring(emo.date2 ,0,10)  }</li>
			
			<li><span>* 申请日期：</span>${fn:substring(emo.currdate ,0,10)  }</li>
			
			<li><span><font color="red">* 进行审核：</font></span>
					<c:choose>
						<c:when test="${emo.ischeck==0}">
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
				<textarea style="width: 250;margin-left: 125px" name="checkremarks" id="checkremarks">${emo.checkremarks }
				</textarea>
			</li>
			
		</ul>
			
		<br>
			<input type="hidden" id="employid" name="employid" value="${emo.lineid }">
		<button type="button" id="sub" style="margin-left: 10px;height: 40px" class="tj add_btn" onclick="form.submit()">提交</button>
		<button type="button" style="margin-left: 10px;height: 40px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		</form>
</div>
   
  </body>
</html>
