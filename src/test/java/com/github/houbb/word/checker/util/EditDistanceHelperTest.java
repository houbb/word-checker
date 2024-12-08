package com.github.houbb.word.checker.util;

import org.junit.Assert;
import org.junit.Test;

public class EditDistanceHelperTest {

    @Test
    public void minDistanceTest() {
        Assert.assertEquals(3, EditDistanceHelper.minDistance("horse", "ros"));
        Assert.assertEquals(5, EditDistanceHelper.minDistance("intention", "execution"));
    }

    @Test
    public void minDistanceListTest() {
        Assert.assertEquals("[horse, hors, hos, ros]", EditDistanceHelper.minDistanceList("horse", "ros").toString());
        Assert.assertEquals("[intention, intenution, intecution, inecution, ixecution, execution]", EditDistanceHelper.minDistanceList("intention", "execution").toString());
    }

}
