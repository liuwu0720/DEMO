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
		$(document).ready(function(){
			//获取部门
			$('#sel1').change(function(){
				var pid=$('#sel1 option:selected').val();
				//alert('pid='+pid);
				$.post('<%=basePath%>feeBudgetController/getDeptByPid',
						{pid:pid},
						function(depts){
							$('#sel2').empty();
							$('#sel2').append('<option value="0">--请选择--</option>');
							for(var i=0;i<depts.length;i++){
								var option='<option value="'+depts[i][0]+'">'+depts[i][1]+'</option>';
								$('#sel2').append(option);
							}
						},
						'json');
			});
		});
		//提交表单
		function submitForm(){
			var iCompany=$('#sel1 option:selected').val();
			if(iCompany==0){
				alert('请选择公司');
				return;
			}
			var iDept=$('#sel2 option:selected').val();
			if(iDept==0){
				alert('请选择部门');
				return;
			}
			var file=$('#file').val();
			alert('file:'+file);
			if(!file){
				alert('请选择要上传的文件');
				return;
			}
			//if(!file.endsWith('.xls')){
			//	alert('文件格式需以".xls"结尾');
			//	return;
			//}
			var regex=/^.+\.xls$/;
			if(!regex.test(file)){
				alert('文件格式需以".xls"结尾');
				return;
			}
			$('#myform').submit();
		}
	</script>
  </head>
  
  <body>
    <div class="rightsider">

		<div class="info_tabs clearfix">
			<a  class="hover">单项费用预算批量添加</a>
		</div>
		<dl class="notice clearfix">
			<dd>单项费用预算管理</dd>
		</dl>
		<form action="<%=basePath%>feeBudgetController/addByExcel" method="post" enctype="multipart/form-data"
			id="myform">
			<table id="tab">
				<tr>
					<td>公司:</td>
					<td>
						<select name="iCompany" id="sel1">
							<c:if test="${budget.iCompany==null}" var="y">
								<option value="0">--请选择--</option>
								<c:forEach items="${companys}" var="company">
									<option value="${company.lineid}">${company.deptname}</option>
								</c:forEach>
							</c:if>
							<c:if test="${!y}">
								<option value="${comp.lineid}">${comp.deptname}</option>
							</c:if>
							<!--<c:forEach items="${companys}" var="company">
								<c:if test="${budget.iCompany==company.lineid}" var="isTrue">
									<option value="${company.lineid}" selected="selected">${company.deptname}</option>
								</c:if>
								<c:if test="${!isTrue}">
									<option value="${company.lineid}">${company.deptname}</option>
								</c:if>
							</c:forEach>-->
						</select>
					</td>
				<tr>
				</tr>
					<td>部门:</td>
					<td>
						<select name="iDept" id="sel2">
							<c:if test="${budget.iDept==null}" var="yes">
								<option value="0">--请选择--</option>
							</c:if>
							<c:if test="${!yes}">
								<option value="${budget.iDept}" selected="selected">${budget.vcDeptname}</option>
							</c:if>
							<!--<c:forEach items="${depts}" var="dept">
								<c:if test="${budget.iDept==dept[0]}" var="yes">
									<option value="${dept[0]}" selected="selected">${dept[1]}</option>
								</c:if>
								<c:if test="${!yes}">
									<option value="${dept[0]}" selected="selected">${dept[1]}</option>
								</c:if>
							</c:forEach>-->
						</select>
					</td>
				</tr>
				<tr>
					<td>EXCEL文件</td>
					<td><input type="file" name="file" id="file" style="width:150px"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="提交" onclick="submitForm()"
					 style="background:#ff6600;width: 100px;align:center"/></td>
				</tr>
			</table>
		</form>	
	</div>	
  </body>
</html>
