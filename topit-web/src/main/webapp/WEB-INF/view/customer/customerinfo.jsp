<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.housemanagersystem.util.Constant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户管理</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript">
	$(function() {
		var flag;
		$('#customerinfo_data')
				.datagrid(
						{
							idField : 'id', //只要创建数据表格 就必须要加 ifField
							title : '客户信息',
							fit : true,
							height : 600,
							url : 'getCustomerInfo.do',
							striped : true, //隔行变色特性 
							loadMsg : '数据正在加载,请耐心的等待...',
							fitColumns :true,
							rownumbers : true,
							singleSelect : false,
						
							frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
						    {field:'ck',checkbox:true},
							{
								field : 'id',
								title : '客户编号',
								width : 100,
								align : 'center',
								hidden : true
							},{
								field : 'name',
								title : '客户名称',
								align : 'center',
								width : 100,
							} ] ],
							columns : [ [

							{
								field : 'ethnic',
								title : '名族',
								width : 100,
								align : 'center',

							}, {
								field : 'certificatestype',
								title : '证件类型',
								width : 100,
								align : 'center',
							}, {
								field : 'certificatesvalue',
								title : '证件号码',
								width : 100,
								align : 'center',
							}, {
								field : 'sex',
								title : '性别',
								width : 150,
								align : 'center',
							}, {
								field : 'hukou',
								title : '户籍',
								width : 100,
								align : 'center',
							}, {
								field : 'telnumber',
								title : '电话号码',
								width : 100,
								align : 'center',
							}, {
								field : 'occupation',
								title : '现行职业',
								width : 100,
								align : 'center',
							},
							{
								field : 'workplace',
								title : '工作单位',
								width : 150,
								align : 'center',
							},{
								field : 'regintime',
								title : '登记时间',
								width : 150,
								align : 'center',
								formatter : Common.DateFormatter
							}, {
								field : 'isruzhu',
								title : '是否入住',
								width : 100,
								align : 'center',
								sortable : true
							}, 
							 {
								field : 'description',
								title : '备注',
								width : 100,
								align : 'center',
								sortable : true
							}] ],
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 50 ],
							//工具栏
							toolbar : [
									{
										text : '新增客户',
										iconCls : 'icon-add',
										handler : function() {
											flag = 'save';
											$('#customerInfodialog').dialog({
												title : '新增客户'
											});
											$('#customerInfoform').get(0).reset();
											$('#customerInfodialog')
													.dialog('open');
										}

									},
									{
										text : '编辑客户信息',
										iconCls : 'icon-edit',
										handler : function() {
											flag = 'edit';
											var arr = $('#customerinfo_data')
													.datagrid('getSelections');
											if (arr.length != 1) {
												$.messager.show({
													title : '提示信息!',
													msg : '请选择一行记录进行修改!'
												});
											} else {
												$('#customerInfodialog').dialog({
													title : '修改模块'
												});
												$('#customerInfoform').get(0)
														.reset();
												$('#customerInfodialog').dialog(
												'open');
												$('#customerInfoform').form('load',
																{
																	id : arr[0].id,
																	name : arr[0].name,
																	ethnic:arr[0].ethnic,
																	certificatestype: arr[0].certificatestype,
																	certificatesvalue : arr[0].certificatesvalue,
																	workplace:arr[0].workplace,
																	occupation:arr[0].occupation,
																	sex : arr[0].sex,
																	hukou : arr[0].hukou,
																	telnumber:arr[0].telnumber,
																	regintime:arr[0].regintime,
																	isruzhu:arr[0].isruzhu,
																	description:arr[0].description
																});
												
												
											}

										}

									},
									{
										text : '删除模块',
										iconCls : 'icon-remove',
										handler : function() {
											flag = 'del';
											var arr = $('#customerinfo_data')
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
																						'delCustomerInfo.do',
																						{
																							id : id
																						},
																						function(
																								result) {
																							$(
																									'#customerinfo_data')
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
					if ($('#customerInfoform').form('validate')) {
						$.ajax({
							type : 'post',
							url : flag == 'save' ? 'saveCustomerInfo.do'
									: 'updateCustomerInfo.do',
							cache : false,
							data : $('#customerInfoform').serialize(),
							dataType : 'json',
							success : function(result) {
								$('#customerInfoform').get(0)
								.reset();
								$('#customerInfodialog').dialog('close');
								$('#customerinfo_data').datagrid('reload');
								if (result.errorCode == 0) {
									$.messager.alert('提示信息',
											result.errorDetail, 'info');
								} else {
									$.messager.alert('提示信息',
											result.errorDetail, 'error');
								}
								//清空所选择的行
								$('#customerinfo_data').datagrid('unselectAll');

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
			$('#customerInfodialog').dialog('close');
		});
		/**
		  搜索
		
		 */

		$('#dosearch').click(function() {
			var customerName = $('#customerName').val();
			var customer_isruzhu = $('#customer_isruzhu').val();
			$('#customerinfo_data').datagrid('load', {
				name : customerName,
				isruzhu : customer_isruzhu
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
						<label for="customerName"> 客户姓名:</label> <input
							class="easyui-validatebox " type="text" name="customerName"
							id="customerName" style="width: 200px;" required="true" /> <label
							for="customer_isruzhu">是否入住:</label> <input
							class="easyui-validatebox " type="text" name="customer_isruzhu"
							id="customer_isruzhu" style="width: 200px;" required="true" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="dosearch" href="#" class="easyui-linkbutton"
							data-options="iconcls:'icon-search'" style="width: 90px;">搜索</a>
					</div>
				</tr>
			</form>
		</div>
		<div region="center">
			<table id="customerinfo_data"></table>
		</div>
		<div id="customerInfodialog" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 50%; height: 55%;">
			<div id="dialogtab" class="easyui-tabs" fit=false plain=true
				style="padding: 3%;">
				<div title="房源信息" align="center" style="width: 100%;" fit=true>
					<form id="customerInfoform" action="" method="post"
						style="margin-top: 20px; margin-bottom: 20px">
						<input type="hidden" name="id" value="-1" />
						<table>
							<tr>
								<td style="width: 30%;"><label for="name"
									style="padding-left: 6px;">客户姓名:</label> <input
									class="easyui-validatebox" type="text" name="name"
									style="width: 182px;" required="true" /></td>
								<td style="width: 30%;"><label for="ethnic"
									style="padding-left: 6px;">所属民族:</label> <select id="ethnic"
									class="easyui-combobox " name="ethnic"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.CUSTOMER_ETHNIC%>"
									panelHeight="100" valueField="name" textField="name" value=""
									style="width: 188px; height: auto"></select></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="certificatestype"
									style="padding-left: 6px;">证件类型:</label> <select
									id="certificatestype" class="easyui-combobox "
									name="certificatestype"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.CUSTOMER_CERTIFICATESTYPE%>"
									panelHeight="100" valueField="name" textField="name" value=""
									style="width: 188px; height: auto"></select></td>
								<td style="width: 30%;"><label for="certificatesvalue"
									style="padding-left: 6px;">证件号码:</label> <input
									class="easyui-validatebox" type="text" name="certificatesvalue"
									style="width: 182px;" required="true" /></td>

							</tr>
							<tr>
								<td style="width: 30%;"><label for="sex"
									style="padding-left: 6px;">客户性别: </label> <select
									class="easyui-combobox " id="sex" name="sex" panelHeight="100"
									url="getComboxdata.do?type=<%=Constant.Comboxdata.CUSTOMER_SEX%>"
									valueField="name" textField="name" value=""
									style="width: 188px; height: auto">
								</select></td>
								<td style="width: 30%;"><label for="hukou"
									style="padding-left: 6px;">客户户籍: </label><input
									class="easyui-validatebox" type="text" name="hukou"
									style="width: 182px;" required="true" /></td>
							</tr>


							<tr>
								<td style="width: 30%;"><label for="telnumber"
									style="padding-left: 6px;">电话号码:</label> <input
									class="easyui-validatebox" type="text" name="telnumber"
									style="width: 182px;" required="true" /></td>
								<td style="width: 30%;"><label for="occupation"
									style="padding-left: 6px;">现行职业:</label> <input
									class="easyui-validatebox" type="text" name="occupation"
									style="width: 182px;" required="true" /></td>
							</tr>
							<tr>
								<td style="width: 30%;"><label for="workplace"
									style="padding-left: 6px;">工作单位:</label> <input
									class="easyui-validatebox " type="text" name="workplace"
									style="width: 182px;" required=true /></td>

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
