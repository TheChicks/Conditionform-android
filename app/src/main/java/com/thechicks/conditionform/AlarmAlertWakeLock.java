package com.thechicks.conditionform;

import android.content.Context;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by opklnm102 on 2016-04-30.
 */
public class AlarmAlertWakeLock {

    private static PowerManager.WakeLock sWakeLock = null;

    // cpu lock
    public static void acquireCpuWakeLock(Context context){
        if(sWakeLock != null){
            return;
        }

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        sWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "wakelock new");
        sWakeLock.acquire();
    }

    // cpu, screen lock
    public static void acquireScreenCpuWakeLock(Context context){
        if(sWakeLock != null){
            return;
        }

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        sWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "wakelock screen cpu");
//        sWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "wakelock screen cpu");

        sWakeLock.acquire();
    }

    // lock cancel
    public static void releaseCpuLock(){
        if(sWakeLock != null){
            sWakeLock.release();
            sWakeLock = null;
        }
    }
}
