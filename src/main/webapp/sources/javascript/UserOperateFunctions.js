/* 固定参数 */

/* 全局数组，用于存储被选择的用户名 */
let selectedUsernames = new Set();
/* 全局数组,保存用户表字段数组 */
let userDataField = ['username', 'email', 'nick', 'password', 'pgroup'];

function loadUserSearchTable() {
    fadeOutElem(innerpage, false, () => {
        $.ajax({
            url: 'UserNumServlet',
            method: 'POST',
            dataType: 'text',
            success: (data) => {
                let data_int = parseInt(data);
                let target_table = $("#inner_user_operate > div > table[data-table='user_search']");
                let init = () => {
                    fadeOutElem(innerpage, false, () => {
                        getInfo(
                            target_table,
                            'UserSearchPaginateServlet',
                            'data-field=username',
                            $('#user_pagination'),
                            data_int,
                            getAutoPagesize(0.9, 161),
                            userDataField,
                            selectedUsernames,
                            0,
                            false,
                            () => fadeInElem(innerpage)
                        );
                    });
                }
                let resizeTimeout;
                $(window).resize(() => {
                    click_lock = true;
                    clearTimeout(resizeTimeout);
                    resizeTimeout = setTimeout(() => {
                        init();
                    }, 300); // 300毫秒的节流间隔
                });
                init();
            },
            error: (jqXHR) => {
                console.log("用户数量请求错误:", jqXHR.status, jqXHR.statusText);
            }
        });
    });
}

