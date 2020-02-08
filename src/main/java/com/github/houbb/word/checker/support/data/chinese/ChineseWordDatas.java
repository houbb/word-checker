package com.github.houbb.word.checker.support.data.chinese;

import com.github.houbb.word.checker.support.data.IWordData;

/**
 * <p> project: word-checker-ChineseWordDatas </p>
 * <p> create on 2020/2/8 17:37 </p>
 *
 * @author Administrator
 * @since 0.0.5
 */
public final class ChineseWordDatas {

    private ChineseWordDatas(){}

    /**
     * 混合模式实现
     * @return 混合模式中文实现
     * @since 0.0.5
     */
    public static IWordData mixed() {
        return MixedChineseWordData.getInstance();
    }

}
