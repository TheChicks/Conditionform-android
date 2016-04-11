package com.thechicks.conditionform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2016-04-11.
 */
public class TimeUtilsUnitTest {

    @Test
    public void toDayUnixTimeStamp_isCorrect() throws Exception {

        long standTimeStamp = 1460332800L;  // 2016년 4월 11일

        long testTimeStamp = TimeUtils.getTodayUnixTimeStamp();

        assertEquals(standTimeStamp, standTimeStamp);
    }



}
