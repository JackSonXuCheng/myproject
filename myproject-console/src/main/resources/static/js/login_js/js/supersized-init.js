jQuery(function ($) {
    /**/
    $.supersized({

        // Functionality
        slideshow: 0,         //幻灯片显示 0 关闭 1开启
        slide_interval: 6000,    // Length between transitions 转换之间的长度
        transition: 1,    // 0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left 过渡：3，// 0-None，1-Fade，2-Slide
        transition_speed: 3000,    // Speed of transition
        performance: 1,    // 0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)

        // Size & Position
        min_width: 0,    // Min width allowed (in pixels)
        min_height: 0,    // Min height allowed (in pixels)
        vertical_center: 1,    // Vertically center background 垂直居中背景
        horizontal_center: 1,    // Horizontally center background 水平居中背景
        fit_always: 0,    // Image will never exceed browser width or height (Ignores min. dimensions)
        fit_portrait: 1,    // Portrait images will not exceed browser height 纵向图像不会超过浏览器高度
        fit_landscape: 0,    // Landscape images will not exceed browser width 景观图像不会超过浏览器宽度

        // Components
        slide_links: 'blank',    // Individual links for each slide (Options: false, 'num', 'name', 'blank')
        slides: [    // Slideshow Images
            {image: '../../../img/login_img/img/1.jpg'},
            {image: '../../../img/login_img/img/2.jpg'},
            {image: '../../../img/login_img/img/3.jpg'}
        ]

    });

});
