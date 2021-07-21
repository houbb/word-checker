package com.github.houbb.word.checker.bs.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.nlp.common.segment.ICommonSegment;
import com.github.houbb.word.checker.bs.IWordCheckerBs;
import com.github.houbb.word.checker.core.IWordChecker;
import com.github.houbb.word.checker.core.IWordCheckerContext;
import com.github.houbb.word.checker.core.impl.EnWordChecker;
import com.github.houbb.word.checker.core.impl.WordCheckerContext;
import com.github.houbb.word.checker.core.impl.ZhWordChecker;
import com.github.houbb.word.checker.support.data.IWordData;
import com.github.houbb.word.checker.support.data.chinese.ChineseWordDatas;
import com.github.houbb.word.checker.support.data.english.EnglishWordDatas;
import com.github.houbb.word.checker.support.format.IWordFormat;
import com.github.houbb.word.checker.support.format.impl.WordFormats;
import com.github.houbb.word.checker.support.segment.InnerCommonSegments;

import java.util.*;

/**
 * 拼写引导类
 * <p> project: word-checker-EnWordCheckerBs </p>
 * <p> create on 2020/2/6 9:34 </p>
 *
 * @author Administrator
 * @since 0.0.8
 */
public final class WordCheckerBs implements IWordCheckerBs {

    private WordCheckerBs(){}

    /**
     * 单词拼写实现类
     * @since 0.0.3
     */
    private ICommonSegment commonSegment = InnerCommonSegments.defaults();

    /**
     * 英文单词数据信息
     * @since 0.0.8
     */
    private IWordData enWordData = EnglishWordDatas.mixed();

    /**
     * 中文单词数据信息
     * @since 0.0.8
     */
    private IWordData zhWordData = ChineseWordDatas.mixed();

    /**
     * 单词格式化
     * @since 0.0.8
     */
    private IWordFormat wordFormat = WordFormats.defaults();

    /**
     * 单词拼写实现类
     * @since 0.0.3
     */
    private IWordChecker enWordChecker = EnWordChecker.getInstance();

    /**
     * 单词拼写实现类
     * @since 0.0.5
     */
    private IWordChecker zhWordChecker = Instances.singleton(ZhWordChecker.class);

    /**
     * 创建新的实例
     * @return this
     * @since 0.0.3
     */
    public static WordCheckerBs newInstance() {
        return new WordCheckerBs();
    }

    public WordCheckerBs commonSegment(ICommonSegment commonSegment) {
        this.commonSegment = commonSegment;
        return this;
    }

    public WordCheckerBs enWordData(IWordData enWordData) {
        this.enWordData = enWordData;
        return this;
    }

    public WordCheckerBs zhWordData(IWordData zhWordData) {
        this.zhWordData = zhWordData;
        return this;
    }

    public WordCheckerBs wordFormat(IWordFormat wordFormat) {
        this.wordFormat = wordFormat;
        return this;
    }

    public WordCheckerBs enWordChecker(IWordChecker enWordChecker) {
        this.enWordChecker = enWordChecker;
        return this;
    }

    public WordCheckerBs zhWordChecker(IWordChecker zhWordChecker) {
        this.zhWordChecker = zhWordChecker;
        return this;
    }

    @Override
    public boolean isCorrect(String text) {
        if(StringUtil.isEnglish(text)) {
            return true;
        }

        // 第一步执行分词
        List<String> segments = commonSegment.segment(text);

        final IWordCheckerContext zhContext = buildChineseContext();
        final IWordCheckerContext enContext = buildEnglishContext();

        // 全部为真，才认为是正确。
        for(String segment : segments) {
            // 如果是英文
            if(StringUtil.isEnglish(segment)) {
                // 执行英文的判断
                if(!enWordChecker.isCorrect(segment, enContext)) {
                    return false;
                }
            } else if(StringUtil.isChinese(segment)) {
                // 如果是中文
                if(!zhWordChecker.isCorrect(segment, zhContext)) {
                    return false;
                }
            }
            // 其他忽略
        }

        return true;
    }

    @Override
    public String correct(String text) {
        if(StringUtil.isEnglish(text)) {
            return text;
        }

        StringBuilder stringBuilder = new StringBuilder();

        final IWordCheckerContext zhContext = buildChineseContext();
        final IWordCheckerContext enContext = buildEnglishContext();

        // 第一步执行分词
        List<String> segments = commonSegment.segment(text);
        // 全部为真，才认为是正确。
        for(String segment : segments) {
            // 如果是英文
            if(StringUtil.isEnglish(segment)) {
                String correct = enWordChecker.correct(segment, enContext);
                stringBuilder.append(correct);
            } else if(StringUtil.isChinese(segment)) {
                String correct = zhWordChecker.correct(segment, zhContext);
                stringBuilder.append(correct);
            } else {
                // 其他忽略
                stringBuilder.append(segment);
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public List<String> correctList(String text, int limit) {
        throw new UnsupportedOperationException("长文本模式不支持，请使用 correctMap!");
    }

    @Override
    public List<String> correctList(String text) {
        throw new UnsupportedOperationException("长文本模式不支持，请使用 correctMap!");
    }

    /**
     * 对应的 map 信息
     * @param text 文本
     * @param limit 限制
     * @return 结果
     * @since 0.0.8
     */
    public Map<String, List<String>> correctMap(String text, int limit) {
        if(StringUtil.isEmpty(text)) {
            return Collections.emptyMap();
        }

        final IWordCheckerContext zhContext = buildChineseContext();
        final IWordCheckerContext enContext = buildEnglishContext();

        // 第一步执行分词
        List<String> segments = commonSegment.segment(text);
        Map<String, List<String>> maps = new HashMap<>();
        for(String segment : segments) {
            List<String> list = new ArrayList<>();
            // 如果是英文
            if(StringUtil.isEnglish(segment)) {
                list = enWordChecker.correctList(segment, limit, enContext);
            } else if(StringUtil.isChinese(segment)) {
                list = zhWordChecker.correctList(segment, limit, zhContext);
            } else {
                list.add(segment);
            }

            maps.put(segment, list);
        }

        return maps;
    }

    /**
     * 对应的 map 信息
     * @param text 文本
     * @return 结果
     * @since 0.0.8
     */
    public Map<String, List<String>> correctMap(String text) {
        return correctMap(text, Integer.MAX_VALUE);
    }

    /**
     * 构建英文上下文
     * @since 0.0.3
     * @return 上下文
     */
    private IWordCheckerContext buildEnglishContext() {
        WordCheckerContext context = new WordCheckerContext();
        context.wordData(enWordData).wordFormat(wordFormat);
        return context;
    }

    /**
     * 构建中文上下文
     * @since 0.0.3
     * @return 上下文
     */
    private IWordCheckerContext buildChineseContext() {
        WordCheckerContext context = new WordCheckerContext();
        context.wordData(zhWordData).wordFormat(wordFormat);
        return context;
    }

}
