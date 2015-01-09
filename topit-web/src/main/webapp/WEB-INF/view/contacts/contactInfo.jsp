<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.housemanagersystem.util.Constant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合同管理</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript">
	$(function() {
		var flag;
		$('#contactinfo_data')
				.datagrid(
						{
							idField : 'id', //只要创建数据表格 就必须要加 ifField
							title : '合同信息',
							fit : true,
							height : 600,
							url : 'getContactInfo.do',
							striped : true, //隔行变色特性 
							loadMsg : '数据正在加载,请耐心的等待...',
							rownumbers : true,
							singleSelect : false,
							frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
							{
								field : 'ck',
								checkbox : true
							}, {
								field : 'id',
								title : '合同编号',
								width : 100,
								align : 'center',
							}, {
								field : 'houseid',
								title : '房源编号',
								width : 100,
								align : 'center',
							}, {
								field : 'housename',
								title : '房源名称',
								align : 'center',
								width : 100,
							}, ] ],
							columns : [ [ {
								field : 'customername',
								title : '住户姓名',
								width : 100,
								align : 'center',
							},{
								field : 'servicetype',
								title : '租售类型',
								width : 60,
								align : 'center',
							},{
								field : 'contactstart',
								title : '合同开始时期',
								width : 150,
								align : 'center',
								formatter : Common.DateFormatter
							},{
								field : 'contactend',
								title : '合同结束时期',
								width : 150,
								align : 'center',
								formatter : Common.DateFormatter
							},{
								field : 'housetype',
								title : '房间类型',
								width : 60,
								align : 'center',
							}, {
								field : 'houseArea',
								title : '房间面积',
								width : 80,
								align : 'center',
							}, {
								field : 'houseDecorate',
								title : '装修情况',
								width : 60,
								align : 'center',
							}, {
								field : 'houseequipment',
								title : '房间设施',
								width : 150,
								align : 'center',
							}, {
								field : 'houseUseType',
								title : '房源用途',
								width : 60,
								align : 'center',
							}, {
								field : 'houseDaypri',
								title : '日供金额',
								width : 100,
								align : 'center',
							}, {
								field : 'houseMonthpri',
								title : '月供金额',
								width : 100,
								align : 'center',
							}, {
								field : 'waterstart',
								title : '水表度数',
								width : 100,
								align : 'center',
							}, {
								field : 'electricstart',
								title : '电表度数',
								width : 100,
								align : 'center',
							}, {
								field : 'chargeitem',
								title : '附加资费',
								width : 150,
								align : 'center',
							},  {
								field : 'isVariable',
								title : '是否有效',
								width : 150,
								align : 'center',
							}, {
								field : 'servername',
								title : '办理人员',
								width : 100,
								align : 'center',
							} ] ],
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 50 ],
							//工具栏
							toolbar : [
									{
										text : '新增合同',
										iconCls : 'icon-add',
										handler : function() {
											flag = 'save';
											$('#contactInfodialog').dialog({
												title : '新增客户'
											});
											$('#contactInfoform').get(0)
													.reset();
											$('#contactInfodialog').dialog(
													'open');
										}

									},
									{
										text : '新建合同',
										iconCls : 'icon-edit',
										handler : function() {
											$.messager
													.confirm(
															'提示信息',
															'合同信息属于重要信息，确认编辑?',
															function(r) {

																flag = 'edit';
																var arr = $(
																		'#contactinfo_data')
																		.datagrid(
																				'getSelections');
																if (arr.length != 1) {
																	$.messager
																			.show({
																				title : '提示信息!',
																				msg : '请选择一行记录进行修改!'
																			});
																} else {
																	$(
																			'#contactInfodialog')
																			.dialog(
																					{
																						title : '修改模块'
																					});
																	$(
																			'#contactInfoform')
																			.get(
																					0)
																			.reset();
																	$(
																			'#contactInfodialog')
																			.dialog(
																					'open');
																	$(
																			'#contactInfoform')
																			.form(
																					'load',
																					{
																						id : arr[0].id,
																						houseequipment : arr[0].houseequipment,
																						electricstart : arr[0].electricstart,
																						waterstart : arr[0].waterstart,
																						servername : arr[0].servername,
																						chargeitem : arr[0].chargeitem,
																						customername : arr[0].customername,
																						houseid : arr[0].houseid,
																						housename : arr[0].housename,
																						houseArea : arr[0].houseArea,
																						houseMonthpri : arr[0].houseMonthpri,
																						houseDaypri : arr[0].houseDaypri,
																						houseDecorate : arr[0].houseDecorate,
																						houseUseType : arr[0].houseUseType,
																						housetype : arr[0].housetype,
																						customerno : arr[0].customerno,
																						servicetype : arr[0].servicetype,
																						contactstart : TimeFormater(arr[0].contactstart),
																						contactend : TimeFormater(arr[0].contactend),
																					});

																}

															});

										}

									},
									{
										text : '删除合同',
										iconCls : 'icon-remove',
										handler : function() {
											flag = 'del';
											var arr = $('#contactinfo_data')
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
																						'delContactInfo.do',
																						{
																							id : id
																						},
																						function(
																								result) {
																							$(
																									'#contactinfo_data')
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
																	'#contactinfo_data')
																	.datagrid(
																			'unselectAll');
																});
												
											}
										}
									} ]
						});
		$('#btnSave').click(
				function() {
					if ($('#contactInfoform').form('validate')) {
						$.ajax({
							type : 'post',
							url : flag == 'save' ? 'saveContactInfo.do'
									: 'updateContactInfo.do',
							cache : false,
							data : $('#contactInfoform').serialize(),
							dataType : 'json',
							success : function(result) {
								$('#contactInfoform').get(0).reset();
								$('#contactInfodialog').dialog('close');
								$('#contactinfo_data').datagrid('reload');
								if (result.errorCode == 0) {
									$.messager.alert('提示信息',
											result.errorDetail, 'info');
								} else {
									$.messager.alert('提示信息',
											result.errorDetail, 'error');
								}
								//清空所选择的行
								$('#contactinfo_data').datagrid('unselectAll');

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

		//加载客户信息
		$('#customername')
				.combogrid(
						{
							panelWidth : 400,
							value : '选择客户',
							idField : 'name',
							textField : 'name',
							url : '${pageContext.request.contextPath}/customer/getCustomerInfo.do',
							striped : true, //隔行变色特性 
							loadMsg : '数据正在加载,请耐心的等待...',
							rownumbers : true,
							singleSelect : true,
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 50 ],
							columns : [ [ {
								field : 'id',
								title : '客户编号',
								width : 60,
								align : 'center',
							}, {
								field : 'name',
								title : '客户名称',
								width : 100,
								align : 'center',
							}, {
								field : 'sex',
								title : '性别',
								width : 120,
								align : 'center',
							}, {
								field : 'telnumber',
								title : '电话号码',
								width : 100,
								align : 'center',
							} ] ]
						});
		//加载资费信息
		$('#chargeitem')
				.combogrid(
						{
							panelWidth : 400,
							value : '选择项目',
							idField : 'name',
							textField : 'name',
							url : '${pageContext.request.contextPath}/chargeitem/getChargeitems.do',
							striped : true, //隔行变色特性 
							loadMsg : '数据正在加载,请耐心的等待...',
							rownumbers : true,
							multiple : true,
							separator : '|',
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 50 ],
							columns : [ [ {
								field : 'name',
								title : '资费名称',
								width : 120,
								align : 'center',
							}, {
								field : 'price',
								title : '单价',
								width : 120,
								align : 'center',
							}, {
								field : 'description',
								title : '备注信息',
								width : 130,
								align : 'center',
							} ] ],
							onSelect : function(record) {

							},
							onUnselect : function(record) {

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
			$('#contactInfodialog').dialog('close');
		});
		/**
		  搜索
		
		 */

		$('#dosearch').click(function() {
			var customerName = $('#customerName').val();
			var houseId = $('#houseId').val();
			var houseName = $('#houseName').val();
			$('#contactinfo_data').datagrid('load', {
				name : customerName,
				houseid : houseId,
				housename:houseName
			});
			$('#houseInfo_search').get(
					0).reset();
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
						<label for="customername"> 客户姓名:</label> <input
							class="easyui-validatebox " type="text" name="customername"
							id="customerName" style="width: 200px;" required="true" /> <label
							for="houseid">房源编号:</label> <input class="easyui-validatebox "
							type="text" name="houseid" id="houseId" style="width: 200px;"
							required="true" /> <label for="housename">房源名称:</label> <input
							class="easyui-validatebox " type="text" name="housename"
							id="houseName" style="width: 200px;" required="true" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="dosearch" href="#" class="easyui-linkbutton"
							data-options="iconcls:'icon-search'" style="width: 90px;">搜索</a>
					</div>
				</tr>
			</form>
		</div>
		<div region="center">
			<table id="contactinfo_data"></table>
		</div>
		<div id="contactInfodialog" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 60%; height: 70%;">
			<div id="dialogtab" class="easyui-tabs" fit=false plain=true
				style="padding: 3%;">
				<div title="合同信息" align="center" style="width: 100%;" fit=true>
					<form id="contactInfoform" action="" method="post"
						style="margin-top: 20px; margin-bottom: 20px">
						<input type="hidden" name="id" value="-1" />
						<table>
							<tr>
								<td style="width: 30%;"><label for="houseid"
									style="padding-left: 6px;">房源编号:</label> <input
									class="easyui-validatebox" type="text" name="houseid"
									style="width: 182px;" required=true /></td>
								<td style="width: 30%;"><label for="housename"
									style="padding-left: 6px;">房源名称:</label> <input
									class="easyui-validatebox " type="text" name="housename"
									style="width: 182px;" required=true /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="customername"
									style="padding-left: 6px;">客户姓名:</label> <select
									id="customername" name="customername" style="width: 188px;">
								</select></td>
								<td style="width: 30%;"><label for="servicetype"
									style="padding-left: 6px;">业务类型:</label> <input
									class="easyui-validatebox " type="text" name="servicetype"
									style="width: 182px;" required=true /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="contactstart"
									style="padding-left: 6px;">合同起始: </label> <input
									id="contactstart" name="contactstart" style="width: 188px;"></td>
								<td style="width: 30%;"><label for="contactend"
									style="padding-left: 6px;">合同截止: </label> <input
									id="contactend" name="contactend" style="width: 188px;"></td>

							</tr>
							<tr>
								<td style="width: 30%;"><label for="houseArea"
									style="padding-left: 6px;">房源面积:</label> <input
									class="easyui-validatebox " type="text" name="houseArea"
									style="width: 182px;" required=true /></td>
								<td style="width: 30%;"><label for="houseDecorate"
									style="padding-left: 6px;">装修情况:</label> <select id="decorate"
									class="easyui-combobox " name="houseDecorate"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_DECORETE%>"
									panelHeight="100" valueField="name" textField="name" value=""
									style="width: 188px; height: auto">
								</select></td>

							</tr>
							<tr>
								<td style="width: 30%;"><label for="housetype"
									style="padding-left: 6px;">房源类型: </label> <select
									class="easyui-combobox " id="housetype" name="housetype"
									panelHeight="100"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_HOUSETYPE%>"
									valueField="name" textField="name" value=""
									style="width: 188px; height: auto">
								</select></td>
								<td style="width: 30%;"><label for="houseequipment"
									style="padding-left: 6px;">房间设施: </label><select
									id="houseequipment" class="easyui-combobox "
									name="houseequipment" panelHeight="100" multiple="true"
									separator="|"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_EQUEPMENTS%>"
									valueField="name" textField="name" value=""
									style="width: 188px; height: auto">
								</select></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="houseUseType"
									style="padding-left: 6px;">房源用途:</label> <select id="usetype"
									class="easyui-combobox " name="houseUseType"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_USETYPE%>"
									valueField="name" textField="name" value="" panelHeight="100"
									style="width: 188px; height: auto"></select></td>
								<td style="width: 30%;"><label for="chargeitem"
									style="padding-left: 6px;">附加资费:</label> <select
									id="chargeitem" name="chargeitem" style="width: 188px;"></select></td>
							</tr>
							<tr>

								<td style="width: 30%;"><label for="electricstart"
									style="padding-left: 6px;">电表度数:</label> <input
									class="easyui-validatebox " type="text" name="electricstart"
									style="width: 182px;" required=true /></td>
								<td style="width: 30%;"><label for="waterstart"
									style="padding-left: 6px;">水表度数:</label> <input
									class="easyui-validatebox " type="text" name="waterstart"
									style="width: 182px;" required=true /></td>

							</tr>
							<tr>
								<td style="width: 30%;"><label for="houseDaypri"
									style="padding-left: 6px;">日供价格:</label> <input
									class="easyui-validatebox " type="text" name="houseDaypri"
									style="width: 182px;" required=true /></td>
								<td style="width: 30%;"><label for="houseMonthpri"
									style="padding-left: 6px;">月租价格: </label><input
									class="easyui-validatebox " type="text" name="houseMonthpri"
									style="width: 182px;" required=true /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="customerno"
									style="padding-left: 6px;">租客人数:</label> <input
									class="easyui-validatebox " type="text" name="customerno"
									style="width: 182px;" required=true /></td>
								<td style="width: 30%;"><label for="servername"
									style="padding-left: 6px;">操作人员: </label><input
									class="easyui-validatebox " type="text" name="servername"
									style="width: 182px;" required=true /></td>
							</tr>
							<tr>
								<td colspan="2" align="center" style="margin-top: 3%"><a
									id="btnSave" class="easyui-linkbutton" style="width: 90px;">确定</a>
									<a id="btnCancel" class="easyui-linkbutton"
									style="width: 90px;">关闭</a></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
