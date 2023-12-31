//获取元素
let nav = $('#nav');
let menu_button = $('#menu_button'); // <--菜单按钮
let menu = $('#menu');  // <--菜单
let admin_operate = $("#admin_oprate > li"); // <-- 管理菜单大选项
let admin_operate_list = $("#admin_oprate > ul > li[data-op]"); // <--拿到所有操作项
let innerpage = $('#inner_page');  // <--内部页外层
let index = $('#index'); // <--内部欢迎页
let loginstate = $('#login_state'); // <--登录选项
let msgbox = $('#msg_box');
let msgbox_text = $('#msg_box div:first-child'); // <--消息盒子
let msgicon = $('#msg_box div:first-child::before');

//获取选择器

//常量定义
let menu_button_text_margin_left = 1;
let left_persent = getCssRootVarValue('slide-width', false); //居中盒子初始左边距离百分比
let fadeIn_speed = 300; //淡入速度 <- 淡入完毕之前禁止点击切换菜单
let center_speed = 1000; //居中动画速度
let current_innerpage; //当前页面
let click_lock; //切换点击加载锁

let userdata; //当前用户数据

//页面完全加载完毕时事件
$(window).on('load', () => {

    click_lock = true;
    // 初始触发resize事件，确保页面加载时菜单居中
    $(document).resize();

    //初始发送登录请求
    getLoginState();

    //初始加载欢迎页
    index.click(function () {
        if (click_lock) {
            if (current_innerpage !== 'index') {
                click_lock = false;
                innerpage.css('opacity', 0)
                innerpage.load('view/welcome_admin.jsp', () => {
                    current_innerpage = 'index';
                    if (userdata != null) {
                        $('.inner_index span:first-child').text(`欢迎使用本系统${',' + userdata['nick']}`);
                    } else {
                        let inps = $('#loginbox div div:nth-child(2)');
                        console.log(inps.html())
                        /* 初始清空输入框 */
                    }
                    fadeInElem(innerpage, true,);
                    center(false);
                });
            }
        }
    });

    //绑定登录(登出)功能
    loginstate.click(function () {
        if (loginstate.data('login-state') === 'login-out') {
            $.ajax({
                url: 'LoginOutServlet',
                method: 'POST',
                success: () => {
                    loginOutDeal();
                },
                error: (jqXHR) => {
                    console.log("登出请求错误:", jqXHR.status, jqXHR.statusText);
                }
            });
        }
    })


    /*innerpage.css('opacity', 0)
    innerpage.load('view/welcome_admin.jsp', () => fadeInElem(innerpage));*/
    //为菜单按钮绑定功能
    menu_button.on('click', () => {
        //获取当前打开状态,然后开关菜单后弹性居中
        let current_animation = menu.css('animation-name');
        //执行下面的判断和center
        if (current_animation === 'none' || current_animation === 'slidein-width, white-transparent') {
            openMenu(center);
        } else {
            closeMenu(center);
        }
    });

    //选择每个li绑定事件加载内部页
    //加载子选项跳转信息
    let clickElem;
    /* 文章管理 */
    clickElem = $('#post_admin');
    loadPage(clickElem, 'post_add', 'admin_post_add.jsp', () => loadPostAddTable());
    loadPage(clickElem, 'post_operate', 'admin_post_operate.jsp', () => loadPostSearchTable());
    loadPage(clickElem, 'post_review', 'admin_post_review.jsp', () => loadPostSearchTable());
    /* 用户管理 */
    clickElem = $('#user_admin');
    loadPage(clickElem, 'user_operate', 'admin_user_operate.jsp', () => loadUserSearchTable());
    loadPage(clickElem, 'pgroup_operate', 'admin_pgroup_operate.jsp', () => {
        loadPgroupSearchTable();
    });

    //对每个li的下一个ul收放
    admin_operate.each(function () {
        let op_list = $(this).next();
        //紧跟的不是ul则不绑定
        if (op_list.is('ul')) {
            //初始为收入状态
            op_list.hide();
            $(this).click(() => {
                op_list.slideToggle('swing');
            });
        }
    });

    // 监听窗口变化，并进行节流
    let resizeTimeout;
    $(window).resize(() => {
        click_lock = true;
        clearTimeout(resizeTimeout);
        resizeTimeout = setTimeout(() => {
            center();
        }, 300); // 300毫秒的节流间隔
    });

})

