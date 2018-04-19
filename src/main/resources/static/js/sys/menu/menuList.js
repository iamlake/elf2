var oData;
layui.use(['form', 'layer', 'elf', 'treeGrid'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        elf = layui.elf;
    var treeGrid = layui.treeGrid;

    var treeGridIns = treeGrid.render({
        elem: '#table_menu',
        url: basePath + '/menu',
        cellMinWidth: 100,
        id: "tableJson",
        height: "full-125",
        treeId: 'menuId',//树形id字段名称
        treeUpId: 'parentMenuId',//树形父id字段名称
        treeShowName: 'title',//以树形式显示的字段
        cols:
            [[
                {type: 'numbers'},
                {field: 'title', minWidth: '150', title: '菜单名称'},
                {field: 'href', minWidth: '200', title: '路径'},
                {field: 'icon', minWidth: '50', title: '图标', align: 'center', templet: function (d) {
                        if (d.icon != undefined && d.icon != '') {
                            if (d.icon.indexOf("icon-") != -1) {
                                return '<i class="seraph ' + d.icon + '" data-icon="' + d.icon + ' " style="font-size: 25px !important;"></i>';
                            } else {
                                return '<i class="layui-icon" data-icon="' + d.icon + '" style="font-size: 25px !important;">' + d.icon + '</i>';
                            }
                        } else {
                            return "无";
                        }
                    }
                },
                {field: 'target', minWidth: '50', title: '目标框架', align: 'center'},
                // {field: 'menuOrder', minWidth: '50', title: '排序', align: 'center'},
                {field: 'appId', minWidth: '50', title: '应用', align: 'center'},
                // {field: 'showDefault', minWidth: '50', title: '默认显示', align: 'center'},
                {title: '操作', minWidth: 175, templet: '#menuListBar', fixed: "right", align: "center"}
            ]],
        page: false
    });

    //搜索
    $(".btn_query").on("click", function () {
        if ($(".search_value").val() != '') {
            treeGrid.reload("tableJson", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".search_value").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    $(".btn_addRoot").click(function () {
        menuEditForward(null, 'root');
    })

    function menuEditForward(d, oType) {
        oData = d;
        var title = '';
        if ('root' == oType) {
            title = '新建根菜单';
        }else if('child' == oType){
            title = '新建子菜单';
        }else{
            title = '编辑菜单';
        }
        var index = layui.layer.open({
            title: title,
            type: 2,
            content: basePath + "/page/sys_menu_menuEdit?oType=" + oType,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回菜单列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        $(window).on("resize", function () {
            setTimeout(function () {
                layui.layer.full(index);
            }, 150)
        })
    }

    //列表操作
    treeGrid.on('tool(menuList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'doAddChild') { //新建子菜单
            // layer.msg('菜单名：' + data.title + ' 的查看操作');
            menuEditForward(data, 'child');
        } else if (layEvent === 'doEdit') { //编辑
            menuEditForward(data, 'edit');
        } else if (layEvent === 'doDel') { //删除
            layer.confirm('确定删除此菜单？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                //treeGridIns.reload();
                layer.close(index);
                // })
            });
        }
    });

})