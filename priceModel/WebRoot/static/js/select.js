//选择导入文件时
function importFile() {
	$("#warn").hide();
	$("#fileDiv").slideUp("slow", function() {
		$("#fileDiv").show(2000);
		$("#selfDiv").hide(2000);
	});
};
// 用户自己输入线路
function inputSelf() {
	$("#warn").hide();
	$("#selfDiv").slideUp("slow", function() {
		$("#selfDiv").show(2000);
		$("#fileDiv").hide(2000);
	});
};
// 警告框
function showMessage(message) {
	$("#warn").show();
	$("#warn_p").html(message);
}
// 上传按钮
function uploadFile(excelform) {

	var strFile = excelform.excelfile.value;
	var aDir = strFile.split("\\");
	var strFileType = aDir[aDir.length - 1].split(".")[1];
	if (excelform.excelfile.value == null || excelform.excelfile.value == '') {
		excelform.excelfile.focus();
		showMessage("文件不能为空")
		return false;
	} else if (!((String(strFileType).toLowerCase() == "xlsx") || (String(
			strFileType).toLowerCase() == "xls"))) {
		showMessage("文件格式不对，请导入以.xls或者.xlsx的excel文件！")
		return false;
	} else {
		excelform.submit();
		$("body").mask("正在对上传的文件进行分析！");
		return true;
	}
};

// 查询车辆:用户导入
function serachCar(obj) {
	
	var customerId =  $("#"+obj+"select").val();
	if(customerId == 0){
		showMessage("请选择客户")
	}else{
		$("#warn").hide();
		$("body").unmask();
		$("body").mask("正在查询，请稍等……");
		right.location.href = 'searchcar.do?legId=' + obj+"&&customerId="+customerId;
	}
	
}

// 确认将此线路加入空载:用户导入
function setLegEmptly(obj) {
	var user = $("#smUserNameId").val();
	
	$.prompt("确认将这条线路当作空载吗?", {
		title : "提示！",
		buttons : {
			"Yes" : true,
			"No" : false
		},
		submit : function(e, v, m, f) {
			// use e.preventDefault() to prevent closing when needed or return
			// false.
			e.preventDefault();
			if (v) {
				$.prompt.close();
				$("body").mask("正在验证所有线路，请稍等……");
				window.location.href = "setEmtly.do?leg=" + obj+"&&user="+user;
			} else {
				$.prompt.close();

			}

		}
	});
}
// 确认将此线路加入空载:用户输入
function setLegEmptly2(obj) {
	var user = $("#smUserNameId").val();
	$.prompt("确认将这条线路当作空载吗?", {
		title : "提示！",
		buttons : {
			"Yes" : true,
			"No" : false
		},
		submit : function(e, v, m, f) {
			// use e.preventDefault() to prevent closing when needed or return
			// false.
			e.preventDefault();
			if (v) {
				$.prompt.close();
				$("body").mask("正在验证所有线路，请稍等……");
				window.location.href = "setEmtly2.do?leg=" + obj+"&&user="+user;;
			} else {
				$.prompt.close();

			}

		}
	});
}
// 取消将此线路加入空载:导入文件情况
function cancelLeg(obj) {
	// window.location.href="setEmtly.do?leg="+obj;
	var user = $("#smUserNameId").val();
	$.prompt("确认删除此条线路吗?", {
		title : "提示！",
		buttons : {
			"Yes" : true,
			"No" : false
		},
		submit : function(e, v, m, f) {
			// use e.preventDefault() to prevent closing when needed or return
			// false.
			e.preventDefault();
			if (v) {
				$.prompt.close();
				window.location.href = "emtlydelete.do?leg=" + obj+"&&user="+user;
				$("body").mask("正在删除，请稍等……");
			} else {
				$.prompt.close();

			}

		}
	});
}
// 取消将此线路加入空载:用户输入线路情况
function cancelLeg2(obj) {
	var user = $("#smUserNameId").val();
	$.prompt("确认删除此条线路吗?", {
		title : "提示！",
		buttons : {
			"Yes" : true,
			"No" : false
		},
		submit : function(e, v, m, f) {
			// use e.preventDefault() to prevent closing when needed or return
			// false.
			e.preventDefault();
			if (v) {
				$.prompt.close();
				window.location.href = "emtlydelete2.do?leg=" + obj+"&&user="+user;
				$("body").mask("正在删除，请稍等……");
			} else {
				$.prompt.close();

			}

		}
	});
}