function getLoginState() {
    let loginspan = loginstate.find('span').eq(0);
    //发送请求拿到登录状态
    $.ajax({
        url: 'UserLoginCheckServlet',
        method: 'POST',
        dataType: 'json',
        success: (data) => {
            userdata = data;
            if (userdata === null) {
                loginOutDeal();
                loginstate.data('login-state', 'login-in');
            } else if (userdata.username !== undefined) {
                loginInDeal(loginspan);
                loginstate.data('login-state', 'login-out');
            }
            getBackgroundMessage();
        },
        error: (jqXHR) => {
            console.log("登录请求错误:", jqXHR.status, jqXHR.statusText);
        }
    });
}

function loginInDeal() {
    let span = loginstate.find('span').eq(0)
    loginstate.data('login-state', 'login-out');
    span.text('登出');
    let headimg = span.next('span');
    headimg.show();
    headimg.animate({opacity: 1}, 300, "swing");
    current_innerpage = 'login-out';
    index.click();
}

function loginOutDeal() {
    let span = loginstate.find('span').eq(0)
    loginstate.data('login-state', 'login-in');
    span.text('登录');
    let headimg = span.next('span');
    headimg.animate({opacity: 0}, 300, "swing", () => headimg.hide());
    current_innerpage = 'login-in';
    userdata = null;
    index.click();
}

let last_list_elem;
function loadPage(clickElem, op, pageurl, callback) {
    //clickElem是点击目标jquery对象,op是操作名,pageurl是跳转页url
    let dataop = `[data-op=${op}]`;
    let url = `view/${pageurl}`;
    clickElem.click(function () {
        let target_elem = admin_operate_list.filter(dataop);
        target_elem.click(function () {
            if (click_lock) {
                if (current_innerpage !== op) {
                    click_lock = false;
                    innerpage.css('opacity', 0);
                    // 使用 AJAX 请求加载页面内容
                    $.ajax({
                        url: url,
                        method: 'POST',
                        success: function (data) {
                            // 将加载的内容插入到 innerpage 中
                            innerpage.html(data);

                            if (typeof callback == 'function') callback();
                            current_innerpage = op;
                            if (last_list_elem !== undefined) {
                                last_list_elem.css({color: target_elem.css('color')});
                            }
                            last_list_elem = target_elem;
                            target_elem.css({color: '#bbbbbb'});

                            // 确保内容加载完毕后执行居中和淡入
                            // 这里可以添加一些条件判断，确保内容完全加载
                            getBackgroundMessage(); // <--拿到后台消息
                            center(false);
                        },
                        error: function () {
                            console.error('Failed to load page.');
                        }
                    });
                }
            }
        })
    });
}

function fadeInElem(elem, lock = true, callback) {
    //传参为jquery获取的dom对象
    elem.animate({opacity: 1}, fadeIn_speed, 'swing', () => {
        click_lock = lock;
        if (typeof callback === "function") callback();
    });
}

function fadeOutElem(elem, lock = true, callback) {
    //传参为jquery获取的dom对象
    elem.animate({opacity: 0}, fadeIn_speed, 'swing', () => {
        click_lock = lock;
        if (typeof callback === "function") callback();
    });
}

//打开菜单
function openMenu(callback) {
    menu_button.css('margin-left', menu_button_text_margin_left * 2 + '%');
    menu.css('animation-name', 'slideout-width, transparent-white');
    innerpage.css('animation-name', 'slideout-left');
    if (typeof callback === 'function') callback();
}

//关闭菜单
function closeMenu(callback) {
    menu_button.css('margin-left', menu_button_text_margin_left + '%');
    menu.css('animation-name', 'slidein-width, white-transparent');
    innerpage.css('animation-name', 'slidein-left');
    if (typeof callback === 'function') callback();
}

function fadeInMsg(speed, callback) {
    let cb = () => {
        if (typeof callback === "function") callback();
    }
    msgbox_text.show(() => {
        msgbox_text.animate({opacity: 1}, speed, 'swing', () => cb());
    });

}

function fadeOutMsg(speed, callback, hide = true) {
    let cb = () => {
        if (typeof callback === "function") callback();
    }
    msgbox_text.animate({opacity: 0}, speed, 'swing', () => {
        cb();
        if (typeof hide == "boolean" && hide === true) msgbox_text.hide();
    });

}

