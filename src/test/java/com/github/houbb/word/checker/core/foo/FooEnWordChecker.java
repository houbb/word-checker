package com.github.houbb.word.checker.core.foo;

import com.github.houbb.word.checker.core.WordChecker;

import java.util.List;

/**
 * 自定义英文校验
 * 注意：本示例没有任何实际意义，仅仅作为演示
 * @author binbin.hou
 * @since 0.0.2
 */
public class FooEnWordChecker implements WordChecker {
    @Override
    public boolean isCorrect(String word) {
        return false;
    }

    @Override
    public String correct(String word) {
        return null;
    }

    @Override
    public List<String> correctList(String word, int limit) {
        return null;
    }

    @Override
    public List<String> correctList(String word) {
        return null;
    }
}
