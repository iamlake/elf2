layui.define(['jquery'], function (exports) {
    var $ = layui.jquery;
    var obj = {
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
    };

    exports('common', obj);
});