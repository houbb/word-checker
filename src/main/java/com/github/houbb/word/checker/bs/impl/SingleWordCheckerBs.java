package com.github.houbb.word.checker.bs.impl;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.word.checker.bs.IWordCheckerBs;
import com.github.houbb.word.checker.constant.WordCheckerConst;
import com.github.houbb.word.checker.core.IWordChecker;
import com.github.houbb.word.checker.core.IWordCheckerContext;
import com.github.houbb.word.checker.core.impl.EnWordChecker;
import com.github.houbb.word.checker.core.impl.WordCheckerContext;
import com.github.houbb.word.checker.support.data.IWordData;
import com.github.houbb.word.checker.support.data.english.EnglishWordDatas;
import com.github.houbb.word.checker.support.format.IWordFormat;
import com.github.houbb.word.checker.support.format.impl.WordFormats;

import java.util.List;

/**
 * 英文拼写引导类
 * <p> project: word-checker-EnWordCheckerBs </p>
 * <p> create on 2020/2/6 9:34 </p>
 *
 * @author Administrator
 * @since 0.0.3
 */
public final class SingleWordCheckerBs implements IWordCheckerBs {

    /**
     * 单词拼写实现类
     * @since 0.0.3
     */
    private IWordChecker wordChecker = EnWordChecker.getInstance();

    /**
     * 单词数据信息
     * @since 0.0.3
     */
    private IWordData wordData = EnglishWordDatas.mixed();

    /**
     * 单词格式化
     * @since 0.0.3
     */
    private IWordFormat wordFormat = WordFormats.defaults();

    /**
     * 最大的编辑距离
     * @since 1.1.0
     */
    private int maxEditDistance = WordCheckerConst.DEFAULT_MAX_EDIT_DISTANCE;

    /**
     * 上下文
     * @since 1.1.0
     */
    private IWordCheckerContext wordCheckerContext = null;

    /**
     * 创建新的实例
     * @return this
     * @since 0.0.3
     */
    public static SingleWordCheckerBs newInstance() {
        return new SingleWordCheckerBs();
    }

    /**
     * 设置字典信息
     * @param wordData 单词字典
     * @return this
     */
    public SingleWordCheckerBs wordData(IWordData wordData) {
        ArgUtil.notNull(wordData, "wordData");

        this.wordData = wordData;
        return this;
    }

    /**
     * 设置字典格式化
     * @param wordFormat 单词格式化
     * @return this
     */
    public SingleWordCheckerBs wordFormat(IWordFormat wordFormat) {
        ArgUtil.notNull(wordFormat, "wordFormat");

        this.wordFormat = wordFormat;
        return this;
    }

    /**
     * 最大编辑距离
     * @param maxEditDistance 最大编辑距离
     * @return this
     * @since 1.1.0
     */
    public SingleWordCheckerBs maxEditDistance(int maxEditDistance) {
        ArgUtil.gt("maxEditDistance", maxEditDistance, 0);

        this.maxEditDistance = maxEditDistance;
        return this;
    }

    /**
     * 设置拼写纠正算法
     * @param wordChecker 单词拼写
     * @return 结果
     * @since 1.1.0
     */
    public SingleWordCheckerBs wordChecker(IWordChecker wordChecker) {
        ArgUtil.notNull(wordChecker, "wordChecker");

        this.wordChecker = wordChecker;
        return this;
    }

    /**
     * 初始化上下文
     * @return 上下文
     * @since 1.1.0
     */
    public SingleWordCheckerBs init() {
        this.initContext();

        return this;
    }

    @Override
    public boolean isCorrect(String word) {
        return wordChecker.isCorrect(word, this.wordCheckerContext);
    }

    @Override
    public String correct(String word) {
        return wordChecker.correct(word, this.wordCheckerContext);
    }

    @Override
    public List<String> correctList(String word, int limit) {
        return wordChecker.correctList(word, limit, this.wordCheckerContext);
    }

    @Override
    public List<String> correctList(String word) {
        return wordChecker.correctList(word, this.wordCheckerContext);
    }

    /**
     * 构建上下文
     * @since 1.1.0
     * @return 上下文
     */
    private void initContext() {
        this.wordCheckerContext = WordCheckerContext.newInstance()
                .maxEditDistance(maxEditDistance)
                .wordData(wordData)
                .wordFormat(wordFormat);
    }

}
