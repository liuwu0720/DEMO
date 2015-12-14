<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<title>合同信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
<style type="text/css">
	#tab {
	border:1px solid black;
	border-collapse: collapse;
	margin:0 auto; 
	width:50%;
}
	#tab  td {
	border:1px solid black;
	width:20%;
	height:30px; 
	padding: 5px;
	margin: 0;
	text-align: center;
}
	#td1 {
	background-color: #ff6600;
}
</style>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//父类合同下拉框选择事件
		$('#sel1').change(function(){
			var pid=$('#sel1 option:selected ').val();
			//alert("pid:"+pid);
			$.post('<%=path %>/contractController/getTypesByPid',
					{pid:pid},
					function(json){
						//alert("isSuccess:"+json.isSuccess);
						$('#sel2').empty();
						//var opt=new Option('--请选择--',0);
						$('#sel2').append('<option value="0">--请选择--</option>');
						if(json.isSuccess){
							var data=json.data;
							for(var i=0;i<data.length;i++){
								//var option=new Option(data[i].vcType,data[i].id);
								//$('#sel2').append(option);
								$('#sel2').append('<option value="'+data[i].id+'">'+data[i].vcType+'</option>');
							}
						}
					},'json');
			
		});
		//2级下拉框选择事件
		$('#sel2').change(function(){
			var val=$('#sel2 option:selected').val();
			//alert('val:'+val);
			$('#td_pro').empty();
			var str='';
			switch(val){
				case '8'://整车收入合同 
					str='<select name="nLong">'
							+'<option value="0">长期合同（1年或一年以上）</option>'
							+'<option value="1">临时合同</option>'
							+'</select>';
					break;
				case '9'://零部件收入合同
					str='<select name="nMainsign">'
						+'<option value="0">与主机厂签订的</option>'
						+'<option value="1">非与主机厂签订的</option>'
						+'</select>';
					break;
				case '13'://物流器具设备采购合同
					str='<select name="nPrice">'
						+'<option value="0">单件单价≤5万</option>'
						+'<option value="1">单件单价＞5万</option>'
						+'</select>';
					break;
				case '14'://办公设备采购合同
					str='<select name="nPrice">'
						+'<option value="0">单件单价≤5万</option>'
						+'<option value="1">单件单价＞5万</option>'
						+'</select>';
					break;
				case '15'://土地租赁合同
					str='<select name="nYearmoney">'
						+'<option value="0">年度金额≤50万</option>'
						+'<option value="1">年度金额＞50万</option>'
						+'</select>';
					break;
				case '18'://以项目向下采购合同
					str='<select name="nYearmoney">'
						+'<option value="0">年度金额≤20万</option>'
						+'<option value="1">年度金额＞20万</option>'
						+'</select>';
					break;
				case '19'://其他类合同
					str='<select name="nExpect">'
						+'<option value="0">预算内</option>'
						+'<option value="1">预算外</option>'
						+'</select>';
					break;
			}
			if(str!=''){
				$('#td_pro').html(str);
			}
		});
	})	
	//提交表单
	function submitForm(){
	
		
		//解决ie不兼容trim
		if(!String.prototype.trim) {
            String.prototype.trim = function () {
                 return this.replace(/^\s+|\s+$/g,'');
            };
      	}
		var vcContractname=$('input[name="vcContractname"]').val().trim();
		//alert(!vcContractname);
		if(!vcContractname){
			alert('合同名称不能为空');
			$('input[name="vcContractname"]').focus();
			return;
		}
		var vcContractno=$('input[name="vcContractno"]').val().trim();
		if(!vcContractno){
			alert('合同编号不能为空');
			$('input[name="vcContractno"]').focus().trim();
			return;
		}
		var vcPartya=$('input[name="vcPartya"]').val().trim();
		if(!vcPartya){
			alert('甲方不能为空');
			$('input[name="vcPartya"]').focus();
			return;
		}
		var vcPartyb=$('input[name="vcPartyb"]').val().trim();
		if(!vcPartyb){
			alert('乙方不能为空');
			$('input[name="vcPartyb"]').focus();
			return;
		}
		var pContracttype=$('#sel1').val().trim();
		//alert("pContracttype:"+pContracttype);
		if(pContracttype==0){
			alert('请选择一级合同类型');
			return;
		}
		var IContracttype=$('#sel2').val().trim();
		//alert("IContracttype:"+IContracttype);
		if(IContracttype==0){
			alert('请选择二级合同类型');
			return;
		}
		var start=$('input[name="start"]').val().trim();
		if(!start){
			alert('合同生效日期不能为空');
			$('input[name="start"]').focus().trim();
			return;
		}
		var end=$('input[name="end"]').val().trim();
		if(!end){
			alert('失效日期不能为空');
			$('input[name="end"]').focus();
			return;
		}
		
		$('#myform').submit();
	}
	//处理CKEDITOR的值
    function CKupdate() {
       for (instance in CKEDITOR.instances)
       CKEDITOR.instances[instance].updateElement();
    }
	//按钮添加
	function addBtn(obj){
		var str='<div>'
					+'<input type="file" name="files" style="width: 200px"/>&nbsp;&nbsp;&nbsp;'
					+'<input type="file" name="files" style="width: 200px"/>&nbsp;&nbsp;&nbsp;'
					+'<img src="<%=path %>/img/add.png" onclick="addBtn(this)" style="width: 25px;heigth:25px;vertical-align:middle">&nbsp;&nbsp;&nbsp;'
					+'<img src="<%=path %>/img/bin.png" onclick="delBtn(this)" style="width: 25px;heigth:25px;vertical-align:middle">'
				+'</div>';
		$(obj).parent().parent().append(str);
	}
	//按钮删除
	function delBtn(obj){
		var len=$(obj).parent().parent().children().length;
		if(len>1){
			var div=$(obj).parent().parent().children().last();
			//alert(div.html());
			div.remove();
		}
	}
