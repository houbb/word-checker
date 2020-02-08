package com.github.houbb.word.checker.support.segment;

import com.github.houbb.nlp.common.segment.ICommonSegment;
import com.github.houbb.nlp.common.segment.impl.CommonSegments;
import com.github.houbb.word.checker.support.segment.trie.DefaultChineseTrieTreeMap;

import java.util.List;

/**
 * <p> project: word-checker-DefaultChineseSegment </p>
 * <p> create on 2020/2/8 17:39 </p>
 *
 * @author Administrator
 * @since 0.0.5
 */
public class DefaultChineseSegment implements ICommonSegment {

    /**
     * 默认分词实现
     * @since 0.0.5
     */
    private static final ICommonSegment COMMON_SEGMENT = CommonSegments.fastForward(new DefaultChineseTrieTreeMap());

    @Override
    public List<String> segment(String string) {
        return COMMON_SEGMENT.segment(string);
    }

}
