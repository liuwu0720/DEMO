<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'travel_table.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">



//差旅费添加按钮
var index = '${fn:length(travelfees.travelfees)}'-1;
function addBtntravel(obj){
	console.log(index);
	index ++;	
	//$(obj).parent().parent().clone(true).insertAfter($(obj).parent().parent());
	
	var temp1 = temp.replace(/\[0]/g,'['+index+']');
	
	$("#travel_tbody1").append(temp1);
}
//差旅费按钮删除
function delBtntravel(obj){
	if(index>0){
		index --;
	}
	
	var len=$(obj).parent().parent().parent().children().length;
	if(len>2){
		var div=$(obj).parent().parent().parent().children().last();
		//alert(div.html());
		div.remove();
	}

	}
</script>
</head>

<body>
	<table class="table">
		<tbody id="travel_tbody1">
			<tr style="font-size: 5;height: 20px;">
				<td>开始日期</td>
				<td>到达日期</td>
				<td>交通工具</td>
				<td>交通费单据张数</td>
				<td>交通费金额</td>
				<td>出差地点</td>
				<td>出差天数</td>
				<td>出差补贴</td>
				<td>住宿费金额</td>
				<td>住宿费单据张数</td>
				<td>杂费单据张数</td>
				<td>杂费</td>
				
				<td style="width: 35px;">操作</td>
			</tr>

			<c:forEach items="${travelfees.travelfees }" var="t"
				varStatus="vstatus">
				<tr id="travel_tr2">
					<td><input type="text"
						name="travelfees[${vstatus.index}].dtStart" value="${t.dtStart }"
						style="width: 100px"
						onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" /></td>

					<td><input type="text"
						name="travelfees[${vstatus.index}].dtEnd" value="${t.dtEnd }"
						style="width: 100px"
						onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" /></td>
					<td><select name="travelfees[${vstatus.index}].vcTool">
							<c:forEach items="${trafficTools}" var="type">
								<c:if test="${type.vcTools==t.vcTool}" var="isTrue">
									<option value="${type.vcTools}" selected="selected">${type.vcTools}</option>
								</c:if>
								<c:if test="${!isTrue}">
									<option value="${type.vcTools}">${type.vcTools}</option>
								</c:if>
							</c:forEach>
					</select></td>
					<td><input type="text" size="5" onkeyup="value=value.replace(/[^\d]/g,'0') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,'0'))"
						name="travelfees[${vstatus.index}].nToollist"
						value="${t.nToollist }"></td>
					<td><input type="text" size="5" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"
						name="travelfees[${vstatus.index}].nTool" value="${t.nTool }" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"></td>
					<td><input type="text" size="5"
						name="travelfees[${vstatus.index}].vcPlace" value="${t.vcPlace }"></td>
					<td><input type="text" size="5"
						name="travelfees[${vstatus.index}].nDays" value="${t.nDays }" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"></td>
					<td><input type="text" size="5"
						name="travelfees[${vstatus.index}].nSubsidies" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"
						value="${t.nSubsidies }"></td>
					<td><input type="text" size="5"
						name="travelfees[${vstatus.index}].nHotel" value="${t.nHotel }" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"></td>
					<td><input type="text" size="5"
						name="travelfees[${vstatus.index}].nHotellist"  onkeyup="value=value.replace(/[^\d]/g,'0') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,'0'))"
						value="${t.nHotellist }"></td>
					<td><input type="text" size="5"
						name="travelfees[${vstatus.index}].nOtherlist"  onkeyup="value=value.replace(/[^\d]/g,'0') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,'0'))"
						value="${t.nOtherlist }"></td>
					<td><input type="text" size="5"
						name="travelfees[${vstatus.index}].nOther" value="${t.nOther }" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"></td>
				
					<td><img src="<%=path%>/img/add.png"
						onclick="addBtntravel(this)"
						style="width: 15px;heigth:15px;vertical-align:middle;cursor: pointer;">
						<img src="<%=path%>/img/bin.png"
						onclick="delBtntravel(this)"
						style="width: 15px;heigth:15px;vertical-align:middle;cursor: pointer;"></td>
				</tr>
			</c:forEach>
		</tbody>
</body>
</html>
