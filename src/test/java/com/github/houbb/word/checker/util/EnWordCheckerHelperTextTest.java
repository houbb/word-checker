package com.github.houbb.word.checker.util;

import org.junit.Assert;
import org.junit.Ignore;
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
public class EnWordCheckerHelperTextTest {

    /**
     * 是否拼写正确
     */
    @Test
    @Ignore
    public void isCorrectTest() {
        final String text = "I lovv you";
        String result = WordCheckerHelper.correct(text);
        Assert.assertEquals("I love you", result);
    }

    @Test
    public void correctListTest() {
        String text = "I";

        List<String> list = WordCheckerHelper.correctList(text);

        System.out.println(list);
    }

}
