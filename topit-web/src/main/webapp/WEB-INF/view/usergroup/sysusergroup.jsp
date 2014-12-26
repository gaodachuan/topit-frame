<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common.jspf"%>
<title>系统用户组</title>
<style type="text/css">
html, body {
	height: 100%;
	overflow: hidden;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/treegrid_dnd.js"></script>
<script type="text/javascript">
	var flag='';
	var groupId;	
	var listAction;
	$(function() {		
		$('#sys_user_group').datagrid({
			idField : 'id',
			title : '用户组管理',
			url : 'sysusergroup.do?method=getList',
			fitColumns : true,
			striped : true,
			loadMsg : '数据正在加载，请耐心等待...',
			rownumbers : true,
			loadFilter : function(data){
				listAction=data.listAction;
				if(data.resultPageObject){
					return data.resultPageObject;
				}
			},
			columns : [ [
					{
						field : 'id',
						title : '编号',
						width : 50,
						align : 'center',
					},
					{
						field : 'name',
						title : '名称',
						width : 100,
						align : 'center',
					},
					{
						field : 'description',
						title : '描述',
						width : 200,
						align : 'center',
					},
					{
						field : 'lastRightEditTime',
						title : '权限设置时间',
						width : 120,
						align : 'center',
						formatter : Common.DateFormatter, 
					},
					{
						field : 'createTime',
						title : '创建时间',
						width : 120,
						align : 'center',
						formatter : Common.DateFormatter,
					},
					{
						field : 'usernum',
						title : '用户数',
						width : 50,
						align : 'center',
					},
					{
						field : 'inactive',
						title : '状态',
						width : 50,
						align : 'center',

						formatter : function(value, record,
								index) {
							var status;
							if (value == 0) {
								status = '<img src=\'${pageContext.request.contextPath}/icons/clear.png\'>禁用';
							} else if (value == 1) {
								status = '<img src=\'${pageContext.request.contextPath}/icons/ok.png\'>正常';
							}
							return status;
						}
					},
					{
						field : 'operation',
						title : '操作',
						width : 150,
						align : 'center',
						formatter : function(value, record,
								index) {
							var authorization = '设置权限';
							var status = '';
							var del = '删除';

							if (record.inactive == 0) {
								status = '启用';
							} else if (record.inactive == 1) {
								status = '停用';
							}

							var changestatus=0;
							var setAuthorization=0;
							var str='';
							for (var i=0;i<listAction.length;i++){
								var option=listAction[i];
								if(option.actionId===4 && changestatus===0){
									changestatus++;
									str += '<a href=\"javascript:void(0)\" id=\"editstatus\" onclick=\"changestatus('
									+ record.id
									+ ')\">'
									+ status
									+ '</a>&nbsp;';
								}
								if(option.actionId===5 && setAuthorization===0){
									setAuthorization++;
									str += '<a href=\"javascript:void(0)\" id=\"setAuthorization\" onclick=\"setAuthorization('
									+ record.id
									+ ')\">'
									+ authorization
									+ '</a>&nbsp;';
								}
							}
							
							if (str.length===0){
								return '无操作';
							}else{
								return str;
							}
						}
					} ] ],
					singleSelect:true,
					pagination : true,
					pageSize : 5,
					pageList : [ 5, 10, 15, 20, 50 ],
					toolbar : [],
					onLoadSuccess : function(data){
						for (var i=0;i<listAction.length;i++){
							var option=listAction[i];
							if (option.actionId===1){
								$('#sys_user_group').datagrid('addToolbarItem',[{
									'text' : option.name,
									'iconCls' : 'icon-add',
									'actionId' : option.actionId,
									'handler' : function() {
										flag='save';
										$('#sysusergroupdialog').dialog({
											title : '新增用户组'
										});
										$('#sysusergroupform').get(0).reset();
										$('#sysusergroupdialog').dialog('open');
									}
								}]);
							}
							if(option.actionId===2){
								$('#sys_user_group').datagrid('addToolbarItem',[{
									'text':option.name,
									'iconCls':'icon-remove',
									'actionId' : option.actionId,
									'handler':function(){
										var arr=$('#sys_user_group').datagrid('getSelections');
										if(arr.length<1){
											$.messager.show({
												title:'提示信息',
												msg:'请选择一行数据进行删除！'
											});
										}else{
											$.messager.confirm('确认对话框', '您确定删除选定记录吗？', function(r){
												if (r){
												    var ids='';
													for(var i=0;i<arr.length;i++){
												    	ids+=arr[i].id+',';
												    }
												    ids=ids.substring(0, ids.length-1);
												    $.ajax({
														type : 'post',
														url : '${pageContext.request.contextPath}/usergroup/delete/'
																+ ids + '.do',
														success : function(result) {
															//刷新
															$('#sys_user_group').datagrid('reload');
															//清空所选择的行
															$('#sys_user_group').datagrid('unselectAll');
															if (result.errorCode == 0) {
																$.messager.show({
																	title:'提示信息',
																	msg:result.errorDetail
																});
															} else {
																$.messager.show({
																	title:'提示信息',
																	msg:result.errorDetail
																});
															}	
														}
													});
												}
											});
										}
									}
								}]);
							}
							if(option.actionId===3){
								$('#sys_user_group').datagrid('addToolbarItem',[{
									'text' : option.name,
									'iconCls' : 'icon-edit',
									'actionId' : option.actionId,
									'handler' : function() {
										flag='edit';
										var arr=$('#sys_user_group').datagrid('getSelections');
										if(arr.length!=1){
											$.messager.show({
												title:'提示信息',
												msg:'需选择一行进行修改！'
											});
										}else{
											$('#sysusergroupdialog').dialog({
												title : '修改用户组'
											});
											$('#sysusergroupdialog').dialog('open');
											$('#sysusergroupform').get(0).reset();
											$('#sysusergroupform').form('load',{
												id:arr[0].id,
												name : arr[0].name,
												description : arr[0].description
											});
											
										}								
									}
								}]);
							}
						}
						$('#sys_module').datagrid("resize");
					}
			
		});
			
		$('#btnSave').click(function() {
			if ($('#sysusergroupform').form('validate')) {
				$.ajax({
					type : 'post',
					url : flag=='save'?'${pageContext.request.contextPath}/usergroup/save.do':'${pageContext.request.contextPath}/usergroup/edit.do',
					cache : false,
					data : $('#sysusergroupform')
							.serialize(),
					dataType : 'json',
					success : function(result) {
						$('#sysusergroupdialog').dialog('close');
						//刷新
						$('#sys_user_group').datagrid('reload');
						if (result.errorCode == 0) {
							$.messager.show({
								title:'提示信息',
								msg:result.errorDetail
							});
							
						} else {
							$.messager.show({
								title:'提示信息',
								msg:result.errorDetail
							});
						}
						//清空所选择的行
						$('#sys_user_group').datagrid('unselectAll');
					}									
				});
			} else {
				$.messager.show({
					title : '提示信息!',
					msg : '数据验证不通过,不能保存!'
				});
			}
		});

		$('#btnCancel').click(function() {
			$('#sysusergroupdialog').dialog('close');
		});		
	});

//更改用户组禁用状态
function changestatus(id){

	$.messager.confirm('提示','您确认更改用户组状态吗？',function(r){    
	    if (r){    
	    	$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/usergroup/changestatus.do',
				cache : false,
				data :{
					id : id
				},
				dataType : 'json',
				success : function(result) {
					if (result.errorCode == 0) {
						$.messager.show({
							title:'提示信息',
							msg:result.errorDetail
						});
					} else {
						$.messager.show({
							title:'提示信息',
							msg:result.errorDetail
						});
					}
					//刷新
					$('#sys_user_group').datagrid('reload');
					//清空所选择的行
					$('#sys_user_group').datagrid('unselectAll');
				}
			});   
	    }    
	}); 
}


