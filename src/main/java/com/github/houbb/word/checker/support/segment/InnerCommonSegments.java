package com.github.houbb.word.checker.support.segment;

import com.github.houbb.nlp.common.segment.ICommonSegment;

/**
 * 通用分词
 *
 * @author binbin.hou
 * @since 0.0.8
 */
public final class InnerCommonSegments {

    private InnerCommonSegments() {
    }

    /**
     * 默认的中文分词
     *
     * @return 中文分词
     * @since 0.0.8
     */
    public static ICommonSegment defaultChinese() {
        return new DefaultChineseSegment();
    }

    /**
     * 默认分词策略
     * @return 默认分词策略
     * @since 0.0.8
     */
    public static ICommonSegment defaults() {
        return new DefaultSegment();
    }

}
