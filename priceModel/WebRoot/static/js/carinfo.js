$(document).ready(function() {
	$("#car").datagrid({
		loadMsg : '数据加载中....',
		title : '商品车信息一览表',
		iconCls : 'icon-edit',
		width : 1000,
		height : 530,
		url : "getCarInfo.do",
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
			colspan : 4
		},

		], [ {
			field : 'carname',
			title : '商品车名称',
			width : 120
		}, {
			field : 'weight',
			title : '重量（单位：吨）',
			width : 120
		}, {
			field : 'length',
			title : '长度（单位：米）',
			width : 120
		}, {
			field : 'manufacturer',
			title : '厂商',
			width : 120
		}
		
		] ],

		toolbar : [ {
			id : 'btnadd1',
			text : '新增商品车',
			iconCls : 'icon-add',
			handler : function() {
				addCarInfo();
			}
		}, '-', {
			id : 'btncut1',
			text : '删除',
			iconCls : 'icon-cancel',
			handler : function() {
				deleteCarInfo();
			}
		}, '-', {
			id : 'btnstart1',
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				editCarInfo();
			}
		} ]
	});
	var p = $('#car').datagrid('getPager');
	if (p) {
		$(p).pagination({
			onBeforeRefresh : function() {
				$("#car").datagrid('reload');
			}
		});
	}

});



//新增
function addCarInfo() {
	$('#cardiv').dialog('open').dialog('setTitle', '新增线路');
	$('#carfm').form('clear');
}
//保存
function saveCar() {
	$.messager.progress();
	$('#carfm').form(
			'submit',
			{

				url : "saveCar.do",
				onSubmit : function() {
					var isValid = $("#carfm").form('enableValidation').form(
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

						$('#cardiv').dialog('close');
						$("#car").datagrid('reload');
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			}

	);
}
//编辑
function editCarInfo(){

		  var row = $('#car').datagrid('getSelected');
	      if (row){
	          $('#cardiv').dialog('open').dialog('setTitle','编辑商品车基本信息');
	          $('#carfm').form('load',row);
	         // url = 'update_user.php?id='+row.id;
	      }
	
}
//删除

function deleteCarInfo(){
	var row = $('#car').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm', '确认需要删除 吗?', function(r) {
			if (r) {
				$.post('deleteCar.do', {
					id : row.id
				}, function(result) {
					if (result.isSuccess) {
						$.messager.show({ // show error message
							title : '提示',
							msg :"删除成功"
						});
						$("#car").datagrid('reload'); // reload the user data
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

