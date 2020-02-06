package com.github.houbb.word.checker.support.data.impl;

import com.github.houbb.word.checker.support.data.IWordData;

/**
 * 英文字典路径
 * <p> project: word-checker-WordDatas </p>
 * <p> create on 2020/2/6 11:29 </p>
 *
 * @author Administrator
 * @since 0.0.3
 */
public final class WordDatas {

    private WordDatas(){}

    /**
     * 系统内置英语字典
     * @return 字典
     * @since 0.0.3
     */
    public static IWordData systemEnglish() {
        return SystemEnglishWordData.getInstance();
    }

}
