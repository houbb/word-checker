package com.github.houbb.word.checker.util;

import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.word.checker.core.impl.EnWordChecker;
import com.github.houbb.word.checker.exception.WordCheckException;
import com.github.houbb.word.checker.support.i18n.I18N;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-02 09:57  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @version 0.0.1
 * @since 0.0.1
 */
public final class EnWordUtil {

    private static final Map<String, Integer> WORD_MAP = new HashMap<>();

    private static final Log log = LogFactory.getLog(EnWordUtil.class);

    static {
        try {
            InputStream is = EnWordChecker.class.getResourceAsStream("/data/en/data.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(is,
                    StandardCharsets.UTF_8));
            Pattern p = Pattern.compile("\\w+");
            for (String temp = ""; temp != null; temp = in.readLine()) {
                Matcher m = p.matcher(temp.toLowerCase());
                while (m.find()) {
                    WORD_MAP.put((temp = m.group()), WORD_MAP.containsKey(temp) ? WORD_MAP.get(temp) + 1 : 1);
                }
            }
        } catch (IOException e) {
            log.error("Load data meet ex: ", e);
            throw new WordCheckException(I18N.get("english_data_file_load_failed"));
        }
    }

    /**
     * 获取单词 MAP
     * @return 单词MAP
     */
    public static Map<String, Integer> getWordMap() {
        return WORD_MAP;
    }

}
