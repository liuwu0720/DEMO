<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="css/style.css" />
  </head>
  <body bgcolor="#E3E3E3">

    <div class="header" style="background-color:#E3E3E3;">

   <div class="toper_left" style="background-color:#E3E3E3;">
      
       <span class="logo" style="background-color:#E3E3E3;"><img src="css/logo0825.gif" width="337" style="" height="77" /></span>
   
   </div>

   
   <div class="toper_right" >
   
     
        
      <span class="logo"> <font color="white" >欢迎您!&nbsp;
    <%=session.getAttribute("uname") %><br/>现在是&nbsp;<span id="time"></span>
    <script>setInterval("time.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);</script>
    </font></span>
    	
       <span class="info">客服电话：0755-36885376 | 使用帮助 | 联系我们 </span>
       
   </div>

</div>
  </tr>
</table>
</body>
</html>
