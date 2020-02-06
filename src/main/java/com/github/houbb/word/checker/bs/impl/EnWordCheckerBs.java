package com.github.houbb.word.checker.bs.impl;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.word.checker.bs.IWordCheckerBs;
import com.github.houbb.word.checker.core.IWordChecker;
import com.github.houbb.word.checker.core.IWordCheckerContext;
import com.github.houbb.word.checker.core.impl.EnWordChecker;
import com.github.houbb.word.checker.core.impl.WordCheckerContext;
import com.github.houbb.word.checker.support.data.IWordData;
import com.github.houbb.word.checker.support.data.impl.WordDatas;
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
public final class EnWordCheckerBs implements IWordCheckerBs {

    /**
     * 单词拼写实现类
     * @since 0.0.3
     */
    private IWordChecker wordChecker = EnWordChecker.getInstance();

    /**
     * 单词数据信息
     * @since 0.0.3
     */
    private IWordData wordData = WordDatas.mixedEnglish();

    /**
     * 单词格式化
     * @since 0.0.3
     */
    private IWordFormat wordFormat = WordFormats.defaults();

    /**
     * 创建新的实例
     * @return this
     * @since 0.0.3
     */
    public static EnWordCheckerBs newInstance() {
        return new EnWordCheckerBs();
    }

    /**
     * 设置字典信息
     * @param wordData 单词字典
     * @return this
     */
    public EnWordCheckerBs wordData(IWordData wordData) {
        ArgUtil.notNull(wordData, "wordData");

        this.wordData = wordData;
        return this;
    }

    /**
     * 设置字典格式化
     * @param wordFormat 单词格式化
     * @return this
     */
    public EnWordCheckerBs wordFormat(IWordFormat wordFormat) {
        ArgUtil.notNull(wordFormat, "wordFormat");

        this.wordFormat = wordFormat;
        return this;
    }

    @Override
    public boolean isCorrect(String word) {
        return wordChecker.isCorrect(word, buildContext());
    }

    @Override
    public String correct(String word) {
        return wordChecker.correct(word, buildContext());
    }

    @Override
    public List<String> correctList(String word, int limit) {
        return wordChecker.correctList(word, limit, buildContext());
    }

    @Override
    public List<String> correctList(String word) {
        return wordChecker.correctList(word, buildContext());
    }

    /**
     * 构建上下文
     * @since 0.0.3
     * @return 上下文
     */
    private IWordCheckerContext buildContext() {
        WordCheckerContext context = new WordCheckerContext();
        context.wordData(wordData).wordFormat(wordFormat);
        return context;
    }

}
