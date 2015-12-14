<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/taglib.jsp"%>

<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%
	session.setAttribute("index", "index");
	session.setMaxInactiveInterval(15);//设置超时时间
%>
<base href="<%=basePath%>">
<%
	String urlString = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/index.do?legId=" + request.getParameter("legId")
			+ "&&truckId=" + request.getParameter("truckId");
	if (request.getParameter("legpageNo") != null) {
		urlString += "&&legpageNo=" + request.getParameter("legpageNo")
				+ "&&";
	}else{
		urlString += "&&";
	}
%>

<title>My JSP 'fail.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/stylesheets/jquery.loadmask.css"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/javascripts/jquery.loadmask.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.left {
	margin-left: 10%;
	margin-top: 2%;
	float: left;
	width: 40%;
	border: 2px solid green;
	height:100%;
}

.right {
	
	margin-right: 4%;
	margin-top: 2%;
	float: right;
	width: 40%;
	border: 2px solid green;
	height:90%;
}

.left_top {
	border-bottom: 2px solid red;
	border-top: 2px solid red;
	padding: 1px;
	margin: 5px;
}

.right_top {
	border-bottom: 2px solid red;
	border-top: 2px solid red;
	padding: 10px;
	margin: 10px;
}

.left_buttom {
	border-bottom: 2px solid blue;
	border-top: 2px solid blue;
	padding: 10px;
	margin: 10px;
	margin-top: 20px;
}

.right_buttom {
	border-bottom: 2px solid blue;
	border-top: 2px solid blue;
	padding: 1em;
	margin: 1em;
	margin-top: 20px;
	width: 90%;
	height:45em;
}

.select {
	font-size: 20px;
	color: blue;
}
.main{
	width: 100%;
	height:100%;
	margin:0 auto;
}
</style>
</head>

<body>
<div id="warn" class="alert alert-warning alert-dismissible" role="alert" style="display: none;text-align: center;">
  <strong>提示!</strong><p id="warn_p">商品车有些属性和拖车相关请选择拖车 </p>
</div>

