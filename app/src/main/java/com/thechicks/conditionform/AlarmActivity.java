package com.thechicks.conditionform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AlarmActivity extends AppCompatActivity {

    AlarmListAdapter mAlarmListAdapter;

    @Bind(R.id.recyclerView_alarm)
    RecyclerView rvAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);


        rvAlarm.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvAlarm.setHasFixedSize(true);

        mAlarmListAdapter = new AlarmListAdapter(getApplicationContext());
        rvAlarm.setAdapter(mAlarmListAdapter);
    }
}
