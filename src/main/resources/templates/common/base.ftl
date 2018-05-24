<#assign basePath = request.contextPath />
<#--<#macro basePath><#if springMacroRequestContext.getContextPath()=="/">-->
<#--<#else>${springMacroRequestContext.getContextPath()}-->
<#--</#if></#macro>-->
<#--<#global ctx><@basePath/></#global>-->
<base id="basePath" href="${basePath}">

<!-- layui -->
<link rel="icon" href="${basePath}/static/images/favicon.ico">
<link rel="stylesheet" href="${basePath}/static/assets/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="${basePath}/static/assets/css/public.css" media="all"/>
<!-- TODO 页面公共资源 -->
<script type="text/javascript">
    var basePath = document.getElementById("basePath").href;
    //外部图标链接
    var iconUrl = "https://at.alicdn.com/t/font_400842_q6tk84n9ywvu0udi.css";
    var loginUser = {
        account : '${(user_session.currentUser.account)!'N/A'}',
        fullname : '${(user_session.currentUser.fullname)!'N/A'}',
        userHead : '${(user_session.currentUser.userHead)!'N/A'}'
    }
</script>

<script type="text/javascript" src="${basePath}/static/assets/layui/layui.js"></script>
<script type="text/javascript" src="${basePath}/static/assets/js/global.js"></script>