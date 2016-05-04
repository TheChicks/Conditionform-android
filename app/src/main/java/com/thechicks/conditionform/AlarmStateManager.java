package com.thechicks.conditionform;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016-05-02.
 */
public class AlarmStateManager extends BroadcastReceiver {

    private static final String DEFAULT_SNOOZE_MINUTES = "10";

    public static void registerNextAlarmWithAlarmManager(Context context, AlarmInstance instance) {

        //알람 등록
        Intent intent = new Intent(context, AlarmStateManager.class);

        long triggerTime = 0;
        long intervalTime = 24 * 60 * 60 * 1000;  //24시간

        boolean isRepeat = false;

        if(isRepeat){

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
        }
    }

    private long setTriggerTime(AlarmInstance instance){

        long intervalTime = 24 * 60 *60 * 1000;  //24시간

        long triggerTime = 0 ;


        return triggerTime;
    }


    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
