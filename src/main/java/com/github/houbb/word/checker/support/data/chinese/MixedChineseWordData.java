package com.github.houbb.word.checker.support.data.chinese;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.word.checker.constant.WordCheckerConst;
import com.github.houbb.word.checker.exception.WordCheckRuntimeException;
import com.github.houbb.word.checker.support.data.AbstractWordData;
import com.github.houbb.word.checker.support.data.IWordData;

import java.util.List;
import java.util.Map;

/**
 * 混合模式-中文单词数据
 * （1）系统内置
 * （2）用户自定义
 *
 * @author binbin.hou
 * @since 0.0.5
 */
class MixedChineseWordData extends AbstractWordData {

    private MixedChineseWordData(){}



    /**
     * 静态内部类实现单例
     */
    private static class ChineseWordDataHolder {
        private static final MixedChineseWordData INSTANCE = new MixedChineseWordData();
    }

    public static MixedChineseWordData getInstance() {
        return ChineseWordDataHolder.INSTANCE;
    }

    /**
     * 数据集合
     * @since 0.0.1
     */
    private static final Map<String, List<String>> WORD_MAP;

    static {
        try {
            List<String> lines = StreamUtil.readAllLines(WordCheckerConst.SYSTEM_ZH_DICT_PATH);
            List<String> defineLines = StreamUtil.readAllLines(WordCheckerConst.SYSTEM_ZH_DICT_PATH);
            lines.addAll(defineLines);

            WORD_MAP = Guavas.newHashMap(lines.size());

            for(String line : lines) {
                if(StringUtil.isEmptyTrim(line)) {
                    continue;
                }

                String[] strings = line.split(StringUtil.BLANK);
                WORD_MAP.put(strings[0], StringUtil.splitToList(strings[1]));
            }
        } catch (Exception e) {
            throw new WordCheckRuntimeException("chinese dict load failed!");
        }
    }

    @Override
    public Map<String, List<String>> correctData() {
        return WORD_MAP;
    }

}
