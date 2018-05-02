package com.github.houbb.word.checker.core.impl;

import com.github.houbb.word.checker.constant.WordCheckerContant;
import com.github.houbb.word.checker.core.WordChecker;
import com.github.houbb.word.checker.util.EnWordUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p> 英文单词拼写检查 </p>
 *
 * <pre> Created: 2018-05-02 08:59  </pre>
 * <pre> Project: word-checker  </pre>
 * @author houbinbin
 * @version 0.0.1
 * @since 0.0.1
 */
public final class EnWordChecker implements WordChecker {

    /**
     * 当前类实例
     */
    private static final EnWordChecker INSTANCE = new EnWordChecker();

    /**
     * 单词表+出现频率
     */
    private static final Map<String, Integer> WORD_MAP  = EnWordUtil.getWordMap();

    /**
     * 构造器私有
     */
    private EnWordChecker() {
    }

    /**
     * 获取一个单例
     * @return 实例
     */
    public static EnWordChecker getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isCorrect(String original) {
        return WORD_MAP.containsKey(original);
    }

    @Override
    public final String correct(String word) {
        if (WORD_MAP.containsKey(word)) {
            return word;
        }
        List<String> list = edits(word);
        HashMap<Integer, String> candidates = new HashMap<>();
        for (String s : list) {
            if (WORD_MAP.containsKey(s)) {
                candidates.put(WORD_MAP.get(s), s);
            }
        }
        if (candidates.size() > 0) {
            return candidates.get(Collections.max(candidates.keySet()));
        }
        for (String s : list) {
            for (String w : edits(s)) {
                if (WORD_MAP.containsKey(w)) {
                    candidates.put(WORD_MAP.get(w), w);
                }
            }
        }
        return candidates.size() > 0 ? candidates.get(Collections.max(candidates.keySet())) : word;
    }

    @Override
    public List<String> correctList(String word, int limit) {
        return null;
    }

    @Override
    public List<String> correctList(String word) {
        return correctList(word, WordCheckerContant.DEFAULT_BEST_MATCH_LIMIT);
    }

    //region private methods

    /**
     * 构建出当前单词的所有可能错误情况
     *
     * @param word 输入单词
     * @return 返回结果
     */
    private List<String> edits(String word) {
        List<String> result = new LinkedList<>();
        for (int i = 0; i < word.length(); ++i) {
            result.add(word.substring(0, i) + word.substring(i + 1));
        }
        for (int i = 0; i < word.length() - 1; ++i) {
            result.add(word.substring(0, i) + word.substring(i + 1, i + 2) + word.substring(i, i + 1) + word.substring(i + 2));
        }
        for (int i = 0; i < word.length(); ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i + 1));
            }
        }
        for (int i = 0; i <= word.length(); ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i));
            }
        }
        return result;
    }
    //endregion

}
