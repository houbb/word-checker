package com.github.houbb.word.checker.support.data.english;

import com.github.houbb.word.checker.exception.WordCheckRuntimeException;
import com.github.houbb.word.checker.support.data.AbstractWordData;
import com.github.houbb.word.checker.support.dto.CandidateDto;
import com.github.houbb.word.checker.support.i18n.I18N;

import java.util.HashMap;
import java.util.List;
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
    private static Map<String, Long> wordMap = new HashMap<>();

    /**
     * 对称删减数据集合
     * @since 0.1.0
     */
    private static final Map<String, List<CandidateDto>> symSpellData = new HashMap<>();

    static {
        try {
            Map<String, Long> systemWordMap = EnglishWordDatas.system().freqData();
            Map<String, Long> defineWordMap = EnglishWordDatas.define().freqData();

            wordMap.putAll(systemWordMap);
            wordMap.putAll(defineWordMap);

            InnerWordDataUtil.initSymSpellMap(wordMap, symSpellData);
        } catch (Exception e) {
            throw new WordCheckRuntimeException(I18N.get("english_data_file_load_failed"));
        }
    }

    @Override
    public Map<String, Long> freqData() {
        return wordMap;
    }

    @Override
    public Map<String, List<CandidateDto>> symSpellData() {
        return symSpellData;
    }

}
