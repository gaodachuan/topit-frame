<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.housemanagersystem.util.Constant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>租售管理</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript">
	$(function() {
		var flag;
		$('#houseinfo_data')
				.datagrid(
						{
							idField : 'id', //只要创建数据表格 就必须要加 ifField
							title : '房源信息',
							fit : true,
							height : 600,
							url : '${pageContext.request.contextPath}/houseinfo/getHouseInfo.do',
							striped : true, //隔行变色特性 
							loadMsg : '数据正在加载,请耐心的等待...',
							rownumbers : true,
							singleSelect : true,

							frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
							{
								field : 'id',
								title : '房间编号',
								width : 100,
								align : 'center',
								hidden : true
							}, {
								field : 'number',
								title : '房间编号',
								align : 'center',
								width : 100,
							}, {
								field : 'name',
								title : '房间名称',
								align : 'center',
								width : 100,
							} ] ],
							columns : [ [

							{
								field : 'housetype',
								title : '房间类型',
								width : 100,
								align : 'center',

							}, {
								field : 'area',
								title : '房间面积',
								width : 100,
								align : 'center',
							}, {
								field : 'decorate',
								title : '装修情况',
								width : 100,
								align : 'center',
							}, {
								field : 'equipment',
								title : '房间设施',
								width : 100,
								align : 'center',
							}, {
								field : 'usetype',
								title : '房间用途',
								width : 100,
								align : 'center',
							}, {
								field : 'warningtime',
								title : '报警天数',
								width : 100,
								align : 'center',
							}, {
								field : 'dayrentpri',
								title : '日租价格',
								width : 100,
								align : 'center',
								sortable : true
							}, {
								field : 'monthrentpri',
								title : '月租/月供价格',
								width : 100,
								align : 'center',
								sortable : true
							}, {
								field : 'dealType',
								title : '业务类型',
								width : 100,
								align : 'center',
							}, {
								field : 'status',
								title : '房间状态',
								width : 100,
								align : 'center',
							}, {
								field : 'rentstart',
								title : '合同开始时间',
								width : 100,
								align : 'center',
								formatter : Common.DateFormatter,
							}, {
								field : 'rentend',
								title : '合同结束时间',
								width : 100,
								align : 'center',
								formatter : Common.DateFormatter,
							}, {
								field : 'electricStart',
								title : '电表度数',
								width : 100,
								align : 'center',

							}, {
								field : 'waterStart',
								title : '水表度数',
								width : 100,
								align : 'center',

							}, {
								field : 'months',
								title : '月份数',
								width : 100,
								align : 'center',
							}, {
								field : 'days',
								title : '天数',
								width : 100,
								align : 'center',
							}, {
								field : 'description',
								title : '备注',
								width : 100,
								align : 'center',
							} ] ],
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 50 ],
							//工具栏
							toolbar : [
									{
										text : '房屋出租',
										iconCls : 'icon-add',
										handler : function() {
											flag = 'edit';
											var arr = $('#houseinfo_data')
													.datagrid('getSelections');
											if (arr.length != 1) {
												$.messager.show({
													title : '提示信息!',
													msg : '请选择一行记录进行修改!'
												});
											} else {
												if (arr[0].status == '已租') {
													$.messager.show({
														title : '提示信息',
														msg : '房间已出租，不能操作'
													});
												} else if (arr[0].dealType == '出售') {
													$.messager.show({
														title : '提示信息',
														msg : '此房间只用于出售，不能出租！'
													});
												}

												else {
													$('#houseInfodialog')
															.dialog({
																title : '房屋出租'
															});
													$('#houseInfoform').get(0)
															.reset();
													$('#houseInfodialog')
															.dialog('open');
													$('#houseInfoform')
															.form(
																	'load',
																	{
																		'h.id' : arr[0].id,
																		'h.name' : arr[0].name,
																		'h.number' : arr[0].number,
																		'h.area' : arr[0].area,
																		'h.usetype' : arr[0].usetype,
																		'h.decorate' : arr[0].decorate,
																		'h.equipment' : arr[0].equipment,
																		'h.dayrentpri' : arr[0].dayrentpri,
																		'h.monthrentpri' : arr[0].monthrentpri,
																		'h.dealType' : arr[0].dealType,
																		'h.housetype' : arr[0].housetype,
																		'h.ElectricStart' : arr[0].electricStart,
																		'h.WaterStart' : arr[0].waterStart

																	});

												}
											}
										}

									},
									{
										text : '房屋出售',
										iconCls : 'icon-edit',
										handler : function() {

										}

									},
									{
										text : '房屋续租',
										iconCls : 'icon-remove',
										handler : function() {
											flag = '续租';
											var arr = $('#houseinfo_data')
													.datagrid('getSelections');
											if (arr.length != 1) {
												$.messager.show({
													title : '提示信息!',
													msg : '请选择一行记录处理!'
												});
											} else {
												if (arr[0].status != '已租') {
													$.messager.show({
														title : '提示信息',
														msg : '房间未出租，不能续租'
													});
												} else {
													$('#xuzuInfodialog')
															.dialog({
																title : '房屋续租'
															});
													$('#xuzu_houseInfoform')
															.get(0).reset();
													$('#xuzuInfodialog')
															.dialog('open');
													$('#xuzu_houseInfoform')
															.form(
																	'load',
																	{
																		'h.id' : arr[0].id,
																		'h.name' : arr[0].name,
																		'h.number' : arr[0].number,
																		'h.area' : arr[0].area,
																		'h.usetype' : arr[0].usetype,
																		'h.decorate' : arr[0].decorate,
																		'h.equipment' : arr[0].equipment,
																		'h.dayrentpri' : arr[0].dayrentpri,
																		'h.monthrentpri' : arr[0].monthrentpri,
																		'h.dealType' : arr[0].dealType,
																		'h.housetype' : arr[0].housetype,
																		'h.ElectricStart' : arr[0].electricStart,
																		'h.WaterStart' : arr[0].waterStart,
																		'h.rentstart' : TimeFormater(arr[0].rentstart),
																		'h.rentend' : TimeFormater(arr[0].rentend)
																	});
													//除了

												}
											}
										}

									},
									{
										text : '房屋退组',
										iconCls : 'icon-remove',
										handler : function() {
											flag = '续租';
											var arr = $('#houseinfo_data')
													.datagrid('getSelections');
											if (arr.length != 1) {
												$.messager.show({
													title : '提示信息!',
													msg : '请选择一行记录处理!'
												});
											} else {
												if (arr[0].status != '已租') {
													$.messager.show({
														title : '提示信息',
														msg : '房间未出租，不能退租'
													});
												} else {                                                     
													$.ajax({
														type : 'post',
														url : 'tuizuService.do',
														cache : false,
														data : {id:arr[0].id},
														dataType : 'json',
														success : function(result) {
															$('#houseinfo_data').datagrid('reload');
															if (result.errorCode == 0) {
																$.messager.alert('提示信息',
																		result.errorDetail, 'info');
															} else {
																$.messager.alert('提示信息',
																		result.errorDetail, 'error');
															}
															//清空所选择的行
															$('#houseinfo_data').datagrid('unselectAll');

														},
														error : function(result) {
															$.meesager.show({
																title : result.status,
																msg : result.errorDetail
															});
														}
													});
													
												}
											}
										}

									} ]
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
		//初始化日期组件
		$('#rentstart').datetimebox({
			showSeconds : true
		});

		$('#rentend').datetimebox({
			showSeconds : true
		});
		//保存	 
		$('#btnSave').click(
				function() {
					if ($('#houseInfoform').form('validate')) {
						$.ajax({
							type : 'post',
							url : 'centerService.do',
							cache : false,
							data : $('#houseInfoform').serialize(),
							dataType : 'json',
							success : function(result) {
								$('#houseInfoform').get(0).reset();
								$('#houseInfodialog').dialog('close');
								$('#houseinfo_data').datagrid('reload');
								if (result.errorCode == 0) {
									$.messager.alert('提示信息',
											result.errorDetail, 'info');
								} else {
									$.messager.alert('提示信息',
											result.errorDetail, 'error');
								}
								//清空所选择的行
								$('#houseinfo_data').datagrid('unselectAll');

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

		//续租保存
		$('#xuzu_commit').click(
				function() {
					if ($('#xuzu_houseInfoform').form('validate')) {
						$.ajax({
							type : 'post',
							url : 'xuzuService.do',
							cache : false,
							data : $('#xuzu_houseInfoform').serialize(),
							dataType : 'json',
							success : function(result) {
								$('#houseInfoform').get(0).reset();
								$('#houseInfodialog').dialog('close');
								$('#houseinfo_data').datagrid('reload');
								if (result.errorCode == 0) {
									$.messager.alert('提示信息',
											result.errorDetail, 'info');
								} else {
									$.messager.alert('提示信息',
											result.errorDetail, 'error');
								}
								//清空所选择的行
								$('#houseinfo_data').datagrid('unselectAll');

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
			$('#rentInfoform').get(0).reset();
			$('#houseInfodialog').dialog('close');
		});
		/**
		  搜索
		
		 */

		$('#dosearch').click(function() {
			var houseName = $('#houseName').val();
			var houseNumber = $('#houseNumber').val();
			var houseType = $('#houseType').combobox("getValue");
			$('#houseinfo_data').datagrid('load', {
				name : houseName,
				number : houseNumber,
				housetype : houseType
			});
			$('#houseInfo_search').get(
					0).reset();
		});

		$('#nextStep').click(function() {
			$('#dialogtab').tabs('select', '租房信息');
		});

	})
</script>
</head>

<body>
	<div id="lay" class="easyui-layout" style="width: 100%; height: 600px">
		<div region="north" title="条件搜索" collapsible="true" padding-top=20px
			padding-bottom=20px style="height: 12%;">

			<!--搜索  -->
			<form id="houseInfo_search" method="post" action=""
				style="margin-top: 10px; margin-bottom: 20px">
				<tr>
					<div class="dv-form-item">
						<label for="houseName"> 房源名称:</label> <input
							class="easyui-validatebox " type="text" name="houseName"
							id="houseName" style="width: 200px;" /> <label for="houseNumber">房源编号:</label>
						<input class="easyui-validatebox " type="text" name="houseNumber"
							id="houseNumber" style="width: 200px;" required="true" /> <label
							for="houseType">房源类型:</label> <select class="easyui-combobox "
							id="houseType" name="housetype" panelHeight="100"
							url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_HOUSETYPE%>"
							valueField="name" textField="name" value=""
							style="width: 188px; height: auto"></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="dosearch" href="#" class="easyui-linkbutton"
							data-options="iconcls:'icon-search'" style="width: 90px;">搜索</a>
					</div>
				</tr>
			</form>
		</div>
		<div region="center">
			<table id="houseinfo_data"></table>
		</div>

		<!--业务表单  -->
		<div id="houseInfodialog" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 55%; height: 70%;">
			<!--当前业务处理的房源信息  -->
			<!--一个大的Form表单  -->
			<form id="houseInfoform"
				style="margin-top: 20px; margin-bottom: 20px">
				<input type="hidden" name="h.id" value="-1" />
				<div id="dialogtab" class="easyui-tabs" fit=false plain=true
					style="padding: 3%;">
					<div title="房源信息" align="center" style="width: 100%; height: 50%"
						fit=true>
						<table style="margin-top: 5%; margin-left: 5%">
							<tr>
								<td style="width: 30%;"><label for="h.number"
									style="padding-left: 6px;">房源编号:</label> <input
									class="easyui-validatebox" type="text" name="h.number"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.name"
									style="padding-left: 6px;">房源名称:</label> <input
									class="easyui-validatebox " type="text" name="h.name"
									style="width: 182px;" readonly="readonly" /></td>
							</tr>

							<tr>
								<td style="width: 30%;"><label for="h.area"
									style="padding-left: 6px;">房源面积:</label> <input
									class="easyui-validatebox " type="text" name="h.area"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.decorate"
									style="padding-left: 6px;">装修情况:</label> <input
									class="easyui-validatebox " type="text" name="h.decorate"
									style="width: 182px;" readonly="readonly" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="h.housetype"
									style="padding-left: 6px;">房源类型: </label> <input
									class="easyui-validatebox " type="text" name="h.housetype"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.equipment"
									style="padding-left: 6px;">房间设施: </label> <input
									class="easyui-validatebox " type="text" name="h.equipment"
									style="width: 182px;" readonly="readonly" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="h.usetype"
									style="padding-left: 6px;">房源用途: </label><input
									class="easyui-validatebox " type="text" name="h.usetype"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.dealType"
									style="padding-left: 6px;">租售方式:</label> <input
									class="easyui-validatebox " type="text" name="h.dealType"
									style="width: 182px;" readonly="readonly" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="h.dayrentpri"
									style="padding-left: 6px;">日供价格:</label> <input
									class="easyui-validatebox " type="text" name="h.dayrentpri"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.monthrentpri"
									style="padding-left: 6px;">月租价格: </label><input
									class="easyui-validatebox " type="text" name="h.monthrentpri"
									style="width: 182px;" readonly="readonly" /></td>
							</tr>
							<tr>

								<td style="width: 30%;"><label for="h.ElectricStart"
									style="padding-left: 6px;">电表度数:</label> <input
									class="easyui-validatebox " type="text" name="h.ElectricStart"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.WaterStart"
									style="padding-left: 6px;">水表度数:</label> <input
									class="easyui-validatebox " type="text" name="h.WaterStart"
									style="width: 182px;" readonly="readonly" /></td>

							</tr>


						</table>
						<div align="center" style="margin-bottom: 1%; margin-top: 3%">
							<a id="nextStep" class="easyui-linkbutton" style="width: 90px;">业务办理-></a>
						</div>

					</div>


					<!--业务办理信息  -->
					<div title="租房信息" align="center" style="width: 90%; height: 50%"
						fit=true>
						<table style="margin-top: 5%; margin-left: 5%">
							<tr>
								<td style="width: 30%;"><label for="con.servername"
									style="padding-left: 6px;">办理人员:</label> <input
									class="easyui-validatebox " type="text" name="con.servername"
									style="width: 182px;" required="required" /></td>
								<td style="width: 30%;"><label for="con.customername"
									style="padding-left: 6px;">租客姓名:</label> <select
									id="customername" name="con.customername" style="width: 182px;">
								</select></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="con.customerno"
									style="padding-left: 6px;">房客人数:</label> <input
									class="easyui-numberbox " type="text" name="con.customerno"
									style="width: 182px;" required="required" /></td>

								<td style="width: 30%;"><label for="con.servicetype"
									style="padding-left: 6px;">租赁方式: </label> <select
									id="servicetype" class="easyui-combobox "
									name="con.servicetype" panelHeight="100"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_DEALTYPE%>"
									valueField="name" textField="name" value=""
									style="width: 182px; height: auto"></select></td>

							</tr>
							<tr>
								<td style="width: 30%;"><label for="con.contactstart"
									style="padding-left: 6px;">合同起始: </label> <input
									id="contactstart" name="con.contactstart" style="width: 182px;"></td>
								<td style="width: 30%;"><label for="con.contactend"
									style="padding-left: 6px;">合同截止: </label> <input
									id="contactend" name="con.contactend" style="width: 182px;"></td>

							</tr>
							<tr>
								<!--资费项目设置  -->
								<td style="width: 30%;"><label for="con.chargeitem"
									style="padding-left: 6px;">资费设置:</label> <select
									id="chargeitem" name="con.chargeitem" style="width: 182px;">
								</select></td>
							</tr>
						</table>
						<div align="center" style="margin-bottom: 1%; margin-top: 3%">
							<a id="btnSave" class="easyui-linkbutton" style="width: 90px;">确定</a>
							<a id="btnCancel" class="easyui-linkbutton" style="width: 90px;">关闭</a>
						</div>

					</div>

				</div>

			</form>
		</div>
		<!--续租  -->
		<div id="xuzuInfodialog" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 55%; height: 70%;">
			<form id="xuzu_houseInfoform"
				style="margin-top: 20px; margin-bottom: 20px">
				<input type="hidden" name="h.id" value="-1" />
				<div id="dialogtab" class="easyui-tabs" fit=false plain=true
					style="padding: 3%;">
					<div title="房源信息" align="center" style="width: 100%; height: 50%"
						fit=true>
						<table style="margin-top: 5%; margin-left: 5%">
							<tr>
								<td style="width: 30%;"><label for="h.number"
									style="padding-left: 6px;">房源编号:</label> <input
									class="easyui-validatebox" type="text" name="h.number"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.name"
									style="padding-left: 6px;">房源名称:</label> <input
									class="easyui-validatebox " type="text" name="h.name"
									style="width: 182px;" readonly="readonly" /></td>
							</tr>

							<tr>
								<td style="width: 30%;"><label for="h.area"
									style="padding-left: 6px;">房源面积:</label> <input
									class="easyui-validatebox " type="text" name="h.area"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.decorate"
									style="padding-left: 6px;">装修情况:</label> <input
									class="easyui-validatebox " type="text" name="h.decorate"
									style="width: 182px;" readonly="readonly" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="h.housetype"
									style="padding-left: 6px;">房源类型: </label> <input
									class="easyui-validatebox " type="text" name="h.housetype"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.equipment"
									style="padding-left: 6px;">房间设施: </label> <input
									class="easyui-validatebox " type="text" name="h.equipment"
									style="width: 182px;" readonly="readonly" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="h.usetype"
									style="padding-left: 6px;">房源用途: </label><input
									class="easyui-validatebox " type="text" name="h.usetype"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.dealType"
									style="padding-left: 6px;">租售方式:</label> <input
									class="easyui-validatebox " type="text" name="h.dealType"
									style="width: 182px;" readonly="readonly" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="h.dayrentpri"
									style="padding-left: 6px;">日供价格:</label> <input
									class="easyui-validatebox " type="text" name="h.dayrentpri"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.monthrentpri"
									style="padding-left: 6px;">月租价格: </label><input
									class="easyui-validatebox " type="text" name="h.monthrentpri"
									style="width: 182px;" readonly="readonly" /></td>
							</tr>
							<tr>

								<td style="width: 30%;"><label for="h.ElectricStart"
									style="padding-left: 6px;">电表度数:</label> <input
									class="easyui-validatebox " type="text" name="h.ElectricStart"
									style="width: 182px;" readonly="readonly" /></td>
								<td style="width: 30%;"><label for="h.WaterStart"
									style="padding-left: 6px;">水表度数:</label> <input
									class="easyui-validatebox " type="text" name="h.WaterStart"
									style="width: 182px;" readonly="readonly" /></td>

							</tr>
							<tr>
								<td style="width: 30%;"><label for="h.rentstart"
									style="padding-left: 6px;">合同起始: </label> <input id="rentstart"
									name="h.rentstart" style="width: 182px;" readonly="readonly"></td>
								<td style="width: 30%;"><label for="h.rentend"
									style="padding-left: 6px;">合同截止: </label> <input id="rentend"
									name="h.rentend" style="width: 182px;"></td>

							</tr>
						</table>
						<div align="center" style="margin-bottom: 1%; margin-top: 3%">
							<a id="xuzu_commit" class="easyui-linkbutton"
								style="width: 90px;">确定</a>
						</div>

					</div>
				</div>
		</div>
</body>
</html>
