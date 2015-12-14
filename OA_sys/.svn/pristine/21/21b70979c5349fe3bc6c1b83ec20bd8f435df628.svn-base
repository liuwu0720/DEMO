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
	<style type="text/css">
		td{
			width: 120px;
		
		}
	</style>
  </head>
  
  <body>
  <form action="EmployrecruitmentServlet/getUpdatePage" method="post">
  
  <div class="rightsider">

<div class="info_tabs clearfix">
 <a href="" class="hover">招聘信息</a> 
</div>

<dl class="notice clearfix">
      
  <dd>详细信息 </dd>   
             
</dl>
    	
	    	<table>
	<tr>
		<td>
			<span>* 申请职位：</span>
		</td>
		<td> 
			${emp.position.positionname}
		</td>
		<td  style="width: 150px;">
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
	<tr><td>专长/技能</td><td>${emp.expertise}
	</td>
	</tr><br>
	<tr><td>工作内容</td><td>${emp.workcontents}</td></tr>
	
</table>
	</br>
	<ul class="qy_info">
		<li><span><font style="font-size: 15">* 审核状态：</font></span>
					<c:forEach items="${stamap}" var="sta">
			     		<c:if test="${emp.ischeck==sta.key}">
			     			<font color="red" style="font-size: 15">${sta.value }</font>
			     		</c:if>
			     	</c:forEach>
			</li>
			
			<li><span>* 审核用户：</span>
				<c:choose>
					<c:when test="${emp.actualuser != null }">
						${emp.actualuser.employrecord.employname }
					</c:when>
					<c:otherwise>
						${emp.checkuser.employrecord.employname}
					</c:otherwise>
				</c:choose>
			</li>
			
			<li><span>* 审核说明：</span>${emp.checkremarks }</li>
			
		</ul>
	
		<input type="hidden" id="id" name="id" value="${emp.lineid }">
		 <button type="submit" style="margin-left: 10px;height: 30px;width: 60px" class="tj add_btn" onclick="">审批</button>
		<button type="button" style="margin-left: 10px;height: 30px;width: 60px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
</div>
   </form>
  </body>
</html>
