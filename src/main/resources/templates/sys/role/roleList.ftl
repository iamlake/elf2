<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色管理</title>
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
                角色名称：
                <div class="layui-input-inline">
                    <input type="text" name="roleName" class="layui-input search_roleName" placeholder="请输入名称"/>
                </div>
            </div>
            <div class="layui-inline">
                角色描述：
                <div class="layui-input-inline">
                    <input type="text" name="remark" class="layui-input search_remark" placeholder="请输入描述"/>
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn btn_query" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal btn_edit"><i class="layui-icon">&#xe608;</i>新建角色</a>
            </div>
        </form>
    </blockquote>
    <table id="table_role" lay-filter="roleList"></table>

    <!--操作-->
    <script type="text/html" id="roleListBar">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="doDetail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="doEdit"><i class="layui-icon">&#xe642;</i>编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="enabled">已启用</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="doDel"><i class="layui-icon">&#xe640;</i>删除</a>
    </script>
</form>
<script type="text/javascript">
    var oData, isNew;
    layui.use(['form', 'layer', 'table', 'codelist'], function () {
        var $ = layui.jquery,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                table = layui.table, codelist = layui.codelist;

        var tableIns = table.render({
            elem: '#table_role',
            cellMinWidth: 95,
            page: true,
            height: "full-125",
            limits: [5, 10, 30, 100],
            limit: 10,
            id: "tableJson",
            cols: [[
                {type: 'checkbox', fixed: 'left', width: 50},
                {field: 'roleName', title: '角色名称', minWidth: 100, align: "center"},
                {field: 'roleType', title: '角色类别', minWidth: 100, align: "center", templet: function (d) {
                        return codelist.getValueName("roleType", d.roleType);
                    }
                },
                {field: 'timeBegin', title: '生效时间', minWidth: 100, align: "center"},
                {field: 'timeEnd', title: '失效时间', minWidth: 100, align: "center"},
                {field: 'remark', title: '描述', minWidth: 150, align: "center"},
                {title: '操作', minWidth: 225, templet: '#roleListBar', fixed: "right", align: "center"}
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
                url: basePath + '/role',
                where: {
                    roleName: $(".search_roleName").val(),
                    remark: $(".search_remark").val()
                }, page: {
                    curr: 1 //重新从第 1 页开始
                }
            })
        }

        //新建/编辑角色
        function roleEditForward(data, type) {
            oData = data;
            isNew = type;
            var index = layui.layer.open({
                title: type ? "新建角色" : "编辑角色",
                type: 2,
                content: basePath + "/page/sys_role_roleEdit",
                success: function (layero, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
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
            roleEditForward(null, true);
        })

        //列表操作
        table.on('tool(roleList)', function (obj) {
            var layEvent = obj.event,
                    data = obj.data;

            if (layEvent === 'doDetail') {//查看
                layer.msg('名称：[' + data.roleName + '] 的查看操作');
            } else if (layEvent === 'doEdit') { //编辑
                roleEditForward(data, false);
            } else if (layEvent === 'enabled') { //启用停用
                var _this = $(this),
                        enabledText = "是否确定停用？",
                        btnText = "已停用";
                if (_this.text() == "已停用") {
                    enabledText = "是否确定启用？",
                            btnText = "已启用";
                }
                layer.confirm(enabledText, {
                    icon: 3,
                    title: '提示信息',
                    cancel: function (index) {
                        layer.close(index);
                    }
                }, function (index) {
                    _this.text(btnText);
                    layer.close(index);
                }, function (index) {
                    layer.close(index);
                });
            } else if (layEvent === 'doDel') { //删除
                layer.confirm('确定要删除这个角色？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post({
                        url: basePath + '/role',
                        data: {
                            'roleId': data.roleId,
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