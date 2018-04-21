layui.define(['jquery','linq'], function (exports) {
    var $ = layui.jquery, linq = layui.linq;
    var obj = {
        /**
         * 获得form中控件的值返回JSON对象
         * 相同的checkbox用分号拼接起来
         * @param {formObj} 指定的选择器
         * @param {jsonExt} 需要拼接在后面的Json对象
         * @method getBinding
         * @author Liyiming
         * */
        getBinding: function (formObj, jsonDataExt) {
            var dataBinding = {}, array = formObj.serializeArray();
            $(array).each(function () {
                if (dataBinding[this.name]) {
                    dataBinding[this.name] += ';' + this.value;
                } else {
                    dataBinding[this.name] = this.value;
                }
            });

            if (jsonDataExt != undefined) {
                var extArray = jsonDataExt.split(';');
                $(extArray).each(function () {
                    var extSplitArray = this.split(':');
                    dataBinding[extSplitArray[0]] = extSplitArray[1];
                });
            }
            return dataBinding;
        },

        /**
         * 将Json对象赋值给form
         * @param {formObj} 指定的选择器
         * @param {jsonValue} 需要给form赋值的json对象
         * @method setData
         * @author Liyiming
         * */
        setData: function (formObj, jsonData) {
            $.each(jsonData, function (name, ival) {
                var $oinput = formObj.find("input[name=" + name + "]");
                if ($oinput.attr("type") == "checkbox") {
                    if (ival !== null) {
                        var checkboxObj = $("[name=" + name + "]");
                        var checkArray = ival.split(";");
                        for (var i = 0; i < checkboxObj.length; i++) {
                            for (var j = 0; j < checkArray.length; j++) {
                                if (checkboxObj[i].value == checkArray[j]) {
                                    checkboxObj[i].click();
                                }
                            }
                        }
                    }
                } else if ($oinput.attr("type") == "radio") {
                    for (var i = 0; i < $oinput.length; i++) {
                        if ($oinput[i].value == ival) {
                            $oinput[i].click();
                        }
                    }
                } else if ($oinput.attr("type") == "textarea") {
                    formObj.find("[name=" + name + "]").html(ival);
                } else {
                    formObj.find("[name=" + name + "]").val(ival);
                }
            })
        },
        /**
         * @Description: 绑定下拉列表数据源
         * @Param: {o} select对象
         * @Param: {d} 数据集
         * @Param: {c} value
         * @Param: {n} name
         * @return: null
         * @Author: Liyiming
         * @Date: 2018/4/15
         */
        bindSelect: function (o, d, c, n, b) {
            var html = b ? '' : '<option value="">请选择</option>';
            if (d) {
                for (var i = 0; i < d.length; i++) {
                    html += '<option value="' + d[i][c] + '">' + d[i][n] + '</option>';
                }
                o.html(html).removeAttr("disabled");
            }
        },

        /**
         * 将Json对象转换为树形结构
         * @param {a} json数据
         * @param {idStr} id的字符串
         * @param {pidStr} 父id的字符串
         * @param {chindrenStr} children的字符串
         * @method transTreeData
         * @author Liyiming
         * */
        transTreeData: function (a, idStr, pidStr, childrenStr) {
            var r = [], hash = {}, id = idStr, pid = pidStr, children = childrenStr, i = 0, j = 0, len = a.length;
            for (; i < len; i++) {
                hash[a[i][id]] = a[i];
            }
            for (; j < len; j++) {
                var aVal = a[j], hashVP = hash[aVal[pid]];
                if (hashVP) {
                    !hashVP[children]
                    && (hashVP[children] = []);
                    hashVP[children].push(aVal);
                } else {
                    r.push(aVal);
                }
            }
            return r;
        },

        /**
         * 获取GET参数
         * @param {name} 参数名
         * @method getRequestParam
         * @author Liyiming
         * */
        getRequestParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null)
                return unescape(r[2]);
            return "";
        },

        /**
         * @Description: 获取codelist
         * @Param: {re} 是否刷新
         * @return: JsonObject
         * @Author: Liyiming
         * @Date: 2018/4/19
         */
        getCodelistCollection: function (re) {
            var codelist = window.sessionStorage.getItem("cache_codelist");
            if (codelist == null || codelist == undefined || re) {
                $.ajax({
                    url: basePath + '/codelist',
                    type: 'GET',
                    async: false,
                    success: function (result) {
                        if (result.data) {
                            window.sessionStorage.setItem("cache_codelist", JSON.stringify(result.data));
                        }
                        return result.data;
                    }
                });
            } else {
                return JSON.parse(codelist);
            }
        },
        /**
         * @Description: 通过CodeType获取codelist集合
         * @Param: {type} CodeType
         * @Param: {re} 是否刷新
         * @return: codelist
         * @Author: Liyiming
         * @Date: 2018/4/20
         */
        getCodelist: function (type, re) {
            var codelistCollection = this.getCodelistCollection(re);
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
        getCodeValueName:function (type, value) {
            var codelistCollection = this.getCodelistCollection(false);
            var codelist = linq.from(codelistCollection).where(function (x) {
                return x.codeType == type && x.codeValue == value;
            }).toArray();
            return codelist[0].codeValueName;
        }
    }

    exports('elf', obj);
});