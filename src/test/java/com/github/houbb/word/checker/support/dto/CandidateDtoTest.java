/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
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
        dtoList.add(CandidateDto.builder().word("hello").count(2).build());
        dtoList.add(CandidateDto.builder().word("word").count(12).build());
        dtoList.add(CandidateDto.builder().word("the").count(6).build());
        Collections.sort(dtoList);
        Assert.assertEquals(12, dtoList.get(0).getCount());
    }

}
