package com.thechicks.conditionform.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dong on 2016-05-13.
 */
public class PreferencesUtils {

    public static void setPreferences(Context context, String key, Long value) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void setPreferences(Context context, String key, Boolean value) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean getPreferencesBoolean(Context context, String key){
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }

    public static Long getPreferencesLong(Context context, String key){
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        return pref.getLong(key, TimeUtils.getCurrentUnixTimeStamp());
    }
}
