<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	$(function() {

		$('#sys_user_group')
				.datagrid(
						{
							idField : 'id',
							title : '用户组管理',
							url : 'sysusergroup.do?method=getList',
							fitColumns : true,
							striped : true,
							loadMsg : '数据正在加载，请耐心等待...',
							rownumbers : true,
							/* frozenColumns : [ [ {
								field : 'checkbox',
								checkbox : true
							}

							] ], */
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
											/* console.info(value);
											console.info(record);
											console.info(index); */
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
											/* console.info('<a href=\"'+record.id+'\">增加</a>'); */
											var authorization = '设置权限';
											var status = '';
											var del = '删除';

											if (record.inactive == 0) {
												status = '启用';
											} else if (record.inactive == 1) {
												status = '停用';
											}

											return '<a href=\"javascript:void(0)\" id=\"setAuthorization\" onclick=\"setAuthorization('
													+ record.id
													+ ')\">'
													+ authorization
													+ '</a>&nbsp'
													+ '<a href=\"javascript:void(0)\" id=\"editstatus\" onclick=\"changestatus('
													+ record.id
													+ ')\">'
													+ status
													+ '</a>';
										}
									} ] ],
							singleSelect:true,
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 50 ],

							toolbar : [ {
								text : '新增用户组',
								iconCls : 'icon-add',
								handler : function() {
									flag='save';
									$('#sysusergroupdialog').dialog({
										title : '新增用户组'
									});
									$('#sysusergroupform').get(0).reset();
									$('#sysusergroupdialog').dialog('open');
								}
							},{
								text : '修改用户组',
								iconCls : 'icon-edit',
								handler : function() {
									flag='edit';
									var arr=$('#sys_user_group').datagrid('getSelections');
									if(arr.length!=1){
										$.messager.show({
											title:'提示信息',
											/* msg:'请至少选择一行数据！' */
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
							},{
								text:'删除选定用户组',
								iconCls:'icon-remove',
								handler:function(){
									var arr=$('#sys_user_group').datagrid('getSelections');
									if(arr.length<1){
										$.messager.show({
											title:'提示信息',
											/* msg:'请至少选择一行数据！' */
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
							}]

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

		//权限对话框关闭后，隐藏操作权限表格
		$('#authorization').dialog({
			onBeforeClose : function(){
				$("#tablediv").hide();
			}
		});
		
		//权限对话框
		$('#authorization').dialog({
			onOpen : function(){
				
				/*  #####表格的加载######  */
				$('#sys_module_action').datagrid({	
					idField : 'actionId',
					fitColumns : true,
					rownumbers : true,
					toolbar : [
					        {
					        	text : '保存选定操作权',
								iconCls : 'icon-save',
								handler : function() {
									var ModuleNode=$('#moduletree').tree('getChecked');
									var ModuleActionNode=$('#sys_module_action').datagrid('getChecked');
									console.log(ModuleNode+ModuleActionNode);
								}
					        	   
					        },{
					        	text : '保存选定模块具备所有操作权',
								iconCls : 'icon-save',
								handler : function() {
									var node = $('#mudletree').tree('getChecked');
									if(node.length != 0){
										for (var i=0;i<node.length;i++){
											var object = node[i];
											if(object.tablename === 'SYS_MODULE'){
												$.ajax({
													type : 'post',
													url : '${pageContext.request.contextPath }/sysmoduleaction/saveAllAuthorization.do?',
													cache : false,
													data : {
														tabId : object.tabId,
														groupId : groupId
													},
													dataType : 'json',
													success : function(result){
														//清空所选择的行
														$('#sys_module_action').datagrid('unselectAll');
														//刷新
														$('#sys_module_action').datagrid('reload');
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
											
										}
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
						
					}
				});
				
				/*  #####模块树的展现######  */
				 $('#mudletree').tree({
					url:'${pageContext.request.contextPath}/MenuOption/LoadMudelList.do',
					animate : true,
					lines : true,
					//onLoadSuccess : loadModuleSuccess,
					checkbox : true,
					onSelect : function(node){
						//取出对应sys_module（系统模块）表中模块的Id
						var tabId = node.tabId;
						$('#sys_module_action').datagrid({
						url : '${pageContext.request.contextPath }/sysmoduleaction/getlist.do?tabId='+tabId+'&groupId='+groupId,	
						});
						$("#tablediv").show();
					}
				}); 
				
				
				
				
				}
		});
	});

function changestatus(id){

	$.messager.confirm('提示','您确认更改用户组状态吗？',function(r){    
	    if (r){    
	    	$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/usergroup/changestatus/'
						+ id + '.do',
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



function setAuthorization(id){
	groupId='';
	groupId = id;
	$('#authorization').dialog('open');	
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