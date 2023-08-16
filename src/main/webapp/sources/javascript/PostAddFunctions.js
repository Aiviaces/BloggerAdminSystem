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
    ckeditor = CKEDITOR.replace(textarea_id, {
        width: 'inherit',
        height: 'inherit'
    });
}