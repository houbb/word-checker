package com.github.houbb.word.checker.core.impl;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.word.checker.core.IWordChecker;
import com.github.houbb.word.checker.core.IWordCheckerContext;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * MVP 版本，不支持分词。
 *
 * <p> project: word-checker-ZhWordChecker </p>
 * <p> create on 2020/2/8 17:41 </p>
 *
 * @author Administrator
 * @since 0.0.5
 */
public class ZhWordChecker implements IWordChecker {

    @Override
    public boolean isCorrect(String word, IWordCheckerContext context) {
        final Map<String, List<String>> wordData = context.wordData().correctData();
        return !wordData.containsKey(word);
    }

    @Override
    public String correct(String word, IWordCheckerContext context) {
        return correctList(word, 1, context).get(0);
    }

    @Override
    public List<String> correctList(String word, int limit, IWordCheckerContext context) {
        final Map<String, List<String>> wordData = context.wordData().correctData();

        // 判断是否错误
        if(isCorrect(word, context)) {
            return Collections.singletonList(word);
        }

        List<String> allList = wordData.get(word);
        final int minLimit = Math.min(allList.size(), limit);
        List<String> resultList = Guavas.newArrayList(minLimit);
        for(int i = 0; i < minLimit; i++) {
            resultList.add(allList.get(i));
        }

        return resultList;
    }

    @Override
    public List<String> correctList(String word, IWordCheckerContext context) {
        return correctList(word, Integer.MAX_VALUE, context);
    }

}
