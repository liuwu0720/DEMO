<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'taskForm.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
<style type="text/css">
#tab {
	border: 1px solid black;
	border-collapse: collapse;
	margin: 0 auto;
	width: 60%;
}

#tab  td {
	border: 1px solid black;
	height: 30px;
	padding: 5px;
	margin: 0;
	text-align: center;
}

#td1 {
	background-color: #ff6600;
}
</style>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
	
	function preview() {
		$("#tab").css('width', '650px');
		var bdhtml=window.document.body.innerHTML;
		var startStr="<!--startprint-->";//设置打印开始区域 
		var endStr="<!--endprint-->";//设置打印结束区域 
		var printHtml=bdhtml.substring(bdhtml.indexOf(startStr)+startStr.length,bdhtml.indexOf(endStr));//从标记里获取需要打印的页面 
		window.document.body.innerHTML=printHtml;//需要打印的页面 
		window.print(); 
		window.document.body.innerHTML=bdhtml;//还原界面 
	}
</script>

</head>


<body>
	<div class="rightsider">

		<div class="info_tabs clearfix">
			<a class="hover">费用申请资料</a>
		</div>
		<dl class="notice clearfix">
			<dd>费用申请资料</dd>
		</dl>
		<form id="myform">
			<!--startprint-->
			<table id="tab">
				<tr>
					<td colspan="4">
						<div style="float:left;">
							<img src="<%=basePath%>css/unlcn.png">
						</div>
						<div
							style="text-align: center;font-family: 黑体;font-size: 20px;font-weight: bolder; ">
							中联物流（中国）有限公司费用申请表</div>
					</td>
				</tr>
				<tr>
					<td>报销单编号</td>
					<td>${costApply.vcApplyNo }</td>
					<td>申请事由</td>
					<td>${costApply.vcReason }</td>
				</tr>
				<tr>
					<td>费用类别</td>
					<td>${costApply.vcCosttypename }</td>
					<td>费用金额</td>
					<td>${costApply.nCost }</td>
				</tr>
				<tr>
					<td>费用申请人</td>
					<td>${costApply.vcName }</td>
					<td>所属部门</td>
					<td>${costApply.vcDeptName }</td>
				</tr>
				<tr>
					<td>申请日期</td>
					<td><fmt:formatDate value="${costApply.dtApply }"
							pattern="yyyy-MM-dd " /></td>
					<td>状态</td>
					<td><c:if test="${costApply.nState==0}">初始录入</c:if> <c:if
							test="${costApply.nState==1}">审核中</c:if> <c:if
							test="${costApply.nState==2}">完成</c:if> <c:if
							test="${costApply.nState==3}">审核中(驳回)</c:if></td>
				</tr>
				<tr>
					<td>费用承担部门</td>
					<td>${costApply.vcDeptName2}</td>
					<td>报销公司</td>
					<td>${costApply.vcCompany}</td>
				</tr>
				<tr>
					<td>收款人银行帐号</td>
					<td>${costApply.vcBank}</td>
					<td>收款人</td>
					<td>${costApply.vcAcceptMan}</td>
				</tr>
				<tr>
					<td>费用承担公司</td>
					<td>${costApply.vcCompany2}</td>
					<td>结算方式</td>
					<c:if test="${costApply.iPayType==1 }">
						<td>银行转帐</td>
					</c:if>
					<c:if test="${costApply.iPayType ==2 }">
						<td>现金</td>
					</c:if>
				</tr>
				<c:if test="${costApply.vcNote==null }">
				<tr id="td1">
					<td colspan="4">费用明细</td>

				</tr>
				</c:if>
				<tr>
					<td colspan="4"><c:if
							test="${fn:length(travelfees.travelfees)>0}">

							<%@ include file="travel_table.jsp"%>
						</c:if> <c:if test="${fn:length(feeTraffics.feeTraffics)>0}">
							<%@ include file="traffic_table.jsp"%>
						</c:if> <c:if test="${fn:length(feeTels.feeTels)>0}">
							<%@ include file="phone_table.jsp"%>
						</c:if></td>
					<c:if test="${costApply.vcNote!=null }">
						<tr id="td1">
							<td colspan="4">申请备注</td>
						</tr>

						<tr>
							<td colspan="4"><textarea cols="100" name="vcNote">${costApply.vcNote }</textarea></td>
						</tr>
					</c:if>
				<!-- <tr id="td1">
					<td colspan="4">审批意见</td>
				</tr>
				<tr>
					<td colspan="4"><textarea rows="3" cols="100" name="comment"
							placeholder="请填写审批意见" padding="0">							
						</textarea></td>
				</tr>
 -->
				<tr id="td1">
					<td colspan="4">审批记录</td>
				</tr>
				<tr>
					<td colspan="4">
						<table style="width: 100%;border: 1px solid ;">
							<tr>
								<td bgcolor="d3eaef"><span>时间</span></td>
								<td bgcolor="d3eaef"><span>部门</span></td>
								<td bgcolor="d3eaef"><span>批注人</span></td>
								<td bgcolor="d3eaef"><span>批注信息</span></td>
								<td bgcolor="d3eaef"><span>备注</span></td>
							</tr>
							<c:forEach items="${costAuditrecords}" var="co">
								<tr>
									<td bgcolor="#FFFFFF"><fmt:formatDate
											value="${co.dtAudit}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td bgcolor="#FFFFFF">${co.vcDept }</td>
									<td bgcolor="#FFFFFF">${co.vcUser }</td>
									<td bgcolor="#FFFFFF">${co.vcComment }</td>
									<td bgcolor="#FFFFFF">${co.vcNote }</td>
								</tr>
							</c:forEach>

						</table>
					</td>
				</tr>
				<!--endprint-->
				<tr>
					<td colspan="4"><input type="button" value="返回" onclick="javascript:history.go(-1)"
						style="background:#ff6600;width: 100px;align:center" />
							<input type="button" value="打印" onclick="preview()"
					 style="background:#ff6600;width: 100px;align:center"/>
					</td>
				</tr>
			</table>
			
			
		</form>
	</div>
</body>
</html>
