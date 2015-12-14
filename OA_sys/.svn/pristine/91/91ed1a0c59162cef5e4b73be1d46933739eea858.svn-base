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
		 msg='确认要删除该申请么？';
		 if(window.confirm(msg))
		 {
		  URL="${pageContext.request.contextPath }/EmployrecruitmentServlet/delEhrByGrId?id="+uid;
		  window.location=URL;
		 }
		}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
<a href="EmployrecruitmentServlet/getEhdByUncheck">招聘申请信息</a>
  
</div>
  <div class="htsp">

	 <span><a href="EmployrecruitmentServlet/addbefore" >招聘申请</a> <a href="EmployrecruitmentServlet/getEhdByChecked">招聘历史记录</a> </span>
     

  </div>

   <div class="usr_info_cols clearfix">
   
   <ul>
   
     <li class="ht_num" style="width: 6%">用户编号</li>      
     <li class="dealer" style="width: 10%">申请人 </li>     
     <li class="dealer" style="width: 10%">申请部门 </li>   
     <li class="price" style="width: 12%">申请岗位</li>    
     <li class="price" style="width: 6%">人数</li> 
     <li class="date" style="width: 10%">申请理由</li> 
     <li class="date" style="width: 10%">工作经验</li> 
     <li class="date" style="width: 10%">审批状态</li>
     <li class="date" style="width: 10%">审批备注</li>
      <li class="date" style="width: 10%">操作</li>
   </ul>
  
   </div>


   <c:forEach items="${emplist}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
   
     <li class="ht_num" style="width: 6%">${ehd.opuser.userno }</li>      
     <li class="ht_name" style="width: 10%">${ehd.employrecord.employname} </li>  
     <li class="ht_name" style="width: 10%">${ehd.dept.deptname} </li>  
     <li class="dealer" style="width: 12%">${ehd.position.positionname} </li>   
     <li class="price" style="width: 6%">${ehd.peopleNumber}</li> 
     <li class="price" style="width: 10%">${ehd.employrecruitmentType.typename}</li>
      
     <li class="price" style="width: 10%">
     	<c:forEach items="${workmap}" var="sta">
     		<c:if test="${ehd.workyear==sta.key}">
     			<span class="unpassing">${sta.value }</span>
     		</c:if>
     	</c:forEach>
     	
     </li> 

     <li class="pass" style="width: 10%">
     	<c:forEach items="${stamap}" var="sta">
     		<c:if test="${ehd.ischeck==sta.key}">
     			<span class="unpassing">${sta.value }</span>
     		</c:if>
     	</c:forEach>
     	
     </li> 
     <li class="price" style="width: 10%">${ehd.checkremarks}</li>
      
      <li class="pass" style="width: 10%" >
     <a href="javascript:delete_add(${ehd.lineid })"><i class="icon_del" style="margin-top: 45px"></i>删除</a>
     
    </li>
  
     
     </ul>
        </c:forEach>
 
</div>

</body>
</html>
