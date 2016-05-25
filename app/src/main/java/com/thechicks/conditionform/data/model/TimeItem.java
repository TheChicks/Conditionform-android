package com.thechicks.conditionform.data.model;

import com.thechicks.conditionform.util.TimeUtils;

/**
 * Created by Administrator on 2016-05-09.
 */
public class TimeItem {

    public static final int TYPE_WAKEUP = 0;
    public static final int TYPE_MORNING = 1;
    public static final int TYPE_LUNCH = 2;
    public static final int TYPE_DINNER = 3;
    public static final int TYPE_SLEEP = 4;

    int type;
    long time;

    boolean enabled = false;

    public TimeItem() {
    }

    public TimeItem(long time) {
        this.type = determineType(time);
        this.time = time;
    }

    public TimeItem(long time, boolean enabled) {
        this.type = determineType(time);
        this.time = time;
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        this.type = determineType(time);
    }

    public int determineType(long time) {
        //Todo: 시간대 판단. + 분 구현해야함

        int hour = TimeUtils.timestampToHour(time);
//        int minute =  TimeUtils.timestampToMinute(time);

        int type = -1;

        if (5 <= hour && hour <= 7) {
            type = TYPE_WAKEUP;
        } else if (8 <= hour && hour < 10) {
            type = TYPE_MORNING;
        } else if (10 <= hour && hour < 16) {
            type = TYPE_LUNCH;
        } else if (16 <= hour && hour < 22) {
            type = TYPE_DINNER;
        } else {
            type = TYPE_SLEEP;
        }

        return type;
    }
}
