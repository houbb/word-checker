package com.github.houbb.word.checker.core;

import com.github.houbb.word.checker.core.foo.FooEnWordChecker;
import org.junit.Assert;
import org.junit.Test;

/**
 * 自定义 checker 测试
 * @author binbin.hou
 * @since 0.0.2
 */
public class FooWordCheckerTest {

    /**
     * 自定义单词检查类测试
     */
    @Test
    public void defineWorkerCheckerTest() {
        WordCheckers wordCheckers = WordCheckers
                .word("hello")
                .wordChecker(new FooEnWordChecker());

        Assert.assertFalse(wordCheckers.isCorrect());
        Assert.assertNull(wordCheckers.correct());
        Assert.assertNull(wordCheckers.correctList());
        Assert.assertNull(wordCheckers.correctList(2));
    }

}
