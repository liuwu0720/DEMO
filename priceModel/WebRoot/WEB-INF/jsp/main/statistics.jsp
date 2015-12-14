<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<%@ include file="../taglib.jsp"%>
<title>My JSP 'statistics.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	$(function() {
		var $tab_li = $('.panel-body ul li');
		$tab_li.hover(function() {
			$(this).addClass('active').siblings().removeClass('active');
			var index = $tab_li.index(this);
			$('div.detail > table').eq(index).show().siblings().hide();
		});
		
		
		
	})
		
</script>
<style type="text/css">
* {
	margin: 0 auto;
	padding: 0;
}

body {
	text-align: center;
	margin: 0 auto;
}

.detail {
	margin: 0 auto;
	margin-top: 20px;
	width: 80%;
}

#tabEnterNpDiv {
	margin: 0 auto;
	width: 100%;
}
</style>

</head>
<body>
	<div id="tabEnterNpDiv">
		<c:forEach items="${routeTruckSummarForm }" var="route">
			<div class="panel panel-success"
				style="width: 1000px;margin-top: 20px;">
				<!-- Default panel contents -->
				<div class="panel-heading">
					线路：
					<c:forEach items="${route.routeSummary.legInfos }" var="leg">${leg.origin }---${leg.startPoint }--${leg.vcCustomer }(客户)--${leg.destination }&nbsp;&nbsp;&nbsp;</c:forEach>
				</div>
				<div class="panel-body" style="margin-left: 10%;">
					<ul class="nav nav-pills" style="cursor: pointer;">
						<li class="active"><a>实际成本</a>
						</li>
						<li><a>新的采购价</a>
						</li>
						<li><a>当前采购价</a>
						</li>
						<li><a>平均收入价格</a>
						</li>
						<li><a>新方案下供应商利润</a>
						</li>
						<li><a>当前供应商利润</a>
						</li>
						<li><a>新方案下中联利润</a>
						</li>
					</ul>
				</div>
				<div class="detail">
					<!-- 实际支出 -->
					<table class="table table-striped table-hover">
						<thead>
							<tr style="text-align: center;background-color: #99CC99">
								<td>线路</td>
								<td>/板/公里</td>
								<td>/板</td>
								<td>/辆/公里</td>
								<td>/辆</td>
								<td>/月</td>
								<td>操作</td>
							</tr>
						</thead>
						<c:forEach items="${routeLegTruckSummaryForm }" var="routeleg">
							<c:if
								test="${route.routeSummary.id == routeleg.routeSummary.id }">
								<tr style="text-align: center;">
									<td>${routeleg.legInfo.origin
										}--${routeleg.legInfo.destination }</td>
									<td><fmt:formatNumber
											value="${routeleg.actualCost_Truck_km }" type="currency"
											pattern="￥0.00#" />
									</td>
									<td><fmt:formatNumber
											value="${routeleg.actualCost_truck_per }" type="currency"
											pattern="￥0.00#" />
									</td>
									<td><fmt:formatNumber
											value="${routeleg.actualCost_Car_km }" type="currency"
											pattern="￥0.00#" />
									</td>
									<td><fmt:formatNumber value="${routeleg.actualCost_car }"
											type="currency" pattern="￥0.00#" />
									</td>
									<td><fmt:formatNumber
											value="${routeleg.actualCost_Month }" type="currency"
											pattern="￥0,000" />
									</td>

									<td><button class="btn btn-primary btn-sm"
											onclick="getMoreDetails(${routeleg.legInfo.id})">
											<i class="icon-search"></i>更多详情
										</button></td>
								</tr>
							</c:if>
						</c:forEach>
						<tr class="warning">
							<td>统计</td>
							<td><fmt:formatNumber
									value="${route.totalActualCost_truck_km }" type="currency"
									pattern="￥0.00#" />
							</td>
							<td><fmt:formatNumber
									value="${route.totalActualCost_truck }" type="currency"
									pattern="￥0.00#" /></td>
							<td><fmt:formatNumber
									value="${route.totalActualCost_car_km }" type="currency"
									pattern="￥0.00#" /></td>
							<td><fmt:formatNumber value="${route.totalActualCost_car }"
									type="currency" pattern="￥0.00#" /></td>
							<td><fmt:formatNumber
									value="${route.totalActualCost_month }" type="currency"
									pattern="￥0.00#" /></td>

						</tr>
					</table>


					<!-- 新的采购支出 -->
					<table class="table" style="display: none">
						<thead>
							<tr style="text-align: center;background-color: #99CC99 ">
								<td>线路</td>
								<td>/板/公里</td>
								<td>/板</td>
								<td>/辆/公里</td>
								<td>/辆</td>
								<td>/月</td>
								<td>操作</td>
							</tr>
						</thead>
						<c:forEach items="${routeLegTruckSummaryForm }" var="routeleg">
							<c:if
								test="${route.routeSummary.id == routeleg.routeSummary.id }">
								<tr style="text-align: center;">
									<td>${routeleg.legInfo.origin
										}-${routeleg.legInfo.destination }</td>
									<td><fmt:formatNumber
											value="${routeleg.newProCost_truck_km }" type="currency"
											pattern="￥0.00#" /></td>
									<td><fmt:formatNumber
											value="${routeleg.newProCost_truck_per }" type="currency"
											pattern="￥0.00#" /></td>
									<td><fmt:formatNumber
											value="${routeleg.newProCost_car_km }" type="currency"
											pattern="￥0.00#" /></td>
									<td><fmt:formatNumber value="${routeleg.newProCost_car }"
											type="currency" pattern="￥0.00#" /></td>
									<td><fmt:formatNumber
											value="${routeleg.newProCost_month }" type="currency"
											pattern="￥0,000" /></td>
									<td><button class="btn btn-primary btn-sm"
											onclick="getMoreDetails(${routeleg.legInfo.id})">
											<i class="icon-search"></i>更多详情
										</button></td>
								</tr>
							</c:if>
						</c:forEach>
						<tr class="warning">
							<td>统计</td>
							<td><fmt:formatNumber
									value="${route.totalNewProCost_truck_km }" type="number"
									pattern="￥0.00#" /></td>
							<td><fmt:formatNumber
									value="${route.totalNewProCost_truck }" type="number"
									pattern="￥0.00#" /></td>
							<td><fmt:formatNumber
									value="${route.totalNewProCost_car_km }" type="number"
									pattern="￥0.00#" /></td>
							<td><fmt:formatNumber value="${route.totalNewProCost_car }"
									type="number" pattern="￥0.00#" /></td>
							<td><fmt:formatNumber
									value="${route.totalNewProCost_month }" type="number"
									pattern="￥0,000" /></td>
							<td></td>
						</tr>
					</table>

					<!-- 当前采购支出-->
					<table class="table" style="display: none">
						<thead>
							<tr style="text-align: center;background-color: #99CC99 ">
								<td>线路</td>
								<td>板/公里</td>
								<td>/板</td>
								<td>/辆/公里</td>
								<td>/辆</td>
								<td>/月</td>
								<td>操作</td>
							</tr>
						</thead>
						<c:forEach items="${routeLegTruckSummaryForm }" var="routeleg">
							<c:if
								test="${route.routeSummary.id == routeleg.routeSummary.id }">
								<tr style="text-align: center;">
									<td>${routeleg.legInfo.origin
										}-${routeleg.legInfo.destination }</td>
									<td><fmt:formatNumber
											value="${routeleg.curProCost_truck_km }" type="currency"
											pattern="￥0.00#" /></td>
									<td><fmt:formatNumber
											value="${routeleg.curProCost_truck_per }" type="currency"
											pattern="￥0.00#" /></td>
									<td><fmt:formatNumber
											value="${routeleg.curProCost_car_km }" type="currency"
											pattern="￥0.00#" /></td>
									<td><fmt:formatNumber value="${routeleg.curProCost_car }"
											type="currency" pattern="￥0.00#" /></td>
									<td><fmt:formatNumber
											value="${routeleg.curProCost_month }" type="currency"
											pattern="￥0,000" /></td>
									<td><button class="btn btn-primary btn-sm"
											onclick="getMoreDetails(${routeleg.legInfo.id})">
											<i class="icon-search"></i>更多详情
										</button></td>
								</tr>
							</c:if>
						</c:forEach>
						<tr class="warning">
							<td>统计</td>
							<td><fmt:formatNumber
									value="${route.totalCurProCost_truck_km }" type="currency"
									pattern="￥0.00#" /></td>
							<td><fmt:formatNumber value="${route.totalCurProCost_truk }"
									type="currency" pattern="￥0.00#" /></td>
							<td><fmt:formatNumber
									value="${route.totalCurProCost_car_km }" type="currency"
									pattern="￥0.00#" /></td>
							<td><fmt:formatNumber value="${route.totalCurProCost_car }"
									type="currency" pattern="￥0.00#" /></td>
							<td><fmt:formatNumber
									value="${route.totalCurProCost_month }" type="currency"
									pattern="￥0,000" /></td>
							<td></td>
						</tr>
					</table>

					<!-- 平均收入价格-->
					<table class="table" style="display: none">
						<thead>
							<tr style="text-align: center;background-color: #99CC99">
								<td>线路</td>
								<td>/板/公里</td>
								<td>/板</td>
								<td>/辆/公里</td>
								<td>/辆</td>
								<td>/月</td>
								<td>操作</td>
							</tr>
						</thead>
						<c:forEach items="${routeLegTruckSummaryForm }" var="routeleg">
							<c:if
								test="${route.routeSummary.id == routeleg.routeSummary.id }">
								<tr style="text-align: center;">
									<td>${routeleg.legInfo.origin
										}-${routeleg.legInfo.destination }</td>
									<td><fmt:formatNumber
											value="${routeleg.avgIncomePrice_truck_km }" type="currency"
											pattern="￥0.00#" /></td>
									<td><fmt:formatNumber
											value="${routeleg.avgIncomePrice_truck_per }" type="currency"
											pattern="￥0.00#" /></td>
									<td><fmt:formatNumber
											value="${routeleg.avgIncomePrice_car_km }" type="currency"
											pattern="￥0.00#" /></td>
									<td><fmt:formatNumber
											value="${routeleg.avgIncomePrice_car }" type="currency"
											pattern="￥0.00#" /></td>
									<td><fmt:formatNumber
											value="${routeleg.avgIncomePrice_month }" type="currency"
											pattern="￥0,000" /></td>
									<td><button class="btn btn-primary btn-sm"
											onclick="getMoreDetails(${routeleg.legInfo.id})">
											<i class="icon-search"></i>更多详情
										</button></td>
								</tr>
							</c:if>
						</c:forEach>
						<tr class="warning">
							<td>统计</td>
							<td><fmt:formatNumber
									value="${route.totalAvgInPrice_truck_km }" type="currency"
									pattern="￥0.00#" /></td>
							<td><fmt:formatNumber
									value="${route.totalAvgInPrice_truck }" type="currency"
									pattern="￥0.00#" /></td>
							<td><fmt:formatNumber
									value="${route.totalAvgInPrice_car_km }" type="currency"
									pattern="￥0.00#" /></td>
							<td><fmt:formatNumber value="${route.totalAvgInPrice_car }"
									type="currency" pattern="￥0.00#" /></td>
							<td><fmt:formatNumber
									value="${route.totalAvgInPrice_month }" type="currency"
									pattern="￥0,000" /></td>
							<td></td>
						</tr>
					</table>

					<!-- 新方案下供应商利润-->
					<table class="table" style="display: none">
						<thead>
							<tr style="text-align: center;background-color: #99CC99">
								<td>线路</td>
								<td>/板/公里</td>
								<td>/板</td>
								<td>/月</td>
								<td>利润率</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${routeLegTruckSummaryForm }" var="routeleg">
								<c:if
									test="${route.routeSummary.id == routeleg.routeSummary.id }">
									<tr style="text-align: center;">
										<td>${routeleg.legInfo.origin
											}-${routeleg.legInfo.destination }</td>
										<td><fmt:formatNumber
												value="${routeleg.vendorProfit_Truck_km }" type="currency"
												pattern="￥0.00#" /></td>
										<td><fmt:formatNumber
												value="${routeleg.vendorProfit_truck_per }" type="currency"
												pattern="￥0.00#" /></td>
										<td><fmt:formatNumber
												value="${routeleg.vendorProfit_Month }" type="currency"
												pattern="￥0,000" /></td>
										<td><fmt:formatNumber
												value="${routeleg.vendorProfit_percent }" type="currency"
												pattern="0.00#" /></td>
										<td><button class="btn btn-primary btn-sm"
												onclick="getMoreDetails(${routeleg.legInfo.id})">
												<i class="icon-search"></i>更多详情
											</button></td>
									</tr>
								</c:if>
							</c:forEach>
							<tr class="warning">
								<td>统计</td>
								<td><fmt:formatNumber
										value="${route.totalVenPro_truck_km }" type="currency"
										pattern="￥0.00#" /></td>
								<td><fmt:formatNumber value="${route.totalVenPro_truck }"
										type="currency" pattern="￥0.00#" /></td>
								<td><fmt:formatNumber value="${route.totalVenPro_month }"
										type="currency" pattern="￥0,000" /></td>
								<td><fmt:formatNumber
										value="${route.totalVendorPro_percent }" type="currency"
										pattern="0.00#" /></td>
								<td></td>
							</tr>
						</tbody>
					</table>

					<!-- 当前供应商利润-->
					<table class="table" style="display: none">
						<thead>
							<tr style="text-align: center;background-color: #99CC99">
								<td>线路</td>
								<td>/板/公里</td>
								<td>/板</td>
								<td>/月</td>
								<td>利润率</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${routeLegTruckSummaryForm }" var="routeleg">
								<c:if
									test="${route.routeSummary.id == routeleg.routeSummary.id }">
									<tr style="text-align: center;">
										<td>${routeleg.legInfo.origin
											}-${routeleg.legInfo.destination }</td>
										<td><fmt:formatNumber
												value="${routeleg.curVendorPro_truck_km }" type="currency"
												pattern="￥0.00#" /></td>
										<td><fmt:formatNumber
												value="${routeleg.curVendorPro_truck_per }" type="currency"
												pattern="￥0.00#" /></td>
										<td><fmt:formatNumber
												value="${routeleg.curVendorPro_month }" type="currency"
												pattern="￥0,000" /></td>
										<td><fmt:formatNumber
												value="${routeleg.curVendorPro_percent }" type="percent"
												pattern="0.00#" /></td>
										<td><button class="btn btn-primary btn-sm"
												onclick="getMoreDetails(${routeleg.legInfo.id})">
												<i class="icon-search"></i>更多详情
											</button></td>
									</tr>
								</c:if>
							</c:forEach>
							<tr class="warning">
								<td>统计</td>
								<td><fmt:formatNumber
										value="${route.totalCurVenPro_truck_km }" type="currency"
										pattern="￥0.00#" /></td>
								<td><fmt:formatNumber
										value="${route.totalCurVenPro_truck }" type="currency"
										pattern="￥0.00#" /></td>
								<td><fmt:formatNumber value="${route.totalCurVenPro_month}"
										type="currency" pattern="￥0,000" /></td>
								<td><fmt:formatNumber
										value="${route.totalCurVenPro_percent }" type="percent"
										pattern="0.00#" /></td>
								<td></td>
							</tr>
						</tbody>
					</table>

					<!-- 新方案下中联利润-->
					<table class="table" style="display: none">
						<thead>
							<tr style="text-align: center;background-color: #99CC99">
								<td>线路</td>
								<td>/板/公里</td>
								<td>/板</td>
								<td>/月</td>
								<td>利润率</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${routeLegTruckSummaryForm }" var="routeleg">
								<c:if
									test="${route.routeSummary.id == routeleg.routeSummary.id }">
									<tr style="text-align: center;">
										<td>${routeleg.legInfo.origin
											}-${routeleg.legInfo.destination }</td>
										<td><fmt:formatNumber
												value="${routeleg.unionProfit_truck_km }" type="currency"
												pattern="￥0.00#" /></td>
										<td><fmt:formatNumber
												value="${routeleg.unionProfit_truck_per }" type="currency"
												pattern="￥0.00#" /></td>
										<td><fmt:formatNumber
												value="${routeleg.unionProfit_month }" type="currency"
												pattern="￥0,000" /></td>
										<td><fmt:formatNumber
												value="${routeleg.unionProfit_percent }" type="number"
												pattern="0.00#" /></td>

										<td><button class="btn btn-primary btn-sm"
												onclick="getMoreDetails(${routeleg.legInfo.id})">
												<i class="icon-search"></i>更多详情
											</button></td>
									</tr>
								</c:if>
							</c:forEach>
							<tr class="warning">
								<td>统计</td>
								<td><fmt:formatNumber value="${route.totalUnPro_truck_km }"
										type="currency" pattern="￥0.00#" /></td>
								<td><fmt:formatNumber value="${route.totalUnPro_truck }"
										type="currency" pattern="￥0.00#" /></td>
								<td><fmt:formatNumber value="${route.totalUnPro_month}"
										type="currency" pattern="￥0,000" /></td>
								<td><fmt:formatNumber value="${route.totalUnPro_percent }"
										type="number" pattern="0.00#" /></td>
								<td><input id="hiddenId" type="hidden" value="${smUser.userName }"></td>
								
							</tr>
						</tbody>
					</table>
				</div>
				<table class="table table-striped table-bordered"
					style="width: 40%;margin-left: 20%;">
					<caption>线路其它信息</caption>
					<tbody>
						<tr>
							<td>发车频率</td>
							<td>${route.routeSummary.frequency }（趟/每月）</td>
						</tr>
						<tr class="info">
							<td>耗费时间</td>
							<td>${route.routeSummary.duration }(天)</td>
						</tr>

					</tbody>
				</table>
			</div>
		</c:forEach>
		<button class="btn btn-primary btn-sm" onclick="exportReport()">
			<i class="icon-search"></i>导出PDF报告
		</button>
		<button class="btn btn-primary btn-sm" onclick="exportReport2()">
			<i class="icon-search"></i>导出EXCEL报告
		</button>
		<%--<button class="btn btn-primary btn-sm" onclick="backbefore()" style="position: fixed;right: 10%">
			点击返回
		</button>
	--%>
	</div>
	<script type="text/javascript">
	
		var currentUser = $("#hiddenId").val();
		
		function getMoreDetails(obj){
			var user = $("#hiddenId").val();
			var legId = obj;
			window.open ('chart.do?curLegId='+legId+'&&smUser='+user, 'newwindow', 'height=700, width=1000, top=0,left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
		}
		function exportReport(){
			var tmp=window.open("about:blank","","width=790,height=590")  
			tmp.moveTo(0,0)  
			//tmp.resizeTo(screen.width-20,screen.height-20)  
			tmp.focus()  
			tmp.location="exprot.do?user="+currentUser; 
		}
		function exportReport2(){
			var tmp=window.open("about:blank","","width=790,height=590")  
			tmp.moveTo(0,0)  
			//tmp.resizeTo(screen.width-20,screen.height-20)  
			tmp.focus()  
			tmp.location="exprot2.do?user="+currentUser;; 
		}
		function backbefore(){
			window.history.back();
		}
	
	
</script>
</body>
</html>
