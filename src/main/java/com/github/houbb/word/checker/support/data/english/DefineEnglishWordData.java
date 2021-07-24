package com.github.houbb.word.checker.support.data.english;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.word.checker.constant.WordCheckerConst;
import com.github.houbb.word.checker.exception.WordCheckRuntimeException;
import com.github.houbb.word.checker.support.data.AbstractWordData;
import com.github.houbb.word.checker.support.dto.CandidateDto;
import com.github.houbb.word.checker.support.i18n.I18N;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义-英文单词数据
 * @author binbin.hou
 * @since 0.0.4
 */
class DefineEnglishWordData extends AbstractWordData {

    private DefineEnglishWordData(){}

    /**
     * 静态内部类实现单例
     */
    private static class EnWordDataHolder {
        private static final DefineEnglishWordData INSTANCE = new DefineEnglishWordData();
    }

    public static DefineEnglishWordData getInstance() {
        return EnWordDataHolder.INSTANCE;
    }

    /**
     * 数据集合
     * @since 0.0.1
     */
    private static final Map<String, Long> wordMap = new HashMap<>();

    /**
     * 对称删减数据集合
     * @since 0.1.0
     */
    private static final Map<String, List<CandidateDto>> symSpellData = new HashMap<>();

    static {
        try {
            List<String> allLines = StreamUtil.readAllLines(WordCheckerConst.DEFINE_EN_DICT_PATH);
            InnerWordDataUtil.initWordMap(allLines, wordMap);
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
