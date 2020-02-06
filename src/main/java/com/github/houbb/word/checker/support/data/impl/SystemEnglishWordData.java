package com.github.houbb.word.checker.support.data.impl;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.word.checker.constant.WordCheckerConst;
import com.github.houbb.word.checker.exception.WordCheckRuntimeException;
import com.github.houbb.word.checker.support.data.IWordData;
import com.github.houbb.word.checker.support.i18n.I18N;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统内置-英文单词数据
 * @author binbin.hou
 * @since 0.0.2
 */
class SystemEnglishWordData implements IWordData {

    private SystemEnglishWordData(){}

    /**
     * 静态内部类实现单例
     */
    private static class EnWordDataHolder {
        private static final SystemEnglishWordData INSTANCE = new SystemEnglishWordData();
    }

    public static SystemEnglishWordData getInstance() {
        return EnWordDataHolder.INSTANCE;
    }

    /**
     * 数据集合
     * @since 0.0.1
     */
    private static Map<String, Integer> wordMap = new HashMap<>();

    static {
        try {
            List<String> allLines = StreamUtil.readAllLines(WordCheckerConst.SYSTEM_EN_DICT_PATH);
            for(String line: allLines) {
                if (StringUtil.isEmpty(line)) {
                    continue;
                }
                String[] strings = StringUtil.splitToStringArray(line);
                wordMap.put(strings[0], Integer.valueOf(strings[1]));
            }
        } catch (Exception e) {
            throw new WordCheckRuntimeException(I18N.get("english_data_file_load_failed"));
        }
    }

    @Override
    public Map<String, Integer> data() {
        return wordMap;
    }

}
