package com.thechicks.conditionform.data.database;

import com.thechicks.conditionform.data.model.Alarm;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.data.model.Pill;
import com.thechicks.conditionform.ui.history.History;

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

    public List<History> findAllDisease();

}
