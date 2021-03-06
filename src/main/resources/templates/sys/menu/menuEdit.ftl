<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>菜单管理--layui后台管理模板 2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12 div_app">
        <label class="layui-form-label">所属应用</label>
        <div class="layui-input-block">
            <select class="appSelect" lay-filter="appSelect" name="appId" lay-verify="required"></select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12 div_pmenu">
        <label class="layui-form-label">上级菜单</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input parentMenu" name="parentMenuId" readonly>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input title" lay-verify="required" placeholder="请输入菜单名称" name="title">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">请求URL</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" placeholder="请输入请求URL" name="href">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单图标</label>
        <div class="layui-input-block">
            <input type="hidden" class="layui-input" name="iconfont" id="iconValue" placeholder="请选择菜单图标">
            <div class="layui-input-inline" style="width: 60px;" id="realIcon">
            </div>
            <div class="layui-input-inline" style="width: 100px;">
                <a class="layui-btn layui-btn-normal" id="selectIcon">选择图标</a>
            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-verify="required" placeholder="请输入排序号" name="menuOrder">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">目标框架</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" placeholder="请输入目标框架" name="target">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <select class="activeFlag" lay-filter="activeFlag" name="activeFlag">
                    <option value="0">限制使用</option>
                    <option value="1">正常使用</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">默认折叠</label>
        <div class="layui-input-block">
            <input type="checkbox" name="spread" lay-skin="switch" value="1" lay-text="是|否">
        </div>
    </div>
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
    <input type="hidden" name="level" id="hid_level">
    <input type="hidden" name="menuId" id="hid_menuId">
</form>
<script>
    var $, iconShow, initIcon;
    layui.use(['form', 'layer', 'elf'], function () {
        var form = layui.form,
                layer = parent.layer === undefined ? layui.layer : top.layer,
                elf = layui.elf;
        $ = layui.jquery;

        var isNew = true, oType = parent.oType, oData = parent.oData;

        $(function () {
            $.get(basePath + "/app", function (result) {
                elf.bindSelect($('.appSelect'), result.data, 'appId', 'title');
                if ('root' == oType) {
                    $('.parentMenu').val('0');
                    $('.div_pmenu').attr("style", "display:none");
                } else {
                    if ('child' == oType) {
                        $('.appSelect').val(oData.appId);
                        $('.parentMenu').val(oData.menuId);
                        $('.div_app').attr("style", "display:none");
                    } else if ('edit' == oType) {
                        isNew = false;
                        elf.setData($(".layui-form"), oData);
                        if ($("#iconValue").val()) {
                            initIcon($("#realIcon"), $("#iconValue").val(), null);
                        }
                        $('.appSelect').attr("disabled", "disabled");
                    }
                }
                form.render();
            })
        });

        //选择图标
        $("#selectIcon").on("click", function () {
            iconShow = layui.layer.open({
                type: 2,
                title: '选择图标',
                shadeClose: true,
                content: basePath + '/page/sys_menu_icons'
            });
            layui.layer.full(iconShow);
            $(window).on("resize", function () {
                setTimeout(function () {
                    layui.layer.full(iconShow);
                }, 150)
            })
        });

        initIcon = function (o, v, s) {
            var style = s ? ('font-size: ' + s + ' !important;') : ('font-size: 35px !important;');
            o.html(elf.parseIconHtml(v, style));
        };

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
                    url: basePath + '/menu',
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