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
<form class="layui-form layui-row jobForm">
<#--<div class="layui-input-block layui-red pwdTips">&nbsp;</div>-->
    <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
        <div class="layui-form-item">
            <label class="layui-form-label">任务名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="jobClassName">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">任务分组</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="jobGroup">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">表达式</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="cronExpression">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="save">确认修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    layui.use(['form', 'layer', 'laydate', 'table', 'laytpl', 'elf'], function () {
        var form = layui.form,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                $ = layui.jquery, elf = layui.elf;
        var isNew = parent.isNew, oData = parent.oData;

        $(function () {
            if (!isNew) {
                elf.setData($(".layui-form"), oData);
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
                            // 刷新父页面
                            //parent.location.reload();
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