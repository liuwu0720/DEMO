<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html public "-/w3c/dtd html 4.01 transitional/en" 
"http://www.w3.org/tr/html4/loose.dtd">
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<title>企业管理-档案管理</title>

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
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.autocomplete.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />

		<script type="text/javascript">
	$(document).ready(function(){
		
		inits();
	});

	
		/* var data = $( "stu", dataxml ).map(function() {
			                return {
			                    positionName: $( "name", this ).text(),
			                    lineid: $( "id", this ).text()
			                };
			            }); 
						
						  $(document).ready(function(){
								$("#positionName").autocomplete(data,{
								   		minChars: 0,  			//在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
								   		//width:200,			//指定下拉框的宽度. Default: input元素的宽度
								   		max:10,					//autoComplete下拉显示项目的个数.Default: 10
										//delay:20,				//击键后激活autoComplete的延迟时间(单位毫秒).Default: 远程为400 本地10
										//autoFill:true,		//要不要在用户选择时自动将用户当前鼠标所在的值填入到input框. Default: false
										//mustMatch:true,		// 如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
										matchContains:true,	//决定比较时是否要在字符串内部查看匹配,如ba是否与foo bar中的ba匹配.使用缓存时比较重要.不要和autofill混用.Default: false
										selectFirst:true,	//如果设置成true,在用户键入tab或return键时autoComplete下拉列表的第一个值将被自动选择,尽管它没被手工选中(用键盘或鼠标).当然如果用户选中某个项目,那么就用用户选中的值. Default: true
										//cacheLength:50,		//缓存的长度.即对从数据库中取到的结果集要缓存多少条记录.设成1为不缓存.Default: 10
										//matchSubset:true,		//autoComplete可不可以使用对服务器查询的缓存,如果缓存对foo的查询结果,那么如果用户输入foo就不需要再进行检索了,直接使用缓存.通常是打开这个选项以减轻服务器的负担以提高性能.只会在缓存长度大于1时有效.Default: true
										//matchCase:false,		//比较是否开启大小写敏感开关.使用缓存时比较重要.如果你理解上一个选项,这个也就不难理解,就好比foot要不要到FOO的缓存中去找.Default: false
										//multiple:true,		//是否允许输入多个值即多次使用autoComplete以输入多个值. Default: false
										//multipleSeparator:"==>",		//如果是多选时,用来分开各个选择的字符. Default: ","
										scroll:true,			//当结果集大于默认高度时是否使用卷轴显示 Default: true
										scrollHeight:200,		//自动完成提示的卷轴高度用像素大小表示 Default: 180
										//dataType:'json',		//返回的数据类型为JSON类型
										//下拉框显示的数据 
										formatItem:function(row, i, n){
											//return "哈哈 "+row.positionName;
											return row.positionName;
										}
										
										 //formatResult: function(data) {//定义最终返回的数据，比如我们还是要返回原始数据，而不是formatItem过的数据
										 
										//在选择完之后的回调函数
								   }).result(function(event, item, formatted){
								   		//alert("item"+item.positionName+" id "+item.lineid+" formatted "+formatted);
									   $("#positionid").val(item.lineid);
								   });
								});
							}); 
								 
							*/	

