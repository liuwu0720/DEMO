$(document).ready(
		function() {

			$('#legtruck').combogrid(
					{
						panelWidth : 550,
						value : '请选择拖车',
						title : '拖车信息一览表',
						idField : 'id',
						// textField : 'origin',
						url : "getTruckInfo2.do?type=" + 2,
						columns : [ [ {
							field : 'trucktype',
							title : '拖车名称',
							width : 120
						}, {
							field : 'length1',
							title : '第一层长度（单位：米）',
							width : 150
						}, {
							field : 'length2',
							title : '第二层长度（单位：米）',
							width : 150
						}, {
							field : 'length2',
							title : '第三层长度（单位：米）',
							width : 150
						}, {
							field : 'loadingWeight',
							title : '最大载重（单位：吨）',
							width : 150
						} ] ],
						onClickRow : function(rowIndex, rowData) {
							var temp = $.trim(rowData.trucktype) ;
							$('#legtruck').combogrid('setValue', temp);
							var truckId = rowData.id;
							var truckName = rowData.trucktype;
							var jsonStr = {};
							jsonStr.truckId = truckId;
							$("#legtruck3").datagrid('load',jsonStr);
							$(".panel-title").each(function(){
								$(this).html(truckName+"对应的所有线路的属性");
							});
							
						}
					});

			//加载线路详情信息
			$("#legtruck3").datagrid({
				loadMsg : '数据加载中....',
				title : "对应的所有线路的属性",
				iconCls : 'icon-edit',
				width : 1000,
				height : 530,
				url : "getLegByTruck.do",
				nowrap : false,
				striped : true,
				collapsible : true,
				/*sortName : 'ID',
				sortOrder : 'asc',*/
				
				remoteSort : false,
				pagination : true,
				rownumbers : true,
				singleSelect : true,
				columns : [ [ {
					title : '基本信息',
					colspan : 5
				},
				{
					title : '与拖车相关信息',
					colspan : 2
				},
				], [  {
					field : 'ORIGIN',
					title : '始发地',
					width : 120
				}, {
					field : 'DESTINATION',
					title : '目的地',
					width : 120
				}, {
					field : 'INCOME_DISTANCE',
					title : '收入距离',
					width : 120
				},	
				
				   {
					field : 'ACTUAL_DISTANCE',
					title : '实际距离',
					width : 120
				}, {
					field : 'EMPTY_DISTANCE',
					title : '空载距离',
					width : 120
				}
				, {
					field : 'FULLCOST',
					title : '每公里满载成本',
					width : 120
				}, {
					field : 'EMPTCOST',
					title : '每公里空载成本',
					width : 120
				}
				] ],
				
				
				toolbar : [ {
					id : 'btnadd2',
					text : '新增线路',
					iconCls : 'icon-add',
					handler : function() {
						addTruckInfoJDBC();
					}
				}, '-', {
					id : 'btncut2',
					text : '删除',
					iconCls : 'icon-cancel',
					handler : function() {
						deleteTruckInfoJDBC();
					}
				}, '-', {
					id : 'btnstart2',
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						editTruckInfoJDBC();
					}
				} ]
			});
		});


//编辑
function editTruckInfoJDBC(){
	  var row = $('#legtruck3').datagrid('getSelected');
      if (row){
          $('#legtruckdiv').dialog('open').dialog('setTitle','编辑线路与拖车相关信息');
          $('#legtruckfm').form('load',row);
      }
}

//编辑保存
function editTruckInfoSave(){

	$.messager.progress();
	$('#legtruckfm').form(
			'submit',
			{

				url : "editSaveTruck.do",
				onSubmit : function() {
					var isValid = $("#legtruckfm").form('enableValidation').form(
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
						msg :"保存成功"
					});
					if (data.isSuccess) {

						$('#legtruckdiv').dialog('close');
						$("#legtruck3").datagrid('reload');
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			}

	);
}
//删除
function deleteTruckInfoJDBC(){
	var row = $('#legtruck3').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm', '确认需要删除 吗?', function(r) {
			if (r) {
				$.post('delLegTruckInfo.do', {
					id : row.ID
				}, function(result) {
					if (result.isSuccess) {
						$.messager.show({ // show error message
							title : '提示',
							msg :"删除成功"
						});
						$("#legtruck3").datagrid('reload'); // reload the user data
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

//新增
function addTruckInfoJDBC(){
	$('#legtruckdiv2').dialog('open').dialog('setTitle', '新增线路');
	$('#bbb').combobox({
		url : 'selLegInfo.do',
		valueField : 'id',
		textField : 'legname'
		/*onSelect : function(rec) {
			var url = "getMoreCarInfo.do?carId=" + rec.id
		}*/
	});
}
//新增保存
function addTruckInfoSave() {
	$.messager.progress();
	$('#legtruckfm2').form(
			'submit',
			{

				url : "addSaveLeg.do",
				onSubmit : function() {
					var isValid = $("#legtruckfm2").form('enableValidation')
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

						$('#legtruckdiv2').dialog('close');
						$("#legtruck3").datagrid('reload');
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			}

	);
}