//设置权限函数
function setAuthorization(id){
	
	groupId='';
	groupId = id;

	/*  #####表格的加载######  */
	$('#sys_module_action').datagrid({	
		data : null,
		idField : 'actionId',
		fitColumns : true,
		rownumbers : true,
		toolbar : [
		        {
		        	text : '保存选定操作权',
					iconCls : 'icon-save',
					handler : function() {
						$.messager.confirm('确认对话框', '确定更改当前模块的权限吗？', function(r){
							if (r){
								var str='';
								var getChecked = $('#sys_module_action').datagrid('getChecked');
								var treeNode=$('#mudletree').tree('getSelected');
								var moduleId=treeNode.tabId;
								for(var i=0;i<getChecked.length;i++){
									if((typeof getChecked[i])!='undefined'){
										console.log(getChecked[i]);
										var checked=getChecked[i];
										str+='{"groupId" : '+groupId+' ,'+'"moduleId" : '+checked.moduleId+' ,'+' "actionId" : '+checked.actionId+'},';
									}	
								}
								str='['+str.substr(0,str.length-1)+']';
								$.ajax({
									type : 'post',
									url : '${pageContext.request.contextPath }/sysmoduleaction/saveCheckedAuthorization.do',
									cache : false,
									data : {
										getChecked : str,
										groupId : groupId,
										treeSelected : moduleId
									},
									dataType : 'json',
									success : function(result){
										if (result.errorCode == 0) {
											$.messager.show({
												title:'提示信息',
												msg:result.errorDetail
											});
										} else {
											$.messager.show({
												title:'提示信息',
												msg:result.errorDetail
											});
										}
										$('#sys_module_action').datagrid('reload');
										//更新权限操作按钮
										$('#sys_user_group').datagrid({
											toolbar : []
										});
										$('#sys_user_group').datagrid('reload');
										$('#sys_module').datagrid("resize");
									}		
								});
							}
						});
					}
		        	   
		        },{
		        	text : '保存选定模块具备所有操作权',
					iconCls : 'icon-save',
					handler : function() {
						var node = $('#mudletree').tree('getChecked');
						if(node.length != 0){
							var selectedModuleNums=0;
							for(var i=0;i<node.length;i++){
								if(node[i].tablename === 'SYS_MODULE'){
									selectedModuleNums++;
								}
							}
							$.messager.confirm('确认对话框', '确定更改选定的'+selectedModuleNums+'个模块的权限吗？', function(r){
								if (r){
									for (var i=0;i<node.length;i++){
										var object = node[i];
										if(object.tablename === 'SYS_MODULE'){
											$.ajax({
												type : 'post',
												url : '${pageContext.request.contextPath }/sysmoduleaction/saveAllAuthorization.do',
												cache : false,
												data : {
													tabId : object.tabId,
													groupId : groupId
												},
												dataType : 'json',
												success : function(result){
													getChecked=[];
													getUnchecked=[];										
													if (result.errorCode == 0) {
														$.messager.show({
															title:'提示信息',
															msg:result.errorDetail
														});
													} else {
														$.messager.show({
															title:'提示信息',
															msg:result.errorDetail
														});
													}
													$('#sys_module_action').datagrid('selectAll');
													$('#sys_module_action').datagrid('reload');
													//更新权限操作按钮
													$('#sys_user_group').datagrid({
														toolbar : []
													});
													$('#sys_user_group').datagrid('reload');
													$('#sys_module').datagrid("resize");
												}		
											});
										}	
									}
								}
							})							
						}else{
							$.messager.alert('提示','请先选定模块再进行保存操作！','warning');
						}
					}   
		        }
			],
			columns : [[
				{
					field : 'ck',
					checkbox : true 
				},{
					field : 'name',
					title : '操作',
					width : 50,
					align : 'center'
				},{
					field : 'description',
					title : '说明',
					width : 100,
					align : 'center'
				},{
					field : 'groupId',
					title : '用户Id',
					width : 50,
					align : 'center',
					hidden : true
				}              
			]],
			onLoadSuccess : function(data){
				$('#sys_module_action').datagrid('clearChecked');
				$.each(data.rows,function(index,item){
						if(item.groupId != 0){
							$('#sys_module_action').datagrid('checkRow',index);
						}
				});	
			},
			onUncheck : function(rowIndex,rowData){
				if (rowData.groupId===1 && rowData.moduleId===24 && rowData.actionId===5) {
					$.messager.alert('提示消息','超级管理员的该权限无法取消！','warning');
					$('#sys_module_action').datagrid('checkRow',rowIndex); 

				}	
			}
		});
		
		/*  #####模块树的展现######  */
		 $('#mudletree').tree({
			url:'${pageContext.request.contextPath}/usergroup/LoadMudelList.do',
			animate : true,
			lines : true,
			checkbox : true,
			onLoadSuccess : function(node, data){
				//将节点转化成DOM对象
				var obj = document.getElementById(getChildren(data).domId); 
				$('#mudletree').tree('select',obj);
			},
			onSelect : function(node){
				//取出对应sys_module（系统模块）表中模块的Id
				var tabId = node.tabId;		
				$.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath }/sysmoduleaction/getlist.do',
					cache : false,
					data : {
						tabId : tabId,
						groupId : groupId
					},
					dataType : 'json',
					success : function(result){
						$('#sys_module_action').datagrid({
							data : result,	
						});
					}
				});
			}
		});
	
		//打开对话框
		 $('#authorization').dialog('open');
		
		 
		
		 
}

