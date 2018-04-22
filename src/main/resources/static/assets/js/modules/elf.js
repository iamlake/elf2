layui.define(['jquery', 'linq'], function (exports) {
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
         * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
         * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
         * Examples:
         * dateFormat("2016-10-04 8:9:4.423","yyyy-MM-dd hh:mm:ss.S")        ==> 2016-10-04 08:09:04.423
         * dateFormat("1507353913000","yyyy-M-d h:m:s.S")                    ==> 2017-10-7 13:25:13.0
         * @param {datetime} 指定的选择器
         * @param {pattern} 需要给form赋值的json对象
         * @method dateFormat
         * @author Liyiming
         * */
        dateFormat: function (datetime, pattern) {
            console.log(parseInt(datetime));
            if (parseInt(datetime) == datetime) {
                if (datetime.length == 10) {
                    datetime = parseInt(datetime) * 1000;
                } else if (datetime.length == 13) {
                    datetime = parseInt(datetime);
                }
            }
            datetime = new Date(datetime);
            var o = {
                "M+": datetime.getMonth() + 1, // 月份
                "d+": datetime.getDate(), // 日
                "h+": datetime.getHours(), // 小时
                "m+": datetime.getMinutes(), // 分
                "s+": datetime.getSeconds(), // 秒
                "q+": Math.floor((datetime.getMonth() + 3) / 3), // 季度
                "S": datetime.getMilliseconds()
                // 毫秒
            };
            if (/(y+)/.test(pattern))
                pattern = pattern.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(pattern))
                    pattern = pattern.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return pattern;
        },
        /**
         * @Description: 判断是否是合法URL
         * @Param: {url} url参数
         * @method checkUrl
         * @Author: Liyiming
         * @Date: 2018/4/19
         */
        checkUrl: function CheckUrl(url) {
            var reg = /^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
            if (!reg.test(url)) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    exports('elf', obj);
});