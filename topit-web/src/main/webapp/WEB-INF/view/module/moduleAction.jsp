<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模块操作维护</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript">
	$(function() {
		$('#ModleActionList')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/sysmoduleaction/getAllSysModuleActions.do',
							idField : 'actionId',
							treeField : 'name',
							striped : true, //隔行变色特性 
							loadMsg : '数据正在加载,请耐心的等待...',
							rownumbers : true,
							singleSelect : true,
							lines : true,
							frozenColumns : [ [ {
								title : '操作名称',
								field : 'name',
								width : '15%',
								align : 'center'
							}, {
								field : 'actionId',
								title : '权限编号',
								width : '15%',
								align : 'center'
							},

							] ],
							columns : [ [ {
								field : 'moduleId',
								title : '模块编号',
								width : '15%',
								align : 'center'
							}, {
								field : 'line',
								title : '排序编号',
								width : '10%',
								align : 'center',
							}, {
								field : 'controllerClassName',
								title : '控制器名',
								width : '20%',
								align : 'center',
							}, {
								field : 'actionFunctionName',
								title : '执行方法名',
								width : '20%',
								align : 'center',
							}, {
								field : 'description',
								title : '操作说明',
								width : '20%',
								align : 'center',
							} ] ],
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 50 ],
							//工具栏
							toolbar : [ {
								text : '生成模块操作数据',
								iconCls : 'icon-add',
								handler : function() {
									$.ajax({
										url : 'create.do',
										success : function(result) {
											if (result.errorCode == 0) {
												$.messager.alert('提示信息',
														result.errorDetail,
														'info');
												$('#ModleActionList').datagrid(
														'reload');
											} else {
												$.messager.alert('提示信息',
														result.errorDetail,
														'error');
											}
										}
									});

								}

							} ]

						});

	});
</script>

</head>
<!--选项分类  -->
<body class="easyui-layout">
	<!-- 选项列表 -->
	<div id="mainPanle" region="center" style="overflow: hidden; resize: none;" border="false">
		<div id="p1" class="easyui-panel" title="选项列表" fit="true" collapsible="false" border="false">
			<table id="ModleActionList" style="width: 100%; height: 100%" fit="true"></table>
		</div>
	</div>
</body>
</html>
