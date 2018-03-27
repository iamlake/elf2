layui.use(['form', 'layer', 'laydate', 'util', 'elf'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        util = layui.util,
        elf = layui.elf;
    var isNew = false;

    laydate.render({
        elem: '#txt_birthDate', max: util.toDateString(new Date(), "yyyy-MM-dd")
    });

    form.on("submit(save)", function (data) {
        var ext = 'isNew:' + isNew;
        var url = basePath + '/user';
        if (!isNew) {
            url += '?_method=PUT';
        }
        var formData = elf.getBinding($('.layui-form'), ext);
        // 弹出loading
        var index = top.layer.msg('数据提交中，请稍候…', {
            icon: 16,
            time: 500,
            shade: 0.8
        }, function () {
            $.post({
                url: url,
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

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})