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
    <H2><EM>404 Error</EM>: 抱歉, 您所查找的页面不存在, 可能已被删除或您输错了网址!</H2>
    <img src="./Images/title.jpg"><br><br>
<A href="javascript:history.go(-1);">◂返回上一页</A> </P>
 </center>
<DL class=texts style="margin-left: 35%">
  <DT>没有发现你要找的页面, 经砖家仔细研究结果如下: 
  <DD>
  <UL>
    <LI>贵玉手输入地址时可能存在键入错误 
    <LI>小蜗牛把页面落家里忘记带了 
    <LI>电信网通那头接口生锈了 </LI>
	<LI>如果您对本站有任何疑问、建议，请联系管理员</LI>
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
    </SCRIPT></UL></DD></DL>
   
  </body>
</html>
