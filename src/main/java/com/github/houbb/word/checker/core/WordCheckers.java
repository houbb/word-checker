package com.github.houbb.word.checker.core;

import com.github.houbb.word.checker.core.impl.EnWordChecker;

import java.util.List;

/**
 * <p> 单词拼写检查工具类 </p>
 *
 * 1. 提供更加优雅灵活的单词校验工具类
 * 2. 使用 fluent 语法代码 util 的死板，和 new 的繁琐。
 * <pre> Created: 2018/4/28 上午6:41  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @version 0.0.2
 * @since 0.0.2
 */
public class WordCheckers {

    private WordCheckers(){}

    /**
     * 单词
     */
    private String word;

    /**
     * 忽略大小写
     */
    private boolean ignoreCase;

    /**
     * 单词检查的实现类
     */
    private WordChecker wordChecker = EnWordChecker.getInstance();

    /**
     * 指定英文单词，并且创建实例
     * @param word 单词
     * @return 实例
     */
    public static WordCheckers word(final String word) {
        WordCheckers instance = new WordCheckers();
        instance.setWordAndInitChecker(word);
        return instance;
    }

    /**
     * 指定单词实现类
     * @param wordChecker 单词实现类
     * @return this
     */
    public WordCheckers wordChecker(final WordChecker wordChecker) {
        this.wordChecker = wordChecker;
        return this;
    }

    /**
     * 忽略大小写
     * Fixed: https://github.com/houbb/word-checker/issues/1
     * @param ignoreCase 忽略大小写
     * @return 本身
     */
    public WordCheckers ignoreCase(final boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        if(ignoreCase) {
            // 忽略大小写，则单词统一转化为小写。
            this.word = word.toLowerCase();
        }
        return this;
    }

    /**
     * 检验当前单词是否正确
     * @return 是否
     */
    public boolean isCorrect() {
        return this.wordChecker.isCorrect(word);
    }

    /**
     * 返回最佳纠正结果
     * @return 最佳纠正结果
     */
    public String correct() {
        return wordChecker.correct(word);
    }

    /**
     * 最佳纠正列表
     * 1. list 的大小永远不会大于 limit，大小返回应该是 [0, limit]
     * @param limit 限制，用于指定返回列表的大小
     * @return 最佳匹配列表
     */
    public List<String> correctList(final int limit) {
        return wordChecker.correctList(word, limit);
    }

    /**
     * 所有匹配纠正列表
     * @return 所有匹配纠正列表
     */
    public List<String> correctList() {
        return wordChecker.correctList(word);
    }

    /**
     * 设置单词并且初始化检查器
     * @param word 单词
     */
    private void setWordAndInitChecker(final String word) {
        wordChecker = EnWordChecker.getInstance();
        this.word = word;
    }

}
