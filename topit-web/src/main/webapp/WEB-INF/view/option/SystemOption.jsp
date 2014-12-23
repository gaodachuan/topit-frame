<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>整体布局</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript">
	$(function() {
		$('#category').tree({
			url : '${pageContext.request.contextPath}/SystemOption/getAll.do',
			animate : true,
			onSelect : function(node) {
				$("#sysOptionList").attr("src","${pageContext.request.contextPath}/SystemOption/SysOptionList.do?categoryId="+node.attributes.categoryId);
			}
		});

	});
</script>
</head>
<!--选项分类  -->
<body class="easyui-layout">
	<div region="west" style="width: 200px; resize: none;">
		<div id="p" class="easyui-panel" title="选项分类" fit="true"
			collapsible="false" border="false">
			<ul id="category"></ul>
		</div>
	</div>
	<!-- 选项列表 -->
	<div id="mainPanle" region="center"
		style="overflow: hidden; resize: none;">
		<iframe  id="sysOptionList" align="center" width="100%" height="100%"
			src="<c:url value='/SystemOption/SysOptionList.do?categoryId=1'/>" frameborder="no" border="0"
			marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</div>
</body>
</html>
