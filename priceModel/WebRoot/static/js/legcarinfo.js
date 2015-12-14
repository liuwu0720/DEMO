$(document).ready(function() {
			$('#legcar').combogrid(
					{
						panelWidth : 650,
						value : '请选择线路',
						title : '线路信息一览表',
						idField : 'id',
						// textField : 'origin',
						url : "getLegInfo.do?type=" + 2,
						rownumbers : true,
						columns : [ [ {
							field : 'origin',
							title : '出发地',
							width : 120
						}, {
							field : 'destination',
							title : '目的地',
							width : 120
						}, {
							field : 'incomeDistance',
							title : '收入里程',
							width : 120
						}, {
							field : 'actualDistance',
							title : '实际里程',
							width : 120
						}, {
							field : 'emptyDistance',
							title : '空载里程',
							width : 120
						}, {
							field : 'days',
							title : '天数',
							width : 120
						} ] ],
						onClickRow : function(rowIndex, rowData) {
							var temp = $.trim(rowData.origin) + " --- "
									+ $.trim(rowData.destination);
							// $('#legcar').combogrid('setValue', temp);
							//$('#legcar').combogrid('clear');
							$('#legcar').combogrid('setValue', temp);
							var legId = rowData.id;
							var origin = rowData.origin;
							var destination = rowData.destination;
							//loadCarInfo(legId,origin,destination);
							var jsonStr = {};
							jsonStr.legId = legId;
							$("#legcar3").datagrid('load',jsonStr);
							$(".panel-title").each(function(){
								$(this).html(origin+"--"+destination+"对应的商品车属性");
							});
							
						}
					});
			
			$("#legcar3").datagrid({

				url : "getCarByLeg.do",
				title :  "对应的商品车属性",
				iconCls : 'icon-edit',
				width : 1000,
				height : 530,
				striped : true,
				pagination : true,
				rownumbers : true,
				singleSelect : true,
				columns : [ [ {
					title : '基本信息',
					colspan : 4
				}, {
					title : '与线路相关信息',
					colspan : 2
				}, ], [ {
					field : 'CARNAME',
					title : '商品车名称',
					width : 120
				}, {
					field : 'WEIGHT',
					title : '重量(单位：吨)',
					width : 120
				}, {
					field : 'LENGTH',
					title : '长度(单位：米)',
					width : 120
				},

				{
					field : 'INCOMEPRICE',
					title : '收入',
					width : 120
				}, {
					field : 'VENDORCOST',
					title : '当前采购支出',
					width : 120
				}, {
					field : 'RATIO',
					title : '出现概率',
					width : 120
				}

				] ],

				toolbar : [ {
					id : 'btnadd2',
					text : '新增商品车',
					iconCls : 'icon-add',
					handler : function() {
						addCarInfoJDBC();
					}
				}, '-', {
					id : 'btncut2',
					text : '删除',
					iconCls : 'icon-cancel',
					handler : function() {
						deleteCarInfoJDBC();
					}
				}, '-', {
					id : 'btnstart2',
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						editCarInfoJDBC();
					}
				} ]
			});

		});


// 编辑
function editCarInfoJDBC() {
	var row = $('#legcar3').datagrid('getSelected');
	if (row) {
		$('#legcardiv').dialog('open').dialog('setTitle', '编辑商品车与线路相关信息');
		$('#legcarfm').form('load', row);
	}
}
// 编辑保存
function editCarInfoSave() {
	$.messager.progress();
	$('#legcarfm').form(
			'submit',
			{

				url : "editSaveCar.do",
				onSubmit : function() {
					var isValid = $("#legcarfm").form('enableValidation').form(
							'validate');

					if (!isValid) {
						$.messager.progress('close'); // 如果表单是无效的则隐藏进度条
					}
					return isValid; // 返回false终止表单提交
				},
				success : function(data) {
					var data = eval('(' + data + ')'); // change the JSON
					$.messager.show({ // show error message
						title : '提示',
						msg : "保存成功"
					});
					if (data.isSuccess) {

						$('#legcardiv').dialog('close');
						$("#legcar3").datagrid('reload');
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			}

	);
}

// 删除
function deleteCarInfoJDBC() {
	var row = $('#legcar3').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm', '确认需要删除 吗?', function(r) {
			if (r) {
				$.post('delLegCarInfo.do', {
					id : row.ID
				}, function(result) {
					if (result.isSuccess) {
						$.messager.show({ // show error message
							title : '提示',
							msg : "删除成功"
						});
						$("#legcar3").datagrid('reload'); // reload the user
															// data
					} else {
						$.messager.show({ // show error message
							title : 'Error',
							msg : "删除失败！"
						});
					}
				}, 'json');
			}
		});
	}
}

// 新增
function addCarInfoJDBC() {
	$('#legcardiv2').dialog('open').dialog('setTitle', '新增线路');
	$('#ccc').combobox({
		url : 'selCarInfo.do',
		valueField : 'id',
		textField : 'carname',
		onSelect : function(rec) {
			var url = "getMoreCarInfo.do?carId=" + rec.id
		}
	});

}
// 新增保存
function addCarInfoSave() {
	$.messager.progress();
	$('#legcarfm2').form(
			'submit',
			{

				url : "addSaveCar.do",
				onSubmit : function() {
					var isValid = $("#legcarfm2").form('enableValidation')
							.form('validate');

					if (!isValid) {
						$.messager.progress('close'); // 如果表单是无效的则隐藏进度条
					}
					return isValid; // 返回false终止表单提交
				},
				success : function(data) {
					var data = eval('(' + data + ')'); // change the JSON
					$.messager.show({ // show error message
						title : '提示',
						msg : "保存成功"
					});
					if (data.isSuccess) {

						$('#legcardiv2').dialog('close');
						$("#legcar3").datagrid('reload');
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			}

	);
}
