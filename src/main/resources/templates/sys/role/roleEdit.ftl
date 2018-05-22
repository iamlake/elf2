<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色维护</title>
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
        <label class="layui-form-label">角色名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input account" lay-verify="required" placeholder="请输入角色名称" name="roleName">
        </div>
    </div>
    <div class="layui-row">
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">角色类别</label>
            <div class="layui-input-block">
                <select class="roleType" lay-filter="userType" name="roleType"></select>
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">生效时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-verify="date" placeholder="请输入生效时间" id="txt_timeBegin" name="timeBegin">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">失效时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-verify="date" placeholder="请输入失效时间" id="txt_timeEnd" name="timeEnd">
            </div>
        </div>
    </div>
    <#--<div class="layui-form-item layui-row layui-col-xs12">-->
        <#--<label class="layui-form-label">上级路径</label>-->
        <#--<div class="layui-input-block">-->
            <#--<input type="text" class="layui-input" autocomplete="off" placeholder="请输入路径" name="rolePath">-->
        <#--</div>-->
    <#--</div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea class="layui-textarea remark" placeholder="请输入备注信息" name="remark"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="save">保存</button>
            <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
        </div>
    </div>
    <input type="hidden" name="roleId" id="hid_roleId">
    <input type="hidden" name="parentRoleId" id="hid_parentRoleId">
</form>
<script type="text/javascript">
    layui.use(['form', 'layer', 'laydate', 'elf', 'codelist'], function () {
        var form = layui.form,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                $ = layui.jquery,
                elf = layui.elf, codelist = layui.codelist;
        var isNew = parent.isNew, oData = parent.oData;

        layui.laydate.render({elem: '#txt_timeBegin'});
        layui.laydate.render({elem: '#txt_timeEnd'});

        $(function () {
            codelist.bindSelect($(".roleType"), "roleType");
            if (!isNew) {
                elf.setData($(".layui-form"), oData);
            }
            form.render();
        });

        form.on("submit(save)", function (data) {
            var ext = 'isNew:' + isNew;
            if (!isNew) {
                ext += ';_method:put';
            }
            var formData = elf.getBinding($('.layui-form'), ext);
            // 弹出loading
            var index = top.layer.msg('数据提交中，请稍候…', {
                icon: 16,
                time: 500,
                shade: 0.8
            }, function () {
                $.post({
                    url: basePath + '/role',
                    data: formData,
                    success: function (result) {
                        top.layer.close(index);
                        top.layer.msg(result.msg, {
                            icon: 1,
                            time: 800
                            // 0.5秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            layer.closeAll("iframe");
                            // 刷新父页面
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