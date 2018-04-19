package com.elf.core.common.utils;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br>Title: StringUtil
 * <br>Description: 字符串操作工具类
 * <br>Author:李一鸣(li-yiming@neusoft.com)
 * <br>Date:2013-6-6
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final char SEPARATOR = '_';

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换为字节数组
     *
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     *
     * @param html
     * @return
     */
    public static String replaceMobileHtml(String html) {
        if (html == null) {
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }

    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * <br>
     * Description: 如果为空，则设置值 <br>
     * Author:李一鸣(liyiming.neu@neusoft.com) <br>
     * Date:2016年8月2日
     *
     * @param target
     * @param source
     */
    public static void setValueIfBlank(String target, String source) {
        if (isBlank(source)) {
            target = source;
        }
    }

    /**
     * 如果不为空，则设置值
     *
     * @param target
     * @param source
     */
    public static void setValueIfNotBlank(String target, String source) {
        if (isNotBlank(source)) {
            target = source;
        }
    }

    /**
     * <br>Description: 格式化空串
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年9月27日
     *
     * @param o
     * @return
     */
    public static String trimToEmpty(Object o) {
        if (o != null) {
            return trimToEmpty(o.toString());
        } else {
            return "";
        }
    }

    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     *
     * @param objectString 对象串 例如：row.user.id
     *                     返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = split(objectString, ".");
        for (int i = 0; i < vals.length; i++) {
            val.append("." + vals[i]);
            result.append("!" + (val.substring(1)) + "?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }

    /**
     * <br>Description: 去掉字符串右边的空格
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-5
     *
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }

        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);

    }

    /**
     * <br>Description: 判断字符串是否为正整数
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-14
     *
     * @param str
     * @return
     */
    public static boolean isPositiveInt(String str) {
        boolean bRet;
        String numberPattern = "[0-9]*";
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher isNum = pattern.matcher(str);

        if (isNum.matches()) {
            bRet = true;
        } else {
            bRet = false;
        }
        return bRet;
    }

    /**
     * <br>Description: 判断字符串是否为数字(包括正负小数)
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-6
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        boolean bRet;
        String integerPattern = "^\\d+$|-\\d+$";
        Pattern pnInteger = Pattern.compile(integerPattern);
        String decimalPattern = "\\d+\\.\\d+$|-\\d+\\.\\d+$";
        Pattern pnDecimal = Pattern.compile(decimalPattern);

        Matcher isInteger = pnInteger.matcher(str);
        Matcher isDecimal = pnDecimal.matcher(str);
        if (isInteger.matches()) {
            bRet = true;
        } else if (isDecimal.matches()) {
            bRet = true;
        } else {
            bRet = false;
        }

        return bRet;
    }

    /**
     * <br>Description: 判断字符串是否为日期类型
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-7
     *
     * @param sDate
     * @return
     */
    public static boolean isDateTime(String sDate) {
        String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
        String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        if (sDate != null) {
            Pattern pattern = Pattern.compile(datePattern1);
            Matcher match = pattern.matcher(sDate);
            if (match.matches()) {
                pattern = Pattern.compile(datePattern2);
                match = pattern.matcher(sDate);
                return match.matches();
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * <br>Description: 执行正则表达式进行校验
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-18
     *
     * @param value
     * @param regex
     * @return
     */
    public static boolean regexValidation(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(value);
        return match.matches();
    }

    /**
     * <br>Description: 获取字符串的长度(中文字符长度为2，其他字符为1)
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-7
     *
     * @param value
     * @return
     */
    public static int getLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * <br>Description: 获取文件扩展名
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-19
     *
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * <br>Description: 获取不带扩展名的文件名
     * <br>Author:李一鸣(li-yiming@neusoft.com)
     * <br>Date:2013-6-19
     *
     * @param filename
     * @return
     */
    public static String getFileNameWithoutEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * <br>Description: 获取UUID
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年9月27日
     *
     * @param b
     * @return
     */
    public static String getUUID(boolean b) {
        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
        return b ? uuid.replace("-", "") : uuid;
    }

    /**
     * <br>Description: 获取32位UUID
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年9月27日
     *
     * @return
     */
    public static String getUUID() {
        return StringUtils.getUUID(true);
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.getUUID());
    }
}
