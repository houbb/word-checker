package com.github.houbb.word.checker.core;

import com.github.houbb.word.checker.core.impl.EnWordChecker;

import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void isCorrectTest() {
        final String hello = "hello";
        final String speling = "speling";
        Assert.assertTrue(EnWordChecker.getInstance().isCorrect(hello));
        Assert.assertFalse(EnWordChecker.getInstance().isCorrect(speling));
    }

    @Test
    public void correctTest() {
        final String hello = "hello";
        final String speling = "speling";
        Assert.assertEquals("hello", EnWordChecker.getInstance().correct(hello));
        Assert.assertEquals("spelling", EnWordChecker.getInstance().correct(speling));
    }

}