<div class="main">
	<!-- 左边DIV -->
	<div class="left">
		<p align="left" class="text-info">请选择线路(你可以最多选择2条线路)</p>
		<div id="left" class="left_top">
			<table class="table table-hover table-striped table-condensed">
				<tr style="text-align: center;">
					<td>选择</td>
					<td>始发地</td>
					<td>目的地</td>
					<td>操作</td>
				</tr>

				<tbody>
					<c:forEach items="${legInfos }" var="leg">
						<tr class="success" style="text-align: center;">
							<td><input type="checkbox" name="legId" value="${leg.id }">
							</td>
							<td>${leg.origin }</td>
							<td>${leg.destination }</td>
							<td><input onclick="searchDetail(${leg.id })"
								class="btn btn-primary btn-sm" type="button" value="查询车辆信息" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${legtotalPages>0 }">
				<div>
					第${legpage }页；共${legtotalPages }页
					<ul class="pagination pagination-sm">
						<c:choose>
							<c:when test="${legpage>1 }">
								<li><a href="index.do?legpageNo=${legpage-1}">上一页 </a></li>
							</c:when>
							<c:otherwise>
								<li class="previous disabled"><a>上一页</a></li>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${legpage < legtotalPages }">
								<li><a href="index.do?legpageNo=${legpage+1}">下一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="previous disabled"><a>下一页</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>
			</c:if>

		</div>
		<div id="left_buttom" class="left_buttom">
			<span><p class="text-info">请选择拖车：</p> <select id="truckId"
				class="select">
					<option value="-1">请选择</option>
					<c:forEach items="${truckInfos }" var="truck">
						<option value="${truck.id }">${truck.trucktype }</option>

					</c:forEach>
			</select> </span>
			<table class="table table-hover table-striped table-condensed">
				<caption>拖车相关信息</caption>
				<tr>
					<td>最大载重</td>
					<td id="max_td">(T)</td>
				</tr>
				<tr>
					<td>第一排长度</td>
					<td id="len1_td">(M)</td>
				</tr>
				<tr>
					<td>第二排长度</td>
					<td id="len2_td">(M)</td>
				</tr>
				<tr>
					<td>第三排长度</td>
					<td id="len3_td">(M)</td>
				</tr>
			</table>
		</div>
	
		<div align="right" style="margin-left: 20px;">
			<table class="table table-hover table-striped table-condensed">
				<caption>运输方案（三选一）</caption>
				<tr>
					<td align="left"><input type="radio" id="ven11"
						name="vendorPro"><input type="text" id="ven1"
						style="margin-left: 15px;">分供方利润/月 <span
						style="color: red" id="errorspan"></span></td>
				</tr>
				<tr>
					<td align="left"><input type="radio" id="ven22"
						name="vendorPro"><input type="text" id="ven2"
						style="margin-left: 15px;">分供方利润率 <span style="color: red"
						id="errorspan2"></span></td>
				</tr>
				<tr>
					<td align="left"><input type="radio" id="ven33"
						name="vendorPro"><input type="text" id="ven3"
						style="margin-left: 15px;">中联利润率 <span style="color: red"
						id="errorspan3"></span></td>
				</tr>
			</table>
			<button id="caculate" class="btn btn-primary" type="button"
				onclick="startCaculate()" style="margin: 5px;">开始计算</button>
			<button class="btn btn-primary" type="button" style="margin: 5px;">清除</button>
		</div>
	</div>
	
		<!-- 右边DIV -->
	<div id="right" class="right">
		<p align="center" class="text-info">线路：${slegInfo.origin
			}---->${slegInfo.destination }</p>
		<div id="right_top" class="right_top">

			<table class="table table-hover table-striped">
				<caption>线路详情:</caption>
				<tr style="text-align: center;">
					<td>收入里程(KM)</td>
					<td>实际里程(KM)</td>
					<td>成本/拖车(RMB)</td>
				</tr>
				<tr style="text-align: center;color: blue">
					<td>${slegInfo.incomeDistance }</td>
					<td>${slegInfo.actualDistance }</td>
					<td>${slegInfo.costByTruck }</td>
				</tr>

			</table>
		</div>
		<div id="right_buttom" class="right_buttom">
			<p class="text-danger">${message }</p>
			<table class="table table-hover table-striped">
				<caption>商品车详情</caption>
				<tr style="text-align: center;">
					<td>商品车类型</td>
					<td>长度</td>
					<td>重量</td>
					<td>收入/每公里</td>
					<td>出现比率（6个月）</td>
				</tr>
				<c:forEach items="${carInfos }" var="legcar">
					<tr style="text-align: center;">
						<td>${legcar.carname }</td>
						<td>${legcar.length }</td>
						<td>${legcar.weight }</td>
						<td>${legcar.incomePrice }</td>
						<td>${legcar.ratio }</td>
					</tr>
				</c:forEach>
			</table>

			<c:if test="${totalPages>0 }">
				<div>
					第${page }页；共${totalPages }页
					<ul class="pagination pagination-sm">
						<c:choose>
							<c:when test="${page>1 }">
								<li><a href="<%=urlString%>pageNo=${page-1}">上一页 </a></li>
							</c:when>
							<c:otherwise>
								<li class="previous disabled"><a>上一页</a></li>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${page < totalPages }">
								<li><a href="<%=urlString%>pageNo=${page+1}">下一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="previous disabled"><a>下一页</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>
			</c:if>
		</div>

	</div>
