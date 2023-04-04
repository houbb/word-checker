package com.github.houbb.word.checker.bs.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.nlp.common.segment.ICommonSegment;
import com.github.houbb.word.checker.bs.IWordCheckerBs;
import com.github.houbb.word.checker.constant.WordCheckerConst;
import com.github.houbb.word.checker.core.IWordChecker;
import com.github.houbb.word.checker.core.impl.EnWordChecker;
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
     * 英文单词格式化
     * @since 0.0.8
     */
    private IWordFormat enWordFormat = WordFormats.defaults();

    /**
     * 单词拼写实现类
     * @since 0.0.3
     */
    private final IWordChecker enWordChecker = EnWordChecker.getInstance();

    /**
     * 英文最大的编辑距离
     * @since 1.1.0
     */
    private int enMaxEditDistance = WordCheckerConst.DEFAULT_MAX_EDIT_DISTANCE;

    /**
     * 单词拼写实现类
     * @since 0.0.5
     */
    private final IWordChecker zhWordChecker = Instances.singleton(ZhWordChecker.class);

    /**
     * 中文单词数据信息
     * @since 0.0.8
     */
    private IWordData zhWordData = ChineseWordDatas.mixed();

    /**
     * 中文最大的编辑距离
     * @since 1.1.0
     */
    private int zhMaxEditDistance = WordCheckerConst.DEFAULT_MAX_EDIT_DISTANCE;

    /**
     * 中文单词格式化
     * @since 1.1.0
     */
    private IWordFormat zhWordFormat = WordFormats.defaults();

    /**
     * 英文引导类实现
     * @since 1.1.0
     */
    private SingleWordCheckerBs enWordCheckerBs = null;

    /**
     * 中文引导类实现
     * @since 1.1.0
     */
    private SingleWordCheckerBs zhWordCheckerBs = null;

    /**
     * 创建新的实例
     * @return this
     * @since 0.0.3
     */
    public static WordCheckerBs newInstance() {
        return new WordCheckerBs();
    }

    /**
     * 初始化
     * @since 1.1.0
     */
    public WordCheckerBs init() {
        enWordCheckerBs = SingleWordCheckerBs.newInstance()
                .wordFormat(enWordFormat)
                .maxEditDistance(enMaxEditDistance)
                .wordChecker(enWordChecker)
                .wordData(enWordData)
                .init();


        zhWordCheckerBs = SingleWordCheckerBs.newInstance()
                .wordFormat(zhWordFormat)
                .maxEditDistance(zhMaxEditDistance)
                .wordChecker(zhWordChecker)
                .wordData(zhWordData)
                .init();

        return this;
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

    public WordCheckerBs enWordFormat(IWordFormat enWordFormat) {
        this.enWordFormat = enWordFormat;
        return this;
    }

    public WordCheckerBs enMaxEditDistance(int enMaxEditDistance) {
        this.enMaxEditDistance = enMaxEditDistance;
        return this;
    }

    public WordCheckerBs zhMaxEditDistance(int zhMaxEditDistance) {
        this.zhMaxEditDistance = zhMaxEditDistance;
        return this;
    }

    public WordCheckerBs zhWordFormat(IWordFormat zhWordFormat) {
        this.zhWordFormat = zhWordFormat;
        return this;
    }

    @Override
    public boolean isCorrect(String text) {
        if(StringUtil.isEmpty(text)) {
            return true;
        }

        // 第一步执行分词
        List<String> segments = commonSegment.segment(text);

        // 全部为真，才认为是正确。
        for(String segment : segments) {
            // 如果是英文
            if(StringUtil.isEnglish(segment)) {
                // 执行英文的判断
                if(!enWordCheckerBs.isCorrect(segment)) {
                    return false;
                }
            } else if(StringUtil.isChinese(segment)) {
                // 如果是中文
                if(!zhWordCheckerBs.isCorrect(segment)) {
                    return false;
                }
            }
            // 其他忽略
        }

        return true;
    }

    @Override
    public String correct(String text) {
        if(StringUtil.isEmpty(text)) {
            return text;
        }

        StringBuilder stringBuilder = new StringBuilder();

        // 第一步执行分词
        List<String> segments = commonSegment.segment(text);
        // 全部为真，才认为是正确。
        for(String segment : segments) {
            // 如果是英文
            if(StringUtil.isEnglish(segment)) {
                String correct = enWordCheckerBs.correct(segment);
                stringBuilder.append(correct);
            } else if(StringUtil.isChinese(segment)) {
                String correct = zhWordCheckerBs.correct(segment);
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
        Map<String, List<String>> map = correctMap(text, limit);

        return getList(map);
    }

    @Override
    public List<String> correctList(String text) {
        Map<String, List<String>> map = correctMap(text);

        return getList(map);
    }

    private List<String> getList(Map<String, List<String>> map) {
        if(MapUtil.isEmpty(map)) {
            return Collections.emptyList();
        }

        for(Map.Entry<String, List<String>> entry: map.entrySet()) {
            return entry.getValue();
        }
        return Collections.emptyList();
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

        // 第一步执行分词
        List<String> segments = commonSegment.segment(text);
        Map<String, List<String>> maps = new HashMap<>();
        for(String segment : segments) {
            List<String> list = new ArrayList<>();
            // 如果是英文
            if(StringUtil.isEnglish(segment)) {
                list = enWordCheckerBs.correctList(segment, limit);
            } else if(StringUtil.isChinese(segment)) {
                list = zhWordCheckerBs.correctList(segment, limit);
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

}
