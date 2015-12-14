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
		 msg='确认要删除该条请假条么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmployoutServlet/delEmoByGrid?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body>
<div class="rightsider" style="width: 1200px;">

<div class="info_tabs clearfix">
  
  
<%@include file="../employutil.jsp"  %>
  
</div>
  <div class="htsp">

	 <span><a href="oa_employout/addemo.jsp?id=a2" >新建外出登记</a> <a href="EmployoutServlet/getEmoByChecked?id=a2" class="hover">外出历史记录</a> </span>
     

  </div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   
     <li class="date1" style="">用户编号</li>      
     <li class="date1" style="">档案编号 </li>  
     <li class="date2" style="">外出开始时间 </li>   
     <li class="date2" style="">外出结束时间</li> 
     <li class="date2" style="">请假原因</li>
     <li class="date2" style="">审批人</li>
     <li class="date2" style="">审批状态</li> 
      <li class="date2" style="">审核说明</li> 
      <li class="date2" style="">操作</li> 
		
   </ul>
  
   </div>


   <c:forEach items="${emo}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
   
     <li class="date1" style="">${ehd.opuser.userno }</li>      
     <li class="date1" style="">${ehd.recordid } </li>  
     <li class="date2" style="">${fn:substring(ehd.date1 ,0,10)  } </li>   
     <li class="date2" style="">${fn:substring(ehd.date2 ,0,10)  }</li> 
     <li class="date2" style="">${ehd.reason }</li>
     <li class="date2" style="margin-left: 40px;">
     		<c:choose>
     			<c:when test="${ehd.actualuser==null }">
     				${ehd.checkuser.employrecord.employname }
     			</c:when>
     			<c:otherwise>
     				${ehd.actualuser.employrecord.employname }
     			</c:otherwise>
     		</c:choose>
     
     </li>
     <li class="date2" style="">
     	<c:forEach items="${stamap}" var="sta">
     		<c:if test="${ehd.ischeck==sta.key}">
     			<span class="unpassing">${sta.value }</span>
     		</c:if>
     	</c:forEach>
     </li> 

    <li class="date2" style="">${ehd.checkremarks }</li>
  
    <li class="date2"  style="margin-left: 10px;text-align: right;" >
   		<c:if test="${ehd.ischeck<2 }">
   			<a href="javascript:delete_add(${ehd.lineid })"><i class="icon_del" style="margin-top: 20px;margin-left: 50px;"></i></a>
     
   		</c:if>
     	
    </li>
     </ul>
        </c:forEach>
        
  <%@include file="../page.jsp"  %>
</div>

</body>
</html>
