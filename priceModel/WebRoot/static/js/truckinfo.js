$(document).ready(function() {
	$("#truck").datagrid({
		loadMsg : '数据加载中....',
		title : '商品车信息一览表',
		iconCls : 'icon-edit',
		width : 1000,
		height : 530,
		url : "getTruckInfo2.do",
		nowrap : false,
		striped : true,
		collapsible : true,
		//sortName : 'id',
		//sortOrder : 'asc',
		remoteSort : false,
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		columns : [ [ {
			title : '基本信息',
			colspan : 5
		},

		], [ {
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
		}
		
		] ],

		toolbar : [ {
			id : 'btnadd5',
			text : '新增拖车',
			iconCls : 'icon-add',
			handler : function() {
				addTruckInfo();
			}
		}, '-', {
			id : 'btncut5',
			text : '删除',
			iconCls : 'icon-cancel',
			handler : function() {
				deleteTruckInfo();
			}
		}, '-', {
			id : 'btnstart5',
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				editTruckInfo();
			}
		} ]
	});
	var p = $('#truck').datagrid('getPager');
	if (p) {
		$(p).pagination({
			onBeforeRefresh : function() {
				$("#truck").datagrid('reload');
			}
		});
	}

});
//新增
function addTruckInfo(){
	$('#truckdiv').dialog('open').dialog('setTitle', '新增线路');
	$('#truckfm').form('clear');
}


//保存
function saveTruck() {
	$.messager.progress();
	$('#truckfm').form(
			'submit',
			{

				url : "saveTruck.do",
				onSubmit : function() {
					var isValid = $("#truckfm").form('enableValidation').form(
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

						$('#truckdiv').dialog('close');
						$("#truck").datagrid('reload');
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			}

	);
}
//编辑
function editTruckInfo(){

		  var row = $('#truck').datagrid('getSelected');
	      if (row){
	          $('#truckdiv').dialog('open').dialog('setTitle','编辑商品车基本信息');
	          $('#truckfm').form('load',row);
	         // url = 'update_user.php?id='+row.id;
	      }
	
}
//删除

function deleteTruckInfo(){
	var row = $('#truck').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm', '确认需要删除 吗?', function(r) {
			if (r) {
				$.post('deleteTruck.do', {
					id : row.id
				}, function(result) {
					if (result.isSuccess) {
						$.messager.show({ // show error message
							title : '提示',
							msg :"删除成功"
						});
						$("#truck").datagrid('reload'); // reload the user data
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



