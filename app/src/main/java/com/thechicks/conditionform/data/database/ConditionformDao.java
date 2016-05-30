package com.thechicks.conditionform.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.thechicks.conditionform.data.model.Alarm;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.data.model.Pill;
import com.thechicks.conditionform.data.model.History;
import com.thechicks.conditionform.ui.home.DiseaseListAdapter;
import com.thechicks.conditionform.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by opklnm102 on 2016-05-18.
 */
public class ConditionformDao implements IConditionformDao {

    public static final String TAG = ConditionformDao.class.getSimpleName();

    private ConditionformDbHelper mDbHelper;

    public ConditionformDao(Context context) {
        mDbHelper = ConditionformDbHelper.getInstance(context);
    }

    public void close() {
        mDbHelper.close();
    }

    @Override
    public long addDisease(Disease disease) {
        ContentValues cv = new ContentValues();

        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_NAME, disease.getName());
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_IMAGE, disease.getImg());
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR, disease.getColor());
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_DATE_START, disease.getDateStart());
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_DATE_END, disease.getDateEnd());
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE, disease.getDosageType());

//        if(disease.getDosageType() == Disease.DOSAGE_TYPE_EVERYHOUR){
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_START_HOUR, disease.getTimeStartHour());
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_START_MINUTE, disease.getTimeStartMinute());
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_INTERVAL, disease.getTimeInterval());
//        }else {
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_WAKEUP, disease.isEnabledWakeup() ? 1 : 0);
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_MORNING, disease.isEnabledMorning() ? 1 : 0);
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_LUNCH, disease.isEnabledLunch() ? 1 : 0);
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_EVENING, disease.isEnabledEvening() ? 1 : 0);
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_SLEEP, disease.isEnabledSleep() ? 1 : 0);
//        }

        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_ONE_TIME, disease.getDosageOneTime());
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL, disease.getDosageTotal());
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL_DAYS, disease.getDosageTotalDays());

        long rowId = mDbHelper.insert(Constants.DiseaseEntray.TABLE_NAME, cv);

        if (rowId < 0) {
            return 0;
        }
        return rowId;
    }

    @Override
    public long addPill(long diseaseRowId, Pill pill) {
        ContentValues cv = new ContentValues();

        cv.put(Constants.PillEntray.COLUMN_PILL_FK_DISEASE_ID, diseaseRowId);
        cv.put(Constants.PillEntray.COLUMN_PILL_NAME_KOREA, pill.getKoName());

        long rowId = mDbHelper.insert(Constants.PillEntray.TABLE_NAME, cv);

        if (rowId < 0) {
            return 0;
        }
        return rowId;
    }

    @Override
    public boolean addHistory(long diseaseRowId, long date) {
        ContentValues cv = new ContentValues();

        cv.put(Constants.HistoryEntray.COLUMN_HISTORY_FK_DISEASE_ID, diseaseRowId);
        cv.put(Constants.HistoryEntray.COLUMN_HISTORY_DATE, date);

        long rowId = mDbHelper.insert(Constants.HistoryEntray.TABLE_NAME, cv);

        if (rowId < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addAlarm(long diseaseRowId, Alarm alarm) {
        ContentValues cv = new ContentValues();

        cv.put(Constants.AlarmEntray.COLUMN_ALARM_FK_DISEASE_ID, diseaseRowId);
        //Todo: 시간 추가


        long rowId = mDbHelper.insert(Constants.PillEntray.TABLE_NAME, cv);

        if (rowId < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addPrescription(long diseaseRowId, long pillRowId) {
        ContentValues cv = new ContentValues();

        cv.put(Constants.PrescriptionEntray.COLUMN_PRESCRIPTION_DISEASE_ID, diseaseRowId);
        cv.put(Constants.PrescriptionEntray.COLUMN_PRESCRIPTION_PILL_ID, pillRowId);

        long rowId = mDbHelper.insert(Constants.PrescriptionEntray.TABLE_NAME, cv);

        if (rowId < 0) {
            return false;
        }
        return true;
    }

    // 식사일때 저장
    // color, img, name, mPillArrayList, dateStart, dateEnd, dosageType
    // TimeList

    //시간일때 저장
    // color, img, name, mPillArrayList, dateStart, dateEnd, dosageType
    // timeStartHour, timeStartMinute, timeInterval,

    /*
    메인 화면 쿼리
    Disease - id, color, img, name, dosageType,
              enabledWakeup, enabledMorning, enabledLunch, enabledEvening, enabledSleep
              timeInterval, dosageTotal

    History - takeWakeup, takeMorning, takeLunch, takeEvening, takeSleep
              dosageCurrnt
     */
    @Override
    public List<Disease> findDiseaseByDate(long timeStamp) {

        final String[] HOME_LIST_COLUMN = {Constants.DiseaseEntray.TABLE_NAME + "." + Constants.DiseaseEntray._ID, Constants.DiseaseEntray.COLUMN_DISEASE_NAME,
                Constants.DiseaseEntray.COLUMN_DISEASE_IMAGE, Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR,
                Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE, Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_WAKEUP,
                Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_MORNING, Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_LUNCH,
                Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_EVENING, Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_SLEEP,
                Constants.DiseaseEntray.COLUMN_DISEASE_TIME_INTERVAL, Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL,
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_WAKEUP, Constants.HistoryEntray.COLUMN_HISTORY_TAKE_MORNING,
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_LUNCH, Constants.HistoryEntray.COLUMN_HISTORY_TAKE_EVENING,
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_SLEEP, Constants.HistoryEntray.COLUMN_HISTORY_TAKE_CURRENT
        };

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();


        sqLiteQueryBuilder.setTables(Constants.DiseaseEntray.TABLE_NAME +
                " INNER JOIN " + Constants.HistoryEntray.TABLE_NAME + " ON " +
                Constants.DiseaseEntray.TABLE_NAME + "." + Constants.DiseaseEntray._ID + " = " + Constants.HistoryEntray.COLUMN_HISTORY_FK_DISEASE_ID);

        //Todo: createdAt으로 정렬
//        String SORT_ORDER_CREATEDAT_DESC = Constants.DiseaseEntray.COLUMN_DISEASE_CREATEDAT + " DESC";

        List<Disease> diseaseList = new ArrayList<>();

        Cursor cursor = sqLiteQueryBuilder.query(mDbHelper.getReadableDatabase(),
                HOME_LIST_COLUMN,
                Constants.HistoryEntray.COLUMN_HISTORY_DATE + " = ?",
                new String[]{Long.toString(timeStamp)},
                null,
                null,
                null);

        Log.e(TAG, " cursor count=" + cursor.getCount());

        while (cursor.moveToNext()) {
            Disease disease = new Disease();
            disease.setId(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray._ID)));
            disease.setName(cursor.getString(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_NAME)));
            disease.setImg(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_IMAGE)));
            disease.setColor(cursor.getString(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR)));
            disease.setDosageType(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE)));
            disease.setEnabledWakeup(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_WAKEUP)) == 1 ? true : false);
            disease.setEnabledMorning(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_MORNING)) == 1 ? true : false);
            disease.setEnabledLunch(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_LUNCH)) == 1 ? true : false);
            disease.setEnabledEvening(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_EVENING)) == 1 ? true : false);
            disease.setEnabledSleep(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_SLEEP)) == 1 ? true : false);
            disease.setTimeInterval(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_INTERVAL)));
            disease.setTakeWakeup(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_WAKEUP)) == 1 ? true : false);
            disease.setTakeMorning(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_MORNING)) == 1 ? true : false);
            disease.setTakeLunch(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_LUNCH)) == 1 ? true : false);
            disease.setTakeEvening(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_EVENING)) == 1 ? true : false);
            disease.setTakeSleep(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_SLEEP)) == 1 ? true : false);
            disease.setDosageCurrnt(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_CURRENT)));
            disease.setDosageTotal(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL)));
            diseaseList.add(disease);

            Log.e(TAG, " " + disease);
        }

        cursor.close();

        return diseaseList;
    }

    /*
    복용 내역 관리 쿼리
    Disease -  id, dateStart, dateEnd, dosageType, timeInterval, name, image, color
    */
    @Override
    public List<History> findAllDisease() {

        final String[] HISTORY_LIST_COLUMN = {Constants.DiseaseEntray._ID,
                Constants.DiseaseEntray.COLUMN_DISEASE_NAME, Constants.DiseaseEntray.COLUMN_DISEASE_IMAGE,
                Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR, Constants.DiseaseEntray.COLUMN_DISEASE_DATE_START,
                Constants.DiseaseEntray.COLUMN_DISEASE_DATE_END, Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE,
                Constants.DiseaseEntray.COLUMN_DISEASE_TIME_INTERVAL};

        List<History> historyList = new ArrayList<>();

        Cursor cursor = mDbHelper.query(Constants.DiseaseEntray.TABLE_NAME,
                HISTORY_LIST_COLUMN,
                null,
                null,
                null,
                null,
                Constants.DiseaseEntray.SORT_ORDER_DATE_START_DESC);

        Log.e(TAG, " cursor count=" + cursor.getCount());

        while (cursor.moveToNext()) {
            History history = new History();
            history.setDiseaseId(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray._ID)));
            history.setName(cursor.getString(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_NAME)));
            history.setImg(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_IMAGE)));
            history.setColor(cursor.getString(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR)));
            history.setDateStart(cursor.getLong(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DATE_START)));
            history.setDateEnd(cursor.getLong(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DATE_END)));
            history.setDosageType(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE)));
            history.setTimeInterval(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_INTERVAL)));
            historyList.add(history);

            Log.e(TAG, " " + history);
        }

        cursor.close();

        return historyList;
    }

    /*
     복용내역 상세정보 쿼리
     Disease - id, name, image, color, dosageType,
               enabledWakeup, enabledMorning, enabledLunch, enabledEvening, enabledSleep
               timeInterval, dosageTotal
               dateStart, dateEnd
               timeStartHour, timeStartMinute
               dosageOneTime, dosageTotalDays
      Pill - koName
     */
    @Override
    public Disease findDiseaseById(int rowId) {

        //Todo: Column 추가,
        final String[] DISEASE_COLUMN = {Constants.DiseaseEntray.TABLE_NAME + "." + Constants.DiseaseEntray._ID, Constants.DiseaseEntray.COLUMN_DISEASE_NAME,
                Constants.DiseaseEntray.COLUMN_DISEASE_IMAGE, Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR,
                Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE, Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_WAKEUP,
                Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_MORNING, Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_LUNCH,
                Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_EVENING, Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_SLEEP,
                Constants.DiseaseEntray.COLUMN_DISEASE_TIME_INTERVAL, Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL,
                Constants.DiseaseEntray.COLUMN_DISEASE_DATE_START, Constants.DiseaseEntray.COLUMN_DISEASE_DATE_END,
                Constants.DiseaseEntray.COLUMN_DISEASE_TIME_START_HOUR, Constants.DiseaseEntray.COLUMN_DISEASE_TIME_START_MINUTE,
                Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_ONE_TIME, Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL_DAYS,
                Constants.PillEntray.COLUMN_PILL_NAME_KOREA
        };

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        sqLiteQueryBuilder.setTables(Constants.DiseaseEntray.TABLE_NAME +
                " INNER JOIN " + Constants.PillEntray.TABLE_NAME + " ON " +
                Constants.DiseaseEntray.TABLE_NAME + "." + Constants.DiseaseEntray._ID + " = " + Constants.PillEntray.COLUMN_PILL_FK_DISEASE_ID);

        Cursor cursor = sqLiteQueryBuilder.query(mDbHelper.getReadableDatabase(),
                DISEASE_COLUMN,
                Constants.DiseaseEntray.TABLE_NAME + "." + Constants.DiseaseEntray._ID + "= ?" + " AND " + Constants.PillEntray.COLUMN_PILL_FK_DISEASE_ID + " = ?",
                new String[]{Integer.toString(rowId), Integer.toString(rowId)},
                null,
                null,
                null);

        Log.e(TAG, " cursor count=" + cursor.getCount());

        Disease disease = new Disease();

        if (cursor.getCount() != 0) {

            cursor.moveToFirst();
            disease.setName(cursor.getString(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_NAME)));
            disease.setImg(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_IMAGE)));
            disease.setColor(cursor.getString(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR)));
            disease.setDosageType(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE)));
            disease.setEnabledWakeup(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_WAKEUP)) == 1 ? true : false);
            disease.setEnabledMorning(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_MORNING)) == 1 ? true : false);
            disease.setEnabledLunch(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_LUNCH)) == 1 ? true : false);
            disease.setEnabledEvening(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_EVENING)) == 1 ? true : false);
            disease.setEnabledSleep(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_SLEEP)) == 1 ? true : false);
            disease.setTimeInterval(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_INTERVAL)));
            disease.setDosageTotal(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL)));
            disease.setDateStart(cursor.getLong(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DATE_START)));
            disease.setDateEnd(cursor.getLong(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DATE_END)));
            disease.setTimeStartHour(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_START_HOUR)));
            disease.setTimeStartMinute(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_START_MINUTE)));
            disease.setDosageOneTime(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_ONE_TIME)));
            disease.setDosageTotalDays(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL_DAYS)));

            ArrayList<Pill> pillArrayList = new ArrayList<>();
            for (int i = 0; i < cursor.getCount(); i++) {
                Pill pill = new Pill();
                pill.setKoName(cursor.getString(cursor.getColumnIndex(Constants.PillEntray.COLUMN_PILL_NAME_KOREA)));
                pillArrayList.add(pill);
                cursor.moveToNext();
                Log.e(TAG, " " + pill);
            }

            disease.setPillArrayList(pillArrayList);

            Log.e(TAG, " " + disease);
        }

        cursor.close();

        return disease;
    }

    /*
     복용 확인 쿼리
     Disease - id, name, image, color, dosageType,
               enabledWakeup, enabledMorning, enabledLunch, enabledEvening, enabledSleep
               timeInterval, dosageTotal
               dateStart, dateEnd
               timeStartHour, timeStartMinute
               dosageOneTime, dosageTotalDays
      History - takeWakeup, takeMorning, takeLunch, takeEvening, takeSleep
                takeCurrent
      Pill - koName
     */
    @Override
    public Disease findDiseaseWithHistoryByIdAndDate(int rowId, long date) {

        //Todo: Column 추가,
        final String[] DISEASE_COLUMN = {Constants.DiseaseEntray.TABLE_NAME + "." + Constants.DiseaseEntray._ID, Constants.DiseaseEntray.COLUMN_DISEASE_NAME,
                Constants.DiseaseEntray.COLUMN_DISEASE_IMAGE, Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR,
                Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE, Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_WAKEUP,
                Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_MORNING, Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_LUNCH,
                Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_EVENING, Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_SLEEP,
                Constants.DiseaseEntray.COLUMN_DISEASE_TIME_INTERVAL, Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL,
                Constants.DiseaseEntray.COLUMN_DISEASE_DATE_START, Constants.DiseaseEntray.COLUMN_DISEASE_DATE_END,
                Constants.DiseaseEntray.COLUMN_DISEASE_TIME_START_HOUR, Constants.DiseaseEntray.COLUMN_DISEASE_TIME_START_MINUTE,
                Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_ONE_TIME, Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL_DAYS,
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_WAKEUP, Constants.HistoryEntray.COLUMN_HISTORY_TAKE_MORNING,
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_LUNCH, Constants.HistoryEntray.COLUMN_HISTORY_TAKE_EVENING,
                Constants.HistoryEntray.COLUMN_HISTORY_TAKE_SLEEP, Constants.HistoryEntray.COLUMN_HISTORY_TAKE_CURRENT,
                Constants.PillEntray.COLUMN_PILL_NAME_KOREA
        };

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        sqLiteQueryBuilder.setTables(Constants.DiseaseEntray.TABLE_NAME +
                " INNER JOIN " + Constants.PillEntray.TABLE_NAME + " ON " +
                Constants.DiseaseEntray.TABLE_NAME + "." + Constants.DiseaseEntray._ID + " = " + Constants.PillEntray.COLUMN_PILL_FK_DISEASE_ID +
                " INNER JOIN " + Constants.HistoryEntray.TABLE_NAME + " ON " +
                Constants.DiseaseEntray.TABLE_NAME + "." + Constants.DiseaseEntray._ID + " = " + Constants.HistoryEntray.COLUMN_HISTORY_FK_DISEASE_ID);

        Cursor cursor = sqLiteQueryBuilder.query(mDbHelper.getReadableDatabase(),
                DISEASE_COLUMN,
                Constants.DiseaseEntray.TABLE_NAME + "." + Constants.DiseaseEntray._ID + "= ?" +
                        " AND " + Constants.PillEntray.COLUMN_PILL_FK_DISEASE_ID + " = ?" +
                        " AND " + Constants.HistoryEntray.COLUMN_HISTORY_FK_DISEASE_ID + " = ?" +
                        " AND " + Constants.HistoryEntray.COLUMN_HISTORY_DATE + " = ?",
                new String[]{Integer.toString(rowId), Integer.toString(rowId), Integer.toString(rowId), Long.toString(date)},
                null,
                null,
                null);

        Disease disease = new Disease();

        if (cursor.getCount() != 0) {

            cursor.moveToFirst();
            disease.setName(cursor.getString(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_NAME)));
            disease.setImg(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_IMAGE)));
            disease.setColor(cursor.getString(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR)));
            disease.setDosageType(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE)));
            disease.setEnabledWakeup(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_WAKEUP)) == 1 ? true : false);
            disease.setEnabledMorning(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_MORNING)) == 1 ? true : false);
            disease.setEnabledLunch(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_LUNCH)) == 1 ? true : false);
            disease.setEnabledEvening(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_EVENING)) == 1 ? true : false);
            disease.setEnabledSleep(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_SLEEP)) == 1 ? true : false);
            disease.setTimeInterval(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_INTERVAL)));
            disease.setDosageTotal(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL)));
            disease.setDateStart(cursor.getLong(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DATE_START)));
            disease.setDateEnd(cursor.getLong(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DATE_END)));
            disease.setTimeStartHour(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_START_HOUR)));
            disease.setTimeStartMinute(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_TIME_START_MINUTE)));
            disease.setDosageOneTime(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_ONE_TIME)));
            disease.setDosageTotalDays(cursor.getInt(cursor.getColumnIndex(Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TOTAL_DAYS)));
            disease.setTakeWakeup(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_WAKEUP)) == 1 ? true : false);
            disease.setTakeMorning(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_MORNING)) == 1 ? true : false);
            disease.setTakeLunch(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_LUNCH)) == 1 ? true : false);
            disease.setTakeEvening(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_EVENING)) == 1 ? true : false);
            disease.setTakeSleep(cursor.getInt(cursor.getColumnIndex(Constants.HistoryEntray.COLUMN_HISTORY_TAKE_SLEEP)) == 1 ? true : false);

            ArrayList<Pill> pillArrayList = new ArrayList<>();
            for (int i = 0; i < cursor.getCount(); i++) {
                Pill pill = new Pill();
                pill.setKoName(cursor.getString(cursor.getColumnIndex(Constants.PillEntray.COLUMN_PILL_NAME_KOREA)));
                pillArrayList.add(pill);
                cursor.moveToNext();
                Log.e(TAG, " " + pill);
            }

            disease.setPillArrayList(pillArrayList);

            Log.e(TAG, " " + disease);
        }

        cursor.close();

        return disease;
    }


}