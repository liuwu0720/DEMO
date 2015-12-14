<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<%@ include file="../taglib.jsp"%>

<title>My JSP 'screen3.jsp' starting page</title>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/jquery-ui.min.css"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/FusionCharts/JS/FusionCharts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/jquery-ui.min.js"></script>




<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
* {
	margin: 0 auto;
	padding: 0;
}

.main {
	width: 100%;
	height: 100%;
	margin: 0 auto;
}

.leftdiv {
	width: 43%;
	border-top: 2px solid red;
	margin-top: 1%;
	margin-left: 1%;
	height: 30%;
	float: left;
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

.buttom {
	display: block;
	width: 80%;
	margin-top: 4%;
	margin-left: 1%;
	height: 30%;
}

.left2 {
	margin-top: 20px;
	border: 2px solid blue;
	padding: 2px;
}
</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				var myChart = new FusionCharts(
						"FusionCharts/Charts/Column3D.swf", "myChartId", "400",
						"400");
				var xmlstr = '${createXmlDataFile1}';
				myChart.setDataXML(xmlstr);
				myChart.render("right");

				var myChart2 = new FusionCharts("FusionCharts/Charts/Line.swf",
						"myChartId2", "800", "400");
				var xmlstr2 = '${createXmlDataFile2}';
				myChart2.setDataXML(xmlstr2);
				myChart2.render("right2");

				$("#dialog").dialog({
					autoOpen : false,
					height : 400,
					width : 800,
					show : {
						effect : "blind",
						duration : 1000
					},
					hide : {
						effect : "explode",
						duration : 1000
					}

				});

				$("#opener").click(function() {
					$("#dialog").dialog("open");
				});
				
			});
</script>
</head>

<body>
	<div class="main">
		<div class="leftdiv">
			<p class="text-center">线路：${curRouteLegTruckSummary.legInfo.origin
				}--${curRouteLegTruckSummary.legInfo.destination }</p>
			<div>
				<table class="table table-bordered table-condensed table-hover">
					<tr class="info" style="text-align: center;">
						<td>明细</td>
						<td>价格/板/公里</td>
						<td>价格/板(￥)</td>
						<td>价格/月(￥)</td>
					</tr>
					<tr class="active" style="text-align: center;">
						<td>实际成本</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.actualCost_Truck_km }"
								type="number" pattern="0.0#" /></td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.actualCost_truck_per }"
								type="number" pattern="0.0#" /></td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.actualCost_Month }"
								type="number" pattern="0,000" /></td>
					</tr>
					<tr class="danger" style="text-align: center;">
						<td>新采购价</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.newProCost_truck_km }"
								type="number" pattern="0.0#" /></td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.newProCost_truck_per }"
								type="number" pattern="0.0#" /></td>		
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.newProCost_month }"
								type="number" pattern="0,000" /></td>
					</tr>

					<tr class="active" style="text-align: center;">
						<td>当前采购价</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.curProCost_truck_km }"
								type="number" pattern="0.0#" /></td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.curProCost_truck_per }"
								type="number" pattern="0.0#" /></td>		
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.curProCost_month }"
								type="number" pattern="0,000" /></td>
					</tr>

					<tr class="success" style="text-align: center;">
						<td>平均收入价格</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.avgIncomePrice_truck_km }"
								type="number" pattern="0.0#" /></td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.avgIncomePrice_truck_per }"
								type="number" pattern="0.0#" /></td>				
								
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.avgIncomePrice_month }"
								type="number" pattern="0,000" /></td>
					</tr>

					<tr class="warning" style="text-align: center;">
						<td>新方案下供应商利润</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.vendorProfit_Truck_km }"
								type="number" pattern="0.0#" /></td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.vendorProfit_truck_per }"
								type="number" pattern="0.0#" /></td>		
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.vendorProfit_Month }"
								type="number" pattern="0,000" /></td>
					</tr>

					<tr class="danger" style="text-align: center;">
						<td>当前供应商利润</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.curVendorPro_truck_km }"
								type="number" pattern="0.0#" /></td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.curVendorPro_truck_per }"
								type="number" pattern="0.0#" /></td>		
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.curVendorPro_month }"
								type="number" pattern="0,000" /></td>
					</tr>

					<tr class="warning" style="text-align: center;">
						<td>新方案下中联利润</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.unionProfit_truck_km }"
								type="number" pattern="0.0#" /></td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.unionProfit_truck_per }"
								type="number" pattern="0.0#" /></td>		
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.unionProfit_month }"
								type="number" pattern="0,000" /></td>
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
						<td>新方案供应商利润率</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.vendorProfit_percent }"
								type="number" pattern="0.0%" /></td>
					</tr>

					<tr class="active" style="text-align: center;">
						<td>当前供应商利润率</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.curVendorPro_percent }"
								type="number" pattern="0.0%" /></td>
					</tr>

					<tr class="warning" style="text-align: center;">
						<td>新方案中联利润率</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.unionProfit_percent }"
								type="number" pattern="0.0%" /></td>
					</tr>
					<tr class="active" style="text-align: center;">
						<td>平均装载量/板</td>
						<td><fmt:formatNumber
								value="${curRouteLegTruckSummary.avgCarsPerTruckCombo }"
								type="number"  /></td>
					</tr>

				</table>
			</div>
			<div id="right2" class="buttom"></div>
		</div>

		<div class="rightdiv">
			<div class="right" id="right"></div>

			<div id="dialog" title="该线路对应的商品车信息">
				<table class="table table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>车型</th>
							<th>重量</th>
							<th>长度</th>
							<th>应收单价</th>
							<th>应付单价</th>
							<th>发运比例</th>
							<th>单公里采购价</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${carInfoList}" var="car" varStatus="vstatus">
							<c:if test="${vstatus.count%2==0 }">
								<tr class="success" style="text-align: center;">
									<td>${car.carname }</td>
									<td><fmt:formatNumber value="${car.weight }" type="number"
											pattern="0.00#" /></td>
									<td><fmt:formatNumber value="${car.length }" type="number"
											pattern="0.00#" /></td>
									<td><fmt:formatNumber value="${car.incomePrice }"
											type="number" pattern="0.00#" /></td>
									<td><fmt:formatNumber value="${car.currentProCost }"
											type="number" pattern="0.00#" /></td>
									<td>${car.ratio }</td>
									<td>${car.fleetPrice }</td>
								</tr>
							</c:if>
							<c:if test="${vstatus.count%2 != 0 }">
								<tr style="text-align: center;">

									<td>${car.carname }</td>
									<td><fmt:formatNumber value="${car.weight }" type="number"
											pattern="0.00#" /></td>
									<td><fmt:formatNumber value="${car.length }" type="number"
											pattern="0.00#" /></td>
									<td><fmt:formatNumber value="${car.incomePrice }"
											type="number" pattern="0.00#" /></td>
									<td><fmt:formatNumber value="${car.currentProCost }"
											type="number" pattern="0.00#" /></td>
									<td>${car.ratio }</td>
									<td>${car.fleetPrice }</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<button id="opener" class="btn btn-primary">商品车详情</button>
		</div>

	</div>
</body>


</html>
