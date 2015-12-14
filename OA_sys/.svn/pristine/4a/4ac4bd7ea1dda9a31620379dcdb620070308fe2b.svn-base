<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	<link rel="stylesheet" type="text/css" href="css/style.css" />-->
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	
	<script type="text/javascript">
		function sures(id)
		{
				var params = document.getElementById("params").value;
			
            	window.location="${pageContext.request.contextPath }/EmployrecordServlet/getNewEmpCord?params="+params;
		  		
		}
		
		function hoysures()
		{
			
            window.location="${pageContext.request.contextPath }/EmployholidayServlet/getNewHoliday";
		  		
		}
		function showDate(url)
		{
			window.location="${pageContext.request.contextPath }/"+url;
		}
		
	</script>
  </head>  
  <body>
	
	<table  width="100%">
		<tr>
			<td width="400px;">
					<table  style="margin-top: 100px;">
						<tr align="center">
							<td colspan="4" align="center"> --待办事项--</td>
							<td></td>
						</tr>
						<tr>
							<td width="200px;" bgcolor="#F0F0F0" align="center"><span>类别 </span></td>
							<td width="100px;" bgcolor="#F0F0F0" align="center"><span> 条数</span></td>
						</tr>
						<c:if test="${newEmpcount>0}" >
							<tr>
								<td width="200px;" bgcolor="#F0F0F0" align="center"><span> 新员工入职通知  </span></td>
								<td width="100px;" bgcolor="#F0F0F0" align="center"><span> <a href="javascript:sures();">( ${newEmpcount} )</a></span></td>
							</tr>
						</c:if>
						
						<c:forEach items="${noticemaps}" var="sta">
							<c:forEach items="${noticeSta}" var="s">
								<c:if test="${s.key==sta.key}">
									<tr>
										<td width="200px;" bgcolor="#F0F0F0" align="center"><span>${fn:split(s.value,',')[0]}  </span></td>
										<td width="100px;" bgcolor="#F0F0F0" align="center"><span> <a href="javascript:showDate('${fn:split(s.value,',')[1]}');">( ${sta.value} )</a></span></td>
									</tr>
								</c:if>
							</c:forEach>
						</c:forEach>
					
				</table>
			</td>
			<td width="50px;" bgcolor=""></td>
			<td>
				<table  style="margin-top: 100px;">
						<tr align="center">
							<td colspan="4" align="center"> --待审批--</td>
							<td></td>
						</tr>
						<tr>
							<td width="200px;" bgcolor="#F0F0F0" align="center"><span>类别 </span></td>
							<td width="100px;" bgcolor="#F0F0F0" align="center"><span> 条数</span></td>
						</tr>
						<c:forEach items="${maps}" var="sta">
							<c:forEach items="${stamaps}" var="s">
								<c:if test="${s.key==sta.key}">
									<tr>
										<td width="200px;" bgcolor="#F0F0F0" align="center"><span>${fn:split(s.value,',')[0]}  </span></td>
										<td width="100px;" bgcolor="#F0F0F0" align="center"><span> <a href="javascript:showDate('${fn:split(s.value,',')[1]}');">( ${sta.value} )</a></span></td>
									</tr>
								</c:if>
							</c:forEach>
						</c:forEach>
						
				</table>
			</td>
		</tr>
	
	</table>
	
			
	<input type="hidden" value="${params}" id="params" name="params">
  </body>
</html>
