<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%!	
	String getds(String str1,String str2)
	{
		System.out.print( str1+""+str2 );
		return "ss";
	}
%>


<!DOCTYPE HTML>
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>加班信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style.css" />
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
		  URL="${pageContext.request.contextPath }/EmployovertimeServlet/delEhrByGrId?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body style="overflow: auto;overflow-y:hidden;overflow-x:scroll;">
<div class="rightsider" style="width: 1500px;">

<div class="info_tabs clearfix">
  
<%@include file="../employutil.jsp"  %>
  
</div>
  <div class="htsp">

	 <span><a href="EmployovertimeServlet/addbefore?id=a3" >新建加班登记</a> <a href="EmployovertimeServlet/getEhdByChecked?id=a3">加班历史记录</a> </span>
     

  </div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   
     <li class="date1" style="">用户编号</li>      
     <li class="date1" style="">档案编号 </li>  
     <li class="date3" style="">加班开始时间 </li>   
     <li class="date3" style="">加班结束时间</li>  
     <li class="date1" style="">小时</li>  
     <li class="date1" style="">天数</li>    
     <li class="date2" style="">加班类型</li> 
     <li class="date2" style="">加班原因</li> 
     <li class="date2" style="">加班内容</li>
     <li class="date2" style="">审批人</li>
     <li class="date2" style="">审批状态</li> 
     <li class="date2" style="">审批备注</li> 
     <li class="date2" style="">操作</li>  
     
     

   </ul>
  
   </div>


   <c:forEach items="${emplist}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
   
     <li class="date1" style="">${ehd.opuser.userno }</li>      
     <li class="date1" style="">${ehd.recordid } </li>  
     <li class="date3" style="margin-left: 20px;">${fn:substring(ehd.date1 ,0,19)  } </li>   
     <li class="date3" style="margin-left: 20px;">${fn:substring(ehd.date2 ,0,19)  }</li>  
     <!-- 托班算小时   加班 法定  算天数    employovertimeType ==1  为托班-->
     <c:choose>
     	<c:when test="${ehd.employovertimeType.lineid == 1}">
     		 <li class="date1" style="margin-left: 0px;"> ${ehd.differdate }  </li> 
     	</c:when>
     	<c:otherwise>
     		 <li class="date1" style="margin-left: 0px;"> 0  </li> 
     	</c:otherwise>
     </c:choose>
    
     <c:choose>
     	<c:when test="${ehd.employovertimeType.lineid != 1}">
     		  <li class="date1" style="">${ehd.days }</li>
     	</c:when>
     	<c:otherwise>
     		 <li class="date1" style="margin-left: 0px;"> 0  </li> 
     	</c:otherwise>
     </c:choose>
      
     <li class="date2" style="">${ehd.employovertimeType.typename }</li>
      
     <li class="date2" style="">${ehd.reason }</li>
     <li class="date2" style="margin-left: 20px;">${ehd.contents }</li>
     <li class="date2" style="margin-left: 20px;">${ehd.checkuser.employrecord.employname }</li>
     <li class="date2" style="">
     	<c:forEach items="${stamap}" var="sta">
     		<c:if test="${ehd.ischeck==sta.key}">
     			<span class="unpassing">${sta.value }</span>
     		</c:if>
     	</c:forEach>
     	
     </li> 

     <li class="date2" style=" margin-left: 10px;" >${ehd.checkremarks }</li>
    
      <li class="date2"  style="margin-left: 10px;text-align: right;" >
     	<a href="javascript:delete_add(${ehd.lineid })"><i class="icon_del" style="margin-top: 25px;margin-left: 30px;"></i></a>
     
    </li>
  
     
     </ul>
        </c:forEach>
 
<%@include file="../page.jsp"  %>
</div>
</body>
</html>
