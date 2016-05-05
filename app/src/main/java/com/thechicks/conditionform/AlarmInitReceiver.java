package com.thechicks.conditionform;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class AlarmInitReceiver extends BroadcastReceiver {

    public static final String TAG = AlarmInitReceiver.class.getSimpleName();

    private static final String PERF_VOLUME_DEF_DONE = "vol_def_done";

    public AlarmInitReceiver() {
    }

    /**
     * Sets Alarm on ACTION_BOOT_COMPLETED.
     * Todo: Resets alarm on TIME_SET, TIMEZONE_CHANGED
     */
    @Override

    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, " onReceive()");

        Intent serviceIntent = new Intent(context, AlarmInitService.class);
        context.startService(serviceIntent);


//        final String action = intent.getAction();
//
//        PendingResult result = goAsync();  //Broadcast Receiver에서 background작업을 할 수 있게
//
//        //Alarm 초기화가 끝날 때까지 CPU가 잠들지않게 잠금
//        final PowerManager.WakeLock wakeLock = AlarmAlertWakeLock.createPartialWakeLock(context);
//        wakeLock.acquire();
//
//        if(action.equals(Intent.ACTION_BOOT_COMPLETED)){
//
//            //Update all the alarm instances on time change event
//
//
//
//            result.finish();
//            wakeLock.release();
//        }


    }
}
