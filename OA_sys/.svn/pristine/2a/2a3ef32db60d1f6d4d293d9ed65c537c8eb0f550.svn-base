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
	
<!--[if IE 6]><script src="http://stjscn.s-msn.com/portal/wp/DD_belatedPNG.js" type="text/javascript"></script><![endif]-->
<!--[if lt IE 6]>
<link href="skins/ie.css" type="text/css" rel="stylesheet" />
<![endif]-->
<!--[if lt IE 7]>
<link href="skins/ie.css" type="text/css" rel="stylesheet" />
<![endif]-->
<!--[if lt IE 8]>
<link href="skins/ie.css" type="text/css" rel="stylesheet" />
<![endif]-->
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
		  URL="${pageContext.request.contextPath }/EmploytripServlet/delEhrByGrId?id="+uid;
		  window.location=URL;
		 }
		}
		function showDate(uid)
		{
		 
		  URL="${pageContext.request.contextPath }/EmploytripServlet/getEhdByid?id="+uid+"&paramType=show";
		  window.location=URL;
		 
		}
		function printDate(uid)
		{
		var openPdfPrint = window.open("<%=basePath%>oa_employtrip/PDA.jsp","","status=no,toolbar=no,menubar=no,location=no,left=260,top=60");
		 
		}
</script>

</head>
<body>
<div class="rightsider" style="width: 1100px;">

<div class="info_tabs clearfix">
  
<%@include file="../employutil.jsp"  %>
  
</div>
  <div class="htsp">

	 <span><a href="EmploytripServlet/addbefore?id=a5" >出差登记</a> <a href="EmploytripServlet/getEhdByChecked?id=a5">出差历史记录</a> </span>
     

  </div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   
     <li class="date1" style="">用户编号</li>      
     <li class="date1" style="">档案编号 </li>  
     <li class="date2" style="">出差开始时间 </li>   
     <li class="date2" style="">出差结束时间</li>    
     <li class="date2" style="">出差工具</li> 
     <li class="date2" style="">出差事由</li> 
     <li class="date2" style="">审批人</li> 
     <li class="date2" style="">备注</li>
     <li class="date2" style="">审批状态</li> 
     <li class="date3" style="">操作</li>  
     
     

   </ul>
  
   </div>


   <c:forEach items="${emplist}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left:20px;">
   
     <li class="date1" style="">${ehd.opuser.userno }</li>      
     <li class="date1" style="">${ehd.recordid } </li>  
     <li class="date2" style="margin-left: 10px;">${fn:substring(ehd.date1 ,0,19)  } </li>   
     <li class="date2" style="">${fn:substring(ehd.date2 ,0,19)  }</li> 
     <li class="date2" style="">${ehd.employtripTool.toolname }</li>
      
     <li class="date2" style="margin-left: 20px;">${ehd.reason }</li>
     <li class="date2" style="margin-left: 10px;">
     		<c:choose>
     			<c:when test="${ehd.actualuser==null }">
     				${ehd.checkuser.employrecord.employname }
     			</c:when>
     			<c:otherwise>
     				${ehd.actualuser.employrecord.employname }
     			</c:otherwise>
     		</c:choose>
     
     </li>
     <li class="date2" style="text-align: center;">${ehd.remarks }</li>
     <li class="date2" style="margin-left: 40px;">
     	<c:forEach items="${stamap}" var="sta">
     		<c:if test="${ehd.ischeck==sta.key}">
     			<span class="unpassing">${sta.value }</span>
     		</c:if>
     	</c:forEach>
     	
     </li> 

    
      <li class="date3" style="margin-left: 30px;" >
     	<a href="javascript:delete_add(${ehd.lineid })"><i class="icon_del" style="margin-top: 25px"></i><font size="4" >删除</font></a>
     	<span style="width: 50px;">&nbsp;</span>
     	<!-- <a href="javascript:printDate(${ehd.lineid })"><i class="icon_read" style="margin-top: 45px"></i> 打印 </a> -->
     	 <a href="${pageContext.request.contextPath }/EmploytripServlet/getPrintDateByID?id=${ehd.lineid }">  <font size="4" >导出</font>  </a> 
    </li>
  
     
     </ul>
        </c:forEach>
 
 <%@include file="../page.jsp"  %>
</div>

</body>
</html>
