layui.config({
    base: basePath + '/static/assets/js/' // 自定义layui组件的目录
}).extend({ // 设定组件别名
    //--------1、modules
    bodyTab: 'modules/bodyTab',
    address: 'modules/address',
    treeGrid: 'modules/treeGrid',
    elf: 'modules/elf',
    codelist: 'modules/codelist',

    //--------2、plugins
    echarts: 'plugins/echarts', //echarts 核心包
    linq: 'plugins/linq',
    zTree: 'plugins/zTree'
});