let timer_msgbox;

function fadeShowMsg(speed, fadeInTime, fadeOutTime, callback) {
    clearTimeout(timer_msgbox);
    fadeInMsg(fadeInTime, () => {
        timer_msgbox = setTimeout(() => {
            fadeOutMsg(fadeOutTime, () => {
                if (typeof callback === "function") callback();
            });
        }, speed);
    });
}

/* 动画效果写在css */
function ShowMsg(speed, fadeInTime, fadeOutTime, callback) {
    clearTimeout(timer_msgbox);
    fadeInMsg(0, () => {
        timer_msgbox = setTimeout(() => {
            fadeOutMsg(0, () => {
                if (typeof callback === "function") callback();
            }, false);
        }, speed);
    });
}

function slideShowMsg(speed, slideSpeed, callback) {
    clearTimeout(timer_msgbox);
    msgbox.removeClass('bleak').addClass('luminous-white');
    msgbox_text.animate({
        width: '100%',
        opacity: 1,
    }, slideSpeed, 'linear', () => {
        timer_msgbox = setTimeout(() => {
            msgbox.removeClass('luminous-white').addClass('bleak');
            msgbox_text.animate({width: 0, opacity: 0}, slideSpeed, 'linear', () => {
                if (typeof callback === "function") {
                    callback();
                }
            });
        }, speed);
    })
}

function getBackgroundMessage() {
    $.ajax({
        url: 'MessageServelt',
        dataType: 'text',
        method: 'POST',
        success: (msg) => {
            if (msg && msg !== '') {
                msgbox_text.text(msg);
                slideShowMsg(1000, 200, () => {
                    console.log('Got Message.', msg);
                    msgbox_text.text('');
                });
            }
        },
        error: (jqXHR) => {
            console.log("后台消息请求错误:", jqXHR.status, jqXHR.statusText);
        }
    });
}


//弹性居中
/*
如果添加了参数(true,false)
会使得剧中具备动画效果,时间有全局常量修改
该效果不好变控制,就算是作废的,源于导入页面后初始位置与居中位置不一导致的动画
关闭动画效果,则是取消了动画时间使得一开始就居中
*/
function center(isAnimation = true) {
    let current_animation = menu.css('animation-name'); //当前动画

    let vw = $(window).width(); //窗口宽度
    let leftP = 0.5 * (left_persent * vw);  //菜单栏移动百分比*窗口宽度

    let elem = $('.inner_page'); // 只选择带有标识类名的元素
    if (elem.length === 0) {
        console.log('unfind');
        return;
    } // 确保有匹配的元素

    let width = parseFloat(elem.find('div').css('width').slice(0, -2)); //内层盒子宽度
    let mid = 0.5 * vw - width / 2; //居中位置

    let speed = (isAnimation ? center_speed : 0); //是否有动画效果
    if (current_animation === 'none' || current_animation === 'slidein-width, white-transparent') {
        elem.find('div').animate({left: mid}, speed);
    } else {
        elem.find('div').animate({left: mid - leftP}, speed);
    }
}


//获取css的变量
function getCssRootVarValue(varname, referToWindow = true) {
    // 像素, 毫秒 获取整形值
    // 百分比, 秒 获取浮点值
    // referToWindow 指是否参考与窗口宽度按百分比返回像素值,为false时返回浮点百分比
    let val = $(':root').css('--' + varname);
    return standardizedValues(val, referToWindow)
}

//获取css的变量
function getCssVarValue(elem, cssAttr, referToWindow = true) {
    let val = elem.css(cssAttr);
    return standardizedValues(val, referToWindow);
}

function standardizedValues(val, referToWindow) {
    // 像素, 毫秒 获取整形值
    // 百分比, 秒 获取浮点值
    // referToWindow 指是否参考与窗口宽度按百分比返回像素值,为false时返回浮点百分比
    if (val.includes('px')) return parseInt(val.slice(0, -2));
    else if (val.includes('vh')) return $(window).height() * parseFloat(val.slice(0, -2)) / 100;
    else if (val.includes('%')) return (parseFloat(val.slice(0, -1)) / 100) * (referToWindow ? $(window).width() : 1);
    else if (val.includes('s')) return parseFloat(val.slice(0, -1)) * 1000;
    else if (val.includes('ms')) return parseInt(val.slice(0, -2));
    else return parseFloat(val);
}