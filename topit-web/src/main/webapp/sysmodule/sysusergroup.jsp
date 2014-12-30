<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统用户组</title>
<link rel="stylesheet" type="text/css" href="../css/easyui.css" />
<link rel="stylesheet" type="text/css" href="../css/icon.css" />
<link rel="stylesheet" type="text/css" href="../css/ManageStyle.css" />
<link rel="stylesheet" type="text/css" href="../css/MyCenter.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.marquee.css" />
<script type="text/javascript" src="../js/myjs.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<style type="text/css">
html, body {
	height: 100%;
	overflow: hidden;
}
</style>
<script type="text/javascript">
	$(function() {
		var flag;
		$('#sys_user_group').datagrid({
			idField : 'id',
			title : '用户组管理',
			fit : true,
			height : 450,
			url : 'sysusergroup.do?method=getList',
			fitColumns : true,
			striped : true,
			loadMsg : '数据正在加载，请耐心等待...',
			rownumbers : true,
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 50,
				align : 'center',
			}, {
				field : 'name',
				title : '名称',
				width : 100,
				align : 'center',
			}, {
				field : 'description',
				title : '描述',
				width : 200,
				align : 'center',
			}, {
				field : 'lastRightEditTime',
				title : '权限设置时间',
				width : 120,
				align : 'center',
				formatter: Common.DateFormatter,
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 120,
				align : 'center',
				formatter: Common.DateFormatter,
			}, {
				field : 'usernum',
				title : '用户数',
				width : 50,
			}, {
				field : 'inactive',
				title : '状态',
				width : 50,
			}, {
				field : 'operation',
				title : '操作',
				width : 150,
			} ] ],
			pagination : true,
			pageSize : 5,
			pageList : [ 5, 10, 15, 20, 50 ],

			toolbar : [ {
				text : '新增用户组',
				iconCls : 'icon-add',
				handler : function() {
					flag: 'add';
					$('#sysusergroupdialog').dialog({
						title : '新增用户组'
					});
					$('#sysusergroupform').get(0).reset();
					$('#sysusergroupdialog').dialog('open');
				}
			} ]

		});

		$('#btnSave')
				.click(
						function() {
							if ($('#sysusergroupform').form('validate')) {
								$
										.ajax({
											type : 'post',
											url : flag == 'save' ? '#################sysmodule.do?method=save'
													: '######################sysmodule.do?method=update',
											cache : false,
											data : $('#sysoptionform')
													.serialize(),
											dataType : 'json',
											success : function(result) {
												$('#sysusergroupdialog')
														.dialog('close');
												$('#sys_user_group').datagrid(
														'reload');
												$.messager.show({
													title : result.status,
													msg : result.message
												});
											},
											error : function(result) {
												$.meesager.show({
													title : result.status,
													msg : result.message
												});
											}
										});
							} else {
								$.messager.show({
									title : '提示信息!',
									msg : '数据验证不通过,不能保存!'
								});
							}
						});

		$('#btnCancel').click(function() {
			$('#sysusergroupdialog').dialog('close');
		});
	})
</script>
</head>
<body>
	<%--  ********easyui-layout**********  --%>
	<div id="lay" class="easyui-layout" style="width: 100%; height: 100%">
		<%--  ********中心页面布局************ --%>
		<div region="center">
			<table id="sys_user_group"></table>
		</div>

		<div id="sysusergroupdialog" title="新增模块" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 600px;">
			<form id="sysusergroupform" action="" method="post">
				<input type="hidden" name="id" value="" />
				<table>
					<tr>
						<td><label style="padding-left: 6px;">模块名称:</label></td>
						<td><input type="text" name="name" class="easyui-validatebox"
							required=true missingMessage="模块名称必填!" value="" /></td>
					</tr>
					<tr>
						<td><label style="padding-left: 6px;">模块分类:</label></td>
						<td><input type="text" name="categoryId"
							class="easyui-validatebox" required=true missingMessage="模块分类必填!"
							value="" /></td>
					</tr>
					<tr>
						<td><label style="padding-left: 6px;">模块路径:</label></td>
						<td><input type="text" name="modulePath"
							class="easyui-validatebox" required=true missingMessage="模块路径必填！"
							value="" /></td>
					</tr>
					<tr>
						<td><label style="padding-left: 6px;">模块描述:</label></td>
						<td><input type="text" name="description"
							class="easyui-validatebox" /></td>
					<tr>
						<td><label style="padding-left: 6px;">出生日期:</label></td>
						<td><input id="birthday" style="width: 160px;" type="text"
							name="birthday" value="" /></td>
					</tr>
					<tr align="center">
						<td colspan="2"><a id="btnSave" class="easyui-linkbutton">确定</a>
							<a id="btnCancel" class="easyui-linkbutton">关闭</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>