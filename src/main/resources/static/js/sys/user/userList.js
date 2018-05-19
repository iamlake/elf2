var oData, isNew;
layui.use(['form', 'layer', 'table', 'codelist'], function () {
    var $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        table = layui.table,
        codelist = layui.codelist;

    //用户列表
    var tableIns = table.render({
        elem: '#table_user',
        url: basePath + '/user',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [5, 10, 30, 100],
        limit: 10,
        id: "tableJson",
        cols: [[
            {type: 'checkbox', fixed: 'left', width: 50},
            {field: 'account', title: '账号', minWidth: 100, align: "center"},
            {field: 'fullname', title: '姓名', minWidth: 100, align: "center"},
            {field: 'email', title: '邮箱', minWidth: 200, align: 'center', templet: function (d) {
                    return '<a class="layui-blue" href="mailto:' + d.email + '">' + d.email + '</a>';
                }
            },
            {field: 'sex', title: '性别', align: 'center', templet: function (d) {
                    return codelist.getValueName("sex", d.sex);
                }
            },
            {field: 'activeFlag', title: '用户状态', align: 'center', templet: function (d) {
                    return d.activeFlag == "1" ? "正常使用" : "限制使用";
                    // return d.activeFlag == "1" ? '<span class="layui-badge layui-bg-green">正常</span>' : '<span class="layui-badge layui-bg-gray">停用</span>';
                }
            },
            // {field: 'userGrade', title: '用户等级', align:'center',templet:function(d){
            //         if(d.userGrade == "0"){
            //             return "注册会员";
            //         }else if(d.userGrade == "1"){
            //             return "中级会员";
            //         }else if(d.userGrade == "2"){
            //             return "高级会员";
            //         }else if(d.userGrade == "3"){
            //             return "钻石会员";
            //         }else if(d.userGrade == "4"){
            //             return "超级会员";
            //         }
            //     }},
            // {field: 'userEndTime', title: '最后登录时间', align:'center',minWidth:150},
            {title: '操作', minWidth: 225, templet: '#userListBar', fixed: "right", align: "center"}
        ]]
    });

    //搜索
    $(".btn_query").on("click", function () {
        table.reload("tableJson", {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                account: $(".search_accont").val(),
                fullname: $(".search_fullname").val()
            }
        })
    });

    //新建/编辑用户
    function userEditForward(data, type) {
        oData = data;
        isNew = type;
        var index = layui.layer.open({
            title: type ? "新建用户" : "编辑用户",
            type: 2,
            content: basePath + "/page/sys_user_userEdit",
            success: function (layero, index) {
                // var iframeWin = window[layero.find('iframe')[0]['name']];
                // if (d) {
                //     var body = layui.layer.getChildFrame('body', index);
                //     elf.setData(body.find(".layui-form"), d);
                //     form.render();
                // } else {
                //     iframeWin.isNew = true;
                // }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    $(".btn_edit").click(function () {
        userEditForward(null, true);
    })

    //TODO-----批量删除
    $(".btn_delAll").click(function () {
        var checkStatus = table.checkStatus('tableJson'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        } else {
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'doDetail') {//查看
            layer.msg('姓名：' + data.fullname + ' 的查看操作');
        } else if (layEvent === 'doEdit') { //编辑
            userEditForward(data, false);
        } else if (layEvent === 'enabled') { //启用停用
            var _this = $(this),
                enabledText = "是否确定停用此用户？",
                btnText = "已停用";
            if (_this.text() == "已停用") {
                enabledText = "是否确定启用此用户？",
                    btnText = "已启用";
            }
            layer.confirm(enabledText, {
                icon: 3,
                title: '提示信息',
                cancel: function (index) {
                    layer.close(index);
                }
            }, function (index) {
                _this.text(btnText);
                layer.close(index);
            }, function (index) {
                layer.close(index);
            });
        } else if (layEvent === 'doDel') { //删除
            layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            });
        }
    });

})