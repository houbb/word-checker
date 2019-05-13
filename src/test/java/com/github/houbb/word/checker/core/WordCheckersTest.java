package com.github.houbb.word.checker.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> 单词检查引导测试类 </p>
 *
 * <pre> Created: 2018-05-13 10:33  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @version 0.0.2
 * @since 0.0.2
 */
public class WordCheckersTest {

    /**
     * 是否拼写正确
     */
    @Test
    public void isCorrectTest() {
        final String hello = "hello";
        final String speling = "speling";

        Assert.assertTrue(WordCheckers.word(hello).isCorrect());
        Assert.assertFalse(WordCheckers.word(speling).isCorrect());
    }

    /**
     * 是否拼写正确
     * 1. 原来的单词纠正仅仅支持小写字母的检查
     * 2. 支持指定忽略大小写。
     */
    @Test
    public void isCorrectIgnoreCaseTest() {
        final String hello = "Hello";

        Assert.assertFalse(WordCheckers.word(hello).isCorrect());
        Assert.assertTrue(WordCheckers.word(hello).ignoreCase(true).isCorrect());
    }

    /**
     * 返回最佳匹配结果
     */
    @Test
    public void correctTest() {
        final String hello = "hello";
        final String speling = "speling";
        Assert.assertEquals("hello", WordCheckers.word(hello).correct());
        Assert.assertEquals("spelling", WordCheckers.word(speling).correct());
    }

    /**
     * 默认纠正匹配列表
     * 1. 默认返回所有
     */
    @Test
    public void correctListTest() {
        final String word = "goo";
        List<String> stringList = WordCheckers.word(word).correctList();
        Assert.assertTrue(stringList.size() > 0);
    }

    /**
     * 纠正列表场景2
     * 1. 指定返回列表的长度
     */
    @Test
    public void correctListWithLimitTest() {
        final String word = "goo";
        final int limit = 2;
        List<String> stringList = WordCheckers.word(word).correctList(limit);
        Assert.assertEquals(limit, stringList.size());
    }

    /**
     * 纠正列表场景3
     * 1. 指定返回列表的长度
     * 2. 如果匹配少于limit，则结果会少于 limit 的指定数量。
     */
    @Test
    public void correctListWithLimit2Test() {
        final String word = "speling";
        final int limit = 2;
        List<String> stringList = WordCheckers.word(word).correctList(limit);
        Assert.assertEquals(1, stringList.size());
    }

}
