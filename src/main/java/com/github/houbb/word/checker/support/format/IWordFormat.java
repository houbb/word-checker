package com.github.houbb.word.checker.support.format;

/**
 * 单词格式化接口
 *
 * <p> project: word-checker-IWordFormat </p>
 * <p> create on 2020/2/6 9:50 </p>
 *
 * @author Administrator
 * @since 0.0.3
 */
public interface IWordFormat {

    /**
     * 格式化
     * @param ch 原始字符
     * @return 格式化结果
     * @since 0.0.3
     */
    char format(final char ch);

}
