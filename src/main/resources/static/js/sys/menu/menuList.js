layui.use(['form','layer','table','laytpl','treeGrid'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
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
                                return '<i class="seraph ' + d.icon + '" data-icon="' + d.icon + '"></i>';
                            } else {
                                return '<i class="layui-icon" data-icon="' + d.icon + '">' + d.icon + '</i>';
                            }
                        }else{
                            return "无";
                        }
                    }
                },
                {field: 'target', minWidth: '50', title: '目标框架', align: 'center'},
                // {field: 'menuOrder', minWidth: '50', title: '排序', align: 'center'},
                {field: 'appId', minWidth: '50', title: '应用', align: 'center'},
                {field: 'showDefault', minWidth: '50', title: '默认显示', align: 'center'},
                {title: '操作', minWidth: 175, templet: '#menuListBar', fixed: "right", align: "center"}
            ]],
        page: false
    });

    //搜索
    $(".btn_query").on("click",function(){
        if($(".search_value").val() != ''){
            table.reload("tableJson",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".search_value").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

})