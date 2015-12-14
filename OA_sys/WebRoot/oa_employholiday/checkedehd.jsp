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
		function delete_add(uid)
		{
		 msg='确认要删除该记录吗？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmployholidayServlet/delEhrByGrId?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body style="overflow: auto;overflow-y:hidden;overflow-x:scroll;">
<div class="rightsider"  style="width: 1000px;" >

<div class="info_tabs clearfix">
  

<%@include file="../employutil.jsp"  %>
  
</div>
  <div class="htsp">

	 <span><a href="oa_employholiday/addehd.jsp" >新建请假登记</a> <a href="EmployholidayServlet/getEhdByChecked?id=a1" class="hover">请假历史记录</a> </span>
     

  </div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   
     <li class="date1" >用户编号</li>      
     <li class="date1" >档案编号 </li>  
     <li class="date2" >请假开始时间 </li>   
     <li class="date2" >请假结束时间</li> 
     <li class="date1" >天数</li> 
     <li class="date2" >请假原因</li>
     <li class="date1" >审批人</li>
     <li class="date2" >审批状态</li>  
     <li class="date2" >审批备注</li>  
     <li class="date2" >操作</li>  
     
     

   </ul>
  
   </div>


   <c:forEach items="${ehd}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
   
     <li class="date1"  >${ehd.opuser.userno }</li>      
     <li class="date1"  >${ehd.recordid } </li>  
     <li class="date2"  >${fn:substring(ehd.date1 ,0,19)  } </li>   
     <li class="date2"  >${fn:substring(ehd.date2 ,0,19)  }</li> 
     <li class="date1" style="margin-left: 30px;" >${ehd.days } </li> 
     <li class="date2"  >${ehd.reason }</li>
     <li class="date2"  >
     		<c:choose>
     			<c:when test="${ehd.actualuser==null }">
     				${ehd.checkuser.employrecord.employname }
     			</c:when>
     			<c:otherwise>
     				${ehd.actualuser.employrecord.employname }
     			</c:otherwise>
     		</c:choose>
     </li>
     <li class="date2"  >
     	<c:forEach items="${stamap}" var="sta">
     		<c:if test="${ehd.ischeck==sta.key}">
     			<span class="unpassing">${sta.value }</span>
     		</c:if>
     	</c:forEach>
     </li> 
	<li class="date2" >${ehd.checkremarks }</li>
   
  <li class="date2" style="margin-top: 1%;margin-left: 5%;">
   	<c:if test="${ehd.ischeck<2 }">
   		<a href="javascript:delete_add(${ehd.lineid })"><i class="icon_del" style="margin-top: 25px;margin-left: 50px;"></i></a>
     
   	</c:if>
     	
    </li>
     
     </ul>
        </c:forEach>
        
 <%@include file="../page.jsp"  %>
</div>

</body>
</html>
