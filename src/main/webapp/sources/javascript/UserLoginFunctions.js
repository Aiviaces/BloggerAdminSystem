/* 登录 */
function login() {
    $('#loginbox').find('input[data-field]');
    $.ajax({
        url: 'UserLoginServlet',
        data: {},
        success: () => {
            //这里还没有做登录判断结果的返回
            getLoginState();
            loginInDeal(loginstate.find('span').eq(0));
            getBackgroundMessage();
        },
        error: (jqXHR) => {
            console.log("登录状态请求错误:", jqXHR.status, jqXHR.statusText);
        }
    })
}


$(document).ready(() => {
    // console.log(inps.html())
    /* 动画效果:聚焦进入输入状态打开验证码,离开时收起 */
    let captcha = $('#captcha div:first-child');
    $("input[data-field='captcha']");
    //监听输入框的focus事件
    captcha.click(function () {
        // 显示验证码盒子
        $(this).slideToggle(100, function () {
            flushCaptchaImg($(this).find('img'), captcha.width(), captcha.height());
            $(this).slideToggle(300);
        });
    });

    captcha.click();

    $('#loginbtn').click(() => {
        login();
    });
})

function flushCaptchaImg(elem, width, height, callback) {
    $.ajax({
        url: "CaptchaImgServlet",
        method: 'POST',
        data: {
            width: width,
            height: height
        },
        dataType: 'text',
        success: (img) => {
            elem.attr('src', img);
            if (typeof callback === "function") callback();
        },
        error: function () {
            elem.text('ERROR...');
            console.error('刷新验证码失败.');
        }
    })
}