package com.github.houbb.word.checker.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> 英文单词测试类 </p>
 *
 * <pre> Created: 2018-05-02 10:33  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @since 0.0.3
 */
public class EnWordCheckersTest {

    /**
     * 是否拼写正确
     */
    @Test
    public void isCorrectTest() {
        final String hello = "hello";
        final String speling = "speling";
        Assert.assertTrue(EnWordCheckers.isCorrect(hello));
        Assert.assertFalse(EnWordCheckers.isCorrect(speling));
    }

    /**
     * 返回最佳匹配结果
     */
    @Test
    public void correctTest() {
        final String hello = "hello";
        final String speling = "speling";
        Assert.assertEquals("hello", EnWordCheckers.correct(hello));
        Assert.assertEquals("spelling", EnWordCheckers.correct(speling));
    }

    /**
     * 默认纠正匹配列表
     * 1. 默认返回所有
     */
    @Test
    public void correctListTest() {
        final String word = "goox";
        List<String> stringList = EnWordCheckers.correctList(word);
        Assert.assertEquals("[good, goo, goon, goof, gook, goop, goos, gox, goog, gool, goor]", stringList.toString());
    }

    /**
     * 纠正列表场景2
     * 1. 指定返回列表的长度
     */
    @Test
    public void correctListWithLimitTest() {
        final String word = "goox";
        final int limit = 2;
        List<String> stringList = EnWordCheckers.correctList(word, limit);
        Assert.assertEquals("[good, goo]", stringList.toString());
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
        List<String> stringList = EnWordCheckers.correctList(word, limit);
        Assert.assertEquals(2, stringList.size());
    }

    /**
     * 格式化大小写测试
     * @since 0.0.3
     */
    @Test
    public void isCorrectFormatCaseTest() {
        final String word = "stRing";

        Assert.assertTrue(EnWordCheckers.isCorrect(word));
    }

    /**
     * 格式化宽度测试
     * @since 0.0.3
     */
    @Test
    public void isCorrectFormatWidthTest() {
        final String word = "stｒing";

        Assert.assertTrue(EnWordCheckers.isCorrect(word));
    }

    /**
     * 自定义词库
     * @since 0.0.4
     */
    @Test
    public void defineWordDictTest() {
        final String word = "my-long-long-define-word";
        final String word2 = "my-long-long-define-word-two";
        Assert.assertTrue(EnWordCheckers.isCorrect(word));
        Assert.assertTrue(EnWordCheckers.isCorrect(word2));
    }

}
