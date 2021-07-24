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
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class AbstractEnWordChecker implements IWordChecker {

    /**
     * 获取完整的备选列表
     *
     * @param word    单词
     * @param context 上下文
     * @return 结果
     * @since 0.1.0
     */
    protected abstract List<CandidateDto> getAllCandidateList(String word, IWordCheckerContext context);

    @Override
    public boolean isCorrect(String word, final IWordCheckerContext context) {
        String formatWord = formatWord(word, context.wordFormat());
        return context.wordData().freqData().containsKey(formatWord);
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
        //fast-failed
        if(StringUtil.isEmptyTrim(word)) {
            return Collections.emptyList();
        }

        String formatWord = formatWord(word, context.wordFormat());



        // 获取所有的单词列表
        List<CandidateDto> candidateDtos = getAllCandidateList(word, context);
        if (candidateDtos.size() > 0) {
            return getCandidateList(candidateDtos, limit);
        }

        // 没有匹配的单词
        return Collections.singletonList(word);
    }

    @Override
    public List<String> correctList(String word, final IWordCheckerContext context) {
        return correctList(word, Integer.MAX_VALUE, context);
    }

    /**
     * 格式化单词
     *
     * @param original   原始信息
     * @param wordFormat 格式化实现
     * @return 结果
     * @since 0.0.3
     */
    protected String formatWord(final String original, final IWordFormat wordFormat) {
        if (StringUtil.isEmptyTrim(original)) {
            return original;
        }

        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = original.toCharArray();

        for (char c : chars) {
            stringBuilder.append(wordFormat.format(c));
        }
        return stringBuilder.toString();
    }

    /**
     * 获取待选列表
     *
     * @param candidateDtos 列表
     * @param limit         限制
     * @return 截断后的列表
     * @since 0.0.1
     */
    protected List<String> getCandidateList(List<CandidateDto> candidateDtos, final int limit) {
        List<String> result = new LinkedList<>();
        //1.排序
        Collections.sort(candidateDtos);

        int toIndex = limit;
        if (toIndex > candidateDtos.size()) {
            toIndex = candidateDtos.size();
        }
        for (CandidateDto dto : candidateDtos) {
            if (result.size() >= toIndex) {
                break;
            }
            if (result.contains(dto.getWord())) {
                continue;
            }
            result.add(dto.getWord());
        }
        return result;
    }

}
