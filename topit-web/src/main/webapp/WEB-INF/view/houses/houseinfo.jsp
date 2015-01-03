<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.housemanagersystem.util.Constant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房源管理</title>
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
							url : 'getHouseInfo.do',
							striped : true, //隔行变色特性 
							loadMsg : '数据正在加载,请耐心的等待...',
							rownumbers : true,
							singleSelect : false,
						
							frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
						    {field:'ck',checkbox:true},
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
								field : 'electricStart',
								title : '电表度数',
								width : 100,
								align : 'center',
								
							},
							{
								field : 'waterStart',
								title : '水表度数',
								width : 100,
								align : 'center',
								
							},{
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
										text : '新增房源',
										iconCls : 'icon-add',
										handler : function() {
											flag = 'save';
											$('#houseInfodialog').dialog({
												title : '新增房源'
											});
											$('#houseInfoform').get(0).reset();
											$('#houseInfodialog')
													.dialog('open');
										}

									},
									{
										text : '编辑房源',
										iconCls : 'icon-edit',
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
													title : '修改模块'
												});
												$('#houseInfoform').get(0)
														.reset();
												$('#houseInfodialog').dialog(
												'open');
												
												$('#houseInfoform').form('load',
																{
																	id : arr[0].id,
																	name : arr[0].name,
																	number:arr[0].number,
																	area: arr[0].area,
																	usetype : arr[0].usetype,
																	decorate : arr[0].decorate,
																	equipment : arr[0].equipment,
																	dayrentpri:arr[0].dayrentpri,
																	monthrentpri:arr[0].monthrentpri,
																	dealType:arr[0].dealType,
																	housetype:arr[0].housetype,
																	description:arr[0].description,
																	ElectricStart:arr[0].electricStart,
																	WaterStart:arr[0].waterStart,
																	rentstart:TimeFormater(arr[0].rentstart),
																	rentend:TimeFormater(arr[0].rentend)
																});
												
										
												
											}
											
											

										}

									},
									{
										text : '删除模块',
										iconCls : 'icon-remove',
										handler : function() {
											flag = 'del';
											var arr = $('#houseinfo_data')
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
																						'delHouseInfo.do',
																						{
																							id : id
																						},
																						function(
																								result) {
																							$(
																									'#houseinfo_data')
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
					if ($('#houseInfoform').form('validate')) {
						$.ajax({
							type : 'post',
							url : flag == 'save' ? 'saveHouseInfo.do'
									: 'updateHouseInfo.do',
							cache : false,
							data : $('#houseInfoform').serialize(),
							dataType : 'json',
							success : function(result) {
								$('#houseInfoform').get(0)
								.reset();
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
			$('#houseInfodialog').dialog('close');
		});
		/**
		  搜索
		
		 */

		$('#dosearch').click(function() {
			var houseName = $('#houseName').val();
			var houseNumber = $('#houseNumber').val();
			$('#houseinfo_data').datagrid('load', {
				name : houseName,
				number : houseNumber
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
						<label for="houseName"> 房源名称:</label> <input
							class="easyui-validatebox " type="text" name="houseName"
							id="houseName" style="width: 200px;" required="true" /> <label
							for="houseNumber">房源编号:</label> <input
							class="easyui-validatebox " type="text" name="houseNumber"
							id="houseNumber" style="width: 200px;" required="true" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
			class="easyui-dialog" closed=true style="width: 50%; height: 60%;">
			<div id="dialogtab" class="easyui-tabs" fit=false plain=true
				style="padding: 3%;">
				<div title="房源信息" align="center" style="width: 100%;" fit=true>
					<form id="houseInfoform" action="" method="post"
						style="margin-top: 20px; margin-bottom: 20px">
						<input type="hidden" name="id" value="-1" />
						
						<input type="hidden" name="rentstart" value="-1" />
						<input type="hidden" name="rentend" value="-1" />
						<table>
							<tr>
								<td style="width: 30%;"><label for="number"
									style="padding-left: 6px;">房源编号:</label> <input
									class="easyui-validatebox" type="text" name="number"
									style="width: 182px;" required=true /></td>
								<td style="width: 30%;"><label for="name"
									style="padding-left: 6px;">房源名称:</label> <input
									class="easyui-validatebox " type="text" name="name"
									style="width: 182px;" required=true /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="area"
									style="padding-left: 6px;">房源面积:</label> <input
									class="easyui-validatebox " type="text" name="area"
									style="width: 182px;" required=true /></td>
								<td style="width: 30%;"><label for="decorate"
									style="padding-left: 6px;">装修情况:</label> <select id="decorate" class="easyui-combobox "
									name="decorate"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_DECORETE%>"
									panelHeight="100"
									valueField="name" textField="name" value=""
									style="width: 188px;height: auto">
								</select></td>

							</tr>
							 <tr>
								<td style="width: 30%;"><label for="housetype"
									style="padding-left: 6px;">房源类型: </label> <select class="easyui-combobox "
									id="housetype" name="housetype"
									panelHeight="100"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_HOUSETYPE%>"
									valueField="name" textField="name" value=""
									style="width: 188px;height: auto">
								</select></td>
								<td style="width: 30%;"><label for="equipment"
									style="padding-left: 6px;">房间设施: </label><select id="equipment" class="easyui-combobox "
									name="equipment"
									panelHeight="100"
									multiple="true"
									separator="|"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_EQUEPMENTS%>"
									valueField="name" textField="name" value=""
									style="width: 188px;height: auto">
								</select></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="usetype"
									style="padding-left: 6px;">房源用途:</label> <select id="usetype" class="easyui-combobox "
									name="usetype"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_USETYPE%>"
									valueField="name" textField="name" value=""
									panelHeight="100"
									style="width: 188px;height: auto"></select></td>
								<td style="width: 30%;"><label for="dealType"
									style="padding-left: 6px;">租售方式:</label> <select id="dealType" class="easyui-combobox "
									name="dealType"
									panelHeight="100"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.HOUSEINFO_DEALTYPE%>"
									valueField="name" textField="name" value=""
									style="width: 188px;height: auto"></select></td>
							</tr>
							<tr>

								<td style="width: 30%;"><label for="ElectricStart"
									style="padding-left: 6px;">电表度数:</label> <input
									class="easyui-validatebox " type="text" name="ElectricStart"
									style="width: 182px;" required=true /></td>
								<td style="width: 30%;"><label for="WaterStart"
									style="padding-left: 6px;">水表度数:</label> <input
									class="easyui-validatebox " type="text" name="WaterStart"
									style="width: 182px;" required=true /></td>

							</tr>
							<tr>
								<td style="width: 30%;"><label for="dayrentpri"
									style="padding-left: 6px;">日供价格:</label> <input
									class="easyui-validatebox " type="text" name="dayrentpri"
									style="width: 182px;" required=true /></td>
								<td style="width: 30%;"><label for="monthrentpri"
									style="padding-left: 6px;">月租价格: </label><input
									class="easyui-validatebox " type="text" name="monthrentpri"
									style="width: 182px;" required=true /></td>
							</tr>
							<tr>
								<td colspan="2"><label for="description"
									style="padding-left: 6px;"> <lable for="dayrentpri"
											style="padding-left: 6px;">备 注:</label> <input
									class="easyui-validatebox " name="description"
									style="width: 188px;" required=true /></td>
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
