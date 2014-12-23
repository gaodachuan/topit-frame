<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单设置</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/treegrid_dnd.js"></script>
<script type="text/javascript">
	$(function() {
		$('#mudletree')
				.tree(
						{
							url : '${pageContext.request.contextPath}/MenuOption/LoadMudelList.do',
							animate : true,
							lines : true,
							onLoadSuccess : loadModuleSuccess,
							onDblClick : function(node) {
								if (node && !node.children) {
									var id = insertNewMenuItem(
											'${pageContext.request.contextPath}/MenuOption/addMenuItem.do',
											node.tabId, node.text);
									if (id) {
										setTimeout(function() {
											$('#MenuItemsList').treegrid(
													'reload', id);
										}, 500);
									}

								}
							}
						});

	});

	$(function() {
		$('#MenuItemsList')
				.treegrid(
						{
							url : '${pageContext.request.contextPath}/MenuOption/getMenuOptions.do',
							idField : 'id',
							treeField : 'name',
							nowrap : false,
							border : false,
							toolbar : '#toolbar',
							rownumbers : true,
							singleSelect : true,
							fitColumns : true,
							lines : true,
							columns : [ [ {
								title : '菜单名称',
								field : 'name',
								width : '25%'
							}, {
								field : 'tip',
								title : '模块名称',
								width : '20%',
								align : 'center'
							}, {
								field : 'iconfile',
								title : '图标',
								width : '15%',
								align : 'center'
									
							},{
								field : 'tip',
								title : '提示信息',
								width : '20%',
								align : 'center'
							} ,{
								field : 'lastedittime',
								title : '最后修改时间',
								width : '20%',
								align : 'center',
							    formatter : Common.DateFormatter
							}
							] ],
							onLoadSuccess : loadMenuSuccess,
							onBeforeDrag : onBeforeDrag,
							onBeforeDrop : onBeforeDrop,
							onDragEnter : onDragEnter,
							onDrop : onDrop

						});
	});

	//拖动插入
	function onDrop(targetRow, sourceRow, point) {
		var sourceId = sourceRow.id;
		var targetId = targetRow.id;
		var data = {
			source : sourceId,
			target : targetId,
			point : point
		};
		doMenuAction(
				'${pageContext.request.contextPath}/MenuOption/MoveMenuItem.do',
				data);
		refreshTree();
	}
</script>

</head>
<!--选项分类  -->
<body class="easyui-layout">
	<div region="west" style="width: 200px; resize: none;">
		<div id="p" class="easyui-panel" title="系统模块" fit="true" collapsible="false" border="false">
			<ul id="mudletree"></ul>
		</div>
	</div>
	<!-- 选项列表 -->
	<div id="mainPanle" region="center" style="overflow: hidden; resize: none;" border="false">
		<div id="p1" class="easyui-panel" title="选项列表" fit="true" collapsible="false" border="false">
			<div id="toolbar" class="datagrid-toolbar">
				<a class="easyui-linkbutton " onclick="createFolder('${pageContext.request.contextPath}/MenuOption/addMenuItem.do')" data-options="plain:true,iconCls:'icon-add'"
					href="javascript:void(0)">新增菜单分组</a> <a class="easyui-linkbutton " onclick="doDelete('${pageContext.request.contextPath}/MenuOption/DeleteMenuItem.do')"
					data-options="plain:true,iconCls:'icon-remove'" href="javascript:void(0)">删除选中菜单</a>
			</div>
			<table id="MenuItemsList" style="width: 100%; height: 100%" fit="true"></table>
		</div>
	</div>
</body>
</html>
