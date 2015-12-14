<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
%>
<% 
String userno = String.valueOf( request.getSession(  ).getAttribute( "username") );
session.setAttribute( "username" , userno );
session.setMaxInactiveInterval( 50000 );

%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/stylesheets/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/stylesheets/buttons.css"
	type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/stylesheets/font-awesome.css"
	type="text/css" />

	
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/static/stylesheets/font-awesome-ie7.min.css"
	type="text/css" />	--%>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascripts/jquery-1.9.1.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascripts/bootstrap.min.js"></script>



<style type="text/css">
body {
	font-family: Microsoft YaHei,'宋体';
	
}
html, body {height: 100%;}
</style>
