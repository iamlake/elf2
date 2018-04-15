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
    <style type="text/css">

    </style>
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" class="layui-input search_value" placeholder="请输入搜索的内容" />
                </div>
                <a class="layui-btn btn_query" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal btn_addRoot"><i class="layui-icon">&#xe608;</i>添加菜单</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn"><i class="layui-icon">&#xe640;</i>批量删除</a>
            </div>
        </form>
    </blockquote>
    <table id="table_menu" lay-filter="menuList"></table>

    <!--操作-->
    <script type="text/html" id="menuListBar">
        <a class="layui-btn layui-btn-xs" lay-event="doEdit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="doAddChild">添加子菜单</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="doDel">删除</a>
    </script>
</form>
<script type="text/javascript" src="${basePath}/static/js/sys/menu/menuList.js"></script>
</body>
</html>