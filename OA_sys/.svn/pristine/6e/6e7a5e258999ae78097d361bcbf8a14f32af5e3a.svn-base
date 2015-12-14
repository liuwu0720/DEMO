<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'travel_table.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<table  class="table">
			<tr">
				<td style="width: 55px;">开始日期</td>
				<td style="width: 55px;">到达日期</td>
				<td style="width: 55px;">交通工具</td>
				<td style="width: 55px;">交通费单据张数</td>
				<td style="width: 55px;">交通费金额</td>
				<td style="width: 55px;">出差地点</td>
				<td style="width: 55px;">出差天数</td>
				<td style="width: 55px;">出差补贴</td>
				<td style="width: 55px;">住宿费金额</td>
				<td style="width: 55px;">住宿费单据张数</td>
				<td style="width: 55px;">杂费单据张数</td>
				<td style="width: 55px;">杂费</td>
				
			</tr>
			<c:forEach items="${travelfees.travelfees }" var="t"
				varStatus="vstatus">
				<tr id="travel_tr2">
					<td>${t.dtStart }</td>
					<td>${t.dtEnd }</td>
					<td>${t.vcTool }</td>
					<td>${t.nToollist }</td>
					<td>${t.nTool }</td>
					<td>${t.vcPlace }</td>
					<td>${t.nDays }</td>
					<td>${t.nSubsidies }</td>
					<td>${t.nHotel }</td>
					<td>${t.nHotellist }</td>
					<td>${t.nOtherlist }</td>
					<td>${t.nOther }</td>
					
				
				</tr>
			</c:forEach>
		</table>	
</body>
</html>
