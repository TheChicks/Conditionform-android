package com.thechicks.conditionform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AlarmActivity extends AppCompatActivity {

    AlarmListAdapter mAlarmListAdapter;

    @Bind(R.id.recyclerview_alarm_item)
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
    }

    @Override
    protected void onResume() {
        super.onResume();

        AlarmKlaxon.start(AlarmActivity.this, null, false);
    }

    @Override
    protected void onPause() {
        super.onPause();

        AlarmKlaxon.stop(AlarmActivity.this);
    }
}
