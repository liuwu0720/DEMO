$(document).ready(function() {
	$("#test").datagrid({
		loadMsg : '数据加载中....',
		title : '线路信息一览表',
		iconCls : 'icon-edit',
		width : 1000,
		height : 530,
		url : "getLegInfo.do",
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
			colspan : 6
		},

		], [ {
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

		toolbar : [ {
			id : 'btnadd4',
			text : '新增线路',
			iconCls : 'icon-add',
			handler : function() {
				openDialog_add();
			}
		}, '-', {
			id : 'btncut4',
			text : '删除线路',
			iconCls : 'icon-cancel',
			handler : function() {
				deleteLegInfo();
			}
		}, '-', {
			id : 'btnstart4',
			text : '编辑线路',
			iconCls : 'icon-edit',
			handler : function() {
				editLegInfo();
			}
		} ]
	});
	var p = $('#test').datagrid('getPager');
	if (p) {
		$(p).pagination({
			onBeforeRefresh : function() {
				$("#test").datagrid('reload');
			}
		});
	}

});

function resize() {
	$('#test').datagrid('resize', {
		width : 700,
		height : 400
	});
}


//编辑线路
function editLegInfo() {
	  var row = $('#test').datagrid('getSelected');
      if (row){
          $('#leg').dialog('open').dialog('setTitle','编辑线路');
          $('#legfm').form('load',row);
         // url = 'update_user.php?id='+row.id;
      }
}
/**
function getSelections() {
	var ids = [];
	var rows = $('#test').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i].code);
	}
	alert(ids.join(':'));
}
function clearSelections() {
	$('#test').datagrid('clearSelections');
}
function selectRow() {
	$('#test').datagrid('selectRow', 2);
}
function selectRecord() {
	$('#test').datagrid('selectRecord', '002');
}
function unselectRow() {
	$('#test').datagrid('unselectRow', 2);
}
function mergeCells() {
	$('#test').datagrid('mergeCells', {
		index : 2,
		field : 'addr',
		rowspan : 2,
		colspan : 2
	});
}
*/
// 新增线路
function openDialog_add() {
	$('#leg').dialog('open').dialog('setTitle', '新增线路');
	$('#legfm').form('clear');
}

// 保存线路
function saveLeg() {
	$.messager.progress();
	$('#legfm').form(
			'submit',
			{

				url : "saveLeg.do",
				onSubmit : function() {
					var isValid = $("#legfm").form('enableValidation').form(
							'validate');
					var isva = $("input[name='incomeDistance']").val();
				
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

						$('#leg').dialog('close');
						$("#test").datagrid('reload');
					}
					$.messager.progress('close'); // 如果提交成功则隐藏进度条

				}

			}

	);
}
//删除线路
function deleteLegInfo() {
	var row = $('#test').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm', '确认需要删除这条线路?', function(r) {
			if (r) {
				$.post('deleteLeg.do', {
					id : row.id
				}, function(result) {
					if (result.isSuccess) {
						$.messager.show({ // show error message
							title : '提示',
							msg :"删除成功"
						});
						$("#test").datagrid('reload'); // reload the user data
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
