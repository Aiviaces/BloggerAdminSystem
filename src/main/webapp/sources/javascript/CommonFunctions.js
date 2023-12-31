/* 全选功能,选择功能 */
function bindCheckBox(target_table, target_field, save_array) {
    target_table.off("change");
    // 全选复选框的点击事件委托
    target_table.on("change", "#selectall", function () {
        let isChecked = $(this).prop("checked");
        // 遍历除全选复选框外的其他复选框
        target_table.find(`input[type='checkbox']`).prop("checked", isChecked);
        if (isChecked) {
            // 将所有用户名添加到 selectedUsernames
            target_table.find(`td[${target_field}]`).each(function () {
                save_array.add($(this).text());
            });
        } else {
            save_array.clear();
        }
    });


    // 复选框的点击事件委托
    target_table.on("change", "input[type='checkbox']", function () {
        let isChecked = $(this).prop("checked");
        let field = $(this).closest("tr").find(`td[${target_field}]`).text();
        if (isChecked) {
            save_array.add(field);
        } else {
            save_array.delete(field);
        }
    });
}

function bindPaginateBox(target_table, target_servlet, target_field, target_paginatebox, pagesize, num, datafield_array, excludefield_array, save_array, type = 'span') {
    target_paginatebox.off('click');
    target_paginatebox.data('offset', 0);
    target_paginatebox.on('click', 'a', function () {

        let paginateType = $(this).data('paginate');
        let offset = target_paginatebox.data('offset');

        // 根据不同分页类型更新 offset 的值
        if (paginateType === 'begin') {
            offset = 0;
        } else if (paginateType === 'last') {
            // 计算上一页的 offset
            offset = Math.max(offset - pagesize, 0);
        } else if (paginateType === 'next') {
            // 计算下一页的 offset
            offset = Math.max(0, Math.min(offset + pagesize, num - pagesize));
        } else if (paginateType === 'end') {
            // 计算末页的 offset
            offset = Math.max(0, num - pagesize);
        }
        // 调用 getUserInfo 函数更新表格内容
        getInfo(
            target_table,
            target_servlet,
            target_field,
            target_paginatebox,
            num,
            pagesize,
            datafield_array,
            excludefield_array,
            save_array,
            offset,
            true,
            type
        );
        target_paginatebox.data('offset', offset);
    });
}

/**
 * 1. 务必保证传入参数的数组 key 与 datafield (data-field属性的值) 数组相同匹配
 * 2. 排除项做的比较粗糙,代码之后再优化了
 *  */
function setTableValues(target_table, key_values, datafield_array, type = "span", exclude_field_array) {
    let cnt1 = key_values.length;
    let cnt2 = datafield_array.length;
    let tds = target_table.find('tr:gt(0)');
    /* 用户名,邮箱,昵称,密码,用户组 */
    for (let i = 0; i < cnt1; i++) {
        let tr = tds.eq(i);
        for (let j = 0; j < cnt2; j++) {
            let key = datafield_array[j]; //数据域
            let value = key_values[i][key];
            let td = tr.find(`td[data-field=${key}]`);
            const haveExcludeField = exclude_field_array && exclude_field_array.includes(key);

            if (type === "span") {
                const html = (haveExcludeField ? `<span></span>` : `<span>${value}</span>`);
                td.html(html).attr('title', value);
            } else if (type === "checkbox") {
                let html;
                if (!haveExcludeField) {
                    if (value === 1) value = 'checked';
                    else if (value === 0) value = '';
                    else {
                        console.log('后端响应值错误', key, value);
                        continue;
                    }
                    html = `<input type="checkbox" ${value}\>`;
                    td.addClass('disabled-checkbox');
                } else {
                    html = `<span>${value}</span>`;
                }
                td.html(html).attr('title', value);
            }
        }
    }
}


function initTable(target_table, target_servlet, target_field, target_paginatebox, num, pagesize, callback, save_array, datafield_array, excludefield_array, type) {
    target_table.find('tr:gt(0)').remove();

    let addRow = (isNull = false) => {
        let row = $('<tr></tr>');
        /* 复选框,用户名,邮箱,昵称,密码,用户组 */
        row.append($(`<td><label>${(isNull ? '' : '<input type="checkbox"/>')}</label></td>`));
        for (let i = 0; i < datafield_array.length; i++) {
            row.append($(`<td data-field=${datafield_array[i]}></td>`));
        }
        row.append($(`<td>${(isNull ? '' : '<a>编辑</a><a>删除</a>')}</td>`));
        target_table.append(row);
    }
    for (let i = 0; i < pagesize; i++) {
        if (i < num) addRow();
        else addRow(true);
    }
    //如果条数小于行数,多的行留空

    bindCheckBox(target_table, target_field, save_array);
    bindPaginateBox(
        target_table,
        target_servlet,
        target_field,
        target_paginatebox,
        pagesize, num, datafield_array, excludefield_array, save_array, type
    );
    if (typeof callback == 'function') callback();
}

function getInfo(target_table, target_servlet, target_field, target_paginatebox, num, pagesize, datafield_array, excludefield_array, save_array, offset = 0, inited = true, type = 'span', callback) {
    $.ajax({
        url: target_servlet,
        method: 'POST',
        dataType: 'json',
        data: {
            offset: offset,
            pagesize: pagesize
        },
        success: (data) => {
            /*console.log({
                源Servlet: target_servlet,
                方式: (inited ? '重置单元格内容' : '初始化单元格'),
                填入值类型: type,
                传入值: data
            });*/
            if (inited) {
                setTableValues(target_table, data, datafield_array, type, excludefield_array);
            } else {
                initTable(
                    target_table,
                    target_servlet,
                    target_field,
                    target_paginatebox,
                    num,
                    pagesize,
                    () => setTableValues(target_table, data, datafield_array, type, excludefield_array),
                    save_array,
                    datafield_array,
                    excludefield_array,
                    type
                );
                if (typeof callback === "function") callback();
            }
        },
        error: (jqXHR) => {
            console.log("用户列表请求错误:", jqXHR.status, jqXHR.statusText);
        }
    });
}

/**
 * 获取自适应单元格的行数
 * @param persent 百分比乘算
 * @param offset Y轴上的偏移量
 * @returns {number} 返回自适应单元格行数
 */
function getAutoPagesize(persent = 1, offset = 0) {
    let max_td_num = Math.floor(1 / (getCssRootVarValue('t-search-row-height') / (getCssRootVarValue('innerbox-div-heidth') - offset)));
    return Math.floor(max_td_num * persent);
}