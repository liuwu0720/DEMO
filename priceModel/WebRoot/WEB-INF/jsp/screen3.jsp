<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/taglib.jsp"%>

<!DOCTYPE HTML">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'screen3.jsp' starting page</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/FusionCharts/JS/FusionCharts.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">

.leftdiv {
	margin-left: 10%;
	float: left;
	width: 40%;
	border-top: 2px solid red;
	margin-top: 10px; 
	height:30%;
}

.rightdiv {
	margin-right: 5%;
	float: right;
	width: 40%;
	border-top: 2px solid red;
	margin-top: 10px; 
}

.left {
	margin-top: 2%;
	border: 2px solid green;
	padding: 2px;
}
.buttom{
	
	position:relative;
	width:80%;
	margin: 5px auto;
	height:30%;
}
.left2 {
	margin-top: 20px;
	border: 2px solid blue;
	padding: 2px;
}
</style>
<script type="text/javascript">
$(document).ready(function (){
	var myChart = new FusionCharts("FusionCharts/Charts/Column3D.swf", "myChartId", "400", "400");
	var xmlstr = '${createXmlDataFile1}';
	myChart.setDataXML(xmlstr);
	myChart.render("right");
	
	var myChart2 = new FusionCharts("FusionCharts/Charts/Line.swf", "myChartId2", "800", "400");
	var xmlstr2 = '${createXmlDataFile2}';
	myChart2.setDataXML(xmlstr2);
	myChart2.render("right2");
});
	
</script>
</head>

<body >
	
	<div class="leftdiv">
	<p class="text-center">线路：${curRouteLegTruckSummary.legInfo.origin }--${curRouteLegTruckSummary.legInfo.destination }</p>
		<div class="left">
			<table class="table table-bordered table-condensed table-hover">
				<tr class="info" style="text-align: center;">
					<td>明细</td>
					<td>价格/板/公里</td>
					<td>价格/月</td>
				</tr>
				<tr class="active" style="text-align: center;">
					<td>实际支出</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.actualCost_Truck }"  type="number" pattern="0.0#"/> 
					</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.actualCost_Month }" type="number" pattern="0.0#" />
					</td>
				</tr>
				<tr class="danger" style="text-align: center;">
					<td>新方案采购支出</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.newProCost_truck }" type="number" pattern="0.0#" />
					</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.newProCost_month }" type="number" pattern="0.0#" />
					</td>
				</tr>

				<tr class="active" style="text-align: center;">
					<td>当前方案采购支出</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.curProCost_truck }" type="number" pattern="0.0#" />
					</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.curProCost_month }" type="number" pattern="0.0#" />
					</td>
				</tr>

				<tr class="success" style="text-align: center;">
					<td>平均收入价格</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.avgIncomePrice_truck }" type="number" pattern="0.0#" />
					</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.avgIncomePrice_month }" type="number" pattern="0.0#" />
					</td>
				</tr>

				<tr class="warning" style="text-align: center;">
					<td>新方案下供应商利润</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.newProCost_truck }" type="number" pattern="0.0#" />
					</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.newProCost_month }" type="number" pattern="0.0#" />
					</td>
				</tr>

				<tr class="danger" style="text-align: center;">
					<td>当前供应商利润</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.curVendorPro_truck }" type="number" pattern="0.0#" />
					</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.curVendorPro_month }" type="number" pattern="0.0#" />
					</td>
				</tr>

				<tr class="warning" style="text-align: center;">
					<td>新方案下中联利润</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.unionProfit_truck }" type="number" pattern="0.0#" />
					</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.unionProfit_month }" type="number" pattern="0.0#" />
					</td>
				</tr>
			</table>

		</div>
		<div class="left2">
			<table class="table table-bordered table-condensed table-hover">
				<tr class="active" style="text-align: center;">
					<td>其它明细</td>
					<td>值</td>
				</tr>

				<tr class="warning" style="text-align: center;">
					<td>新方案供应商占比</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.vendorProfit_percent }" type="number" pattern="0.0%" />
					</td>
				</tr>

				<tr class="active" style="text-align: center;">
					<td>当前供应商占比</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.curVendorPro_percent }" type="number" pattern="0.0%" />
					</td>
				</tr>

				<tr class="warning" style="text-align: center;">
					<td>新方案中联占比</td>
					<td><fmt:formatNumber value="${curRouteLegTruckSummary.unionProfit_percent }" type="number" pattern="0.0%" />
					</td>
				</tr>

				
			</table>
		</div>
	</div>
	
	<div class="rightdiv">
		<div class="right" id="right">
		
		</div>
		
	</div>
<div  id="right2" class="buttom">
		
</div>
</body>


</html>
