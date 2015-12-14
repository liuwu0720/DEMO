<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	
</head>
<body>
<div class="rightsider">

<div class="info_tabs clearfix">
 <a href="EmployrecordServlet/getAllEmr" class="hover">详细信息</a> 
</div>

<dl class="notice clearfix">
      
  <dd>员工资料 </dd>   
             
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
					<td>* 员工编号：${emr.fileno }</td>
					<td width="50px"></td>
					<td rowspan="5"><c:if test="${emr.filename!=null }">
							<img
								src="EmployrecordServlet/readphoto?photourl=/${emr.filename }"
								width="200" height="150" />
							<br>&nbsp;&nbsp;&nbsp;&nbsp;员工头像</c:if> <c:if
							test="${emr.filename==null }">
							<img src="css/nophoto.gif" width="110" height="150" />
						</c:if>
					</td>
				</tr>
				<tr height="30px">
					<td>* 员工姓名：${emr.employname}</td>
					<td width="50px"></td>
				</tr>
				<tr height="30px">
					<td>* 所属公司：${compyName }</td>
					<td width="50px"></td>
				</tr>
				<tr height="30px">
					<td>* 所属部门：${emr.dept.deptname}</td>
					<td width="50px"></td>
				</tr>
				<tr height="30px">
					<td>*
						职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：${po.positionname }</td>
					<td width="50px"></td>
				</tr>
				<tr height="30px">
					<td>* 办公公司：${compyName2}</td>
					<td width="50px"></td>
				</tr>
				<tr height="30px">
					<td>* 办公地址：${emr.deptaddress.compayName} > ${emr.deptaddress.deptAddress}</td>
					<td width="50px"></td>
				</tr>
				<tr height="30px">
					<td>* 入职时间：${fn:substring(emr.comedate ,0,10) }</td>
					<td width="50px"></td>
					<td></td>
				</tr>
				<tr height="30px">
					<td>档案合同类型：${emr.contractType.typename}</td>
					<td width="50px"></td>
					<td></td>
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
					<td>* 手&nbsp;&nbsp;机&nbsp;&nbsp;号&nbsp;&nbsp;：${emr.mobile }</td>
					<td width="50px"></td>
					<td>* 个人邮箱&nbsp;&nbsp;&nbsp;：${emr.email }</td>
				</tr>
				<tr height="30px">
					<td>* 固定电话：${emr.tel1 }</td>
					<td width="50px"></td>
					<td>* 分机号：${emr.tel2 }</td>
				</tr>
				<tr height="30px">
					<td >&nbsp;&nbsp;&nbsp;&nbsp;直线电话：<input type="text" name="telline" value="${emr.telline}"/>
					</td>
					<td width="50px"></td>
					<td>*
						其&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;它：${emr.contactno}</td>
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
					<td colspan="3">* 证件编号&nbsp;&nbsp;&nbsp;：${emr.cardno }</td>
				</tr>
				<tr height="30px">
					<td colspan="3">*
						出生日期&nbsp;&nbsp;&nbsp;：${fn:substring(emr.birthday ,0,10) }</td>
				</tr>
				<tr height="30px">
					<td colspan="3">* 户口所在地：${emr.accountadd }</td>
				</tr>
				<tr height="30px">
					<td colspan="3">* 家庭住址&nbsp;&nbsp;&nbsp;：${emr.address }</td>
				</tr>
				<tr height="30px">
					<td>* 婚姻状况&nbsp;&nbsp;&nbsp;：<c:if test="${emr.marriage==0 }">保密</c:if>
						<c:if test="${emr.marriage==1 }">未婚</c:if>
						<c:if test="${emr.marriage==2 }">已婚</c:if></td>
					<td width="50px"></td>
					<td>* 家庭电话&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：${emr.hometel }</td>
				</tr>

				<tr>
					<td colspan="3">&nbsp;&nbsp;银行卡号： <input type="text"
						name="bankcardno" id="bankcardno" value="${emr.bankcardno }"
						style="width: 350px;" />
					</td>
				</tr>
				<tr height="30px">
					<td>* 紧急联系人：${emr.emergencycontact }</td>
					<td width="50px"></td>
					<td>* 紧急联系电话：${emr.emergencytel }</td>
				</tr>
				<tr height="30px">
					<td colspan="3"><hr color="#A9A9A9" size="2">
					</td>
				</tr>


				<tr><td><font color="#FF4500" size="4"><strong>* 学历信息</strong></font></td><td width="50px"></td><td></td></tr>
				<tr height="30px">
					<td>
						* 教育程度：${emr.educationlevel.levelname }
					</td>
					<td width="50px"></td>
					<td>* 毕业学校：${emr.school }</td>
				</tr>
				<tr height="30px"><td>* 所学专业：${emr.technology }</td><td width="50px"></td>
    			<td >* 毕业时间：
	    			<fmt:formatDate value="${emr.graduationdate }" pattern="yyyy/MM/dd"/> 
    			</td>
    		</tr>
   			<tr>
   				<td colspan="3">&nbsp;&nbsp;&nbsp;所持证书：<input type="text" name="certificate" id="certificate" value="${emr.certificate }"  /></td>
   			</tr>
    		<tr height="30px"><td colspan="3"><hr color="#A9A9A9" size="2"></td></tr>
    		
    		
    		<tr><td><font color="#FF4500" size="4"><strong>* 个人简历</strong></font></td><td width="50px"></td><td></td></tr>
    		<tr height="30px"><td colspan="3">${emr.resume }</td></tr>
    		<tr height="30px"><td colspan="3"><hr color="#A9A9A9" size="2"></td></tr>
    		
    		</table>
    	
	<button type="button" style="height: 40px" class="tj add_btn" onclick="location.href='javascript:history.go(-1);'">返回</button>
		
</ul>

</div>
</body>
</html>