</div>	

	<script type="text/javascript">
		$(function() {

			function next() {

				if ($("input[name='lineid']:checked").val()
						&& $("input[name='dragid']:checked").val()) {
					var lindeId = $("input[name='lineid']:checked").val();
					var dragId = $("input[name='dragid']:checked").val();
					document.all.form1.action = "second.do?lineId=" + lindeId
							+ "&&dragId=" + dragId;
				} else {
					alert("线路或者拖车未选");
				}

			}
			$("input[type='checkbox']").click(function() {
				if ($("input[name='legId']:checked").length <= 4) {
					$("#caculate").attr("disabled", false);
					$("#warn").hide();
				} else {
					$("#caculate").attr("disabled", "disabled");
					$("#warn").show();
					$("#warn_p").html("最多可以选择4条线路");
				}
			});
		//选择拖车
			$("#truckId").click(function() {
				if (parseInt($("#truckId").val()) !== -1) {
				
					$.post("getTruckInfo.do", {
						"truckId" : $("#truckId").val()
					}, function(data) {
						$("#max_td").html(data.loadingWeight + "(T)");
						$("#len1_td").html(data.length1 + "(M)");
						$("#len2_td").html(data.length2 + "(M)");
						$("#len3_td").html(data.length3 + "(M)");

					});
				}else{
					$("#max_td").html("(T)");
					$("#len1_td").html("(M)");
					$("#len2_td").html("(M)");
					$("#len3_td").html("(M)");
				}

			})
			
		$("#ven1").bind("click",function(){
			$("#ven11").attr("checked","checked");
		})
		$("#ven2").bind("click",function(){
			
			$("#ven22").attr("checked","checked");
		})
		$("#ven3").bind("click",function(){
			$("#ven33").attr("checked","true");
		})
		
		})
		//查询车辆相关信息
		function searchDetail(obj){
			var url = GetRequest();
			
			if(parseInt($("#truckId").val()) !== -1){
				$("#warn").hide();
				if(GetQueryString("legpageNo")!==null){
					window.location.href="index.do?legId="+obj+"&&truckId="+$("#truckId").val()+"&&legpageNo="+GetQueryString("legpageNo");
				}else{
					window.location.href="index.do?legId="+obj+"&&truckId="+$("#truckId").val();
				}
			}else{
					//alert("请选择拖车")
				$("#warn").show();
				$("#warn_p").html("商品车一些属性与拖车相关，请选择拖车类型！");
				
			}
	
			
		}
		//js获取请求参数
		function GetRequest() {
			  var url = location.search; //获取url中"?"符后的字串
			   var theRequest = new Object();
			   if (url.indexOf("?") != -1) {
			      var str = url.substr(1);
			      strs = str.split("&");
			      for(var i = 0; i < strs.length; i ++) {
			         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
			      }
			   }
			   return theRequest;
			}
		function GetQueryString(name) {

			   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");

			   var r = window.location.search.substr(1).match(reg);

			   if (r!=null)
				 return unescape(r[2]);
			   return null;
		}
		function startCaculate(){
			$("#warn").hide();
			var vendor = $("#ven1").val();//分供方每月利润
			var patrn=/^[0-9]+\.{0,1}[0-9]{0,2}$/; //分供方每月利润	只能为小数或者整数
			var patrn2 = /^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/;
			$("#ven11")[0].value = $("#ven1").val();
			$("#ven22")[0].value = $("#ven2").val();
			$("#ven33")[0].value = $("#ven3").val();
			var legId = [];
			  $("input[name='legId']:checked").each(function () {
				  legId.push(this.value);
	            });
			  if(!$("input[name='legId']:checked").val() || parseInt($("#truckId").val()) == -1){
				  $("#warn").show();
					$("#warn_p").html("拖车或者路线未选！");
					//alert("拖车或者路线未选");
					return false;
				}  
			  
			var urlstr = "caculate.do?legId="+legId+"&&truckId="+$("#truckId").val();
			if(!$("input[name='vendorPro']:checked").val()){
				$("#warn").show();
				$("#warn_p").html("请选择运输方案!");
				//alert("请选择方案");
				return false;
			}	
			else if($("input[name='vendorPro'][id='ven11']:checked").val() ){
				 if(!patrn.exec($("input[name='vendorPro'][id='ven11']:checked").val())){
					$("#errorspan").html("格式不符合,只能为整数或小数");
					return false;
				}else{
					$("#errorspan").html("");
					urlstr += "&&vendor="+$("input[name='vendorPro'][id='ven11']:checked").val();
					  window.location.href = urlstr;
				}
				
			}else if($("input[name='vendorPro'][id='ven22']:checked").val() ){
				 if(!patrn2.exec($("input[name='vendorPro'][id='ven22']:checked").val())){
						$("#errorspan2").html("格式不符合,只能为小数");
						return false;
					}else if(parseInt($("input[name='vendorPro'][id='ven22']:checked").val())>=1){
						$("#errorspan2").html("利润率不能大于1");
					
					}else{
						urlstr += "&&venpro="+$("input[name='vendorPro'][id='ven22']:checked").val();
						  window.location.href = urlstr;
					}
					
				}
			else if($("input[name='vendorPro'][id='ven33']:checked").val() ){
				 if(!patrn2.exec($("input[name='vendorPro'][id='ven33']:checked").val())){
						$("#errorspan3").html("格式不符合,只能为小数");
						return false;
					}else if(parseInt($("input[name='vendorPro'][id='ven33']:checked").val())>=1){
						$("#errorspan3").html("利润率不能大于1");
					
					}else{
						urlstr += "&&unionpro="+$("input[name='vendorPro'][id='ven33']:checked").val();
						  window.location.href = urlstr;
					}
					
				}
			$("body").unmask();
			 $("body").mask("正在计算，请稍等……");
		}
		function selectRatio(obj){
			console.log(obj.id);
			//console.log($("#"+obj.id).parent().find("input[name='vendorPro']").attr("checked",true));
			$("#"+obj.id+"1").attr("checked",true);
		}
	</script>
</body>
</html>
