<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>邮箱密码重置</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		//发送验证码
		function sendCode(codeObj){
			var email=$('#email').val();
			$.post('<%=basePath%>mailController/sendCode',
			function(data){
				//alert(data.msg);
				if(data.isSuccess){
					$(codeObj).parent().next().html(data.msg).css('color','#7cfc00').css('font-size','18px');
				}else{
					$(codeObj).parent().next().html(data.msg).css('color','red').css('font-size','18px');
				}
			},
			'json');
		};
		//ajax提交
		function ajaxSubmit(){
			var code=$.trim($('#code').val());
			if(code==""){
				alert('验证码不能为空');
				$('#code').focus();
				return ;
			}
			var password=$.trim($('#password').val());
			if(password==""){
				alert('密码不能为空！');
				$('#password').focus();
				return ;
			}
			var regexp=/^\d+$/;
			if(regexp.test(password)){
				alert('密码不能为纯数字！');
				return;
			}
			regexp=/^[A-Za-z]+$/;
			//alert("regexp:"+regexp.test(password));
			if(regexp.test(password)){
				alert('密码不能为纯字母！');
				return;
			}
			regexp=/^.{8,}$/;
			//alert("regexp:"+regexp.test(password));
			if(!regexp.test(password)){
				alert('密码必须是8位以上！');
				return;
			}
			//验证2次密码是否一致
			var repwd=$.trim($('#repwd').val());
			if(repwd==""){
				alert('确认密码不能为空！');
				$('#repwd').focus();
				return;
			}
			//alert(password+','+repwd);
			if(password!=repwd){
				$('#repwd').parent().next().html('2次输入的密码不一致，请确认')
				.css('color','red').css('font-size','18px');
				return;
			}else{
				$('#repwd').parent().next().html('已确认')
				.css('color','#7cfc00').css('font-size','18px');
			}
			$.post('<%=basePath%>mailController/updateMailPwd',
					{code:code,password:password},
					function(data){
						alert(data.msg);
					},
					'json'
			);
		}
	</script>
  </head>
  <body style="overflow: auto;overflow-y:hidden;overflow-x:scroll;">
    <div class="rightsider" style="width: 1100px;">
    	<div class="info_tabs clearfix">
    		<a href="mailController/getMailById" class="hover">邮箱密码修改</a>
    	</div>
    	<form action="<%=basePath%>mailController/updateMailPwd" method="post" id="myform">
    		<table align="left"  cellpadding="0" cellspacing="0"  class="gridtable">
    			<tr>
    				<td width="10%"></td>
    				<td><input type="button" value="点击获取验证码" style="background:#ff6600;width: 120px;align:center" onclick="sendCode(this)" /></td>
    				<td></td>
    			</tr>
    			<tr >
    				<td width="10%">
    					验证码<font color="red">*</font>：
    				</td>
    				<td>
    					<input type="text" name="code" id="code"/>
    				</td>
    				<td>
    					
    				</td>
    			</tr>
    			<tr >
    				<td width="10%">
    					新密码<font color="red">*</font>：
    				</td>
    				<td>
    					<input type="password" name="password" id="password"/>
    				</td>
    				<td>密码不能包含空格，长度最少8位，不能包含用户名，
    					不能为纯数字，<br/>
    				     纯字母，单个字符连续出现次数不能大于等于密码总长度一半
    				</td>
    			</tr>
    			<tr>
    				<td>
    					确认密码<font color="red">*</font>：
    				</td>
    				<td>
    					<input type="password" name="repwd" id="repwd"/>
    				</td>
    				<td></td>
    			</tr>
    			<tr>
    				<td width="10%"></td>
    				<td>
    					<input type="button"  id="btn" value="确定" style="background:#ff6600;width: 80px;align:center" onclick="ajaxSubmit()"/>
    				</td>
    				<td></td>
    			</tr>
    		</table>
    	</form>
    </div>
  </body>
</html>