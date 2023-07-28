//获取元素
let nav = $('#nav');
let menu_button = $('#menu_button');
let menu = $('#menu');
let admin_operate = $("#admin_oprate > li"); // <-- 管理菜单大选项
let admin_operate_list = $("#admin_oprate > ul > li[data-op]"); // <--拿到所有操作项
let innerpage = $('#inner_page');


//获取选择器
let inner_postadd = $('.inner_postadd_admin');

//常量定义
let menu_min_width = 200;
let menu_button_text_margin_left = 1;
let left_persent=0.08;
let innerdiv_width=500;
let allow_click=true;

//文档准备完毕时事件
$(document).ready(() => {
    //初始加载欢迎页
    innerpage.css('opacity', 0)
    innerpage.load('view/welcome_admin.jsp', () => fadeInElem(innerpage));
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
    //初始加载欢迎页
    $('#index').click(() => {
        if (allow_click)
        {
            allow_click=false;
            innerpage.css('opacity', 0)
            innerpage.load('view/welcome_admin.jsp', () => {
                left_persent=0.08;
                innerdiv_width=500;
                fadeInElem(innerpage);
                center();
            });
        }
    })
    //加载子选项跳转信息
    loadPage($('#post_admin'), inner_postadd, 'post_add', 'admin_post_add.jsp',0.10,1000);

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
    $(window).on('resize', () => {
        clearTimeout(resizeTimeout);
        resizeTimeout = setTimeout(center, 200); // 200毫秒的节流间隔
    });

    // 初始化调用center()函数，确保页面加载时菜单居中
    center();
})


function loadPage(clickElem, divElem, op, pageurl,leftP,innerDW) {
    //clickElem是点击目标jquery对象,op是操作名,pageurl是跳转页url
    let dataop = `[data-op=${op}]`;
    let url = `view/${pageurl}`;
    clickElem.click(() => {
        admin_operate_list.filter(dataop).click(() => {
            if (allow_click)
            {
                allow_click=false;
                left_persent=leftP;
                innerdiv_width=innerDW;
                innerpage.css('opacity', 0);
                innerpage.load(url, () => {fadeInElem(innerpage);center();});
            }
        });
    });
}

function fadeInElem(elem) {
    //传参为jquery获取的dom对象
    elem.animate({opacity: 1}, 300,'swing',()=>{allow_click=true;});
}

function fadeOutElem(elem) {
    //传参为jquery获取的dom对象
    elem.animate({opacity: 0}, 500,'swing',);
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

//弹性居中
function center() {
    //默认是欢迎页值
    //console.log(left_persent,innerdiv_width)
    let vw = $(window).width(); //窗口宽度
    let leftP = left_persent * vw;  //菜单栏移动百分比*窗口宽度
    let current_animation = menu.css('animation-name');
    if (current_animation === 'none' || current_animation === 'slidein-width, white-transparent') {
        $('.inner_page').find('div').animate({left: 0.5 * vw - innerdiv_width/2}, 1000);
    } else {
        $('.inner_page').find('div').animate({left: 0.5 * vw - leftP - innerdiv_width/2}, 1000);
    }
}

