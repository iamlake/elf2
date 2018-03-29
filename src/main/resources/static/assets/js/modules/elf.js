layui.define(['jquery'], function (exports) {
    var $ = layui.jquery;
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
        }
        /**
         var jsonData = eval('[
         {"id":"4","pid":"1","name":"大家电"},
         {"id":"5","pid":"1","name":"生活电器"},
         {"id":"1","pid":"0","name":"家用电器"},
         {"id":"2","pid":"0","name":"服饰"},
         {"id":"3","pid":"0","name":"化妆"},
         {"id":"7","pid":"4","name":"空调"},
         {"id":"8","pid":"4","name":"冰箱"},
         {"id":"9","pid":"4","name":"洗衣机"},
         {"id":"10","pid":"4","name":"热水器"},
         {"id":"11","pid":"3","name":"面部护理"},
         {"id":"12","pid":"3","name":"口腔护理"},
         {"id":"13","pid":"2","name":"男装"},
         {"id":"14","pid":"2","name":"女装"},
         {"id":"15","pid":"7","name":"海尔空调"},
         {"id":"16","pid":"7","name":"美的空调"},
         {"id":"19","pid":"5","name":"加湿器"},
         {"id":"20","pid":"5","name":"电熨斗"}
         ]');

         var jsonDataTree = transData(jsonData, 'id', 'pid', 'chindren');
         console.log(jsonDataTree);
         //结果如下：
         [
         {"id":"1","pid":"0","name":"家用电器", "chindren":[
                 {"id":"4","pid":"1","name":"大家电", "chindren":[
                         {"id":"7","pid":"4","name":"空调", "chindren":[
                                 {"id":"15","pid":"7","name":"海尔空调"},
                                 {"id":"16","pid":"7","name":"美的空调"}
                             ]},
                         {"id":"8","pid":"4","name":"冰箱"},
                         {"id":"9","pid":"4","name":"洗衣机"},
                         {"id":"10","pid":"4","name":"热水器"}
                     ]},
                 {"id":"5","pid":"1","name":"生活电器","chindren":[
                         {"id":"19","pid":"5","name":"加湿器"},
                         {"id":"20","pid":"5","name":"电熨斗"}
                     ]}
             ]},
         {"id":"2","pid":"0","name":"服饰","chindren":[
                 {"id":"13","pid":"2","name":"男装"},
                 {"id":"14","pid":"2","name":"女装"}
             ]},
         {"id":"3","pid":"0","name":"化妆","chindren":[
                 {"id":"11","pid":"3","name":"面部护理"},
                 {"id":"12","pid":"3","name":"口腔护理"}
             ]}
         ]
         */
    }

    exports('elf', obj);
});