/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. houbinbin Inc.
 * word-checker All rights reserved.
 */

package com.github.houbb.word.checker.support.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p> I18N 支持 </p>
 *
 * <pre> Created: 2018-05-02 11:24  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since 0.0.1
 */
public class I18N {

    private static final String DEFAULT_PROPERTIES_FILE_NAME = "i18n.WordCheckerMessages";

    public static String get(final String key) {
        Locale currentLocale = Locale.getDefault();
        ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_PROPERTIES_FILE_NAME, currentLocale);
        return myResources.getString(key);
    }

}
