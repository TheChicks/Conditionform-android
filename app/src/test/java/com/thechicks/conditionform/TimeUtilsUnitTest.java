package com.thechicks.conditionform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2016-04-11.
 */
public class TimeUtilsUnitTest {

    @Test
    public void todayUnixTimeStamp_isCorrect() throws Exception {

//        long standard = 1460678400L;  // 2016년 4월 15일

        long standard = TimeUtils.getCurrentUnixTimeStamp();

        long test = TimeUtils.getTodayUnixTimeStamp();

        assertEquals(standard, test);
    }

    @Test
    public void tomorrowUnixTimeStamp_isCorrect() throws Exception {

        long standard = 1460764800L;  // 2016년 4월 16일

        long today = TimeUtils.getTodayUnixTimeStamp();

        long test = TimeUtils.getTomorrowUnixTimeStamp(today);

        assertEquals(standard, test);
    }

    @Test
    public void yesterdayUnixTimeStamp_isCorrect() throws Exception {

        long standard = 1460592000L;  // 2016년 4월 14일

        long today = TimeUtils.getTodayUnixTimeStamp();

        long test = TimeUtils.getYesterdayUnixTimeStamp(today);

        assertEquals(standard, test);
    }



    @Test
    public void unixTimeStampToStringDate_isCorrect() throws Exception {

        String standard = "2016-04-15";

        long today = TimeUtils.getTodayUnixTimeStamp();

        String test = TimeUtils.UnixTimeStampToStringDate(today);

        assertEquals(standard, test);
    }

    @Test
    public void unixTimeStampToStringDateYearMonthDay_isCorrect() throws Exception {

        String standard = "2016년 04월 15일";

        long today = TimeUtils.getTodayUnixTimeStamp();

        String test = TimeUtils.UnixTimeStampToStringDateYearMonthDay(today);

        assertEquals(standard, test);
    }

    @Test
    public void unixTimeStampToStringDateMonthDay_isCorrect() throws Exception {

        String standard = "4월 15일";

        long today = TimeUtils.getTodayUnixTimeStamp();

        String test = TimeUtils.UnixTimeStampToStringDateMonthDay(today);

        assertEquals(standard, test);
    }
}
