<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>代码表维护</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                代码表名称：
                <div class="layui-input-inline">
                    <input type="text" name="codeType" class="layui-input txt_codeType" lay-verify="required"
                           placeholder="请输入名称"/>
                </div>
            </div>
            <div class="layui-inline">
                代码表描述：
                <div class="layui-input-inline">
                    <input type="text" name="codeTypeName" class="layui-input txt_codeTypeName" lay-verify="required"
                           placeholder="请输入描述"/>
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal btn_add"><i class="layui-icon">&#xe608;</i>添加代码</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn btn_save"><i class="layui-icon">&#x1005;</i>全部保存</a>
            </div>
        </form>
    </blockquote>
    <table id="table_codelist" lay-filter="codeList"></table>

    <!--操作-->
    <script type="text/html" id="codeListBar">
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="doDel"><i class="layui-icon">&#xe640;</i>删除</a>
    </script>
</form>
<script type="text/javascript">
    layui.use(['form', 'layer', 'table', 'elf'], function () {
        var $ = layui.jquery,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                table = layui.table,
                elf = layui.elf;
        var isNew = parent.isNew, oData = parent.oData;

        var tableIns = table.render({
            elem: '#table_codelist',
            cellMinWidth: 95,
            page: false,
            height: "full-125",
            limit: 500,
            id: "tableJson",
            cols: [[
                {type: 'numbers', fixed: "left"},
                {field: 'codeValue', title: '代码值', edit: 'text', minWidth: 100, align: "center"},
                {field: 'codeValueName', title: '代码名称', edit: 'text', minWidth: 150, align: "center"},
                {
                    field: 'activeFlag', title: '是否可用', minWidth: 100, align: "center", templet: function (d) {
                        if (d.activeFlag != undefined && d.activeFlag != '') {
                            if (d.activeFlag == 1) {
                                return '<input type="checkbox" name="activeFlag" lay-filter="activeFlag" lay-skin="switch" lay-text="是|否" checked>';
                            } else {
                                return '<input type="checkbox" name="activeFlag" lay-filter="activeFlag" lay-skin="switch" lay-text="是|否">';
                            }
                        } else {
                            return "无";
                        }
                    }
                },
                {field: 'codeOrder', title: '排序', edit: 'text', minWidth: 100, align: "center"},
                {field: 'filter', title: '级联属性', edit: 'text', minWidth: 100, align: "center"},
                {field: 'remark', title: '备注', edit: 'text', minWidth: 150, align: "center"},
                {title: '操作', minWidth: 225, templet: '#codeListBar', fixed: "right", align: "center"}
            ]]
        });

        $(function () {
            if (!isNew) {
                elf.setData($(".layui-form"), oData);
                doQuery();
            }
        });

        //校验
        function validateData(data) {
            var ret = true;
            var codeType = $('.txt_codeType').val();
            var codeTypeName = $('.txt_codeTypeName').val();
            if (codeType && codeTypeName) {
                if (data) {
                    for (var i = 0; i < data.length; i++) {
                        //注意不要用===判断
                        if (data[i] == "" || typeof(data[i]) == "undefined") {
                            continue;
                        }
                        if (!data[i].codeValue || !data[i].codeValueName || !data[i].codeOrder) {
                            layer.msg("代码值、代码名称和排序不允许为空！", {
                                time: 2000,
                                icon: 5,
                                anim: 6
                            });
                            ret = false;
                            break;
                        }
                        data[i].codeType = codeType;
                        data[i].codeTypeName = codeTypeName;
                    }
                } else {
                    layer.msg("至少要有一条代码表的记录！", {
                        time: 2000,
                        icon: 5,
                        anim: 6
                    });
                    ret = false;
                }
            } else {
                layer.msg("代码表名称和代码表描述不允许为空！", {
                    time: 2000,
                    icon: 5,
                    anim: 6
                });
                ret = false;
            }
            return ret;
        }

        //保存
        $(".btn_save").on("click", function () {
            var tData = table.cache.tableJson;
            if (validateData(tData)) {
                var valueArray = [];
                //清掉数组中为空的元素
                for (var i = 0; i < tData.length; i++) {
                    if (tData[i] == "" || typeof(tData[i]) == "undefined") {
                        tData.splice(i, 1);
                        i = i - 1;
                    } else {
                        valueArray.push(tData[i].codeValue);
                    }
                }
                if (elf.checkArrRepeated(valueArray)) {
                    layer.msg("代码值不允许重复！", {
                        time: 2000,
                        icon: 5,
                        anim: 6
                    });
                } else {
                    $.ajax({
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        url: basePath + '/codelist/type',
                        type: "POST",
                        data: JSON.stringify(tData),
                        dataType: "json",
                        success: function (result) {
                            if (result.code == 0) {
                                top.layer.msg(result.msg, {
                                    icon: 1,
                                    time: 1000
                                }, function () {
                                    layer.closeAll("iframe");
                                    // 刷新父页面
                                    parent.location.reload();
                                });
                            } else {
                                top.layer.msg(result.msg, {
                                    time: 2000,
                                    icon: 5,
                                    anim: 6
                                });
                            }
                        }
                    })
                }
            }
            return false;
        });

        //新增一行
        $(".btn_add").click(function () {
            var tData = table.cache.tableJson;
            if (!tData) {
                tData = [];
            }
            tData.push({
                codeType: '',
                codeTypeName: '',
                codeValue: '',
                codeValueName: '',
                activeFlag: '1',
                codeOrder: '',
                filter: '',
                remark: ''
            })
            table.reload("tableJson", {
                data: tData
            })
        })

        function doQuery() {
            tableIns.reload({
                url: basePath + '/codelist',
                where: {
                    codeType: oData.codeType,
                    codeTypeName: oData.codeTypeName
                }
            })
        }

        //列表操作
        table.on('tool(codeList)', function (obj) {
            var layEvent = obj.event,
                    data = obj.data;
            if (layEvent === 'doDel') { //删除
                layer.confirm('是否确定删除？', {icon: 3, title: '提示信息'}, function (index) {
                    obj.del();
                    top.layer.close(index);
                });
            }
        });
    })
</script>
</body>
</html>