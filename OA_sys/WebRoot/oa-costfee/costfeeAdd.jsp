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

  </head>
  
  <body>
    <div class="rightsider">

		<div class="info_tabs clearfix">
			<a  class="hover">添加</a>
		</div>
		<dl class="notice clearfix">
			<dd>资料</dd>
		</dl>
		<form action="<%=basePath%>costfeeController/save" method="post" enctype="multipart/form-data"
			id="myform">
			<table id="tab">
				<tr>
					<td colspan="2">
						<div style="float:left;margin: 5px 150px 0px 5px"><img src="<%=basePath%>css/unlcn.png"></div>
						<div><span style="font-family: 黑体;font-size: 20px;font-weight: bolder;float:left">中联物流（中国）有限公司<br/>费用申请表</span></div>
					</td>
				</tr>
				<tr>
					<td>费用类别</td>
					<td><input type="text" name="iCosttype" /></td>
				</tr>
				<tr>
					<td>费用金额</td>
					<td><input type="text" name="nCost" /></td>
				</tr>
				<tr>
					<td>申请备注</td>
					<td><input type="text" name="vcNote" /></td>
				</tr>
				<tr>
					<td colspan="4"><input type="submit" value="提交"
					 style="background:#ff6600;width: 100px;align:center"/></td>
				</tr>
			</table>
		</form>	
		
	</div>	
  </body>
</html>
