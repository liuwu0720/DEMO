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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<base href="<%=basePath%>">

<title>合同信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
<style type="text/css">
#tab {
	border: 1px solid black;
	border-collapse: collapse;
	margin: 0 auto;
	width: 50%;
}

#tab  td {
	border: 1px solid black;
	width: 20%;
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
<script type="text/javascript"
	src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	//删除附件
	function delFile(id){
		//alert('id='+id);
		var isDel=confirm('确认删除吗？');
		if(!isDel){
			return;
		}
		$.post('<%=basePath%>contractController/delContractFile',
				{id:id},
				function(data){
					alert(data.message);
					if(data.isSuccess){
						var contractId=data.contractId;
						window.location.href='<%=basePath%>contractController/viewContract?id='+contractId;
					}
				},
				'json');
	}
</script>
</head>

<body>
	<div class="rightsider">

		<div class="info_tabs clearfix">
			<a class="hover">合同添加</a>
		</div>
		<dl class="notice clearfix">
			<dd>合同资料</dd>
		</dl>
		<form action="<%=basePath%>contractController/saveContract"
			method="post" enctype="multipart/form-data" id="myform">
			<table id="tab">
				<tr>
					<td colspan="4">
						<div style="float:left;margin: 5px 150px 0px 5px"><img src="<%=basePath%>css/unlcn.png"></div>
						<div><span style="font-family: 黑体;font-size: 20px;font-weight: bolder;float:left">中联物流（中国）有限公司<br/>合同评审表</span></div>
					</td>
				</tr>
				<tr>
					<td>合同名称</td>
					<td>${contract.vcContractname}</td>
					<td>合同编号</td>
					<td>${contract.vcContractno}</td>
				</tr>
				<tr>
					<td>甲方</td>
					<td>${contract.vcPartya}</td>
					<td>乙方</td>
					<td>${contract.vcPartyb}</td>
				</tr>
				<tr>
					<td>合同类别</td>
					<td>${partype.vcType}</td>
					<td>${subtype.vcType}</td>
					<td id="td_pro">
						<!--整车收入合同 --> <c:if test="${subtype.vcTypeNo=='scarload'}">

							<c:if test="${contract.nLong==0}">
									长期合同（1年或一年以上）
									
								</c:if>
							<c:if test="${contract.nLong==1}">
									临时合同
									
								</c:if>

						</c:if> <!--零部件收入合同--> <c:if test="${subtype.vcTypeNo=='spartincome'}">

							<c:if test="${contract.nMainsign==0}">
									与主机厂签订的
									
								</c:if>
							<c:if test="${contract.nMainsign==1}">
									非与主机厂签订的
									
								</c:if>

						</c:if> <!--物流器具设备采购合同--> <c:if test="${subtype.vcTypeNo=='sequipment'}">

							<c:if test="${contract.nPrice==0}">
									单件单价≤5万
									
								</c:if>
							<c:if test="${contract.nPrice==1}">
									单件单价＞5万
									
								</c:if>

						</c:if> <!--办公设备采购合同--> <c:if test="${subtype.vcTypeNo=='soffical'}">

							<c:if test="${contract.nPrice==0}">
									单件单价≤5万
									
								</c:if>
							<c:if test="${contract.nPrice==1}">
									单件单价＞5万
									
								</c:if>

						</c:if> <!--土地租赁合同--> <c:if test="${subtype.vcTypeNo=='sland'}">

							<c:if test="${contract.nYearmoney==0}">
									年度金额≤50万
									
								</c:if>
							<c:if test="${contract.nYearmoney==1}">
									年度金额＞50万
									
								</c:if>

						</c:if> <!--以项目向下采购合同--> <c:if test="${subtype.vcTypeNo=='sproperty'}">


							<c:if test="${contract.nYearmoney==0}">
									年度金额≤20万
									
								</c:if>
							<c:if test="${contract.nYearmoney==1}">
									年度金额＞20万
									
								</c:if>

						</c:if> <!--其他类合同--> <c:if test="${subtype.vcTypeNo=='sother'}">

							<c:if test="${contract.nExpect==0}">
									预算内
									
								</c:if>
							<c:if test="${contract.nExpect==1}">
									预算外
									
								</c:if>

						</c:if>
					</td>
				</tr>
				<tr>
					<td>合同生效日期</td>
					<td>${contract.dtStart}</td>
					<td>终止日期</td>
					<td>${contract.dtEnd}</td>
				</tr>
				<tr>
					<td>签约类别</td>
					<td><c:if test="${contract.nNew==0}">
							新签
						</c:if> <c:if test="${contract.nNew==1}">
							续签
						</c:if></td>
					<td>紧急程度</td>
					<td><c:if test="${contract.nEmergency==0}">
							正常
						</c:if> <c:if test="${contract.nEmergency==1}">
							紧急
						</c:if></td>
				</tr>
				<tr>
					<td>总收入</td>
					<td>总成本</td>
					<td>总运量</td>
					<td>毛利率</td>
				</tr>
				<tr>
					<td>${contract.nTotalmoney }</td>
					<td>${contract.nTotalprice }</td>
					<td>${contract.nTotalnum }</td>
					<td>${contract.nRate }</td>
				</tr>
				<tr>
					<td><span style="font-weight:bolder">附件</span></td>
					<td colspan="3" style="text-align: left">
						<c:forEach
							items="${files }" var="file">
							${file.vcFilename }&nbsp;&nbsp;
							<a
								href="<%=basePath%>contractController/downloadFile?fileName=${file.vcFilename }"
								target="_blank"> <span
								style="font-weight:bolder;font-family:华文楷体;font-size:15px;color:#00CD00">下载</span></a>&nbsp;&nbsp;
							<a href="javascript:;" style="font-weight:bolder;font-family:华文楷体;font-size:15px;color:#00CD00"
							onclick="delFile(${file.id})">删除</a>
							<br />
						</c:forEach></td>
				</tr>
				<tr id="td1">
					<td colspan="4">合同内容及相关要求</td>
				</tr>
				<tr style="height:300px;">
					<td colspan="4"><textarea rows="15" cols="100"
							name="vcContent" padding="0" disabled="disabled">
							${contract.vcContent}
						</textarea> <script type="text/javascript">
							CKEDITOR.replace('vcContent');
						</script></td>
				</tr>

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
							<c:forEach items="${contractApprovals}" var="co">
								<tr>
									<td bgcolor="#FFFFFF"><fmt:formatDate
											value="${co.dtApproval}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td bgcolor="#FFFFFF">${co.vcDeptName }</td>		
									<td bgcolor="#FFFFFF">${co.vcApprovalname }</td>
									<td bgcolor="#FFFFFF">${co.vcComment }</td>
									<td bgcolor="#FFFFFF">${co.vcNote }</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4"><input type="button" value="返回"
						onclick="javascript:history.go(-1)"
						style="background:#ff6600;width: 100px;align:center" /></td>
				</tr>
			</table>
			<!-- id -->
			<input type="hidden" name="lineid" value="${contract.lineid}" /> <input
				type="hidden" name="iUser" value="${contract.iUser}" /> <input
				type="hidden" name="nEnable" value="${contract.nEnable}" /> <input
				type="hidden" name="nEnable" value="${contract.dtCreate}" />
		</form>
	</div>
</body>
</html>
