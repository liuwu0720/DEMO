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
    
    <title>考勤异常信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="../css/style.css" />

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	
<script type="text/javascript">
$(document).ready(function(){
	
	var id = "${id}";
	if(null!=id  && ""!=id){
		
		var temp = $("#"+id);
		temp.siblings().removeAttr("class");
		temp.attr("class","hover");
	}
	
});
		function delete_add(uid)
		{
		 msg='确认要删除该条请假条么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmployattendanceServlet/delEhrByGrId?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body >
<div class="rightsider" style="width: 1000px;">

<div class="info_tabs clearfix">
  
<%@include file="../employutil.jsp"  %>
  
</div>
  <div class="htsp">

	 <span><a href="EmployattendanceServlet/addbefore?id=a4" >新建考勤登记</a> <a href="EmployattendanceServlet/getEhdByChecked?id=a4">考勤历史记录</a> </span>
     

  </div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   
     <li class="date1" style="">用户编号</li>      
     <li class="date1" style="">档案编号 </li>  
     <li class="date2" style="">考勤时间 </li>      
     <li class="date2" style="">考勤类型</li> 
     <li class="date2" style="">原因</li> 
     <li class="date2" style="">原因</li> 
     <li class="date2" style="">审批人</li> 
     <li class="date2" style="">审批备注</li> 
     <li class="date2" style="">操作</li>  
     
     

   </ul>
  
   </div>


   <c:forEach items="${emplist}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
   
     <li class="date1" style="">${ehd.opuser.userno }</li>      
     <li class="date1" style="">${ehd.recordid } </li>  
     <li class="date2" style="margin-left: 10px;">${fn:substring(ehd.date1 ,0,19)  } </li>   
     <li class="date2" style="margin-left: 20px;">${ehd.typename }</li>
      
     <li class="date2" style="margin-left: 20px;">${ehd.reason }</li>
     <li class="date2" style="">${ehd.checkuser.employrecord.employname }</li>
     <li class="date2" style="">
     	<c:forEach items="${stamap}" var="sta">
     		<c:if test="${ehd.ischeck==sta.key}">
     			<span class="unpassing">${sta.value }</span>
     		</c:if>
     	</c:forEach>
     	
     </li> 

    
     <li class="date2" style="">${ehd.checkremarks }</li>
      <li class="date2" style="" >
     <a href="javascript:delete_add(${ehd.lineid })"><i class="icon_del" style="margin-top: 25px"></i>删除</a>
     
    </li>
  
     
     </ul>
        </c:forEach>
        
  <%@include file="../page.jsp"  %>
</div>

</body>
</html>
