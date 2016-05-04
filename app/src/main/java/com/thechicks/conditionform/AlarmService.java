package com.thechicks.conditionform;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class AlarmService extends Service {

    public static final String TAG = AlarmService.class.getSimpleName();

    //
    public static final String ALARM_START_ACTION = "com.thechicks.conditionform.ALARM_START";


    public static final String ALARM_STOP_ACTION = "com.thechicks.conditionform.ALARM_STOP";


    public static final String ALARM_ALERT_ACTION = "com.thechicks.conditionform.ALARM_ALERT";


    public static final String ALARM_DONE_ACTION = "com.thechicks.conditionform.ALARM_DONE";



    public AlarmService() {
    }

    public static void startAlarm(Context context, AlarmInstance instance){
//        Intent intent =
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