//通过递归，找到第一个叶子节点
var getChildren=function(data){
	for(var i=0;i<data.length;i++){
		if (data[i].children != null){
			for (var j=0;j<data[i].children.length;j++){
				getChildren((data[i].children)[j]);
				return ((data[i].children)[j]).children[0];
			}
		}
	};
}

</script>
</head>
<body>
	<%--  ********easyui-layout**********  --%>
	<div id="lay" class="easyui-layout" style="width: 100%; height: 100%">
		<%--  ********中心页面布局************ --%>
		<div region="center">
			<table fit=true id="sys_user_group"></table>
		</div>
		<div id="sysusergroupdialog" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 350px;height:250px;">
			<div id="dialogtab" plain=true class="easyui-tabs" fit=true>
				<div title="用户组信息" align="center">   
					<form id="sysusergroupform" action="" method="post">
						<input type="hidden" name="id" value="" />
						<table >
							<tr align="right">
								<td>用户组名称</td>
								<td></td>
							</tr>
							<tr align="left">
								<td></td>
								<td><input type="text" name="name" class="easyui-validatebox" 
								required=true missingMessage="用户组名称必填！" /></td>
							</tr>
							<tr align="right">
								<td>用户组描述</td>
								<td></td>
							</tr>
							<tr align="left">
								<td></td>
								<td><textarea name="description" cols="21" rows="5"></textarea></td>
							</tr>
							<tr align="center">
								<td colspan="2">
									<a id="btnSave" class="easyui-linkbutton">确定</a>
									<a id="btnCancel" class="easyui-linkbutton">关闭</a>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		
		<!-- ######权限设置面板######## -->
		
		<div id="authorization" class="easyui-dialog" title="用户组权限设置" style="width:800px;height:600px" 
		data-options="iconCls:'icon-man',closed:true,modal:true">
			<div id="authorization_lay" class="easyui-layout" style="width:100%;height:100%">
				<div region="west" title="系统模块权限" style="width:200px;border:false" collapsible=false>
					<ul id="mudletree"></ul>
				</div>	
				<div region="center">
					<div id="moduleaction" plain=true class="easyui-tabs" fit=true border=false>
						<div title="模块操作权限" align="center" id="tablediv">
							<table id="sys_module_action" fit=true></table>
						</div>
					</div>
				</div>
			</div>		
		</div>		
	</div>
</body>
</html>