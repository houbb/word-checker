package com.github.houbb.word.checker.util;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * <p> 纠正测试类 </p>
 *
 * <pre> Created: 2018-05-02 10:33  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @since 0.0.8
 */
public class WordCheckerHelperTest {

    @Test
    public void isCorrectSingleTest() {
        final String speling = "speling";

        Assert.assertFalse(WordCheckerHelper.isCorrect(speling));
    }

    /**
     * 是否拼写正确
     */
    @Test
    public void isCorrectTest() {
        final String hello = "hello 你好";
        final String speling = "speling 你好 以毒功毒";
        Assert.assertTrue(WordCheckerHelper.isCorrect(hello));
        Assert.assertFalse(WordCheckerHelper.isCorrect(speling));
    }

    /**
     * 返回最佳匹配结果
     */
    @Test
    public void correctTest() {
        final String hello = "hello 你好";
        final String speling = "speling 你好以毒功毒";
        Assert.assertEquals("hello 你好", WordCheckerHelper.correct(hello));
        Assert.assertEquals("spelling 你好以毒攻毒", WordCheckerHelper.correct(speling));
    }

    /**
     * 返回最佳匹配结果
     */
    @Test
    @Ignore
    public void correctMap() {
        final String hello = "hello 你好";
        final String speling = "speling 你好以毒功毒";
        Assert.assertEquals("{hello=[hello],  =[ ], 你=[你], 好=[好]}", WordCheckerHelper.correctMap(hello).toString());
        Assert.assertEquals("{ =[ ], speling=[spelling, spewing, sperling, seeling, spieling, spiling, speeling, speiling, spelding], 你=[你], 好=[好], 以毒功毒=[以毒攻毒]}", WordCheckerHelper.correctMap(speling).toString());
    }

    /**
     * 返回最佳匹配结果
     */
    @Test
    @Ignore
    public void correctMapLimit() {
        final String hello = "hello 你好";
        final String speling = "speling 你好以毒功毒";
        Assert.assertEquals("{hello=[hello],  =[ ], 你=[你], 好=[好]}", WordCheckerHelper.correctMap(hello, 2).toString());
        Assert.assertEquals("{ =[ ], speling=[spelling, spewing], 你=[你], 好=[好], 以毒功毒=[以毒攻毒]}", WordCheckerHelper.correctMap(speling, 2).toString());
    }

}
