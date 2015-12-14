
<%@ page errorPage="error.jsp" language="java" import="java.util.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<%@ include file="../taglib.jsp"%>

<title>My JSP 'select.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/select.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/select.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/stylesheets/jquery.loadmask.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/jquery-impromptu.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/jquery-ui.min.css"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/jquery.loadmask.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/jquery-impromptu.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/tooltip.js"></script>
</head>

<style>
.ui-autocomplete {
	max-height: 100px;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: hidden;
}
/* IE 6 doesn't support max-height
   * we use height instead, but this forces the menu to always be this tall
   */
* html .ui-autocomplete {
	height: 100px;
}

#radio,#fileField2 {
	display: inline;
	float: left;
	margin-bottom: 2px;
	margin-right: 3px;
	height: 23px;
	float: left;
}

.main {
	width: 100%;
	height: 80%;
	margin: 0 auto;
}

.leftdiv {
	width: 48%;
	margin: 1%;
	float: left;
	border: 1px solid red;
	margin-top: 1%;
}

.rightdiv {
	height: 90%;
	display: inline;
	margin-right: 4%;
	float: right;
	width: 43%;
	border: 1px solid red;
	margin-top: 1%;
}
</style>
<script type="text/javascript">
window.onload=function showtable(){
	var tablename=document.getElementById("filetable");
	var li=tablename.getElementsByTagName("tr");
	for (var i=0;i<=li.length;i++){
	if (i%2==0){
		li[i].className="success";
	}
	 
	}
}

$(document).ready(function(){
	
	$("body").unmask();
	var prLegfileimports = '${prLegFileImportForm.prLegfileimports }';
	var prSelfinputlegs = '${prSelfInputLegForm.prSelfinputlegs}';
	if(prLegfileimports){
		$("#fileDiv").show();
		$("#selfDiv").hide();
		$("input[name='type'][id='typeradio1']").attr("checked", true);
	}
	if(prSelfinputlegs){
		$("#selfDiv").show();
		$("#fileDiv").hide();
		$("input[name='type'][id='typeradio2']").attr("checked", true);
	}

	if('${error}'){
		$("#warn").show();
		
	}
	$("button").tooltip();

	
	$( "#startpoint" ).autocomplete({  //始发地提车点拉取下拉框
			 minLength: 0,
	         source: function(request,response){
	       	  delay:0,
	       	  $.ajax({
	       		  url:"startpoint.do",
	       		  dataType: "json",
	       		  type:"post",
	                 data: {
	                   startpoint: $("#startcity").val()
	                 },
	                 success: function( data ) {
	               	
	                   response( data );
	                 }
	       	  })
	         }
	       });         
	
	
	$( "#startcity" ).autocomplete({  //始发地拉取下拉框
		source: function(request,response){
	    delay:0,
	    $.ajax({
	      url:"smStartCity.do",
	      dataType: "json",
	      type:"post",
	      data: {
	     startcity: request.term
	    },
         success: function( data ) {
	                            	
         response( data );
		 }
	 	})
 	}
});
	$( "#endtcity" ).autocomplete({  //始发地拉取下拉框
		source: function(request,response){
	    delay:0,
	    $.ajax({
	      url:"endtcity.do",
	      dataType: "json",
	      type:"post",
	      data: {
	          endcity: request.term
	    },
         success: function( data ) {
	                            	
         response( data );
		 }
	 	})
 	}
});  
	
	
	
})

// 查询车辆：用户输入
function serachCar2(obj) {
	var customerId =  $("#"+obj+"select").val();
	if(customerId == 0){
		showMessage("请选择客户")
	}else{
		$("#warn").hide();
		$("body").unmask();
		$("body").mask("正在查询，请稍等……");
	right.location.href = 'searchcar2.do?legId=' + obj+"&&customerId="+customerId;
	}
}
function changeHistory(obj){
	var hostoryUser = $("#smUserNameId").val();
	window.location.href = 'view.do?history='+obj+'&&hUser='+hostoryUser;
	$("body").mask("正在查询，请稍等……");
}

