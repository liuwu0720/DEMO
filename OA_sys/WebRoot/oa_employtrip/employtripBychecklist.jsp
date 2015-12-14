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

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	var id = "${id}";
	if(null!=id  && ""!=id){
		
		var temp = $("#"+id);
		temp.siblings().removeAttr("class");
		temp.attr("class","hover");
	}
	
	var freshenParam = "${freshenParam}";
	if(freshenParam!=null)
	{
		window.parent.frames['leftFrame'].fun();
	}
	
});
		function delete_add(uid)
		{
		 msg='确认要删除该条请假条么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmploytripServlet/del?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body>
<div class="rightsider" style="width: 1200px;">

<div class="info_tabs clearfix">
  
 
   <%@include file="../employManagerutil.jsp"  %>
    
</div>
  <div class="htsp">

	 <span><a href="EmploytripServlet/getEhdByIscheck?ischeck=0" >未审批</a> <a href="EmploytripServlet/getEhdByIscheck?ischeck=1">未通过</a>  <a href="EmploytripServlet/getEhdByIscheck?ischeck=2">已通过</a></span>
     

  </div>

 <div class="htban_info_cols clearfix">
   
   <ul>
   
     <li class="date1" style="">编号</li> 
     <li class="date1" style="">姓名 </li>   
     <li class="date2" style="">部门</li> 
     <li class="date2" style="">地址</li> 
     <li class="date2" style="">原因</li>
     <li class="date2" style="">备注</li>
     <li class="date2" style="">开始时间</li>
     <li class="date2" style="">结束时间</li>
     <li class="date2" style="">状态</li>  
     <li class="date2" style="width: 150px;">操作</li>  
     
     
     <li class="date2"></li>
    
   </ul>
  
   </div>


   <c:forEach items="${emplist}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
   
     <li class="date1" style="">${ehd[1] }</li>      
     <li class="date1" style="">${ehd[2]} </li>   
     <li class="date2" style="margin-left: 10px;">${ehd[3]}</li> 
     <li class="date2" style="margin-left: 20px;">${ehd[4] }</li>
     <li class="date2" style="">${ehd[5] }</li>
     <li class="date2" style="">${ehd[6] }</li>
     <li class="date2" style="margin-left: 20px;">${fn:substring(ehd[7] ,0,10) }</li>
     <li class="date2" style="">${fn:substring(ehd[8] ,0,10) }</li>
     <li class="date2" style="margin-left: 10px;">
     		<c:forEach items="${stamap}" var="sta">
     		<c:if test="${ehd[9]==sta.key}">
     			<span class="unpassing">${sta.value }</span>
     		</c:if>
     	</c:forEach>
     </li> 
      <li class="date2" style="margin-top: 5px;width: 150px;">
     
     	<a style="margin-left: 20px;" href="javascript:delete_add(${ehd[0] })"> 删除</a>
    	<a style="margin-left: 10px;" href="${pageContext.request.contextPath }/EmploytripServlet/getUpdatePage?id=${ehd[0] }"> 审批</a>
    	<a style="margin-left: 10px;" href="${pageContext.request.contextPath }/EmploytripServlet/getEhdByid?id=${ehd[0] }"> 查看</a>
     
     </li>
     
     </ul>
        </c:forEach>
 
 <%@include file="../page.jsp"  %>
</div>

</body>
</html>
