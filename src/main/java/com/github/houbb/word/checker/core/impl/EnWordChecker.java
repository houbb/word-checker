package com.github.houbb.word.checker.core.impl;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.word.checker.core.IWordChecker;
import com.github.houbb.word.checker.core.IWordCheckerContext;
import com.github.houbb.word.checker.exception.WordCheckRuntimeException;
import com.github.houbb.word.checker.support.dto.CandidateDto;
import com.github.houbb.word.checker.support.format.IWordFormat;
import com.github.houbb.word.checker.support.i18n.I18N;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
public final class EnWordChecker implements IWordChecker {

    /**
     * 构造器私有
     * @since 0.0.1
     */
    private EnWordChecker() {
    }

    /**
     * 静态内部类，实现单例
     * @since 0.0.2
     */
    private static class EnWordCheckerHolder {
        private static final EnWordChecker INSTANCE = new EnWordChecker();
    }

    /**
     * 获取一个单例
     *
     * @return 实例
     */
    public static EnWordChecker getInstance() {
        return EnWordCheckerHolder.INSTANCE;
    }

    @Override
    public boolean isCorrect(String word, final IWordCheckerContext context) {
        String formatWord = formatWord(word, context.wordFormat());
        return context.wordData().data().containsKey(formatWord);
    }

    @Override
    public final String correct(String word, final IWordCheckerContext context) {
        return correctList(word, 1, context).get(0);
    }

    @Override
    public List<String> correctList(String word, int limit, final IWordCheckerContext context) {
        if (limit < 1) {
            throw new WordCheckRuntimeException(I18N.get("english_word_correct_limit_out_of_range"));
        }

        String formatWord = formatWord(word, context.wordFormat());

        final Map<String, Integer> wordDataMap = context.wordData().data();
        if (wordDataMap.containsKey(formatWord)) {
            // 返回原始信息
            return Collections.singletonList(word);
        }

        List<String> options = edits(formatWord);
        List<CandidateDto> candidateDtos = new LinkedList<>();
        for (String option : options) {
            if (wordDataMap.containsKey(option)) {
                CandidateDto dto = CandidateDto.builder()
                        .word(option).count(wordDataMap.get(option)).build();
                candidateDtos.add(dto);
            }
        }
        if (candidateDtos.size() > 0) {
            return getCandidateList(candidateDtos, limit);
        }

        for (String option : options) {
            for (String optionEdit : edits(option)) {
                if (wordDataMap.containsKey(optionEdit)) {
                    CandidateDto dto = CandidateDto.builder()
                            .word(option).count(wordDataMap.get(option)).build();
                    candidateDtos.add(dto);
                }
            }
        }
        if (candidateDtos.size() > 0) {
            return getCandidateList(candidateDtos, limit);
        }

        return Collections.singletonList(word);
    }

    /**
     * 获取待选列表
     *
     * @param candidateDtos 列表
     * @param limit         限制
     * @return 截断后的列表
     * @since 0.0.1
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
    public List<String> correctList(String word, final IWordCheckerContext context) {
        return correctList(word, Integer.MAX_VALUE, context);
    }

    /**
     * 构建出当前单词的所有可能错误情况
     *
     * @param word 输入单词
     * @return 返回结果
     * @since 0.0.1
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
                result.add(word.substring(0, i) + c + word.substring(i + 1));
            }
        }
        for (int i = 0; i <= word.length(); ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                result.add(word.substring(0, i) + c + word.substring(i));
            }
        }
        return result;
    }

    /**
     * 格式化单词
     * @param original 原始信息
     * @param wordFormat 格式化实现
     * @return 结果
     * @since 0.0.3
     */
    private String formatWord(final String original, final IWordFormat wordFormat) {
        if(StringUtil.isEmptyTrim(original)) {
            return original;
        }

        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = original.toCharArray();

        for(char c : chars) {
            stringBuilder.append(wordFormat.format(c));
        }
        return stringBuilder.toString();
    }

}
