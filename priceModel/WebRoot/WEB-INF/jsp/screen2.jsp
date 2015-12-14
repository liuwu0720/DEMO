<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<%--<% 
if(session.getAttribute("index")==null||session.getAttribute("index")=="") 
{ 

	out.println("<SCRIPT language=JavaScript>");
	out.println("alert(\"连接超时，请重新登录 ！\");");
	out.println("window.top.location='default.jsp'");
	out.println("</script>"); 
 
}
%>--%>
<title>My JSP 'screen2.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.nave{
border-top: 2px solid red;
margin:0 auto;
width:1000px;
margin-top: 20px;

}
.nave a{
	cursor:pointer;

}
.detail{
margin:0 auto;
width:1000px;
height:350px;
margin-top: 20px;
border: 1px solid blue;
}
.route{
margin:0 auto;
width:1000px;
margin-top:5px;
border-top: 1px solid blue;
border-bottom: 1px solid blue;

}
</style>
</head>
<script type="text/javascript">
$(function(){
	var $tab_li = $('#tab ul li');
	$tab_li.hover(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var index = $tab_li.index(this);
		$('div.detail > table').eq(index).show().siblings().hide();
	});	

})

</script>
<body>
<div class="nave" id="tab">
	<ul class="nav nav-pills">
		<li  class="active"><a>实际支出</a>
		</li>
		<li ><a>新的采购支出</a>
		</li>
		<li ><a>当前采购支出</a>
		</li>
		<li ><a>平均收入价格</a>
		</li>
		<li ><a>新方案下供应商利润</a>
		</li>
		<li ><a>当前供应商利润</a>
		</li>
		<li ><a>新方案下中联利润</a>
		</li>
		
	</ul>
</div>
<div class="detail" >
<!-- 实际支出 -->
<table class="table" >
	<thead>
		<tr style="text-align: center;background-color: #99CC99 ">
			<td>线路</td>
			<td>板/公里</td>
			<td>辆/公里</td>
			<td>/月</td>
			<td></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${routeLegTruckSummaries}" var="route">
		<tr style="text-align: center;">
			<td>${route.legInfo.origin }---->${route.legInfo.destination }</td>
			<td><fmt:formatNumber value="${route.actualCost_Truck }" type="number" pattern="0.0#" />
			</td>
			<td> <fmt:formatNumber value="${route.actualCost_Car }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.actualCost_Month }" type="number" pattern="0.0#" />
			</td>
			<td><button class="btn btn-primary btn-sm" onclick="getMoreDetails(${route.legInfo.id})"><i class="icon-search"></i>更多详情</button></td>
		</tr>
	</c:forEach>
	<tr style="text-align: center;background-color: #99CC66 ">
		<td>统计</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalActualCost_truck }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalActualCost_car }" type="number" pattern="0.0#" />
		</td>
		<td>
		<fmt:formatNumber value="${routeTruckSummary.totalActualCost_month }" type="number" pattern="0.0#" />
		</td>
		<td></td>
	</tr>
	</tbody>
</table>

<!-- 新的采购支出 -->
<table class="table"  style="display: none">
	<thead>
		<tr style="text-align: center;background-color: #99CC99 ">
			<td>线路</td>
			<td>板/公里</td>
			<td>辆/公里</td>
			<td>/月</td>
			<td></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${routeLegTruckSummaries}" var="route">
		<tr style="text-align: center;">
			<td>${route.legInfo.origin }---->${route.legInfo.destination }</td>
			<td><fmt:formatNumber value="${route.newProCost_truck }" type="number" pattern="0.0#" />
			</td>
			<td>
			<fmt:formatNumber value="${route.newProCost_car }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.newProCost_month }" type="number" pattern="0.0#" />
			</td>
			<td><button class="btn btn-primary btn-sm" onclick="getMoreDetails(${route.legInfo.id})"><i class="icon-search"></i>更多详情</button></td>
		</tr>
	</c:forEach>
	<tr style="text-align: center;background-color: #99CC66 ">
		<td>统计</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalNewProCost_truck }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalNewProCost_car }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalNewProCost_month }" type="number" pattern="0.0#" />
		</td>
		<td></td>
	</tr>
	</tbody>
</table>

<!-- 当前采购支出-->
<table class="table"  style="display: none">
	<thead>
		<tr style="text-align: center;background-color: #99CC99 ">
			<td>线路</td>
			<td>板/公里</td>
			<td>辆/公里</td>
			<td>/月</td>
			<td></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${routeLegTruckSummaries}" var="route">
		<tr style="text-align: center;">
			<td>${route.legInfo.origin }---->${route.legInfo.destination }</td>
			<td><fmt:formatNumber value="${route.curProCost_truck }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.curProCost_car }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.curProCost_month }" type="number" pattern="0.0#" />
			</td>
			<td><button class="btn btn-primary btn-sm" onclick="getMoreDetails(${route.legInfo.id})"><i class="icon-search"></i>更多详情</button></td>
		</tr>
	</c:forEach>
	<tr style="text-align: center;background-color: #99CC66 ">
		<td>统计</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalCurProCost_truck }" type="number" pattern="0.0#" />
		</td>
		<td>
		<fmt:formatNumber value="${routeTruckSummary.totalCurProCost_truck }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalCurProCost_month }" type="number" pattern="0.0#" />
		</td>
		<td></td>
	</tr>
	</tbody>
