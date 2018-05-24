var $, tab, dataStr, layer, curapp;
layui.use(['bodyTab', 'form', 'element', 'layer', 'jquery', 'linq', 'elf'], function () {
    var form = layui.form,
        element = layui.element,
        linq = layui.linq, elf = layui.elf;
    $ = layui.$;
    layer = parent.layer === undefined ? layui.layer : top.layer;
    tab = layui.bodyTab({
        openTabNum: "20",  //最大可打开窗口数量
        url: basePath + "/authorityMenu" //获取菜单json地址
    });

    var ROLE_APP = loginUser.account + "_roleApp",
        ROLE_MENU = loginUser.account + "_roleMenu";


    //通过顶部菜单获取左侧二三级菜单
    function getData(application) {
        if (null == window.sessionStorage.getItem(ROLE_MENU)) {
            $.getJSON(tab.tabConfig.url, function (result) {
                if (result.data.length > 0) {
                    window.sessionStorage.setItem(ROLE_MENU, JSON.stringify(result.data));
                    var menuList = linq.from(result.data).where(function (x) {
                        return x.appId == application
                    }).toArray();
                    dataStr = elf.transTreeData(menuList, 'menuId', 'parentMenuId', 'children');
                    //重新渲染左侧菜单
                    tab.render();
                }
            })
        } else {
            var menuList = linq.from(JSON.parse(window.sessionStorage.getItem(ROLE_MENU))).where(function (x) {
                return x.appId == application
            }).toArray();
            dataStr = elf.transTreeData(menuList, 'menuId', 'parentMenuId', 'children');
            //重新渲染左侧菜单
            tab.render();
        }
    }

    //页面加载时判断左侧菜单是否显示

    //隐藏左侧导航
    $(".hideMenu").click(function () {
        if ($(".topLevelMenus li.layui-this a").data("url")) {
            layer.msg("此栏目状态下左侧菜单不可展开");  //主要为了避免左侧显示的内容与顶部菜单不匹配
            return false;
        }
        $(".layui-layout-admin").toggleClass("showMenu");
        //渲染顶部窗口
        tab.tabMove();
    })

    $(function () {
        loadTopLevelMenus();
    });

    function loadTopLevelMenus() {
        if (null == window.sessionStorage.getItem(ROLE_APP)) {
            $.get(basePath + "/authorityApp", function (result) {
                if (result.data.length > 0) {
                    window.sessionStorage.setItem(ROLE_APP, JSON.stringify(result.data));
                    buildTopLevelMenusHtml(result.data);
                } else {
                    layer.msg("您没有任何应用菜单权限！", {
                        time : 1000,
                        icon : 5,
                        anim : 6
                    });
                }
            });
        } else {
            buildTopLevelMenusHtml(JSON.parse(window.sessionStorage.getItem(ROLE_APP)));
        }
    }

    function buildTopLevelMenusHtml(appData) {
        var innerhtml = '';
        var innerhtml2 = '';
        //----topLevelMenus pc
        linq.from(appData).forEach(function (value, index) {
            if (index == 0) {
                curapp = value.appId;
                innerhtml += '<li class="layui-nav-item layui-this" data-menu="' + value.appId + '">';
                innerhtml += '<a href="javascript:;"><i class="layui-icon" data-icon="' + value.style + '">' + value.style + '</i><cite>' + value.title + '</cite></a>';
                innerhtml += '</li>';
            } else {
                innerhtml += '<li class="layui-nav-item" data-menu="' + value.appId + '" pc>';
                innerhtml += '<a href="javascript:;"><i class="layui-icon" data-icon="' + value.style + '">' + value.style + '</i><cite>' + value.title + '</cite></a>';
                innerhtml += '</li>';
            }
        });
        //----mobileTopLevelMenus
        innerhtml2 += '<li class="layui-nav-item" data-menu="' + curapp + '">';
        innerhtml2 += '<a href="javascript:;"><i class="seraph icon-caidan"></i><cite>layuiCMS</cite></a>';
        innerhtml2 += '<dl class="layui-nav-child">';
        linq.from(appData).forEach(function (value, index) {
            if (index == 0) {
                innerhtml2 += '<dd class="layui-this" data-menu="' + curapp + '">';
                innerhtml2 += '<a href="javascript:;"><i class="layui-icon" data-icon="' + value.style + '">' + value.style + '</i><cite>' + value.title + '</cite></a>';
                innerhtml2 += '</dd>';
            } else {
                innerhtml2 += '<dd data-menu="' + value.appId + '">';
                innerhtml2 += '<a href="javascript:;"><i class="layui-icon" data-icon="' + value.style + '">' + value.style + '</i><cite>' + value.title + '</cite></a>';
                innerhtml2 += '</dd>';
            }
        });
        innerhtml2 += '</dl></li>';
        $(".topLevelMenus").html(innerhtml);
        $(".mobileTopLevelMenus").html(innerhtml2);
        //通过顶部菜单获取左侧菜单
        $(".topLevelMenus li,.mobileTopLevelMenus dd").click(function () {
            if ($(this).parents(".mobileTopLevelMenus").length != "0") {
                $(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
            } else {
                $(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
            }
            $(".layui-layout-admin").removeClass("showMenu");
            $("body").addClass("site-mobile");
            curapp = $(this).data("menu");
            getData(curapp);
            //渲染顶部窗口
            tab.tabMove();
        });
        //通过顶部菜单获取左侧二三级菜单
        getData(curapp);
    }

    $(".changePwd").click(function () {
        layui.layer.open({
            title: "修改密码",
            type: 2,
            area: ['770px', '395px'],
            content: basePath + "/page/sys_user_changePwd",
            success: function (layero, index) {
                $.get(basePath + "/user/" + loginUser.account,
                    function (result) {
                        if (result.code == '0') {
                            var body = layui.layer.getChildFrame('body', index);
                            elf.setData(body.find(".layui-form"), result.data[0]);
                            form.render();
                        } else {
                            layer.msg("请刷新页面后重试！");
                        }
                    }
                );
            }
        })
    });

    //手机设备的简单适配
    $('.site-tree-mobile').on('click', function () {
        $('body').addClass('site-mobile');
    });
    $('.site-mobile-shade').on('click', function () {
        $('body').removeClass('site-mobile');
    });

    // 添加新窗口
    $("body").on("click", ".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')", function () {
        //如果不存在子级
        if ($(this).siblings().length == 0) {
            addTab($(this));
            $('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
        }
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    })

    //清除缓存
    $(".clearCache").click(function () {
        window.sessionStorage.clear();
        window.localStorage.clear();
        var index = layer.msg('清除缓存中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            layer.close(index);
            layer.msg("缓存清除成功！");
        }, 1000);
    })

    //用户注销
    $(".signOut").click(function () {
        window.sessionStorage.clear();
        window.localStorage.clear();
        window.location.href = basePath + "/logout";
    })

    //刷新后还原打开的窗口
    if (cacheStr == "true") {
        if (window.sessionStorage.getItem("menu") != null) {
            menu = JSON.parse(window.sessionStorage.getItem("menu"));
            curmenu = window.sessionStorage.getItem("curmenu");
            var openTitle = '';
            for (var i = 0; i < menu.length; i++) {
                openTitle = '';
                if (menu[i].iconfont) {
                    if (menu[i].iconfont.split("-")[0] == 'icon') {
                        openTitle += '<i class="seraph ' + menu[i].iconfont + '"></i>';
                    } else {
                        openTitle += '<i class="layui-icon">' + menu[i].iconfont + '</i>';
                    }
                }
                openTitle += '<cite>' + menu[i].title + '</cite>';
                openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + menu[i].layId + '">&#x1006;</i>';
                element.tabAdd("bodyTab", {
                    title: openTitle,
                    content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
                    id: menu[i].layId
                })
                //定位到刷新前的窗口
                if (curmenu != "undefined") {
                    if (curmenu == '' || curmenu == "null") {  //定位到后台首页
                        element.tabChange("bodyTab", '');
                    } else if (JSON.parse(curmenu).title == menu[i].title) {  //定位到刷新前的页面
                        element.tabChange("bodyTab", menu[i].layId);
                    }
                } else {
                    element.tabChange("bodyTab", menu[menu.length - 1].layId);
                }
            }
            //渲染顶部窗口
            tab.tabMove();
        }
    } else {
        window.sessionStorage.removeItem("menu");
        window.sessionStorage.removeItem("curmenu");
    }
})

//打开新窗口
function addTab(_this) {
    tab.tabAdd(_this);
}

//捐赠弹窗
function donation() {
    layer.tab({
        area: ['260px', '367px'],
        tab: [{
            title: "微信",
            content: "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src=" + basePath + "/static/assets/images/pay4wechat.jpg></div>"
        }, {
            title: "支付宝",
            content: "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src=" + basePath + "/static/assets/images/pay4alipay.jpg></div>"
        }]
    })
}

//图片管理弹窗
function showImg() {
    $.getJSON(basePath + '/static/assets/json/images.json', function (json) {
        var res = json;
        layer.photos({
            photos: res,
            anim: 5
        });
    });
}