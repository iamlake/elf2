<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>组织机构管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${basePath}/static/assets/css/ztree.css" media="all"/>
</head>
<body class="childrenBody">
<div class="layui-row layui-col-space3">
    <div class="layui-col-lg2 layui-col-md3 layui-col-xs12">
        <fieldset class="layui-elem-field">
            <legend style="font-size: 7px">Search</legend>
            <div class="layui-field-box">
                <form class="layui-form">
                    <select name="search" id="search" lay-search lay-filter="searchDim">
                        <option value="">改变组织或维度</option>
                        <option value="1">组织机构</option>
                        <option value="2">其他</option>
                    </select>
                </form>
            </div>
        </fieldset>
        <div id="treeDemo" class="ztree"></div>
    </div>
    <div class="layui-col-lg10 layui-col-md9 layui-col-xs12">
        <blockquote class="layui-elem-quote quoteBox">
            <form class="layui-form queryForm">
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
                    <a class="layui-btn btn_query" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</a>
                </div>
                <div class="layui-inline">
                    <a class="layui-btn layui-btn-normal btn_add"><i class="layui-icon">&#xe608;</i>添加用户</a>
                </div>
                <div class="layui-inline">
                    <a class="layui-btn layui-btn-danger layui-btn-normal btn_delAll"><i class="layui-icon">&#xe640;</i>批量删除</a>
                </div>
            </form>
        </blockquote>
        <table id="table_user" lay-filter="userList"></table>

        <!--操作-->
        <script type="text/html" id="userListBar">
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="doDetail">查看</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="doDel"><i
                    class="layui-icon">&#xe640;</i>删除</a>
        </script>
    </div>
</div>

