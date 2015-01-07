<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.housemanagersystem.util.Constant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收费管理</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript">
	$(function() {
		var flag;
		$('#chargeDetailinfo_data')
				.datagrid(
						{
							idField : 'id', //只要创建数据表格 就必须要加 ifField
							title : '收费管理',
							fit : true,
							height : 600,
							url : 'getChargedetailInfo.do',
							striped : true, //隔行变色特性 
							loadMsg : '数据正在加载,请耐心的等待...',
							fitColumns : true,
							rownumbers : true,
							singleSelect : false,
							frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
							{
								field : 'ck',
								checkbox : true
							}, {
								field : 'id',
								title : '客户编号',
								width : 100,
								align : 'center',
								hidden : true
							}, {
								field : 'dealid',
								title : '手工单号',
								width : 100,
								align : 'center',
								hidden : true
							}, {
								field : 'houseid',
								title : '房源编号',
								align : 'center',
								width : 100,
							}, {
								field : 'housename',
								title : '房源名称',
								align : 'center',
								width : 100,
							} ] ],
							columns : [ [

							{
								field : 'customername',
								title : '客户姓名',
								width : 100,
								align : 'center',

							}, {
								field : 'chargename',
								title : '资费名称',
								width : 100,
								align : 'center',
							}, {
								field : 'price',
								title : '单价',
								width : 100,
								align : 'center',
							}, {
								field : 'number',
								title : '数量',
								width : 150,
								align : 'center',
							}, {
								field : 'countcharge',
								title : '金额',
								width : 100,
								align : 'center',
							}, {
								field : 'isComplete',
								title : '结算情况',
								width : 100,
								align : 'center',

							}, {
								field : 'createTime',
								title : '生成时间',
								width : 100,
								align : 'center',
								formatter : Common.DateFormatter
							}, {
								field : 'completeTime',
								title : '缴费时间',
								width : 100,
								align : 'center',
								formatter : Common.DateFormatter
							}, {
								field : 'servername',
								title : '操作员',
								width : 100,
								align : 'center',
							} ] ],
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 50 ],
							//工具栏
							toolbar : [
									{
										text : '新增收费',
										iconCls : 'icon-add',
										handler : function() {
											flag = 'save';
											$('#chargeDetailInfodialog')
													.dialog({
														title : '新增客户'
													});
											$('#chargeDetailInfoform').get(0)
													.reset();
											$('#chargeDetailInfodialog')
													.dialog('open');
										}

									},
									{
										text : '编辑收费明细',
										iconCls : 'icon-edit',
										handler : function() {
											flag = 'edit';
											var arr = $(
													'#chargeDetailinfo_data')
													.datagrid('getSelections');
											if (arr.length != 1) {
												$.messager.show({
													title : '提示信息!',
													msg : '请选择一行记录进行修改!'
												});
											} else {
												$('#chargeDetailInfodialog')
														.dialog({
															title : '修改模块'
														});
												$('#chargeDetailInfoform').get(
														0).reset();
												$('#chargeDetailInfodialog')
														.dialog('open');
												$('#chargeDetailInfoform')
														.form(
																'load',
																{
																	id : arr[0].id,
																	dealid : arr[0].dealid,
																	isComplete : arr[0].isComplete,
																	createTime : TimeFormater(arr[0].createTime),
																	completeTime : TimeFormater(arr[0].completeTime),
																	servername : arr[0].servername,
																	countcharge : arr[0].countcharge,
																	number : arr[0].number,
																	price : arr[0].price,
																	customername : arr[0].customername,
																	chargename : arr[0].chargename,
																	housename : arr[0].housename,
																	regintime : arr[0].regintime,
																	houseid : arr[0].houseid,
																});

											}

										}

									},
									{
										text : '删除收费明细',
										iconCls : 'icon-remove',
										handler : function() {
											flag = 'del';
											var arr = $(
													'#chargeDetailinfo_data')
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
																						'delChargeDetailInfo.do',
																						{
																							id : id
																						},
																						function(
																								result) {
																							$(
																									'#chargeDetailinfo_data')
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
																	$(
																			'#chargeDetailinfo_data')
																			.datagrid(
																					'unselectAll');
																});

											}
										}
									},
									{
										text : '费用收取',
										iconCls : 'icon-edit',
										handler : function() {
											var arr = $(
													'#chargeDetailinfo_data')
													.datagrid('getSelections');
											if (arr.length == 0) {
												$.messager.show({
													title : '提示信息!',
													msg : '请至少选择一行记录进行修改!'
												});
											} else {
												var i = 0;
												var ids = '';
												for (i; i < arr.length; i++) {
													if (arr[i].isComplete == '已完成') {
														$.messager
																.alert(
																		'提示信息',
																		'所选收费含有已完成缴费项目，不能重复收费！',
																		'error');
														return;
													} else {
														ids += arr[i].id + ',';
													}
												}
												ids = ids.substring(0,
														ids.length - 1);
												$
														.post(
																'PayforChargeDetails.do',
																{
																	id : ids
																},
																function(result) {
																	$(
																			'#chargeDetailinfo_data')
																			.datagrid(
																					'reload');
																	if (result.errorCode == 0) {
																		$.messager
																				.alert(
																						'提示信息',
																						result.errorDetail,
																						'info');
																		$.messager
																				.confirm(
																						'提示信息',
																						'是否需要打印凭据?',
																						function(
																								r) {
																							if (r) {
																								$.messager
																										.alert(
																												'提示信息',
																												'未连接打印机设备',
																												'error');
																							}
																						});
																	} else {
																		$.messager
																				.alert(
																						'提示信息',
																						result.errorDetail,
																						'error');
																	}

																});

											}

										}

									} ]
						});
		$('#btnSave').click(
				function() {
					if ($('#chargeDetailInfoform').form('validate')) {
						$.ajax({
							type : 'post',
							url : flag == 'save' ? 'saveChargedetailInfo.do'
									: 'updateChargedetailInfo.do',
							cache : false,
							data : $('#chargeDetailInfoform').serialize(),
							dataType : 'json',
							success : function(result) {
								$('#chargeDetailInfoform').get(0).reset();
								$('#chargeDetailInfodialog').dialog('close');
								$('#chargeDetailinfo_data').datagrid('reload');
								if (result.errorCode == 0) {
									$.messager.alert('提示信息',
											result.errorDetail, 'info');
								} else {
									$.messager.alert('提示信息',
											result.errorDetail, 'error');
								}
								//清空所选择的行
								$('#chargeDetailinfo_data').datagrid(
										'unselectAll');

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
		//初始化日期组件
		$('#contactstart').datetimebox({
			showSeconds : true
		});

		$('#contactend').datetimebox({
			showSeconds : true
		});

		/**
		 * 关闭窗口方法
		 */
		$('#btnCancel').click(function() {
			$('#chargeDetailInfodialog').dialog('close');
		});
		/**
		  搜索
		
		 */

		$('#dosearch').click(function() {
			var houseId = $('#houseId').val();
			var houseName = $('#houseName').val();
			var isComplete = $('#isComplete').val();
			$('#chargeDetailinfo_data').datagrid('load', {
				houseid : houseId,
				housename : houseName,
				isComplete : isComplete
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
						<label for="houseId">房源编号:</label> <input
							class="easyui-validatebox " type="text" name="houseId"
							id="houseId" style="width: 200px;" required="true" /> <label
							for="houseName">房源名称:</label> <input class="easyui-validatebox "
							type="text" name="houseName" id="houseName" style="width: 200px;"
							required="true" /> <label for="isComplete">缴费情况:</label> <input
							class="easyui-validatebox " type="text" name="isComplete"
							id="isComplete" style="width: 200px;" required="true" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="dosearch" href="#" class="easyui-linkbutton"
							data-options="iconcls:'icon-search'" style="width: 90px;">搜索</a>
					</div>
				</tr>
			</form>
		</div>
		<div region="center">
			<table id="chargeDetailinfo_data"></table>
		</div>
		<div id="chargeDetailInfodialog" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 50%; height: 55%;">
			<div id="dialogtab" class="easyui-tabs" fit=false plain=true
				style="padding: 3%;">
				<div title="房源信息" align="center" style="width: 100%;" fit=true>
					<form id="chargeDetailInfoform" action="" method="post"
						style="margin-top: 20px; margin-bottom: 20px">
						<input type="hidden" name="id" value="-1" />
						<table>
							<tr>
								<td style="width: 30%;"><label for="houseid"
									style="padding-left: 6px;">房源编号:</label> <input
									class="easyui-validatebox" type="text" name="houseid"
									style="width: 182px;" required="true" /></td>
								<td style="width: 30%;"><label for="housename"
									style="padding-left: 6px;">房源名称:</label> <input
									class="easyui-validatebox" type="text" name="housename"
									style="width: 182px;" required="true" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="customername"
									style="padding-left: 6px;">房客姓名:</label> <input
									class="easyui-validatebox" type="text" name="customername"
									style="width: 182px;" required="true" /></td>
								<td style="width: 30%;"><label for="chargename"
									style="padding-left: 6px;">资费名称:</label> <input
									class="easyui-validatebox" type="text" name="chargename"
									style="width: 182px;" required="true" /></td>

							</tr>
							<tr>
								<td style="width: 30%;"><label for="price"
									style="padding-left: 6px;">资费单价:</label> <input
									class="easyui-validatebox" type="text" name="price"
									style="width: 182px;" required="true" /></td>
								<td style="width: 30%;"><label for="number"
									style="padding-left: 6px;">资费数量:</label> <input
									class="easyui-validatebox" type="text" name="number"
									style="width: 182px;" required="true" /></td>
							</tr>


							<tr>
								<td style="width: 30%;"><label for="countcharge"
									style="padding-left: 6px;">结算金额:</label> <input
									class="easyui-validatebox" type="text" name="countcharge"
									style="width: 182px;" required="true" /></td>
								<td style="width: 30%;"><label for="isComplete"
									style="padding-left: 6px;">结算情况:</label> <input
									class="easyui-validatebox" type="text" name="isComplete"
									style="width: 182px;" required="true" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="createTime"
									style="padding-left: 6px;">生成时间: </label> <input
									id="contactstart" name="createTime" style="width: 188px;"></td>
								<td style="width: 30%;"><label for="completeTime"
									style="padding-left: 6px;">缴费时间: </label> <input
									id="contactend" name="completeTime" style="width: 188px;"></td>

							</tr>

							<tr>
								<td style="width: 30%;"><label for="dealid"
									style="padding-left: 6px;">手工单号:</label> <input
									class="easyui-validatebox" type="text" name="dealid"
									style="width: 182px;" required="true" /></td>
								<td style="width: 30%;"><label for="servername"
									style="padding-left: 6px;">操作人员:</label> <input
									class="easyui-validatebox" type="text" name="servername"
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
