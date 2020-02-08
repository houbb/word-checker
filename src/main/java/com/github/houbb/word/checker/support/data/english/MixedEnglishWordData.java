package com.github.houbb.word.checker.support.data.english;

import com.github.houbb.word.checker.exception.WordCheckRuntimeException;
import com.github.houbb.word.checker.support.data.AbstractWordData;
import com.github.houbb.word.checker.support.i18n.I18N;

import java.util.HashMap;
import java.util.Map;

/**
 * 混合模式-英文单词数据
 * （1）系统内置
 * （2）用户自定义
 *
 * @author binbin.hou
 * @since 0.0.4
 */
class MixedEnglishWordData extends AbstractWordData {

    private MixedEnglishWordData(){}

    /**
     * 静态内部类实现单例
     */
    private static class EnWordDataHolder {
        private static final MixedEnglishWordData INSTANCE = new MixedEnglishWordData();
    }

    public static MixedEnglishWordData getInstance() {
        return EnWordDataHolder.INSTANCE;
    }

    /**
     * 数据集合
     * @since 0.0.1
     */
    private static Map<String, Integer> wordMap = new HashMap<>();

    static {
        try {
            Map<String, Integer> systemWordMap = EnglishWordDatas.system().freqData();
            Map<String, Integer> defineWordMap = EnglishWordDatas.define().freqData();

            wordMap.putAll(systemWordMap);
            wordMap.putAll(defineWordMap);
        } catch (Exception e) {
            throw new WordCheckRuntimeException(I18N.get("english_data_file_load_failed"));
        }
    }

    @Override
    public Map<String, Integer> freqData() {
        return wordMap;
    }

}
