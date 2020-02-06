package com.github.houbb.word.checker.core;

import com.github.houbb.word.checker.support.data.IWordData;
import com.github.houbb.word.checker.support.format.IWordFormat;

/**
 * <p> 单词拼写检查上下文-接口 </p>
 *
 * <pre> Created: 2018/4/28 上午6:41  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @since 0.0.3
 */
public interface IWordCheckerContext {

    /**
     * 英文单词数据
     * @return 单词数据信息
     * @since 0.0.3
     */
    IWordData wordData();

    /**
     * 单词格式化
     * @return 格式化
     * @since 0.0.3
     */
    IWordFormat wordFormat();

}
