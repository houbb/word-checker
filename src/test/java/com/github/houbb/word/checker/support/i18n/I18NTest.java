/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. houbinbin Inc.
 * word-checker All rights reserved.
 */

package com.github.houbb.word.checker.support.i18n;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-02 11:26  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since 0.0.1
 */
public class I18NTest {

    @Test
    public void i18nTest() {
        Locale.setDefault(Locale.CHINA);
        Assert.assertEquals("英语原始数据文件加载失败", I18N.get("english_data_file_load_failed"));
        Locale.setDefault(Locale.ENGLISH);
        Assert.assertEquals("english data file load failed", I18N.get("english_data_file_load_failed"));
    }

}
