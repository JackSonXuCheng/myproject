<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加用户角色</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<#--<link href="${site.contextPath}/lib/layui-v2.5.4/css/layui.css" rel="stylesheet" type="text/css"/>-->
    <link href="${site.contextPath}/antdesign/css/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body" style="padding: 15px;">
            <form class="layui-form" wid100 action="submit" method="post" lay-filter="component-form-group">
                <input type="hidden" name="pageSize" value="${pageSize}">
                <input type="hidden" name="pageNum" value="${pageNum}">
                <div class="layui-form-item">
                    <label class="layui-form-label">管理员名称：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="username" lay-verify="required" maxLength="128" placeholder="请输入管理员名称"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">管理员密码：</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" lay-verify="required" maxLength="128"
                               placeholder="请输入管理员密码"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">描述：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="description" lay-verify="required" maxLength="128" placeholder="请输入描述"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">是否启动:</label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="isBuiltin" id="switchTest" lay-skin="switch"
                               lay-filter="switchTest"
                               lay-text="是|不是">
                    </div>
                </div>
            <#--<div class="layui-form-item">
                <label class="layui-form-label">xxxx：</label>
                <div class="layui-input-inline">
                    <input type="text" name="description" lay-verify="required" maxLength="128" placeholder="xxxx" autocomplete="off" class="layui-input">
                </div>
            </div>-->

                <div class="layui-form-item">
                    <label class="layui-form-label"></label>
                    <div class="layui-input-block">
                        <div class="layui-footer" style="left: 0;">
                            <button class="layui-btn" lay-submit="" lay-filter="component-form-demo1">提 交</button>
                            <button type="reset" class="layui-btn layui-btn-primary"
                                    onclick="location.href='list?pageSize=${pageSize}&pageNum=${pageNum}'">取
                                消
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script src="${site.contextPath}/lib/jquery.js"></script>
<script src="${site.contextPath}/layui/layui.js"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form
                , layer = layui.layer
                , $ = layui.$;

        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 5) {
                    return '标题至少得5个字符啊';
                }
            }
            , pass: [
                /^[\S]{6,12}$/
                , '密码必须6到12位，且不能出现空格'
            ]
        });
    });
</script>
</html>
