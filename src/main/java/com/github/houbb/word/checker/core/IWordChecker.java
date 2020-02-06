package com.github.houbb.word.checker.core;

import java.util.List;

/**
 * <p> 单词拼写检查-接口 </p>
 *
 * <pre> Created: 2018/4/28 上午6:41  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @since 0.0.3
 */
public interface IWordChecker {

    /**
     * 是否拼写正确
     * @param word 原始内容
     * @param context 上下文
     * @return {@code true} 正确拼写, {@code false} 错误拼写
     * @since 0.0.3
     */
    boolean isCorrect(final String word,
                      final IWordCheckerContext context);

    /**
     * 最佳纠正结果
     * 1. 如果不存在，则返回单词本身
     * @param word 原始内容
     * @param context 上下文
     * @return 最佳匹配结果
     * @since 0.0.3
     */
    String correct(final String word,
                   final IWordCheckerContext context);

    /**
     * 最佳纠正列表
     * 1. list 的大小永远不会大于 limit，大小返回应该是 [0, limit]
     * @param word 单词
     * @param limit 限制，用于指定返回列表的大小
     * @param context 上下文
     * @return 最佳匹配列表
     * @since 0.0.3
     */
    List<String> correctList(final String word, final int limit,
                             final IWordCheckerContext context);

    /**
     * 所有匹配纠正列表
     * @param word 单词
     * @param context 上下文
     * @return 所有匹配纠正列表
     * @since 0.0.3
     */
    List<String> correctList(final String word,
                             final IWordCheckerContext context);

}
