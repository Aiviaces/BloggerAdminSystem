/* 固定参数 */

/* 全局数组，用于存储被选择的用户名 */
let selectedPgroupnames = new Set();
/* 全局数组,保存用户表字段数组 */
let pgroupDataField = ['username', 'email', 'nick', 'password', 'pgroup'];

function loadPgroupSearchTable() {
    $.ajax({
        url: 'UserNumServlet',
        method: 'POST',
        dataType: 'text',
        success: (data) => {
            let data_int = parseInt(data);
            let target_table=$("#inner_pgroup_operate > div > table[data-table='pgroup_search']")
            let init=()=>getInfo(
                target_table,
                'UserSearchPaginateServlet',
                'data-field=username',
                $('#pgroup_pagination'),
                data_int,
                getAutoPagesize(0.55),
                pgroupDataField,
                selectedPgroupnames,
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



