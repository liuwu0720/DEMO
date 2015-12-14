<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>考勤报表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>


<script type="text/javascript">
var checksql = "${returnPath}";
	function excelpoi()
	{
		
		$("#myform2").attr("action","CheckInfoController/getCheckInfoExcel");
		$("#myform2").submit();
	}
	function serach()
	{
		if($("#d12").val()!="")
		{
			if($("#d11").val()=="")
			{
				alert("请输入开始时间.");
				return false;
			}
		}
		
		
		$("#myform2").attr("action","CheckInfoController/getCheckInfoList");
		$("#myform2").submit();
	}
	function expaa()
	{
		alert("ssss");
		$.ajax({
			type:"post",
			url:"<%=basePath%>CheckInfoController/getCheckInfoExcel",
			data:"checksql="+checksql,
			dataType : "text",
			success:function(data)
			{
				alert("xxx");
			}	
		});
	}
</script>
</head>
<body>
<div class="rightsider" style="width: 1100px;">
<div class="info_tabs clearfix">
  <a  class="hover">考勤报表</a> 
</div>
<div class="usr_info_t">
<form action="CheckInfoController/getCheckInfoList" method="post" id="myform2">
			<table style="width: 100%;">
				<tr>
					<td width="300px;"> 
					
					</td>
					
					
					<td>
						<input name="begin" type="text" id="d11" value="<c:if test="${begin!='null'}">${begin}</c:if>"  style="width: 120px" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
						<img src="./My97DatePicker/skin/datePicker.gif" width="24" height="33" align="absmiddle" style="cursor:pointer" onClick="WdatePicker({el:'d11',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
					</td>
					<td>
						<input name="end" type="text" id="d12" value="<c:if test="${end!='null'}">${end}</c:if>"   style="width: 120px" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
						<img src="./My97DatePicker/skin/datePicker.gif" width="24" height="33" align="absmiddle" style="cursor:pointer" onClick="WdatePicker({el:'d12',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
					</td>
					<td>
						<select style="width: 120px;" id="deptid" name="deptid" >
						<option value="0">---未选择---</option>
						<c:forEach items="${deptlist}" var="dept">
							<c:choose>
								<c:when test="${dept.lineid == deptid}">
									<option selected="selected" value="${dept.lineid}">${dept.deptname}</option>	
								</c:when>
								<c:otherwise>
									<option value="${dept.lineid}">${dept.deptname}</option>	
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>
					</td>
					<td>
						<div style="width: 120px;" ><label style="float: left;vertical-align: text-bottom;">姓名:</label> <input type="text" style="width: 80px;float: left;" id="usname" value="<c:if test="${usname!='null'}">${usname}</c:if>"   name="usname"/></div>  
					</td>
					<td>
						<input type="button" style="width: 80px;" value=" 查询  " onClick="serach();" />
					</td>
					<td>
						<input type="button" style="width: 100px;" value=" 导出  " onClick="excelpoi();" />
					</td>
				</tr>
			</table>
			<!-- 
			<div class="search_box clearfix" style="width: 1200px;height:50px;border: 1px solid red;float:left;dispaly:block;">
				
				
			</div>
			 -->
			</form>
		</div>
		
 <div class="htban_info_cols clearfix">
   <ul>
	     <li class="date" style="width: 100px;text-align: center;">姓名</li> 
	     <li class="date" style="width: 100px;text-align: center;">请假/天 </li>   
	     <li class="date" style="width: 100px;text-align: center;">外出时间/小时</li> 
	     <li class="date" style="width: 100px;text-align: center;">托班时间/小时</li>
	     <li class="date" style="width: 100px;text-align: center;">周末加班/天</li>
	     <li class="date" style="width: 100px;text-align: center;">法假加班/天</li>
	     <li class="date" style="width: 100px;text-align: center;">出差时间/天</li>
	     <li class="date" style="width: 100px;text-align: center;">考勤次数</li>  
	     <li class="btn_turn"></li>
   </ul>
   </div>	
   <c:forEach items="${checkList}" var="check">
     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
	     <li class="date" style="width: 100px;text-align: center;">
		     ${check[1]}
	     </li>  
	     
	     <li class="date" style="width: 100px;text-align: center;">
	     		<c:choose>
		     	<c:when test="${check[2]>0 && check[2]!=null}" >
		     		<a href="CheckInfoController/getInfoByusid?usid=${check[0]}&params=holiday&begin=${begin}&end=${end}" >${check[2]}</a> 
		     	</c:when>
		     	<c:otherwise>
		     		&nbsp;
		     	</c:otherwise>
		     </c:choose>
	     </li>   
	     <li class="date" style="width: 100px;text-align: center;">
	     		<c:choose>
		     	<c:when test="${check[3]>0 && check[3]!=null}" >
		     		<a href="CheckInfoController/getInfoByusid?usid=${check[0]}&params=layout&begin=${begin}&end=${end}" >${check[3]}</a>
		     	</c:when>
		     	<c:otherwise>
		     		&nbsp;
		     	</c:otherwise>
		     </c:choose>
	     </li> 
	      <li class="date" style="width: 100px;text-align: center;margin-left: 20px;">
	     	<c:choose>
		     	<c:when test="${check[4]>0 && check[4]!=null}" >
		     		<a href="CheckInfoController/getInfoByusid?usid=${check[0]}&params=overtime&begin=${begin}&end=${end}" >${check[4]}</a>
		     	</c:when>
		     	<c:otherwise>
		     		&nbsp;
		     	</c:otherwise>
		     </c:choose>
	     </li>
	     <li class="date" style="width: 100px;text-align: center;margin-left: 20px;">
	     	<c:choose>
		     	<c:when test="${check[5]>0 && check[5]!=null}" >
		     		<a href="CheckInfoController/getInfoByusid?usid=${check[0]}&params=overtimeWeek&begin=${begin}&end=${end}" >${check[5]}</a>
		     	</c:when>
		     	<c:otherwise>
		     		&nbsp;
		     	</c:otherwise>
		     </c:choose>
	     </li> <li class="date" style="width: 100px;text-align: center;margin-left: 20px;">
	     	<c:choose>
		     	<c:when test="${check[6]>0 && check[6]!=null}" >
		     		<a href="CheckInfoController/getInfoByusid?usid=${check[0]}&params=overtimejiaWeek&begin=${begin}&end=${end}" >${check[6]}</a>
		     	</c:when>
		     	<c:otherwise>
		     		&nbsp;
		     	</c:otherwise>
		     </c:choose>
	     </li>
	     <li class="date" style="width: 100px;text-align: center;">
	     	<c:choose>
		     	<c:when test="${check[7]>0 && check[7]!=null}" >
		     		<a href="CheckInfoController/getInfoByusid?usid=${check[0]}&params=trip&begin=${begin}&end=${end}" >${check[7]}</a>
		     	</c:when>
		     	<c:otherwise>
		     		&nbsp;
		     	</c:otherwise>
		     </c:choose>
	     </li>
	     <li class="date" style="width: 100px;text-align: right;">
			<c:choose>
		     	<c:when test="${check[8]>0 && check[8]!=null}" >
		     		<a href="CheckInfoController/getInfoByusid?usid=${check[0]}&params=attendance&begin=${begin}&end=${end}" >${check[8]}</a>
		     	</c:when>
		     	<c:otherwise>
		     		&nbsp;
		     	</c:otherwise>
		     </c:choose>
		</li>
	     <li class="btn_turn"></li>
     </ul>
  </c:forEach>
 
 <%@include file="../page.jsp"  %>
</div>
<input type="hidden" value="${deptid}" name="deptid" id="deptid">
</body>
</html>
