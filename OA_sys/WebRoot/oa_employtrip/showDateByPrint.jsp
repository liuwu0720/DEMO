<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>My JSP 'getbyid.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
  </head>
  <style type="text/css"> 
.table 
{ 
width: 100%; 
padding: 0; 
margin: 0; 
} 
th { 
font: bold 12px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #4f6b72; 
border-right: 1px solid #C1DAD7; 
border-bottom: 1px solid #C1DAD7; 
border-top: 1px solid #C1DAD7; 
letter-spacing: 2px; 
text-transform: uppercase; 
text-align: left; 
padding: 6px 6px 6px 12px; 
background: #CAE8EA no-repeat; 
} 
td { 
border-right: 1px solid #C1DAD7; 
border-bottom: 1px solid #C1DAD7; 
background: #fff; 
font-size:14px; 
padding: 6px 6px 6px 12px; 
color: #4f6b72; 
} 
td.alt { 
background: #F5FAFA; 
color: #797268; 
} 
th.spec,td.spec { 
border-left: 1px solid #C1DAD7; 
} 
/*---------for IE 5.x bug*/ 
html>body td{ font-size:14px;} 
tr.select th,tr.select td 
{ 
background-color:#CAE8EA; 
color: #797268; 
} 
</style> 
  

  <script type="text/javascript">
  	function preview()
  	{
  		bdhtml=window.document.body.innerHTML; 
  		sprnstr="<!--startprint-->"; 
  		eprnstr="<!--endprint-->"; 
  		prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); 
  		prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); 
  		window.document.body.innerHTML=prnhtml; 
  		window.print(); 
  	}
  </script>
  <body>
  
  <div class="rightsider">

<div class="info_tabs clearfix">
 <a href="" class="hover">出差信息</a> 
</div>

<dl class="notice clearfix">
      
  <dd>详细信息 </dd>   
             
</dl>
	<!--startprint--> 
    	<table class="table">
    		<tr>
    			<td class="spec" width="60px;">用户姓名:</td>
    			<td class="spec" width="200px;">${ehd.opuser.employrecord.employname}</td>
    			<td class="spec" width="100px;">部门:</td>
    			<td class="spec" width="200px;">${ehd.dept.deptname}</td>
    		</tr>
    		<tr >
    			<td class="spec">出差地址:</td>
    			<td>${ehd.address}</td>
    			<td>乘坐工具:</td>
    			<td>${ehd.employtripTool.toolname}</td>
    		</tr>
    		<tr>
    			<td>出差事由:</td>
    			<td>${ehd.reason}</td>
    			<td>备注:</td>
    			<td>${ehd.remarks}</td>
    		</tr>
    		<tr>
    			<td>开始时间:</td>
    			<td>${fn:substring(ehd.date1 ,0,19)  }</td>
    			<td>结束时间:</td>
    			<td>${fn:substring(ehd.date2 ,0,19)  }</td>
    		</tr>
    		<tr>
    			<td>申请日期:</td>
    			<td>${fn:substring(ehd.currdate ,0,10)  }</td>
    			<td>审核状态:</td>
    			<td>
    				<c:forEach items="${stamap}" var="sta">
			     		<c:if test="${ehd.ischeck==sta.key}">
			     			${sta.value }
			     		</c:if>
			     	</c:forEach>
    			</td>
    		</tr>
    		<tr>
    			<td>审核用户:</td>
    			<td>
    				<c:choose>
						<c:when test="${ehd.actualuser != null }">
							${ehd.actualuser.employrecord.employname }
						</c:when>
						<c:otherwise>
							${ehd.checkuser.employrecord.employname}
						</c:otherwise>
					</c:choose>
    			</td>
    			<td>审批备注:</td>
    			<td>${ehd.checkremarks }</td>
    		</tr>
    	</table>
	   <!--endprint-->
		 <button type="submit" style="margin-left: 10px;height: 30px;width: 60px" class="tj add_btn" onclick="preview();">打印</button>
		<button type="button" style="margin-left: 10px;height: 30px;width: 60px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
</div>
  </body>
</html>
