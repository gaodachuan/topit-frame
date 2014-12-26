<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统用户</title>
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
   var sysUserForm={
		loginName:function(){
			return document.getElementById("loginName").value;
		},
		password:function(){
			return document.getElementById("password").value;
		},
		realName:function(){
			return document.getElementById("realName").value;
		},
		remark:function(){
			return document.getElementById("remark").value;
		}
   }
</script>
<script type="text/javascript">
   
	$(
			function() {
              //自定义的效验器(验证用户输入信息)
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
				var listAction;
				function comboxselect(array){
					var groupName;
					var groupNames=[];
					$('#SysUserGroup').combobox('clear');
					/*获取用户组Id*/
					groupName=array.groupIds;
					//if(groupName.indexOf(",")==-1){
						groupNames=groupName.split(",");
						console.info(groupNames);
					//}else{
					//	groupNames=groupName;
					//}
		
				
					/*combobox提前选中*/
					for(var i=0;i<groupNames.length;i++){
						var j;
						j=parseInt(groupNames[i]);
						console.info(j);
						$('#SysUserGroup').combobox('select',j);	
					}
				}
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
					loadFilter : function(data){
						listAction=data.listAction;
						if(data.resultPageObject){
							return data.resultPageObject;
						}
					},
					/**frozenColumns:[[
					              {
					            	  field:'ck',
					            	  width:50,
					            	  checkbox:true
					              }  
					                
					              ]],**/
					columns : [ [ {
						field : 'loginName',
						title : '登录名',
						width : 100,
					}, {
						field : 'realName',
						title : '姓名',
						width : 100,
						sortable : true
					}, {
						field : 'groupName',
						title : '隶属用户组',
						width : 100,
						sortable : true,
						formatter:function(value,rec,index){
							var flag=value;
							if(flag==null){
								flag='暂无';
							}else{
							flag='<span style=color:red title='+value+'>';
							flag+='<img src=\'${pageContext.request.contextPath}/icons/man.png\'/>'+value+'</span>'
							}
							return flag;
						}
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
						field : 'lastLoginTime',
						title : '上次登录时间',
						width : 100,
						sortable : true,
					    formatter: Common.DateFormatter
					},{
						field : 'remark',
						title : '备注',
						width : 100,
						sortable : true,
						formatter:function(value){
							if(value==null){
								value="无";
							}
							return '<span style=color:green title='+value+'>'+value+'</span>';
						}
					} ] ],
					singleSelect:true,
					pagination : true,
					pageSize : 10,
					pageList : [1, 5, 10, 15, 20, 50 ],
					//工具栏
					toolbar : [] ,
					onLoadSuccess : function(data){
						
						for (var i=0;i<listAction.length;i++){
							var option=listAction[i];
							if (option.actionId===1){
								$('#sys_user').datagrid('addToolbarItem',[{
									'text' : option.name,
									'iconCls' : 'icon-add',
									'actionId' : option.actionId,
									'handler' : function() {
										flag='add';
										$('#sysuser').dialog({
											title : '新增系统用户'
										});
										$('#sysuserform').get(0).reset();
										$('#sysuser').dialog('open');
									}
								}]);
						}
							if(option.actionId===2){
								$('#sys_user').datagrid('addToolbarItem',[{
									'text' : option.name,
									'iconCls' : 'icon-edit',
									'actionId' : option.actionId,
									handler:function(){
										flag='edit';
									   
										var arr=$('#sys_user').datagrid('getSelections');
										if(arr.length!=1){
											$.messager.show({
											    title:'提示信息！',
											    msg:'只能选择一行记录进行修改！'
											});
										}else{
											$('#sysuser').dialog('open');//打开窗口
											$('#sysuserform').get(0).reset();//清空表单数据
											console.info(arr[0]);
											comboxselect(arr[0]);
											$('#sysuserform').form('load',{ 
										        sysUserId:arr[0].id,
												  vers:arr[0].version,
                                                loginName:arr[0].loginName,
                                                realName:arr[0].realName,  
                                                password:arr[0].passWord,  
                                                repassword:arr[0].passWord,
												remark:arr[0].remark,
                                                AllowLoginWeekDay:arr[0].allowLoginWeekDay,
												}
											
											);
										}
									}
								}]);
							}//end of 2
							if(option.actionId===3){
								$('#sys_user').datagrid('addToolbarItem',[{
									'text' : option.name,
									'iconCls' : 'icon-remove',
									'actionId' : option.actionId,
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
									
								}]);
						
							}
					}
			  }     //end of onLoadSuccess
				}),	
				$('#btnSave')
						.click(
								function() {
									if ($('#sysuserform').form('validate')) {
										$
												.ajax({
													type : 'post',
													url :flag=='add'?'sysuser.do?method=save':'sysuser.do?method=update',
													cache : false,
													
													//data:$('#sysuserform').serializeJson(),
													data : $('#sysuserform').serialize(),
													//data :{loginName:sysUserForm.loginName,password:sysUserForm.password,realName:sysUserForm.realName},	
													dataType : 'json',
													success : function(result) {
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
				/**
				*查询方法
				**/
				$("#dosearch").click(function(){
					var sysUserName=$("#sysUser").val();
					var sysUserGroupId=$("#SysUserGroupSearch").combobox('getValue');
					//console.info(sysUserGroupId);
					$('#sys_user')
									.datagrid('load',{sysUserName:sysUserName,sysUserGroupId:sysUserGroupId});
				});
				//js方法：序列化表单
				/**function serializeForm(form){
					var obj={};
					$.each(form.serializeArray(),function(index){
						if(obj[this['name']]){
							obj[this['name']]=obj[this['name']]+','+this['value'];
						}else{
							obj[this['name']]=this['value'];
						}
					});
					return obj;
				}**/
			})
</script>
 <script type="text/javascript">
 $(function() {
	 $('#SysUserGroup').combobox({
		    multiple:true,
			formatter: function(row){
				var opts = $(this).combobox('options');
				var ti=row[opts.textField];
				var flag;
				flag='<span style=color:red >'
				flag+='<img src=\'${pageContext.request.contextPath}/icons/man.png\'/>'+ti+'</span>'
				return flag;
			}
		});
	 $('#SysUserGroupSearch').combobox({
			formatter: function(row){
				var opts = $(this).combobox('options');
				var ti=row[opts.textField];
				var flag;
				flag='<span style=color:red >'
				flag+='<img src=\'${pageContext.request.contextPath}/icons/man.png\'/>'+ti+'</span>'
				return flag;
			}
		});
	//	var select[];//用于存放多选星期id的的数组
	 $('#AllowLoginWeekDay').combobox({
		 data:[{id:1,val:'星期一'},{id:2,val:'星期二'},{id:3,val:'星期三'},{id:4,val:'星期四'},{id:5,val:'星期五'},{id:6,val:'星期六'},{id:0,val:'星期日'}],
		 valueField:'id',
		 textField:'val',
		 multiple:true,
		 formatter: function(row){
				var opts = $(this).combobox('options');
				var ti=row[opts.textField];
				var flag;
				flag='<span style=color:blue title='+ti+'>'+'<img src=\'${pageContext.request.contextPath}/icons/datebox_arrow.png\'/>'+'&nbsp;&nbsp;&nbsp;'+ti+'</span>'
				return flag;
			}
			//
		
	 /**  onUnselect:function(record){
	    	
	    	select.concat(record.id);
	    	console.info(record.id);
	    	console.info(record.val);
	    	//console.info(record.length);
	    	
	 },
	   onSelect:function(record){
		   for(var key in select)
           {
			   if(select[key]=record){
				   
			   }

           }
	      // console.info(record.id);
	   }**/	
	 });
 })
 </script>
</head>

<body>
<div id="lay" class="easyui-layout" style="width: 100%; height: 100%">
      <div region="north" title="查询条件" collapsible=false padding-top=20px
			padding-bottom=20px style="height: auto;">
			<form id="module_search" method="post" action=" "
				style="margin-top: 10px; margin-bottom: 20px">
				<div class="dv-form-row">
					<div class="dv-form-item">
						<label for="moduleName">用户:</label> <input
							class="easyui-validatebox " type="text" name="sysUser" id="sysUser"
							style="width: 200px;" required="true" /> <label for="moduleType">用户组:</label>
						<input type="text" name="SysUserGroup" style="width:200px;" value="全部" id="SysUserGroupSearch"
							class="easyui-combobox" data-options="valueField:'id',textField:'name',url:'sysUserGroupCombox.do'"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="dosearch" href="#" class="easyui-linkbutton"
							data-options="iconcls:'icon-search'" style="width: 90px;">搜索</a>
						&nbsp;&nbsp;<a id="reset" href="#" class="easyui-linkbutton"
							data-options="iconcls:'icon-search'" style="width: 90px;">重置</a>
					</div>
				</div>
			</form>
		</div>
      <div region="center">
	  <table id="sys_user"></table>
      </div>   
		<div id="sysuser" title="新增系统用户" modal=true draggable=false
			class="easyui-dialog" closed=true style="width: 625px;">
			<form id="sysuserform" action="" method="post">
				<input type="hidden" name="id" value="" />
				<table>
					<tr>
						<td><label style="padding-left: 6px;">登陆名:</label></td>
						<td><input type="text" name="loginName" class="easyui-validatebox" style="width:200px;height:18px" id="loginName"
							required=true missingMessage="(建议用字母或汉字构成用户登录名)" value="" /></td>
							<td></td>
							<td></td> 
 
					</tr>
					<tr>
						<td><label style="padding-left: 6px;">密码:</label></td>
						<td><input id="password" type="password" name="password" style="width:200px;height:18px" id="password"
							class="easyui-validatebox" required=true missingMessage="(建议设置复杂一点的密码，以确保用户安全)"
							validType="" value="" /></td>
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
						<td><input type="text" name="realName" style="width:200px;height:18px" id="realName"
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
						<td colspan="3"><input type="text" name="SysUserGroup" style="width:500px;height:25px" id="SysUserGroup" required="required"
							class="easyui-combobox" data-options="valueField:'id',textField:'name',url:'sysUserGroupCombox.do'"/></td>
						
					</tr>
					<tr >
						<td><label style="padding-left: 6px;">安全登录时间:</label></td>
						<td > <input type="text" name="AllowLoginWeekDay" style="width:200px;height:25px;" id="AllowLoginWeekDay"
							class="easyui-combobox" /></td>
						<td><input type="text" name="AllowLoginTime1" style="width:70px;height:25px;"
							class="easyui-timespinner" data-options=" "  /></td><td>
						<input type="text" name="AllowLoginTime2" style="width:70px;height:25px;"
							class="easyui-timespinner" data-options=" "  /></td>
					</tr>
					
					<tr >
					     <td><label style="padding-left: 6px;">备注:</label></td>
						<td colspan="3"><textarea name="remark" rows="4" cols="68"></textarea></td>
					</tr>
				    <tr>
				     <td><input type="text" name="vers"   id="vers"  value="" style="display:none;" /></td>
				      <td><input type="text" name="sysUserId"   id="sysUserId"  value="" style="display:none;" /></td>
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
