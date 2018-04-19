<#include "../../common/base.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户中心--layui后台管理模板 2.0</title>
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
                <a class="layui-btn layui-btn-normal btn_edit"><i class="layui-icon">&#xe608;</i>新建用户</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-danger layui-btn-normal btn_delAll"><i class="layui-icon">&#xe640;</i>批量删除</a>
            </div>
        </form>
    </blockquote>
    <table id="table_user" lay-filter="userList"></table>

    <!--操作-->
    <script type="text/html" id="userListBar">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="doDetail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="doEdit"><i class="iconfont icon-edit"></i>编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="enabled">已启用</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="doDel"><i class="layui-icon">&#xe640;</i>删除</a>
    </script>
</form>
<script type="text/javascript" src="${basePath}/static/js/sys/user/userList.js"></script>
</body>
</html>