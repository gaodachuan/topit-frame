/**
 * 实现菜单设置页面的拖拽
 */

(function($) {
	$.extend($.fn.treegrid.defaults, {
		onBeforeDrag : function(row) {
		}, // return false to deny drag
		onStartDrag : function(row) {
		},
		onStopDrag : function(row) {
		},
		onDragEnter : function(targetRow, sourceRow) {
		}, // return false to deny
		// drop
		onDragOver : function(targetRow, sourceRow) {
		}, // return false to deny
		// drop
		onDragLeave : function(targetRow, sourceRow) {
		},
		onBeforeDrop : function(targetRow, sourceRow, point) {
		},
		onDrop : function(targetRow, sourceRow, point) {
		}, // point:'append','top','bottom'
	});

	$
			.extend(
					$.fn.treegrid.methods,
					{
						enableDnd : function(jq, id) {
							if (!$('#treegrid-dnd-style').length) {
								$('head')
										.append(
												'<style id="treegrid-dnd-style">'
														+ '.treegrid-row-top td{border-top:1px solid red}'
														+ '.treegrid-row-bottom td{border-bottom:1px solid red}'
														+ '.treegrid-row-append .tree-title{border:1px solid red}'
														+ '</style>');
							}
							return jq
									.each(function() {
										var target = this;
										var state = $.data(this, 'treegrid');
										state.disabledNodes = [];
										var t = $(this);
										var opts = state.options;
										if (id) {
											var nodes = opts.finder.getTr(
													target, id);
											var rows = t.treegrid(
													'getChildren', id);
											for (var i = 0; i < rows.length; i++) {
												nodes = nodes
														.add(opts.finder
																.getTr(
																		target,
																		rows[i][opts.idField]));
											}
										} else {
											var nodes = t.treegrid('getPanel')
													.find('tr[node-id]');
										}
										nodes
												.draggable(
														{
															disabled : false,
															revert : true,
															cursor : 'pointer',
															proxy : function(
																	source) {
																var row = t
																		.treegrid(
																				'find',
																				$(
																						source)
																						.attr(
																								'node-id'));
																var p = $(
																		'<div class="tree-node-proxy"></div>')
																		.appendTo(
																				'body');
																p
																		.html('<span class="tree-dnd-icon tree-dnd-no">&nbsp;</span>'
																				+ row[opts.treeField]);
																p.hide();
																return p;
															},
															deltaX : 15,
															deltaY : 15,
															onBeforeDrag : function(
																	e) {
																if (opts.onBeforeDrag
																		.call(
																				target,
																				getRow(this)) == false) {
																	return false
																}
																if ($(e.target)
																		.hasClass(
																				'tree-hit')
																		|| $(
																				e.target)
																				.parent()
																				.hasClass(
																						'datagrid-cell-check')) {
																	return false;
																}
																if (e.which != 1) {
																	return false;
																}
																$(this)
																		.next(
																				'tr.treegrid-tr-tree')
																		.find(
																				'tr[node-id]')
																		.droppable(
																				{
																					accept : 'no-accept'
																				});

															},
															onStartDrag : function() {
																$(this)
																		.draggable(
																				'proxy')
																		.css(
																				{
																					left : -10000,
																					top : -10000
																				});
																var row = getRow(this);
																opts.onStartDrag
																		.call(
																				target,
																				row);
																state.draggingNodeId = row[opts.idField];
															},
															onDrag : function(e) {
																var x1 = e.pageX, y1 = e.pageY, x2 = e.data.startX, y2 = e.data.startY;
																var d = Math
																		.sqrt((x1 - x2)
																				* (x1 - x2)
																				+ (y1 - y2)
																				* (y1 - y2));
																if (d > 3) { // when
																	// drag
																	// a
																	// little
																	// distance,
																	// show
																	// the
																	// proxy
																	// object
																	$(this)
																			.draggable(
																					'proxy')
																			.show();
																	var tr = opts.finder
																			.getTr(
																					target,
																					$(
																							this)
																							.attr(
																									'node-id'));
																	var treeTitle = tr
																			.find('span.tree-title');
																	e.data.startX = treeTitle
																			.offset().left;
																	e.data.startY = treeTitle
																			.offset().top;
																	e.data.offsetWidth = 0;
																	e.data.offsetHeight = 0;
																}
																this.pageY = e.pageY;
															},
															onStopDrag : function() {
																$(this)
																		.next(
																				'tr.treegrid-tr-tree')
																		.find(
																				'tr[node-id]')
																		.droppable(
																				{
																					accept : 'tr[node-id]'
																				});
																for (var i = 0; i < state.disabledNodes.length; i++) {
																	var tr = opts.finder
																			.getTr(
																					target,
																					state.disabledNodes[i]);
																	tr
																			.droppable('enable');
																}
																state.disabledNodes = [];
																var row = t
																		.treegrid(
																				'find',
																				state.draggingNodeId);
																opts.onStopDrag
																		.call(
																				target,
																				row);
															}
														})
												.droppable(
														{
															accept : 'tr[node-id]',
															onDragEnter : function(
																	e, source) {
																if (opts.onDragEnter
																		.call(
																				target,
																				getRow(this),
																				getRow(source)) == false) {
																	allowDrop(
																			source,
																			false);
																	var tr = opts.finder
																			.getTr(
																					target,
																					$(
																							this)
																							.attr(
																									'node-id'));
																	tr
																			.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
																	tr
																			.droppable('disable');
																	state.disabledNodes
																			.push($(
																					this)
																					.attr(
																							'node-id'));
																}
															},
															onDragOver : function(
																	e, source) {
																var nodeId = $(
																		this)
																		.attr(
																				'node-id');
																if ($
																		.inArray(
																				nodeId,
																				state.disabledNodes) >= 0) {
																	return

																	

																																		

																	

																																																			

																	

																																		

																	

																																																																				

																	

																																		

																	

																																																			

																	

																																		

																	

																																																																				

																	

																																		

																	

																																																			

																	

																																		

																	

																}
																var pageY = source.pageY;
																var top = $(
																		this)
																		.offset().top;
																var bottom = top
																		+ $(
																				this)
																				.outerHeight();

																allowDrop(
																		source,
																		true);
																var tr = opts.finder
																		.getTr(
																				target,
																				nodeId);
																tr
																		.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
																if (pageY > top
																		+ (bottom - top)
																		/ 2) {
																	if (bottom
																			- pageY < 5) {
																		tr
																				.addClass('treegrid-row-bottom');
																	} else {
																		tr
																				.addClass('treegrid-row-append');
																	}
																} else {
																	if (pageY
																			- top < 5) {
																		tr
																				.addClass('treegrid-row-top');
																	} else {
																		tr
																				.addClass('treegrid-row-append');
																	}
																}
																if (opts.onDragOver
																		.call(
																				target,
																				getRow(this),
																				getRow(source)) == false) {
																	allowDrop(
																			source,
																			false);
																	tr
																			.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
																	tr
																			.droppable('disable');
																	state.disabledNodes
																			.push(nodeId);
																}
															},
															onDragLeave : function(
																	e, source) {
																allowDrop(
																		source,
																		false);
																var tr = opts.finder
																		.getTr(
																				target,
																				$(
																						this)
																						.attr(
																								'node-id'));
																tr
																		.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
																opts.onDragLeave
																		.call(
																				target,
																				getRow(this),
																				getRow(source));
															},
															onDrop : function(
																	e, source) {
																var dest = this;
																var action, point;
																var tr = opts.finder
																		.getTr(
																				target,
																				$(
																						this)
																						.attr(
																								'node-id'));
																if (tr
																		.hasClass('treegrid-row-append')) {
																	action = append;
																	point = 'append';
																} else {
																	action = insert;
																	point = tr
																			.hasClass('treegrid-row-top') ? 'top'
																			: 'bottom';
																}

																var dRow = getRow(this);
																var sRow = getRow(source);
																if (opts.onBeforeDrop
																		.call(
																				target,
																				dRow,
																				sRow,
																				point) == false) {
																	tr
																			.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
																	return;
																}
																action(sRow,
																		dRow,
																		point);
																tr
																		.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
															}
														});

										function allowDrop(source, allowed) {
											var icon = $(source).draggable(
													'proxy').find(
													'span.tree-dnd-icon');
											icon
													.removeClass(
															'tree-dnd-yes tree-dnd-no')
													.addClass(
															allowed ? 'tree-dnd-yes'
																	: 'tree-dnd-no');
										}
										function getRow(tr) {
											var nodeId = $(tr).attr('node-id');
											return t.treegrid('find', nodeId);
										}
										function append(sRow, dRow) {
											doAppend();
											if (dRow.state == 'closed') {
												t.treegrid('expand',
														dRow[opts.idField]);
											}

											function doAppend() {
												var data = t.treegrid('pop',
														sRow[opts.idField]);
												t
														.treegrid(
																'append',
																{
																	parent : dRow[opts.idField],
																	data : [ data ]
																});
												opts.onDrop.call(target, dRow,
														data, 'append');
											}
										}
										function insert(sRow, dRow, point) {
											var param = {};
											if (point == 'top') {
												param.before = dRow[opts.idField];
											} else {
												param.after = dRow[opts.idField];
											}

											var data = t.treegrid('pop',
													sRow[opts.idField]);
											param.data = data;
											t.treegrid('insert', param);
											opts.onDrop.call(target, dRow,
													data, point);
										}
									});
						}
					});
})(jQuery);

