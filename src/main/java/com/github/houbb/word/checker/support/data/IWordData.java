package com.github.houbb.word.checker.support.data;

import java.util.List;
import java.util.Map;

/**
 * 单词数据结构
 * @author binbin.hou
 * @since 0.0.2
 */
public interface IWordData {

    /**
     * 获取对应的数据信息
     * key: 单词表
     * value: 出现频率
     * @return Map 集合
     * @since 0.0.3
     */
    Map<String, Integer> freqData();

    /**
     * 修正数据列表
     * @return map
     * @since 0.0.5
     */
    Map<String, List<String>> correctData();

}
