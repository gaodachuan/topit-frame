<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单设置</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/treegrid_dnd.js"></script>
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

	var isFirst = true;
	var menuSelect;
	var editRow;
	var MenuIcon;
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
							//fitColumns : true,
							lines : true,
							frozenColumns : [ [ {//冻结列特性 ,不要与fitColumns 特性一起使用 
								title : '菜单名称',
								field : 'name',
								width : '30%'
							}, {
								 
								field : 'iconfile',
								title : '图标',
								width : '20%',
								align : 'center',
								editor:{  
									 type : "combobox",
						                options : {
						                	url:'${pageContext.request.contextPath}/MenuOption/getMenuIcons.do',
						                    valueField : 'name',
						                    textField : 'name',
						                    editable : false,
						                    // required : true,
						                    panelHeight : "auto",
						                    missingMessage : "请选择图标",
						                    formatter : function(row) {
											   var res;										
                                               res='<div align=\'center\'><img src=\'${pageContext.request.contextPath}/icons/menuIcons/'+row.name+'\'><br>'+row.name+'<\/div>';
											   return res;
											},
						                    onSelect : function(record) {					                    	
						                        menuSelect=record.name;						                        
						                        $('#MenuItemsList').treegrid('endEdit', editRow.id);
						                    },
						                }  
						                 
						                }	
							} ] ],
							columns : [ [{
								field : 'tip',
								title : '模块名称',
								width : '20%',
								align : 'center'
							},{
								field : 'tip',
								title : '提示信息',
								width : '20%',
								align : 'center'
							},{
								field : 'lastedittime',
								title : '最后修改时间',
								width : '20%',
								align : 'center',
								formatter : Common.DateFormatter
							} ] ],
							onAfterEdit : function(index, row) {
								$.ajax({
									url : 'setMenuIcon.do',
									data : {
										id : editRow.id,
										name : menuSelect
									},
									success : function(res) {
									}
								});
							},
							onDblClickRow : function(row) {
								if (row.moduleid != -1) {
									editRow = row;
									$('#MenuItemsList').treegrid('beginEdit',
											row.id);
								}

							},
							onLoadSuccess : loadMenuSuccess,
							onBeforeDrag : onBeforeDrag,
							onBeforeDrop : onBeforeDrop,
							onDragEnter : onDragEnter,
							onDrop : onDrop,
							onBeforeLoad : function(row, param) {
								if (isFirst) {
									param.id = "#" + ${menuId.rootitemid};
									isFirst = false;
								}
								return true;
							}

						});
	});

	function loadMenuSuccess(row) {
		$(this).treegrid('enableDnd', row && row.Lid > 1 ? row.Lid : null);

	}

	//拖动插入
	function onDrop(targetRow, sourceRow, point) {
		var sourceId = sourceRow.id;
		var targetId = targetRow.id;
		var data = {
			source : sourceId,
			target : targetId,
			point : point
		};
		doMenuAction('${pageContext.request.contextPath}/MenuOption/MoveMenuItem.do',data);
		refreshTree();
	}
</script>

</head>
<!--选项分类  -->
<body class="easyui-layout">
	<div region="west" style="width: 200px; resize: none;">
		<div id="p" class="easyui-panel" title="系统模块" fit="true"
			collapsible="false" border="false">
			<ul id="mudletree"></ul>
		</div>
	</div>
	<!-- 选项列表 -->
	<div id="mainPanle" region="center"
		style="overflow: hidden; resize: none;" border="false">
		<div id="p1" class="easyui-panel" title="选项列表" fit="true"
			collapsible="false" border="false">
			<div id="toolbar" class="datagrid-toolbar">
				<a class="easyui-linkbutton "
					onclick="createFolder('${pageContext.request.contextPath}/MenuOption/addMenuItem.do')"
					data-options="plain:true,iconCls:'icon-add'"
					href="javascript:void(0)">新增菜单分组</a> <a class="easyui-linkbutton "
					onclick="doDelete('${pageContext.request.contextPath}/MenuOption/DeleteMenuItem.do')"
					data-options="plain:true,iconCls:'icon-remove'"
					href="javascript:void(0)">删除选中菜单</a>
			</div>
			<table id="MenuItemsList" style="width: 100%; height: 100%"
				fit="true"></table>
		</div>
	</div>
</body>
</html>
