package com.github.houbb.word.checker.data;

import com.github.houbb.word.checker.support.data.english.EnglishWordDatas;
import org.junit.Test;

import java.util.Map;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class EnWordTest {

    @Test
    public void oneWordTest() {
        Map<String, Long> freqData = EnglishWordDatas.mixed().freqData();
        for(String key : freqData.keySet()) {
            if(key.length() == 1) {
                System.out.println(key+","+freqData.get(key));
            }
        }
    }

}
