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
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">账号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input account" lay-verify="required" placeholder="请输入账号" name="account">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" placeholder="请输入姓名" name="fullname">
        </div>
    </div>
    <div class="layui-row">
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block sex"></div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">生日</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-verify="date" placeholder="请输入出生年月" id="txt_birthDate"
                       name="birthdate">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">用户类别</label>
            <div class="layui-input-block">
                <select class="userType" lay-filter="userType" name="userType">
                    <option value="0">系统管理员</option>
                    <option value="1">业务用户</option>
                    <option value="2">游客</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">电子邮件</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input email" lay-verify="required|email" autocomplete="off"
                   placeholder="请输入邮箱地址" name="email">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">移动电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="phone" placeholder="请输入手机号码"
                   name="mobileTelephone">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" placeholder="请输入家庭住址" name="homeAddress">
        </div>
    </div>
    <div class="layui-row">
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">证件类型</label>
            <div class="layui-input-block">
                <select class="credentialsType" lay-filter="credentialsType" name="credentialsType"></select>
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">证件号码</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input credentialsNumber" autocomplete="off"
                       placeholder="请输入证件号码" name="credentialsNumber">
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
    <div class="layui-form-item">
        <label class="layui-form-label">是否锁定</label>
        <div class="layui-input-block">
            <input type="checkbox" lay-skin="switch" lay-filter="isLocked" value="1" lay-text="是|否" id="switch_isLocked">
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
    <input type="hidden" name="userId" id="hid_userId">
    <input type="hidden" name="isLocked" id="hid_isLocked" value="F">
</form>
<script type="text/javascript" src="${basePath}/static/js/sys/user/userEdit.js"></script>
</body>
</html>