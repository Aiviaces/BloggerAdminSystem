/* 固定参数 */

/* 全局数组，用于存储被选择的用户名 */
let selectedPostnames = new Set();
/* 全局数组,保存用户表字段数组 */
let postDataField = ['username', 'email', 'nick', 'password', 'pgroup'];

function loadPostSearchTable() {
    $.ajax({
        url: 'UserNumServlet',
        method: 'POST',
        dataType: 'text',
        success: (data) => {
            let data_int = parseInt(data);
            let target_table=$("#inner_post_operate > div > table[data-table='post_search']");
            let init=()=>getInfo(
                target_table,
                'UserSearchPaginateServlet',
                'data-field=username',
                $('#post_pagination'),
                data_int,
                getAutoPagesize(0.9),
                postDataField,
                selectedPostnames,
                0,
                false
            );
            init();
            $(window).resize(()=>init());
        },
        error: (jqXHR) => {
            console.log("用户组数量请求错误:", jqXHR.status, jqXHR.statusText);
        }
    });
}



