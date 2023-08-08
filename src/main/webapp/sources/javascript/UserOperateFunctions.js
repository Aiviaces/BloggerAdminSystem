/* 固定参数 */
let t_user_search_row = 7; // <-- 最多8
let t_user_search_col = 5;

function loadUserSearchTable() {
    let t_user_search = $("#inner_user_operate > div > table[data-table='user_search']");
    t_user_search_row=(t_user_search_row>7?7:t_user_search_row);
    t_user_search.find('tr:gt(0)').remove();
    for (let i = 0; i < t_user_search_row; i++) {
        let row = $('<tr></tr>');
        row.append($('<td><label><input type="checkbox"/></label></td>'));
        for (let j=0;j<t_user_search_col;j++)
        {
            row.append('<td></td>');
        }
        row.append($('<td><a>编辑</a><a>删除</a></td>'));
        t_user_search.append(row);
    }
}