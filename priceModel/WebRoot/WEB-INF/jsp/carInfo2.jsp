<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%@ include file="taglib.jsp"%>

<title>My JSP 'right.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/stylesheets/jquery.loadmask.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/jquery-ui.min.css"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/jquery.loadmask.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/tooltip.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		$("body").unmask();
		
		$("#checkAll").click(function() {
			 var code_Values = document.getElementsByTagName("input"); 
       	   for(i = 0;i < code_Values.length;i++){ 
       	   if(code_Values[i].type == "checkbox") 
       	   { 
       	   code_Values[i].checked = this.checked; 
       	   } 
       	   }
		});
		
		if (window.parent.document) {
			$(window.parent.document.body).unmask();
		}
		if ('${error1}') {
			$("#warn1").show();
			$("#carInfo").hide();
			//$(window.parent.document).find("td[class='${leg}+show']").hide();
			//$(window.parent.document).find("td[class='${leg}+hide']").show();
		}
		
		if ('${error}') {
			$("#warn").show();
			$("#carInfo").hide();
			
		}
		if ('${message}') {
			$("#warn2").show();
		}
		
		if('${historyCarInfos}'){
			$("#opener").show();
		}
		
		var message = '${message1}';
		 $(window.parent.document).find("button[name='${leg}']").attr("title",message);
	  	$(window.parent.document).find("button[name='${leg}']").tooltip();
	
	  	
	  //jqueryui
		$("#dialog").dialog({
			autoOpen : false,
			height : 400,
			width : 600,
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
		
		
		
	/**	 $("table tr:not('#alltr')").click(function(){
		        var input = $(this).find("input[type='checkbox']");//获取checkbox
		        //判断当前checkbox是否为选中状态
		       
		        if(input[0].checked){
		            input[0].checked = false;
		        }else{
		        	input[0].checked = true;
		        	 //console.log(input.attr("checked"));
		        }

		    }) **/
		    $("table tr:not('#alltr')").find("td:not(':first'):not(':last')").click(function(){
		        var input = $(this).parent().find("input[type='checkbox']");//获取checkbox
		        //判断当前checkbox是否为选中状态
		       
		        if(input[0].checked){
		            input[0].checked = false;
		        }else{
		        	input[0].checked = true;
		        	
		        }
		        
		        var alllength = $("input[type='checkbox']").not("#checkAll").length;
				
		        if($("input[type='checkbox']:checked").not("#checkAll").length == alllength){
					
					$("#checkAll")[0].checked = true;
				}else{
					$("#checkAll").attr("checked",false);
				}
		    })
	
	});
	
	function checkvalue(){
		if ($("input[type='checkbox']:checked").length < 1) {
			alert("请至少选择1辆车");
			return false;
		} else {
			$("body").mask("正在修改请稍候！");
			return true;
		}
	}
	
	// 确认将此线路加入空载:用户导入
	function setLegEmptly2(obj) {
		parent.setLegEmptly2(obj);
	}
	function cancelLeg2(obj){
		parent.cancelLeg2(obj);
	}
	
</script>


</head>

