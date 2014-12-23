<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

<script type="text/javascript" src="../js/topit.frame.js"></script>
<style type="text/css">
html, body {
	height: 100%;
	overflow: hidden;
}
</style>
<script type="text/javascript">
	$(
			function() {
              //自定义的效验器
              $.extend($.fn.validatebox.defaults.rules,{
            	  minLength:{
            		  validator:function(value,param){
            			  return value.length>=param[0];
            		  },
            		  message:'输入至少{0}个字符'
            	  },
            	  equalTo:{
            		  validator:function(value,param){
            			  return $(param[0]).val()==value;
            		  },
            		  message:'字段不匹配'
            	  }
              }
            		  
              );
				var flag;
				$('#sys_user').datagrid({
					idField : 'id', //只要创建数据表格 就必须要加 ifField
					title : '系统用户',
					fit : true,
					height : 600,
					url : 'sysuser.do?method=getList',
					fitColumns : true,
					striped : true, //隔行变色特性 
					loadMsg : '数据正在加载,请耐心的等待...',
					rownumbers : true,
					/**frozenColumns:[[
					              {
					            	  field:'ck',
					            	  width:50,
					            	  checkbox:true
					              }  
					                
					              ]],**/
					columns : [ [ {
						field : 'id',
						title : '编号',
						width : 100,
						align : 'center',
					}, {
						field : 'loginName',
						title : '登录名',
						width : 100,
					}, {
						field : 'realName',
						title : '姓名',
						width : 100,
						sortable : true
					}, {
						field : 'createTime',
						title : '隶属用户组',
						width : 100,
						sortable : true,
						formatter:Common.DateFormatter
					} ,{
						field : 'createTime',
						title : '主属门店',
						width : 100,
						sortable : true,
						formatter:Common.DateFormatter
					} ,{
						field : 'createTime',
						title : '主属部门',
						width : 100,
						sortable : true,
						formatter:Common.DateFormatter
					},{
						field : 'isOnline',
						title : '状态',
						width : 100,
						sortable : true
					},{
						field : 'loginTimes',
						title : '登录次数',
						width : 100,
						sortable : true
					},{
						field : 'lastActivityTime',
						title : '上次登录时间',
						width : 100,
						sortable : true
					},{
						field : 'remark',
						title : '备注',
						width : 100,
						sortable : true
					} ] ],
					pagination : true,
					pageSize : 10,
					pageList : [ 5, 10, 15, 20, 50 ],
					//工具栏
					toolbar : [ {
						text : '新增',
						iconCls : 'icon-add',
						handler : function() {
							flag = 'add';
							$('#sysuser').dialog({
								title : '新增系统用户'
							});
							$('#sysuserform').get(0).reset();
							$('#sysuser').dialog('open');

						}
                         
					} ,{
						text:'修改',
						iconCls:'icon-edit',
						handler:function(){
							flg='edit';
							var arr=$('#sys_user').datagrid('getSelections');
							if(arr.length!=1){
								$.messager.show({
								    title:'提示信息！',
								    msg:'只能选择一行记录进行修改！'
								});
							}else{
								$('#sysuser').dialog('open');//打开窗口
								$('#sysuserform').get(0).reset();//清空表单数据
								$('#sysuserform').form('load',{
									  id:arr[0].id,
									  loginName:arr[0].loginName,
									  realName:arr[0].realName,
									  password:arr[0].passWord,
									  repassword:arr[0].passWord
								});
							}
						}//end of function
					},{
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							var arr=$('#sys_user').datagrid('getSelections');
							if(arr.length<=0){
								$.messager.show({
									title:'提示信息',
									msg:'至少选择一行记录进行删除！'
								});
							}else{
								$.messager.confirm('确认对话框', '您确定删除选定'+arr.length+'条记录吗？', function(r){
									if (r){
								
										var ids='';
										for(var i=0;i<arr.length;i++){
											ids+=arr[i].id+',';
										}
										ids=ids.substring(0,ids.length-1);
										$.post('sysuser.do?method=delete',{ids:ids},function(result){
											//刷新数据表格
											$('#sys_user').datagrid('reload');
											arr.length=0;
											$.messager.show({
												title : result.status,
												msg : result.errorDetail
											});
										}	
										)	
										}// end of if
								  })//end of function
							
							}//if-else
						}
					}]

				});
				$('#btnSave')
						.click(
								function() {
									if ($('#sysuserform').form('validate')) {
										$
												.ajax({
													type : 'post',
													url :flag=='add'?'sysuser.do?method=save':'sysuser.do?method=update',
													cache : false,
													data : $('#sysuserform')
															.serialize(),
													dataType : 'json',
													success : function(result) {
														//alert('ok!'),
														$('#sysuser')
																.dialog('close');
														$('#sys_user')
																.datagrid(
																		'reload');
														$.messager
																.show({
																	title : result.status,
																	msg : result.errorDetail
																});
													},
													error : function(result) {
														$.meesager
																.show({
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
					$('#sysuser').dialog('close');
				});
			})
</script>
</head>

<body>
<div id="lay" class="easyui-layout" style="width: 100%; height: 100%">
           <div region="center">
			<table id="sys_user"></table>
           </div>
           
		<div id="sysuser" title="新增系统用户" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 700px;">
			<form id="sysuserform" action="" method="post">
				<input type="hidden" name="id" value="" />
				<table>
					<tr>
						<td><label style="padding-left: 6px;">登陆名:</label></td>
						<td><input type="text" name="loginName" class="easyui-validatebox" style="width:200px;height:18px"
							required=true missingMessage="(建议用字母或汉字构成用户登录名)" value="" /></td>
							<td></td>
							<td></td> 
 
					</tr>
					<tr>
						<td><label style="padding-left: 6px;">密码:</label></td>
						<td><input id="password" type="password" name="password" style="width:200px;height:18px"
							class="easyui-validatebox" required=true missingMessage="(建议设置复杂一点的密码，以确保用户安全)"
							validType="minLength[5]" value="" /></td>
							<td></td>
							<td></td> 
					</tr>
					<tr>
						<td><label style="padding-left: 6px;">确认密码:</label></td>
						<td><input type="password" name="repassword" style="width:200px;height:18px"
							class="easyui-validatebox" validType="equalTo['#password']" required=true invalidMessage="(两次输入密码不匹配)"
							 missingMessage="(建议设置复杂一点的密码，以确保用户安全)"
							value="" /></td>
							<td></td>
							<td></td>
 
					</tr>
					<tr>
						<td><label style="padding-left: 6px;">姓名：</label></td>
						<td><input type="text" name="realName" style="width:200px;height:18px"
							class="easyui-validatebox" required=true missingMessage="(用户的真实姓名)"
							value="" /></td>
							<td></td>
							<td></td>
					</tr>
					<tr>
						<td><label style="padding-left: 6px;">主属门店:</label></td>
						<td><input type="combox" name="description" style="width:200px;height:25px"
							class="easyui-combobox" /></td>
						<td><label style="padding-left: 6px;">主属部门:</label></td>
						<td><input type="text" name="description" style="width:200px;height:25px"
							class="easyui-combobox" /></td>
							<td></td> 
					</tr>
					<tr>
						<td><label style="padding-left: 6px;">隶属用户组:</label></td>
						<td colspan="3"><input type="text" name="safeLoginTime" style="width:500px;height:25px"
							class="easyui-combobox" /></td>
						
					</tr>
					<tr >
						<td><label style="padding-left: 6px;">安全登录时间:</label></td>
						<td ><input type="text" name="safeLoginTime" style="width:200px;height:25px"
							class="easyui-combobox" /></td>
						<td><input type="text" name="safeLoginTime" style="width:70px;height:25px;float:left"
							class="easyui-combobox" /></td><td>
						<input type="text" name="safeLoginTime" style="width:70px;height:25px;float:left"
							class="easyui-combobox" /></td>
					</tr>
					<tr >
					     <td><label style="padding-left: 6px;">备注:</label></td>
						<td colspan="3"><textarea rows="4" cols="70"></textarea></td>
					</tr>
					<tr align="center">
						<td colspan="2"><a id="btnSave" class="easyui-linkbutton">确定</a>
							<a id="btnCancel" class="easyui-linkbutton">关闭</a></td>
					</tr>
				</table>
			</form>
		</div>
           </div>
</body>
</html>
