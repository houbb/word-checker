package com.github.houbb.word.checker.core;

import com.github.houbb.word.checker.constant.WordCheckerContant;
import com.github.houbb.word.checker.core.impl.EnWordChecker;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-02 10:33  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @version 0.0.1
 * @since 0.0.1
 */
public class EnWordCheckerTest {

    /**
     * 是否拼写正确
     */
    @Test
    public void isCorrectTest() {
        final String hello = "hello";
        final String speling = "speling";
        Assert.assertTrue(EnWordChecker.getInstance().isCorrect(hello));
        Assert.assertFalse(EnWordChecker.getInstance().isCorrect(speling));
    }

    /**
     * 返回最佳匹配结果
     */
    @Test
    public void correctTest() {
        final String hello = "hello";
        final String speling = "speling";
        Assert.assertEquals("hello", EnWordChecker.getInstance().correct(hello));
        Assert.assertEquals("spelling", EnWordChecker.getInstance().correct(speling));
    }

    /**
     * 默认纠正匹配列表
     * 1. 默认结果为5个匹配
     */
    @Test
    public void correctListTest() {
        final String word = "goo";
        List<String> stringList = EnWordChecker.getInstance().correctList(word);
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
        List<String> stringList = EnWordChecker.getInstance().correctList(word, limit);
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
        List<String> stringList = EnWordChecker.getInstance().correctList(word, limit);
        Assert.assertEquals(1, stringList.size());
    }

}
