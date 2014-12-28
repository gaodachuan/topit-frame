<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common.jspf"%>
<title>系统用户组</title>
<style type="text/css">
html, body {
	height: 100%;
	overflow: hidden;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/treegrid_dnd.js"></script>
<script type="text/javascript">
	var flag = '';
	var groupId;
	$(function() {
		$('#sys_menu_manage')
				.datagrid(
						{
							idField : 'id',
							title : '菜单管理',
							url : 'getSysMenus.do',
							fitColumns : true,
							striped : true,
							loadMsg : '数据正在加载，请耐心等待...',
							rownumbers : true,
							frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
							{
								field : 'id',
								title : '菜单编号',
								width : 100,
								align : 'center',
							}, {
								field : 'name',
								title : '菜单名称',
								width : 100,
								align : 'center',
							},

							] ],
							columns : [ [

									{
										field : 'description',
										title : '菜单描述',
										width : 200,
										align : 'center',
									},
									{
										field : 'systemid',
										title : '所属子系统',
										width : 50,
										align : 'center',
									},
									{
										field : 'creator',
										title : '创建人',
										width : 50,
										align : 'center',
									},
									{
										field : 'createtime',
										title : '创建时间',
										width : 120,
										align : 'center',
										formatter : Common.DateFormatter,
									},
									{
										field : 'lastedittime',
										title : '最后修改时间',
										width : 120,
										align : 'center',
										formatter : Common.DateFormatter,
									},
									{
										field : 'inactive',
										title : '状态',
										width : 50,
										align : 'center',

										formatter : function(value, record,
												index) {
											var status;
											if (value == 0) {
												status = '<img src=\'${pageContext.request.contextPath}/icons/clear.png\'>禁用';
											} else if (value == 1) {
												status = '<img src=\'${pageContext.request.contextPath}/icons/ok.png\'>正常';
											}
											return status;
										}
									},
									{
										field : 'operation',
										title : '操作',
										width : 150,
										align : 'center',
										formatter : function(value, record,
												index) {
											var authorization = '设置菜单项';
											var status = '';
											var del = '删除';
											if (record.inactive == 0) {
												status = '启用';
											} else if (record.inactive == 1) {
												status = '停用';
											}

											return '<a href=\"javascript:void(0)\" id=\"setAuthorization\" onclick=\"setAuthorization('
													+ record.id
													+ ')\">'
													+ authorization
													+ '</a>&nbsp'
													+ '<a href=\"javascript:void(0)\" id=\"editstatus\" onclick=\"changestatus('
													+ record.id
													+ ')\">'
													+ status + '</a>';
										}
									} ] ],
							singleSelect : true,
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 50 ],

							toolbar : [
									{
										text : '新增菜单',
										iconCls : 'icon-add',
										handler : function() {
											flag = 'save';
											$('#sysMenudialog').dialog({
												title : '新增菜单'
											});
											$('#sysMenuform').get(0).reset();
											$('#sysMenudialog').dialog('open');
										}
									},
									{
										text : '修改菜单',
										iconCls : 'icon-edit',
										handler : function() {
											flag = 'edit';
											var arr = $('#sys_menu_manage')
													.datagrid('getSelections');
											if (arr.length != 1) {
												$.messager.show({
													title : '提示信息',
													msg : '需选择一行进行修改！'
												});
											} else {
												$('#sysMenudialog').dialog({
													title : '修改菜单'
												});
												$('#sysMenudialog').dialog(
														'open');
												$('#sysMenuform').get(0)
														.reset();
												$('#sysMenuform')
														.form(
																'load',
																{
																	id:arr[0].id,
																	name : arr[0].name,
																	systemid : arr[0].systemid,
																	description : arr[0].description
																});

											}
										}
									},
									{
										text : '删除选定菜单',
										iconCls : 'icon-remove',
										handler : function() {
											var arr = $('#sys_menu_manage')
													.datagrid('getSelections');
											if (!arr.length ==1) {
												$.messager.show({
													title : '提示信息',
													msg : '请选择一行数据进行删除！'
												});
											} else {
												$.messager
														.confirm(
																'确认对话框',
																'您确定删除选定记录吗？',
																function(r) {
																	if (r) {
											
																		$
																				.ajax({
																					type : 'post',
																					url :'deleteMenu.do',
																					data:{id:arr[0].id},
																					success : function(
																							result) {
																						//刷新
																						$(
																								'#sys_menu_manage')
																								.datagrid(
																										'reload');
																						//清空所选择的行
																						$(
																								'#sys_menu_manage')
																								.datagrid(
																										'unselectAll');
																						if (result.errorCode == 0) {
																							$.messager
																									.show({
																										title : '提示信息',
																										msg : result.errorDetail
																									});
																						} else {
																							$.messager
																									.show({
																										title : '提示信息',
																										msg : result.errorDetail
																									});
																						}
																					}
																				});
																	}
																});
											}
										}
									} ]
						});

		$('#btnSave')
				.click(
						function() {
							if ($('#sysMenuform').form('validate')) {
								$
										.ajax({
											type : 'post',
											url : flag == 'save' ? 'createMenu.do'
													: 'updateMenu.do',
											cache : false,
											data : $('#sysMenuform')
													.serialize(),
											dataType : 'json',
											success : function(result) {
												$('#sysMenudialog').dialog(
														'close');
												//刷新
												$('#sys_menu_manage').datagrid(
														'reload');
												if (result.errorCode == 0) {
													$.messager
															.show({
																title : '提示信息',
																msg : result.errorDetail
															});

												} else {
													$.messager
															.show({
																title : '提示信息',
																msg : result.errorDetail
															});
												}
												//清空所选择的行
												$('#sys_menu_manage').datagrid(
														'unselectAll');
												//刷新
												$('#sys_menu_manage')
														.datagrid(
																'reload');
												$('#sysMenudialog').dialog('close');
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
			$('#sysMenudialog').dialog('close');
		});
	});

	//更改菜单禁用状态
	function changestatus(id) {

		$.messager
				.confirm(
						'提示',
						'您确认更改用户组状态吗？',
						function(r) {
							if (r) {
								$.ajax({
											type : 'post',
											url : 'changestatus.do',
											data:{id:id},		
											success : function(result) {
												if (result.errorCode == 0) {
													$.messager
															.show({
																title : '提示信息',
																msg : result.errorDetail
															});
												} else {
													$.messager
															.show({
																title : '提示信息',
																msg : result.errorDetail
															});
												}
												//刷新
												$('#sys_menu_manage').datagrid(
														'reload');
												//清空所选择的行
												$('#sys_menu_manage').datagrid(
														'unselectAll');
											}
										});
							}
						});
	}

	//设置菜单
	function setAuthorization(id) {
		$("#addMenuItemUI").attr("src","addMenus.do?id="+id);
		//打开对话框
		$('#authorization').dialog('open');
	}
</script>
</head>
<body>
	<%--  ********easyui-layout**********  --%>
	<div id="lay" class="easyui-layout" style="width: 100%; height: 100%">
		<%--  ********中心页面布局************ --%>
		<div region="center">
			<table fit=true id="sys_menu_manage"></table>
		</div>
		<div id="sysMenudialog" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 40%; height: 45%;">
			<div id="dialogtab" plain=true class="easyui-tabs" fit=false style="padding: 3%;height:100%">
				<div title="菜单信息" align="center" style="width: 100%;" fit=true>
				<form id="sysMenuform" style="margin-top: 20px; margin-bottom: 20px">
					<input type="hidden" name="id" value="-1" />
					 <table>
						<tr align="center">
							<td style="width: 30%;"><label for="name"
								style="padding-left: 6px;">菜单名称:</label> <input
								class="easyui-validatebox" type="text" name="name"
								style="width: 182px;" required=true missingMessage="用户组名称必填！" />
							</td>
						</tr>
						<tr align="center">
							<td style="width: 30%;"><label for="systemid"
								style="padding-left: 6px;">系统编号:</label> 
								<select id="systemid"
								class="easyui-combobox " name="systemid" url=""
								panelHeight="100" valueField="name" textField="name" value=""
								style="width: 188px; height: auto">
							   </select>
							</td>
						</tr>
						<tr align="center">
							<td style="width: 30%;"><label for="description"
								style="padding-left: 6px;">功能描述:</label> 
								<textarea rowspan="4"
									name="description" cols="21" rows="1">
									</textarea>
							</td>

						</tr>
						<tr align="center">
							<td><a id="btnSave" class="easyui-linkbutton">确定</a>
								<a id="btnCancel" class="easyui-linkbutton">关闭</a></td>
						</tr>
					 </table>
				</form>
				</div>
			</div>
		</div>

		<!-- ######菜单设置面板######## -->
		<div id="authorization" class="easyui-dialog" title="菜单项设置"
			style="width: 80%; height: 550px;"
			data-options="iconCls:'icon-man',closed:true,modal:true">
			<div id="authorization_lay" class="easyui-layout"
				style="width: 100%; height: 100%">
				
				<iframe id="addMenuItemUI" align="center" width="100%" height="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
				</div>
		</div>
	</div>
</body>
</html>