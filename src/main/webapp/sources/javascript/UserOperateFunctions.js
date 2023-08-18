/**
 * @param selectedUsernames 全局数组，用于存储被选择的用户名
 * @param userDataField 全局数组,保存用户表字段数组
 * @param userExcludeField 如果就置空,加快速度
 */
let selectedUsernames = new Set();
let userDataField = ['username', 'email', 'nick', 'password', 'pgroup'];
let userExcludeField;


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
                            userExcludeField,
                            selectedUsernames,
                            0,
                            false,
                            'span',
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

