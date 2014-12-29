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
												$('#houseInfodialog').dialog({
													title : '房屋出租'
												});
												$('#houseInfoform').get(0)
														.reset();
												$('#houseInfodialog').dialog(
														'open');
												$('#houseInfoform')
														.form(
																'load',
																{
																	id : arr[0].id,
																	name : arr[0].name,
																	number : arr[0].number,
																	area : arr[0].area,
																	usetype : arr[0].usetype,
																	decorate : arr[0].decorate,
																	equipment : arr[0].equipment,
																	dayrentpri : arr[0].dayrentpri,
																	monthrentpri : arr[0].monthrentpri,
																	dealType : arr[0].dealType,
																	housetype : arr[0].housetype,
																	ElectricStart : arr[0].electricStart,
																	WaterStart : arr[0].waterStart

																});

											}
										}

									}, {
										text : '房屋出售',
										iconCls : 'icon-edit',
										handler : function() {

										}

									}, {
										text : '房屋续租',
										iconCls : 'icon-remove',
										handler : function() {

										}

									}, {
										text : '房屋退组',
										iconCls : 'icon-remove',
										handler : function() {

										}

									} ]
						});

		//加载客户信息
		$('#CustomerName')
				.combogrid(
						{
							panelWidth : 400,
							value : '选择客户',
							idField : 'id',
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
		$('#ChargeitemSet')
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
							separator:'|',
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
							url : flag == 'save' ? 'saveHouseInfo.do'
									: 'updateHouseInfo.do',
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
		});
		
		$('#nextStep').click(function(){
			
			$('#dialogtab').tabs('select','租房信息');
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
		<div id="houseInfodialog" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 55%; height: 70%;">

			<!--当前业务处理的房源信息  -->

			<div id="dialogtab" class="easyui-tabs" fit=false plain=true
				style="padding: 3%;">
				<div title="房源信息" align="center" style="width: 100%; height: 50%"
					fit=true>
					<form id="houseInfoform" action="" method="post"
						style="margin-top: 20px; margin-bottom: 20px">
						<input type="hidden" name="id" value="-1" />
						<table>
							<tr>
								<td style="width: 30%;"><label for="number"
									style="padding-left: 6px;">房源编号:</label> <input
									class="easyui-validatebox" type="text" name="number"
									style="width: 182px;" disabled="true" /></td>
								<td style="width: 30%;"><label for="name"
									style="padding-left: 6px;">房源名称:</label> <input
									class="easyui-validatebox " type="text" name="name"
									style="width: 182px;" disabled="true" /></td>
							</tr>

							<tr>
								<td style="width: 30%;"><label for="area"
									style="padding-left: 6px;">房源面积:</label> <input
									class="easyui-validatebox " type="text" name="area"
									style="width: 182px;" disabled="true" /></td>
								<td style="width: 30%;"><label for="decorate"
									style="padding-left: 6px;">装修情况:</label> <input
									class="easyui-validatebox " type="text" name="decorate"
									style="width: 182px;" disabled="true" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="housetype"
									style="padding-left: 6px;">房源类型: </label> <input
									class="easyui-validatebox " type="text" name="housetype"
									style="width: 182px;" disabled="true" /></td>
								<td style="width: 30%;"><label for="equipment"
									style="padding-left: 6px;">房间设施: </label> <input
									class="easyui-validatebox " type="text" name="equipment"
									style="width: 182px;" disabled="true" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="usetype"
									style="padding-left: 6px;">房源用途: </label><input
									class="easyui-validatebox " type="text" name="usetype"
									style="width: 182px;" disabled="true" /></td>
								<td style="width: 30%;"><label for="dealType"
									style="padding-left: 6px;">租售方式:</label> <input
									class="easyui-validatebox " type="text" name="dealType"
									style="width: 182px;" disabled="true" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="dayrentpri"
									style="padding-left: 6px;">日供价格:</label> <input
									class="easyui-validatebox " type="text" name="dayrentpri"
									style="width: 182px;" disabled="true" /></td>
								<td style="width: 30%;"><label for="monthrentpri"
									style="padding-left: 6px;">月租价格: </label><input
									class="easyui-validatebox " type="text" name="monthrentpri"
									style="width: 182px;" disabled="true" /></td>
							</tr>
							<tr>

								<td style="width: 30%;"><label for="ElectricStart"
									style="padding-left: 6px;">电表度数:</label> <input
									class="easyui-validatebox " type="text" name="ElectricStart"
									style="width: 182px;" disabled="true" /></td>
								<td style="width: 30%;"><label for="WaterStart"
									style="padding-left: 6px;">水表度数:</label> <input
									class="easyui-validatebox " type="text" name="WaterStart"
									style="width: 182px;" disabled="true" /></td>

							</tr>
						</table>
						<div align="center" style="margin-top: 5%">
							<a id="nextStep" class="easyui-linkbutton" style="width: 90px;">业务办理-></a>
						</div>
					</form>
				</div>


				<!--业务办理信息  -->
				<div title="租房信息" align="center" style="width: 100%; height: 50%"
					fit=true>
					<form id="rentInfoform" action="" method="post"
						style="margin-top: 20px; margin-bottom: 20px">
						<input type="hidden" name="id" value="-1" />
						<table>
							<tr>

								<td style="width: 30%;"><label for="Transactor"
									style="padding-left: 6px;">办理人员:</label> <input
									class="easyui-validatebox " type="text" name="Transactor"
									style="width: 182px;" required="required" /></td>
								<td style="width: 30%;"><label for="CustomerName"
									style="padding-left: 6px;">租客姓名:</label> <select
									id="CustomerName" name="CustomerName" style="width: 182px;">
								</select></td>

							</tr>

							<tr>

								<td style="width: 30%;"><label for="CustomerCounts"
									style="padding-left: 6px;">房客人数:</label> <input
									class="easyui-numberbox " type="text" name="CustomerCounts"
									style="width: 182px;" required="required" /></td>

								<td style="width: 30%;"><label for="rentType"
									style="padding-left: 6px;">租赁方式: </label> <select id="rentType"
									class="easyui-combobox " name="rentType" panelHeight="100"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_DEALTYPE%>"
									valueField="name" textField="name" value=""
									style="width: 182px; height: auto"></select></td>

							</tr>
							<tr>
								<td style="width: 30%;"><label for="rentstart"
									style="padding-left: 6px;">合同起始: </label> <input id="rentstart"
									name="rentstart" style="width: 182px;"></td>
								<td style="width: 30%;"><label for="rentend"
									style="padding-left: 6px;">合同截止: </label> <input id="rentend"
									name="rentend" style="width: 182px;"></td>

							</tr>


							<tr style="display: none">
								<td style="width: 30%;"><label for="ElectricStart"
									style="padding-left: 6px;">电表度数:</label> <input
									class="easyui-numberbox " type="text" name="ElectricStart"
									style="width: 182px;" required="required" /></td>
								<td style="width: 30%;"><label for="WaterStart"
									style="padding-left: 6px;">水表度数:</label> <input
									class="easyui-numberbox " type="text" name="WaterStart"
									style="width: 182px;" required="required" /></td>
							</tr>
							<tr>
								<!--资费项目设置  -->
								<td style="width: 30%;"><label for="ChargeitemSet"
									style="padding-left: 6px;">资费设置:</label> <select
									id="ChargeitemSet" name="ChargeitemSet" style="width: 182px;">
								</select></td>
							</tr>
						</table>
						<div align="center" style="margin-top: 5%">
							<a id="btnSave" class="easyui-linkbutton" style="width: 90px;">确定</a>
							<a id="btnCancel" class="easyui-linkbutton" style="width: 90px;">关闭</a>
						</div>
					</form>
				</div>



			</div>



		</div>

	</div>
</body>
</html>
