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
    
    <title>My JSP 'getbyid.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<style type="text/css">
		td{
			width: 120px;
		
		}
	</style>
  </head>
  
  <body>
  <div class="rightsider">

<div class="info_tabs clearfix">
 <a href="" class="hover">加班审批</a> 
</div>

<dl class="notice clearfix">
      
  <dd>详细信息 </dd>   
             
</dl>
    	<form action="EmployrecruitmentServlet/updateEhd" method="post">
	    	
	<table>
	<tr>
		<td>
			<span>* 申请职位：</span>
		</td>
		<td> 
			${emp.position.positionname}
		</td>
		<td>
			<span>* 人数：</span>
		</td>
		<td> <input type="text" value="${emp.peopleNumber}" id="numbers" name="numbers" size="10"></td>
	</tr>
	<tr>
		<td><span>* 申请理由：</span></td>
			<td>
				${emp.employrecruitmentType.typename}
			</td>
		<td>
			<span>* 到职时间：</span>
		</td>
		<td>${fn:substring(emp.checktime,0,10)}</td>
	</tr>
		<tr>
			
		</tr>
	<tr>
		<td>
			<span>* 性别：</span>
		</td>
		<td> 
			<c:if test="${emp.sex==1}">
				男
			</c:if>
			<c:if test="${emp.sex==2}">
				女
			</c:if>
		<td>
			<span>* 婚否：</span>
		</td>
		<td> 
				<c:forEach items="${maritalmap}" var="sta">
			     		<c:if test="${emp.marital==sta.key}">
			     			<span class="unpassing">${sta.value }</span>
			     		</c:if>
			     </c:forEach>
		<td>
			<span>* 年龄：</span>
		</td>
		<td>
			 <c:forEach items="${agemap}" var="sta">
			     		<c:if test="${emp.agerange==sta.key}">
			     			<span class="unpassing">${sta.value }</span>
			     		</c:if>
			     </c:forEach>
	</tr>
	<tr>
		<td>
			<span>* 学历：</span>
		</td>
		<td> 
			 ${emp.educationlevel.levelname}
		<td>
			<span>* 外语：</span>
		</td>
		<td> ${emp.englevel}</td><td>
			<span>* 工作年限：</span>
		</td>
		<td> 
			 <c:forEach items="${workmap}" var="sta">
			     		<c:if test="${emp.workyear==sta.key}">
			     			<span class="unpassing">${sta.value }</span>
			     		</c:if>
			     </c:forEach>
	</tr>
	<tr>
		<td>
			<span>* 专业：</span>
			
		</td>
		<td> ${emp.professional}</td>
	</tr>
	<tr><td>* 专长/技能</td><td>${emp.expertise}
	</td>
	</tr><br>
	<tr><td>* 工作内容</td><td>${emp.workcontents}</td></tr>
	
</table>
			<br>
			<ul class="qy_info">
			<li><span><font color="red">* 进行审核：</font></span>
					<c:choose>
						<c:when test="${emp.ischeck==0}">
							<select name="ischeck" id="ischeck">
							<option value="2">同意</option>
							<option value="1">不同意</option>
							</select>
						</c:when>
						<c:otherwise>
							<script type="text/javascript">
								$(document).ready(function(){
									$("#sub").attr("disabled",true);
									});
							</script>	
							<font color="red" style="font-size: 15">已审批</font>
						</c:otherwise>
					</c:choose>
					
			</li>
			
			<li><span>* 审核备注：</span><br>
				<textarea style="width: 250;margin-left: 125px" name="checkremarks" id="checkremarks">${emp.checkremarks }
				</textarea>
			</li>
			
			<br>
		</ul>
		<input type="hidden" id="id" name="id" value="${emp.lineid }">
		<button type="button" id="sub" style="margin-left: 10px;height: 40px" class="tj add_btn" onclick="form.submit()">提交</button>
		<button type="button" style="margin-left: 10px;height: 40px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		</form>
</div>
   
  </body>
</html>
