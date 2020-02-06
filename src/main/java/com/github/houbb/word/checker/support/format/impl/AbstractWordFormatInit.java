package com.github.houbb.word.checker.support.format.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.word.checker.support.format.IWordFormat;

import java.util.List;

/**
 * 全角半角格式化处理
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public abstract class AbstractWordFormatInit implements IWordFormat {

    @Override
    public char format(char ch) {
        Pipeline<IWordFormat> pipeline = new DefaultPipeline<>();
        this.init(pipeline);

        List<IWordFormat> formatList = pipeline.list();

        char result = ch;
        for(IWordFormat format : formatList) {
            result = format.format(result);
        }

        return result;
    }

    /**
     * 初始化列表
     *
     * @param pipeline     当前列表泳道
     * @since 0.0.3
     */
    protected abstract void init(final Pipeline<IWordFormat> pipeline);

}
