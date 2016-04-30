package com.thechicks.conditionform;

import android.content.Context;
import android.os.PowerManager;

/**
 * Created by Administrator on 2016-04-30.
 */
public class AlarmAlertWakeLock {

    private static PowerManager.WakeLock sWakeLock;

    public static PowerManager.WakeLock createPartialWakeLock(Context context){
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        return pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "wakelock new");
    }

    public static void acquireCpuWakeLock(Context context){
        if(sWakeLock != null){
            return;
        }

        sWakeLock = createPartialWakeLock(context);
        sWakeLock.acquire();
    }

    public static void acquireScreenCpuWakeLock(Context context){
        if(sWakeLock != null){
            return;
        }

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        sWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "wakelock screen cpu");
        sWakeLock.acquire();
    }

    public static void releaseCpuLock(){
        if(sWakeLock != null){
            sWakeLock.release();
            sWakeLock = null;
        }
    }
}
