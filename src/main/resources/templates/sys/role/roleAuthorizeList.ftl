<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色权限配置</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${basePath}/static/assets/css/ztree.css" media="all"/>
    <style>
        .layui-form-checkbox span {
            height: inherit!important;
            font-size: 13px;
        }
    </style>
</head>
<body class="childrenBody">
<div class="layui-row layui-col-space3">
    <div class="layui-col-lg5 layui-col-md5 layui-col-xs12">
        <div class="layui-tab layui-tab-card">
            <ul class="layui-tab-title">
                <li class="layui-this">业务角色列表</li>
            </ul>
            <div class="layui-tab-content">
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
            <div class="layui-tab-content">
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
                        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="doUserDel"><i class="layui-icon">&#xe640;</i>删除</a>
                    </script>
                </div>
                <div class="layui-tab-item">
                    <div class="layui-row layui-col-space2">
                        <div class="box box-solid layui-col-md4 layui-col-lg4">
                            <div class="box-header">
                                <form class="layui-form">
                                    <div class="box-title">
                                        <input type="checkbox" lay-skin="primary" title="全部选择"lay-filter="appAll">
                                    </div>
                                    <div class="box-tools pull-right">
                                        <a class="btn btn-box-tool layui-blue" href="javascript:void(0);"
                                           onclick="doExpandAll(this, 'app')">展开</a>/
                                        <a class="btn btn-box-tool layui-blue" href="javascript:void(0);"
                                           onclick="saveAppAuthority()">保存修改</a>
                                    </div>
                                </form>
                            </div>
                            <div class="box-body">
                                <fieldset class="layui-elem-field">
                                    <legend>应用</legend>
                                    <div class="layui-field-box">
                                        <div id="tree_app" class="ztree"></div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                        <div class="box box-solid layui-col-md8 layui-col-lg8">
                            <div class="box-header">
                                <form class="layui-form">
                                    <div class="box-title">
                                        <input type="checkbox" lay-skin="primary" title="全部选择" lay-filter="menuAll">
                                    </div>
                                    <div class="box-tools pull-right">
                                        <a class="btn btn-box-tool layui-blue" href="javascript:void(0);"
                                           onclick="doExpandAll(this, 'menu')">展开</a>/
                                        <a class="btn btn-box-tool layui-blue" href="javascript:void(0);"
                                           onclick="saveMenuAuthority()">保存修改</a>
                                    </div>
                                </form>
                            </div>
                            <div class="box-body">
                                <fieldset class="layui-elem-field">
                                    <legend>菜单树</legend>
                                    <div class="layui-field-box">
                                        <div id="tree_menu" class="ztree"></div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-tab-item">施工中…</div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var tableRoleOnClick, doExpandAll, saveAppAuthority, saveMenuAuthority;
    var cRoleId = null, doQueryUser, tData;
    layui.use(['form', 'layer', 'table', 'element', 'codelist', 'elf', 'zTree'], function () {
        var $ = layui.jquery,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                table = layui.table,
                form = layui.form,
                element = layui.element,
                codelist = layui.codelist,
                elf = layui.elf,
                zTree = layui.zTree;
        var treeAppObj, treeMenuObj;

        $(function () {
            createAppTree();
            createMenuTree();
        });

        //角色列表
        var tableRoleIns = table.render({
            elem: '#table_role',
            url: basePath + '/role',
            cellMinWidth: 95,
            page: true,
            height: "full-190",
            limits: [5, 10, 30, 100],
            limit: 10,
            id: "tableRoleJson",
            cols: [[
                // {type: "checkbox", fixed: "left", width: 50},
                {type: 'numbers'},
                {field: 'roleName', title: '角色', minWidth: 100, align: "center", templet: function (d) {
                        return "<a class=\"layui-blue\" href=\"#\" onclick=\"javascript:tableRoleOnClick('" + d.roleId + "');return false;\">" + d.roleName + "</a>";
                    }
                },
                {field: 'remark', title: '描述', minWidth: 120, align: "center"}
            ]]
        });

        //搜索
        $(".btn_roleQuery").on("click", function () {
            tableRoleIns.reload({
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    name: $(".search_roleName").val()
                }
            })
        });

        tableRoleOnClick = function (v) {
            $('.queryUserForm')[0].reset();
            cRoleId = v;
            doQueryUser(true);
            bindAppAuthority(v);
            bindMenuAuthority(v);
        }

        //用户列表
        var tableUserIns = table.render({
            elem: '#table_user',
            cellMinWidth: 95,
            page: true,
            height: "full-190",
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

        doQueryUser = function (b) {
            if (cRoleId != null) {
                tableUserIns.reload({
                    url: basePath + '/user/roleUnitUser',
                    where: {
                        roleId: cRoleId,
                        account: $(".search_accont").val(),
                        fullname: $(".search_fullname").val()
                    }, page: {
                        curr: 1 //重新从第 1 页开始
                    },
                    done: function () {
                        if(b){
                            tData = table.cache.tableUserJson;
                        }
                    }
                })
            }else{
                top.layer.msg("请先选择一个角色！", {
                    time: 2000,
                    icon: 5,
                    anim: 6
                });
            }
        }

        //搜索
        $(".btn_userQuery").on("click", function () {
            doQueryUser();
        });

        //添加用户
        $(".btn_userAdd").click(function () {
            if (cRoleId != null) {
                layui.layer.open({
                    title: "添加用户",
                    type: 2,
                    maxmin: true,
                    area: ['1280px', '768px'],
                    content: basePath + "/page/sys_role_roleUserAdd",
                    success: function () {
                        $('.queryUserForm')[0].reset();
                    }
                });
            } else {
                top.layer.msg("请先选择一个角色！", {
                    time: 2000,
                    icon: 5,
                    anim: 6
                });
            }
        });

        // 批量删除
        $(".btn_userDelAll").click(function () {
            var checkStatus = table.checkStatus('tableUserJson'),
                    data = checkStatus.data,
                    userIds = '';
            if (data.length > 0) {
                for (var i in data) {
                    userIds += data[i].userId;
                    userIds += ',';
                }
                layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post({
                        url: basePath + '/userRoles',
                        data: {
                            'userIds': userIds,
                            'roleId': cRoleId,
                            '_method': 'delete'
                        },
                        success: function (result) {
                            top.layer.close(index);
                            if (result.code == 0) {
                                top.layer.msg(result.msg, {
                                    icon: 1,
                                    time: 1000
                                }, function () {
                                    doQueryUser(true);
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
                })
            } else {
                layer.msg("请选择需要删除的用户", {
                    time: 2000,
                    icon: 5,
                    anim: 6
                });
            }
        })

        // 列表操作
        table.on('tool(userList)', function (obj) {
            var layEvent = obj.event,
                    data = obj.data;
            if (layEvent === 'doUserDetail') {//查看
                var detail = '姓名： [ ';
                detail += data.fullname;
                detail += ' ] ，';
                detail += '所属组织： [ ';

                if (data.unitList != undefined && data.unitList != '[]') {
                    for (var i = 0; i < data.unitList.length; i++) {
                        detail += data.unitList[i].unitName;
                        if (i != data.unitList.length - 1) {
                            detail += ' , ';
                        }
                    }
                } else {
                    detail += '未设置';
                }
                detail += ' ] ';

                layer.msg(detail);
            } else if (layEvent === 'doUserDel') { //删除
                layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post({
                        url: basePath + '/userRoles',
                        data: {
                            'userIds': data.userId,
                            'roleId': cRoleId,
                            '_method': 'delete'
                        },
                        success: function (result) {
                            top.layer.close(index);
                            if (result.code == 0) {
                                top.layer.msg(result.msg, {
                                    icon: 1,
                                    time: 1000
                                }, function () {
                                    doQueryUser(true);
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

        function createAppTree() {
            $.get(basePath + "/app", function (result) {
                var setting_app = {
                    data: {
                        key: {
                            name: "title"
                        },
                        simpleData: {
                            enable: true,
                            idKey: "appId",
                            pIdKey: "ext1",
                            rootPId: null
                        }
                    },
                    check: {
                        enable: true
                    },
                    callback: {
                        beforeClick: beforeAppNodeClick,
                        onClick: onAppNodeClick
                    }
                };
                treeAppObj = zTree.init($("#tree_app"), setting_app, result.data);
            })
        }
        
        function beforeAppNodeClick(treeId, treeNode, clickFlag) {
            
        }
        
        function onAppNodeClick(event, treeId, treeNode, clickFlag) {
            // createMenuTree();
        }

        function createMenuTree() {
            $.get(basePath + "/menu", function (result) {
                var setting_menu = {
                    data: {
                        key: {
                            name: "title"
                        },
                        simpleData: {
                            enable: true,
                            idKey: "menuId",
                            pIdKey: "parentMenuId",
                            rootPId: 0
                        }
                    },
                    check: {
                        enable: true
                    }
                };
                treeMenuObj = zTree.init($("#tree_menu"), setting_menu, result.data);
            })
        }

        function bindAppAuthority(roleId) {
            treeMenuObj.checkAllNodes(false);
            $.get(basePath + "/appAuthority", {'roleId': roleId}, function (result) {
                var appNodes = treeAppObj.transformToArray(treeAppObj.getNodes());
                if (result.code == 0 && result.count > 0) {
                    for (var i in result.data) {
                        for (var j in appNodes) {
                            if (result.data[i].resourceId == appNodes[j].appId) {
                                appNodes[j].checked = true;
                                treeAppObj.updateNode(appNodes[j], false);
                            }
                        }
                    }
                }
            })
        }

        function bindMenuAuthority(roleId) {
            treeMenuObj.checkAllNodes(false);
            $.get(basePath + "/menuAuthority", {'roleId': roleId}, function (result) {
                var menuNodes = treeMenuObj.transformToArray(treeMenuObj.getNodes());
                if (result.code == 0 && result.count > 0) {
                    for (var i in result.data) {
                        for (var j in menuNodes) {
                            if (result.data[i].resourceId == menuNodes[j].menuId) {
                                menuNodes[j].checked = true;
                                treeMenuObj.updateNode(menuNodes[j], false);
                            }
                        }
                    }
                }
            })
        }

        form.on('checkbox(appAll)', function(data){
            treeAppObj.checkAllNodes(data.elem.checked);
            return false;
        });

        form.on('checkbox(menuAll)', function(data){
            treeMenuObj.checkAllNodes(data.elem.checked);
            return false;
        });

        doExpandAll = function (obj, tag) {
            var hrefText = "展开";
            var expandFlag = false;
            if (obj.innerText == "展开") {
                hrefText = "折叠";
                expandFlag = true;
            }
            if (tag == 'app') {
                treeAppObj.expandAll(expandFlag);
            } else if (tag == 'menu') {
                treeMenuObj.expandAll(expandFlag);
            }
            obj.innerText = hrefText;
        }

        saveAppAuthority = function () {
            var checkedNodes = treeAppObj.getCheckedNodes(true);
            var appIds = '';
            for (var i in checkedNodes) {
                appIds += checkedNodes[i].appId;
                appIds += ',';
            }
            // 弹出loading
            var index = top.layer.msg('数据提交中，请稍候…', {
                icon: 16,
                time: 500,
                shade: 0.8
            }, function () {
                $.post({
                    url: basePath + "/appAuthority",
                    data: {
                        appIds: appIds,
                        roleId: cRoleId
                    },
                    success: function (result) {
                        top.layer.close(index);
                        top.layer.msg(result.msg, {
                            icon: 1,
                            time: 1000
                        }, function () {

                        });
                    }
                });
            });
        }

        saveMenuAuthority = function () {
            var checkedNodes = treeMenuObj.getCheckedNodes(true);
            var menuIds = '';
            for (var i in checkedNodes) {
                menuIds += checkedNodes[i].menuId;
                menuIds += ',';
            }
            // 弹出loading
            var index = top.layer.msg('数据提交中，请稍候…', {
                icon: 16,
                time: 500,
                shade: 0.8
            }, function () {
                $.post({
                    url: basePath + "/menuAuthority",
                    data: {
                        menuIds: menuIds,
                        roleId: cRoleId
                    },
                    success: function (result) {
                        top.layer.close(index);
                        top.layer.msg(result.msg, {
                            icon: 1,
                            time: 1000
                        }, function () {

                        });
                    }
                });
            });
        }
    })
</script>
</body>
</html>