function loadMenuSuccess(row) {
	$(this).treegrid('enableDnd', row && row.Lid > 1 ? row.Lid : null);

}

/**
 * 操作菜单树
 * 
 * @param node
 */

function loadModuleSuccess(node) {
	var root = $('#mudletree').tree('getRoot');
	if (root) {
		var children = $('#mudletree').tree('getChildren', root);
		var iconCls = 'icon-folder';
		var child;
		if (children) {
			for (var i = 0; i < children.length; i++) {
				child = children[i];
				if (child.children) {
					$('#mudletree').tree('update', {
						target : child.target,
						iconCls : iconCls
					});
				}
			}
		}
	}
}

var parentId = -1;
// 开始拖动
function onBeforeDrag(row) {
	if (row.id == 1) {// 顶级菜单不能拖动
		return false;
	}
	parentId = row._parentId;
	return true;
}
// 结束拖动
function onDragEnter(targetRow, sourceRow) {
	// 不同拖到顶级菜单
	if (targetRow.id == 1) {
		return false;
	}
	// 不能拖动当前菜单下
	if (targetRow.moduleid == -1 && targetRow.id == sourceRow._parentId) {
		return false;
	}
	return true;
}
// 开始插入
function onBeforeDrop(targetRow, sourceRow, point) {
	if (targetRow.id == 1) {
		return false;
	}
	// 菜单不能拖动到菜单下形成父子关系
	if (targetRow.moduleid != -1 && point == 'append') {
		return false;
	}
	return true;
}

