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
    let cnf = {
        height: getCssRootVarValue('innerbox-div-heidth')
    }
    console.log(cnf)
    ckeditor = CKEDITOR.replace(textarea_id, cnf);
}