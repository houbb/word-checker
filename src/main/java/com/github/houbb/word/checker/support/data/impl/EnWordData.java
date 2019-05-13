package com.github.houbb.word.checker.support.data.impl;

import com.github.houbb.word.checker.core.impl.EnWordChecker;
import com.github.houbb.word.checker.exception.WordCheckRuntimeException;
import com.github.houbb.word.checker.support.data.WordData;
import com.github.houbb.word.checker.support.i18n.I18N;
import com.github.houbb.word.checker.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 英文单词数据
 * @author binbin.hou
 * @since 0.0.2
 */
public class EnWordData implements WordData {

    private EnWordData(){}

    /**
     * 静态内部类实现单例
     */
    private static class EnWordDataHolder {
        private static final EnWordData INSTANCE = new EnWordData();
    }

    public static EnWordData getInstance() {
        return EnWordDataHolder.INSTANCE;
    }

    /**
     * 数据集合
     */
    private static Map<String, Integer> wordMap = new HashMap<>();

    static {
        try(InputStream is = EnWordChecker.class.getResourceAsStream("/data/en/word.txt")) {
            BufferedReader in = new BufferedReader(new InputStreamReader(is,
                    StandardCharsets.UTF_8));

            while (in.ready()) {
                String entry = in.readLine();
                if (StringUtil.isEmpty(entry)) {
                    continue;
                }
                String[] strings = StringUtil.splitByComma(entry);
                wordMap.put(strings[0], Integer.valueOf(strings[1]));
            }
        } catch (IOException e) {
            throw new WordCheckRuntimeException(I18N.get("english_data_file_load_failed"));
        }
    }

    @Override
    public Map<String, Integer> data() {
        return wordMap;
    }

}
