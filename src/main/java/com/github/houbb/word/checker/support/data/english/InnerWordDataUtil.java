package com.github.houbb.word.checker.support.data.english;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.word.checker.constant.WordCheckerConst;

import java.util.List;
import java.util.Map;

/**
 * 内部单词数据工具类
 * <p> project: word-checker-InnerWordDataUtil </p>
 * <p> create on 2020/2/6 13:42 </p>
 *
 * @author Administrator
 * @since 0.0.4
 */
final class InnerWordDataUtil {

    private InnerWordDataUtil(){}

    /**
     * 初始化单词 map
     * @param lines 行信息
     * @param wordMap 单词 map
     * @since 0.0.4
     */
    static void initWordMap(final List<String> lines,
                            final Map<String, Integer> wordMap) {
        if(CollectionUtil.isEmpty(lines)) {
            return;
        }

        for(String line: lines) {
            if (StringUtil.isEmpty(line)) {
                continue;
            }

            String[] strings = StringUtil.splitToStringArray(line);
            if(strings.length > 1) {
                wordMap.put(strings[0], Integer.valueOf(strings[1]));
            } else {
                wordMap.put(strings[0], WordCheckerConst.DEFAULT_EN_WORD_COUNT);
            }
        }
    }

}
