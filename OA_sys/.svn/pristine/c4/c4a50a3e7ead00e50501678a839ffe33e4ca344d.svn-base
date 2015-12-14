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
    
    <title>员工请假未审批信息</title>
    
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
		  URL="${pageContext.request.contextPath }/EmployoutServlet/del&id="+uid;
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

 	
  <%@include file="../employManagerutil.jsp"  %>

</div>
  <div class="htsp">

	 <span><a href="EmployoutServlet/getEmoByIscheck?ischeck=0" >未审批</a> <a href="EmployoutServlet/getEmoByIscheck?ischeck=1">未通过</a>  <a href="EmployoutServlet/getEmoByIscheck?ischeck=2">已通过</a></span>
     

  </div>

 <div class="htban_info_cols clearfix">
   
   <ul>
   
     
     <li class="date1" style="">编号</li> 
     <li class="date1" style="">姓名 </li>   
     <li class="date2" style="">部门</li> 
     <li class="date2" style="text-align: center;">原因</li>
     <li class="date3" style="">开始时间</li>
     <li class="date3" style="">结束时间</li>  
     <li class="date2" style="">状态</li>  
     <li class="date2" style="">操作</li>  
     
    
   </ul>
  
   </div>


   <c:forEach items="${emplist}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
    
     <li class="date1" style="">${ehd[1] }</li>      
     <li class="date1" style="">${ehd[2]} </li>   
     <li class="date2" style="">${ehd[3]}</li> 
     <li class="date2" style="text-align: right;">${ehd[4] }</li>
     <li class="date3" style="margin-left: 20px;">${fn:substring(ehd[5] ,0,16) }</li>
     <li class="date3" style="margin-left: 20px;">${fn:substring(ehd[6] ,0,16) }</li>
     <li class="date2" style="">
     	<c:if test="${ehd[7]==0 }"><span class="unpassing">未审核</span></c:if>
     	<c:if test="${ehd[7]==1 }"><span class="unpassed">未通过</span></c:if>
     	<c:if test="${ehd[7]==2 }"><span class="passed">已通过</span></c:if>
     </li> 
      <li class="date2" style="margin-top: 5px;width: 160px;">
     
    	<a style="margin-left: 20px;" href="javascript:delete_add(${ehd[0] })"> 删除</a> 
    	<a style="margin-left: 20px;" href="${pageContext.request.contextPath }/EmployoutServlet/getUpdatePage?id=${ehd[0] }"> 审批</a> 
    	<a style="margin-left: 20px;" href="${pageContext.request.contextPath }/EmployoutServlet/getEmoByid?id=${ehd[0] }"> 查看</a>
     
     </li>
     
     </ul>
        </c:forEach>
 
<%@include file="../page.jsp"  %>
</div>

</body>
</html>
