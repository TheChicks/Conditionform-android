package com.thechicks.conditionform.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public static ConditionformDbHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (ConditionformDbHelper.class) {
                if (instance == null) {
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
                Constants.DiseaseEntray.COLUMN_DISEASE_UPDATEAT + " NUMERIC " +

                /*
                Constants.DiseaseEntray.COLUMN_DISEASE_FK_HISTORY_ID + " INTEGER, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_FK_ALARM_ID + " INTEGER, " +
                Constants.DiseaseEntray.COLUMN_DISEASE_FK_PILL_ID + " INTEGER, " +
                " FOREIGN KEY (" + Constants.DiseaseEntray.COLUMN_DISEASE_FK_HISTORY_ID + ") REFERENCES " +
                Constants.HistoryEntray.TABLE_NAME + " (" + Constants.HistoryEntray._ID + ")," +
                " FOREIGN KEY (" + Constants.DiseaseEntray.COLUMN_DISEASE_FK_ALARM_ID + ") REFERENCES " +
                Constants.AlarmEntray.TABLE_NAME + " (" + Constants.AlarmEntray._ID + ")," +
                " FOREIGN KEY (" + Constants.DiseaseEntray.COLUMN_DISEASE_FK_PILL_ID + ") REFERENCES " +
                Constants.PillEntray.TABLE_NAME + " (" + Constants.PillEntray._ID + ")" +
                */
                " );";

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
                Constants.HistoryEntray.COLUMN_HISTORY_UPDATEAT + " NUMERIC, " +
                Constants.HistoryEntray.COLUMN_HISTORY_FK_DISEASE_ID + " INTEGER, " +
                " FOREIGN KEY (" + Constants.HistoryEntray.COLUMN_HISTORY_FK_DISEASE_ID + ") REFERENCES " +
                Constants.DiseaseEntray.TABLE_NAME + " (" + Constants.DiseaseEntray._ID + ")" +
                " );";

        final String SQL_CREATE_ALARM_TABLE = "CREATE TABLE " + Constants.AlarmEntray.TABLE_NAME + " (" +
                Constants.AlarmEntray._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.AlarmEntray.COLUMN_ALARM_ENABLED + " NUMERIC, " +
                Constants.AlarmEntray.COLUMN_ALARM_RINGTONE_PATH + " TEXT, " +
                Constants.AlarmEntray.COLUMN_ALARM_TIME + " NUMERIC, " +
                Constants.AlarmEntray.COLUMN_ALARM_DATE + " NUMERIC, " +
                Constants.AlarmEntray.COLUMN_ALARM_CREATEDAT + " NUMERIC, " +
                Constants.AlarmEntray.COLUMN_ALARM_UPDATEAT + " NUMERIC, " +
                Constants.AlarmEntray.COLUMN_ALARM_FK_DISEASE_ID + " INTEGER, " +
                " FOREIGN KEY (" + Constants.AlarmEntray.COLUMN_ALARM_FK_DISEASE_ID + ") REFERENCES " +
                Constants.DiseaseEntray.TABLE_NAME + " (" + Constants.DiseaseEntray._ID + ")" +
                " );";

        final String SQL_CREATE_PILL_TABLE = "CREATE TABLE " + Constants.PillEntray.TABLE_NAME + " (" +
                Constants.PillEntray._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.PillEntray.COLUMN_PILL_NAME_KOREA + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_NAME_ENGLISH + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_IMAGE_URL + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_INGREDIENT + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_ASSORTMENT + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_UNITARINESS_OR_COMPLEXNESS + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_MANUFACTURE_ASSORTMENT + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_SELLER + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_FORMULATION + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_TAKING_ROUTE + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_KOREA_FOOD_AND_DRUG_ADMINISTRATION_CATEGORY + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_INSURANCE_CODE + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_TABOO_COMBINATION + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_TABOO_AGE + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_TABOO_PREGNANT + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_CAUTION_OLD_MAN + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_CAUTION_VOLUME_AND_TREATMENT_PERIOD + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_CAUTION_DIVISION + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_PROHIBITION_BLOOD_DONATION + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_SHAPE_INFO + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_PACKING_UNIT + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_STORAGINT_METHOD + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_EFFICACY + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_DOSAGE + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_PRECAUTION + " TEXT, " +
                Constants.PillEntray.COLUMN_PILL_MEDICATION_GUIDE + " TEXT, " +
                Constants.PillEntray.COLUMN_Pill_CREATEDAT + " NUMERIC, " +
                Constants.PillEntray.COLUMN_Pill_UPDATEAT + " NUMERIC, " +
                Constants.PillEntray.COLUMN_PILL_FK_DISEASE_ID + " INTEGER, " +
                " FOREIGN KEY (" + Constants.PillEntray.COLUMN_PILL_FK_DISEASE_ID + ") REFERENCES " +
                Constants.DiseaseEntray.TABLE_NAME + " (" + Constants.DiseaseEntray._ID + ")" +
                " );";

        final String SQL_CREATE_PRESCRIPTION_TABLE = "CREATE TABLE " + Constants.PrescriptionEntray.TABLE_NAME + " (" +
                Constants.PrescriptionEntray.COLUMN_PRESCRIPTION_DISEASE_ID + " INTEGER, " +
                Constants.PrescriptionEntray.COLUMN_PRESCRIPTION_PILL_ID + " INTEGER, " +
                Constants.PrescriptionEntray.COLUMN_PRESCRIPTION_CREATEDAT + " NUMERIC, " +
                Constants.PrescriptionEntray.COLUMN_PRESCRIPTION_UPDATEAT + " NUMERIC, " +
                " PRIMARY KEY (" + Constants.PrescriptionEntray.COLUMN_PRESCRIPTION_DISEASE_ID + ", " + Constants.PrescriptionEntray.COLUMN_PRESCRIPTION_PILL_ID + "), " +
                " FOREIGN KEY (" + Constants.PrescriptionEntray.COLUMN_PRESCRIPTION_DISEASE_ID + ") REFERENCES " +
                Constants.DiseaseEntray.TABLE_NAME + " (" + Constants.DiseaseEntray._ID + ")," +
                " FOREIGN KEY (" + Constants.PrescriptionEntray.COLUMN_PRESCRIPTION_PILL_ID + ") REFERENCES " +
                Constants.PillEntray.TABLE_NAME + " (" + Constants.PillEntray._ID + ")" +
                " );";

        //table 생성 sql 실행
        db.execSQL(SQL_CREATE_DISEASE_TABLE);
        db.execSQL(SQL_CREATE_HISTORY_TABLE);
        db.execSQL(SQL_CREATE_ALARM_TABLE);
        db.execSQL(SQL_CREATE_PILL_TABLE);
        db.execSQL(SQL_CREATE_PRESCRIPTION_TABLE);
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
    public long insert(ContentValues cv) {
        //execSQL()를 이용해서 직접 SQL문으로 레코드를 추가할 수도 있다.

        return db.insert(Constants.DiseaseEntray.TABLE_NAME,
                null,
                cv);
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return getReadableDatabase().query(Constants.DiseaseEntray.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy);
    }

    public int update(ContentValues cv, String whereClause, String[] whereArgs) {
        //갱신된 레코드수 리턴
        return db.update(Constants.DiseaseEntray.TABLE_NAME,
                cv,
                whereClause,
                whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs) {
        //삭제된 레코드수 리턴
        return db.delete(Constants.DiseaseEntray.TABLE_NAME,
                whereClause,
                whereArgs);
    }

    //메인 화면 쿼리
    //color, img, name, mPillArrayList, dateStart, dateEnd, dosageType
    // showWakeup, showMorning, showLunch, showEvening, showSleep
    // takeWakeup, takeMorning, takeLunch, takeEvening, takeSleep
    // timeStartHour, timeStartMinute, timeInterval, dosageCurrnt, dosageTotal


    // 식사일때 저장
    // color, img, name, mPillArrayList, dateStart, dateEnd, dosageType
    // TimeList


    //시간일때 저장
    // color, img, name, mPillArrayList, dateStart, dateEnd, dosageType
    // timeStartHour, timeStartMinute, timeInterval,


}
