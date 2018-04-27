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
                <a class="layui-btn layui-btn-normal btn_edit"><i class="layui-icon">&#xe616;</i>确定</a>
            </div>
        </form>
    </blockquote>
    <table id="table_user" lay-filter="userList"></table>
</form>
<script type="text/javascript">
    var oData, isNew;
    layui.use(['form', 'layer', 'table', 'codelist'], function () {
        var $ = layui.jquery,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                table = layui.table,
                codelist = layui.codelist;

        //用户列表
        var tableIns = table.render({
            elem: '#table_user',
            url: basePath + '/user',
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
                {
                    field: 'email', title: '邮箱', minWidth: 200, align: 'center', templet: function (d) {
                        return '<a class="layui-blue" href="mailto:' + d.email + '">' + d.email + '</a>';
                    }
                },
                {
                    field: 'sex', title: '性别', align: 'center', templet: function (d) {
                        return codelist.getValueName("sex", d.sex);
                    }
                },
                {
                    field: 'activeFlag', title: '用户状态', align: 'center', templet: function (d) {
                        return d.activeFlag == "1" ? "正常使用" : "限制使用";
                    }
                }
            ]]
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

        //新建/编辑用户
        function userEditForward(data, type) {
            oData = data;
            isNew = type;
            var index = layui.layer.open({
                title: type ? "新建用户" : "编辑用户",
                type: 2,
                content: basePath + "/page/sys_user_userEdit",
                success: function (layero, index) {
                    // var iframeWin = window[layero.find('iframe')[0]['name']];
                    // if (d) {
                    //     var body = layui.layer.getChildFrame('body', index);
                    //     elf.setData(body.find(".layui-form"), d);
                    //     form.render();
                    // } else {
                    //     iframeWin.isNew = true;
                    // }
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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
            userEditForward(null, true);
        })

        //TODO-----批量删除
        $(".btn_delAll").click(function () {
            var checkStatus = table.checkStatus('tableJson'),
                    data = checkStatus.data,
                    newsId = [];
            if (data.length > 0) {
                for (var i in data) {
                    newsId.push(data[i].newsId);
                }
                layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                    // $.get("删除文章接口",{
                    //     newsId : newsId  //将需要删除的newsId作为参数传入
                    // },function(data){
                    tableIns.reload();
                    layer.close(index);
                    // })
                })
            } else {
                layer.msg("请选择需要删除的用户");
            }
        })

        //列表操作
        table.on('tool(userList)', function (obj) {
            var layEvent = obj.event,
                    data = obj.data;

            if (layEvent === 'doDetail') {//查看
                layer.msg('姓名：' + data.fullname + ' 的查看操作');
            } else if (layEvent === 'doEdit') { //编辑
                userEditForward(data, false);
            } else if (layEvent === 'enabled') { //启用停用
                var _this = $(this),
                        enabledText = "是否确定停用此用户？",
                        btnText = "已停用";
                if (_this.text() == "已停用") {
                    enabledText = "是否确定启用此用户？",
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
                layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
                    // $.get("删除文章接口",{
                    //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                    // },function(data){
                    tableIns.reload();
                    layer.close(index);
                    // })
                });
            }
        });

    })
</script>
</body>
</html>