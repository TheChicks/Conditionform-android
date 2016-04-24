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
    RecyclerView recyclerview_alarm_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);


        recyclerview_alarm_item.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerview_alarm_item.setHasFixedSize(true);

        mAlarmListAdapter = new AlarmListAdapter(getApplicationContext());
        recyclerview_alarm_item.setAdapter(mAlarmListAdapter);
    }
}
