<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>整体布局</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript">
     function loginOut(value){
    	 window.location.href=value;
     }
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="true" style="overflow: hidden; height: 60px; resize: none;">
		<iframe align="center" width="100%" height="130px" src="<c:url value='/main/header.do'/>" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</div>
	<div region="south" border="true" style="overflow: hidden; height: 30px; resize: none;">
		<iframe align="center" width="100%" height="130px" src="<c:url value='/main/footer.do'/>" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</div>
	<div region="west" title="导航菜单" style="width: 200px; resize: none;">
		<div id="aa" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px">
			<div title="系统维护" data-options="iconCls:'icon-reload',collapsed:false">
				<ul style="list-style-type: none;">
					
					<li><a href="#" class="easyui-linkbutton" style="width: 90px" data-options="plain:true,iconCls:'icon-large-picture',size:'large',iconAlign:'top'"
						onclick="openModule(1, '模块维护','${pageContext.request.contextPath}/module/module.do')">模块维护</a></li>
					<li><a href="#" class="easyui-linkbutton" style="width: 90px" data-options="plain:true,iconCls:'icon-large-shapes',size:'large',iconAlign:'top'"
						onclick="openModule(6, '系统分类','${pageContext.request.contextPath}/category/category.do')">系统分类</a></li>
					<li><a href="#" class="easyui-linkbutton" style="width: 90px" data-options="plain:true,iconCls:'icon-large-clipart',size:'large',iconAlign:'top'"
						onclick="openModule(3, '系统选项','${pageContext.request.contextPath}/SystemOption/SystemOption.do')">系统设置</a></li>
					<li><a href="#" class="easyui-linkbutton" style="width: 90px" data-options="plain:true,iconCls:'icon-large-smartart',size:'large',iconAlign:'top'"
						onclick="openModule(5, '菜单设置','${pageContext.request.contextPath}/MenuOption/init.do')">系统菜单设置</a></li>
					<li><a href="#" class="easyui-linkbutton" style="width: 90px" data-options="plain:true,iconCls:'icon-large-shapes',size:'large',iconAlign:'top'"
						onclick="openModule(2, '用户组管理','${pageContext.request.contextPath}/usergroup/sysusergroup.do')">用户组管理</a></li>
					<li><a href="#" class="easyui-linkbutton" style="width: 90px" data-options="plain:true,iconCls:'icon-large-shapes',size:'large',iconAlign:'top'"
						onclick="openModule(4, '系统用户管理','${pageContext.request.contextPath}/users/sysuser.do')">系统用户管理</a></li>
				</ul>
			</div>
			<c:forEach items="${menus}" var="panle">

				<div title="${panle.title }" iconcls="icon-reload" style="padding: 10px;">
					<ul style="list-style-type: none; ">
						<c:forEach items="${panle.menus }" var="menu">
			         <li>
			          <a href="#" class="easyui-linkbutton" style="width: 90px" data-options="plain:true,iconCls:'${menu.menuIcon }',size:'large',iconAlign:'top'"
								onclick="openModule(${menu.moduleId }, '${menu.menuName }','${pageContext.request.contextPath}/${menu.path }')">${menu.menuName }</a></li>
						</c:forEach>

					</ul>
				</div>
			</c:forEach>
		</div>
	</div>

	<div id="mainPanle" region="center" style="overflow: hidden; resize: none;" border="false">
		<div id="mainTab" class="easyui-tabs" style="width: 100%; height: auto;" fit="true" border="false"></div>
	</div>
</body>
</html>