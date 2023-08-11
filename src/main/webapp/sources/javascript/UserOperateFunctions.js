/* 固定参数 */
let max_td_num; //最大单元格行数

/* 全局数组，用于存储被选择的用户名 */
let selectedUsernames = new Set();
/* 全局数组,保存用户表字段数组 */
let userDataField = ['username', 'email', 'nick', 'password', 'pgroup'];

function loadUserSearchTable() {
    $.ajax({
        url: 'UserNumServlet',
        method: 'POST',
        dataType: 'text',
        success: (data) => {
            let data_int = parseInt(data);
            let target_table=$("#inner_user_operate > div > table[data-table='user_search']");
            let init=()=>getInfo(
                target_table,
                'UserSearchPaginateServlet',
                'data-field=username',
                $('#user_pagination'),
                data_int,
                getAutoPagesize(0.55),
                userDataField,
                selectedUsernames,
                0,
                false
            );
            init();
            $(window).resize(()=>init());
        },
        error: (jqXHR) => {
            console.log("用户数量请求错误:", jqXHR.status, jqXHR.statusText);
        }
    });
}



