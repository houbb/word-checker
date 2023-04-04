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
        // 以  good 拼写为例子
        dtoList.add(CandidateDto.newInstance().word("good").count(2).editDistance(0));
        dtoList.add(CandidateDto.newInstance().word("god").count(2).editDistance(1));
        dtoList.add(CandidateDto.newInstance().word("goods").count(3).editDistance(1));
        dtoList.add(CandidateDto.newInstance().word("mood").count(10).editDistance(1));
        dtoList.add(CandidateDto.newInstance().word("golds").count(3).editDistance(2));
        Collections.sort(dtoList);

//        System.out.println(dtoList);

        Assert.assertEquals("good", dtoList.get(0).word());

        //TODO: 验证排序的结果
    }

}