// 拖车ajax取值
function changeForm(obj) {
	if (parseInt(obj) !== -1) {

		$.post("getTruck.do", {
			"truckId" : obj
		}, function(data) {
			$("#truck_tr").html(
					"<td>" + data.loadWeight + "</td>" + "<td>" + data.length1
							+ "</td>" + "<td>" + data.length2 + "</td>"
							+ "<td>" + data.length3 + "</td>" + "<td>"
							+ data.dcweightSelf + "</td>");
		});
	} else {
		$("#truck_tr").html("");
	}
}
// 用户自定义输入时确定按钮
function sureInput(obj) {

	var startcity = $("#startcity").val();
	var startpoint = $("#startpoint").val();
	var endtcity = $("#endtcity").val();
	if (endtcity == '' || startcity == '' || startpoint == '') {
		showMessage("出发地、提车车、目的地不能为空")
	} else {
		document.forms.selfselect.submit();
		$("body").mask("正在对输入的线路进行分析");
	}
}
// 计算类型切换
function caculatetypeSelect() {
	var select = $("select[name='caculatetype']").val();
	if (select.match("totalvenprofit")) {
		$("#span").html("分供方利润/月");
	} else if (select.match("venprofit")) {
		$("#span").html("请输入小于1的小数");
	} else if (select.match("unionprofit")) {
		$("#span").html("请输入小于1的小数");
	}
}

// 开始计算
function check() {
	var select = $("select[name='caculatetype']").val();// 计算方式
	var truck = $("select[name='truck']").val();// 拖车
	var emptlyCost = $("input[name='truckcost']").val();// 空载成本
	var inputValue = $("input[name='venpro']").val();// 用户输入的值
	var patrn = /^[0-9]+\.{0,1}[0-9]{0,2}$/; // 分供方每月利润 只能为小数或者整数
	var patrn2 = /^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/;// 只能为小数

	if (select == 0) {
		showMessage("请选择计算方式！")
		return false;
	} else if (truck == -1) {
		showMessage("请选择计拖车！")
		return false;
	} else if (emptlyCost == null || emptlyCost == "") {
		showMessage("请填写空载成本！")
		return false;
	} else if (inputValue == null || inputValue == "") {
		$("#span").html("不能为空！请填写相关值");
		return false;
	} else if (select == "totalvenprofit" && !patrn.exec(inputValue)) {
		showMessage("格式不符合,只能为整数或小数！")
		return false;
	} else if (select == "venprofit" && !patrn2.exec(inputValue)) {
		showMessage("格式不符合,只能为小数！")
		return false;
	} else if (select == "unionprofit" && !patrn2.exec(inputValue)) {
		showMessage("格式不符合,只能为小数！")
		return false;
	}

	else {

		$("body").mask("正在计算...");
		document.all.caculate.submit();
	}

}

