package com.thechicks.conditionform.data.database;

import com.thechicks.conditionform.data.model.Alarm;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.data.model.Pill;
import com.thechicks.conditionform.data.model.History;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by opklnm102 on 2016-05-19.
 */
public interface IConditionformDao {

    public long addDisease(Disease disease);

    public long addPill(long diseaseRowId, Pill pill);

    public boolean addHistory(long diseaseRowId, long date);

    public boolean addAlarm(long diseaseRowId, Alarm alarm);

    public boolean addPrescription(long diseaseRowId, long pillRowId);

    public ArrayList<History> findAllDisease();

    public ArrayList<Disease> findDiseaseByDate(long timeStamp);

    public Disease findDiseaseById(int diseaseRowId);

    public Disease findDiseaseWithHistoryByIdAndDate(int diseaseRowId, long date);

    public boolean updateTakeHistory(long timeStamp, Disease disease);

    public boolean deleteDisease(long diseaseRowId);

}
