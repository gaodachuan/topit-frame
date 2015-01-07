//开启模块：模块号，模块名称，模块地址
function openModule(module, title, url, refresh) {
	if (!module || module < 0) {
		module = 0;
	}
	var tabiframe = "tab-iframe-" + module;
	var tabmodule = "tab-module-" + module;
	var tabc = $('#mainTab');
	if (existsModuleTab(tabc, module)) {
		if (refresh) {
			$('#' + tabiframe).attr("src", url);
		}
	} else {
		var content = '<iframe id="' + tabiframe
				+ '" scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';
		tabc.tabs('add', {
			id : tabmodule,
			title : title,
			content : content,
			closable : true
		});
	}
}
// 关闭模块：选项卡，模块号
function existsModuleTab(tabc, module) {
	var tabs = tabc.tabs("tabs");
	var tabmodule = "tab-module-" + module;
	var exists = false;
	var tab;
	for (var i = 0; i < tabs.length; i++) {
		tab = tabs[i];
		if (tab[0].id == tabmodule) {
			tabc.tabs('select', tabc.tabs('getTabIndex', tab));
			exists = true;
			break;
		}
	}
	return exists;
}
// 时间格式化
var Common = {
	// EasyUI用DataGrid用日期格式化格式（yyyy-mm-dd HH:MM:ss）
	DateFormatter : function(value, rec, index) {
		if (value == undefined) {
			return "";
		}
		/* json格式时间转js时间格式 */
		// value = value.substr(1, value.length - 2);
		var datetime = new Date(parseInt(value.toString().replace("/Date(", "")
				.replace(")/", ""), 10));
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1 < 10 ? "0"
				+ (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
				: datetime.getDate();
		var hour = datetime.getHours() < 10 ? "0" + datetime.getHours()
				: datetime.getHours();
		var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
				: datetime.getMinutes();
		var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
				: datetime.getSeconds();
		return year + "-" + month + "-" + date + " " + hour + ":" + minute
				+ ":" + second;

	}
};

function TimeFormater(value)
{
	if (value == undefined) {
		return "";
	}
	/* json格式时间转js时间格式 */
	// value = value.substr(1, value.length - 2);
	var datetime = new Date(parseInt(value.toString().replace("/Date(", "")
			.replace(")/", ""), 10));
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0"
			+ (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
			: datetime.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours()
			: datetime.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
			: datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
			: datetime.getSeconds();
	return year + "-" + month + "-" + date + " " + hour + ":" + minute
			+ ":" + second;

}
(function ($) {
    $.fn.serializeJson = function (serializeObj) {
        if (!serializeObj) {
            serializeObj = {};
        }
        $(this.serializeArray()).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

$(function() {
	$.extend($.fn.datagrid.methods,
		{addToolbarItem : function(jq, items) {
				return jq.each(function() {
					var toolbar = $(this).parent().prev("div.datagrid-toolbar");
					var tdx = toolbar.children("table").children("tbody").children("tr").children("td");
					if (tdx.length == 0) {
						$("<td></td>").appendTo(toolbar.children("table").children("tbody").children("tr"))
					};
					for (var i = 0; i < items.length; i++) {
							var item = items[i];
							
							if (item === "-") {
								toolbar.append('<div class="datagrid-btn-separator"></div>');
							} else {
								var aId = item.actionId;
								if(toolbar.children("table").children("tbody").children("tr").children("td").find("#a_button_datagird_"+aId).length==0){
									var btn = $("<a id=\""+"a_button_datagird_"+aId+"\"  href=\"javascript:void(0)\"></a>");
									btn[0].onclick = eval(item.handler|| function() {});
									btn.css("float","left").appendTo(toolbar.children("table").children("tbody").children("tr").children("td")).linkbutton($.extend({},item,{plain : true}));
									
								}
							}
						}
								toolbar = null;
					});
					}
			});
})

function buildToobarItem(name, flag, actions) {

	for (var j = 0; j < actions.length; j++) {
		var actionObj = actions[j]
		for (var i = 0; i < flag.length; i++) {
			var option = flag[i];
			if (option.actionId == actionObj.action) {
				$('#' + name).datagrid("addToolbarItem", [ {
					"text" : option.name,
					"iconCls" : actionObj.icon,
					"actionId" : option.actionId,
					"handler" : function() {
						eval(actions.functionname+"()");
					}
				} ]);

			}
		}
	}
}
	
	
	
