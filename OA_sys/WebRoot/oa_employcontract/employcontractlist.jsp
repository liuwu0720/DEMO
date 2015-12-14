<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>员工合同信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

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
		
	function delete_add(uid) {
		msg = '是否删除该合同信息?';
		if (window.confirm(msg)) {
			URL = "${pageContext.request.contextPath }/EmployovertimeServlet/del?id="
					+ uid;
			window.location = URL;
		}
	}

	function selectAll() {
		if ($("#selectall").attr('checked') == true) {
			$("[name='ichkbox']:checkbox").attr("checked", true);
		} else {
			$("[name='ichkbox']:checkbox").attr("checked", false);
		}
	}
	function getcontrat()
	{
		
	}
</script>

</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
</div>
  <div class="htsp" align="right">
  
 	<span><input type="button" align="left"  onclick="getcontrat();" value="未签订合同"  >  <input type="button" value="签订合同"  >
 	 <label style="width: 50px;"></label>
 	</span> 
  </div>

 <div class="htban_info_cols clearfix">
   
   <ul>
   	<li class="ht_num" style="width: 50px;"> <input type="checkbox" id="selectall" name="selectall"></li>
     <li class="ht_num" style="width: 100px;">合同编号</li> 
     <li class="dealer" style="width: 100px;">姓名 </li>   
     <li class="price" style="width: 100px;">部门</li> 
     <li class="date" style="width: 100px;">生效日期</li>
     <li class="date" style="width: 100px;">失效日期</li>
     <li class="pass" style="width: 50px;">年限</li>
     <li class="pass" style="width: 50px;">类型</li>  
     
   </ul>
  
   </div>


   <c:forEach items="${emplist}" var="ehd">

     <ul class="bafy_info_lit clearfix" style="margin-left: 1%">
	     <li class="ht_num" style="width: 50px;"> <input type="checkbox" id="ichkbox" name="ichkbox"></li>
	     <li class="ht_num" style="width: 100px;">合同编号</li> 
	     <li class="dealer" style="width: 100px;">姓名 </li>   
	     <li class="price" style="width: 100px;">部门</li> 
	     <li class="date" style="width: 100px;">生效日期</li>
	     <li class="date" style="width: 100px;">失效日期</li>
	     <li class="pass" style="width: 50px;">年限</li>
	     <li class="pass" style="width: 50px;">类型</li>  
	    
	      <li class="deals" style="margin-top: 1%;margin-left: 5%">
	     
	     	<a href="javascript:delete_add(${ehd[0] })"><i class="icon_del"></i>删除</a><br>
	    	<a href="${pageContext.request.contextPath }/EmployovertimeServlet/getEhdByid?id=${ehd[0] }"><i class="icon_read"></i>查看</a>
	     
	     </li>
	     
	     </ul>
        </c:forEach>
 
 <%@include file="../page.jsp"  %>
</div>

</body>
</html>