// 异步跟新tree
function doMenuAction(url, data) {
	var res = false;
	$.post(url, data, function(result) {
		if (result.success) {
			$.messager.show({
				title : '提示：',
				msg : result.errorDetail,
				showType : 'show'
			});
			res = true;
		} else {
			$.messager.alert('错误', '请求失败', 'error');
		}
	}, 'json');

	return res;
}

// 确定插入的位置
function checkNewPosition() {
	var row = $('#MenuItemsList').treegrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请您先选择需要新增菜单项的插入位置！', 'info');
	}
	return row;
}
// 添加新的菜单
function insertNewMenuItem(url, moduleLid, name) {
	var row = checkNewPosition();
	if (row) {
		var parentId = row._parentId;
		if (row.parentId == -1&& moduleLid!=-1) {
			$.messager.alert('提示', '不能插入到根菜单，请先创建或者选择菜单分组！', 'error');
			return 0;
		} else {
			if (row.moduleid == -1) {
				parentId = row.id;
			}
			var data = {
				parentId : parentId,
				moduleLid : moduleLid,
				textname : name,
			};
			doMenuAction(url, data);
			return parentId;
		}

	}
}

// 添加菜单分组
function createFolder(url) {
	var row = checkNewPosition()
	if (row && row.parentId==-1) {
		$.messager.prompt('新增菜单分组', '请输入菜单分组的名称', function(r) {
			if (r) {
				var id = insertNewMenuItem(url, -1, r);
				if (id) {
					setTimeout(function() {
						$('#MenuItemsList').treegrid('reload', id);
					}, 500);

				}

			}
		});
	}
	else{
		$.messager.alert('提示', '不能再菜单分组中嵌套菜单分组！', 'error')
	}
}
// 删除菜单
function doDelete(url) {
	// debugger
	var row = $('#MenuItemsList').treegrid('getSelected');
	parentId = row._parentId;
	if (!row) {
		$.messager.alert('提示', '请先选择需要删除的菜单项！', 'info');

	} else {
		var c = $('#MenuItemsList').treegrid('getChildren', row.id)
		if (c.length == 0) {
			var id = row.id;
			if (id != 1) {
				$.messager.confirm('提示', '您确定要删除选定的菜单项么？', function(r) {
					if (r) {
						var parentId = row._parentId;
						var data = {
							currentId : id
						};
						doMenuAction(url, data);
						$('#MenuItemsList').treegrid('remove', row.id);
						refreshTree(parentId);
					}
				});
			} else {
				$.messager.alert('提示', '对不起，您不能删除菜单的根节点！', 'error');
			}
		} else {
			$.messager.alert('提示', '对不起，不能删除非空文件夹！', 'error');
		}
	}
}

function setMenuNodeIcon(root) {
	var iconCls = 'icon-tree-folder';

	if (root.moduleid == -1) {
		iconCls = 'icon-tree-folder';
	}

	$('#MenuItemsList').treegrid('update', {
		id : root.id,
		row : {
			iconCls : iconCls,
			state : 'closed'
		},
	});

}

function refreshTree() {
	var children = $('#MenuItemsList').treegrid('getChildren', parentId);
	if (children.length == 0) {
		var parent = $('#MenuItemsList').treegrid('find', parentId);
		setMenuNodeIcon(parent);
	}
}
