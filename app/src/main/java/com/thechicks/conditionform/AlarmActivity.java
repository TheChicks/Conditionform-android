package com.thechicks.conditionform;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

public class AlarmActivity extends AppCompatActivity {
    AlarmListAdapter mAlarmListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);

// 여기를 고쳐보자 광엽아...
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.setHasFixedSize(true);
//
//        mAlarmListAdapter = new AlarmListAdapter(getApplicationContext());
//        recyclerView.setAdapter(mPillSearchListAdapter);
//        mPillSearchListAdapter.setOnListItemClickListener(new PillSearchListAdapter.OnListItemClickListener() {
//            @Override
//            public void onListItemClick(PillSearchItem disease) {
//                //                Intent intent = new Intent(getActivity(), 이동할 액티비티);
//                //                startActivity(intent);
//                Toast.makeText(getActivity(), "List Item Click", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
