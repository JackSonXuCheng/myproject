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
                        <input type="text" name="username" id="username" lay-verify="required|remote" maxLength="128"
                               placeholder="请输入管理员名称"
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
                    <label class="layui-form-label">管理员角色:</label>
                    <div class="layui-input-block">
                        <#list roles as role>
                            <input type="checkbox" name="roleIds" value="${role.id}" lay-skin="primary"
                                   title="${role.roleName}">
                        </#list>
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
    layui.use(['form', 'layer'], function () {
        var form = layui.form
                , layer = layui.layer;

        $("#username").mouseleave(function () {
            var username = $("#username").val();
            if (username == "") {
                layer.msg("请先填管理员名称");
                return false;
            }

            $.ajax({
                url: "checkAdmin",
                type: "get",
                data: {'username': username},
                cache: false,
                success: function (result) {
                    if (result.code != 0) {
                        layer.msg(result.message, {time: 1500});
                        $("#username").focus();
                    }
                }

            });

        });

        //自定义验证规则
        form.verify({
            remote: function (value) {
                var code = 0;
                $.ajax({
                    url: "checkAdmin",
                    type: "get",
                    data: {'username': value},
                    cache: false,
                    /*必须加上async 才能不能跳转添加*/
                    async: false,
                    success: function (result) {
                        if (result.code != 0) {
                            code = result.code;
                        }
                    }
                });
                if (code != 0) {
                    $("#username").focus();
                    return '该账号已存在';
                }
            },
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
