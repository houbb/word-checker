package com.github.houbb.word.checker.support.format.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.word.checker.support.format.IWordFormat;

/**
 * 格式工具类
 * @author binbin.hou
 * @since 0.0.3
 */
public final class WordFormats {

    private WordFormats(){}

    /**
     * 默认格式化实现
     * @return 格式化实现
     * @since 0.0.3
     */
    public static IWordFormat defaults() {
        return chains(halfWidth(), lowerCase());
    }

    /**
     * 转换为半角
     * @return 半角
     * @since 0.0.3
     */
    public static IWordFormat halfWidth() {
        return Instances.singleton(HalfWidthWordFormat.class);
    }

    /**
     * 转换为小写
     * @return 小写
     * @since 0.0.3
     */
    public static IWordFormat lowerCase() {
        return Instances.singleton(LowerCaseWordFormat.class);
    }


    /**
     * 无格式化实现
     * @return 格式化实现
     * @since 0.0.3
     */
    public static IWordFormat none() {
        return Instances.singleton(NoneWordFormat.class);
    }

    /**
     * 责任链
     * @param formats 格式化
     * @return 结果
     * @since 0.0.3
     */
    public static IWordFormat chains(final IWordFormat ... formats) {
        if(ArrayUtil.isEmpty(formats)) {
            return none();
        }

        return new AbstractWordFormatInit() {
            @Override
            protected void init(Pipeline<IWordFormat> pipeline) {
                for(IWordFormat format : formats) {
                    pipeline.addLast(format);
                }
            }
        };
    }

}
