package com.github.houbb.word.checker.support.dto;

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

    public CandidateDto(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidateDto)) return false;

        CandidateDto that = (CandidateDto) o;

        if (count != that.count) return false;
        return word != null ? word.equals(that.word) : that.word == null;
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CandidateDto{");
        sb.append("word='").append(word).append('\'');
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(CandidateDto o) {
        return o.count - this.count;
    }

    public static CandidateDtoBuilder builder() {
        return new CandidateDtoBuilder();
    }

    public static class CandidateDtoBuilder {
        /**
         * 单词
         */
        private String word;

        /**
         * 总数
         */
        private int count;

        public CandidateDtoBuilder word(String word) {
            this.word = word;
            return this;
        }
        public CandidateDtoBuilder count(int count) {
            this.count = count;
            return this;
        }

        public CandidateDto build() {
            return new CandidateDto(this.word, this.count);
        }
    }

}
