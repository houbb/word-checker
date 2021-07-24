package com.github.houbb.word.checker.support.data.english;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.word.checker.constant.WordCheckerConst;
import com.github.houbb.word.checker.support.dto.CandidateDto;

import java.util.*;

/**
 * 内部单词数据工具类
 * <p> project: word-checker-InnerWordDataUtil </p>
 * <p> create on 2020/2/6 13:42 </p>
 *
 * @author Administrator
 * @since 0.0.4
 */
public final class InnerWordDataUtil {

    private InnerWordDataUtil() {
    }

    /**
     * 初始化单词 map
     *
     * @param lines   行信息
     * @param wordMap 单词 map
     * @since 0.0.4
     */
    static void initWordMap(final List<String> lines,
                            final Map<String, Long> wordMap) {
        if (CollectionUtil.isEmpty(lines)) {
            return;
        }

        for (String line : lines) {
            if (StringUtil.isEmpty(line)) {
                continue;
            }

            String[] strings = StringUtil.splitToStringArray(line);
            if (strings.length > 1) {
                wordMap.put(strings[0], Long.valueOf(strings[1]));
            } else {
                wordMap.put(strings[0], WordCheckerConst.DEFAULT_EN_WORD_COUNT);
            }
        }
    }

    /**
     * 对称删除拼写纠正词库
     * <p>
     * 1. 如果单词长度小于1，则不作处理。
     * 2. 对单词的长度减去1，依次移除一个字母，把余下的部分作为 key，
     * value 是一个原始的 CandidateDto 列表。
     * 3. 如何去重比较优雅？
     * 4. 如何排序比较优雅？
     * <p>
     * 如果不考虑自定义词库，是可以直接把词库预处理好的，但是只是减少了初始化的时间，意义不大。
     *
     * @param freqMap    频率 Map
     * @param resultsMap 结果 map
     * @since 0.1.0
     */
    static synchronized void initSymSpellMap(Map<String, Long> freqMap,
                                             Map<String, List<CandidateDto>> resultsMap) {
        if (MapUtil.isEmpty(freqMap)) {
            return;
        }

        for (Map.Entry<String, Long> entry : freqMap.entrySet()) {
            String key = entry.getKey();
            Long count = entry.getValue();

            // 长度判断
            int len = key.length();
            // 后续可以根据编辑距离进行调整
            if (len <= 1) {
                continue;
            }

            char[] chars = key.toCharArray();
            Set<String> tempSet = new HashSet<>(chars.length);
            for (int i = 0; i < chars.length; i++) {
                String text = buildString(chars, i);

                // 跳过重复的单词
                if (tempSet.contains(text)) {
                    continue;
                }

                List<CandidateDto> candidateDtos = resultsMap.get(text);
                if (candidateDtos == null) {
                    candidateDtos = new ArrayList<>();
                }
                // 把原始的 key 作为值
                candidateDtos.add(new CandidateDto(key, count));
                // 删减后的文本作为 key
                resultsMap.put(text, candidateDtos);
                tempSet.add(text);
            }
        }

        // 统一排序
        for (Map.Entry<String, List<CandidateDto>> entry : resultsMap.entrySet()) {
            String key = entry.getKey();
            List<CandidateDto> list = entry.getValue();
            if (list.size() > 1) {
                // 排序
                Collections.sort(list);
                resultsMap.put(key, list);
            }
        }
    }

    /**
     * 构建字符串
     *
     * @param chars        字符数组
     * @param excludeIndex 排除的索引
     * @return 字符串
     * @since 0.1.0
     */
    public static String buildString(char[] chars, int excludeIndex) {
        StringBuilder stringBuilder = new StringBuilder(chars.length - 1);

        for (int i = 0; i < chars.length; i++) {
            if (i == excludeIndex) {
                continue;
            }

            stringBuilder.append(chars[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * 构建字符串集合
     * @param chars 字符数组
     * @return 结果
     * @since 0.1.0
     */
    public static Set<String> buildStringSet(char[] chars) {
        Set<String> results = new HashSet<>(chars.length);
        for(int i = 0; i < chars.length; i++) {
            String text = buildString(chars, i);
            results.add(text);
        }

        return results;
    }

}