<body>
	<div id="warn" class="alert alert-warning alert-dismissible"
		role="alert" style="display: none;text-align: center;">
		<strong>提示!</strong>
		<p id="warn_p">${error }</p>
	</div>
	<div id="warn1" class="alert alert-warning alert-dismissible"
		role="alert" style="display: none;text-align: center;">
		<strong>提示!</strong>
		<p id="warn_p">${error1 }</p>
		<button class="btn btn-primary btn-xs" type="button"
			onclick="setLegEmptly2(${leg})">
			<i class="icon-ok"></i>是
		</button>
		<button class="btn btn-primary btn-xs" type="button"
			onclick="cancelLeg2(${leg})">
			<i class="icon-off"></i>否
		</button>
	</div>
	<div id="warn2" class="alert alert-warning alert-dismissible"
		role="alert" style="display: none;text-align: center;">
		<p id="warn_p">${message }</p>
		<button class="btn btn-primary" type="button" style="margin: 5px;display: none;" id="opener" >查看历史修改</button>
	</div>
	<!-- 商品车详细信息 -->
	<div id="carInfo" class="panel panel-primary" style="margin: 10px; ">
		<div class="panel-heading">
			<h3 class="panel-title">路线对应的商品车信息</h3>
		</div>
		<div class="panel-body">
			<div style="margin: 0px;">
				<form id="carform" action="updateLegCar2.do" method="post"
					onsubmit="return checkvalue()">

					<input type="hidden" name="legid" value="${leg }" /> <input
						type="hidden" name="typeId" value="${typeId }" />
					<table class="table table-bordered table-hover table-condensed" id="nsDataGridTable" >
						<thead>
							<tr>
								<th>选择</th>
								<th>车名</th>
								<th>重量</th>
								<th>长度</th>
								<th>应收单价</th>
								<th>应付单价</th>
								<th>发车概率</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${carInfos.carInfos}" var="car"
								varStatus="vstatus">
								<c:if test="${vstatus.count%2==0 }" >
									<tr class="success" style="text-align: center;"  >
										<td id="check"><input type="checkbox" checked="checked" 
											value="${car.id }" name="carInfos[${vstatus.index}].id" />
										</td>
										<td>${car.carname } <input type="hidden"
											value="${car.carname }"
											name="carInfos[${vstatus.index}].carname" />
										</td>
										<td><fmt:formatNumber value="${car.weight }"
												type="number" pattern="0.00#" /> <input type="hidden"
											value="${car.weight }"
											name="carInfos[${vstatus.index}].weight" />
										</td>
										<td><fmt:formatNumber value="${car.length }"
												type="number" pattern="0.00#" /> <input type="hidden"
											value="${car.length }"
											name="carInfos[${vstatus.index}].length" />
										</td>
										<td><fmt:formatNumber value="${car.incomePrice }"
												type="number" pattern="0.00#" /> <input type="hidden"
											value="${car.incomePrice }"
											name="carInfos[${vstatus.index}].incomePrice" />
										</td>
										<td><fmt:formatNumber value="${car.currentProCost }"
												type="number" pattern="0.00#" /> <input type="hidden"
											value="${car.currentProCost }"
											name="carInfos[${vstatus.index}].currentProCost" />
										</td>
										<td><input type="text" value="${car.ratio }" size="6"
											name="carInfos[${vstatus.index}].ratio"></td>
									</tr>
								</c:if>
								<c:if test="${vstatus.count%2 != 0 }">
									<tr style="text-align: center;">
										<td><input type="checkbox" checked="checked"
											value="${car.id }" name="carInfos[${vstatus.index}].id" />
										</td>
										<td>${car.carname }<input type="hidden" checked="checked"
											value="${car.carname }"
											name="carInfos[${vstatus.index}].carname" />
										</td>
										<td><fmt:formatNumber value="${car.weight }"
												type="number" pattern="0.00#" /> <input type="hidden"
											value="${car.weight }"
											name="carInfos[${vstatus.index}].weight" />
										</td>
										<td><fmt:formatNumber value="${car.length }"
												type="number" pattern="0.00#" /> <input type="hidden"
											value="${car.length }"
											name="carInfos[${vstatus.index}].length" />
										</td>
										<td><fmt:formatNumber value="${car.incomePrice }"
												type="number" pattern="0.00#" /> <input type="hidden"
											value="${car.incomePrice }"
											name="carInfos[${vstatus.index}].incomePrice" />
										</td>
										<td><fmt:formatNumber value="${car.currentProCost }"
												type="number" pattern="0.00#" /> <input type="hidden"
											value="${car.currentProCost }"
											name="carInfos[${vstatus.index}].currentProCost" />
										</td>
										<td><input type="text" value="${car.ratio }" size="6"
											name="carInfos[${vstatus.index}].ratio"></td>
									</tr>
								</c:if>
							</c:forEach>
							<tr style="text-align: center;" id="alltr">
								<td><input type="checkbox" id="checkAll" checked="checked"/></td>
								<td>商品车总数:${fn:length(carInfos.carInfos)}</td>
								<td></td>
								<td></td>
								<td></td>
								<td>总比率</td>
								<c:if test="${totalRatio>1||totalRatio<1 }">
									<td>${totalRatio }(请手动调整至等于1)</td>
								</c:if>
								<c:if test="${totalRatio==1}">
									<td>${totalRatio }</td>
								</c:if>
							</tr>
						</tbody>
					</table>
					<button class="btn btn-primary" type="submit" style="margin: 5px;">确定修改</button>
				</form>
			</div>
		</div>
	</div>
	
	<div id="dialog" title="该线路之前修改的商品车信息" style="display: none;">
		<table class="table table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th style="text-align: center;">车型</th>
					<th style="text-align: center;">重量</th>
					<th style="text-align: center;">长度</th>
					<th style="text-align: center;">应收单价</th>
					<th style="text-align: center;">应付单价</th>
					<th style="text-align: center;">发车概率</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${historyCarInfos}" var="car" varStatus="vstatus">
					<c:if test="${vstatus.count%2==0 }">
						<tr class="success" style="text-align: center;">
							<td>${car.carname }</td>
							<td><fmt:formatNumber value="${car.weight }" type="number"
									pattern="0.00#" />
							</td>
							<td><fmt:formatNumber value="${car.length }" type="number"
									pattern="0.00#" />
							</td>
							<td><fmt:formatNumber value="${car.incomePrice }"
									type="number" pattern="0.00#" />
							</td>
							<td><fmt:formatNumber value="${car.currentProCost }"
									type="number" pattern="0.00#" />
							</td>
							<td>${car.ratio }</td>

						</tr>
					</c:if>
					<c:if test="${vstatus.count%2 != 0 }">
						<tr style="text-align: center;">

							<td>${car.carname }</td>
							<td><fmt:formatNumber value="${car.weight }" type="number"
									pattern="0.00#" />
							</td>
							<td><fmt:formatNumber value="${car.length }" type="number"
									pattern="0.00#" />
							</td>
							<td><fmt:formatNumber value="${car.incomePrice }"
									type="number" pattern="0.00#" />
							</td>
							<td><fmt:formatNumber value="${car.currentProCost }"
									type="number" pattern="0.00#" />
							</td>
							<td>${car.ratio }</td>

						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
