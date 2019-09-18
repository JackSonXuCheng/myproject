$().ready(function () {
    //重置
    $(document).on('click', '#search-reset', function () {
        $("#search-form").find('input[type=text],input[type=hidden]').each(function () {
            $(this).attr("value", "");
        });
        $("#search-form").find('select').each(function () {
            $(this).attr("value", "");
        })
    });

    // 全选
    $("#checkAll").click(function () {
        var $this = $(this);
        var $enabledIds = $("#content-table input[name='ids']:enabled");
        if ($this.prop("checked")) {
            $enabledIds.prop("checked", true);
        } else {
            $enabledIds.prop("checked", false);
        }
    });

    //单选
    $(document).on('click', "#content-table input[name='ids']", function () {
        var $this = $(this);
        if ($this.prop("checked")) {
            $this.closest("tr").addClass("selected");
        } else {
            $this.closest("tr").removeClass("selected");
        }
    });

    // 删除
    $(document).on('click', '#delete-btn', function () {
        var $checkedIds = $("#content-table input[name='ids']:enabled:checked");

        layui.use('layer', function () {
            var layer = layui.layer;
            if ($checkedIds == null || $checkedIds.length == 0) {
                layer.msg('请选择删除对象', {time: 2000});
            } else {
                layer.open({
                    type: 1
                    , title: false //不显示标题栏
                    , closeBtn: false
                    , shade: 0
                    , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    , btn: ['删除', '取消']
                    , btnAlign: 'c'
                    , moveType: 1 //拖拽模式，0或者1
                    , area: '300px;'
                    , content: '<div style="padding: 50px;text-align: center;font-weight: 300;">' + "确定删除吗？" + '</div>'
                    , success: function (layero) {
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').click(function () {
                            $.ajax({
                                url: "delete",
                                type: "POST",
                                data: $checkedIds.serialize(),
                                dataType: "json",
                                cache: false,
                                success: function (result) {
                                    if (result.code == 0) {
                                        $checkedIds.closest("tr").remove();
                                        //location.reload(true);
                                        if ($("#content-table").find("tr").size() <= 1) {
                                            setTimeout(function () {
                                                location.reload(true);
                                            }, 1000);
                                        }
                                    }
                                    layer.msg(result.message, {time: 2000});
                                    $checkedIds.prop("checked", false);
                                },
                            });
                        })
                    }
                });
            }
        });
    });

    //刷新
    $(document).on('click', '#refresh-btn', function () {
        var p = $(".layui-laypage-skip .layui-input").val();
        var s = $(".layui-laypage-limits").find("option:selected").val();
        if (p == undefined || s == undefined) {
            location.reload(true);
        } else {
            loadPage(p, s);
        }
    });

    window.loadPage = function (page, pagesize) {
        var p = "";
        var t = $("#search-form").serializeArray();
        $.each(t, function (i, v) {
            if (v.name && v.value) {
                p = p + "&" + v.name + "=" + v.value;
            }
        });
        $.get(location.pathname + "?pageNum=" + page + "&pageSize=" + pagesize + p,
            function (result) {
                $("#table-content").html($(result).filter("#table-content").html());
                layui.form.render();
            }
        );
    }
});