<script type="text/javascript">
    var cUnitId = null, tData, doQuery;
    layui.use(['form', 'layer', 'table', 'codelist', 'zTree'], function () {
        var $ = layui.jquery,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                table = layui.table,
                codelist = layui.codelist,
                zTree = layui.zTree;

        var newCount = 1;

        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                    + "' title='add node' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_" + treeNode.tId);
            if (btn) btn.bind("click", function () {
                var zTreeObj = zTree.getZTreeObj("treeDemo");
                zTreeObj.addNodes(treeNode, {id: (100 + newCount), pId: treeNode.id, name: "new node" + (newCount++)});
                return false;
            });
        };

        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_" + treeNode.tId).unbind().remove();
        };

        $(function () {
            createDimUnitTree();
        });

        var setting = {
            view: {
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom,
                selectedMulti: false
            },
            check: {
                enable: false
            },
            data: {
                key: {
                    name: "aliasName"
                },
                simpleData: {
                    enable: true,
                    idKey: "dimensionUnitId",
                    pIdKey: "parentDimensionUnitId",
                    rootPId: 'dimension_ln'
                }
            },
            callback: {
                beforeClick: beforeTreeNodeClick,
                onClick: onTreeNodeClick
            },
            edit: {
                drag: {
                    isCopy: false,
                    isMove: false
                },
                enable: true
            }
        };

        function createDimUnitTree() {
            // $.get(basePath + "/static/assets/json/dimensionUnits.json", function (result) {
            $.get(basePath + "/dimensionUnit/allChild", {
                parentDimensionUnitId: 'dimension_ln',
                dimensionId: 'city'
            }, function (result) {
                zTree.init($("#treeDemo"), setting, result.data);
            })
        }

        function beforeTreeNodeClick(treeId, treeNode, clickFlag) {
            // console.log("===beforeTreeNodeClick===");
            // console.log("treeId===" + treeId);
            // console.log("treeNode===" + JSON.stringify(treeNode));
            // console.log("clickFlag===" + clickFlag);
        }

        function onTreeNodeClick(event, treeId, treeNode, clickFlag) {
            $(".queryForm")[0].reset();
            cUnitId = treeNode.unitId;
            doQuery();
        }

        //用户列表
        var tableIns = table.render({
            elem: '#table_user',
            // url: basePath + '/user/unitUser',
            // where: {
            //     unitId: cUnitId
            // },
            cellMinWidth: 95,
            page: true,
            height: "full-125",
            limits: [5, 10, 30, 100],
            limit: 10,
            id: "tableJson",
            cols: [[
                {type: "checkbox", fixed: "left", width: 50},
                {field: 'account', title: '账号', minWidth: 100, align: "center"},
                {field: 'fullname', title: '姓名', minWidth: 100, align: "center"},
                {field: 'email', title: '邮箱', minWidth: 200, align: 'center', templet: function (d) {
                        return '<a class="layui-blue" href="mailto:' + d.email + '">' + d.email + '</a>';
                    }
                },
                {field: 'sex', title: '性别', align: 'center', templet: function (d) {
                        return codelist.getValueName("sex", d.sex);
                    }
                },
                {field: 'activeFlag', title: '用户状态', align: 'center', templet: function (d) {
                        return codelist.getValueName("activeFlag", d.activeFlag);
                    }
                },
                {title: '操作', minWidth: 225, templet: '#userListBar', fixed: "right", align: "center"}
            ]]
        });

        //搜索
        $(".btn_query").on("click", function () {
            doQuery();
        });

        doQuery = function () {
            tableIns.reload({
                url: basePath + '/user/unitUser',
                where: {
                    unitId: cUnitId,
                    account: $(".search_accont").val(),
                    fullname: $(".search_fullname").val()
                }, page: {
                    curr: 1 //重新从第 1 页开始
                },
                done: function () {
                    tData = table.cache.tableJson;
                }
            })
        }

        $(".btn_add").click(function () {
            var index = layui.layer.open({
                title: "添加用户",
                type: 2,
                maxmin: true,
                area: ['1280px', '768px'],
                content: basePath + "/page/sys_dimension_unitUserAdd",
                success: function (layero, index) {
                    // setTimeout(function () {
                    //     layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                    //         tips: 3
                    //     });
                    // }, 500)
                }
            })
            // layui.layer.full(index);
            // window.sessionStorage.setItem("index", index);
            // $(window).on("resize", function () {
            //     layui.layer.full(window.sessionStorage.getItem("index"));
            // })
        })

        // 批量删除
        $(".btn_delAll").click(function () {
            var checkStatus = table.checkStatus('tableJson'),
                    data = checkStatus.data,
                    userIds = '';
            if (data.length > 0) {
                for (var i in data) {
                    userIds += data[i].userId;
                    userIds += ',';
                }
                layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post({
                        url: basePath + '/unitUsers',
                        data: {
                            'userIds': userIds,
                            'unitId': cUnitId,
                            '_method': 'delete'
                        },
                        success: function (result) {
                            top.layer.close(index);
                            top.layer.msg(result.msg, {
                                icon: 1,
                                time: 500
                            }, function () {
                                doQuery();
                                layer.close(index);
                            });
                        }
                    });
                })
            } else {
                layer.msg("请选择需要删除的用户");
            }
        })

        // 列表操作
        table.on('tool(userList)', function (obj) {
            var layEvent = obj.event,
                    data = obj.data;
            if (layEvent === 'doDetail') {//查看
                layer.msg('姓名：' + data.fullname + ' 的查看操作');
            } else if (layEvent === 'doDel') { //删除
                layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post({
                        url: basePath + '/unitUser',
                        data: {
                            'userId': data.userId,
                            'unitId': cUnitId,
                            '_method': 'delete'
                        },
                        success: function (result) {
                            top.layer.close(index);
                            top.layer.msg(result.msg, {
                                icon: 1,
                                time: 500
                            }, function () {
                                doQuery();
                                layer.close(index);
                            });
                        }
                    });
                });
            }
        });
    })
</script>
</body>
</html>