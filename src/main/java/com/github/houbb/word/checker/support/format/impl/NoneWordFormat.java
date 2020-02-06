package com.github.houbb.word.checker.support.format.impl;


import com.github.houbb.word.checker.support.format.IWordFormat;

/**
 * 无格式化处理
 * @author binbin.hou
 * @since 0.0.3
 */
public class NoneWordFormat implements IWordFormat {

    @Override
    public char format(char ch) {
        return ch;
    }

}
