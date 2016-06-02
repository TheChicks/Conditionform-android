package com.thechicks.conditionform.ui.home;

import com.thechicks.conditionform.data.model.Disease;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-06-02.
 */
public class EventDateChange {

    private ArrayList<Disease> mDiseaseArrayList;
    private long mCurrentDayTimestamp;

    public long getCurrentDayTimestamp() {
        return mCurrentDayTimestamp;
    }

    public EventDateChange(ArrayList<Disease> diseaseArrayList, long currentDayTimestamp) {
        mDiseaseArrayList = diseaseArrayList;
        mCurrentDayTimestamp = currentDayTimestamp;
    }

    public ArrayList<Disease> getDiseaseArrayList() {
        return mDiseaseArrayList;
    }
}
