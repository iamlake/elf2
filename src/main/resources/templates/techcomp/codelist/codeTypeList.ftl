<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>代码表管理</title>
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
                    <input type="text" name="codeType" class="layui-input search_codeType" placeholder="请输入名称"/>
                </div>
            </div>
            <div class="layui-inline">
                代码表描述：
                <div class="layui-input-inline">
                    <input type="text" name="codeTypeName" class="layui-input search_codeTypeName" placeholder="请输入描述"/>
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn btn_query" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal btn_edit"><i class="layui-icon">&#xe608;</i>新建代码表</a>
            </div>
        </form>
    </blockquote>
    <table id="table_codelist" lay-filter="codeTypeList"></table>

    <!--操作-->
    <script type="text/html" id="codeTypeListBar">
        <a class="layui-btn layui-btn-xs" lay-event="doEdit"><i class="layui-icon">&#xe642;</i>编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="doDel"><i class="layui-icon">&#xe640;</i>删除</a>
    </script>
</form>
<script type="text/javascript">
    var oData, isNew;
    layui.use(['form', 'layer', 'table'], function () {
        var $ = layui.jquery,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                table = layui.table;

        var tableIns = table.render({
            elem: '#table_codelist',
            cellMinWidth: 95,
            page: true,
            height: "full-125",
            limits: [5, 10, 30, 100],
            limit: 10,
            id: "tableJson",
            cols: [[
                {type: "checkbox", fixed: "left", width: 50},
                {field: 'codeType', title: '代码表名称', minWidth: 150, align: "center"},
                {field: 'codeTypeName', title: '代码表描述', minWidth: 150, align: "center"},
                {title: '操作', minWidth: 225, templet: '#codeTypeListBar', fixed: "right", align: "center"}
            ]]
        });

        $(function () {
            doQuery();
        });

        //搜索
        $(".btn_query").on("click", function () {
            doQuery();
        });

        function doQuery() {
            tableIns.reload({
                url: basePath + '/codelist/type',
                where: {
                    codeType: $(".search_codeType").val(),
                    codeTypeName: $(".search_codeTypeName").val()
                }
            })
        }

        //新建/编辑
        function pageEditForward(data, type) {
            oData = data;
            isNew = type;
            var index = layui.layer.open({
                title: type ? "新建代码表" : "编辑代码表",
                type: 2,
                content: basePath + "/page/techcomp_codelist_codelistEdit",
                success: function (layero, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            })
            layui.layer.full(index);
            window.sessionStorage.setItem("index", index);
            $(window).on("resize", function () {
                layui.layer.full(window.sessionStorage.getItem("index"));
            })
        }

        $(".btn_edit").click(function () {
            pageEditForward(null, true);
        })

        //列表操作
        table.on('tool(codeTypeList)', function (obj) {
            var layEvent = obj.event,
                    data = obj.data;
            if (layEvent === 'doEdit') { //编辑
                pageEditForward(data, false);
            } else if (layEvent === 'doDel') { //删除
                layer.confirm('确定要删除这个代码表？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post({
                        url: basePath + '/codelist',
                        data: {
                            'codeType': data.codeType,
                            '_method': 'delete'
                        },
                        success: function (result) {
                            top.layer.close(index);
                            if (result.code == 0) {
                                top.layer.msg(result.msg, {
                                    icon: 1,
                                    time: 1000
                                }, function () {
                                    doQuery();
                                });
                            } else {
                                top.layer.msg(result.msg, {
                                    time: 2000,
                                    icon: 5,
                                    anim: 6
                                });
                            }
                        }
                    });
                });
            }
        });
    })
</script>
</body>
</html>