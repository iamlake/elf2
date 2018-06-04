<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>图标管理--layui后台管理模板 2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <style>
        .iconsLength{ margin:0 5px;}
        .icons li{  margin:5px 0; text-align:center; height:120px; cursor:pointer;}
        .icons li i{ display:block; font-size:35px!important; margin:10px 0; line-height:60px; height:60px;}
        .icons li:hover{ background:rgba(13,10,49,.9); border-radius:5px; color:#fff;}
        .icons li:hover i{ font-size:50px;}
        #copyText{ width:0;height:0; opacity:0; position:absolute; left:-9999px; top:-9999px;}
    </style>
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote">
    内置图标一览表（<span class="layui-red iconsLength"></span>个）。<span class="layui-word-aux">【点击可复制】</span>
</blockquote>
<textarea id="copyText"></textarea>
<ul class="icons layui-row"></ul>

<script type="text/javascript">
    layui.use(['form', 'layer', 'jquery'], function () {
        var defaultIconUrl = basePath + "/static/assets/layui/css/layui.css";
        var layer = parent.layer === undefined ? layui.layer : top.layer;
        var $ = layui.jquery;

        var isPickup;

        $(function () {
            isPickup = parent.iconShow == undefined ? false : true;

            $.get(defaultIconUrl, function (data) {
                var iconHtml = '';
                var iconsArray = data.split(".layui-icon-");
                iconsArray.sort();
                for (var i = 1; i < iconsArray.length; i++) {
                    iconHtml += "<li class='layui-col-xs4 layui-col-sm3 layui-col-md2 layui-col-lg1'>" +
                            "<i class='layui-icon layui-icon-" + iconsArray[i].split(":before")[0] + "'></i>" +
                            "layui-icon-" + iconsArray[i].split(':before')[0] + "</li>";
                }
                $(".icons").html(iconHtml);
                $(".iconsLength").text(iconsArray.length - 1);
            })
        });

        $("body").on("click", ".icons li", function () {
            if (!isPickup) {
                var copyText = document.getElementById("copyText");
                copyText.innerText = $(this).text();
                copyText.select();
                document.execCommand("copy");
                layer.msg("复制成功", {anim: 2});
            } else {
                var iconValue = $(this).text();
                parent.initIcon(parent.$("#realIcon"), iconValue, null);
                parent.$("#iconValue").val(iconValue);
                parent.layer.close(parent.layer.getFrameIndex(window.name));
            }
        })
    })
</script>
</body>
</html>