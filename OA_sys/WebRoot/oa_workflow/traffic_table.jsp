<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'traffic_table.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <table  class="table">
		<tbody id="traffic_tbody1">
			<tr style="font-size: 5;">
				<td>出发日期</td>
				<td>出发地点</td>
				<td>到达时间</td>
				<td>到达地点</td>
				<td>交通工具</td>
				<td>事由</td>
				<td>报销金额</td>
				<td>出差补贴</td>
				
				
			</tr>
			<c:forEach items="${feeTraffics.feeTraffics }" var="t"
				varStatus="vstatus">
			<tr id="traffic_tr2">
				<td>${t.dtStart }</td>
				<td>${t.vcStart }</td>
				<td>${t.dtArrive }</td>
				<td>${t.vcArrive }</td>
				<td>${t.vcEmploytriptool }</td>
				<td>${t.vcReason }</td>
				<td>${t.nReimburse }</td>
				<td>${t.nTravel }</td>
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
  </body>
</html>
