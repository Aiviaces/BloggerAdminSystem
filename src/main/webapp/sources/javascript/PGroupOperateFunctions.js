/* 固定参数 */

/* 全局数组，用于存储被选择的用户名 */
let selectedPgroupnames = new Set();
/* 全局数组,保存用户表字段数组 */
let pgroupDataField =
    [
        "uid",
        "name",
        "p_login",
        "p_admin_pgroup_operate",
        "p_index",
        "p_admin_user_operate",
        "p_admin_post_operate",
        "p_admin_post_add",
        "p_admin_post_review"
    ];
let pgroupExcludeField = ["uid", "name"];

function loadPgroupSearchTable() {
    fadeOutElem(innerpage, false, () => {
        $.ajax({
            url: 'PGroupNumServlet',
            method: 'POST',
            dataType: 'text',
            success: (data) => {
                let data_int = parseInt(data);
                let target_table = $("#inner_pgroup_operate > div > table[data-table='pgroup_search']")
                let init = () => {
                    fadeOutElem(innerpage, false, () => {
                        getInfo(
                            target_table,
                            'PGroupSearchPaginateServlet',
                            'data-field=uid',
                            $('#pgroup_pagination'),
                            data_int,
                            getAutoPagesize(0.9, 161),
                            pgroupDataField,
                            pgroupExcludeField,
                            selectedPgroupnames,
                            0,
                            false,
                            'checkbox',
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
                console.log("权限组组数量请求错误:", jqXHR.status, jqXHR.statusText);
            }
        });
    });
}



