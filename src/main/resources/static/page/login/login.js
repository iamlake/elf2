layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery, active = {
            changeImage : function() {
                var time = new Date().getTime();
                $('.kaptchaImage').attr("src", basePath + "/api/kaptcha.jpg?d=" + time);// 为了不让验证码缓存，安全起见需要次次都刷新
            }
        }

    // 登录按钮事件
    form.on("submit(login)",function(data){
        $.ajax({
            url : basePath + '/login',
            type : 'POST',
            data : data.field,
            success : function(result) {
                if(result.code == '0'){
                    window.location.href = basePath + "/page/index";
                }else{
                    if (result.msg != null) {
                        layer.msg(result.msg, {
                            time : 1000,
                            icon : 5,
                            anim : 6
                        });
                    }
                    active.changeImage();
                }
            }
        });
        return false;
    })

    $('.loginform .kaptchaImage').on('click', function() {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})