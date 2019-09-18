layui.use(['form'], function () {
    var form = layui.form
        , $ = layui.$;

    //监听提交
    form.on('submit(component-form-demo1)', function (data) {
        if (!$(".layui-btn").hasClass("layui-btn-disabled")) {
            $(".layui-btn").addClass("layui-btn-disabled")
        }
    });
});