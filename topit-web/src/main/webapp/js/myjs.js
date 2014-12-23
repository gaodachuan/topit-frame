// #####################################主界面的js###################################################################
     $(function () {
                ShowTime();
                setInterval("ShowTime()", 1000);
                $(".searchbox-text").focus();
            });
            //选择菜单
            function onSelectMenu(title, index) {
                $(".accordion-header-selected").removeClass("accordion-header-selected");
            }
			//显示系统时间
            function ShowTime() {
                var day = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
                var now = new Date();
                var year = now.getFullYear();
                var mon = now.getMonth();
                var date = now.getDate();
                var hour = now.getHours();
                var min = now.getMinutes();
                var sec = now.getSeconds();
                $("#timer").html(year + "-" + formaterNumber(mon) + "-" + formaterNumber(date) + " " + formaterNumber(hour)
                                + ":" + formaterNumber(min) + ":" + formaterNumber(sec) + "(" + day[now.getDay()] + ")");
            }
            //格式化时间
            function formaterNumber(v) {
                if (v < 10) {
                    return "0" + v;
                }
                return v;
            }
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
                    var content = '<iframe id="' + tabiframe + '" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                    tabc.tabs('add', {
                        id: tabmodule,
                        title: title,
                        content: content,
                        closable: true
                    });
                }
            }
            //判断模块是否已经打开
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

            function doSearch(value) {
                if (value == 1) {
                    openModule(1, '分类体系管理', '@Url.Action("Index","Category")');
                }
                else if (value == 2) {
                    openModule(2, '模块维护', '@Url.Action("Index","Module")');
                }
                else if (value == 3) {
                    openModule(3, '用户组管理', '@Url.Action("Index","UserGroup")');
                }
                else
                {
                    openModule(4, "测试", "");
                }
            }
            var iNewMessageCount = 0;

            function addMessage(selector) {
                // increase counter
                iNewMessageCount++;

                // append a new message to the marquee scrolling list
                var $ul = $(selector).append("<li>New message #" + iNewMessageCount + "</li>");
                // update the marquee
                $ul.marquee("update");
            }

            function pause(selector) {
                $(selector).marquee('pause');
            }

            function resume(selector) {
                $(selector).marquee('resume');
            }
// #####################################模块界面js###################################################################


    $(function () {
        $('#@s.Name').keydown(function (e) {
            if (e.keyCode == 13) {
                doSearch(-1);
            }
        }).focus();
    });

    var isAdd = false;
    function create() {
        isAdd = true;
        var url = "moduel.html";
        openDialog(url, {}, '#dlg', '#dlgcc', '模块-新增');
    }

    function edit(id) {
        isAdd = false;
        var url = "";
        openDialog(url, { id: id }, '#dlg', '#dlgcc', '模块-修改')
    }

    function save() {
        var url = isAdd ? "" : "";
        saveEntity('#cform', url, '#dlg', function () { $('#dg').datagrid('reload'); });
    }

    function doSearch(filterLid) {
        $('#dg').datagrid('load', filterLid == -1 ? {
            Name: $('#@s.Name').val(),
            CategoryLid: $('#@s.CategoryLid').combobox('getValue'),
            FilterLid: filterLid
        } : { FilterLid: filterLid }
        );
        if (filterLid==-1) {
            selectCustomFilter();
        }
    }

    function formatEdit(val, row, index) {
        return String.format('<a href="javascript:void(0)" onclick="edit({0})"><span class="icon-application-form" style="width:16px;height:16px;float:left;margin-right:3px"></span>{1}</a>', row['@s.Lid'], val);
    }

    function formatUrl(val, row, index) {
        return String.format('<a href="javascript:void(0)" onclick="tryOpenModule({0},\'{1}\',\'{2}\',false)">{2}</a>', row['@s.Lid'], row['@s.Name'], val);
    }