</script>

</head>

<body>
	<div class="rightsider">

		<div class="info_tabs clearfix">
			<a  class="hover">合同添加</a>
		</div>
		<dl class="notice clearfix">
			<dd>合同资料</dd>
		</dl>
		<form action="<%=basePath%>contractController/saveContract" method="post" enctype="multipart/form-data"
			id="myform">
			<table id="tab">
				<tr>
					<td colspan="4">
						<div style="float:left;margin: 5px 150px 0px 5px"><img src="<%=basePath%>css/unlcn.png"></div>
						<div><span style="font-family: 黑体;font-size: 20px;font-weight: bolder;float:left">中联物流（中国）有限公司<br/>合同评审表</span></div>
					</td>
				</tr>
				<tr>
					<td>合同名称</td>
					<td>
						<input type="text"  name="vcContractname" value="${contract.vcContractname}"/>
					</td>
					<td>合同编号</td>
					<td>
						<input type="text"  name="vcContractno" value="${contract.vcContractno}"/>
					</td>
				</tr>
				<tr>
					<td>甲方</td>
					<td><input type="text"  name="vcPartya" value="${contract.vcPartya}"/></td>
					<td>乙方</td>
					<td><input type="text"  name="vcPartyb" value="${contract.vcPartyb}"/></td>
				</tr>
				<tr>
					<td>合同类别</td>
					<td >
						<select id="sel1" name="pContracttype">
							<option value="0">--请选择--</option>
							<c:forEach items="${pTypelist}" var="pType">
								<c:choose>
									<c:when test="${pType.id==partype.id}">
										<option value="${pType.id}" selected="selected">${pType.vcType}</option>
									</c:when>
									<c:otherwise>
										<option value="${pType.id}">${pType.vcType}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						
					</td>
					<td>
						<select id="sel2" name="IContracttype">
							<c:if test="${sublist==null}" var="isTrue">
								<option value="0">--请选择--</option>
							</c:if>
							<c:if test="${!isTrue}">
								<c:forEach items="${sublist}" var="stype">
								<c:choose>
									<c:when test="${stype.id==subtype.id}">
										<option value="${stype.id}" selected="selected">${stype.vcType}</option>
									</c:when>
									<c:otherwise>
										<option value="${stype.id}">${stype.vcType}</option>
									</c:otherwise>
								</c:choose>
								</c:forEach>
							</c:if>
						</select>
					</td>
					<td id="td_pro">
						<!--整车收入合同 -->
						<c:if test="${subtype.vcTypeNo=='scarload'}">
							<select name="nLong">
								<c:if test="${contract.nLong==0}">
									<option value="0" selected="selected">长期合同（1年或一年以上）</option>
									<option value="1">临时合同</option>
								</c:if>
								<c:if test="${contract.nLong==1}">
									<option value="0" >长期合同（1年或一年以上）</option>
									<option value="1" selected="selected">临时合同</option>
								</c:if>
							</select>
						</c:if>
						<!--零部件收入合同-->
						<c:if test="${subtype.vcTypeNo=='spartincome'}">
							<select name="nMainsign">
								<c:if test="${contract.nMainsign==0}">
									<option value="0" selected="selected">与主机厂签订的</option>
									<option value="1">非与主机厂签订的</option>
								</c:if>
								<c:if test="${contract.nMainsign==1}">
									<option value="0" >与主机厂签订的</option>
									<option value="1" selected="selected">非与主机厂签订的</option>
								</c:if>
							</select>
						</c:if>
						<!--物流器具设备采购合同-->
						<c:if test="${subtype.vcTypeNo=='sequipment'}">
							<select name="nPrice">
								<c:if test="${contract.nPrice==0}">
									<option value="0" selected="selected">单件单价≤5万</option>
									<option value="1">单件单价＞5万</option>
								</c:if>
								<c:if test="${contract.nPrice==1}">
									<option value="0">单件单价≤5万</option>
									<option value="1" selected="selected">单件单价＞5万</option>
								</c:if>
							</select>
						</c:if>
						<!--办公设备采购合同-->
						<c:if test="${subtype.vcTypeNo=='soffical'}">
							<select name="nPrice">
								<c:if test="${contract.nPrice==0}">
									<option value="0" selected="selected">单件单价≤5万</option>
									<option value="1">单件单价＞5万</option>
								</c:if>
								<c:if test="${contract.nPrice==1}">
									<option value="0">单件单价≤5万</option>
									<option value="1" selected="selected">单件单价＞5万</option>
								</c:if>
							</select>
						</c:if>
						<!--土地租赁合同-->
						<c:if test="${subtype.vcTypeNo=='sland'}">
							<select name="nYearmoney">
								<c:if test="${contract.nYearmoney==0}">
									<option value="0" selected="selected">年度金额≤50万</option>
									<option value="1">年度金额＞50万</option>
								</c:if>
								<c:if test="${contract.nYearmoney==1}">
									<option value="0">年度金额≤50万</option>
									<option value="1" selected="selected">年度金额＞50万</option>
								</c:if>
							</select>
						</c:if>
						<!--以项目向下采购合同-->
						<c:if test="${subtype.vcTypeNo=='sproperty'}">
							<select name="nYearmoney">
								<c:if test="${contract.nYearmoney==0}">
									<option value="0" selected="selected">年度金额≤20万</option>
									<option value="1">年度金额＞20万</option>
								</c:if>
								<c:if test="${contract.nYearmoney==1}">
									<option value="0">年度金额≤20万</option>
									<option value="1" selected="selected">年度金额＞20万</option>
								</c:if>
							</select>
						</c:if>
						<!--其他类合同-->
						<c:if test="${subtype.vcTypeNo=='sother'}">
							<select name="nExpect">
								<c:if test="${contract.nExpect==0}">
									<option value="0" selected="selected">预算内</option>
									<option value="1">预算外</option>
								</c:if>
								<c:if test="${contract.nExpect==1}">
									<option value="0">预算内</option>
									<option value="1" selected="selected">预算外</option>
								</c:if>
							</select>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>合同生效日期</td>
					<td><input type="text"  name="start" style="width: 120px" value="<c:if test="${start!='null'}">${contract.dtStart}</c:if>" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						<img src="./My97DatePicker/skin/datePicker.gif" width="24" height="33" align="absmiddle" style="cursor:pointer" onClick="WdatePicker({el:'d11',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
					<td>终止日期</td>
					<td><input type="text"  name="end"  value="<c:if test="${end!='null'}">${contract.dtEnd}</c:if>" style="width: 120px" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						<img src="./My97DatePicker/skin/datePicker.gif" width="24" height="33" align="absmiddle" style="cursor:pointer" onClick="WdatePicker({el:'d12',startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
				</tr>
				<tr>
					<td>签约类别</td>
					<td >
						<c:if test="${contract.nNew==null}">
							新签<input type="radio" name="nNew" value="0">&nbsp;&nbsp;
							续签<input type="radio" name="nNew" value="1">
						</c:if>
						<c:if test="${contract.nNew==0}">
							新签<input type="radio" name="nNew" value="0" checked="checked">&nbsp;&nbsp;
							续签<input type="radio" name="nNew" value="1">
						</c:if>
						<c:if test="${contract.nNew==1}">
							新签<input type="radio" name="nNew" value="0" >&nbsp;&nbsp;
							续签<input type="radio" name="nNew" value="1" checked="checked">
						</c:if>
					</td>
					<td>紧急程度</td>
					<td >
						<c:if test="${contract.nEmergency==null}">
							正常<input type="radio" name="nEmergency" value="0">&nbsp;&nbsp;
							紧急<input type="radio" name="nEmergency" value="1">
						</c:if>
						<c:if test="${contract.nEmergency==0}">
							正常<input type="radio" name="nEmergency" value="0" checked="checked">&nbsp;&nbsp;
							紧急<input type="radio" name="nEmergency" value="1">
						</c:if>
						<c:if test="${contract.nEmergency==1}">
							正常<input type="radio" name="nEmergency" value="0">&nbsp;&nbsp;
							紧急<input type="radio" name="nEmergency" value="1" checked="checked">
						</c:if>
					</td>
				</tr>
				<tr >
					<td>总收入</td>
					<td>总成本</td>
					<td>总运量</td>
					<td>毛利率</td>
				</tr>
				<tr >
					<td><input type="text" name="nTotalmoney" value="${contract.nTotalmoney}"/></td>
					<td><input type="text" name="nTotalprice" value="${contract.nTotalprice}"/></td>
					<td><input type="text"  name="nTotalnum" value="${contract.nTotalnum}"/></td>
					<td><input type="text"  name="nRate" value="${contract.nRate}"/></td>
				</tr>
				<tr>
					<td >文件上传</td>
					<td colspan="3" style="text-align: left;height: 40px;">
						 <div border="1px solid black">
							<input type="file" name="files" style="width: 200px"/>&nbsp;&nbsp;
							<input type="file" name="files" style="width: 200px"/>&nbsp;&nbsp;
							<img src="<%=path %>/img/add.png" onclick="addBtn(this)" style="width: 25px;heigth:25px;vertical-align:middle">&nbsp;&nbsp;
							<img src="<%=path %>/img/bin.png" onclick="delBtn(this)" style="width: 25px;heigth:25px;vertical-align:middle">
						 </div>
						
						
						<!--<input type="file" name="files" /><br/>
						<input type="file" name="files" />
						<input type="file" name="files" />-->
					</td>
				</tr>
				<tr id="td1">
					<td colspan="4">合同内容及相关要求</td>
				</tr>
				<tr style="height:300px;">
					<td colspan="4">
						<textarea rows="15" cols="100" name="vcContent" padding="0" id="vcContent">
							${contract.vcContent}
						</textarea>
						 <script type="text/javascript">
						CKEDITOR.replace('vcContent');
						</script>
					</td>
				</tr>
				<tr>
					<td colspan="4"><input type="button" value="提交" onclick="submitForm()"
					 style="background:#ff6600;width: 100px;align:center"/></td>
				</tr>
			</table>
			<!-- id -->
			<input type="hidden" name="lineid" value="${contract.lineid}"/>
			<input type="hidden" name="iUser" value="${contract.iUser}"/>
			<input type="hidden" name="nEnable" value="${contract.nEnable}"/>
			<input type="hidden" name="dtCreate" value="${contract.dtCreate}"/>
			<input type="hidden" name="nState" value="${contract.nState}"/>
		</form>
	</div>
</body>
</html>
