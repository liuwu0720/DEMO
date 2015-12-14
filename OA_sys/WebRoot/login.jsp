<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<base href="<%=basePath%>">

<title>OA系统登录界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		var uname = $.cookie("cookname");
		var pwd = $.cookie("cookpwd");
		if(uname!=null && uname!="")
		{
			$("#uname").val(uname);
			$("#upass").val(pwd);
		}
	
		$("#myform").submit(function(){
			if($.trim($("#uname").val())==""){
				alert("用户名不能为空");
				$("#uname").focus();
				return false;
			} 
			
		});
		
		
		function ajaxSubmit()
		{
			var uname=$.trim($("#uname").val());
			var upass=$.trim($("#upass").val());
			
			$.ajax({
				type:"post",
				url:"<%=basePath%>UserServlet/ajaxCheckUser",
					data : "uname=" + uname + "&upass=" + upass,
					dataType : "text",
					success : function(data) {
						if (data == "ok") {
							window.location.href = "<%=basePath%>UserServlet/frame";

							if ($("#check").attr("checked") == "checked") {
								var uname = $.trim($("#uname").val());
								var upass = $.trim($("#upass").val());
								//cookie 名称  值  保存时间
								$.cookie("cookname", uname, {
									expires : 7
								});
								$.cookie("cookpwd", upass, {
									expires : 7
								});
							}

						} else if (data == "usernoError") {
							alert("账号不存在,请联系相关人员.");
							return;
						} else if (data == "pwdError") {
							alert("密码错误,请重新输入");
							$("#upass").focus();

						}
					}
				});
			}

			$("#button").click(ajaxSubmit);

			$(document).keyup(function(event) {
				if (event.keyCode == 13) {
					ajaxSubmit();
				}
			});

		});

		function showhelp() {
			window.open("img/helpIESet.html", "");
		}
	</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #016aa9;
	overflow: hidden;
}

.STYLE1 {
	color: #000000;
	font-size: 12px;
}

#button {
	background: url(Images/dl.gif) no-repeat;
	width: 49px;
	height: 18px;
	border: 0px;
	cursor: pointer;
}
-->
</style>
</head>

<body>
	<center>
		<form	action="${pageContext.request.contextPath }/UserServlet/userLogin"
			method="post" name="myForm" id="myform">
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><table width="962" border="0" align="center"
							cellpadding="0" cellspacing="0">
							<tr>
								<td height="235"
									background="${pageContext.request.contextPath }/Images/login_03.gif">&nbsp;</td>
							</tr>
							<tr>
								<td height="53"><table width="100%" border="0"
										cellspacing="0" cellpadding="0">
										<tr>
											<td width="394" height="53" background="Images/login_05.gif">&nbsp;</td>
											<td width="206"
												background="${pageContext.request.contextPath }/Images/login_06.gif"><table
													width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td width="16%" height="25"><div align="right">
																<span class="STYLE1">用户</span>
															</div></td>
														<td width="57%" height="25"><div align="center">
																<input type="text" name="uname" id="uname"
																	style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
															</div></td>
														<td width="27%" height="25">&nbsp;</td>
													</tr>
													<tr>
														<td height="25"><div align="right">
																<span class="STYLE1">密码</span>
															</div></td>
														<td height="25"><div align="center">
																<input type="password" name="upass" id="upass"
																	style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
															</div></td>
														<td height="25"><div align="left">
																<input type="button" id="button" value=""
																	style="width: 49;height:18">
															</div></td>
													</tr>
													<tr>
														<td height="25" style="text-align: left;"><a
															href="javascript:showhelp();">设置</a></td>
														<td colspan="2"><input type="checkbox" id="check"
															name="check" onclick="checks();"> 记住密码(保存7天)</td>
													</tr>
												</table></td>
											<td width="362"
												background="${pageContext.request.contextPath }/Images/login_07.gif">&nbsp;</td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td height="213"
									background="${pageContext.request.contextPath }/Images/login_08.gif">&nbsp;</td>
							</tr>
						</table></td>
				</tr>
			</table>

		</form>
	</center>
</body>
</html>
