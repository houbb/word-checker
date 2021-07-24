package com.github.houbb.word.checker.constant;

/**
 * 单词拼写常量
 * <p> project: word-checker-WordCheckerConst </p>
 * <p> create on 2020/2/6 11:31 </p>
 *
 * @author Administrator
 * @since 0.0.3
 */
public final class WordCheckerConst {

    private WordCheckerConst(){}


    /**
     * 系统内置英文字典路径
     * @since 0.0.3
     */
    public static final String SYSTEM_EN_DICT_PATH = "/data/word_checker_en.txt";

    /**
     * 用户自定义英文字典路径
     * @since 0.0.4
     */
    public static final String DEFINE_EN_DICT_PATH = "/data/define_word_checker_en.txt";

    /**
     * 系统中文字典路径
     * @since 0.05
     */
    public static final String SYSTEM_ZH_DICT_PATH = "/data/word_checker_zh.txt";

    /**
     * 用户自定义中文字典路径
     * @since 0.0.5
     */
    public static final String DEFINE_ZH_DICT_PATH = "/data/define_word_checker_zh.txt";

    /**
     * 默认英文单词出现的次数
     * @since 0.0.4
     */
    public static final Long DEFAULT_EN_WORD_COUNT = 1L;

}
