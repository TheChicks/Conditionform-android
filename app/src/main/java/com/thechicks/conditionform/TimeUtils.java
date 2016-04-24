package com.thechicks.conditionform;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dong on 2015-09-20.
 */
public class TimeUtils {

    //현재시간 unixTimeStamp
    public static long getCurrentUnixTimeStamp(){
        return (System.currentTimeMillis() / 1000);
    }

    //오늘 unixTimeStamp
    public static long getTodayUnixTimeStamp(){
//        DateTime dateTime = new DateTime(DateTimeZone.getDefault());
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return (calendar.getTimeInMillis() / 1000);
    }

    //내일 unixTimeStamp
    public static long getTomorrowUnixTimeStamp(long unixTimestampToday){
        return unixTimestampToday + (24 * 60 * 60);
    }

    //어제 unixTimeStamp
    public static long getYesterdayUnixTimeStamp(long unixTimestampToday){
        return unixTimestampToday - (24 * 60 * 60);
    }

    //시의 timeStamp 구하기
    public static long getHourUnixTimeStamp(int hour){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis() / 1000;
    }

    //시, 분의 timeStamp 구하기
    public static long getHourMinuteUnixTimeStamp(int hourOfDay, int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        //Log용
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
//        String strTime = sdf.format(calendar.getTimeInMillis());  //unix timeStamp -> String
//        Log.e(TAG, "unix time " + calendar.getTimeInMillis());
//        Log.e(TAG,"unix time String " + strTime);

        return (calendar.getTimeInMillis() / 1000);
    }

    //unixTimeStamp -> Date -> String(yyyy-MM-dd)
    public static String UnixTimeStampToStringDate(long timeStamp) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
        SimpleDateFormat convertedSdf = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);

        // -9시간 되는 버그...극혐
//        sdf.setTimeZone(TimeZone.getTimeZone("KST"));
//        convertedSdf.setTimeZone(TimeZone.getTimeZone("KST"));

        String strTime = sdf.format(timeStamp * 1000);  //unix timeStamp -> String

        Date timeDate = null;  //String -> Date
        try {
            timeDate = sdf.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String convertedDate = convertedSdf.format(timeDate); //Date -> String

        return convertedDate;
    }

    //Todo: Am, Pm 처리
    //unixTimeStamp -> Date -> String(HH:mm)
    public static String UnixTimeStampToStringTime(long timeStamp) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
        SimpleDateFormat convertedSdf = new SimpleDateFormat("HH : mm",Locale.KOREA);

        String strTime = sdf.format(timeStamp * 1000);  //unix timeStamp -> String

        Date timeDate = null;  //String -> Date
        try {
            timeDate = sdf.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String convertedDate = convertedSdf.format(timeDate); //Date -> String

        return convertedDate;
    }

    //String(yyyy-MM-dd) -> unixTimeStamp
    public static long stringDayToUnixTimeStamp(String strDay){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
        Date parseDate = null;

        try {
            parseDate = sdf.parse(strDay);
            Log.d("TimeUtils", " " + parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d("TimeUtils", " " + parseDate.getTime());

        return (parseDate.getTime() / 1000);
    }

    //unixTimeStamp -> String(yyyy년 MM월 dd일)
    public static String UnixTimeStampToStringDateYearMonthDay(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp * 1000);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);

        String result;

        if(month < 10){
            result = year + "년 0" + month + "월 " + day + "일";
        }else {
            result = year + "년 " + month + "월 " + day + "일";
        }

        return result;
    }

    //unixTimeStamp -> String(MM월 dd일)
    public static String UnixTimeStampToStringDateMonthDay(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp * 1000);

        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);

        String result = month + "월 " + day + "일";

        return result;
    }

    // 해당일로부터 after만큼 후의 timestamp를 리턴
    // ex. 하루 뒤, 2일 뒤
    public static long getAfterDayUnixTimeStamp(long unixTimestampToday, int after){
        return unixTimestampToday +  (after * 24 * 60 * 60);
    }




}
