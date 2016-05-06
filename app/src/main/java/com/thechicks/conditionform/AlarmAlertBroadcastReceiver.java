package com.thechicks.conditionform;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by opklnm102 on 2016-05-02.
 */
public class AlarmAlertBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = AlarmAlertBroadcastReceiver.class.getSimpleName();

    private static final String DEFAULT_SNOOZE_MINUTES = "10";

    //알람 등록
    public static void registerNextAlarmWithAlarmManager(Context context, AlarmInstance instance) {

        Log.d(TAG, "registerNextAlarmWithAlarmManager()");

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmAlertBroadcastReceiver.class);

        long triggerTime = TimeUtils.getCurrentUnixTimeStamp() * 1000;
        final long intervalTime = 24 * 60 * 60 * 1000;  //24시간

        boolean isRepeat = true;

        if(isRepeat){
            //매일 반복
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent,  PendingIntent.FLAG_UPDATE_CURRENT);

            int alarmType = AlarmManager.RTC_WAKEUP;

            alarmManager.setRepeating(alarmType, triggerTime, intervalTime, pendingIntent );
        }else {
            //반복 안함

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            int alarmType = AlarmManager.RTC_WAKEUP;

            alarmManager.set(alarmType, triggerTime, pendingIntent);

        }
    }

    //알람 취소
    public static void cancelScheduledInstance(Context context, AlarmInstance instance){
        Log.d(TAG, "cancelScheduledInstance()");

        Intent myIntent = new Intent(context, AlarmAlertBroadcastReceiver.class);
        myIntent.putExtra("alarm", new Alarm());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, instance.hashCode(), myIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pendingIntent);
    }

    private long setTriggerTime(AlarmInstance instance){

        long intervalTime = 24 * 60 *60 * 1000;  //24시간

        long triggerTime = 0 ;


        return triggerTime;
    }


    @Override
    public void onReceive(Context context, Intent intent) {


//        Intent alarmInitServiceIntent = new Intent(context, AlarmInitReceiver.class);
//        context.sendBroadcast(alarmInitServiceIntent, null);

        AlarmAlertWakeLock.acquireScreenCpuWakeLock(context);
        Bundle bundle = intent.getExtras();
        final Alarm alarm = (Alarm) bundle.getSerializable("alarm");

        Intent alarmActivityIntent = new Intent(context, AlarmAlertActivity.class);
        alarmActivityIntent.putExtra("alarm", alarm);

        alarmActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(alarmActivityIntent);
    }
}
