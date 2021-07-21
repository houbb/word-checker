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
public class WordCheckersTest {

    /**
     * 是否拼写正确
     */
    @Test
    public void isCorrectTest() {
        final String hello = "hello 你好";
        final String speling = "speling 你好 以毒功毒";
        Assert.assertTrue(WordCheckers.isCorrect(hello));
        Assert.assertFalse(WordCheckers.isCorrect(speling));
    }

    /**
     * 返回最佳匹配结果
     */
    @Test
    public void correctTest() {
        final String hello = "hello 你好";
        final String speling = "speling 你好以毒功毒";
        Assert.assertEquals("hello 你好", WordCheckers.correct(hello));
        Assert.assertEquals("spelling 你好以毒攻毒", WordCheckers.correct(speling));
    }

    /**
     * 返回最佳匹配结果
     */
    @Test
    @Ignore
    public void correctMap() {
        final String hello = "hello 你好";
        final String speling = "speling 你好以毒功毒";
        Assert.assertEquals("{hello=[hello],  =[ ], 你=[你], 好=[好]}", WordCheckers.correctMap(hello).toString());
        Assert.assertEquals("{ =[ ], speling=[spelling, spewing, sperling, seeling, spieling, spiling, speeling, speiling, spelding], 你=[你], 好=[好], 以毒功毒=[以毒攻毒]}", WordCheckers.correctMap(speling).toString());
    }

    /**
     * 返回最佳匹配结果
     */
    @Test
    @Ignore
    public void correctMapLimit() {
        final String hello = "hello 你好";
        final String speling = "speling 你好以毒功毒";
        Assert.assertEquals("{hello=[hello],  =[ ], 你=[你], 好=[好]}", WordCheckers.correctMap(hello, 2).toString());
        Assert.assertEquals("{ =[ ], speling=[spelling, spewing], 你=[你], 好=[好], 以毒功毒=[以毒攻毒]}", WordCheckers.correctMap(speling, 2).toString());
    }

}
