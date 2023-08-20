/* 固定参数 */

/* 获取编辑框 */
let ckeditor;

function loadPostAddTable() {
    initCkeditor('ckeditor');
    fadeOutElem(innerpage, false, () => {
        fadeInElem(innerpage);
    });
}

function initCkeditor(textarea_id) {
    //先判断是否存在输入框,不存在就不初始化
    let exist = $(document).find(`#${textarea_id}`).length;
    if (!exist) return;
    let cnf = {
        height: getCssRootVarValue('innerbox-div-heidth')
    }
    console.log(cnf)
    ckeditor = CKEDITOR.replace(textarea_id, cnf);
}