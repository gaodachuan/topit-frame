<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
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
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/topit.frame.js"></script>
<script type="text/javascript">
	$(function() {
		var flag;
		$('#sys_module')
				.datagrid(
						{
							idField : 'id', //只要创建数据表格 就必须要加 ifField
							title : '模块信息',
							fit : true,
							height : 600,
							url : 'sysmodule.do?method=getList',
							fitColumns : true,
							striped : true, //隔行变色特性 
							loadMsg : '数据正在加载,请耐心的等待...',
							rownumbers : true,
							singleSelect : true,
							columns : [ [
									{
										field : 'id',
										title : '模块编号',
										width : 100,
										align : 'center',
									},
									{
										field : 'name',
										title : '模块名称',
										align : 'center',
										width : 100,
									},
									{
										field : 'modulePath',
										title : '模块路径',
										width : 100,
										align : 'center',
										formatter : function(value, record,
												index) {
											var alink = '<a href=\"#\" id=\"editstatus\" onclick=\" javascript:parent.openModule('
													+ record.id
													+ ",'"
													+ record.name
													+ "','${pageContext.request.contextPath}"
													+ record.modulePath
													+ '\',false)\">'
													+ record.modulePath
													+ '</a>';
											return alink;
										}
									}, {
										field : 'categoryId',
										title : '模块分类',
										width : 100,
										align : 'center',
									}, {
										field : 'createTime',
										title : '创建时间',
										width : 100,
										align : 'center',
										formatter : Common.DateFormatter,
									} ] ],
							pagination : true,
							pageSize : 10,
							pageList : [ 5, 10, 15, 20, 50 ],
							//工具栏
							toolbar : [ {
								text : '新增模块',
								iconCls : 'icon-add',
								handler : function() {
									flag = 'save';
									$('#moduledialog').dialog({
										title : '新增模块'
									});
									$('#moduleform').get(0).reset();
									$('#moduledialog').dialog('open');

								}

							}, {
								text : '编辑模块',
								iconCls : 'icon-edit',
								handler : function() {
									flag = 'edit';
									var arr =$('#sys_module').datagrid('getSelections');
									if(arr.length != 1){
										$.messager.show({
											title:'提示信息!',
											msg:'请选择一行记录进行修改!'
										});
									} else {
										$('#moduledialog').dialog({
											title:'修改模块'
										});
										$('#moduleform').get(0).reset();    
										$('#moduleform').form('load',{	   
											id:arr[0].id ,
											name:arr[0].name ,
											categoryId:arr[0].categoryId ,
											modulePath:arr[0].modulePath ,
											description:arr[0].description
										});
										$('#moduledialog').dialog('open'); 
									}
								

								}

							}, {
								text : '删除模块',
								iconCls : 'icon-remove',
								handler : function() {
									flag = 'del';
									var arr = $('#sys_module').datagrid('getSelections');
									if(arr.length <= 0 ){
										$.messager.show({
											title:'提示信息',
											msg:'请选择进行需要删除的记录!'
										});											
									} else {
										$.messager.confirm('提示信息' , '确认删除?' , function(r){
											if(r){
												var id = '';
												for(var i = 0 ; i < arr.length ; i++){
													id += arr[i].id + ',';
												}
												id = id.substring(0,id.length-1);
												$.post('sysmodule.do?method=del' , {id:id},function(result){
													    $('#sys_module').datagrid('reload');
													    if (result.errorCode == 0) {
															$.messager.alert('提示信息',
																	result.errorDetail, 'info');
														} else {
															$.messager.alert('提示信息',
																	result.errorDetail, 'error');
														}
												});
												
											} else {
												 return ;
											}
										});
									}
								}

							} ]

						});
		$('#btnSave').click(
				function() {
					if ($('#moduleform').form('validate')) {
						$.ajax({
							type : 'post',
							url : flag == 'save' ? 'sysmodule.do?method=save'
									: 'sysmodule.do?method=edit',
							cache : false,
							data : $('#moduleform').serialize(),
							dataType : 'json',
							success : function(result) {
								$('#moduledialog').dialog('close');
								$('#sys_module').datagrid('reload');
								if (result.errorCode == 0) {
									$.messager.alert('提示信息',
											result.errorDetail, 'info');
								} else {
									$.messager.alert('提示信息',
											result.errorDetail, 'error');
								}
								//清空所选择的行
								$('#sys_module').datagrid('unselectAll');

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
			$('#moduledialog').dialog('close');
		});

	})
</script>
</head>

<body>
	<div id="lay" class="easyui-layout" style="width: 100%; height: 600px">
		<div region="north" title="查询条件" collapsible=false padding-top=20px
			padding-bottom=20px style="height: auto;">
			<form id="module_search" method="post" action=""
				style="margin-top: 10px; margin-bottom: 20px">
				<div class="dv-form-row">
					<div class="dv-form-item">
						<label for="moduleName"> 模块名称:</label> <input
							class="easyui-validatebox " type="text" name="moduleName"
							style="width: 200px;" required="true" /> <label for="moduleType">模块分类:</label>
						<input class="easyui-validatebox " type="text" name="moduleType"
							style="width: 200px;" required="true" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="dosearch" href="#" class="easyui-linkbutton"
							data-options="iconcls:'icon-search'" style="width: 90px;">搜索</a>
						&nbsp;&nbsp;<a id="reset" href="#" class="easyui-linkbutton"
							data-options="iconcls:'icon-search'" style="width: 90px;">重置</a>
					</div>
				</div>
			</form>
		</div>
		<div region="center">
			<table id="sys_module"></table>
		</div>
		<div id="moduledialog" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 480px; height: auto;">
			<div id="dialogtab" class="easyui-tabs" fit=false plain=true
				style="padding: 3%;">
				<div title="模块信息" align="center" style="width: 100%;" fit=true>
					<form id="moduleform" action="" method="post"
						style="margin-top: 20px; margin-bottom: 20px">

						<input type="hidden" name="id" value="" />
						<div class="dv-form-row">
							<div class="dv-form-item" style="width: 80%;">
								<label for="name" style="padding-left: 6px;">模块名称:</label> <input
									class="easyui-validatebox " type="text" name="name"
									style="width: 200px;" required=true /> <span></span>
							</div>
						</div>
						<div class="dv-form-row">
							<div class="dv-form-item" style="width: 80%;">
								<label for="categoryId" style="padding-left: 6px;">模块分类:</label>
								<input class="easyui-validatebox " type="text" name="categoryId"
									style="width: 200px;" required=true /> <span></span>
							</div>
						</div>
						<div class="dv-form-row">
							<div class="dv-form-item" style="width: 80%;">
								<label for="modulePath" style="padding-left: 6px;">模块路径:</label>
								<input class="easyui-validatebox " type="text" name="modulePath"
									style="width: 200px;" required=true /> <span></span>
							</div>
						</div>
						<div class="dv-form-row">
							<div class="dv-form-item" style="width: 80%;">
								<label for="description"
									style="padding-left: 6px; vertical-align: top">模块描述:</label>
								<textarea class="easyui-validatebox " name="description"
									style="width: 200px;" required=true></textarea>
								<span></span>
							</div>
						</div>
						<div class="dv-form-row">
							<div class="dv-form-item" style="width: 80%;">
								<a id="btnSave" class="easyui-linkbutton" style="width: 90px;">确定</a>
								<a id="btnCancel" class="easyui-linkbutton" style="width: 90px;">关闭</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
