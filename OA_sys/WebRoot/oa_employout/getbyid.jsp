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
  </head>
  
  <body>
  <form action="EmployoutServlet/getUpdatePage" method="post">
  <div class="rightsider">

<div class="info_tabs clearfix">
 <a href="" class="hover">请假条信息</a> 
</div>

<dl class="notice clearfix">
      
  <dd>详细信息 </dd>   
             
</dl>
    	
	    	<ul class="qy_info">
			
			<li><span>* 用户姓名：</span>${emo.opuser.employrecord.employname}</li>
			<li><span>* 部门：</span>${emo.dept.deptname}</li>
			<li><span>* 请假原因：</span>${emo.reason}</li>
			
			
			<li><span>* 请假开始时间：</span>${fn:substring(emo.date1 ,0,10)  }</li>
			
			<li><span>* 请假结束时间：</span>${fn:substring(emo.date2 ,0,10)  }</li>
			
			<li><span>* 申请日期：</span>${fn:substring(emo.currdate ,0,10)  }</li>
			
			<li><span>* 审核状态：</span>
				<c:if test="${emo.ischeck==0 }">未审核</c:if>
				<c:if test="${emo.ischeck==1 }">未通过</c:if>
				<c:if test="${emo.ischeck==2 }">已通过</c:if>
			</li>
			
			
			<li><span>* 审核用户：</span>
				<c:choose>
					<c:when test="${emo.actualuser != null }">
						${emo.actualuser.employrecord.employname }
					</c:when>
					<c:otherwise>
						${emo.checkuser.employrecord.employname}
					</c:otherwise>
				</c:choose>
			</li>
			
			<li><span>* 审核说明：</span>${emo.checkremarks }</li>
			
			<br>
		
		</ul>
		
</div>
<input type="hidden" id="id" name="id" value="${emo.lineid }">
    <button type="submit" style="margin-left: 10px;height: 30px;width: 60px" class="tj add_btn" onclick="">审批</button>
		<button type="button" style="margin-left: 10px;height: 30px;width: 60px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
	</form>
  </body>
</html>
