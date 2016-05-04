package com.thechicks.conditionform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmActivity extends AppCompatActivity {

    public static final String TAG = AlarmActivity.class.getSimpleName();

    public static final String ALARM_SNOOZE_ACTION = "com.thechicks.conditionform.ALARM_SNOOZE";
    public static final String ALARM_DISMISS_ACTION = "com.thechicks.conditionform.ALARM_DISMISS";


    AlarmListAdapter mAlarmListAdapter;

    @Bind(R.id.recyclerview_alarm)
    RecyclerView rvAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);

        rvAlarm.setLayoutManager(new LinearLayoutManager(AlarmActivity.this, LinearLayoutManager.VERTICAL, false));
        rvAlarm.setHasFixedSize(true);

        mAlarmListAdapter = new AlarmListAdapter(AlarmActivity.this);
        rvAlarm.setAdapter(mAlarmListAdapter);

        AlarmAlertWakeLock.acquireScreenCpuWakeLock(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Alarm start
        AlarmKlaxon.start(AlarmActivity.this, null, false);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Alarm stop
        AlarmKlaxon.stop(AlarmActivity.this);
    }

    @OnClick(R.id.button_snooze)
    public void snooze(){
        Log.d(TAG, " snooze()");

    }

    @OnClick(R.id.button_take)
    public void take(){
        Log.d(TAG, " take()");

    }

    @OnClick(R.id.button_cancel)
    public void cancel(){
        Log.d(TAG, " cancel()");
        AlarmAlertWakeLock.releaseCpuLock();

    }
}
