<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>登录--layui后台管理模板 2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${site.contextPath}/favicon.ico">
    <link rel="stylesheet" href="${site.contextPath}/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${site.contextPath}/css/public.css" media="all"/>
    <script src="${site.contextPath}/js/jquery/jquery.min.js" type="text/javascript"></script>

</head>
<body class="loginBody">
<form class="layui-form" action="login" id="login" method="post">
    <div class="login_face"><img src="${site.contextPath}/images/userAvar.jpg" class="userAvatar"></div>
    <div class="layui-form-item input-item">
        <label for="username">用户名</label>
        <input type="text" placeholder="请输入用户名" autocomplete="off" id="userName" name="username" class="layui-input"
               lay-verify="required">
    </div>
    <div class="layui-form-item input-item">
        <label for="password">密码</label>
        <input type="password" placeholder="请输入密码" autocomplete="off" id="password" name="password" class="layui-input"
               lay-verify="required">
    </div>
    <div class="layui-form-item input-item" id="imgCode">
        <label for="code">验证码</label>
        <input type="text" placeholder="请输入验证码" autocomplete="off" name="code" class="layui-input"
               lay-verify="required">
        <img src="img/getKaptchaImage">
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-block" id="lo" lay-filter="login" lay-submit>登录</button>
    </div>
    <input id="msg" type="hidden" value="${msg}">

    <script type="text/javascript" src="${site.contextPath}/layui/layui.js"></script>

</form>
<script>
    var layer;
    layui.use(['element', 'table', 'layer'], function () {
        layer = layui.layer;

        //表单输入效果
        $(".loginBody .input-item").click(function (e) {
            e.stopPropagation();
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
        })
        $(".loginBody .layui-form-item .layui-input").focus(function () {
            $(this).parent().addClass("layui-input-focus");
        })
        $(".loginBody .layui-form-item .layui-input").blur(function () {
            $(this).parent().removeClass("layui-input-focus");
            if ($(this).val() != '') {
                $(this).parent().addClass("layui-input-active");
            } else {
                $(this).parent().removeClass("layui-input-active");
            }
        })
        var msg = $("#msg").val();
        if (msg) {
            layer.msg(msg, {time: 3000})
        }
    });

</script>
<script>


</script>
</body>
</html>