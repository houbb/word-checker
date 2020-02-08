package com.github.houbb.word.checker.support.data.english;

import com.github.houbb.word.checker.support.data.IWordData;

/**
 * 英文字典路径
 * <p> project: word-checker-WordDatas </p>
 * <p> create on 2020/2/6 11:29 </p>
 *
 * @author Administrator
 * @since 0.0.3
 */
public final class EnglishWordDatas {

    private EnglishWordDatas(){}

    /**
     * 系统内置英语字典
     * @return 字典
     * @since 0.0.3
     */
    public static IWordData system() {
        return SystemEnglishWordData.getInstance();
    }

    /**
     * 自定义英语字典
     * @return 字典
     * @since 0.0.4
     */
    public static IWordData define() {
        return DefineEnglishWordData.getInstance();
    }

    /**
     * 混合模式加载英语字典
     * @return 字典
     * @since 0.0.4
     */
    public static IWordData mixed() {
        return MixedEnglishWordData.getInstance();
    }

}
