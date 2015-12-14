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
    
    <title>部门信息</title>
    
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
<script language="javascript" type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>

<script charset="utf-8" src="<%=path %>/kindeditor/kindeditor.js"></script> 

<script charset="utf-8" src="<%=path %>/kindeditor/lang/zh_CN.js"></script>
<script>  
//--------------------KindEditor
        var editor;  

        KindEditor.ready(function(K) {  

                editor = K.create('#editor_id',{
                
                resizeType:0
                });
               

        }); 
        var editor = K.create('textarea[name="content"]', options); 
        // 取得HTML内容
        html=editor.html();
        //同步数据后可以直接取得textarea的value 
		editor.sync();
		html = document.getElementById('editor_id').value; // 原生API
		html = K('#editor_id').val(); // KindEditor Node API
		html = $('#editor_id').val(); // jQuery 
		// 设置HTML内容
		editor.html('HTML内容');
		
//------------------------------
		function LoadWindow()
		{
		  URL="<%=basePath%>DeptServlet/getAllDeptUpload";
		  loc_x=document.body.scrollLeft+event.clientX-event.offsetX-100;
		  loc_y=document.body.scrollTop+event.clientY-event.offsetY+170;
		  window.showModalDialog(URL,window,"edge:raised;scroll:1;status:0;help:0;resizable:1;dialogWidth:300px;dialogHeight:230px;dialogTop:"+loc_y+"px;dialogLeft:"+loc_x+"px");
		}
		function LoadUser()
		{
		  URL="<%=basePath%>UploadServlet/getUserInfo";
		  loc_x=document.body.scrollLeft+event.clientX-event.offsetX-100;
		  loc_y=document.body.scrollTop+event.clientY-event.offsetY+170;
		  window.showModalDialog(URL,window,"edge:raised;scroll:1;status:0;help:0;resizable:1;dialogWidth:300px;dialogHeight:230px;dialogTop:"+loc_y+"px;dialogLeft:"+loc_x+"px");
		}
		function fun(){
			if(document.getElementById("file").value==""){
				alert("上传文件不能为空！");
				return false;
			}else{
				return true;
			}
		}
	//-------------------------------------------
function login(){
	if(document.getElementById("title").value==""){
		alert("新闻标题不能为空！");
		document.getElementById("title").focus();
		return false;
	}else if(document.getElementById("d11").value==""){
		alert("通知时间不能为空！");
		document.getElementById("d11").focus();
		return false;
	}else if(document.getElementById("content").value==""){
		alert("新闻内容不能为空！");
		document.getElementById("content").focus();
		return false;
	}else{
		return true;
	}

}

</script> 


</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
  
  <a href="NotifyServlet/getAllNotify"  class="hover">新闻管理</a>
  <a href="oa_news/addnews.jsp">新建新闻</a>

</div>



<dl class="notice clearfix">
      
  <dd>新闻管理 &gt; 修改新闻</dd>   
             
</dl>
  <c:forEach items="${no}" var="no">
 <form action="NotifyServlet/updateNews?lineid=${no[0] }&nsid=${no[6] }" method="post" onsubmit="return login()">
<ul class="qy_info">

	<li><span>* 标题：</span><input name="title" type="text"  id="title" value="${no[3] }"></li>
	
	<li><span>* 类型：</span><select name="type" style="width: 200px">
		<c:if test="${no[1] ==0}"><option value="0">通&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;知</option><option value="1">公司新闻</option><option value="2">行业动态</option></c:if>
		<c:if test="${no[1] ==1}"><option value="1">公司新闻</option><option value="0">通&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;知</option><option value="2">行业动态</option></c:if>
		<c:if test="${no[1] ==2}"><option value="2">行业动态</option><option value="0">通&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;知</option><option value="1">公司新闻</option></c:if>
		</select></li>
		<!-- <li><span>* 附件上传：</span><input type="file" name="file"></li> -->
		<li><span>* 附件文档：</span><c:if test="${no[11] ==null}">无附件</c:if><c:if test="${no[11] !=null}">${no[11] }</c:if></li>
	<li><span>* 发布范围：</span><select id="sharetype" name="sharetype">
		<c:if test="${no[8] ==0}"><option value="0">全部共享</option><option value="1">共享部门</option><option value="2">共享人员</option></c:if>
		<c:if test="${no[8] ==1}"><option value="1">共享部门</option><option value="0">全部共享</option><option value="2">共享人员</option></c:if>
		<c:if test="${no[8] ==2}"><option value="2">共享人员</option><option value="0">全部共享</option><option value="1">共享部门</option></c:if>
	</select></li>
		<c:if test="${no[10] !=0}">
		<li id="adddept"><span>* 部门共享：</span><input type="text" id="deptname" style="width: 225px;" readonly="readonly" value="${no[12] }">&nbsp;<input type="button" value="添加部门"  onClick="LoadWindow()" style="width: 70px"></li>
		</c:if>
		<c:if test="${no[10] ==0}">
		<li id="adddept"><span>* 部门共享：</span><input type="text" id="deptname" style="width: 225px;" readonly="readonly">&nbsp;<input type="button" value="添加部门"  onClick="LoadWindow()" style="width: 70px"></li>
		</c:if>
		<c:if test="${no[9] !=0}">
		<li id="adduser"><span>* 共享人员：</span><input type="text" id="username" style="width: 225px;" readonly="readonly" value="${no[15] }">&nbsp;<input type="button" value="添加人员"  onClick="LoadUser()" style="width: 70px"></li>
		</c:if>
		<c:if test="${no[9] ==0}">
		<li id="adduser"><span>* 共享人员：</span><input type="text" id="username" style="width: 225px;" readonly="readonly">&nbsp;<input type="button" value="添加人员"  onClick="LoadUser()" style="width: 70px"></li>
		</c:if>		
	<li><span>* 通知时间：</span><input name="notifydate" type="text" id="d11" style="width: 200px" value="${fn:substring(no[2] ,0,19)  }" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"><img src="./My97DatePicker/skin/datePicker.gif" width="24" height="33" align="absmiddle" style="cursor:pointer" onClick="WdatePicker({el:'d11',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/></li>
			
		</ul>
		<input type="hidden" id="deptid" name="deptid" value="${no[10] }">
		<input type="hidden" id="userid" name="userid" value="${no[9] }">
		<input type="hidden" name="ndeptid" value="${no[13] }">
		<input type="hidden" name="nuserid" value="${no[5] }">
		<input type="hidden" name="currdate" value="${no[14] }">
		<input type="hidden" name="status" value="${no[7] }">
<br>
<textarea id="editor_id" name="content" style="width:700px;height:300px;">${no[4] }</textarea>
<br><br>
<input type="submit" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="location.href='javascript:history.go(-1);'" value="返回">
</form>
</c:forEach>
</div>
</body>
</html>
