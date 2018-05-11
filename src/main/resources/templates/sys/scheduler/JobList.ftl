<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>调度中心</title>
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
                任务名称：
                <div class="layui-input-inline">
                    <input type="text" name="jobClassName" class="layui-input search_name" placeholder="请输入名称"/>
                </div>
            </div>
            <div class="layui-inline">
                任务描述：
                <div class="layui-input-inline">
                    <input type="text" name="description" class="layui-input search_description" placeholder="请输入描述"/>
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn btn_query" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal btn_edit"><i class="layui-icon">&#xe608;</i>新建任务</a>
            </div>
        </form>
    </blockquote>
    <table id="table_job" lay-filter="jobList"></table>

    <!--操作-->
    <script type="text/html" id="jobListBar">
        <a class="layui-btn layui-btn-xs" lay-event="doPause">暂停</a>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="doResume">恢复</a>
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
            elem: '#table_job',
            // url: basePath + '/job/query',
            cellMinWidth: 95,
            page: true,
            height: "full-125",
            limits: [5, 10, 30, 100],
            limit: 10,
            id: "tableJson",
            cols: [[
                {type: "checkbox", fixed: "left", width: 50},
                {field: 'jobName', title: '任务名称', minWidth: 100, align: "center"},
                {field: 'jobGroup', title: '任务所在组', minWidth: 100, align: "center"},
                {field: 'jobClassName', title: '任务类名', minWidth: 100, align: "center"},
                // {field: 'triggerName', title: '触发器名称', minWidth: 100, align: "center"},
                // {field: 'triggerGroup', title: '触发器所在组', minWidth: 100, align: "center"},
                {field: 'cronExpression', title: '表达式', minWidth: 150, align: "center"},
                // {field: 'timeZoneId', title: '时区', minWidth: 100, align: "center"},
                {field: 'description', title: '描述', minWidth: 150, align: "center"},
                {title: '操作', minWidth: 225, templet: '#jobListBar', fixed: "right", align: "center"}
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
                url: basePath + '/job/query',
                where: {
                    jobClassName: $(".search_name").val(),
                    description: $(".search_description").val()
                }, page: {
                    curr: 1 //重新从第 1 页开始
                }
            })
        }

        //新建/编辑任务
        function jobEditForward(data, type) {
            oData = data;
            isNew = type;
            var index = layui.layer.open({
                title: type ? "新建任务" : "编辑任务",
                type: 2,
                content: basePath + "/page/sys_scheduler_jobEdit",
                success: function (layero, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回任务列表', '.layui-layer-setwin .layui-layer-close', {
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
            jobEditForward(null, true);
        })

        //列表操作
        table.on('tool(jobList)', function (obj) {
            var layEvent = obj.event,
                    data = obj.data;
            if (layEvent === 'doPause') {//暂停
                layer.confirm('确定要停止这个任务？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post({
                        url: basePath + '/job/pause',
                        data: {
                            'jobClassName': data.jobClassName,
                            'jobGroupName': data.jobGroup
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
            } else if (layEvent === 'doResume') {//恢复
                layer.confirm('确定要重启这个任务？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post({
                        url: basePath + '/job/resume',
                        data: {
                            'jobClassName': data.jobClassName,
                            'jobGroupName': data.jobGroup
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
            } else if (layEvent === 'doEdit') { //编辑
                jobEditForward(data, false);
            } else if (layEvent === 'doDel') { //删除
                layer.confirm('确定要删除这个任务？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post({
                        url: basePath + '/job/delete',
                        data: {
                            'jobClassName': data.jobClassName,
                            'jobGroupName': data.jobGroup
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