<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加组织用户</title>
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
                <a class="layui-btn layui-btn-normal btn_confirm"><i class="layui-icon">&#xe616;</i>确定</a>
            </div>
        </form>
    </blockquote>
    <table id="table_user" lay-filter="userList"></table>
</form>
<script type="text/javascript">
    var withoutIds;
    layui.use(['form', 'layer', 'table', 'codelist', 'linq'], function () {
        var $ = layui.jquery,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                table = layui.table, linq = layui.linq,
                codelist = layui.codelist;

        $(function () {
            withoutIds = '';
            linq.from(parent.tData).toArray().forEach(function (e) {
                // withoutIds.unshift(e.userId);
                withoutIds += e.userId;
                withoutIds += ',';
            });

            //用户列表
            table.render({
                elem: '#table_user',
                url: basePath + '/user',
                where: {
                    account: $(".search_accont").val(),
                    fullname: $(".search_fullname").val(),
                    withoutIds: withoutIds
                },
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
                    }
                ]]
            });
        });

        //搜索
        $(".btn_query").on("click", function () {
            table.reload("tableJson", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    account: $(".search_accont").val(),
                    fullname: $(".search_fullname").val()
                }
            })
        });

        //确定选择
        $(".btn_confirm").click(function () {
            var checkStatus = table.checkStatus('tableJson'),
                    data = checkStatus.data,
                    userIds = '';
            if (data.length > 0) {
                for (var i in data) {
                    userIds += data[i].userId;
                    userIds += ',';
                }
                // 弹出loading
                var index = top.layer.msg('数据提交中，请稍候…', {
                    icon: 16,
                    time: 500,
                    shade: 0.8
                }, function () {
                    $.post({
                        url: basePath + "/unitUsers",
                        data: {
                            userIds: userIds,
                            unitId: parent.cUnitId
                        },
                        success: function (result) {
                            top.layer.close(index);
                            top.layer.msg(result.msg, {
                                icon: 1,
                                time: 1000
                            }, function () {
                                var _index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(_index);
                                parent.doQuery();
                            });
                        }
                    });
                });
            } else {
                layer.msg("请选择需要添加的用户", {
                    time: 2000,
                    icon: 5,
                    anim: 6
                });
            }
        })
    })
</script>
</body>
</html>