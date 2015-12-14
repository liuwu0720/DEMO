<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'error.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <center>
   <br>
    <H2><EM>500 Error</EM>：无法执行此命令</H2>
    <img src="./Images/error.gif"><br><br>
<A href="javascript:history.go(-1);">◂返回上一页</A> </P>
<h2>错误：
 <%
if(exception.getMessage().equals("Could not execute JDBC batch update; nested exception is org.hibernate.exception.ConstraintViolationException: Could not execute JDBC batch update"))
{
out.print("无法删除该信息，该信息存在子目录");
}else{
	out.print(exception.getMessage());
}
 %>
 </h2>
<br>

<P class="banner_city">页面将在<B class="chengse STYLE1" id=second>30</B>秒后自动跳转到上一页</P>
<SCRIPT type=text/javascript>
                var n = 30;
                var timer = setInterval(function() {
                    n--;
                    document.getElementById("second").innerHTML = n;
                    if (n == 0) {
                        clearInterval(timer);
                        window.location = "javascript:history.go(-1);";
                    }

                }, 1000);
    </SCRIPT>
    </center>
  </body>
</html>
