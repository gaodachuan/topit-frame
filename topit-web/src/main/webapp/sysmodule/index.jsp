<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模块添加</title>
<link rel="stylesheet" type="text/css" href="../css/easyui.css" />
<link rel="stylesheet" type="text/css" href="../css/icon.css" />
<link rel="stylesheet" type="text/css" href="../css/ManageStyle.css" />
<link rel="stylesheet" type="text/css" href="../css/MyCenter.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.marquee.css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<style type="text/css">
html, body {
	height: 100%;
	overflow: hidden;
}
</style>
<script type="text/javascript">
	$(
			function() {

				var flag;
				$('#sys_module').datagrid({
					idField : 'id', //只要创建数据表格 就必须要加 ifField
					title : '模块维护',
					fit : true,
					height : 450,
					url : 'sysmodule.do?method=getList',
					fitColumns : true,
					striped : true, //隔行变色特性 
					loadMsg : '数据正在加载,请耐心的等待...',
					rownumbers : true,
					columns : [ [ {
						field : 'id',
						title : '模块编号',
						width : 100,
						align : 'center',
					}, {
						field : 'name',
						title : '模块名称',
						width : 100,
					}, {
						field : 'categoryId',
						title : '模块分类',
						width : 100,
						sortable : true
					}, {
						field : 'createTime',
						title : '创建时间',
						width : 100,
						sortable : true
					} ] ],
					pagination : true,
					pageSize : 10,
					pageList : [ 5, 10, 15, 20, 50 ],
					//工具栏
					toolbar : [ {
						text : '新增模块',
						iconCls : 'icon-add',
						handler : function() {
							flag = 'add';
							$('#moduledialog').dialog({
								title : '新增模块'
							});
							$('#moduleform').get(0).reset();
							$('#moduledialog').dialog('open');

						}

					} ]

				});
				$('#btnSave')
						.click(
								function() {
									if ($('#moduleform').form('validate')) {
										$
												.ajax({
													type : 'post',
													url : flag == 'save' ? 'sysmodule.do?method=save'
															: 'sysmodule.do?method=update',
													cache : false,
													data : $('#moduleform')
															.serialize(),
													dataType : 'json',
													success : function(result) {
														$('#moduledialog')
																.dialog('close');
														$('#sys_module')
																.datagrid(
																		'reload');
														$.messager
																.show({
																	title : result.status,
																	msg : result.message
																});
													},
													error : function(result) {
														$.meesager
																.show({
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

				/**
				 * 关闭窗口方法
				 */
				$('#btnCancel').click(function() {
					$('#moduledialog').dialog('close');
				});
			})
</script>
</head>

<body>
	<div id="lay" class="easyui-layout" style="width: 100%; height: 100%">
		<div region="north" title="模块查询" collapsible=false
			style="height: 100px;">

			<form id="mysearch" method="post">
				<table style="width: 100%">
					<tr>
						<td style="width: 25%"><label style="padding-left: 6px;">模块：</label></td>
						<td style="width: 25%"><input id=" " name=" " class="textbox"
							style="width: 80%"></td>
						<td style="width: 25%"><label style="padding-left: 6px;">分类：</label></td>
						<td style="width: 25%"><input name="" class="easyui-combobox"
							url="datadic.do?method=getData" valueField="id" textField="name"
							value="" /></td>
					</tr>
					<tr>
						<td colspan="3" align="right"></td>
						<td align="center"><a href="#" class="easyui-linkbutton"
							data-options="iconcls:'icon-search'" onclick="doSearch(-1)"
							style="width: 90px;">搜索</a></td>
					</tr>

				</table>
			</form>

		</div>
		<div region="center">
			<table id="sys_module"></table>
		</div>

		<div id="moduledialog" title="新增模块" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 600px;">
			<form id="moduleform" action="" method="post">
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
							class="easyui-validatebox" required="true" missingMessage="模块路径必填！"
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
