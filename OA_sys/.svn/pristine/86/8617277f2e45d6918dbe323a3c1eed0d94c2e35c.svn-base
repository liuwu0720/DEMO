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
    
    <title>档案修改信息</title>
    
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
		 msg='确认要删除该条档案审批么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmployrecordCheckServlet/del?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body>
<div class="rightsider" style="width: 1600px;">

<div class="info_tabs clearfix">
  
 
  <a href="EmployholidayServlet/getAllEhd">请假管理</a> 
  <a href="EmployoutServlet/getAllEmo" >外出管理</a> 
  <a href="EmployovertimeServlet/getEhdByIscheck?ischeck=0"  >加班管理</a> 
   <a href="EmployattendanceServlet/getEhdByIscheck?ischeck=0" >考勤管理</a> 
   <a href="EmploytripServlet/getEhdByIscheck?ischeck=0"  >出差管理</a> 
     <a href="EmployrecordCheckServlet/getEhdByIscheck?ischeck=0"  class="hover">档案管理</a> 
   
</div>
  <div class="htsp">

	 <span><a href="EmployrecordCheckServlet/getEhdByIscheck?ischeck=0" >未审批</a> <a href="EmployrecordCheckServlet/getEhdByIscheck?ischeck=1">未通过</a>  <a href="EmployrecordCheckServlet/getEhdByIscheck?ischeck=2">已通过</a></span>
     

  </div>

<table width="100%" style="overflow: auto;"  class="gridtable">
  	<tr>
  		 
	  	 <th style="width: 50px;" >姓名</th> 
	   	 <th style="width: 100px;">原公司</th>    
	     <th style="width: 100px;">原部门</th>      
	     <th style="width: 100px;">原岗位</th>
	     <th style="width: 100px;">原办公公司</th>
	     <th style="width: 150px;">原办公地点</th>
	     <th style="width: 100px;">公司</th>
	     <th style="width: 100px;">部门</th>
	     <th style="width: 100px;">岗位</th>
	     <th style="width: 100px;">办公公司</th>
	     <th style="width: 100px;">办公地点</th>
	     <th style="width: 100px;">状态</th>
	     
     
    	<th class="btn_turn" width="100px;"><span>${page }/${pages }页</span><div><a href="EmployrecordServlet/getEmrBook?page=${pages }"></a><a href="EmployrecordServlet/getEmrBook?page=1"></a></div></th>
    
  	</tr>
  	
  	 <c:forEach items="${emplist}" var="emr">
	   <tr>
	   
		   	 <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[1] }</td> 
		   	 <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[2] }</td> 
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[3] }</td>      
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[4] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[5] }</td> 
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[6] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[7] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[8] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[9] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[10] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[11] } </td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">
			      <c:forEach items="${stamap}" var="sta">
	     				<c:if test="${emr[12]==sta.key}">
	     					<span class="unpassing">${sta.value }</span>
	     				</c:if>
	     		</c:forEach>
		      </td>
		
		    <td style="border:0px solid #999999;border-bottom:1px solid #999999;">
		    <a href="${pageContext.request.contextPath }/EmployrecordCheckServlet/getEhdByid?id=${emr[0] }"></i>查看</a>
		    <a href="${pageContext.request.contextPath }/EmployrecordCheckServlet/getUpdatePage?id=${emr[0] }"></i>审批</a>
		    <a href="javascript:delete_add(${emr[0] })"></i>删除</a>
	    	</td>
	   </tr>
	</c:forEach>
  </table>
   
   




   
 
 <%@include file="../page.jsp"  %>
</div>

</body>
</html>
