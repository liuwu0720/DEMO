<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>公共通讯录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
	function delete_add(uid)
		{
		 msg='确认要删除该信息么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmpAddressListServlet/del?id="+uid;
		  window.location=URL;
		 }
		}
		
		$(document).ready(function(){
		$("#myform").submit(function(){
			/* if($.trim($("#employname").val())==""){
				alert("条件不能为空！");
				$("#employname").focus();
				return false;
			} */
			return true;
		});
	});
</script>

</head>
<body>
<div class="rightsider" style="width: 1100px;">

<div class="info_tabs clearfix">
  
 
  <a href="EmpAddressListServlet/getAllEmpAddressList?op=1" class="hover">公共通讯录管理</a> 
  
  <a href="EmpAddressListServlet/addbefore" class="usr_add"><i></i>添加</a>
  
</div>
<div class="usr_info_t"><h3>公共通讯录信息:</h3>  <div class="search_box clearfix">
<form action="EmpAddressListServlet/getInfoByName" method="post" name="myform" id="myform">

	<button type="submit">搜索<i></i></button><input name="employname" id="employname" type="text" />

	<select style="width: 150px;margin-top: 3px;" id="deptid" name="deptid" >
						<option value="0">---未选择---</option>
						
						<c:forEach items="${deptlist}" var="dept">
							<option value="${dept.lineid}">${dept.deptname}</option>	
						</c:forEach>
	</select>
</form></div></div>

   <div class="usr_info_cols clearfix">
   
   <ul>
     <li class="date1" >名称</li>      
     <li class="date1" style="">公司</li>     
     <li class="date3" style="">所属公司</li>  
     <li class="date2" style="">职位</li>    
     <li class="date2" style="">手机号</li> 
     <li class="date2" style="">电话</li>
     <li class="date2" style="">备注</li>  
     <li class="date2" style="">邮箱</li>  
     <li class="date2" style="width: 260px;"></li>    

   </ul>
   
   </div>


   <c:forEach items="${emplist}" var="emp">
   <ul class="usr_info_lit clearfix">
     <li class="date1" style="">${emp.name }</li>      
     <li class="date1" style="">${emp.companyName }</li> 
     <li class="date3" style="">${emp.dept.deptname }</li> 
     <li class="date2" style="">${emp.positionName }</li>  
     <li class="date2" style="">${emp.mobile }</li>  
     <li class="date2" style="">${emp.tel }</li>   
     <li class="date2" style="">${emp.remarks }</li>    
     <li class="date2" style="">${emp.email }</li>   

    <li class="date2" style="width: 260px;">
    	<c:if test="${emp.opuser.lineid == sessionScope.lineid}">
    		 <a href="javascript:delete_add(${emp.lineid })"><i class="icon_del"></i>删除</a>
    		  <a href="${pageContext.request.contextPath }/EmpAddressListServlet/getByupdate?id=${emp.lineid }"><i class="icon_corr"></i>修改</a>
    	</c:if>
	   
	    <a href="${pageContext.request.contextPath }/EmpAddressListServlet/getEmrById?id=${emp.lineid }"><i class="icon_read"></i>查看</a>
    </li>
   </ul>
</c:forEach>

<%@include file="../page.jsp"  %>
</div>
</body>
</html>
