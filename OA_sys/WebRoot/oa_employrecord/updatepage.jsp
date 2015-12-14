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
    
    <title>修改员工信息</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script language="javascript" type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function()
	{
		$("#myform").submit(function()
		{
			if($.trim($("#fileno").val())==""){
				alert("员工编号不能为空！");
				$("#fileno").focus();
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
			if($.trim($("#email").val())=="")
			{
						alert("邮箱不能为空");
						$("#email").focus();
						return false;
			}else
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
			
			
			var checkflag = $("#checkflag").val();
			var bfDeptid = $("#bfDeptid").val();
			var bfpoid = $("#bfpoid").val();
			var bfdeptadrid = $("#bfdeptadrid").val();
			
			var nowdeptid = $("#compy2").val();
			var nowaddresid = $("#compy2Address").val();
			var nowpositionid = $("#positionid").val();
			
		
			if(nowdeptid == "-1")
			{
				alert("部门不能为空");
				$("#compy2").focus();
				return false;
			}
			if(nowaddresid =="" || nowaddresid ==null)
			{
				alert("办公地址不能为空");
				$("#compy2Address").focus();
				return false;
			}
			
			if(checkflag == 0)
			{
				if((bfDeptid != nowdeptid) || (bfdeptadrid != nowaddresid) || (bfpoid != nowpositionid))
				{
					
					if(confirm("部门/ 岗位/ 办公地址 发生改变, 是否提交修改申请."))
					{
						$("#issub").val("1");
						return true;
					}
					
					
				}
			
			}
			return true;
		});
	});

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
  
  <body>
  	<form  action="EmployrecordServlet/updateEmr" method="post" id="myform">
		<div class="rightsider">

			<div class="info_tabs clearfix">
				<a href="EmployrecordServlet/getAllEmr" class="hover">修改信息</a>
			</div>

			<dl class="notice clearfix">

				<dd>员工资料 （"*"为必填项）</dd>

			</dl>


			<ul class="qy_info">
				<table width="92%">
					<tr>
						<td><font color="#FF4500" size="4"><strong>*
									基本信息</strong>
						</font>
						</td>
						<td width="50px"></td>
						<td></td>
					</tr>
					<tr height="30px">
						<td>* 员工编号：<input type="text" name="fileno"
							value="${emr.fileno }" readonly="readonly" id="fileno">
						</td>
						<td width="50px"></td>
						<td rowspan="5"><c:if test="${emr.filename!=null }">
								<img
									src="EmployrecordServlet/readphoto?photourl=/${emr.filename }"
									width="110" height="150" />
								<br>&nbsp;&nbsp;&nbsp;&nbsp;<a
									href="oa_employrecord/updatephoto.jsp?filename=${emr.filename }&id=${emr.lineid}">修改头像</a>
							</c:if> <c:if test="${emr.filename==null }">
								<img src="css/nophoto.gif" width="110" height="150" />
								<br>&nbsp;&nbsp;&nbsp;&nbsp;<a
									href="oa_employrecord/uploadphoto.jsp?id=${emr.lineid}">上传头像</a>
							</c:if>
						</td>
					</tr>
					<tr height="30px">
						<td>* 员工姓名：<input type="text" name="employname"
							value="${emr.employname }" id="employname" />
						</td>
						<td width="50px"></td>
					</tr>
					<tr height="30px">
						<td>* 所属公司：<select name="cpmpy1" id="cpmpy1" onchange="getAllDeptBycompy(this);">
						<option value="-1">---请选择---</option>
						
								<c:forEach items="${compylist}" var="dept">
									<option value="${dept.lineid }"
										<c:if test="${dept.lineid== compyID}"><c:out value="selected"/></c:if>>${dept.deptname}
									</option>
								</c:forEach>
						</select></td>
						<td width="50px"></td>
					</tr>
						<tr height="30px">
						<td>* 所属部门：<select  name="deptid" id="compy2">
								<option value="-1"> ---请选择---</option>
								<c:forEach
									items="${deptlist}" var="dept">
									<option value="${dept[0] }"
										<c:if test="${dept[0]==emr.dept.lineid}"><c:out value="selected"/></c:if>>${dept[2]}</option>
								</c:forEach>
						</select></td>
						<td width="50px"></td>
					</tr>
					<tr height="30px">
						<td>* 职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：<select
							name="positionid" id="positionid"><c:forEach items="${po}" var="po">
									<option value="${po.lineid }"
										<c:if test="${po.lineid==emr.positionid}"><c:out value="selected"/></c:if>>${po.positionname}</option>
								</c:forEach>
						</select>
						</td>
						<td width="50px"></td>
					</tr>
					<tr height="30px">
						<td>* 办公公司：<select name="compay2" id="compay2" onchange="getCompyAddress(this);">
						<option value="-1">---请选择---</option>
							<c:forEach
									items="${compylist}" var="dept">
									<option value="${dept.lineid }"
										<c:if test="${dept.lineid==emr.deptaddress.comPayID}"><c:out value="selected"/></c:if>>${dept.deptname}</option>
								</c:forEach>
						</select></td>
						<td width="50px"></td>
					</tr>
					<tr height="30px">
						<td>* 办公地址：<select name="compy2Address" id="compy2Address" style="width: 200px">
								<c:forEach
									items="${addreslist}" var="dept">
									<option value="${dept[0] }"
										<c:if test="${dept[0] == emr.deptaddress.lineid}"><c:out value="selected"/></c:if>>${dept[3]}</option>
								</c:forEach>
						</select></td>
						<td width="50px"></td>
					</tr>
					<tr height="30px">
						<td colspan="1">* 入职时间：<input type="text" name="comedate"
							value="${fn:substring(emr.comedate ,0,10)  }" id="comedate" /><img
							src="./My97DatePicker/skin/datePicker.gif" width="24" height="33"
							align="absmiddle" style="cursor:pointer"
							onClick="WdatePicker({el:'comedate'})" />
						</td>
						<td width="50px"></td>
						
						<td  >
							档案合同类型: 
							<select name="conTypeID" id="conTypeID"  style="width: 158px">
								<option> --请选择--</option>
								<c:forEach items="${conTypelist}" var="contype">
									<c:choose>
										<c:when test="${emr.contractType.lineid == contype.lineid}">
											<option value="${contype.lineid }" selected="selected">${contype.typename }</option>
										</c:when>
										<c:otherwise>
											<option value="${contype.lineid }">${contype.typename }</option>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3"><hr color="#A9A9A9" size="2">
						</td>
					</tr>


					<tr>
						<td><font color="#FF4500" size="4"><strong>*
									联系方式</strong>
						</font>
						</td>
						<td width="50px"></td>
						<td></td>
					</tr>
					<tr height="30px">
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号：<input
							type="text" name="mobile" value="${emr.mobile }" id="mobile" />
						</td>
						<td width="50px"></td>
						<td>&nbsp;&nbsp;&nbsp;个人邮箱：<input type="text" name="email"
							value="${emr.email }" id="email" />
						</td>
					</tr>
					<tr height="30px">
						<td>&nbsp;&nbsp;&nbsp;&nbsp; 固定电话：<input type="text" name="tel1"
							value="${emr.tel1 }" />
						</td>
						<td width="50px"></td>
						<td>&nbsp;&nbsp;&nbsp; 分机号：<input type="text" name="tel2" value="${emr.tel2 }" />
						</td>
					</tr>
					<tr height="30px">
						<td >&nbsp;&nbsp;&nbsp;&nbsp;直线电话：<input type="text" name="telline" value="${emr.telline}"/>
						</td>
						<td width="50px"></td>
						<td colspan="3">其他联系方式：<input type="text" name="contactno"
							value="${emr.contactno }" />
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3"><hr color="#A9A9A9" size="2">
						</td>
					</tr>


					<tr>
						<td><font color="#FF4500" size="4"><strong>*
									个人信息</strong>
						</font>
						</td>
						<td width="50px"></td>
						<td></td>
					</tr>
					<tr height="30px">
						<td colspan="3">* 证件编号 ：<input type="text" name="cardno"
							value="${emr.cardno }" id="cardno" />
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3">* 出生日期 ：<input type="text" name="birthday"
							value="${fn:substring(emr.birthday ,0,10)  }" id="birthday" /><img
							src="./My97DatePicker/skin/datePicker.gif" width="24" height="33"
							align="absmiddle" style="cursor:pointer"
							onClick="WdatePicker({el:'birthday'})" /></td>
					</tr>
					<tr height="30px">
						<td colspan="3">户口所在地：<input type="text" name="accountadd"
							value="${emr.accountadd }" />
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3">* 家庭住址 ：<input type="text" name="address"
							value="${emr.address }" id="address" />
						</td>
					</tr>
					<tr height="30px">
						<td>&nbsp;&nbsp;&nbsp;婚姻状况：<select name="marriage">
								<c:if test="${emr.marriage==0}">
									<option value="0">--保密--</option>
									<option value="1">--未婚--</option>
									<option value="2">--已婚--</option>
								</c:if>
								<c:if test="${emr.marriage==1}">
									<option value="1">--未婚--</option>
									<option value="0">--保密--</option>
									<option value="2">--已婚--</option>
								</c:if>
								<c:if test="${emr.marriage==2}">
									<option value="2">--已婚--</option>
									<option value="0">--保密--</option>
									<option value="1">--未婚--</option>
								</c:if>
						</select></td>
						<td width="50px"></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家庭电话：<input
							type="text" name="hometel" value="${emr.hometel }" />
						</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;&nbsp;银行卡号： <input type="text"
							name="bankcardno" id="bankcardno" value="${emr.bankcardno }"
							style="width: 350px;" />
						</td>
					</tr>
					<tr height="30px">
						<td>紧急联系人：<input type="text" name="emergencycontact"
							value="${emr.emergencycontact }" />
						</td>
						<td width="50px"></td>
						<td>紧急联系电话：<input type="text" name="emergencytel"
							value="${emr.emergencytel }" />
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3"><hr color="#A9A9A9" size="2">
						</td>
					</tr>


					<tr>
						<td><font color="#FF4500" size="4"><strong>*
									学历信息</strong>
						</font>
						</td>
						<td width="50px"></td>
						<td></td>
					</tr>
					<tr height="30px">
						<td>* 教育程度： <select name="educationlevel">
								<c:forEach var="educ" items="${educlist}">
									<c:choose>
										<c:when test="${emr.educationlevel.lineid == educ.lineid}">
											<option value="${educ.lineid}" selected="selected">---${educ.levelname}---</option>
										</c:when>
										<c:otherwise>
											<option value="${educ.lineid}">---${educ.levelname}---</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>

						</select></td>
						<td width="50px"></td>
						<td>毕业学校：<input type="text" name="school"
							value="${emr.school }" />
						</td>
					</tr>
					<tr height="30px">
						<td>&nbsp;&nbsp;&nbsp;所学专业：<input type="text"
							name="technology" value="${emr.technology }" />
						</td>
						<td width="50px"></td>
						<td>毕业时间：<input type="text" name="graduationdate" id="d12"
							value="${fn:substring(emr.graduationdate,0,10)  }" /><img
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
						<td></td>
					</tr>
					<tr height="30px">
						<td colspan="3" width="400"><textarea rows="5" cols="50"
								name="resume">${emr.resume }</textarea>
						</td>
					</tr>
					<tr height="30px">
						<td colspan="3"><hr color="#A9A9A9" size="2">
						</td>
					</tr>



				</table>
				<input type="hidden" name="lineid" value="${emr.lineid }">
				<button type="submit" style="margin-left: 10px;height: 40px"
					class="tj add_btn">修改</button>
				<button type="button" style="margin-left: 10px;height: 40px"
					class="tj add_btn"
					onclick="location.href='javascript:history.go(-1);'">返回</button>
				<br>
				<br>
			</ul>
		</div>
		
		<input type="hidden" id="bfCompid" name="bfCompid" value="${compyID}">
		<input type="hidden" id="bfDeptid" name="bfDeptid" value="${emr.dept.lineid}">
		<input type="hidden" id="bfpoid" name="bfpoid" value="${emr.positionid}">
		<input type="hidden" id="bfofCompid" name="bfofCompid" value="${emr.deptaddress.comPayID}">
		<input type="hidden" id="bfdeptadrid" name="bfdeptadrid" value="${emr.deptaddress.lineid}">
		<input type="hidden" id="checkflag" name="checkflag" value="${checkflag}">
		<input type="hidden" id="issub" name="issub" value="0">
	</form>
  </body>
</html>
