package com.thechicks.conditionform;

import com.thechicks.conditionform.util.TimeUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by opklnm102 on 2016-04-11.
 */
public class TimeUtilsUnitTest {

    //Todo: test timestamp 직접 구해야함. 홈페이지가 아니라 직접 Calendar클래스로 구해봐야 함..

//    @Test
//    public void todayUnixTimeStamp_isCorrect() throws Exception {
//
//        long standard = 1461423600L;  // 2016년 4월 24일
//
//        long test = TimeUtils.getTodayUnixTimeStamp();
//
//        assertEquals(standard, test);
//    }

    @Test
    public void tomorrowUnixTimeStamp_isCorrect() throws Exception {

        long expected = 1461596400L;

        long today = 1461510000L;

        long test = TimeUtils.getTomorrowUnixTimeStamp(today);

        assertEquals(expected, test);
    }

    @Test
    public void yesterdayUnixTimeStamp_isCorrect() throws Exception {

        long expected = 1461596400L;  // 2016년 4월 23일

        long today = 1461682800L;

        long test = TimeUtils.getYesterdayUnixTimeStamp(today);

        assertEquals(expected, test);
    }


    @Test
    public void unixTimeStampToStringDate_isCorrect() throws Exception {

        String expected = "2016-04-26";

        long today = 1461596400L;

        String test = TimeUtils.unixTimeStampToStringDate(today);

        assertEquals(expected, test);
    }

    @Test
    public void unixTimeStampToStringDateYearMonthDay_isCorrect() throws Exception {

        String expected = "2016년 04월 25일";

        long today = 1461510000L;

        String test = TimeUtils.unixTimeStampToStringDateYearMonthDay(today);

        assertEquals(expected, test);
    }

    @Test
    public void UnixTimeStampToStringTime_isCorrect() throws Exception {

        long standard = 1462802268L;

        String expected = "22 : 57";

        String test = TimeUtils.unixTimeStampToStringTime(standard);

        assertEquals(expected, test);
    }

    @Test
    public void afterDayUnixTimeStamp_isCorrect() throws Exception {

        long expected = 1461682800L;

        long today = 1461510000L;

        long test = TimeUtils.getAfterDayUnixTimeStamp(today, 2);

        assertEquals(expected, test);
    }

    @Test
    public void getDayTimeStamp_isCorrect() throws Exception {

        long standard = 1461596400L;  // 2016년 4월 26일

        long test = TimeUtils.getDayTimeStamp(2016, 4, 26);

        assertEquals(standard, test);
    }

    @Test
    public void timestampToYear_isCorrect() throws Exception {

        long standard = 1461596400L;  // 2016년 4월 26일

        int expected = 2016;

        int test = TimeUtils.timestampToYear(standard);

        assertEquals(expected, test);
    }

    @Test
    public void timestampToMonth_isCorrect() throws Exception {

        long standard = 1461596400L;  // 2016년 4월 26일

        int expected = 4;

        int test = TimeUtils.timestampToMonth(standard);

        assertEquals(expected, test);
    }

    @Test
    public void timestampToDay_isCorrect() throws Exception {

        long standard = 1461596400L;  // 2016년 4월 26일

        int expected = 26;

        int test = TimeUtils.timestampToDay(standard);

        assertEquals(expected, test);
    }

    @Test
    public void timestampToHour_isCorrect() throws Exception {

        long standard = 1462802268L;

        int expected = 22;

        int test = TimeUtils.timestampToHour(standard);

        assertEquals(expected, test);
    }

    @Test
    public void timestampToMinute_isCorrect() {
        long standard = 1462802268L;

        int expected = 57;

        int test = TimeUtils.timestampToMinute(standard);

        assertEquals(expected, test);

    }
}
