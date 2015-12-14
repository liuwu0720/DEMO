<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
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
<style type="text/css">
	
</style>
<script type="text/javascript">
		
		$(document).ready(function(){
		
			$("#myform2").submit(function()
			{
				var empname =$.trim($("#employname").val()); 
				var deptid = $("#deptid").val();
				if(empname ==""  && deptid == 0 )
				{
					alert("搜索姓名不能为空！");
					$("#employname").focus();
					return false;
				}else
				{
					return true;
				}
			
			});
			
			function delete_add(uid)
			{
				 msg='确认要删除该员工么？';
				 if(window.confirm(msg))
				 {
					  URL="${pageContext.request.contextPath }/EmployrecordServlet/del?id="+uid;
					  window.location=URL;
				 }
			}
			
			
	});
	
</script>

</head>
<body>
<div class="rightsider" style="width: 1200px;">

<div class="info_tabs clearfix">
  
 
  <a href="EmployrecordServlet/getEmrBook" class="hover">通讯录管理</a> 
  

  
</div>
		<div class="usr_info_t">
			<h3>职位信息: <label style="width: 50px;"></label>搜索条件：<font color="red"> <c:if test="${dept.deptname != 'null'}">${dept.deptname}</c:if>  &nbsp;<c:if test="${employname != 'null'}"> ${employname}</c:if> </font></h3>
			<div class="search_box clearfix">
				<form action="EmployrecordServlet/getEmrBook" method="post" id="myform2">
					
					<button type="submit">
						搜索<i></i>
					</button>
					
					
					<input name="employname" id="employname" type="text"  />
					
					<select style="width: 150px;margin-top: 3px;" id="deptid" name="deptid" >
						<option value="0">---未选择---</option>
						<c:forEach items="${deptlist}" var="dept">
							<option value="${dept.lineid}">${dept.deptname}</option>	
						</c:forEach>
					</select>
					
					<label>&nbsp; 姓名: </label>
				</form>
			</div>
		</div>

  <table  style="overflow: auto;"  class="gridtable">
  	<tr >
  		 
	  	 <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 50px;" >姓名</td> 
	   	 <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 150px;">所属公司</td>    
	     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 100px;">部门</td>      
	     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 100px;">岗位</td>
	     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 100px;">电话</td>
	     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 100px;">固定电话</td>
	     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 100px;">分机号</td>
	     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 100px;">直线号码</td>
	     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 100px;">其他方式</td>
	     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">邮箱</td>
     

     
    	<td class="btn_turn"><span>${page }/${pages }页</span><div><a href="EmployrecordServlet/getEmrBook?page=${pages }"></a><a href="EmployrecordServlet/getEmrBook?page=1"></a></div></td>
    
  	</tr>
  	
  	 <c:forEach items="${emr}" var="emr">
	   <tr>
		   	 <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[2] }</td> 
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;width: 150px;"   title="${emr[3] }">${fn:substring(emr[3], 0, fn:indexOf(emr[3], ">>"))}</td>      
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[4] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[5] }</td> 
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[6] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[7] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[8] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[9] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;">${emr[10] }</td>
		     <td style="border:0px solid #999999;border-bottom:1px solid #999999;"><a title="单击发送邮件" href="mailto:${emr[11] }">${emr[11] }</a> </td>
		 
		    <td style="border:0px solid #999999;border-bottom:1px solid #999999;">
		    <a href="${pageContext.request.contextPath }/EmployrecordServlet/getEmrBookById?id=${emr[0] }"></i>详情</a>
	    	</td>
	   </tr>
	</c:forEach>
  </table>
   
   

	<%@include file="../page.jsp"  %>
</div>
</body>
</html>
