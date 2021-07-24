package com.github.houbb.word.checker.support.dto;

import java.util.Objects;

/**
 * <p> 待选对象 </p>
 *
 * <pre> Created: 2018-05-02 13:26  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @version 0.0.1
 * @since 0.0.1
 */
public class CandidateDto implements Comparable<CandidateDto> {

    /**
     * 单词
     */
    private String word;

    /**
     * 总数
     */
    private Long count;

    public CandidateDto(String word, Long count) {
        this.word = word;
        this.count = count;
    }

    /**
     * @param word 单词
     * @param count 频率
     * @return 结果
     * @since 0.1.0
     */
    public static CandidateDto of(String word, Long count) {
        return new CandidateDto(word, count);
    }

    public String getWord() {
        return word;
    }

    public Long getCount() {
        return count;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CandidateDto that = (CandidateDto) object;
        return Objects.equals(word, that.word) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, count);
    }

    @Override
    public String toString() {
        return "CandidateDto{" + "word='" + word + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(CandidateDto o) {
        return o.count.compareTo(this.count);
    }

}