function checksub()
{

			//部门不能空  办公地点不能空 
			if($("#compy2").val()=="-1")
			{
				alert("部门不能为空");
				$("#compy2").focus();
				return false;
			}
			if($("#compy2Address").val()=="" || $("#compy2Address").val()==null)
			{
				alert("办公地址不能为空");
				$("#compy2Address").focus();
				return false;
			}
			
			if($.trim($("#fileni").val())==""){
				alert("员工编号不能为空！");
				$("#fileni").focus();
				return false;
			}
			 if($("#employname").val()==""){
				alert("用户姓名不能为空！");
				$("#employname").focus();
				return false;
			} if($("#comedate").val()==""){
				alert("入职时间不能为空！");
				$("#comedate").focus();
				return false;
			} if($("#cardno").val()==""){
				alert("证件编号不能为空！");
				$("#cardno").focus();
				return false;
			}
		
			 if($("#cardno").val().length!=15&&$("#cardno").val().length!=18&&$("#cardno").val().length!=8){
	 			 alert("身份证号码必须为18、15位或8位");
	 			 $("#cardno").focus();
	 			 return false;
	 		} if($("#birthday").val()==""){
	 		 	alert("出生日期不能为空！");
	 		 	$("#birthday").focus();
				return false;
	 		} if($("#address").val()=="")
	 		{
	 		 	alert("家庭住址不能为空！");
	 		 	$("#address").focus();
				return false;
	 		} 
	 		
	 		if($.trim($("#mobile").val())=="")
	 		{
	 			 	alert("手机号码不能为空");
	 				 $("#mobile").focus();
	 				 return false;
	 		}else
	 		{
	 			
	 			 if(isNaN($("#mobile").val())){
	 				 alert("手机号码必须为纯数字");
	 				 $("#mobile").focus();
	 				 return false;
	 			}
	 			if($("#mobile").val().length!=11){
	 				 alert("手机号码位数不正确");
	 				$("#mobile").focus();
	 				 return false;
	 			}
			}
			if($.trim($("#email").val())!="")
			{
					var strReg="";
					var r;
					var str=$("#email").val();
					//strReg=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/i;
					strReg=/^\w+((-\w+)|(\.\w+))*\@{1}\w+\.{1}\w{2,4}(\.{0,1}\w{2}){0,1}/ig;
					r=str.search(strReg);
					if(r==-1) {
						alert("邮箱格式错误!");
						$("#email").focus();
						return false;
					}
			}
			
			var userno = $("#fileni").val();
			$.ajax({
					type:"post",
					url:"<%=basePath%>UserServlet/ajaxCheckUserNo",
					data:"userno="+userno,
					dataType : "text",
					success:function(data)
					{
						if(data!="ok"){
							alert("用户名已存在,请重新输入.");
							$("#fileni").focus();
							return false;
						}else
						{
							$("#myform").submit();
						}
						
					}	
				});
				
}
	function inits()
	{
		//var data;
		$.ajax({
				type:"post",
				url:"<%=basePath%>EmployrecordServlet/loadPositionForXML",
				//data:"uname="+uname+"&upass="+upass,
				dataType : "json",
				success:function(dataxml)
				{
					
					//var data = $("stu", dataxml).map(function() {
			          //      return {
			            //        positionName: $(this).attr("name"),
			              //      lineid: $(this).attr("id")
			                //};
			            //});
					//alert(dataxml);
					//aa();
					$("#positionName").autocomplete(dataxml,{
								   		minChars: 0,  			//在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
								   		//width:200,			//指定下拉框的宽度. Default: input元素的宽度
								   		max:10,					//autoComplete下拉显示项目的个数.Default: 10
										//delay:20,				//击键后激活autoComplete的延迟时间(单位毫秒).Default: 远程为400 本地10
										//autoFill:true,		//要不要在用户选择时自动将用户当前鼠标所在的值填入到input框. Default: false
										mustMatch:true,		// 如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
										matchContains:true,	//决定比较时是否要在字符串内部查看匹配,如ba是否与foo bar中的ba匹配.使用缓存时比较重要.不要和autofill混用.Default: false
										selectFirst:true,	//如果设置成true,在用户键入tab或return键时autoComplete下拉列表的第一个值将被自动选择,尽管它没被手工选中(用键盘或鼠标).当然如果用户选中某个项目,那么就用用户选中的值. Default: true
										//cacheLength:50,		//缓存的长度.即对从数据库中取到的结果集要缓存多少条记录.设成1为不缓存.Default: 10
										//matchSubset:true,		//autoComplete可不可以使用对服务器查询的缓存,如果缓存对foo的查询结果,那么如果用户输入foo就不需要再进行检索了,直接使用缓存.通常是打开这个选项以减轻服务器的负担以提高性能.只会在缓存长度大于1时有效.Default: true
										//matchCase:false,		//比较是否开启大小写敏感开关.使用缓存时比较重要.如果你理解上一个选项,这个也就不难理解,就好比foot要不要到FOO的缓存中去找.Default: false
										//multiple:true,		//是否允许输入多个值即多次使用autoComplete以输入多个值. Default: false
										//multipleSeparator:"==>",		//如果是多选时,用来分开各个选择的字符. Default: ","
										scroll:true,			//当结果集大于默认高度时是否使用卷轴显示 Default: true
										scrollHeight:200,		//自动完成提示的卷轴高度用像素大小表示 Default: 180
										//dataType:'json',		//返回的数据类型为JSON类型
										//下拉框显示的数据 
										formatItem:function(row, i, n){
											//return "哈哈 "+row.positionName;
											return row.positionName;
										}
								   }).result(function(event, item, formatted){
								   		//alert("item"+item.positionName+" id "+item.lineid+" formatted "+formatted);
								   		if(typeof(item) != "undefined")
								   		{
								   			$("#positionid").val(item.lineid);
								   		}
									   
								   });
								   

				}// success end
			});  //ajax end
			
			
	}
	
	function ajaxuserNo()
	{
		var userno = $("fileni").val();
			$.ajax({
					type:"post",
					url:"<%=basePath%>UserServlet/ajaxCheckUserNo",
					data:"userno="+userno,
					dataType : "text",
					success:function(data)
					{
						if(data!="ok"){
							alert("用户名已存在,请重新输入.");
							$("fileni").focus();
							return false;
						}else
						{
							return true;
						}
						
					}	
				});
	}
	function getAllDeptBycompy(obj)
	{
		var compy2 = document.getElementById("compy2");
		 compy2.options.length = 1;
		
		 
		var compy1 = obj.value;
			$.ajax({
				type:"post",
				url:"<%=basePath%>EmployrecordServlet/getAllDeptByCompID",
				data:"compy1="+compy1,
				dataType : "json",
				success:function(data)
				{
					for(var i=0;i<data.length;i++)
					{
						
						var op = new Option(data[i].deptname,data[i].lineid);
						compy2.options.add(op);  
					}
				}	
			});
		
	}
	
	function getCompyAddress(obj)
	{
		var compy2 = document.getElementById("compy2Address");
		 compy2.options.length = 0;
		
		 
		var compy1 = obj.value;
			$.ajax({
				type:"post",
				url:"<%=basePath%>EmployrecordServlet/getCompyAddressByID",
				data:"compy1="+compy1,
				dataType : "json",
				success:function(data)
				{
					for(var i=0;i<data.length;i++)
					{
						
						var op = new Option(data[i].deptaddress,data[i].lineid);
						compy2.options.add(op);  
					}
				}	
			});
	}
	</script>
