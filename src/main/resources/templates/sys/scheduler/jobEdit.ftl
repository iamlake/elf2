<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>调度管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
</head>
<body class="childrenBody">
    <form class="layui-form" style="width:80%;">
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">任务类名</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input jobClassName" placeholder="请输入任务类名" name="jobClassName">
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">任务分组</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input jobGroup" placeholder="请输入任务分组" name="jobGroup">
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">表达式</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" placeholder="请输入表达式" name="cronExpression">
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea description" placeholder="请输入描述信息" name="description"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="save">保存</button>
                <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
            </div>
        </div>
    </form>
<script type="text/javascript">
    layui.use(['form', 'layer', 'table', 'elf'], function () {
        var form = layui.form,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                $ = layui.jquery, elf = layui.elf;
        var isNew = parent.isNew, oData = parent.oData;

        $(function () {
            if (!isNew) {
                elf.setData($(".layui-form"), oData);
                $('.jobClassName').attr("readonly", "readonly");
                $('.jobGroup').attr("readonly", "readonly");
                $('.description').attr("readonly", "readonly");
                form.render();
            }
        });

        form.on("submit(save)", function (data) {
            var formData = elf.getBinding($('.layui-form'), null);
            var url = basePath;
            if (isNew) {
                url += '/job/scheduler';
            } else {
                url += '/job/reschedule';
            }

            // 弹出loading
            var index = top.layer.msg('数据提交中，请稍候…', {
                icon: 16,
                time: 500,
                shade: 0.8
            }, function () {
                $.post({
                    url: url,
                    data: formData,
                    success: function (result) {
                        top.layer.close(index);
                        top.layer.msg(result.msg, {
                            icon: 1,
                            time: 500
                            // 0.5秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            layer.closeAll("iframe");
                            //刷新父页面
                            parent.location.reload();
                        });
                    }
                });
            });
            return false;
        })
    })
</script>
</body>
</html>