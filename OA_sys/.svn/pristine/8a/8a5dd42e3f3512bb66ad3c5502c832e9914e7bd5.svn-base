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
  </head>
  
  <body>
  <form action="EmploytripServlet/getUpdatePage" method="post">
  <div class="rightsider">

<div class="info_tabs clearfix">
 <a href="" class="hover">出差信息</a> 
</div>

<dl class="notice clearfix">
      
  <dd>详细信息 </dd>   
             
</dl>
    	
	    	<ul class="qy_info">
			
			<li><span>* 用户姓名：</span>${ehd.opuser.employrecord.employname}</li>
			<li><span>* 部门：</span>${ehd.dept.deptname}</li>
			
			<li><span>* 出差地址：</span>${ehd.address}</li>
			
			<li><span>* 乘坐工具：</span>${ehd.employtripTool.toolname}</li>
			<li><span>* 出差事由：</span>${ehd.reason}</li>
			<li><span>* 备注：</span>${ehd.remarks}</li>
			
			
			<li><span>* 开始时间：</span>${fn:substring(ehd.date1 ,0,19)  }</li>
			<li><span>* 结束时间：</span>${fn:substring(ehd.date2 ,0,19)  }</li>
			
			<li><span>* 申请日期：</span>${fn:substring(ehd.currdate ,0,10)  }</li>
			
			<li><span>* 审核状态：</span>
					<c:forEach items="${stamap}" var="sta">
			     		<c:if test="${ehd.ischeck==sta.key}">
			     			<span class="unpassing">${sta.value }</span>
			     		</c:if>
			     	</c:forEach>
			</li>
			
			<li><span>* 审核用户：</span>
				<c:choose>
					<c:when test="${ehd.actualuser != null }">
						${ehd.actualuser.employrecord.employname }
					</c:when>
					<c:otherwise>
						${ehd.checkuser.employrecord.employname}
					</c:otherwise>
				</c:choose>
			</li>
			
			<li><span>* 审核说明：</span>${ehd.checkremarks }</li>
			
			<br>
		
		
		</ul>
		<input type="hidden" id="id" name="id" value="${ehd.lineid }">
		 <button type="submit" style="margin-left: 10px;height: 30px;width: 60px" class="tj add_btn" onclick="">审批</button>
		<button type="button" style="margin-left: 10px;height: 30px;width: 60px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
</div>
   </form>
  </body>
</html>
