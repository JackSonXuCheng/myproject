<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui后台管理模板 2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${site.contextPath}/favicon.ico">
    <link rel="stylesheet" href="${site.contextPath}/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${site.contextPath}/css/index.css" media="all"/>
    <script src="${site.contextPath}/js/jquery/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${site.contextPath}/fonts/iconfont.css">
</head>
<body class="main_body">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部 -->
    <div class="layui-header header">
        <div class="layui-main mag0">
            <a href="#" class="logo">layuiCMS 2.0</a>
            <!-- 显示/隐藏菜单 -->
            <a href="javascript:;" class="seraph hideMenu icon-caidan"></a>
            <!-- 顶级菜单 -->
            <ul class="layui-nav mobileTopLevelMenus" mobile>
                <li class="layui-nav-item" data-menu="contentManagement">
                    <a href="javascript:;"><i class="seraph icon-caidan"></i><cite>layuiCMS</cite></a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this" data-menu="contentManagement"><a href="javascript:;"><i
                                class="layui-icon" data-icon="&#xe63c;">&#xe63c;</i><cite>内容管理</cite></a></dd>
                        <dd data-menu="memberCenter"><a href="javascript:;"><i class="seraph icon-icon10"
                                                                               data-icon="icon-icon10"></i><cite>用户中心</cite></a>
                        </dd>
                        <dd data-menu="systemeSttings"><a href="javascript:;"><i class="layui-icon"
                                                                                 data-icon="&#xe620;">&#xe620;</i><cite>系统设置</cite></a>
                        </dd>
                        <dd data-menu="seraphApi"><a href="javascript:;"><i class="layui-icon" data-icon="&#xe705;">&#xe705;</i><cite>使用文档</cite></a>
                        </dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav topLevelMenus" id="nav" pc>
                <li class="layui-nav-item layui-this" data-menu="contentManagement">
                    <a href="#contentManagement"><i class="layui-icon"
                                                    data-icon="&#xe63c;">&#xe63c;</i><cite>内容管理</cite></a>
                </li>
                <li class="layui-nav-item" data-menu="memberCenter" pc>
                    <a href="#memberCenter"><i class="seraph icon-icon10"
                                               data-icon="icon-icon10"></i><cite>用户中心</cite></a>
                </li>
                <li class="layui-nav-item" data-menu="seraphApi" pc>
                    <a href="#seraphApi"><i class="layui-icon " data-icon="&#xe705;">&#xe705;</i><cite>使用文档</cite></a>
                </li>
                <li class="layui-nav-item" data-menu="systemeSttings" pc>
                    <a href="#systemeSttings"><i class="iconfont iconquanjuguanlibeifen"></i><cite>系统设置</cite></a>
                </li>
            </ul>
            <!-- 顶部右侧菜单 -->
            <ul class="layui-nav top_menu">
                <li class="layui-nav-item" pc>
                    <a href="javascript:;" class="clearCache"><i class="layui-icon"
                                                                 data-icon="&#xe640;">&#xe640;</i><cite>清除缓存</cite><span
                            class="layui-badge-dot"></span></a>
                </li>
                <li class="layui-nav-item lockcms" pc>
                    <a href="javascript:;"><i class="seraph icon-lock"></i><cite>锁屏</cite></a>
                </li>
                <li class="layui-nav-item" id="userInfo">
                    <a href="javascript:;"><img src="${site.contextPath}/images/userAvar.jpg"
                                                class="layui-nav-img userAvatar" width="35" height="35"><cite
                            class="adminName"><@shiro.user> <@shiro.principal/> </@shiro.user></cite></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="page/user/userInfo.html"><i class="seraph icon-ziliao"
                                                                                         data-icon="icon-ziliao"></i><cite>个人资料</cite></a>
                        </dd>
                        <dd><a href="javascript:;" data-url="page/user/changePwd.html"><i class="seraph icon-xiugai"
                                                                                          data-icon="icon-xiugai"></i><cite>修改密码</cite></a>
                        </dd>
                        <dd><a href="javascript:;" class="showNotice"><i
                                class="layui-icon">&#xe645;</i><cite>系统公告</cite><span
                                class="layui-badge-dot"></span></a></dd>
                        <dd pc><a href="javascript:;" class="functionSetting"><i class="layui-icon">&#xe620;</i><cite>功能设定</cite><span
                                class="layui-badge-dot"></span></a></dd>
                        <dd pc><a href="javascript:;" class="changeSkin"><i
                                class="layui-icon">&#xe61b;</i><cite>更换皮肤</cite></a></dd>
                        <dd><a href="logout" class="signOut"><i class="seraph icon-tuichu"></i><cite>退出</cite></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧导航 -->
    <div class="layui-side layui-bg-black">
        <div class="user-photo">
            <a class="img" title="我的头像"><img src="${site.contextPath}/images/userAvar.jpg" class="userAvatar"></a>
            <p>你好！<span class="userName"><@shiro.user> <@shiro.principal/> </@shiro.user></span>, 欢迎登录</p>
        </div>
        <!-- 搜索 -->
        <div class="layui-form component">
            <select name="search" id="search" lay-search lay-filter="searchPage">
                <option value="">搜索页面或功能</option>
                <option value="1">layer</option>
                <option value="2">form</option>
            </select>
            <i class="layui-icon">&#xe615;</i>
        </div>
        <div class="navBar layui-side-scroll" id="menus">

            <ul class="layui-nav layui-nav-tree " id="contentManagement">
                <li class="layui-nav-item layui-this">
                    <a href="javascript:;" data-url="index"><i class="layui-icon"
                                                               data-icon=""></i><cite>后台首页</cite></a>
                </li>
                <li class="layui-nav-item"><a data-url="page/news/newsList.html"><i class="seraph icon-text"
                                                                                    data-icon="icon-text"></i><cite>文章列表</cite></a>
                </li>
                <li class="layui-nav-item"><a data-url="page/news/newsList.html"><i class="seraph icon-text"
                                                                                    data-icon="icon-text"></i><cite>图片管理</cite></a>
                </li>
                <li class="layui-nav-item"><a><i class="layui-icon" data-icon=""></i><cite>其他页面</cite><span
                        class="layui-nav-more"></span></a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="page/404.html"><i class="layui-icon" data-icon=""></i><cite>404页面</cite></a>
                        </dd>
                        <dd><a data-url="page/login/login.html" target="_blank"><i class="layui-icon"
                                                                                   data-icon=""></i><cite>登录</cite></a>
                        </dd>
                    </dl>
                </li>
                <span class="layui-nav-bar" style="top: 157.5px; height: 0px; opacity: 0;"></span>
            </ul>
            <ul class="layui-nav layui-nav-tree" id="memberCenter" style="display:none;">

                <li class="layui-nav-item"><a data-url="page/news/newsList.html"><i class="seraph icon-text"
                                                                                    data-icon="icon-text"></i><cite>测试一</cite></a>
                </li>
                <li class="layui-nav-item"><a data-url="page/news/newsList.html"><i class="seraph icon-text"
                                                                                    data-icon="icon-text"></i><cite>测试二</cite></a>
                </li>

            </ul>
            <ul class="layui-nav layui-nav-tree" id="systemeSttings" style="display:none;">
               <@shiro.hasPermission name="admin:newsList">
                <li class="layui-nav-item"><a data-url="page/news/newsList.html"><i class="seraph icon-text"
                                                                                    data-icon="icon-text"></i><cite>全局设置</cite></a>
                </li>
               </@shiro.hasPermission>

                <@shiro.hasPermission name="admin:role">
                <li class="layui-nav-item"><a data-url="role/list"><i class="seraph icon-text"
                                                                      data-icon="icon-text"></i><cite>角色管理</cite></a>
                </li>
                </@shiro.hasPermission>

                <@shiro.hasPermission name="admin:admin">
                <li class="layui-nav-item"><a data-url="admin/list"><i class="seraph icon-text"
                                                                       data-icon="icon-text"></i><cite>管理员管理</cite></a>
                </li>
                </@shiro.hasPermission>
            </ul>
        </div>


    </div>
    <!-- 右侧内容 -->
    <div class="layui-body layui-form">
        <div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
            <ul class="layui-tab-title top_tab" id="top_tabs">
                <li class="layui-this" lay-id=""><i class="layui-icon">&#xe68e;</i> <cite>后台首页</cite></li>
            </ul>
            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon caozuo">&#xe643;</i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i>
                            刷新当前</a></dd>
                        <dd><a href="javascript:;" class="closePageOther"><i class="seraph icon-prohibit"></i> 关闭其他</a>
                        </dd>
                        <dd><a href="javascript:;" class="closePageAll"><i class="seraph icon-guanbi"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div class="layui-tab-item layui-show">
                    <iframe></iframe>
                </div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <div class="layui-footer footer">
        <p><span>copyright @2019 My Project </span>　　<a onclick="donation()"
                                                        class="layui-btn layui-btn-danger layui-btn-sm">捐赠作者</a></p>
    </div>
</div>

<!-- 移动导航 -->
<div class="site-tree-mobile"><i class="layui-icon">&#xe602;</i></div>
<div class="site-mobile-shade"></div>

<script type="text/javascript" src="${site.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${site.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${site.contextPath}/js/cache.js"></script>
<script type="text/javascript">
    $().ready(function () {
        var $nav = $("#nav a");
        var $menus = $("#menus ul");
        $nav.click(function () {

            /*
              console.info("飒飒法零零"+$currentMenu);
              $navBar.hide();
              $currentMenu.show();
              $currentMenu.css("display","block");

              var $this=$(this);
              var va = $("#memberCenter").attr("id");
              console.info("va"+va);
              $("#menus ul").siblings().css("display","none");
              $( "#menus memberCenter ").show();*/
            var $this = $(this);
            var $currentMenu = $($this.attr("href"));
            $menus.hide();
            $currentMenu.show();

            $this.parent().parent().find("li").removeClass("layui-this");
            $this.parent().addClass("layui-this");
            return false;

        });
    });
</script>
</body>
</html>