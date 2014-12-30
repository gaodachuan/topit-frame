<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.housemanagersystem.util.Constant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>资费管理</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript">
	$(function() {
		var flag;
		$('#chargeIntem_data')
				.datagrid(
						{
							idField : 'id', //只要创建数据表格 就必须要加 ifField
							title : '资费项目信息',
							fit : true,
							height : 600,
							url : 'getChargeitems.do',
							striped : true, //隔行变色特性 
							loadMsg : '数据正在加载,请耐心的等待...',
							fitColumns : true,
							rownumbers : true,
							singleSelect : false,
							frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
							{
								field : 'ck',
								checkbox : true
							}, ] ],
							columns : [ [ {
								field : 'id',
								title : '资费编号',
								width : 100,
								align : 'center',
							}, {
								field : 'name',
								title : '资费名称',
								align : 'center',
								width : 100,
							}, {
								field : 'price',
								title : '单价',
								align : 'center',
								width : 100,
							}, {
								field : 'description',
								title : '备注信息',
								align : 'center',
								width : 100,
							} ] ],
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 50 ],
							//工具栏
							toolbar : [
									{
										text : '新增项目',
										iconCls : 'icon-add',
										handler : function() {
											flag = 'save';
											$('#ChargeItemdialog').dialog({
												title : '项目信息'
											});
											$('#ChargeItemform').get(0).reset();
											$('#ChargeItemdialog').dialog(
													'open');
										}

									},
									{
										text : '编辑项目信息',
										iconCls : 'icon-edit',
										handler : function() {
											flag = 'edit';
											var arr = $('#chargeIntem_data')
													.datagrid('getSelections');
											if (arr.length != 1) {
												$.messager.show({
													title : '提示信息!',
													msg : '请选择一行记录进行修改!'
												});
											} else {
												$('#ChargeItemdialog').dialog({
													title : '修改项目'
												});
												$('#ChargeItemform').get(0)
														.reset();
												$('#ChargeItemdialog').dialog(
														'open');
												$('#ChargeItemform')
														.form(
																'load',
																{
																	id : arr[0].id,
																	name : arr[0].name,
																	price : arr[0].price,
																	description : arr[0].description
																});

											}

										}

									},
									{
										text : '删除项目',
										iconCls : 'icon-remove',
										handler : function() {
											flag = 'del';
											var arr = $('#chargeIntem_data')
													.datagrid('getSelections');
											if (arr.length <= 0) {
												$.messager.show({
													title : '提示信息',
													msg : '请选择进行需要删除的记录!'
												});
											} else {
												$.messager
														.confirm(
																'提示信息',
																'确认删除?',
																function(r) {
																	if (r) {
																		var id = '';
																		for (var i = 0; i < arr.length; i++) {
																			id += arr[i].id
																					+ ',';
																		}
																		id = id
																				.substring(
																						0,
																						id.length - 1);
																		$
																				.post(
																						'delChargeitem.do',
																						{
																							id : id
																						},
																						function(
																								result) {
																							$(
																									'#chargeIntem_data')
																									.datagrid(
																											'reload');
																							if (result.errorCode == 0) {
																								$.messager
																										.alert(
																												'提示信息',
																												result.errorDetail,
																												'info');
																							} else {
																								$.messager
																										.alert(
																												'提示信息',
																												result.errorDetail,
																												'error');
																							}
																						});

																	} else {
																		return;
																	}
																});
											}
										}
									} ]
						});
		$('#btnSave').click(
				function() {
					if ($('#ChargeItemform').form('validate')) {
						$.ajax({
							type : 'post',
							url : flag == 'save' ? 'saveChargeitem.do'
									: 'updateChargeitem.do',
							cache : false,
							data : $('#ChargeItemform').serialize(),
							dataType : 'json',
							success : function(result) {
								$('#ChargeItemform').get(0).reset();
								$('#ChargeItemdialog').dialog('close');
								$('#chargeIntem_data').datagrid('reload');
								if (result.errorCode == 0) {
									$.messager.alert('提示信息',
											result.errorDetail, 'info');
								} else {
									$.messager.alert('提示信息',
											result.errorDetail, 'error');
								}
								//清空所选择的行
								$('#chargeIntem_data').datagrid('unselectAll');

							},
							error : function(result) {
								$.meesager.show({
									title : result.status,
									msg : result.errorDetail
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
			$('#ChargeItemdialog').dialog('close');
		});
		/**
		  搜索
		
		 */

		$('#dosearch').click(function() {
			var ChargeItemName = $('#ChargeItemName').val();
			$('#chargeIntem_data').datagrid('load', {
				name : ChargeItemName,
			});
		});

	})
</script>
</head>

<body>
	<div id="lay" class="easyui-layout" style="width: 100%; height: 600px">
		<div region="north" title="条件搜索" collapsible="true" padding-top=20px
			padding-bottom=20px style="height: 12%;">
			<form id="houseInfo_search" method="post" action=""
				style="margin-top: 10px; margin-bottom: 20px">
				<tr>
					<div class="dv-form-item">
						<label for="ChargeItemName"> 资费名称:</label> <input
							class="easyui-validatebox " type="text" name="ChargeItemName"
							id="ChargeItemName" style="width: 200px;" required="true" /> 
						<a id="dosearch" href="#" class="easyui-linkbutton"
							data-options="iconcls:'icon-search'" style="width: 90px;">搜索</a>
					</div>
				</tr>
			</form>
		</div>
		<div region="center">
			<table id="chargeIntem_data"></table>
		</div>
		<div id="ChargeItemdialog" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 50%; height: 55%;">
			<div id="dialogtab" class="easyui-tabs" fit=false plain=true
				style="padding: 3%;">
				<div title="房源信息" align="center" style="width: 100%;" fit=true>
					<form id="ChargeItemform" action="" method="post"
						style="margin-top: 20px; margin-bottom: 20px">
						<input type="hidden" name="id" value="-1" />
						<table>
							<tr>
								<td style="width: 30%;"><label for="name"
									style="padding-left: 6px;">资费名称:</label> <input
									class="easyui-validatebox" type="text" name="name"
									style="width: 182px;" required="true" /></td>
								
							</tr>
							<tr>
								<td style="width: 30%;"><label for="price"
									style="padding-left: 6px;">资费单价:</label> <input
									class="easyui-numberbox" type="text" name="price"
									style="width: 182px;" required="true" /></td>
								
							</tr>
							<tr>
								<td style="width: 30%;"><label for="description"
									style="padding-left: 6px;">备注信息:</label> <input
									class="easyui-validatebox" type="text" name="description"
									style="width: 182px;" required="true" /></td>
								
							</tr>

							<tr>
								<td colspan="2" align="center"><a id="btnSave"
									class="easyui-linkbutton" style="width: 90px;">确定</a> <a
									id="btnCancel" class="easyui-linkbutton" style="width: 90px;">关闭</a></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
