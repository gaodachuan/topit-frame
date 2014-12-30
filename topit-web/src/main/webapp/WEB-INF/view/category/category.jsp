<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common.jspf"%>
<style type="text/css">
.alink {
	padding: 0px 8px 0px 8px;
	font-size: 12px;
	color: #0040ff;
	text-decoration: none;
}

.ilink {
	padding: 0px 8px 0px 8px;
	font-size: 12px;
	color: #999999;
	text-decoration: none;
}

a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	color: #999999;
	text-decoration: none;
}
</style>
<script type="text/javascript">
	$(function() {
		var jsonArray = [];
		$('#category')
				.tree(
						{
							url : 'category.do?method=tree',
							lines : true,
							//dnd:true ,
							//animate : true,
							onSelect : function(node) {
								var tablename = node.tablename;
								var tabId = node.tabId;
								$('#categoryTree')
										.treegrid(
												{
													url : "category.do?method=getTree&tablename="
															+ tablename
															+ "&tabId=" + tabId
												});
							}
						});

		$('#categoryTree')
				.treegrid(
						{
							title : '分类体系分类',
							lines : true,
							nowrap : false,
							rownumbers : true,
							collapsible : true,
							idField : 'id', //数据表格要有主键	
							treeField : 'categoryName', //treegrid 树形结构主键 text
							fitColumns : true,
							columns : [ [
									{
										field : 'categoryName',
										title : '名称',
										width : 120
									},
									{
										field : 'itemId',
										title : '编号',
										width : 120
									},
									{
										field : 'categoryCode',
										title : '代码',
										width : 120
									},
									{
										field : 'description',
										title : '操作',
										width : 120,
										formatter : function(value, record,
												index) {
											//新增分类
											var args = record.id;
											var up;
											var down;
											var sortXtypeId;
											var parentLid;
											var nowId;
											var name;
											var remark;
											var line;
											for (var i = 0; i < jsonArray.length; i++) {
												var obj = jsonArray[i];
												if (obj.id == args) {
													up = obj.up;
													down = obj.down;
													sortXtypeId = record.sortXtypeId;
													line = record.line;
													parentLid =record.parentLid;
													nowId=record.tabId;
													remark=record.remark;
													name= record.categoryName;
													break;
												}
											}

											var buttons = "";
											//添加下级分类
											buttons = buttons
													+ "&thinsp;<a href=\"#\" id=\"addcategory\" class=\"alink\" onclick=\" javascript:addCategory("
													+ nowId + ','
													+ sortXtypeId + ','
													+ "\'添加下级分类\'" + ");\">添加下级分类</a> ";
											//添加同级分类
											buttons = buttons
													+ "&thinsp;<a href=\"#\" id=\"addcategory\" class=\"alink\" onclick=\" javascript:addCategory("
													+ parentLid + ','
													+ sortXtypeId + ','
													+ "\'添加下级分类\'" +");\">添加同级分类</a> ";
											//修改
											buttons = buttons
													+ "&thinsp;<a href=\"#\" id=\"addcategory\" class=\"alink\" onclick=\" javascript:updateCategory("
													+ nowId + ',\''
													+ name + '\',\''
													+ remark + "\');\">修改</a> ";

											buttons = buttons
													+ "&thinsp;"
													+ '<a href=\"#\" id=\"addcategory\" ';
											if (up == null) {
												buttons = buttons
														+ ' class=\"ilink\" onclick=\" javascript:return false;\">'
														+ '上移</a>';
											} else {
												buttons = buttons
														+ ' class=\"alink\" onclick=\" javascript:moveCategory('
														+ args + ',' + line
														+ ',' + up + ','
														+ sortXtypeId + ')\">'
														+ '上移</a>';
											}
											buttons = buttons
													+ '&thinsp;'
													+ '<a href=\"#\" id=\"addcategory\" ';
											if (down == null) {
												buttons = buttons
														+ '  class=\"ilink\" onclick=\" javascript:return false;\">'
														+ '下移</a>';
											} else {
												buttons = buttons
														+ ' class=\"alink\" onclick=\" javascript:moveCategory('
														+ args + ',' + line
														+ ',' + down + ','
														+ sortXtypeId + ')\">'
														+ '下移</a>';
											}

											return buttons;
										}
									} ] ],

							toolbar : [
									{
										text : '新增分类',
										iconCls : 'icon-add',
										handler : function() {
											var id =0;
											var sortXtypeId=$("#category").tree('getSelected');
											var titlename='新增分类';
											
											if(sortXtypeId==null){
												$.messager.show({title : '提示信息!',msg : '请选择类型之后再进行添加!'});
												
											}else{
												
												var tablname =sortXtypeId.tablename;
												if(tablname!='COM_OBJECT_SORT_TYPE'){
													$.messager.show({title : '提示信息!',msg : '不是定义类型无法进行添加!'});
												}
												if(tablname=='COM_OBJECT_SORT_TYPE'){
													addCategory(id,sortXtypeId.tabId,titlename)
												}
											}
											
											
											}
									},
									{
										text : '新增分类体系',
										iconCls : 'icon-add',
										handler : function() {
											flag = 'save';
											$('#categorytypedialog').dialog({title : '设置分类体系'});
											$('#categorytypeform').get(0).reset();
											$('#categorytypedialog').dialog('open');
										}
									} ,{
										text : '修改分类体系',
										iconCls : 'icon-add',
										handler : function() {
											flag = 'update';
											var sortXtypeId=$("#category").tree('getSelected');
											var titlename='设置分类体系';
											
											if(sortXtypeId==null){
												$.messager.show({title : '提示信息!',msg : '请选择类型之后再进行添加!'});
												
											}else{
												
												var tablname =sortXtypeId.tablename;
												if(tablname!='COM_OBJECT_SORT_TYPE'){
													$.messager.show({title : '提示信息!',msg : '不是定义类型无法进行修改!'});
												}
												if(tablname=='COM_OBJECT_SORT_TYPE'){
													var tabId = sortXtypeId.tabId;													
													updateType(tabId);
												}
											}
											
											
											}
									} ],
							loadFilter : function(data, parentId) {
								jsonArray = [];
								buildtree(data, jsonArray)
								return data;
							}
						});
		$('#categoryform-btnCancel').click(function() {
			$('#categorydialog').dialog('close');
		});
		$('#categoryform-btnSave').click(function() {
				if ($('#categoryform').form('validate')) {
					$.ajax({
						type : 'post',
						url : flag == 'save' ? 'category.do?method=save'
								: 'category.do?method=update',
						cache : false,
						data : $('#categoryform').serialize(),
						dataType : 'json',
						success : function(result) {
							$('#categorydialog').dialog('close');
							if (result.errorCode == 0) {
								$.messager.alert('提示信息',
										result.errorDetail, 'info');
								$('#categoryTree').treegrid('reload');
							} else {
								$.messager.alert('提示信息',
										result.errorDetail, 'error');
							}
							
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
		
		
		$('#categorytypeform-btnCancel').click(function() {
			$('#categorytypedialog').dialog('close');
		});
		$('#categorytypeform-btnSave').click(function() {
				if ($('#categorytypeform').form('validate')) {
					$.ajax({
						type : 'post',
						url : flag == 'save' ? 'category.do?method=saveType'
								: 'category.do?method=updateType',
						cache : false,
						data : $('#categorytypeform').serialize(),
						dataType : 'json',
						success : function(result) {
							$('#categorytypedialog').dialog('close');
							if (result.errorCode == 0) {
								$.messager.alert('提示信息',result.errorDetail, 'info');

								$('#category').tree('reload');
							} else {
								$.messager.alert('提示信息',
										result.errorDetail, 'error');
							}

						},
						error : function(result) {
							$.messager.show({
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
	});

	function buildtree(data, jsonArray) {
		var id;
		var up;
		var down;
		var obj;
		for (var j = 0; j < data.length; j++) {
			obj = data[j];
			var a = j - 1;
			var b = j + 1;
			id = obj.tabId;
			if (a >= 0) {
				up = data[a].line;
			} else {
				up = null;
			}
			if ((b < data.length) && (b >= 0)) {
				down = data[b].line;
			} else {
				down = null;
			}
			var arr = {
				"id" : id,
				"up" : up,
				"down" : down
			};
			jsonArray.push(arr);
			if (obj.children != null) {
				jsonArray = buildtree(obj.children, jsonArray)
			}
		}
		return jsonArray;
	}

	function addCategory(id,sortXtypeId,titlename) {

		flag = 'save';
		$('#categorydialog').dialog({title : titlename});
		$('#categoryform').get(0).reset();

		$('#categoryform').form('load', {categoryType : sortXtypeId,parentLid : id});
		$('#categorydialog').dialog('open');
	}
	
	function updateCategory(id,name,remark) {

		flag = 'update';
		$('#categorydialog').dialog({title : '修改分类'});
		$('#categoryform').get(0).reset();

		$('#categoryform').form('load', {name : name,remark : remark,categoryType:id});
		$('#categorydialog').dialog('open');
	}
	
	function updateType(tabId){
		var data = {"id" : tabId};
		var url = "${pageContext.request.contextPath}/category/category.do?method=find";
		$.get(url, data, function(result) {
			var id=result.id;
			var name= result.name;
			var categoryId=result.categoryId;
			var description = result.description;

			$('#categorytypedialog').dialog('open');
			$('#categorytypeform').form('load', {name : name,remark : description,categoryType:categoryId,id:id});
		});
	}
</script>
</head>

<body class="easyui-layout">
	<div region="west" style="width: 200px; resize: none;">
		<div id="p" class="easyui-panel" title="分类导航栏" fit="true"
			collapsible="false" border="false">
			<ul id="category"></ul>
		</div>
	</div>

	<div id="mainPanle" region="center"
		style="overflow: hidden; resize: none;">
		<table id="categoryTree" style="width: 100%; height: auto;"></table>
	</div>

	<div id="categorydialog" modal=true draggable=false
		class="easyui-dialog" closed=true style="width: 480px; height: auto;">
		<div id="dialogtab" class="easyui-tabs" fit=false plain=true
			style="padding: 3%;">
			<div title="基本信息" align="center" style="width: 100%;" fit=true>
				<form id="categoryform" action="" method="post"
					style="margin-top: 20px; margin-bottom: 20px">
					
					<!-- 分类类型 -->
					<input type="hidden" value="" id="categoryType" name="categoryType" />
					<!-- 类型父节点 -->
					<input type="hidden" value="" id="parentLid" name="parentLid" />
					
					<div class="dv-form-row">
						<div class="dv-form-item" style="width: 80%;">
							<label for="name" style="padding-left: 6px;">名称:</label> <input
								class="easyui-validatebox " type="text" name="name"
								style="width: 200px;" required=true /> <span></span>
						</div>
					</div>
					<div class="dv-form-row">
						<div class="dv-form-item" style="width: 80%;">
							<label for="remark" style="padding-left: 6px;">备注:</label> <input
								class="easyui-validatebox " type="text" name="remark"
								style="width: 200px;" required=true /> <span></span>
						</div>
					</div>
					<div class="dv-form-row">
						<div class="dv-form-item" style="width: 80%;">
							<a id="categoryform-btnSave" class="easyui-linkbutton" style="width: 90px;">提交</a>
							<a id="categoryform-btnCancel" class="easyui-linkbutton" style="width: 90px;">取消</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="categorytypedialog" modal=true draggable=false
		class="easyui-dialog" closed=true style="width: 480px; height: auto;">
		<div id="typedialogtab" class="easyui-tabs" fit=false plain=true
			style="padding: 3%;">
			<div title="基本信息" align="center" style="width: 100%;" fit=true>
				<form id="categorytypeform" action="" method="post"
					style="margin-top: 20px; margin-bottom: 20px">

					<input type="hidden" name="id" value="" />
					<div class="dv-form-row">
						<div class="dv-form-item" style="width: 80%;">
							<label for="name" style="padding-left: 6px;">名称:</label> <input
								class="easyui-validatebox " type="text" name="name"
								style="width: 200px;" required=true /> <span></span>
						</div>
					</div>
					<div class="dv-form-row">
						<div class="dv-form-item" style="width: 80%;">
							<label for="type" style="padding-left: 6px;">所属分类:</label> <input name="categoryType" 
							class="easyui-combotree" url="datadic.do?method=getData"  valueField="nodeCode" 
							textField="text" value="" /><span></span>
						</div>
					</div>
					<div class="dv-form-row">
						<div class="dv-form-item" style="width: 80%;">
							<label for="remark" style="padding-left: 6px;">说明:</label> <input
								class="easyui-validatebox " type="text" name="remark"
								style="width: 200px;" required=true /> <span></span>
						</div>
					</div>
					<div class="dv-form-row">
						<div class="dv-form-item" style="width: 80%;">
							<a id="categorytypeform-btnSave" class="easyui-linkbutton" style="width: 90px;">提交</a>
							<a id="categorytypeform-btnCancel" class="easyui-linkbutton" style="width: 90px;">取消</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function moveCategory(args, line, up, type) {
			var data = {
				"args" : args,
				"line" : line,
				"up" : up,
				"type" : type
			};
			var url = "${pageContext.request.contextPath}/category/category.do?method=move";
			$.get(url, data, function(result) {
				$('#categoryTree').treegrid('reload');
			});

		}
	</script>
</body>
</html>
