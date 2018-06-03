<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人资料--layui后台管理模板 2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
</head>
<body class="childrenBody">
<form class="layui-form layui-row">
    <div class="layui-col-md3 layui-col-xs12 user_right">
        <div class="layui-upload-list">
            <img class="layui-upload-img layui-circle userAvatar" id="userFace">
        </div>
        <button type="button" class="layui-btn layui-btn-primary userFaceBtn" style="margin-top: 20px;"><i class="layui-icon">&#xe67c;</i>
            点击更换头像
        </button>
    </div>
    <div class="layui-col-md6 layui-col-xs12">
        <div class="layui-form-item" style="margin-top: 50px;">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
                <input type="text" name="account" disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-verify="required" placeholder="请输入姓名" name="fullname">
            </div>
        </div>
        <div class="layui-form-item" pane="">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block sex"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电子邮件</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input email" lay-verify="required|email" autocomplete="off"
                       placeholder="请输入邮箱地址" name="email">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">移动电话</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-verify="phone" placeholder="请输入手机号码"
                       name="mobileTelephone">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">生日</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-verify="date" placeholder="请输入出生年月" id="txt_birthDate"
                       name="birthdate">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">家庭住址</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" placeholder="请输入家庭住址" name="homeAddress">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">证件类型</label>
            <div class="layui-input-block">
                <select class="credentialsType" lay-filter="credentialsType" name="credentialsType"></select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">证件号码</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input credentialsNumber" autocomplete="off"
                       placeholder="请输入证件号码" name="credentialsNumber">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea remark" placeholder="请输入备注信息" name="remark"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
        <input type="hidden" name="userId" id="hid_userId">
    </div>
</form>
<script type="text/javascript">
    var form, $;
    layui.use(['form', 'layer', 'util', 'upload', 'laydate', 'codelist', 'elf'], function () {
        form = layui.form;
        $ = layui.jquery;
        var layer = parent.layer === undefined ? layui.layer : top.layer;
        var upload = layui.upload;
        var codelist = layui.codelist, elf = layui.elf;

        //上传头像
        upload.render({
            elem: '.userFaceBtn',
            url: basePath + '/static/assets/json/userface.json',
            before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致。
                layer.load(); //上传loading
                obj.preview(function (index, file, result) {
                    $('#userFace').attr('src', result); //图片链接（base64）
                });
            },
            done: function (res, index, upload) {
                layer.closeAll('loading'); //关闭loading
            },
            error: function (index, upload) {
                layer.closeAll('loading'); //关闭loading
            }
        });

        //添加验证规则
        form.verify({
            userBirthday: function (value) {
                if (!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)) {
                    return "出生日期格式不正确！";
                }
            }
        })

        layui.laydate.render({
            elem: '#txt_birthDate', max: layui.util.toDateString(new Date(), "yyyy-MM-dd")
        });

        $(function () {
            $.get(basePath + "/user/" + loginUser.account,
                    function (result) {
                        if (result.code == '0') {
                            codelist.bindRadio($(".sex"), "sex", "sex");
                            codelist.bindSelect($(".credentialsType"), "credentialsType");
                            elf.setData($(".layui-form"), result.data[0]);
                            $('#userFace').attr('src', basePath + result.data[0].userHead);
                            form.render();
                        } else {
                            layer.msg("请刷新页面后重试！");
                        }
                    }
            );
        });

        //提交个人资料
        form.on("submit(save)", function (data) {
            var ext = '_method:put';
            var formData = elf.getBinding($('.layui-form'), ext);
            // 弹出loading
            var index = top.layer.msg('数据提交中，请稍候…', {
                icon: 16,
                time: 500,
                shade: 0.8
            }, function () {
                $.post({
                    url: basePath + '/user',
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