// 演示
function show() {
	$("#paneldiv").hide();
	$("#showdiv").show();

	var tourSubmitFunc = function(e, v, m, f) {
		if (v === -1) {
			$.prompt.prevState();
			return false;
		} else if (v === 1) {
			$.prompt.nextState();
			return false;
		}else if (v === 2) {
			$("#paneldiv").show();
			$("#showdiv").hide();
		}
	}, tourStates = [
			{
				title : '选择输入线路方式',
				html : '这里有两种方式输入线路，一、导入符合要求的excel文件；二、自己输入线路',
				buttons : {
					下一步 : 1
				},
				focus : 0,
				position : {
					container : '#typeradio1',
					x : 200,
					y : 60,
					width : 300,
					arrow : 'lt'
				},
				submit : tourSubmitFunc
			},
			{
				title : '导入文件验证无误后显示的线路列表',
				html : '由于计算量较大，目前最多可以导入8条线路计算，如果自己输入最多可以输入6条',
				buttons : {
					上一步 : -1,
					下一步 : 1
				},
				focus : 1,
				position : {
					container : '#selectshow',
					x : 170,
					y : 0,
					width : 300,
					arrow : 'tc'
				},
				submit : tourSubmitFunc
			},
			{
				title : "环线单线根据此字段识别",
				html : '如果该数字相同视为环线，用户输入的线路都当成环线处理',
				buttons : {
					上一步 : -1,
					下一步 : 1
				},
				focus : 1,
				position : {
					container : '#selectshow2',
					x : -10,
					y : -145,
					width : 300,
					arrow : 'bl'
				},
				submit : tourSubmitFunc
			},
			{
				title : '如果线路查不到商品车，请选择是否加入空载？',
				html : '选择 是 则加入空载，选择 否 则删除此线路！',
				buttons : {
					上一步 : -1,
					下一步 : 1
				},
				focus : 1,
				position : {
					container : '#selectshow3',
					x : 80,
					y : 0,
					width : 400,
					arrow : 'lt'
				},
				submit : tourSubmitFunc
			},
			{
				title : '非空载线路，请选择商品车',
				html : '线路对应的商品车信息是根据ERP半年数据查询得出',
				buttons : {
					上一步 : -1,
					下一步 : 1
				},
				focus : 1,
				position : {
					container : '#selectshow4',
					x : -300,
					y : -150,
					width : 400,
					arrow : 'br'
				},
				submit : tourSubmitFunc
			},
			{
				title : '可以删除不想加入计算的线路',
				html : '',
				buttons : {
					上一步 : -1,
					下一步 : 1
				},
				focus : 1,
				position : {
					container : '#selectshow5',
					x : -50,
					y : 40,
					width : 200,
					arrow : 'tc'
				},
				submit : tourSubmitFunc
			},
			{
				title : '选择计算方式',
				html : '总共有3种计算方式：1、分供方利润每月;2、分供方占比;3、中联占比',
				buttons : {
					上一步 : -1,
					下一步 : 1
				},
				focus : 1,
				position : {
					container : '#selectshow6',
					x : 80,
					y : 0,
					width : 400,
					arrow : 'lt'
				},
				submit : tourSubmitFunc
			},
			{
				title : '填写计算值',
				html : '',
				buttons : {
					上一步 : -1,
					下一步 : 1
				},
				focus : 1,
				position : {
					container : '#selectshow7',
					x : 0,
					y : 20,
					width : 200,
					arrow : 'tc'
				},
				submit : tourSubmitFunc
			}
			,
			{
				title : '选择运输车',
				html : '总共有4种运输车选择',
				buttons : {
					上一步 : -1,
					下一步 : 1
				},
				focus : 0,
				position : {
					container : '#selectshow7',
					x : 10,
					y : 20,
					width : 400,
					arrow : 'lt'
				},
				submit : tourSubmitFunc
			},
			{
				title : '填写运输车单公里空载成本',
				html : '',
				buttons : {
					上一步 : -1,
					下一步 : 1
				},
				focus : 1,
				position : {
					container : '#selectshow8',
					x : 10,
					y : 20,
					width : 200,
					arrow : 'tc'
				},
				submit : tourSubmitFunc
			}
			,
			{
				title : '开始计算',
				html : '所有条件验证无误后开始计算',
				buttons : {
					上一步 : -1,
					下一步 : 1
				},
				focus : 0,
				position : {
					container : '#selectshow9',
					x : 40,
					y : 10,
					width : 200,
					arrow : 'lt'
				},
				submit : tourSubmitFunc
			},
			{
				title: '演示结束',
				html: '',
				buttons: { 关闭: 2 },
				focus: 0,
				position: {
					container : '#selectshow9',
					x : 40,
					y : 10,
					width : 200,
					arrow : 'lt' },
				submit: tourSubmitFunc
			}
			];
	$.prompt(tourStates);
}
function hide(){
	$("#paneldiv").show();
	$("#showdiv").hide();
}

//删除所有

function deleteall() {
	var user = $("#smUserNameId").val();
	
	$.prompt("", {
		title : "确定删除所有输入的线路吗？",
		buttons : {
			"Yes" : true,
			"No" : false
		},
		submit : function(e, v, m, f) {
			// use e.preventDefault() to prevent closing when needed or return
			// false.
			e.preventDefault();
			if (v) {
				$.prompt.close();
				$("body").mask("正在删除，请稍等……");
				window.location.href = "deleteall.do?user="+user;
			} else {
				$.prompt.close();

			}

		}
	});
}