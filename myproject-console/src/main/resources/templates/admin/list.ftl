<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户角色</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">

<#--
    <link href="${site.contextPath}/lib/layui-v2.5.4/css/layui.css" rel="stylesheet" type="text/css"/>
-->
    <link href="${site.contextPath}/antdesign/css/main.css" rel="stylesheet" type="text/css"/>
    <link href="${site.contextPath}/fonts/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="${site.contextPath}/css/base.css" rel="stylesheet" type="text/css"/>
    <style>
        .edit {
            color: #1890FF
        }

        .search-btn {
            line-height: initial;
        }

        .layui-layer-btn .layui-layer-btn0 {
            text-align: center;
            width: 35px;
            height: 35px;
            line-height: 35px;
            margin-right: 10px;
        }

        .layui-layer-btn .layui-layer-btn1 {
            text-align: center;
            width: 35px;
            height: 35px;
            line-height: 35px;
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div class="layui-fluid" id="table-content">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <form id="search-form" class="layui-form" action="list" method="get">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        管理员名称：
                    </div>
                    <div class="layui-inline">
                        <input type="text" name="username" placeholder="请输入管理员名称"
                               <#if searchCdt?? && searchCdt.username??>value="${searchCdt.username}"</#if>
                               autocomplete="off"
                               class="layui-input">
                    </div>
                    <div class="layui-inline search-btn">
                        <button class="layui-btn" lay-submit lay-filter="search">查询</button>
                        <button id="search-reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="layui-card-body layui-form">
            <div style="padding-bottom: 5px;">
                <a href="add?pageNum=${pageResult.pageNum}&pageSize=${pageResult.pageSize}">
                    <button type="button" class="layui-btn layui-btn-normal" id="add-btn">+ 新建</button>
                </a>
                <button type="button" class="layui-btn layui-btn-danger" id="delete-btn" style="margin-left: 10px;">-
                    删除
                </button>
                <button type="button" class="layui-btn layui-btn-warm" id="refresh-btn">刷新</button>
            </div>

            <table class="layui-table">
                <thead>
                <tr>
                    <th style="width: 28px;">
                        <div class="layui-table-cell laytable-cell-1-0-0 laytable-cell-checkbox">
                            <input type="checkbox" id="checkAll" lay-skin="primary" lay-filter="allChoose">
                        </div>
                    </th>
                    <th>角色名称</th>
                    <th>是否启动</th>
                    <th>描述</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="content-table">
                        <#if pageResult.total != 0>
                            <#list pageResult.list as vo>
                            <tr>
                                <td>
                                    <div class="layui-table-cell laytable-cell-1-0-0 laytable-cell-checkbox">
                                        <input type="checkbox" name="ids" value="${vo.id}" lay-skin="primary">
                                    </div>
                                </td>
                                <td>
                                    ${vo.username}
                                </td>
                                <td>
                                    <#if vo.isBuiltin>
                                        <span class="green">是</span>
                                    <#else>
                                        <span class="gray">否</span>
                                    </#if>
                                </td>
                                <td>
                                    ${vo.description}
                                </td>
                                <td>
                                    ${vo.createDate?string('yyyy-MM-dd HH:mm:ss')}
                                </td>
                                <td>
                                    <a href="edit?id=${vo.id}&pageNum=${pageResult.pageNum}&pageSize=${pageResult.pageSize}"
                                       class="edit">编辑</a>
                                </td>
                            </tr>
                            </#list>
                        <#else>

                        </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<input id="msg" type="hidden" value="<#if msg?? >${msg}</#if>">
<#if pageResult?? && pageResult.total !=0 >
<div id="page-footer" style="padding-left: 30px;"></div>
</#if>
</body>
<script src="${site.contextPath}/lib/jquery.js"></script>
<script src="${site.contextPath}/layui/layui.all.js"></script>
<script src="${site.contextPath}/js/common/list.js"></script>
<script>
    $().ready(function () {
        var msg = $("#msg").val();
        if (msg) {
            layui.use('layer', function () {
                layer.msg(msg);
            })
        }

        layui.use(['laypage', 'form'], function () {
            laypage = layui.laypage, form = layui.form;
            laypage.render({
                elem: 'page-footer',
                count: ${pageResult.total},
                limit:${pageResult.pageSize},
                curr:${pageResult.pageNum},
                prev: '<em><</em>',
                next: '<em>></em>',
                layout: ['prev', 'page', 'next', 'limit', 'skip'],
                jump: function (obj, first) {
                    if (!first) {
                        loadPage(obj.curr, obj.limit);
                    }
                }
            });
            form.on('checkbox(allChoose)', function (data) {
                var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:enabled');
                child.each(function (index, item) {
                    item.checked = data.elem.checked;
                });
                form.render('checkbox');
            });
        });
    });
</script>
</html>
