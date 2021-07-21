package com.github.houbb.word.checker.support.segment;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.nlp.common.segment.ICommonSegment;
import com.github.houbb.nlp.common.segment.impl.CommonSegments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 默认的混合分词，支持中文和英文。
 *
 * @author binbin.hou
 * @since 0.0.8
 */
public class DefaultSegment implements ICommonSegment {

    @Override
    public List<String> segment(String s) {
        //根据空格分隔
        List<String> strings = CommonSegments.defaults().segment(s);
        if(CollectionUtil.isEmpty(strings)) {
            return Collections.emptyList();
        }

        List<String> results = new ArrayList<>();
        ICommonSegment chineseSegment = InnerCommonSegments.defaultChinese();
        for(String text : strings) {
            // 进行中文分词
            List<String> segments = chineseSegment.segment(text);

            results.addAll(segments);
        }


        return results;
    }

}
