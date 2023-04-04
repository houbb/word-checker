package com.github.houbb.word.checker.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> 中文单词测试类 </p>
 *
 * <pre> Created: 2018-05-02 10:33  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @since 0.0.5
 */
public class ZhWordCheckerHelperTest {

    /**
     * 是否拼写正确
     * @since 0.0.5
     */
    @Test
    public void isCorrectTest() {
        final String right = "正确";
        final String error = "万变不离其中";

        Assert.assertTrue(WordCheckerHelper.isCorrect(right));
        Assert.assertFalse(WordCheckerHelper.isCorrect(error));
    }

    /**
     * 返回最佳匹配结果
     * @since 0.0.5
     */
    @Test
    public void correctTest() {
        final String right = "正确";
        final String error = "万变不离其中";
        
        Assert.assertEquals("正确", WordCheckerHelper.correct(right));
        Assert.assertEquals("万变不离其宗", WordCheckerHelper.correct(error));
    }

    /**
     * 默认纠正匹配列表
     * 1. 默认返回所有
     */
    @Test
    public void correctListTest() {
        final String word = "万变不离其中";

        List<String> stringList = WordCheckerHelper.correctList(word);
        Assert.assertEquals("[万变不离其宗]", stringList.toString());
    }

    /**
     * 纠正列表场景2
     * 1. 指定返回列表的长度
     */
    @Test
    public void correctListWithLimitTest() {
        final String word = "万变不离其中";
        final int limit = 1;
        List<String> stringList = WordCheckerHelper.correctList(word, limit);
        Assert.assertEquals("[万变不离其宗]", stringList.toString());
    }

}
