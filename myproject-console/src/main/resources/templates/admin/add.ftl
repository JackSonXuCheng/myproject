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
    <style>
        .lnglat {
            margin-top: -34px;
            margin-left: 311px;
        }

        .geo {
            margin-top: -62px;
            margin-left: 621px;
        }

    </style>
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
                    <label class="layui-form-label">地址：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="address" lay-verify="required" maxLength="128" placeholder="请输入关键字进行搜索"
                               autocomplete="off" class="layui-input" id="tipinput">
                        <input type="hidden" name="longitude" id="lonNum">
                        <input type="hidden" name="latitude" id="latNum">
                        <input type="text" class="layui-input lnglat" id="lnglat" readonly="readonly">
                        <button id="bt2" type="button" class="layui-btn geo" onclick="geoCode()">搜索</button>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div id="container" style="width:40%;height: 400px;margin-left: 108px;">

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
                                    onclick="location.href='list?pageSize=${pageSize}&pageNum=${pageNum}'">取消
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
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.10&key=fa88b622064b112e8caefeb05f40b790"></script>
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
<script>

    var map = new AMap.Map('container', {
        resizeEnable: true,
        zoom: 15
    });

    // 创建一个 Marker 实例：
    var marker = new AMap.Marker({
        // position: new AMap.LngLat(116.39, 39.9)   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
    });

    $().ready(function () {
        var lng = map.getCenter().toString();
        var lnglatArray = lng.split(",");
        $("#lonNum").val(lnglatArray[0]);
        $("#latNum").val(lnglatArray[1]);
        document.getElementById("lnglat").value = lng;

        AMap.plugin('AMap.Geocoder', function () {
            var geocoder = new AMap.Geocoder({
                zoomToAccuracy: true
                // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
            });
            geocoder.getAddress(lng, function (status, result) {
                if (status === 'complete' && result.regeocode) {
                    var address = result.regeocode.formattedAddress;
                    document.getElementById('tipinput').value = address;
                }
            });

        });
        /*AMap.plugin('AMap.Geolocation', function() {
            var geolocation = new AMap.Geolocation({
                enableHighAccuracy: true,//是否使用高精度定位，默认:true
                timeout: 10000,          //超过10秒后停止定位，默认：5s
                buttonOffset: new AMap.Pixel(10, 20)//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                //zoomToAccuracy: true   //定位成功后是否自动调整地图视野到定位点
            });

            geolocation.getCurrentPosition(function(status,result){
                if(status=='complete'){
                    var lng = result.position.toString();
                    var lnglatArray =lng.split(",");
                    var longitude =lnglatArray[0];
                    var latitude=lnglatArray[1];
                    $("#lonNum").val(longitude);
                    $("#latNum").val(latitude);
                    var marker = new AMap.Marker({
                        position: new AMap.LngLat(longitude, latitude)   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                    });
                    document.getElementById("lnglat").value = lng;
                }else{
                    onError(result)
                }
            });
        });*/

    });

    // 将创建的点标记添加到已有的地图实例：
    map.on('click', function (e) {
        var lng = e.lnglat.getLng() + ',' + e.lnglat.getLat();
        document.getElementById("lnglat").value = lng;
        $("#lonNum").val(e.lnglat.getLng());
        $("#latNum").val(e.lnglat.getLat());

        AMap.plugin('AMap.Geocoder', function () {
            var geocoder = new AMap.Geocoder({
                // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
            })
            geocoder.getAddress(lng, function (status, result) {
                if (status === 'complete' && result.regeocode) {
                    var address = result.regeocode.formattedAddress;
                    document.getElementById('tipinput').value = address;
                    var marker = new AMap.Marker({
                        position: new AMap.LngLat(e.lnglat.getLng(), e.lnglat.getLat()),   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                        offset: new AMap.Pixel(-13, -30)
                    });
                    map.clearMap();
                    marker.setMap(map);
                } else {
                    log.error('根据经纬度查询地址失败');

                }

            });

        });

    });
    map.add(marker);


    //解析定位结果
    function onComplete(data) {
        var lng = data.position.toString();
        var lnglatArray = lng.split(",");
        $("#lonNum").val(lnglatArray[0]);
        $("#latNum").val(lnglatArray[1]);
        alert(data.position);
        document.getElementById("lnglat").value = lng;
    }

    //解析定位错误信息
    function onError(data) {
        console.info(data);

    }


    //根据地址来进行查询
    function geoCode() {
        map.clearMap();
        AMap.plugin('AMap.Geocoder', function () {
            var geocoder = new AMap.Geocoder({
                // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
            })
            var address = document.getElementById('tipinput').value;
            geocoder.getLocation(address, function (status, result) {
                if (status === 'complete' && result.info === 'OK') {
                    var lnglat = result.geocodes[0].location.toString();
                    var lnglatArray = lnglat.split(",");
                    var lonNum = lnglatArray[0];
                    var latNum = lnglatArray[1]
                    $("#lonNum").val(lnglatArray[0]);
                    $("#latNum").val(lnglatArray[1]);

                    document.getElementById("lnglat").value = lonNum + ',' + latNum;

                    map.on('click', function (e) {
                        var lng = e.lnglat.getLng() + ',' + e.lnglat.getLat();
                        document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat()
                        geocoder.getAddress(lng, function (status, result) {
                            if (status === 'complete' && result.regeocode) {
                                var address = result.regeocode.formattedAddress;
                                document.getElementById('tipinput').value = address;
                            } else {
                                log.error('根据经纬度查询地址失败')
                            }
                        });
                    });

                    var marker = new AMap.Marker({
                        position: new AMap.LngLat(lnglatArray[0], lnglatArray[1]),   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                        offset: new AMap.Pixel(-13, -30)
                    });

                    map.setZoom(15);
                    map.setCenter(result.geocodes[0].location);

                    marker.setMap(map);
                }
            })

        })
    }

</script>
</html>
