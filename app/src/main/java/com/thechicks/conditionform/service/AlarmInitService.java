package com.thechicks.conditionform.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.thechicks.conditionform.data.model.Alarm;
import com.thechicks.conditionform.alert.AlarmAlertReceiver;
import com.thechicks.conditionform.data.model.AlarmInstance;

public class AlarmInitService extends Service {

    public static final String TAG = AlarmInitService.class.getSimpleName();

    //
//    public static final String ALARM_START_ACTION = "com.thechicks.conditionform.ALARM_START";
//
//    public static final String ALARM_STOP_ACTION = "com.thechicks.conditionform.ALARM_STOP";
//
//    public static final String ALARM_ALERT_ACTION = "com.thechicks.conditionform.ALARM_ALERT";
//
//    public static final String ALARM_DONE_ACTION = "com.thechicks.conditionform.ALARM_DONE";



    public AlarmInitService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, " onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, " onStartCommand()");
//        Alarm alarm = getNext();
        Alarm alarm = new Alarm();

        if(alarm != null){
            //Alarm이 없으면 새로 등록
            AlarmAlertReceiver.registerNextAlarmWithAlarmManager(getApplicationContext(), new AlarmInstance());
        }else {
            //Alarm이 있으면 삭제
            AlarmAlertReceiver.cancelScheduledInstance(getApplicationContext(), new AlarmInstance());
        }
        return START_NOT_STICKY;
    }

    private Alarm getNext(){
//        Set<Alarm> alarmQueue = new TreeSet<Alarm>(new Comparator<Alarm>() {
//            @Override
//            public int compare(Alarm lhs, Alarm rhs) {
//                int result = 0;
//                long diff = lhs.
//            }
//        })
        return null;
    }



    public static void startAlarm(Context context, AlarmInstance instance){
//        Intent intent =
    }


}
