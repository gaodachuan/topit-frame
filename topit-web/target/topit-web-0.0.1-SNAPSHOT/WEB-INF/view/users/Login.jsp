<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/easyui.css" />
<link rel="stylesheet" type="text/css" href="../css/icon.css" />
<link rel="stylesheet" type="text/css" href="../css/ManageStyle.css" />
<link rel="stylesheet" type="text/css" href="../css/MyCenter.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.marquee.css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<style type="text/css">
html, body {
	height: 100%;
	overflow: hidden;
}
 .loginbody{
     position:relative; height:100%;
    
 }
 .title{
     position:absolute;
     top:130px;
     right:416px;
 }
 .login{
    position:absolute;
    top:200px;
    right:336px;
 }
</style>
<script type="text/javascript">
    function href(){
    	window.location.href="login.do?method=success";
    }
    $(function(){
    	$('#login')
		.click(
				function() {
					var loginname;
					var passworld;
					loginname=$("#loginName").val();
					password=$("#password").val();
					$.post('login.do?method=login',{loginname:loginname,password:password},function(result){
						$.messager.show({
							title :'提示信息',
						
							msg : result.errorDetail
						});
						if(result.errorCode==0){
							 href()
						}
					}//end of function	
					)	
				});
				
    }) 
</script>
</head>
<body>
    <div class="loginbody">
    <div class="title"><h1 style="color:red">系统登陆</h1></div>
     <div class="login">
     <form id="sysuserform" action="" method="post">
				<table>
					<tr>
						<td><label style="padding-left: 6px;">登陆名:</label></td>
						<td><input id="loginName" type="text" name="loginName" class="easyui-validatebox" style="width:200px;height:18px"
							required=true missingMessage="(用户名不能为空)" value="${SysUser.loginName}" /></td>
					</tr>
					<tr>
						<td><label style="padding-left: 6px;">密码:</label></td>
						<td><input id="password" type="password" name="password" class="easyui-validatebox" style="width:200px;height:18px"
							required=true missingMessage="(密码不能为空)" value="${SysUser.passWord}" /></td>
					</tr>
					<tr align="center">
						<td colspan="2"><a id="login" class="easyui-linkbutton" style="width:80px;height:50px">登陆</a>
					</tr>
				</table>
				</form>
	   </div>
     </div>
     </div>	   		
</body>
</html>