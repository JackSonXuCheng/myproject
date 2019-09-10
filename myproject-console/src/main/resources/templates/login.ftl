
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

    <meta charset="utf-8">
    <title>MyProject Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link rel="stylesheet" href="${site.contextPath}/css/login_css/css/reset.css">
    <link rel="stylesheet" href="${site.contextPath}/css/login_css/css/supersized.css">
    <link rel="stylesheet" href="${site.contextPath}/css/login_css/css/style.css">
    <script src="${site.contextPath}/js/jquery/jquery.min.js" type="text/javascript"></script>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
       <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
    <![endif]-->

</head>

<body>

<div class="page-container">
    <h1>Myproject Login</h1>
    <form action="login" method="post" id="logs">
        <div>
            <input type="text" name="username" class="username" placeholder="Username" autocomplete="off" required/>
        </div>
        <div>
            <input type="password" name="password" class="password" placeholder="Password" oncontextmenu="return false"
                   onpaste="return false" required/>
        </div>
        <button id="lo" type="button">Sign In</button>
    </form>
    <div class="connect">
        <p>If we can only encounter each other rather than stay with each other,then I wish we had never
            encountered.</p>
        <p style="margin-top:20px;">如果只是遇见，不能停留，不如不遇见。</p>
    </div>
</div>
<div class="alert" style="display:none">
    <h2>消息</h2>
    <div class="alert_con">
        <p id="ts">${msg}</p>
        <p style="line-height:70px"><a class="btn">确定</a></p>
    </div>
</div>

<!-- Javascript -->
<script src="${site.contextPath}/js/login_js/js/supersized.3.2.7.min.js"></script>
<script src="${site.contextPath}/js/login_js/js/supersized-init.js"></script>
<script>
    $(".btn").click(function () {
        is_hide();
    })
    var u = $("input[name=username]");
    var p = $("input[name=password]");
    /*$("#submit").live('click',function(){
        if(u.val() == '' || p.val() =='')
        {
            $("#ts").html("用户名或密码不能为空~");
            is_show();
            return false;
        }
    });*/
    window.onload = function () {
        $(".connect p").eq(0).animate({"left": "0%"}, 600);
        $(".connect p").eq(1).animate({"left": "0%"}, 400);
        if ("${msg}") {
            is_show();
        }
    }

    function is_hide() {
        $(".alert").animate({"top": "-40%"}, 300)
    }

    function is_show() {
        $(".alert").show().animate({"top": "45%"}, 300)
    }
</script>
<script>
    $("#lo").click(function () {
        if (u.val() == '' || p.val() == '') {
            $("#ts").html("用户名或密码不能为空~");
            is_show();
            return false;
        }
        $("#logs").submit();
    });
</script>
</body>

</html>

