package com.thechicks.conditionform.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.thechicks.conditionform.data.model.Alarm;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.data.model.Pill;
import com.thechicks.conditionform.ui.history.History;
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
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_WAKEUP, disease.isShowWakeup() ? 1 : 0);
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_MORNING, disease.isShowMorning() ? 1 : 0);
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_LUNCH, disease.isShowLunch() ? 1 : 0);
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_EVENING, disease.isShowEvening() ? 1 : 0);
        cv.put(Constants.DiseaseEntray.COLUMN_DISEASE_ENABLED_SLEEP, disease.isShowSleep() ? 1 : 0);
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

        if(rowId < 0){
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


    //메인 화면 쿼리
    //color, img, name, mPillArrayList, dateStart, dateEnd, dosageType
    // showWakeup, showMorning, showLunch, showEvening, showSleep
    // takeWakeup, takeMorning, takeLunch, takeEvening, takeSleep
    // timeStartHour, timeStartMinute, timeInterval, dosageCurrnt, dosageTotal

    /*
    복용 내역 관리 쿼리
    dateStart, dateEnd, dosageType, timeInterval, name, image, color
    */
    @Override
    public List<History> findAllDisease() {

        List<History> historyList = new ArrayList<>();

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

//        sqLiteQueryBuilder.setTables(Constants.DiseaseEntray);

        final String[] HISTORY_LIST_COLUMN = {Constants.DiseaseEntray.COLUMN_DISEASE_NAME,
                Constants.DiseaseEntray.COLUMN_DISEASE_IMAGE, Constants.DiseaseEntray.COLUMN_DISEASE_LABEL_COLOR,
                Constants.DiseaseEntray.COLUMN_DISEASE_DATE_START, Constants.DiseaseEntray.COLUMN_DISEASE_DATE_END,
                Constants.DiseaseEntray.COLUMN_DISEASE_DOSAGE_TYPE, Constants.DiseaseEntray.COLUMN_DISEASE_TIME_INTERVAL};


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
}