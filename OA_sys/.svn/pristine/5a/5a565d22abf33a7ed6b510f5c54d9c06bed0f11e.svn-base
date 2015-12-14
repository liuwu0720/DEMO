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

//交通费添加按钮
var index = '${fn:length(feeTraffics.feeTraffics)}'-1;
function addBtnupdate(obj){
	
	index ++;	
	//$(obj).parent().parent().clone(true).insertAfter($(obj).parent().parent());
	
	var temp1 = temp1.replace(/\[0]/g,'['+index+']');
	
	$("#traffic_tbody1").append(temp1);
}
//交通费按钮删除
function delBtnupdate(obj){
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
   <table  class="table">
		<tbody id="traffic_tbody1">
			<tr style="font-size: 5;height: 20px;">
				<td>出发日期</td>
				<td>出发地点</td>
				<td>到达时间</td>
				<td>到达地点</td>
				<td>交通工具</td>
				<td>事由</td>
				<td>报销金额</td>
				<td>出差补贴</td>
				<td>备注</td>
				<td style="width: 35px;">操作</td>
			</tr>
			<c:forEach items="${feeTraffics.feeTraffics }" var="t"
				varStatus="vstatus">
			<tr id="traffic_tr2">
				<td><input type="text" name="feeTraffics[${vstatus.index}].dtStart" style="width: 100px"  value="${t.dtStart }"
					onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
					
				</td>
				<td><input type="text" name="feeTraffics[${vstatus.index}].vcStart"  value="${t.vcStart }" size="8"></td>
				<td><input type="text" name="feeTraffics[${vstatus.index}].dtArrive"  value="${t.dtArrive }" style="width: 100px" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" ></td>
				<td><input type="text" name="feeTraffics[${vstatus.index}].vcArrive"  value="${t.vcArrive }" size="8"></td>
				<td><select name="feeTraffics[${vstatus.index}].vcEmploytriptool">
						<c:forEach items="${trafficTools}" var="type">
								<c:if test="${type.vcTools==t.vcEmploytriptool}" var="isTrue">
									<option value="${type.vcTools}" selected="selected">${type.vcTools}</option>
								</c:if>
								<c:if test="${!isTrue}">
									<option value="${type.vcTools}">${type.vcTools}</option>
								</c:if>
							</c:forEach>
				</select></td>
				<td><input type="text" name="feeTraffics[${vstatus.index}].vcReason"  value="${t.vcReason }" size="8"></td>
				<td><input type="text" name="feeTraffics[${vstatus.index}].nReimburse"  value="${t.nReimburse }" size="8"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"></td>
				<td><input type="text" size="5" name="feeTraffics[${vstatus.index}].nTravel"  value="${t.nTravel }"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"></td>
				<td><input type="text" name="feeTraffics[${vstatus.index}].vcNote"  value="${t.vcNote }"></td>
				<td><img src="<%=path%>/img/add.png"
					onclick="addTrafficBtn(this)"
					style="width: 15px;heigth:15px;vertical-align:middle;cursor: pointer;">
					<img src="<%=path%>/img/bin.png" onclick="delTrafficBtn(this)"
					style="width: 15px;heigth:15px;vertical-align:middle;cursor: pointer;"></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
  </body>
</html>
