/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * word-checker All rights reserved.
 */

package com.github.houbb.word.checker.core;

import java.util.List;

/**
 * <p> 单词拼写检查-接口 </p>
 *
 * <pre> Created: 2018/4/28 上午6:41  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public interface WordChecker {

    /**
     * 是否拼写正确
     * @param original 原始内容
     * @return {@code true} 正确拼写, {@code false} 错误拼写
     */
    boolean isCorrect(final String original);

    /**
     * 最佳匹配结果
     * 1. 如果不存在，则返回单词本身
     * @param original 原始内容
     * @return 最佳匹配结果
     */
    String bestMatch(final String original);

    /**
     * 最佳匹配列表
     * 1. list 的大小永远不会大于 limit，大小返回应该是 [0, limit]
     * @param word 单词
     * @param limit 限制，用于指定返回列表的大小
     * @return 最佳匹配列表
     */
    List<String> bestMatchList(final String word, final int limit);

    /**
     * 最佳匹配列表
     * @param word 单词
     * @return 默认大小的最佳匹配列表
     * @see #bestMatchList(String, int) 此处 int 使用默认值
     * @see com.github.houbb.word.checker.constant.WordCheckerContant#DEFAULT_BEST_MATCH_LIMIT
     */
    List<String> bestMatchList(final String word);

}
