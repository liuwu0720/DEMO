<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'phone_table.jsp' starting page</title>
    
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
    <table class="table">
		<tbody id="phone_tbody1">
			<tr style="font-size: 5;height: 20px;">
				<td>开始日期</td>
				<td>结束日期</td>
				<td>累计出差天数</td>
				<td>出差通讯费标准</td>
				<td>实际标准</td>
				<td>报销金额</td>
				<!-- <td>备注</td> -->
				<c:forEach items="${feeTels.feeTels }" var="t"	varStatus="vstatus">	
				<tr id="phone_tr2">
				<td>${t.dtStart }</td>
				<td>${t.dtEnd }</td>
				<td>${t.nDays }</td>
				<td>${t.nStandard }</td>
				<td>${t.nActStandard }</td>
				<td>${t.nMoney }</td>
				<%-- <td>${t.vcNote }</td> --%>
	
			</tr>
			</c:forEach>
		</tbody>
	</table>
  </body>
</html>