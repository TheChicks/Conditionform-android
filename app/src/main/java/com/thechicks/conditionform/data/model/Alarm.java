package com.thechicks.conditionform.data.model;

import android.media.RingtoneManager;
import android.net.Uri;

import java.io.Serializable;

/**
 * Created by opklnm102 on 2016-04-28.
 */
public class Alarm implements Serializable {

    public static final long INVALID_ID = -1;

    public enum Day {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY;

        @Override
        public String toString() {
            switch (this.ordinal()) {
                case 0:
                    return "Sunday";
                case 1:
                    return "Monday";
                case 2:
                    return "Tuesday";
                case 3:
                    return "Wednesday";
                case 4:
                    return "Thursday";
                case 5:
                    return "Friday";
                case 6:
                    return "Saturday";
            }
            return super.toString();
        }
    }


    long id;
    boolean enabled;
    int hour;
    int minutes;

    int daysOfWeek;  //요일. 0(no days set) 1(일) 2(월) 3(화) 4(수) 5(목) 6(금) 7(토), Calendar처럼 쓰자.
    Day[] days = { Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};

    boolean vibrate;  //진동 여부
    String label;
    Uri alert;  //알림음
    boolean deleteAfterUse;  //알람울리고 삭제여부


    public Alarm() {
//        this(0, 0);
    }

    public Alarm(int hour, int minutes) {
        this.id = INVALID_ID;
        this.hour = hour;
        this.minutes = minutes;
        this.vibrate = true;
        this.daysOfWeek = 0;
        this.label = "";
        this.alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        this.deleteAfterUse = false;
    }
}
