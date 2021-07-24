/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. houbinbin Inc.
 * word-checker All rights reserved.
 */

package com.github.houbb.word.checker.support.dto;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-02 13:59  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.8
 */
public class CandidateDtoTest {

    @Test
    public void sortTest() {
        List<CandidateDto> dtoList = new LinkedList<>();
        dtoList.add(CandidateDto.of("hello", 2L));
        dtoList.add(CandidateDto.of("word", 12L));
        dtoList.add(CandidateDto.of("the", 6L));
        Collections.sort(dtoList);
        long count = dtoList.get(0).getCount();
        Assert.assertEquals(12L, count);
    }

}
