package com.github.houbb.word.checker.core.impl;

import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.word.checker.constant.WordCheckerContant;
import com.github.houbb.word.checker.core.WordChecker;
import com.github.houbb.word.checker.exception.WordCheckRuntimeException;
import com.github.houbb.word.checker.support.dto.CandidateDto;
import com.github.houbb.word.checker.support.i18n.I18N;
import com.github.houbb.word.checker.util.EnWordUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p> 英文单词拼写检查 </p>
 *
 * <pre> Created: 2018-05-02 08:59  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @version 0.0.1
 * @since 0.0.1
 */
public final class EnWordChecker implements WordChecker {

    private static final Log log = LogFactory.getLog(EnWordChecker.class);

    /**
     * 当前类实例
     */
    private static final EnWordChecker INSTANCE = new EnWordChecker();

    /**
     * 单词表+出现频率
     */
    private static final Map<String, Integer> WORD_MAP = EnWordUtil.getWordMap();

    /**
     * 构造器私有
     */
    private EnWordChecker() {
    }

    /**
     * 获取一个单例
     *
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
        return correctList(word, 1).get(0);
    }

    @Override
    public List<String> correctList(String word, int limit) {
        if (limit < 1) {
            throw new WordCheckRuntimeException(I18N.get("english_word_correct_limit_out_of_range"));
        }

        if (WORD_MAP.containsKey(word)) {
            return Collections.singletonList(word);
        }

        List<String> options = edits(word);
        List<CandidateDto> candidateDtos = new LinkedList<>();
        for (String option : options) {
            if (WORD_MAP.containsKey(option)) {
                CandidateDto dto = CandidateDto.builder()
                        .word(option).count(WORD_MAP.get(option)).build();
                candidateDtos.add(dto);
            }
        }
        if (candidateDtos.size() > 0) {
            return getCandidateList(candidateDtos, limit);
        }

        for (String option : options) {
            for (String optionEdit : edits(option)) {
                if (WORD_MAP.containsKey(optionEdit)) {
                    CandidateDto dto = CandidateDto.builder()
                            .word(option).count(WORD_MAP.get(option)).build();
                    candidateDtos.add(dto);
                }
            }
        }
        if (candidateDtos.size() > 0) {
            return getCandidateList(candidateDtos, limit);
        }

        log.warn("Could not find correct spell for word: {}", word);
        return Collections.singletonList(word);
    }

    /**
     * 获取待选列表
     *
     * @param candidateDtos 列表
     * @param limit         限制
     * @return 截断后的列表
     */
    private List<String> getCandidateList(List<CandidateDto> candidateDtos, final int limit) {
        List<String> result = new LinkedList<>();
        //1.排序
        Collections.sort(candidateDtos);

        int toIndex = limit;
        if (toIndex > candidateDtos.size()) {
            toIndex = candidateDtos.size();
        }
        for (CandidateDto dto : candidateDtos) {
            if(result.size() >= toIndex) {
                break;
            }
            if(result.contains(dto.getWord())) {
               continue;
            }
            result.add(dto.getWord());
        }
        return result;
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
