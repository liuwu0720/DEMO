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
			//获取费用类型列表
			$('#sel3').change(function(){
				var typeId=$('#sel3 option:selected').val();
				//alert("typeId="+typeId);
				$.post('<%=basePath%>feeController/getTypes',
						{pId:typeId},
						function(types){
							$('#sel4').empty();
							$('#sel4').append('<option value="0">--请选择--</option>');
							for(var i=0;i<types.length;i++){
								var option='<option value="'+types[i].id+'">'+types[i].vcName+'</option>'
								$('#sel4').append(option);
							}
						},
						'json');
			});
			$('#sel4').change(getExistMonths);
			$('#sel2').change(getExistMonths);
		});
		//change事件
		function getExistMonths(){
			var iDept=$('#sel2 option:selected').val();
			if(iDept==0){
				//alert('部门为空');
				return;
			}
			var iCosttype=$('#sel4 option:selected').val();
			if(iCosttype==0){
				//alert('类型为空');
				return;
			}
			//alert(iDept+':'+iCosttype);
			$.post('<%=basePath%>feeBudgetController/getMonthsExist',
					{iDept:iDept,iCosttype:iCosttype},
					function(months){
						//alert(months);
						var checkboxs=$('input[name="nMonths"]');
						checkboxs.each(function(){
							$(this).removeAttr('disabled');
							$(this).removeAttr('checked');
						});
						for(var i=0;i<months.length;i++){
							checkboxs.each(function(){
								if($(this).val()==months[i]){
									$(this).attr('disabled','disabled');
									$(this).attr('checked','checked');
								}
							});
						}
						
					},
					'json');
		}
		//提交表单
		function submitForm(){
			var iCompany=$('#sel1').val();
			if(iCompany==0){
				alert('请选择公司');
				return;
			}
			var iDept=$('#sel2').val();
			if(iDept==0){
				alert('请选择部门');
			}
			var pcostType=$('#sel3').val();
			if(pcostType==0){
				alert('请选择类别');
				return;
			}
			var iCosttype=$('#sel4').val();
			if(iCosttype==0){
				alert('请选择类别');
				return;
			}
			//var nMonth=$('#nMonth').val();
			//if(nMonth==0){
				//alert('请选择月份');
				//return;
			//}
			var nTotalcost=$('#nTotalcost').val();
			var regex=/^[0-9]+.?[0-9]*$/;
			//alert('nTotalcost:'+regex.test(nTotalcost));
			if(!regex.test(nTotalcost)){
				alert('请填写一个数字');
				$('#nTotalcost').focus();
				return;
			}
			$('#myform').submit();
			
		}
	</script>
  </head>
  
  <body>
    <div class="rightsider">

		<div class="info_tabs clearfix">
			<a  class="hover">单项费用预算添加</a>
		</div>
		<dl class="notice clearfix">
			<dd>单项费用预算管理</dd>
		</dl>
		<form action="<%=basePath%>feeBudgetController/save" method="post" 
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
					<td rowspan="2">类别</td>
					<td >
						<select name="pcostType" id="sel3">
							<c:if test="${pTypeId==null}">
								<option value="0">--请选择--</option>
								<option value="1" >日常费用</option>
								<option value="2">特有费用</option>
							</c:if>
							<c:if test="${pTypeId==1}">
								<option value="1" selected="selected">日常费用</option>
							</c:if>
							<c:if test="${pTypeId==2}">
								<option value="2" selected="selected">特有费用</option>
							</c:if>
							<!--<c:if test="${pTypeId==1}">
								<option value="0">--请选择--</option>
								<option value="1" selected="selected">日常费用</option>
								<option value="2">特有费用</option>
							</c:if>
							<c:if test="${pTypeId==2}">
								<option value="0">--请选择--</option>
								<option value="1">日常费用</option>
								<option value="2" selected="selected">特有费用</option>
							</c:if>-->
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<select name="iCosttype" id="sel4">
							<c:if test="${budget.iCosttype==null}" var="yy">
								<option value="0">--请选择--</option>
							</c:if>
							<c:if test="${!yy}">
								<option value="${budget.iCosttype}">${budget.vcCosttype}</option>
							</c:if>
							<!--<c:forEach items="${subTypes}" var="type">
								<c:if test="${type.id==budget.iCosttype}" var="t">
									<option value="${type.id}" selected="selected">${type.vcName}</option>
								</c:if>
								<c:if test="${!t}">
									<option value="${type.id}">${type.vcName}</option>
								</c:if>
							</c:forEach>-->
						</select>
					</td>
				</tr>
				<tr>
					<td>月份:</td>
					<td>
						<c:if test="${budget.nMonth==null}" var="p">
							&nbsp;&nbsp;1月<input type="checkbox" name="nMonths" value="1"/>
							&nbsp;&nbsp;2月<input type="checkbox" name="nMonths" value="2"/>
							&nbsp;&nbsp;3月<input type="checkbox" name="nMonths" value="3"/><br/>
							&nbsp;&nbsp;4月<input type="checkbox" name="nMonths" value="4"/>
							&nbsp;&nbsp;5月<input type="checkbox" name="nMonths" value="5"/>
							&nbsp;&nbsp;6月<input type="checkbox" name="nMonths" value="6"/><br/>
							&nbsp;&nbsp;7月<input type="checkbox" name="nMonths" value="7"/>
							&nbsp;&nbsp;8月<input type="checkbox" name="nMonths" value="8"/>
							&nbsp;&nbsp;9月<input type="checkbox" name="nMonths" value="9"/><br/>
							10月<input type="checkbox" name="nMonths" value="10"/>
							11月<input type="checkbox" name="nMonths" value="11"/>
							12月<input type="checkbox" name="nMonths" value="12"/>
						</c:if>
						<c:if test="${!p}">
							<input name="nMonth" value="${budget.nMonth}" readonly="readonly"/>月
						</c:if>
					</td>
				</tr>
				<tr>
					<td>单项费用预算总额</td>
					<td>
						<input type="text" id="nTotalcost" name="nTotalcost" value="${budget.nTotalcost}"/>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="提交" onclick="submitForm()"
					 style="background:#ff6600;width: 100px;align:center"/></td>
				</tr>
			</table>
			<input type="hidden" name="lineid" value="${budget.lineid}"/>
			<input type="hidden" name="nEnable" value="${budget.nEnable}"/>
			<input type="hidden" name="dtAddstr" value="${budget.dtAdd}"/>
		</form>	
	</div>	
  </body>
</html>
