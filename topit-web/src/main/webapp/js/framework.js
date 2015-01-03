String.format = function () {
    if (arguments.length == 0)
        return null;
    var str = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
        var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
};

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
        - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
            ? o[k]
            : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

Array.prototype.contains = function (elem) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == elem) {
            return true;
        }
    }
    return false;
}

$(function () {
    $(".filter-tabs li").click(function () {
        $(this).parent().children().removeClass("tabs-selected");
        $(this).addClass("tabs-selected");
    });
});

function selectCustomFilter() {
    $(".filter-tabs li").removeClass("tabs-selected");
    $("#filter-custom").addClass("tabs-selected");
}

function formatBoolean(value, rec, rowIndex) {
    return '<span> ' + (value == 1 ? '是' : '') + '</span>';
}

function setRights(moduleLid) {
    //$.getJSON("/Eagle/Common/GetActionRights", { moduleLid: moduleLid, timestamp: new Date().getTime() }, function (data) {
    $.getJSON("/Eagle/Common/GetActionRights", { moduleLid: moduleLid }, function (data) {
        $("[data-aright]").each(function () {
            var rno = $(this).attr("data-aright");
            if (rno != 0 && !data.contains(rno)) {
                $(this).css("display", "none");
            }
        });
    });
}

function reloadDataGrid(dg, moduleLid) {
    var dgObj = $(dg);
    if (dgObj.length == 0) {
        $.messager.alert('错误', String.format('对不起，未找到ID为 {0} 的数据网格！', dg), 'error');
        return;
    }
    if (moduleLid && moduleLid > 0) {
        dgObj.datagrid({ "onLoadSuccess": function(data){ setRights(moduleLid); } }).datagrid('reload');
    }
    else {
        dgObj.datagrid('reload');
    }
}

function reloadTreeGrid(tg, moduleLid) {
    var tgObj = $(tg);
    if (tgObj.length == 0) {
        $.messager.alert('错误', String.format('对不起，未找到ID为 {0} 的数据网格！', tg), 'error');
        return;
    }
    if (moduleLid && moduleLid > 0) {
        tgObj.treegrid({ "onLoadSuccess": function (data) { setRights(moduleLid); } }).treegrid('reload');
    }
    else {
        tgObj.treegrid('reload');
    }
}

function tryOpenModule(module, title, ur, refresh) {
    if (parent.openModule) {
        parent.openModule(module, title, ur, refresh);
    }
    else {
        $.messager.alert('错误', '非法操作，请在管控中心打开本模块页面！', 'error');
    }
}

function checkHtml(htmlContent) {
    if (htmlContent.length == 0) {
        $.messager.alert('错误', '对不起，可能由于您不具备当前操作权限，系统已拒绝了您的请求！', 'error');
        return false;
    }
    return true;
}

function addTimestamp(url) {
    if (url.indexOf('timestamp=') < 0) {
        if (url.indexOf('?') < 0) {
            url = url + '?timestamp=' + new Date().getTime();
        }
        else {
            url = url + '&timestamp=' + new Date().getTime();
        }
    }
    return url;
}

function openDialog(url, data, dlg, dlgcc, title, beforeOpen) {
    var dlgObj = $(dlg);
    var dlgccObj = $(dlgcc);
    if (dlgObj.length == 0) {
        $.messager.alert('错误', String.format('对不起，未找到ID为 {0} 的对话框！', dlg), 'error');
        return;
    }
    if (dlgccObj.length == 0) {
        $.messager.alert('错误', String.format('对不起，未找到ID为 {0} 的对话框的内容容器！', dlgcc), 'error');
        return;
    }
    //debugger
    url = addTimestamp(url);

    $.get(url, data, function (htmlContent) {
        if (checkHtml(htmlContent)) {
            dlgccObj.html(htmlContent);
            $.parser.parse(dlgObj);
            if (beforeOpen) { beforeOpen();}
            dlgObj.dialog('open').dialog('setTitle', title);
        }
    }, "html");
}

function getSelectKeys(dg, idField) {
    var rows = $(dg).datagrid('getSelections');
    var keys = '';
    if (rows.length > 0) {
        for (var i = 0; i < rows.length; i++) {
            if (i == 0) {
                keys = rows[i][idField];
            }
            else {
                keys = keys + "," + rows[i][idField];
            }
        }
    }
    return keys;
}

function saveEntity(form, url, dlg, onSuccess) {
    var formObj = $(form);
    var dlgObj = $(dlg);
    if (formObj.length == 0) {
        $.messager.alert('错误', String.format('保存数据失败，未找到ID为 {0} 的表单！', form), 'error');
        return;
    }
    if (dlgObj.length == 0) {
        $.messager.alert('错误', String.format('保存数据失败，未找到ID为 {0} 的对话框！', dlg), 'error');
        return;
    }
    formObj.form('submit',
     {
         url: url,
         onSubmit: function () {
             return $(this).form('validate');
         },
         success: function (data) {
             var result = jQuery.parseJSON(data);
             if (result.Successful) {
                 dlgObj.dialog('close');
                 if (onSuccess) { onSuccess();}
             } else {
                 $.messager.alert('错误', result.Message, 'error');
             }
         }
     });
}

function confirmDoAction(confirmMsg, url, data, onSuccess) {
    $.messager.confirm('提示',confirmMsg, function (r) {
        if (r) {
            $.post(url, data, function (result) {
                if (result.Successful) {
                    if (onSuccess) { onSuccess(); }
                } else {
                    $.messager.alert('错误', result.Message, 'error');
                }
            }, 'json');
        }
    });
}

function doAction(url, data, onSuccess) {
    $.post(url, data, function (result) {
        if (result.Successful) {
            if (onSuccess) { onSuccess(); }
        } else {
            $.messager.alert('错误', result.Message, 'error');
        }
    }, 'json');
}