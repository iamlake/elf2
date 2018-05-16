<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>业务角色管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
</head>
<body class="childrenBody">
<div class="layui-row layui-col-space3">
    <div class="layui-col-lg5 layui-col-md5 layui-col-xs12">
        <div class="layui-tab layui-tab-card">
            <ul class="layui-tab-title">
                <li class="layui-this">业务角色列表</li>
            </ul>
            <div class="layui-tab-content" style="height: 89%;">
                <div class="layui-tab-item layui-show">
                    <blockquote class="layui-elem-quote quoteBox">
                        <form class="layui-form queryRoleForm">
                            <div class="layui-inline">
                                名称：
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input search_roleName" placeholder="请输入角色名称"/>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <a class="layui-btn btn_roleQuery" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</a>
                            </div>
                            <div class="layui-inline">
                                <a class="layui-btn layui-btn-normal btn_roleAdd"><i class="layui-icon">&#xe608;</i>添加角色</a>
                            </div>
                            <div class="layui-inline">
                                <a class="layui-btn layui-btn-danger layui-btn-normal btn_roleDelAll"><i class="layui-icon">&#xe640;</i>删除角色</a>
                            </div>
                        </form>
                    </blockquote>
                    <table id="table_role" lay-filter="roleList"></table>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-lg7 layui-col-md7 layui-col-xs12">
        <div class="layui-tab layui-tab-card">
            <ul class="layui-tab-title">
                <li class="layui-this">用户列表</li>
                <li>应用菜单授权</li>
                <li>接口授权</li>
            </ul>
            <div class="layui-tab-content" style="height: 89%;">
                <div class="layui-tab-item layui-show">
                    <blockquote class="layui-elem-quote quoteBox">
                        <form class="layui-form queryUserForm">
                            <div class="layui-inline">
                                账号：
                                <div class="layui-input-inline">
                                    <input type="text" name="account" class="layui-input search_accont" placeholder="请输入账号"/>
                                </div>
                            </div>
                            <div class="layui-inline">
                                姓名：
                                <div class="layui-input-inline">
                                    <input type="text" name="fullname" class="layui-input search_fullname" placeholder="请输入姓名"/>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <a class="layui-btn btn_userQuery" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</a>
                            </div>
                            <div class="layui-inline">
                                <a class="layui-btn layui-btn-normal btn_userAdd"><i class="layui-icon">&#xe608;</i>添加用户</a>
                            </div>
                            <div class="layui-inline">
                                <a class="layui-btn layui-btn-danger layui-btn-normal btn_userDelAll"><i class="layui-icon">&#xe640;</i>批量删除</a>
                            </div>
                        </form>
                    </blockquote>
                    <table id="table_user" lay-filter="userList"></table>

                    <!--操作-->
                    <script type="text/html" id="userListBar">
                        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="doUserDetail">查看</a>
                        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="doUserDel"><i
                                class="layui-icon">&#xe640;</i>删除</a>
                    </script>
                </div>
                <div class="layui-tab-item">2</div>
                <div class="layui-tab-item">3</div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form', 'layer', 'table', 'element', 'codelist', 'elf'], function () {
        var $ = layui.jquery,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                table = layui.table,
                element = layui.element,
                codelist = layui.codelist,
                elf = layui.elf;

        //角色列表
        var tableRoleIns = table.render({
            elem: '#table_role',
            url: basePath + '/sec/busi/getAllBusiRoles',
            cellMinWidth: 95,
            page: true,
            height: "full-200",
            limits: [5, 10, 30, 100],
            limit: 10,
            id: "tableRoleJson",
            cols: [[
                {type: "checkbox", fixed: "left", width: 50},
                {field: 'name', title: '角色', minWidth: 100, align: "center"},
                {field: 'description', title: '描述', minWidth: 120, align: "center"}
            ]]
        });

        //搜索
        $(".btn_roleQuery").on("click", function () {
            table.reload("tableRoleJson", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    name: $(".search_roleName").val()
                }
            })
        });

        $(".btn_roleAdd").click(function () {
            var index = layui.layer.open({
                title: "添加角色",
                type: 2,
                maxmin: true,
                area: ['1280px', '768px'],
                content: basePath + "/page/sys_role_busiRoleEdit",
                success: function (layero, index) {
                    // setTimeout(function () {
                    //     layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                    //         tips: 3
                    //     });
                    // }, 500)
                }
            })
        })

        //用户列表
        var tableUserIns = table.render({
            elem: '#table_user',
            url: basePath + '/user',
            cellMinWidth: 95,
            page: true,
            height: "full-200",
            limits: [5, 10, 30, 100],
            limit: 10,
            id: "tableUserJson",
            cols: [[
                {type: "checkbox", fixed: "left", width: 50},
                {field: 'account', title: '账号', minWidth: 100, align: "center"},
                {field: 'fullname', title: '姓名', minWidth: 100, align: "center"},
                {title: '操作', minWidth: 225, templet: '#userListBar', fixed: "right", align: "center"}
            ]]
        });

        //搜索
        $(".btn_userQuery").on("click", function () {
            table.reload("tableUserJson", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    account: $(".search_accont").val(),
                    fullname: $(".search_fullname").val()
                }
            })
        });
    })
</script>
</body>
</html>