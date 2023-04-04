package com.github.houbb.word.checker.util;

import com.github.houbb.word.checker.bs.impl.WordCheckerBs;

import java.util.List;
import java.util.Map;

/**
 * 拼写工具类
 *
 * <p> project: word-checker-EnWordCheckers </p>
 * <p> create on 2020/2/6 9:37 </p>
 *
 * @author Administrator
 * @since 0.0.8
 */
public final class WordCheckerHelper {

    private WordCheckerHelper(){}

    /**
     * 单词拼写实现类
     * @since 0.0.8
     */
    private static final WordCheckerBs WORD_CHECK_BS = WordCheckerBs
            .newInstance()
            .init();

    /**
     * 单词拼写是否正确
     * @param word 单词
     * @return 是否正确
     * @since 0.0.8
     */
    public static boolean isCorrect(String word) {
        return WORD_CHECK_BS.isCorrect(word);
    }

    /**
     * 最佳纠正结果
     * @param word 单词
     * @return 纠正结果
     * @since 0.0.8
     */
    public static String correct(String word) {
        return WORD_CHECK_BS.correct(word);
    }

    /**
     * 指定个数纠正列表结果
     * @param word 单词
     * @param limit 限制个数
     * @return 纠正结果
     * @since 0.0.8
     */
    public static Map<String, List<String>> correctMap(String word, int limit) {
        return WORD_CHECK_BS.correctMap(word, limit);
    }

    /**
     * 全部个数纠正列表结果
     * @param word 单词
     * @return 纠正结果
     * @since 0.0.8
     */
    public static Map<String, List<String>> correctMap(String word) {
        return WORD_CHECK_BS.correctMap(word);
    }

    /**
     * 指定个数纠正列表结果
     * @param word 单词
     * @param limit 限制个数
     * @return 纠正结果
     * @since 1.1.0
     */
    public static List<String> correctList(String word, int limit) {
        return WORD_CHECK_BS.correctList(word, limit);
    }

    /**
     * 全部个数纠正列表结果
     * @param word 单词
     * @return 纠正结果
     * @since 0.0.8
     */
    public static List<String> correctList(String word) {
        return WORD_CHECK_BS.correctList(word);
    }

}
