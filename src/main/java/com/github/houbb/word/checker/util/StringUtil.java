package com.github.houbb.word.checker.util;

/**
 * 字符工具类
 * @author binbin.hou
 * @since 0.0.2
 */
public final class StringUtil {

    private StringUtil(){}

    /**
     * 是否为空
     * @param string 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(final String string) {
        if(null == string
            || "".equals(string)) {
            return true;
        }
        return false;
    }

    /**
     * 根据逗号分隔
     * @param string 字符串
     * @return 字符串数组
     */
    public static String[] splitByComma(final String string) {
        return string.split(",");
    }

}
