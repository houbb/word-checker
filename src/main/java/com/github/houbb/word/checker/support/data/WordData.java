package com.github.houbb.word.checker.support.data;

import java.util.Map;

/**
 * 单词数据结构
 * @author binbin.hou
 * @since 0.0.2
 */
public interface WordData {

    /**
     * 获取对应的数据信息
     * key: 单词表
     * value: 出现频率
     * @return Map 集合
     */
    Map<String, Integer> data();

}
