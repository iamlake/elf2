layui.define(['jquery', 'linq'], function (exports) {
    var $ = layui.jquery, linq = layui.linq;
    var obj = {
        /**
         * @Description: 获取codelist
         * @Param: {re} 是否刷新
         * @return: JsonObject
         * @Author: Liyiming
         * @Date: 2018/4/19
         */
        getAll: function (re) {
            var codelist = window.sessionStorage.getItem("cache_codelist");
            if (codelist == null || codelist == '[]' || re) {
                $.ajax({
                    url: basePath + '/codelist',
                    type: 'GET',
                    async: false,
                    success: function (result) {
                        if (result.data) {
                            window.sessionStorage.setItem("cache_codelist", JSON.stringify(result.data));
                        }
                        codelist =  result.data;
                    }
                });
            } else {
                codelist =JSON.parse(codelist);
            }
            return codelist;
        },
        /**
         * @Description: 通过CodeType获取codelist集合
         * @Param: {type} CodeType
         * @Param: {re} 是否刷新
         * @return: codelist
         * @Author: Liyiming
         * @Date: 2018/4/20
         */
        get: function (type, re) {
            var codelistCollection = this.getAll(re);
            var codelist = linq.from(codelistCollection).where(function (x) {
                return x.codeType == type;
            }).orderBy(function (x) {
                return x.codeOrder;
            }).toArray();
            return codelist;
        },
        /**
         * @Description: 通过CodeType和CodeValue获取CodeValueName
         * @Param: {type} CodeType
         * @Param: {value} CodeValue
         * @return: CodeValueName
         * @Author: Liyiming
         * @Date: 2018/4/21
         */
        getValueName: function (type, value) {
            var codelistCollection = this.getAll(false);
            var codelist = linq.from(codelistCollection).where(function (x) {
                return x.codeType == type && x.codeValue == value;
            }).toArray();
            return codelist[0].codeValueName;
        },
        /**
         * @Description: 绑定下拉列表数据源
         * @Param: {o} select对象
         * @Param: {n} codeTypeName
         * @Param: {b} 请选择
         * @return: null
         * @Author: Liyiming
         * @Date: 2018/4/21
         */
        bindSelect: function (o, n, b) {
            var html = b ? '' : '<option value="">请选择</option>';
            var d = this.get(n);
            if (d) {
                for (var i = 0; i < d.length; i++) {
                    html += '<option value="' + d[i].codeValue + '">' + d[i].codeValueName + '</option>';
                }
                o.html(html).removeAttr("disabled");
            }
        },
        /**
         * @Description: 绑定单选按钮数据源
         * @Param: {o} radio对象
         * @Param: {n} codeTypeName
         * @Param: {f} dataField
         * @return: null
         * @Author: Liyiming
         * @Date: 2018/4/21
         */
        bindRadio: function (o, n, f) {
            var html = '';
            var d = this.get(n);
            if (d) {
                for (var i = 0; i < d.length; i++) {
                    html += '<input type="radio" name="' + f + '" value="' + d[i].codeValue + '" title="' + d[i].codeValueName + '">';
                }
                o.html(html);
            }
        }
    }

    exports('codelist', obj);
});