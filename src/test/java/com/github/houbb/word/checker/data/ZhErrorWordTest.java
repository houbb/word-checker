package com.github.houbb.word.checker.data;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * <p> project: word-checker-ZhErrorWordTest </p>
 * <p> create on 2020/2/8 14:42 </p>
 *
 * @author Administrator
 * @since 0.0.5
 */
@Ignore
public class ZhErrorWordTest {

    @Test
    public void formatErrorWordsTest() {
        final String o = "D:\\_github\\word-checker\\src\\test\\resources\\backup\\600_error_words_v1.txt";
        final String t = "D:\\_github\\word-checker\\src\\test\\resources\\backup\\600_error_words_v2.txt";

        List<String> lines = FileUtil.readAllLines(o);
        List<String> results = Guavas.newArrayList();

        final String prefix = "（";
        for(String line : lines) {
            String trim = StringUtil.trim(line);

            String[] entries = trim.split(" ");
            for(String phrase : entries) {
                int errorIndex = phrase.indexOf(prefix);
                if(errorIndex <= 0) {
                    System.out.println(phrase);
                }

                String right = phrase.substring(0, errorIndex) + phrase.substring(errorIndex+2);
                String error = phrase.substring(0, errorIndex-1)+phrase.substring(errorIndex);

                String result = error + " " + right;
                results.add(result);
            }
        }

        FileUtil.write(t, results);
    }

    @Test
    public void formatErrorWords2Test() {
        final String o = "D:\\_github\\word-checker\\src\\test\\resources\\backup\\four_word_v1.txt";
        final String t = "D:\\_github\\word-checker\\src\\test\\resources\\backup\\four_word_v2.txt";

        List<String> lines = FileUtil.readAllLines(o);
        List<String> results = Guavas.newArrayList();

        final String prefix = "(";
        for(String line : lines) {
            String trim = StringUtil.trim(line);
            if(trim.length() < 4) {
                continue;
            }

            char[] chars = line.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                // 输出重复词
                if(i > 0) {
                    char preChar = chars[i-1];
                    if(preChar == ch) {
                        System.out.println(line);
                    }
                }
            }

            int errorIndex = line.indexOf(prefix);
            if(errorIndex <= 0) {
                System.out.println(line);
            }

            //错误：前面+【正确的词】（错误）+后面
            // 正确：前面+【正确的词】+（）+后面
            String right = line.substring(0, errorIndex) + line.substring(errorIndex+3);
            String error = line.substring(0, errorIndex-1)+line.substring(errorIndex+1).replace(")","");

            String result = error + " " + right;
            results.add(result);
        }

        FileUtil.write(t, results);
    }

    /**
     * 括号中正确
     */
    @Test
    public void insideRightTest() {
        final String o = "D:\\_github\\word-checker\\src\\test\\resources\\backup\\1000_word_v1.txt";
        final String t = "D:\\_github\\word-checker\\src\\test\\resources\\backup\\1000_word_v2.txt";

        List<String> lines = FileUtil.readAllLines(o);
        List<String> results = Guavas.newArrayList();

        final String prefix = "(";
        for(String line : lines) {
            String trim = StringUtil.trim(line);
            if(trim.length() < 4) {
                continue;
            }

            char[] chars = line.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                // 输出重复词
                if(i > 0) {
                    char preChar = chars[i-1];
                    if(preChar == ch) {
                        System.out.println(line);
                    }
                }
            }

            int errorIndex = line.indexOf(prefix);
            if(errorIndex <= 0) {
                System.out.println(line);
            }

            String error = line.substring(0, errorIndex) + line.substring(errorIndex+3);
            String right = line.substring(0, errorIndex-1)+line.substring(errorIndex+1).replace(")","");

            String result = error + " " + right;
            results.add(result);
        }

        FileUtil.write(t, results);
    }

    @Test
    public void distinctAndSort() {
        final String o = "D:\\_github\\word-checker\\src\\test\\resources\\backup\\all_in_one.txt";
        final String t = "D:\\_github\\word-checker\\src\\test\\resources\\backup\\all_in_one_distinct_sort.txt";

        List<String> lines = FileUtil.readAllLines(o);
        lines = CollectionUtil.distinctAndSort(lines);
        FileUtil.write(t, lines);
    }

}
