package com.thechicks.conditionform;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by opklnm102 on 2016-05-02.
 */
public class AlarmAlertBroadcastReceiver extends BroadcastReceiver {

    private static final String DEFAULT_SNOOZE_MINUTES = "10";

    public static void registerNextAlarmWithAlarmManager(Context context, AlarmInstance instance) {

        //알람 등록
//        Intent intent = new Intent(context, AlarmAlertBroadcastReceiver.class);
//
//        long triggerTime = 0;
//        long intervalTime = 24 * 60 * 60 * 1000;  //24시간
//
//        boolean isRepeat = false;
//
//        if(isRepeat){
//
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
//        }

        Intent intent = new Intent(context, AlarmAlertBroadcastReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        int alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;
        final int FIFTEEN_SEC_MILLIS = 10000;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        alarmManager.setRepeating(alarmType, 10000, FIFTEEN_SEC_MILLIS, pendingIntent );



    }

    private long setTriggerTime(AlarmInstance instance){

        long intervalTime = 24 * 60 *60 * 1000;  //24시간

        long triggerTime = 0 ;


        return triggerTime;
    }


    @Override
    public void onReceive(Context context, Intent intent) {


//        Intent alarmIntent = new Intent(context, AlarmAlertActivity.class);
//        Intent mathAlarmServiceIntent = new Intent(context, Alarm )

        AlarmAlertWakeLock.acquireScreenCpuWakeLock(context);
        Bundle bundle = intent.getExtras();
        final Alarm alarm = (Alarm) bundle.getSerializable("alarm");

        Intent alarmActivityIntent = new Intent(context, AlarmAlertActivity.class);
        alarmActivityIntent.putExtra("alarm", alarm);

        alarmActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(alarmActivityIntent);
    }
}
