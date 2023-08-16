/* 全选功能,选择功能 */
function bindCheckBox(target_table, target_field, save_array) {

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

function bindPaginateBox(target_table, target_servlet,target_field, target_paginatebox, pagesize, num, datafield_array, save_array) {
    let maxpage = Math.floor(num / pagesize);
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
            offset = Math.min(offset + pagesize, num - pagesize);
            if (offset < 0) offset = 0;
        } else if (paginateType === 'end') {
            // 计算末页的 offset
            offset = (maxpage - 1) * pagesize;
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
            save_array,
            offset,
            true
        );
        target_paginatebox.data('offset', offset);
        console.log('页偏移', target_paginatebox.data('offset'));
    });
}

/**
 * 务必保证传入参数的数组 key 与 datafield (data-field属性的值) 数组相同匹配,后者数组为可变参数
 *  */
function setTableValues(target_table, key_values, datafield_array) {
    let cnt1 = key_values.length;
    let cnt2 = datafield_array.length;
    let tds = target_table.find('tr:gt(0)');
    /* 用户名,邮箱,昵称,密码,用户组 */
    for (let i = 0; i < cnt1; i++) {
        for (let j = 0; j < cnt2; j++) {
            let key=datafield_array[j]; //数据域
            let value=key_values[i][key];
            tds.eq(i).find(`td[data-field=${key}]`).html(`<span>${value}</span>`).attr('title',value);
        }
    }
}


function initTable(target_table,target_servlet, target_field, target_paginatebox,  num, pagesize, callback, save_array, datafield_array) {
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
    for (let i = 0; i < pagesize; i++){
        if (i<num) addRow();
        else addRow(true);
    }
    //如果条数小于行数,多的行留空

    bindCheckBox(target_table, target_field, save_array);
    bindPaginateBox(
        target_table,
        target_servlet,
        target_field,
        target_paginatebox,
        pagesize, num, datafield_array, save_array
    );
    if (typeof callback == 'function') callback();
}

function getInfo(target_table, target_servlet, target_field, target_paginatebox, num, pagesize, datafield_array, save_array, offset = 0, inited = true, callback) {
    $.ajax({
        url: target_servlet,
        method: 'POST',
        dataType: 'json',
        data: {
            offset: offset,
            pagesize: pagesize
        },
        success: (data) => {

            if (inited) {
                setTableValues(target_table, data, datafield_array);
            } else {
                initTable(
                    target_table,
                    target_servlet,
                    target_field,
                    target_paginatebox,
                    num,
                    pagesize,
                    () => setTableValues(target_table, data, datafield_array),
                    save_array,
                    datafield_array
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
    let res = Math.floor(max_td_num * persent);
    return res;
}