<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'costfeeAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
	<style type="text/css">
		#tab {
	border:1px solid black;
	border-collapse: collapse;
	margin:0 auto; 
	width:40%;
}
	#tab  td {
	border:1px solid black;
	width:20%;
	height:30px; 
	padding: 5px;
	margin: 0;
	text-align: center;
}
	</style>
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		//提交表单
		function submitForm(){
			var iDept=$('#sel1 option:selected').val();
			if(iDept==0){
				alert('请选择公司');
				return;
			}
			var vcUserName=$('#vcUserName').val();
			if(!vcUserName){
				alert("请填写对接人姓名");
				$('#vcUserName').focus();
				return;
			}
			var nLevel=$('#sel2 option:selected').val();
			if(nLevel==0){
				alert('请选择等级');
				return;
			}
			//alert('ddd');
			var lineid=$('#lineid').val();
			var nEnable=$('#nEnable').val();
			$.post('<%=basePath%>feeFinancialController/save',
					{iDept:iDept,vcUserName:vcUserName,nLevel:nLevel,lineid:lineid,nEnable:nEnable},
					function(map){
						alert(map.message);
						if(map.isSuccess==true){
							window.location.href='<%=basePath%>feeFinancialController/findAll';
						}
					},
					'json');
		}
	</script>
  </head>
  
  <body>
    <div class="rightsider">

		<div class="info_tabs clearfix">
			<a  class="hover">费用审批部门财务添加</a>
		</div>
		<dl class="notice clearfix">
			<dd>费用审批财务部门关联管理</dd>
		</dl>
		<form action="<%=basePath%>feeFinancialController/save" method="post" 
			id="myform">
			<table id="tab">
				<tr>
					<td>公司:</td>
					<td>
						<select name="iDept" id="sel1">
							<c:if test="${financial.iDept==null}" >
								<option value="0">--请选择--</option>
							</c:if>
							<c:forEach items="${companys}" var="company">
								<c:if test="${financial.iDept==company.lineid}" var="isTrue">
									<option value="${company.lineid}" selected="selected">${company.deptname}</option>
								</c:if>
								<c:if test="${!isTrue}">
									<option value="${company.lineid}">${company.deptname}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>对接人：</td>
					<td>
						<input id="vcUserName" name="vcUserName" value="${financial.vcUserName}"/>
					</td>
				</tr>
				<tr>
					<td>等级：</td>
					<td>
						<c:if test="${financial.nLevel==null}">
							<select name="nLevel" id="sel2">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
								<option value="60">60</option>
							</select>
						</c:if>
						<c:if test="${financial.nLevel==10}">
							<select name="nLevel" id="sel2">
								<option value="10" selected="selected">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
								<option value="60">60</option>
							</select>
						</c:if>
						<c:if test="${financial.nLevel==20}">
							<select name="nLevel" id="sel2">
								<option value="10">10</option>
								<option value="20" selected="selected">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
								<option value="60">60</option>
							</select>
						</c:if>
						<c:if test="${financial.nLevel==30}">
							<select name="nLevel" id="sel2">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30" selected="selected">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
								<option value="60">60</option>
							</select>
						</c:if>
						<c:if test="${financial.nLevel==40}">
							<select name="nLevel" id="sel2">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40" selected="selected">40</option>
								<option value="50">50</option>
								<option value="60">60</option>
							</select>
						</c:if>
						<c:if test="${financial.nLevel==50}">
							<select name="nLevel" id="sel2">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50" selected="selected">50</option>
								<option value="60">60</option>
							</select>
						</c:if>
						<c:if test="${financial.nLevel==60}">
							<select name="nLevel" id="sel2">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
								<option value="60" selected="selected">60</option>
							</select>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="提交" onclick="submitForm()"
					 style="background:#ff6600;width: 100px;align:center"/></td>
				</tr>
			</table>
			<input type="hidden" name="lineid" id="lineid" value="${financial.lineid}"/>
			<input type="hidden" name="nEnable" id="nEnable" value="${financial.nEnable}"/>
		</form>	
	</div>	
  </body>
</html>
