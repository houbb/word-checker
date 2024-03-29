package com.github.houbb.word.checker.core.impl;

import com.github.houbb.word.checker.core.IWordCheckerContext;
import com.github.houbb.word.checker.support.data.IWordData;
import com.github.houbb.word.checker.support.format.IWordFormat;

/**
 * <p> 单词拼写检查上下文 </p>
 *
 * <pre> Created: 2018/4/28 上午6:41  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @since 0.0.3
 */
public class WordCheckerContext implements IWordCheckerContext {

    /**
     * 英文单词数据
     * @since 0.0.3
     */
    private IWordData wordData;

    /**
     * 单词格式化
     * @since 0.0.3
     */
    private IWordFormat wordFormat;

    /**
     * 最大编辑距离
     * @since 1.1.0
     */
    private int maxEditDistance;

    public static WordCheckerContext newInstance() {
        return new WordCheckerContext();
    }

    @Override
    public IWordData wordData() {
        return wordData;
    }

    public WordCheckerContext wordData(IWordData wordData) {
        this.wordData = wordData;
        return this;
    }

    @Override
    public IWordFormat wordFormat() {
        return wordFormat;
    }

    public WordCheckerContext wordFormat(IWordFormat wordFormat) {
        this.wordFormat = wordFormat;
        return this;
    }

    @Override
    public int maxEditDistance() {
        return maxEditDistance;
    }

    public WordCheckerContext maxEditDistance(int maxEditDistance) {
        this.maxEditDistance = maxEditDistance;
        return this;
    }
}
