package com.thechicks.conditionform.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.thechicks.conditionform.util.Constants;

/**
 * Created by opklnm102 on 2016-05-13.
 */
public class ConditionformDbHelper extends SQLiteOpenHelper {

    public static final String TAG = ConditionformDbHelper.class.getSimpleName();

    private static ConditionformDbHelper instance;
    private static SQLiteDatabase db;

    private static final int VER_RELEASE_A = 0x01;  //app version 1.0.0
    private static final int CUR_DATABASE_VERSION = VER_RELEASE_A;

    private static final String DATABASE_NAME = "conditionform.db";

    //double check
    // 생성 시점에만 동기화를 적용하고, 생성 이후에는 동기화를 제거하는 방법.
    public static ConditionformDbHelper getInstance(Context context){
        if(instance == null){
            synchronized(ConditionformDbHelper.class){
                if(instance == null){
                    instance = new ConditionformDbHelper(context);
                    db = instance.getWritableDatabase();
                }
            }
        }
        return instance;
    }


    public ConditionformDbHelper(Context context) {
        super(context, DATABASE_NAME, null, CUR_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //table 생성 sql문 정의
        final String SQL_CREATE_HISTORY_TABLE = "CREATE TABLE " + Constants.HistoryEntray.TABLE_NAME + " (" +
                Constants.HistoryEntray._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.HistoryEntray.COLUMN_HISTORY_DATE + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_WAKEUP + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_MORNING + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_LUNCH + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_EVENING + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_SLEEP + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_ENABLED_WAKEUP + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_ENABLED_MORNING + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_ENABLED_LUNCH + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_ENABLED_EVENING + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_ENABLED_SLEEP + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_CURRENT + " INTEGER, " +
                Constants.HistoryEntray.COLUMN_HISTORY_TIME_START_HOUR + " INTEGER, " +
                Constants.HistoryEntray.COLUMN_HISTORY_TIME_START_MINUTE + " INTEGER, " +
                Constants.HistoryEntray.COLUMN_HISTORY_TIME_INTERVAL + " INTEGER, " +
                Constants.HistoryEntray.COLUMN_HISTORY_CREATEDAT + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_UPDATEAT + " NUMERIC " +
                " );";

        final String SQL_CREATE_ALARM_TABLE = "CREATE TABLE " + Constants.AlarmEntray.TABLE_NAME + " (" +
                Constants.AlarmEntray._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.AlarmEntray.COLUMN_ALARM_ENABLED + " NUMERIC, " +
                Constants.AlarmEntray.COLUMN_ALARM_RINGTONE_PATH + " TEXT, " +
                Constants.AlarmEntray.COLUMN_ALARM_TIME + " NUMERIC, " +
                Constants.AlarmEntray.COLUMN_ALARM_DATE + " NUMERIC, " +
                Constants.AlarmEntray.COLUMN_ALARM_CREATEDAT + " NUMERIC, " +
                Constants.AlarmEntray.COLUMN_ALARM_UPDATEAT + " NUMERIC " +
                " );";

        final String SQL_CREATE_PILL_TABLE = "CREATE TABLE " + Constants.PillEntray.TABLE_NAME + " (" +
                Constants.PillEntray._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.PillEntray.COLUMN_PILL_NAME + " TEXT, " +
                Constants.PillEntray.COLUMN_Pill_CREATEDAT + " NUMERIC, " +
                Constants.PillEntray.COLUMN_Pill_UPDATEAT + " NUMERIC " +
                //Todo: column 추가
                " );";

        final String SQL_CREATE_DISEASE_TABLE = "CREATE TABLE " + Constants.DiseaseEntray.TABLE_NAME + " (" +
                Constants.DiseaseEntray._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_NAME + " TEXT NOT NULL, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR + " INTEGER NOT NULL, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_DATE_START + " NUMERIC NOT NULL, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_DATE_END + " NUMERIC NOT NULL, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE + " INTEGER NOT NULL, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_ONE_TIME + " INTEGER NOT NULL, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL + " INTEGER NOT NULL, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL_DAYS + " INTEGER NOT NULL, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_CREATEDAT + " NUMERIC, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_UPDATEAT + " NUMERIC, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_FK_HISTORY_ID + " INTEGER, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_FK_ALARM_ID + " INTEGER, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_FK_PILL_ID + " INTEGER, " +
                " FOREIGN KEY (" + Constants.DiseaseEntray.COLUMN_DISEASE_FK_HISTORY_ID + ") REFERENCES " +
                Constants.HistoryEntray.TABLE_NAME + " (" + Constants.HistoryEntray._ID + ")," +
                " FOREIGN KEY (" + Constants.DiseaseEntray.COLUMN_DISEASE_FK_ALARM_ID + ") REFERENCES " +
                Constants.AlarmEntray.TABLE_NAME + " (" + Constants.AlarmEntray._ID + ")," +
                " FOREIGN KEY (" + Constants.DiseaseEntray.COLUMN_DISEASE_FK_PILL_ID + ") REFERENCES " +
                Constants.PillEntray.TABLE_NAME + " (" + Constants.PillEntray._ID + ")" +
                " );";

        //table 생성 sql 실행
        db.execSQL(SQL_CREATE_HISTORY_TABLE);
        db.execSQL(SQL_CREATE_ALARM_TABLE);
        db.execSQL(SQL_CREATE_PILL_TABLE);
        db.execSQL(SQL_CREATE_DISEASE_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TAG, " db open");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, " onUpgrade() from " + oldVersion + "to " + newVersion);

        int version = oldVersion;

        if (version != CUR_DATABASE_VERSION) {
            Log.d(TAG, "Upgrade unsuccessful -- destroying old data during upgrade");

            db.execSQL("DROP TABLE IF EXISTS " + Constants.DiseaseEntray.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Constants.HistoryEntray.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Constants.AlarmEntray.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Constants.PillEntray.TABLE_NAME);

            onCreate(db);
            version = CUR_DATABASE_VERSION;
        }
    }

    @Override
    public synchronized void close() {
        if (instance != null)
            instance = null;
        super.close();
    }

    public static void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

    //Todo: CRUD 구현

}
