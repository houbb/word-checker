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
    private int count;

    /**
     * 编辑距离
     * @since 1.1.0
     */
    private int editDistance;

    @Override
    public int compareTo(CandidateDto o) {
        //1. 优先比较编辑距离，越小的约靠前
        if(o.editDistance != this.editDistance) {
            return this.editDistance - o.editDistance;
        }
        //2. 然后比较频率。越大的靠前
        return o.count - this.count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CandidateDto that = (CandidateDto) o;
        return count == that.count && editDistance == that.editDistance && Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, count, editDistance);
    }

    public static CandidateDto newInstance() {
        return new CandidateDto();
    }

    public String word() {
        return word;
    }

    public CandidateDto word(String word) {
        this.word = word;
        return this;
    }

    public int count() {
        return count;
    }

    public CandidateDto count(int count) {
        this.count = count;
        return this;
    }

    public int editDistance() {
        return editDistance;
    }

    public CandidateDto editDistance(int editDistance) {
        this.editDistance = editDistance;
        return this;
    }

    @Override
    public String toString() {
        return "CandidateDto{" +
                "word='" + word + '\'' +
                ", count=" + count +
                ", editDistance=" + editDistance +
                '}';
    }
}

