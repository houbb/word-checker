package com.github.houbb.word.checker.core.impl;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.word.checker.core.IWordCheckerContext;
import com.github.houbb.word.checker.support.data.IWordData;
import com.github.houbb.word.checker.support.data.english.InnerWordDataUtil;
import com.github.houbb.word.checker.support.dto.CandidateDto;

import java.util.*;

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
public final class SymSpellEnWordChecker extends AbstractEnWordChecker {

    /**
     * 构造器私有
     * @since 0.0.1
     */
    private SymSpellEnWordChecker() {
    }

    /**
     * 静态内部类，实现单例
     * @since 0.0.2
     */
    private static class EnWordCheckerHolder {
        private static final SymSpellEnWordChecker INSTANCE = new SymSpellEnWordChecker();
    }

    /**
     * 获取一个单例
     *
     * @return 实例
     */
    public static SymSpellEnWordChecker getInstance() {
        return EnWordCheckerHolder.INSTANCE;
    }

    /**
     * dictionary entry==input entry,
     * delete(dictionary entry,p1)==input entry  // 预处理
     * dictionary entry==delete(input entry,p2)
     * delete(dictionary entry,p1)==delete(input entry,p2)
     *
     * 为了性能考虑，这里做快速返回。后期可以考虑可以配置，暂时不做处理。
     *
     * @param word    单词
     * @param context 上下文
     * @return 结果
     * @since 0.1.0
     */
    @Override
    protected List<CandidateDto> getAllCandidateList(String word, IWordCheckerContext context) {
        IWordData wordData = context.wordData();
        Map<String, Long> freqData = wordData.freqData();
        Map<String, List<CandidateDto>> symSpellData = wordData.symSpellData();

        //0. 原始字典包含
        if (freqData.containsKey(word)) {
            // 返回原始信息
            CandidateDto dto = CandidateDto.of(word, freqData.get(word));
            return Collections.singletonList(dto);
        }

        // 如果长度为1
        if(word.length() <= 1) {
            CandidateDto dtoA = CandidateDto.of("a", 9081174698L);
            CandidateDto dtoI = CandidateDto.of("i", 3086225277L);
            return Arrays.asList(dtoA, dtoI);
        }

        List<CandidateDto> resultList = new ArrayList<>();

        //1. 对称删减包含输入的单词
        List<CandidateDto> symSpellList = symSpellData.get(word);
        if(CollectionUtil.isNotEmpty(symSpellList)) {
            resultList.addAll(symSpellList);
        }

        // 所有删减后的数组
        Set<String> subWordSet = InnerWordDataUtil.buildStringSet(word.toCharArray());

        //2. 输入单词删减后，在原始字典中存在。
        for(String subWord : subWordSet) {
            if(freqData.containsKey(subWord)) {
                CandidateDto dto = CandidateDto.of(subWord, freqData.get(subWord));
                resultList.add(dto);
            }
        }
        //3. 输入单词删减后，在对称删除字典存在。
        for(String subWord : subWordSet) {
            if(symSpellData.containsKey(subWord)) {
                resultList.addAll(symSpellData.get(subWord));
            }
        }
        if(CollectionUtil.isNotEmpty(resultList)) {
            return resultList;
        }

        //4. 执行替换和修改（递归调用一次）甚至也可以不做处理。
        // 为保证编辑距离为1，只考虑原始字典
        List<String> edits = edits(word);
        for(String edit : edits) {
            if(freqData.containsKey(edit)) {
                CandidateDto dto = CandidateDto.of(edit, freqData.get(edit));
                resultList.add(dto);
            }
        }

        return resultList;
    }

    /**
     * 构建出当前单词的所有可能错误情况
     *
     * 暂时只考虑修改，不考虑 replace。
     *
     * 新增和删除已经全部验证过了。
     *
     * @param word 输入单词
     * @return 返回结果
     * @since 0.1.0
     */
    protected List<String> edits(String word) {
        List<String> result = new LinkedList<>();
        for (int i = 0; i < word.length(); ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                result.add(word.substring(0, i) + c + word.substring(i + 1));
            }
        }
        return result;
    }

}