</head>
<body >
<div class="rightsider">

<div class="info_tabs clearfix">
 <a href="EmployrecordServlet/loadDeptPosition" class="hover">员工档案添加</a> 
</div>

<dl class="notice clearfix">
      
  <dd>员工资料（"*"为必填项） </dd>   
             
</dl>
<form  action="EmployrecordServlet/add" method="post" enctype="multipart/form-data" id="myform">

<ul class="qy_info">
	<table width="92%">
					<tr>
						<td><font color="#FF4500" size="4"><strong>基本信息</strong>
						</font>
						</td>
						<td width="50px"></td>
						<td></td>
					</tr>
					<tr height="30px">
						<td>* 用户名：<input type="text" name="fileno" id="fileni">
						</td>
						<td width="50px"></td>
						<td>* 员工姓名：<input type="text" name="employname"
							id="employname" />
						</td>
					</tr>
					<tr height="30px">
						<td>* 所属公司：<select name="cpmpy1" id="cpmpy1" onchange="getAllDeptBycompy(this);" style="width: 158px">
								<option value="-1">---请选择---</option>
								<c:forEach items="${complist}" var="dept">
									<option value="${dept.lineid }">${dept.deptname }</option>
								</c:forEach>
						</select>
						</td>
						<td width="50px"></td>
						<td>* 办公公司：<select name="compay2" id="compay2" onchange="getCompyAddress(this);" style="width: 158px">
								<option value="-1">---请选择---</option>
								<c:forEach items="${complist}" var="dept">
									<option value="${dept.lineid }">${dept.deptname }</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					
					<tr height="30px">
						<td>* 所属部门：<select name="deptid" id="compy2" style="width: 158px">
							
								<option value="-1"> ---请选择---</option>
								
						</select>
						</td>
						<td width="50px"></td>
						<td>* 办公地点：
						<select name="compy2Address" id="compy2Address" style="width: 200px">
							
						</select>
						</td>
					</tr>
					
					<tr height="30px">
						
						<td>* 岗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
						<input type="text" id="positionName" name="positionName" size="30">
						<input type="hidden" id="positionid" name="positionid"  >
						</td>
						
						<td width="50px"></td>
						
						<td>
							档案合同类别:<select name="conTypeID" id="conTypeID"  style="width: 158px">
								<c:forEach items="${conTypelist}" var="contype">
									<option value="${contype.lineid }">${contype.typename }</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					
					<tr height="30px">
						<td>* 入职时间：<input type="text" name="comedate" id="comedate"
							readonly="readonly" onfocus="WdatePicker({isShowWeek:true})" /><img
							src="./My97DatePicker/skin/datePicker.gif" width="24" height="33"
							align="absmiddle" style="cursor:pointer"
							onClick="WdatePicker({el:'comedate'})" />
						</td>
						<td width="50px"></td>
						<td>&nbsp;&nbsp;上传头像：<input type="file" name="file" id="file"
							width="200px" />
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3"><hr color="#A9A9A9" size="2">
						</td>
					</tr>

					<tr>
						<td><font color="#FF4500" size="4"><strong>联系方式</strong>
						</font>
						</td>
						<td width="50px"></td>
						<td></td>
					</tr>
					<tr height="30px">
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 手机号：<input
							type="text" name="mobile" id="mobile" />
						</td>
						<td width="50px"></td>
						<td>&nbsp;* 个人邮箱：<input type="text" name="email" id="email" />
						</td>
					</tr>
					<tr height="30px">
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;固定电话：<input type="text" name="tel1" id="tel1"/>
						</td>
						<td width="50px"></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分机号：<input type="text" name="tel2" id="tel2" />
						</td>
					</tr>
					<tr height="30px">
						<td >&nbsp;&nbsp;&nbsp;&nbsp;直线电话：<input type="text" name="telline" />
						</td>
						<td width="50px"></td>
						<td>其他联系方式：<input type="text" name="contactno" />
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3"><hr color="#A9A9A9" size="2">
						</td>
					</tr>

					<tr>
						<td><font color="#FF4500" size="4"><strong>个人信息</strong>
						</font>
						</td>
						<td width="50px"></td>
						<td></td>
					</tr>
					<tr height="30px">
						<td>* 证件编号 ：<input type="text" name="cardno" id="cardno" />
						</td>
						<td width="50px"></td>
						<td>&nbsp;&nbsp;&nbsp;* 出生日期 ：<input type="text"
							name="birthday" readonly="readonly" id="birthday"
							onfocus="WdatePicker({isShowWeek:true})" /><img
							src="./My97DatePicker/skin/datePicker.gif" width="24" height="33"
							align="absmiddle" style="cursor:pointer"
							onClick="WdatePicker({el:'birthday'})" /></td>
					</tr>
					<tr height="30px"><td >* 家庭住址 ：<input type="text" name="address" id="address"/></td><td width="50px"></td><td>&nbsp;&nbsp;&nbsp;户口所在地：<input type="text" name="accountadd"/></td></tr>
					<tr height="30px">
						<td>&nbsp;&nbsp;&nbsp;婚姻状况：<select name="marriage">
								<option value="0">--保密--</option>
								<option value="1">--未婚--</option>
								<option value="2">--已婚--</option>
						</select></td>
						<td width="50px"></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家庭电话：<input
							type="text" name="hometel" />
						</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;&nbsp;银行卡号： <input type="text"
							name="bankcardno" id="bankcardno" style="width: 350px;" />
						</td>
					</tr>
					<tr height="30px">
						<td>紧急联系人：<input type="text" name="emergencycontact" />
						</td>
						<td width="50px"></td>
						<td>紧急联系电话：<input type="text" name="emergencytel" />
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3"><hr color="#A9A9A9" size="2">
						</td>
					</tr>

					<tr>
						<td><font color="#FF4500" size="4"><strong>学历信息</strong>
						</font>
						</td>
						<td width="50px"></td>
						<td></td>
					</tr>
					<tr height="30px">
						<td>* 教育程度：<select name="educationlevel" id="educationlevel">
								<c:forEach items="${educs}" var="educ">
									<option value="${educ.lineid}">--${educ.levelname}--</option>
								</c:forEach>
						</select></td>
						<td width="50px"></td>
						<td>毕业学校：<input type="text" name="school" />
						</td>
					</tr>
					<tr height="30px">
						<td>&nbsp;&nbsp;&nbsp;所学专业：<input type="text"
							name="technology" />
						</td>
						<td width="50px"></td>
						<td>毕业时间：<input type="text" name="graduationdate" id="d12"
							readonly="readonly" onfocus="WdatePicker({isShowWeek:true})" /><img
							src="./My97DatePicker/skin/datePicker.gif" width="24" height="33"
							align="absmiddle" style="cursor:pointer"
							onClick="WdatePicker({el:'d12'})" /></td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;&nbsp;&nbsp;所持证书：<input type="text"
							name="certificate" id="certificate" value="${emr.certificate }"
							style="width:450px;" />
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3"><hr color="#A9A9A9" size="2">
						</td>
					</tr>

					<tr>
						<td><font color="#FF4500" size="4"><strong>个人简历</strong>
						</font>
						</td>
						<td width="50px"></td>
						<td>
							<font color="#FF4500" size="4"><strong>所开权限</strong></font>
						</td>
					</tr>
					<tr height="30px">
						<td colspan="" width="400"><textarea rows="5" cols="50"
								name="resume"></textarea>
						</td>
						<td width="50px"></td>
						<td >
							人力行政</br>
							<c:forEach items="${hrmap}" var="hrsta">
								<input type="checkbox" id="hrprepare" name="hrprepare" value="${hrsta.key}">${hrsta.value}
							</c:forEach>
							</br>
							
							
							信息系统</br>
							<c:forEach items="${itmap}" var="itsta">
								<input type="checkbox" id="itprepare" name="itprepare" value="${itsta.key}">${itsta.value}
							</c:forEach>
							</br>
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3"><hr color="#A9A9A9" size="2">
						</td>
					</tr>
				</table>
	
</ul>
<button type="button"  onclick="checksub();" style="margin-left: 10px;height: 40px" class="tj add_btn">添 加</button>
	<button type="button" style="margin-left: 10px;height: 40px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		
</form>
</div>
</body>
</html>