</script>
</head>

<body>
	<div class="main">
		<div id="warn" class="alert alert-warning alert-dismissible"
			role="alert" style="display: none;text-align: center;">
			<strong>提示!</strong>
			<p id="warn_p">${error }</p>
		</div>
		<input id="smUserNameId" type="hidden" value="${smuser }">
		<div class="leftdiv" id="paneldiv" style="display: block;">
			<!-- 请选择线路 -->
			<div class="panel panel-primary" style="margin: 10px;height: 150px;">
				<div class="panel-heading">
					<h3 class="panel-title">请选择线路</h3>
				</div>
				<div class="panel-body" style="width: 890px;height:150px;">
					<div style="width: 890px;">
						<p id="radio">
							<input type="radio" name="type" value="0" id="typeradio1"
								style="margin:0" onclick="importFile();" />方式一、导入文件
						</p>
						<form id="form1" name="excelform" enctype="multipart/form-data"
							method="post" action="<%=basePath%>importExcel.do">
							<input name="excelfile" type="file" id="fileField2" /> <input
								name="smUserName" type="hidden" value="${smuser }">
							<button class="btn btn-primary btn-xs" type="button"
								onclick="uploadFile(excelform);">
								<i class="icon-upload-alt"></i>上传文件
							</button>
							<input type="checkbox" onclick="changeHistory(0)" name="history">历史数据
						</form>
					</div>
					<p style="margin: 2px;position: absolute;">
						<input type="radio" name="type" value="1" id="typeradio2"
							onclick="inputSelf();" />方式二、自定义输入
							<input type="checkbox" onclick="changeHistory(1)" name="history">历史数据
					</p>
				</div>
			</div>

			<!-- 已选择线路 -->
			<div class="panel panel-primary" id="fileDiv"
				style="overflow-x:auto;overflow-y:scroll;margin: 10px;height: 30%;display: block;">
				<div class="panel-heading" style="width: 850px;">
					<h3 class="panel-title">已选择线路</h3>
				</div>
				<div id="fileDiv2" class="panel-body"
					style="height: 300px;width:850px;">

					<table class="table table-bordered table-hover table-condensed"
						id="filetable" style="width: 100%;">
						<thead>
							<tr style="text-align:center;">
								<td>编号</td>
								<td>始发地</td>
								<td>始发地提车点</td>
								<td>选择客户</td>
								<td>目的地</td>
								<td>有效性</td>
								<td>备注</td>
								<td>操作</td>
								<td>删除</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${prLegFileImportForm.prLegfileimports }"
								var="leg" varStatus="vstatus">
								<c:if test="${leg.btnMessage!=null }">
									<tr style="text-align: center;color: blue;" id="${leg.id }tr">
										<td><fmt:formatNumber value="${leg.loopflag }"
												type="number" pattern="#" /> <input type="hidden"
											value="${leg.loopflag }"
											name="prLegfileimports[${vstatus.index}].loopflag" /></td>
										<td>${leg.startcity } <input type="hidden"
											value="${leg.startcity }"
											name="prLegfileimports[${vstatus.index}].startcity" /></td>
										<td>${leg.startpoint } <input type="hidden"
											value="${leg.startpoint }"
											name="prLegfileimports[${vstatus.index}].startpoint" /></td>
										<td><input type="hidden" id="${leg.id}select"
											value="${leg.customerId}" /> ${leg.selectCustomer}</td>
										<td>${leg.endcity} <input type="hidden"
											value="${leg.endcity }"
											name="prLegfileimports[${vstatus.index}].endcity" /></td>
										<input type="hidden" value="${leg.emptlyFlag }"
											name="prLegfileimports[${vstatus.index}].emptlyFlag" />
										<c:if test="${leg.emptlyFlag ==0 }">
											<td class="${leg.id }+show">有效</td>
											<td class="${leg.id }+show"><button type="button"
													class="btn btn-default btn-xs" data-toggle="tooltip"
													data-placement="top" name="tips" title="${leg.message }">提示</button>
											</td>
											<td class="${leg.id }+show"><button
													class="btn btn-primary btn-xs" type="button"
													title="${leg.btnMessage }" onclick="serachCar(${leg.id})"
													name="${leg.id }">
													<i class="icon-hand-right"></i>选择商品车
												</button></td>
											<!-- 如果查不到商品车的情况 -->
											<td class="${leg.id }+hide" style="display: none">待定（请确认是否加入空载？）</td>
											<td class="${leg.id }+hide" style="display: none"><button
													type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td class="${leg.id }+hide" style="display: none">
												<button class="btn btn-primary btn-xs" type="button"
													onclick="setLegEmptly(${leg.id})">
													<i class="icon-ok"></i>是
												</button>
												<button class="btn btn-primary btn-xs" type="button"
													onclick="cancelLeg(${leg.id})">
													<i class="icon-off"></i>否
												</button></td>
										</c:if>
										<c:if test="${leg.emptlyFlag ==1 }">
											<td>空载</td>
											<td><button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top"
													title="${leg.message }">提示</button>
											</td>
											<td><button class="btn btn-primary btn-xs" type="button"
													disabled="disabled" onclick="serachCar(${leg.id})">
													<i class="icon-hand-right"></i>选择商品车
												</button></td>
										</c:if>
										<c:if test="${leg.emptlyFlag ==2 }">
											<td>无效</td>
											<td><button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td><button class="btn btn-primary btn-xs"
													disabled="disabled" type="button"
													onclick="serachCar(${leg.id})">
													<i class="icon-hand-right"></i>选择商品车
												</button></td>
										</c:if>
										<c:if test="${leg.emptlyFlag ==3 }">
											<td>待定
												<button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="查询不到商品车，是否加入空载">查看原因</button></td>
											<td><button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td>
												<button class="btn btn-primary btn-xs" type="button"
													onclick="setLegEmptly(${leg.id})">
													<i class="icon-ok"></i>是
												</button>
												<button class="btn btn-primary btn-xs" type="button"
													onclick="cancelLeg(${leg.id})">
													<i class="icon-off"></i>否
												</button></td>
										</c:if>
										<td><button class="btn btn-primary btn-xs" type="button"
												onclick="cancelLeg(${leg.id})">
												<i class="icon-off"></i>删除
											</button></td>
									</tr>
								</c:if>
								<c:if test="${leg.btnMessage == null }">
									<tr style="text-align: center;" id="${leg.id }tr">
										<td><fmt:formatNumber value="${leg.loopflag }"
												type="number" pattern="#" />
										</td>
										<td>${leg.startcity }</td>
										<td>${leg.startpoint }</td>
										<td><input type="hidden" id="${leg.id}select"
											value="${leg.customerId}" /> ${leg.selectCustomer}</td>
										<td>${leg.endcity}</td>
										<c:if test="${leg.emptlyFlag ==0 }">
											<td class="${leg.id }+show">有效</td>
											<td class="${leg.id }+show"><button type="button"
													class="btn btn-default btn-xs" data-toggle="tooltip"
													data-placement="top" name="tips" title="${leg.message }">提示</button>
											</td>
											<td class="${leg.id }+show"><button
													class="btn btn-primary btn-xs" type="button"
													title="${leg.btnMessage }" onclick="serachCar(${leg.id})"
													name="${leg.id }">
													<i class="icon-hand-right"></i>选择商品车
												</button></td>
											<!-- 如果查不到商品车的情况 -->
											<td class="${leg.id }+hide" style="display: none">待定（请确认是否加入空载？）</td>
											<td class="${leg.id }+hide" style="display: none"><button
													type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td class="${leg.id }+hide" style="display: none">
												<button class="btn btn-primary btn-xs" type="button"
													onclick="setLegEmptly(${leg.id})">
													<i class="icon-ok"></i>是
												</button>
												<button class="btn btn-primary btn-xs" type="button"
													onclick="cancelLeg(${leg.id})">
													<i class="icon-off"></i>否
												</button></td>
										</c:if>
										<c:if test="${leg.emptlyFlag ==1 }">
											<td>空载</td>
											<td><button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td><button class="btn btn-primary btn-xs" type="button"
													disabled="disabled" onclick="serachCar(${leg.id})">
													<i class="icon-hand-right"></i>选择商品车
												</button></td>
										</c:if>
										<c:if test="${leg.emptlyFlag ==2 }">
											<td>无效</td>
											<td><button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td><button class="btn btn-primary btn-xs"
													disabled="disabled" type="button"
													onclick="serachCar(${leg.id})">
													<i class="icon-hand-right"></i>选择商品车
												</button></td>
										</c:if>
										<c:if test="${leg.emptlyFlag ==3 }">
											<td>待定
												<button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="查询不到商品车，是否加入空载？">查看原因</button></td>
											<td><button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td>
												<button class="btn btn-primary btn-xs" type="button"
													onclick="setLegEmptly(${leg.id})">
													<i class="icon-ok"></i>是
												</button>
												<button class="btn btn-primary btn-xs" type="button"
													onclick="cancelLeg(${leg.id})">
													<i class="icon-off"></i>否
												</button></td>
										</c:if>
										<td><button class="btn btn-primary btn-xs" type="button"
												onclick="cancelLeg(${leg.id})">
												<i class="icon-off"></i>删除
											</button>
										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>

			<!-- 自定义输入线路 -->
			<div class="panel panel-primary" id="selfDiv"
				style="margin: 10px;height: 420px; display: none;">
				<div class="panel-heading">
					<h3 class="panel-title">自定义输入线路</h3>
				</div>
				<div class="panel-body">
					<div style="margin: 0px;">
						<form id="selfselect" action="selfselect.do" name="selfselect"
							method="post">
							<table class="table table-bordered table-hover table-condensed">
								<thead>
									<tr style="text-align: center;">
										<td>出发城市</td>
										<td>提车库</td>
										<td>目的地</td>
										<td>操作</td>
									</tr>
								</thead>
								<tbody>
									<tr style="height: 20px;text-align: center;">
										<td><input type="text" id="startcity" size="12"
											name="startcity">
										</td>
										<td><input type="text" id="startpoint" size="12"
											name="startpoint">
										</td>
										<td><input type="text" id="endtcity" size="12"
											name="endtcity"> <input name="smUserName"
											type="hidden" value="${smuser }"></td>
										<td>
											<button class="btn btn-primary btn-xs" type="button"
												onclick="sureInput()">
												<i class="icon-ok"></i>确定
											</button>
											<button class="btn btn-primary btn-xs" type="button"
												style="margin: 2px;" onclick="deleteall()">
												<i class="icon-ok"></i>删除所有
											</button></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<div style="margin: 0px;">
						<table class="table table-bordered table-hover table-condensed">
							<caption style="background-color: #33CC99">
								自定义输入的有效线路（最多测算6条）</caption>
							<thead>
								<tr style="text-align: center;">
									<td>始发地</td>
									<td>始发地提车点</td>
									<td>客户</td>
									<td>目的地</td>
									<td>有效性</td>
									<td>备注</td>
									<td>操作</td>
									<td>删除</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${prSelfInputLegForm.prSelfinputlegs }"
									var="leg" varStatus="vstatus">
									<tr style="text-align: center;" >
										<td>${leg.startcity }</td>
										<td>${leg.startpoint }</td>
										<td><select name="customer" id="${leg.id}select">
												<option value="0">请选择客户</option>
												<c:forEach items="${leg.smcustomers }" var="leg2">
													<c:choose>
														<c:when
															test="${leg2.vccustomername == leg.selectCustomer}">
															<option value="${leg2.ilineid}" selected="selected">${leg.selectCustomer}</option>
														</c:when>
														<c:otherwise>

															<option value="${leg2.ilineid}">${leg2.vccustomername}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
										</select></td>
										<td>${leg.endcity}</td>
										<c:if test="${leg.emptlyflag ==0 }">

											<td class="${leg.id }+show">有效</td>
											<td class="${leg.id }+show"><button type="button"
													class="btn btn-default btn-xs" data-toggle="tooltip"
													data-placement="top" name="tips" title="${leg.message }">提示</button>
											</td>
											<td class="${leg.id }+show"><button
													class="btn btn-primary btn-xs" type="button"
													title="该线路还没选择商品车，请选择!" onclick="serachCar2(${leg.id})"
													name="${leg.id }">
													<i class="icon-hand-right"></i>选择商品车
												</button></td>
											<!-- 如果查不到商品车的情况 -->
											<td class="${leg.id }+hide" style="display: none">待定（请确认是否加入空载？）</td>
											<td class="${leg.id }+hide" style="display: none"><button
													type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td class="${leg.id }+hide" style="display: none">
												<button class="btn btn-primary btn-xs" type="button"
													onclick="setLegEmptly2(${leg.id})">
													<i class="icon-ok"></i>是
												</button>
												<button class="btn btn-primary btn-xs" type="button"
													onclick="cancelLeg2(${leg.id})">
													<i class="icon-off"></i>否
												</button></td>
										</c:if>
										<c:if test="${leg.emptlyflag ==1 }">
											<td>空载</td>
											<td><button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td><button class="btn btn-primary btn-xs" type="button"
													disabled="disabled" onclick="serachCar2(${leg.id})">
													<i class="icon-hand-right"></i>选择商品车
												</button></td>
										</c:if>
										<c:if test="${leg.emptlyflag ==2 }">
											<td>无效</td>
											<td><button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td><button class="btn btn-primary btn-xs"
													disabled="disabled" type="button"
													onclick="serachCar2(${leg.id})">
													<i class="icon-hand-right"></i>选择商品车
												</button></td>
										</c:if>
										<c:if test="${leg.emptlyflag ==3 }">
											<td>待定（请确认是否加入空载？）</td>
											<td><button type="button" class="btn btn-default btn-xs"
													data-toggle="tooltip" data-placement="top" name="tips"
													title="${leg.message }">提示</button>
											</td>
											<td>
												<button class="btn btn-primary btn-xs" type="button"
													onclick="setLegEmptly2(${leg.id})">
													<i class="icon-ok"></i>是
												</button>
												<button class="btn btn-primary btn-xs" type="button"
													onclick="cancelLeg2(${leg.id})">
													<i class="icon-off"></i>否
												</button></td>
										</c:if>
										<td>
											<button class="btn btn-primary btn-xs" type="button"
												onclick="cancelLeg2(${leg.id})">
												<i class="icon-off"></i>删除
											</button>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<form action="startcaculate.do" method="post" name="caculate"
				onSubmit="return check();">
				<input type="hidden" name="select" value="${selecttype }"> <input
					name="smUserName" type="hidden" value="${smuser }">
				<!-- 选择计算方式 -->
				<div class="panel panel-primary" style="margin: 10px;height: 255px;">
					<div class="panel-heading">
						<h3 class="panel-title">选择计算方式及运输车类型</h3>

					</div>
					<div class="panel-body">
						<div>
							<select class="select" name="caculatetype"
								onchange="caculatetypeSelect()">
								<option value="0">请选择计算方式</option>
								<option value="totalvenprofit">分供方利润/月</option>
								<option value="venprofit">分供方占比</option>
								<option value="unionprofit">中联物流占比</option>
							</select> <input type="text" style="height: 25px;" name="venpro"><span
								id="span" style="color: red"></span>
						</div>
						<div style="margin-top: 12px;">
							<select id="truckId" class="select"
								onchange="changeForm(this.value)" name="truck">
								<option value="-1">请选择</option>
								<c:forEach items="${truckInfos }" var="truck">
									<option value="${truck.id }">${truck.trucktype }</option>
								</c:forEach>
							</select> <input type="text" style="height: 25px;" name="truckcost">
							运输车单公里空载成本
							<table class="table table-hover table-striped table-condensed">
								<tr class="info" style="text-align: center;">
									<td>额定载重</td>
									<td>第1排长度</td>
									<td>第2排长度</td>
									<td>第3排长度</td>
									<td>车辆自重</td>
								</tr>
								<tr class="warning" id="truck_tr" style="text-align: center;">
									<td><input id="loadingWeight" type="text" name="loadingWeight" size="6"  /></td>
									<td><input id="length1" type="text" name="length1" size="6"  /></td>
									<td><input id="length2" type="text" name="length2" size="6"  /></td>
									<td><input id="length3" type="text" name="length3" size="6"  /></td>
									<td><input id="dcweightSelf" type="text" name="dcweightSelf" size="6"  /></td>
								</tr>
							</table>
							<button id="caculate" class="btn btn-primary btn-sm"
								type="submit">开始计算</button>
						</div>
					</div>
				</div>
			</form>
		</div>

		<!-- show -->
		<div class="leftdiv" id="showdiv" style="display: none;">
			<!-- 请选择线路 -->
			<div class="panel panel-primary" style="margin: 10px;height: 150px;">
				<div class="panel-heading">
					<h3 class="panel-title">请选择线路</h3>
				</div>
				<div class="panel-body">
					<div style="width: 100%">
						<p id="radio">
							<input type="radio" name="type" value="0" id="typeradio1"
								style="margin:0" />方式一、导入文件
						</p>
						<form enctype="multipart/form-data" method="post">
							<input type="file" id="fileField2" />
							<button class="btn btn-primary btn-xs" type="button">
								<i class="icon-upload-alt"></i>上传文件
							</button>
						</form>
					</div>
					<p style="margin: 2px;position: absolute;">
						<input type="radio" name="type" value="1" id="typeradio2" />方式二、自定义输入
					</p>
				</div>
			</div>


			<!-- 已选择线路 -->
			<div class="panel panel-primary" id="fileDiv"
				style="margin: 10px;height: 400px;display: block;">
				<div class="panel-heading">
					<h3 class="panel-title">已选择线路</h3>
				</div>
				<div class="panel-body" id="selectshow">

					<table class="table table-bordered table-hover  table-condensed">
						<thead>
							<tr style="text-align:center;">
								<td id="selectshow2">编号</td>
								<td>始发地</td>
								<td>始发地提车点</td>
								<td>目的地</td>
								<td>有效性</td>
								<td>备注</td>
								<td>操作</td>
								<td>删除</td>
							</tr>
						</thead>
						<tbody>
							<tr style="text-align: center;">
								<td>2</td>
								<td>深圳</td>
								<td>深圳比亚迪库</td>
								<td>南昌</td>

								<td>有效</td>
								<td><button type="button" class="btn btn-default btn-xs"
										data-toggle="tooltip" data-placement="top"
										title="此单线的目的地【南昌】离最近的提车库之间空载距离为12.5">提示</button>
								</td>
								<td><button class="btn btn-primary btn-xs" type="button"
										id="selectshow4" title="该线路还没选择商品车，请选择!">
										<i class="icon-hand-right"></i>选择商品车
									</button></td>
								<td><button class="btn btn-primary btn-xs" type="button"
										id="selectshow5">
										<i class="icon-off"></i>删除
									</button>
								</td>
							</tr>



							<tr class="success" style="text-align: center;">
								<td>3 <input type="hidden" value="3.0" /></td>
								<td>南昌 <input type="hidden" value="南昌"
									name="prLegfileimports[1].startcity" /></td>
								<td>南昌江铃小蓝库 <input type="hidden" value="南昌江铃小蓝库" /></td>
								<td>深圳 <input type="hidden" value="深圳" /></td>
								<td>有效</td>
								<td><button type="button" class="btn btn-default btn-xs"
										data-toggle="tooltip" data-placement="top"
										title="请选择车辆进行下一步操作！">提示</button>
								</td>
								<td><button class="btn btn-primary btn-xs" type="button"
										title="该线路还没选择商品车，请选择!" name="664">
										<i class="icon-hand-right"></i>选择商品车
									</button></td>
								<td><button class="btn btn-primary btn-xs" type="button">
										<i class="icon-off"></i>删除
									</button></td>
							</tr>
							<tr style="text-align: center;">
								<td>3</td>
								<td>深圳</td>
								<td>深圳比亚迪库</td>
								<td>南昌</td>

								<td>有效</td>
								<td><button type="button" class="btn btn-default btn-xs"
										data-toggle="tooltip" data-placement="top" name="tips"
										title="请选择车辆进行下一步操作！">提示</button>
								</td>
								<td><button class="btn btn-primary btn-xs" type="button"
										title="该线路还没选择商品车，请选择!" name="665">
										<i class="icon-hand-right"></i>选择商品车
									</button></td>




								<td><button class="btn btn-primary btn-xs" type="button">
										<i class="icon-off"></i>删除
									</button>
								</td>
							</tr>



							<tr class="success" style="text-align: center;">
								<td>4 <input type="hidden" value="4.0"
									name="prLegfileimports[3].loopflag" /></td>
								<td>福州 <input type="hidden" value="福州"
									name="prLegfileimports[3].startcity" /></td>
								<td>福州 <input type="hidden" value="福州"
									name="prLegfileimports[3].startpoint" /></td>
								<td>深圳 <input type="hidden" value="深圳"
									name="prLegfileimports[3].endcity" /></td>

								<td>待定（请确认是否加入空载？）</td>
								<td><button type="button" class="btn btn-default btn-xs"
										data-toggle="tooltip" data-placement="top" name="tips"
										title="该条线路查不到商品车请确认是否当作加入空载?默认视为无效线路！">提示</button>
								</td>
								<td>
									<button class="btn btn-primary btn-xs" type="button"
										id="selectshow3">
										<i class="icon-ok"></i>是
									</button>
									<button class="btn btn-primary btn-xs" type="button">
										<i class="icon-off"></i>否
									</button></td>

								<td><button class="btn btn-primary btn-xs" type="button">
										<i class="icon-off"></i>删除
									</button></td>
							</tr>
							<tr style="text-align: center;">
								<td>4</td>
								<td>深圳</td>
								<td>深圳比亚迪库</td>
								<td>福州</td>

								<td>有效</td>
								<td><button type="button" class="btn btn-default btn-xs"
										data-toggle="tooltip" data-placement="top" name="tips"
										title="请选择车辆进行下一步操作！">提示</button>
								</td>
								<td><button class="btn btn-primary btn-xs" type="button"
										title="该线路还没选择商品车，请选择!" name="663">
										<i class="icon-hand-right"></i>选择商品车
									</button></td>
								<td><button class="btn btn-primary btn-xs" type="button">
										<i class="icon-off"></i>删除
									</button>
								</td>
							</tr>



						</tbody>
					</table>
				</div>
			</div>
			<form method="post" name="caculate">
				<input type="hidden" value="selfinput"> <input
					name="smUserName" type="hidden" value="pricemodel">
				<!-- 选择计算方式 -->
				<div class="panel panel-primary" style="margin: 10px;height: 255px;">
					<div class="panel-heading">
						<h3 class="panel-title">选择计算方式及运输车类型</h3>

					</div>
					<div class="panel-body">
						<div>
							<select class="select" name="caculatetype" id="selectshow6">
								<option value="0">请选择计算方式</option>
								<option value="totalvenprofit">分供方利润/月</option>
								<option value="venprofit">分供方占比</option>
								<option value="unionprofit">中联物流占比</option>
							</select> <input type="text" style="height: 25px;" name="venpro"
								id="selectshow7"><span id="span" style="color: red"></span>
						</div>
						<div style="margin-top: 12px;">
							<select id="truckId" class="select" id="selectshow8">
								<option value="-1">请选择</option>

								<option value="1">4轴航母</option>

								<option value="2">5轴单车</option>

								<option value="54">5轴航母</option>

								<option value="56">6轴航母</option>

							</select> <input type="text" style="height: 25px;" id="selectshow8">
							运输车单公里空载成本
							<table class="table table-hover table-striped table-condensed">
								<tr class="info" style="text-align: center;">
									<td>额定载重</td>
									<td>第1排长度</td>
									<td>第2排长度</td>
									<td>第3排长度</td>
									<td>车辆自重</td>
								</tr>
								<tr class="warning" id="truck_tr" style="text-align: center;">

								</tr>
							</table>
							<button class="btn btn-primary btn-sm" id="selectshow9">开始计算</button>
						</div>
					</div>
				</div>
			</form>
		</div>


		<!-- 右侧DIV -->
		<div class="rightdiv">
			<!-- 线路详情 -->
			<%--<div class="panel panel-primary"
			style="margin: 10px; display: block">
			<div class="panel-heading">
				<h3 class="panel-title">线路详情信息</h3>
			</div>
			<div class="panel-body">
				<div style="margin: 0px;">
					<table class="table table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th>出发城市</th>
								<th>提车库</th>
								<th>目的地</th>
								<th>应收公里数</th>
								<th>空载距离</th>
								<th>天数</th>
							</tr>
						</thead>
						<tbody>
							<tr style="height: 20px;">
								<td>九江</td>
								<td>昌河九江长江库</td>
								<td>深圳</td>
								<td>？？？</td>
								<td>？？？</td>
								<td>434</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<!-- 商品车详细信息 -->
		<div class="panel panel-primary"
			style="margin: 10px; display: block;height: 650px;">
			<div class="panel-heading">
				<h3 class="panel-title">路线对应的商品车信息</h3>
			</div>
			<div class="panel-body">
				<div style="margin: 0px;">
				<table class="table table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th>选择</th>
								<th>车名</th>
								<th>重量</th>
								<th>宽度</th>
								<th>应收单价</th>
								<th>应付单价</th>
								<th>发车概率</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="checkbox" value="id"></td>
								<td>SSS</td>
								<td>22</td>
								<td>343</td>
								<td>33</td>
								<td>32</td>
								<td><input type="text" value="0.6" size="5"></td>
							</tr>
							<tr class="success">
								<td><input type="checkbox" value="id"></td>
								<td>SSS</td>
								<td>22</td>
								<td>343</td>
								<td>33</td>
								<td>32</td>
								<td><input type="text" value="0.6" size="5"></td>
							</tr>
							<tr class="error">
							<td><input type="checkbox" value="id"></td>
								<td>SSS</td>
								<td>22</td>
								<td>343</td>
								<td>33</td>
								<td>32</td>
								<td><input type="text" value="0.6" size="5"></td>
							</tr>
							<tr class="warning">
								<td><input type="checkbox" value="id"></td>
								<td>SSS</td>
								<td>22</td>
								<td>343</td>
								<td>33</td>
								<td>32</td>
								<td><input type="text" value="0.6" size="5"></td>
							</tr>
							<tr class="info">
								<td><input type="checkbox" value="id"></td>
								<td>SSS</td>
								<td>22</td>
								<td>343</td>
								<td>33</td>
								<td>32</td>
								<td><input type="text" value="0.6" size="5"></td>
							</tr>
							<tr>
								<td><input type="checkbox" value="id"></td>
								<td>SSS</td>
								<td>22</td>
								<td>343</td>
								<td>33</td>
								<td>32</td>
								<td><input type="text" value="0.6" size="5"></td>
							</tr>

						</tbody>
					</table>
					<button class="btn btn-primary" type="button" style="margin: 5px;">确定修改</button>
				</div>
			</div>
		</div>
		
	--%>
			<iframe name="right" src="right.jsp" scrolling="yes" width='100%'
				height='100%' frameborder=0></iframe>

		</div>

	</div>


</body>
</html>
