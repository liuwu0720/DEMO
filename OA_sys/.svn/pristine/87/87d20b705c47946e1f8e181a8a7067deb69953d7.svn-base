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
    
    <title>员工档案信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./css/style.css" />

<script type="text/javascript">
		function delete_add(uid)
		{
		 msg='确认要删除该条请假条么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmployoutServlet/del?id="+uid;
		  window.location=URL;
		 }
		}
		function login()
		{
			if(document.getElementById("pp").value!=""){
 			  if(isNaN(document.getElementById("pp").value)){
 			  	alert("请输入正确的页码！");
 			  	document.getElementById("pp").focus();
 			  	return false;
 			  }
			}
		}
</script>

</head>
<body>
<div class="rightsider" style="width: 1000px;">

<div class="info_tabs clearfix">

 	<a href="EmployholidayServlet/getAllEhd">请假管理</a> 
   <a href="EmployoutServlet/getAllEmo" class="hover">外出管理</a> 
  
  <a href="EmployovertimeServlet/getEhdByIscheck?ischeck=0" >加班管理</a>
   <a href="EmployattendanceServlet/getEhdByIscheck?ischeck=0" >考勤管理</a> 
    <a href="EmploytripServlet/getEhdByIscheck?ischeck=0" >出差管理</a> 
  <a href="EmployrecordCheckServlet/getEhdByIscheck?ischeck=0"  >档案管理</a> 
  
 

</div>
  <div class="htsp">

	 <span><a href="EmployoutServlet/getEmoByIscheck?ischeck=0" >未审批</a> <a href="EmployoutServlet/getEmoByIscheck?ischeck=1">未通过</a>  <a href="EmployoutServlet/getEmoByIscheck?ischeck=2">已通过</a></span>
     

  </div>

 <div class="htban_info_cols clearfix">
   
   <ul>
   
   	 <li class="date1" style="">编号</li> 
     <li class="date1" style="">姓名 </li>   
     <li class="date2" style="">部门</li> 
     <li class="date2" style="">原因</li>
     <li class="date2" style="">开始时间</li>
     <li class="date2" style="">结束时间</li>  
     <li class="date2" style="">状态</li>  
     <li class="date2" style="">操作</li>  
     
     
     
   </ul>
  
   </div>


   <c:forEach items="${emplist}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
    
     
     <li class="date1" style="">${ehd[1] }</li>      
     <li class="date1" style="margin-left: 10px;">${ehd[2]} </li>   
     <li class="date2" style="margin-left: 20px;">${ehd[3]}</li> 
     <li class="date2" style="">${ehd[4] }</li>
     <li class="date2" style="">${fn:substring(ehd[5] ,0,10) }</li>
     <li class="date2" style="margin-left: 10px;">${fn:substring(ehd[6] ,0,10) }</li>
     <li class="date2" style="margin-left: 20px;">
     	<c:if test="${ehd[7]==0 }"><span class="unpassing">未审核</span></c:if>
     	<c:if test="${ehd[7]==1 }"><span class="unpassed">未通过</span></c:if>
     	<c:if test="${ehd[7]==2 }"><span class="passed">已通过</span></c:if>
     </li> 
      <li class="date2" style="margin-top: 1%;margin-left: 5%;width: 150px;">
     
    	 <a href="javascript:delete_add(${ehd[0] })"><i class="icon_del" style="margin-top: 42px;"></i>删除</a> 
    	<a href="${pageContext.request.contextPath }/EmployoutServlet/getUpdatePage?id=${ehd[0] }"><i class="icon_corr"></i>审批</a>
    	<a href="${pageContext.request.contextPath }/EmployoutServlet/getEmoByid?id=${ehd[0] }"><i class="icon_read"></i>查看</a>
     
     </li>
     
     </ul>
        </c:forEach>
 
<%@include file="../page.jsp"  %>
</div>

</body>
</html>