</table>

<!-- 平均收入价格-->
<table class="table"  style="display: none">
	<thead>
		<tr style="text-align: center;background-color: #99CC99">
			<td>线路</td>
			<td>板/公里</td>
			<td>辆/公里</td>
			<td>/月</td>
			<td></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${routeLegTruckSummaries}" var="route">
		<tr style="text-align: center;">
			<td>${route.legInfo.origin }---->${route.legInfo.destination }</td>
			<td><fmt:formatNumber value="${route.avgIncomePrice_truck }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.avgIncomePrice_car }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.avgIncomePrice_month }" type="number" pattern="0.0#" />
			</td>
			<td><button class="btn btn-primary btn-sm" onclick="getMoreDetails(${route.legInfo.id})"><i class="icon-search"></i>更多详情</button></td>
		</tr>
	</c:forEach>
	<tr style="text-align: center;background-color: #99CC66 ">
		<td>统计</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalAvgInPrice_truck }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalAvgInPrice_car }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalAvgInPrice_month }" type="number" pattern="0.0#" />
		</td>
		<td></td>
	</tr>
	</tbody>
</table>

<!-- 新方案下供应商利润-->
<table class="table"  style="display: none">
	<thead>
		<tr style="text-align: center;background-color: #99CC99">
			<td>线路</td>
			<td>板/公里</td>
			<td>/月</td>
			<td>利润率</td>
			<td></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${routeLegTruckSummaries}" var="route">
		<tr style="text-align: center;">
			<td>${route.legInfo.origin }---->${route.legInfo.destination }</td>
			<td><fmt:formatNumber value="${route.newProCost_truck }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.newProCost_car }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.newProCost_month }" type="number" pattern="0.0#" />
			</td>
			<td><button class="btn btn-primary btn-sm" onclick="getMoreDetails(${route.legInfo.id})"><i class="icon-search"></i>更多详情</button></td>
		</tr>
	</c:forEach>
	<tr style="text-align: center;background-color: #99CC66 ">
		<td>统计</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalNewProCost_truck }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalNewProCost_car }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalNewProCost_month }" type="number" pattern="0.0#" />
		</td>
		<td></td>
	</tr>
	</tbody>
</table>

<!-- 当前供应商利润-->
<table class="table"  style="display: none">
	<thead>
		<tr style="text-align: center;background-color: #99CC99">
			<td>线路</td>
			<td>板/公里</td>
			<td>/月</td>
			<td>利润率</td>
			<td></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${routeLegTruckSummaries}" var="route">
		<tr style="text-align: center;">
			<td>${route.legInfo.origin }---->${route.legInfo.destination }</td>
			<td><fmt:formatNumber value="${route.curVendorPro_truck }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.curVendorPro_month }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.curVendorPro_percent }" type="number" pattern="0.0#" />
			</td>
			<td><button class="btn btn-primary btn-sm" onclick="getMoreDetails(${route.legInfo.id})"><i class="icon-search"></i>更多详情</button></td>
		</tr>
	</c:forEach>
	
	<tr style="text-align: center;background-color: #99CC66 ">
		<td>统计</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalCurVenPro_truck }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalCurVenPro_month }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalCurVenPro_percent }" type="number" pattern="0.0#" />
		</td>
		<td></td>
	</tr>
	</tbody>
</table>

<!-- 新方案下中联利润-->
<table class="table"  style="display: none">
	<thead>
		<tr style="text-align: center;background-color: #99CC99">
			<td>线路</td>
			<td>板/公里</td>
			<td>/月</td>
			<td>利润率</td>
			<td></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${routeLegTruckSummaries}" var="route">
		<tr style="text-align: center;">
			<td>${route.legInfo.origin }---->${route.legInfo.destination }</td>
			<td><fmt:formatNumber value="${route.unionProfit_truck }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.unionProfit_month }" type="number" pattern="0.0#" />
			</td>
			<td><fmt:formatNumber value="${route.unionProfit_percent }" type="number" pattern="0.0#" />
			</td>
			<td><button class="btn btn-primary btn-sm" onclick="getMoreDetails(${route.legInfo.id})"><i class="icon-search"></i>更多详情</button></td>
		</tr>
	</c:forEach>
	
	<tr style="text-align: center;background-color: #99CC66 ">
		<td>统计</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalUnPro_truck }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalUnPro_month }" type="number" pattern="0.0#" />
		</td>
		<td><fmt:formatNumber value="${routeTruckSummary.totalUnPro_percent }" type="number" pattern="0.0#" />
		</td>
		<td></td>
	</tr>
	</tbody>
</table>
</div>
<div class="route">
<table class="table" >
	<caption>ROUTE-INFO</caption>
	<tbody>
		<tr>
			
			<td>发车频率</td>
			<td>${routeSummary.frequency }（趟/每月）</td>
		</tr>
		<tr class="success">
			
			<td>路线持续时间</td>
			<td>${routeSummary.duration }(天)</td>
		</tr>
	
	</tbody>
</table>
</div>
</body>
<script type="text/javascript">
	function getMoreDetails(obj){
		var legId = obj;
		window.location.href = "detail.do?curLegId="+legId;
	}
</script>
</html>
