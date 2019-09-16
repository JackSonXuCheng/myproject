layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
    $ = layui.jquery;

    /*$(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })*/

    /* //登录按钮
     form.on("submit(login)",function(data){
         $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
         setTimeout(function(){
             window.location.href = "/layuicms2.0";
         },1000);
         return false;
     })*/

})
