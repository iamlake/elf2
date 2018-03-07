layui.define([ 'jquery' ], function(exports) {
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
		getBinding : function(formObj, jsonDataExt) {
			var dataBinding = {}, array = formObj.serializeArray();
			$(array).each(function() {
				if (dataBinding[this.name]) {
					dataBinding[this.name] += ';' + this.value;
				} else {
					dataBinding[this.name] = this.value;
				}
			});

			if (jsonDataExt != undefined) {
				var extArray = jsonDataExt.split(';');
				$(extArray).each(function() {
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
		setData : function(formObj, jsonData) {
			$.each(jsonData, function(name, ival) {
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
					$oinput.each(function() {
						var radioObj = $("[name=" + name + "]");
						for (var i = 0; i < radioObj.length; i++) {
							if (radioObj[i].value == ival) {
								radioObj[i].click();
							}
						}
					});
				} else if ($oinput.attr("type") == "textarea") {
					formObj.find("[name=" + name + "]").html(ival);
				} else {
					formObj.find("[name=" + name + "]").val(ival);
				}
			})
		},
		
		/**
		 * 将Json对象转换为树形结构
		 * @param {a} 指定的选择器
		 * @param {idStr} 需要给form赋值的json对象
		 * @param {pidStr} 需要给form赋值的json对象
		 * @param {chindrenStr} 需要给form赋值的json对象
		 * @method transTreeData
		 * @author Liyiming
		 * */
		transTreeData : function(a, idStr, pidStr, childrenStr) {
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

	}
	
	exports('elf', obj);
});