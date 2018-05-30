<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码--layui后台管理模板 2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
</head>
<body class="childrenBody">
<form class="layui-form layui-row changePwd">
<#--<div class="layui-input-block layui-red pwdTips">&nbsp;</div>-->
    <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" disabled class="layui-input layui-disabled" name="account">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">当前密码</label>
            <div class="layui-input-block">
                <input type="password" value="" placeholder="请输入当前密码" lay-verify="required|oldPwd"
                       class="layui-input oldPwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" value="" placeholder="请输入新密码" lay-verify="required|newPwd" id="newPwd"
                       class="layui-input newPwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认新密码</label>
            <div class="layui-input-block">
                <input type="password" value="" placeholder="请确认新密码" lay-verify="required|confirmPwd"
                       class="layui-input confirmPwd">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="changePwd">确认修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
    <input type="hidden" name="userId">
    <input type="hidden" name="password" id="pwd">
</form>
<script type="text/javascript">
    layui.use(['form', 'layer', 'laytpl', 'elf'], function () {
        var form = layui.form,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                $ = layui.jquery, elf = layui.elf;

        //添加验证规则
        form.verify({
            oldPwd: function (value, item) {
                if (value != $('#pwd').val()) {
                    return "密码错误，请重新输入！";
                }
            },
            newPwd: function (value, item) {
                if (value.length < 6) {
                    return "密码长度不能小于6位";
                }
            },
            confirmPwd: function (value, item) {
                if (!new RegExp($("#newPwd").val()).test(value)) {
                    return "两次输入密码不一致，请重新输入！";
                }
            }
        })

        //修改密码
        form.on("submit(changePwd)", function (data) {
            $('#pwd').val($("#newPwd").val());
            var ext = '_method:put';
            var formData = elf.getBinding($('.layui-form'), ext);
            // 弹出loading
            var index = top.layer.msg('数据提交中，请稍候…', {
                icon: 16,
                time: 500,
                shade: 0.8
            }, function () {
                $.post({
                    url: basePath + '/user/password',
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