package com.github.houbb.word.checker.support.format.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.word.checker.support.format.IWordFormat;

/**
 * 英文大小写格式化处理
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class LowerCaseWordFormat implements IWordFormat {

    @Override
    public char format(char ch) {
        return Character.toLowerCase(ch);
    }

}
