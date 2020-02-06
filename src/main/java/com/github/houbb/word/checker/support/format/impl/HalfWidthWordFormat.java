package com.github.houbb.word.checker.support.format.impl;

import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.word.checker.support.format.IWordFormat;

/**
 * 全角半角格式化处理
 * @author binbin.hou
 * @since 0.0.3
 */
public class HalfWidthWordFormat implements IWordFormat {

    @Override
    public char format(char ch) {
        return CharUtil.toHalfWidth(ch);
    }

}
