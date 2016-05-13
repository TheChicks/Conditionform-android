package com.thechicks.conditionform.util;

import android.preference.PreferenceFragment;
import android.provider.BaseColumns;

import com.thechicks.conditionform.BuildConfig;

/**
 * Created by opklnm102 on 2016-04-29.
 */
public class Constants {

    // geneymotion localhost
//    public static final String ENDPOINT = BuildConfig.LOCAL_HOST_IP;

    // api server
    public static final String ENDPOINT = BuildConfig.API_SERVER_IP;

    //SharedPreference
    public static final String PREF_NAME = "conditionform_settings";
    public static final String PREF_VIBRATE = "pref_vibrate";
    public static final String PREF_SOUND = "perf_sound";
    public static final String PREF_TIME_MORNING = "perf_time_morning";
    public static final String PREF_TIME_LUNCH = "perf_time_lunch";
    public static final String PREF_TIME_EVENING = "perf_time_evening";

    //SQLite
    //병
    public static final class DiseaseEntray implements BaseColumns {

        public static final String TABLE_NAME = "disease";

        public static final String COLUMN_DISEASE_NAME = "disease_name";  //TEXT, 이름
        public static final String COLUMN_DISEASE_LABEL_COLOR = "disease_label_color";  //TEXT, 레이블 색
        public static final String COLUMN_DISEASE_DATE_START = "disease_date_start";  //NUMERIC(datetime), 시작 날짜
        public static final String COLUMN_DISEASE_DATE_END = "disease_date_end";  //NUMERIC(datetime), 끝 날짜
        public static final String COLUMN_DISEASE_DOSAGE_TYPE = "disease_dosage_type";  //INTEGER, 복용 타입
        public static final String COLUMN_DISEASE_DOSAGE_ONE_TIME = "disease_dosage_one_time";  //INTEGER, 1회 투여량
        public static final String COLUMN_DISEASE_DOSAGE_TOTAL = "disease_dosage_total";  //INTEGER, 총 투여 횟수
        public static final String COLUMN_DISEASE_DOSAGE_TOTAL_DAYS = "disease_dosage_total_days";  //INTEGER, 투약 일수

        public static final String COLUMN_DISEASE_CREATEDAT = "disease_createdAt";  //NUMERIC(datetime), 컬럼 생성날짜
        public static final String COLUMN_DISEASE_UPDATEAT = "disease_updateAt";  //NUMERIC(datetime), 컬럼 업데이트 날짜
    }

    //히스토리
    public static final class HistoryEntray implements BaseColumns {

        public static final String TABLE_NAME = "history";

        public static final String COLUMN_HISTORY_DATE = "history_date";  //NUMERIC(datetime),날짜
        public static final String COLUMN_HISTORY_CHECK_MORNING = "history_check_morning";  //NUMERIC(boolean), 아침에 먹었나
        public static final String COLUMN_HISTORY_CHECK_LUNCH = "history_check_lunch";  //NUMERIC(boolean), 점심에 먹었나
        public static final String COLUMN_HISTORY_CHECK_EVENING = "history_check_evening";  //NUMERIC(boolean), 저녁에 먹었나
        public static final String COLUMN_HISTORY_CHECK_SLEEP = "history_check_sleep";  //NUMERIC(boolean), 잠자기전에 먹었나
        public static final String COLUMN_HISTORY_CHECK_WAKEUP = "history_check_wakeup";  //NUMERIC(boolean), 일어나서 먹었나
        public static final String COLUMN_HISTORY_CHECK_CURRENT = "history_check_current";  //INTEGER, 현재 얼마나 먹었나.

        public static final String COLUMN_DISEASE_CREATEDAT = "history_createdAt";  //NUMERIC(datetime), 컬럼 생성날짜
        public static final String COLUMN_DISEASE_UPDATEAT = "history_updateAt";  //NUMERIC(datetime), 컬럼 업데이트 날짜
    }

    //알람
    public static final class AlarmEntray implements BaseColumns {

        public static final String TABLE_NAME = "alarm";

        public static final String COLUMN_ALARM_ENABLED = "alarm_enabled";  //NUMERIC(boolean), 알람 활성 여부
        public static final String COLUMN_ALARM_VIBRATE = "alarm_vibrate";  //NUMERIC(boolean), 진동여부(필요없을듯)
        public static final String COLUMN_ALARM_RINGTONE = "alarm_ringtone";  //NUMERIC(boolean), 소리여부(필요없을듯)
        public static final String COLUMN_ALARM_RINGTONE_PATH = "alarm_ringtone_path";  //TEXT, 소리 PATH
        public static final String COLUMN_ALARM_TIME = "alarm_time";  //NUMERIC(datetime), 울릴 시간
        public static final String COLUMN_ALARM_DAY = "alarm_day";  //NUMERIC(datetime), 울릴 날짜

        public static final String COLUMN_ALARM_CREATEDAT = "alarm_createdAt";  //NUMERIC(datetime),컬럼 생성날짜
        public static final String COLUMN_ALARM_UPDATEAT = "alarm_updateAt";  //NUMERIC(datetime),컬럼 업데이트 날짜

    }

    public static final class PillEntray implements BaseColumns {

        public static final String TABLE_NAME = "pill";

        public static final String COLUMN_PILL_NAME = "pill_name";  //TEXT, 이름
        //Todo: 컬럼 정의


        public static final String COLUMN_Pill_CREATEDAT = "pill_createdAt";  //NUMERIC(datetime), 컬럼 생성날짜
        public static final String COLUMN_Pill_UPDATEAT = "pill_updateAt";  //NUMERIC(datetime), 컬럼 업데이트 날짜


    }
}
