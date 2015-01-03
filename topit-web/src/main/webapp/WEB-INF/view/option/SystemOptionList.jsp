<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>整体布局</title>
<%@ include file="../common/common.jspf"%>
<script type="text/javascript">

$(function()
{  	
   $("select").each(function(){
	   $(this).combobox({
		   onLoadSuccess:function(){	   
			   var value=$(this).attr('value');
			   $(this).combobox('select',value);
		   }
	   });	
   });	

});

$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
}; 
function saveOptions() {
     $("#sysOptionForm").submit(function() {	
			var jsonuserinfo = $(this).serializeObject();  
	        $.ajax({
	        	url:$(this).attr('action'),
	        	type:'post',
	        	data:jsonuserinfo,
	        	dataType:'json' , 
				cache:false , 
				success:function(result){
					var massage=result.errorDetail;
					if(result.errorCode==0){		
						$.messager.show({
							title:'提示信息',
							msg:massage,
							showType:'show'
						});
						
					}else{						
						$.messager.show({
							title:'提示信息',
							msg:massage,
							showType:'show'
						});
					}
					
				}	
	        });       
			return false;
		});
	}
</script>

<style>
table.option-table {
	border-width: 1px;
	border-style: dotted;
	border-collapse: collapse;
	margin: 5px 5px 0 5px;
}

table.option-table th {
	padding: 8px;
	border-width: 1px;
	border-style: dotted;
	border-color: #ccc;
}

table.option-table td {
	border-width: 1px;
	border-style: dotted;
	border-color: #ccc;
	padding: 3px;
}

.td-name {
	text-align: right;
	padding-right: 6px;
}

.td-remark {
	text-align: left;
	padding-left: 6px;
}

.td-value {
	padding-left: 6px;
	padding-right: 6px;
}
</style>

</head>
<!--选项分类  -->
<body class="easyui-layout">
	<!-- 选项列表 -->

		<div id="p1" class="easyui-panel" title="选项列表" fit="true" collapsible="false" border="false">
			<form id="sysOptionForm" novalidate="true" name="sysOptionForm" method="post" action="${pageContext.request.contextPath}/SystemOption/SysOptionUpdate.do">
				<input type="hidden" name="categoryId" value="${categoryId}"></input>
				<table id="tg" class="option-table" style="width: 99%;">
					<thead>
						<tr>
							<th width="200" style="text-align: center;">选项名称</th>
							<th width="230" style="text-align: center;">选项值</th>
							<th width="600" style="text-align: center;">选项说明</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sysOptions}" var="option">
							<c:choose>
								<c:when test="${option.dataType == 'text'}">
									<tr>
										<td class="td-name">${option.optionName }</td>
										<td class="td-value"><input class="easyui-numberbox" data-options="required:true" style="width: 90px; display: none;" name="${option.optionKey}"
											value="${option.optionValue}"></input></td>
										<td class="td-remark">${option.description}</td>
									</tr>
								</c:when>
								<c:when test="${option.dataType == 'radio'}">
									<tr>
										<td class="td-name">${option.optionName }</td>
										<td class="td-value"><input id="s" type="radio" value="1" name="${option.optionKey}" <c:if test="${option.optionValue==1}"> checked='checked'</c:if> /> <label
											for="s">是 </label> <input id="ss" type="radio" value="0" name="${option.optionKey}" <c:if test="${option.optionValue==0}"> checked='checked'</c:if> /> <label
											for="ss"> 否 </label></td>
										<td class="td-remark">${option.description}</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td class="td-name">${option.optionName }</td>
										<td class="td-value">
										<select id="optionSelect" class="easyui-combobox " data-options="required:true,editable:false,panelHeight:'auto'" style="width: 90px"
											url="<c:url value="/SystemOption/SysOptionCombox/${option.sortTypeId }.do"/>" valueField="id" textField="name" name="${option.optionKey}"
											value="${option.optionValue }">
										</select>
										</td>
										<td class="td-remark">${option.description}</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<tr>
							<td></td>
							<td colspan="2"><button id="save" class="easyui-linkbutton" onclick="saveOptions()" style="width: 128px;">保存选项设置</button></td>
						</tr>
					</tbody>
				</table>
			</form>

		</div>



</body>
</html>
