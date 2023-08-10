/* 固定参数 */
let t_user_search_row = 7; // <-- 最多7
let offset = 0; // <-- 当前页偏移
let usernum = 0;
let maxpage = 0;
let t_user_search_inited = false; // <-- 表格初始化状态

/* 全局数组，用于存储被选择的用户名 */
let selectedUsernames = new Set();

function loadUserSearchTable() {
    $.ajax({
        url: 'UserNumServlet',
        method: 'GET',
        dataType: 'text',
        success: (data) => {
            let data_int = parseInt(data);
            usernum = data_int;
            maxpage = Math.floor(data_int / t_user_search_row);
            console.log('usernum', usernum);
        },
        error: (jqXHR) => {
            console.log("用户数量请求错误:", jqXHR.status, jqXHR.statusText);
        }
    })
    getUserInfo();
}

function getUserInfo(offset = 0, pagesize = t_user_search_row) {
    let target_table = $("#inner_user_operate > div > table[data-table='user_search']");
    $.ajax({
        url: 'UserSearchPaginateServlet',
        method: 'POST',
        dataType: 'json',
        data: {
            offset: offset,
            pagesize: pagesize
        },
        success: (data) => {
            let row = Math.min(data.length, t_user_search_row);
            if (t_user_search_inited) {
                setTableValues(target_table, data);
            } else {
                initTable(target_table, row, () => setTableValues(target_table, data));
            }
        },
        error: (jqXHR) => {
            console.log("用户列表请求错误:", jqXHR.status, jqXHR.statusText);
        }
    });
}

function initTable(target_table, row = t_user_search_row, callback) {
    target_table.find('tr:gt(0)').remove();
    for (let i = 0; i < row; i++) {
        let row = $('<tr></tr>');
        /* 复选框,用户名,邮箱,昵称,密码,用户组 */
        row.append($('<td><label><input type="checkbox"/></label></td>'));
        row.append($('<td data-field="username"></td>'));
        row.append($('<td data-field="email"></td>'));
        row.append($('<td data-field="nick"></td>'));
        row.append($('<td data-field="password"></td>'));
        row.append($('<td data-field="ugroup"></td>'));
        row.append($('<td><a>编辑</a><a>删除</a></td>'));
        target_table.append(row);
    }
    bindCheckBox(target_table);
    bindPaginateBox();
    t_user_search_inited = true;
    if (typeof callback == 'function') callback();
}

function setTableValues(target_table, key_values) {
    let cnt = key_values.length;
    let tds = target_table.find('tr:gt(0)');
    let row = tds.length;
    /* 用户名,邮箱,昵称,密码,用户组 */
    for (let i = 0; i < cnt; i++) {
        tds.eq(i).find('td[data-field=username]').text(key_values[i]['username']);
        tds.eq(i).find('td[data-field=email]').text(key_values[i]['email']);
        tds.eq(i).find('td[data-field=nick]').text(key_values[i]['nick']);
        tds.eq(i).find('td[data-field=password]').text(key_values[i]['password']);
        tds.eq(i).find('td[data-field=ugroup]').text(key_values[i]['ugroup']);
    }
    for (let i = cnt; i < row; i++) {
        tds.eq(i).find('td').remove();
    }
}

/* 全选功能,选择功能 */
function bindCheckBox(target_table) {

    // 全选复选框的点击事件委托
    target_table.on("change", "#selectall", function () {
        let isChecked = $(this).prop("checked");
        // 遍历除全选复选框外的其他复选框
        target_table.find("input[type='checkbox']").prop("checked", isChecked);
        if (isChecked) {
            // 将所有用户名添加到 selectedUsernames
            target_table.find("td[data-field='username']").each(function () {
                selectedUsernames.add($(this).text());
            });
        } else {
            selectedUsernames.clear();
        }
    });


    // 复选框的点击事件委托
    target_table.on("change", "input[type='checkbox']", function () {
        let isChecked = $(this).prop("checked");
        let username = $(this).closest("tr").find("td[data-field='username']").text();

        if (isChecked) {
            selectedUsernames.add(username);
        } else {
            selectedUsernames.delete(username);
        }
    });
}

function bindPaginateBox() {
    let pageination_box = $('#user_pagination');
    console.log(pageination_box.html())
    pageination_box.on('click', 'a', function () {
        let paginateType = $(this).data('paginate');
        console.log(paginateType)

        // 根据不同分页类型更新 offset 的值
        if (paginateType === 'begin') {
            offset = 0;
        } else if (paginateType === 'last') {
            // 计算上一页的 offset
            offset = Math.max(offset - t_user_search_row, 0);
        } else if (paginateType === 'next') {
            // 计算下一页的 offset
            offset = Math.min(offset + t_user_search_row, usernum - t_user_search_row);
            if (offset < 0) offset = 0;
        } else if (paginateType === 'end') {
            // 计算末页的 offset
            offset = (maxpage - 1) * t_user_search_row;
        }
        console.log('跳转页', offset);
        // 调用 getUserInfo 函数更新表格内容
        getUserInfo(offset);
    });
}