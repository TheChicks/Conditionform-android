package com.thechicks.conditionform.alert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.thechicks.conditionform.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmAlertActivity extends AppCompatActivity {

    public static final String TAG = AlarmAlertActivity.class.getSimpleName();

    public static final String ALARM_SNOOZE_ACTION = "com.thechicks.conditionform.ALARM_SNOOZE";
    public static final String ALARM_DISMISS_ACTION = "com.thechicks.conditionform.ALARM_DISMISS";

    AlarmListAdapter mAlarmListAdapter;

    @Bind(R.id.recyclerview_alarm)
    RecyclerView rvAlarm;

    private boolean alarmActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);

        rvAlarm.setLayoutManager(new LinearLayoutManager(AlarmAlertActivity.this, LinearLayoutManager.VERTICAL, false));
        rvAlarm.setHasFixedSize(true);

        mAlarmListAdapter = new AlarmListAdapter(AlarmAlertActivity.this);
        rvAlarm.setAdapter(mAlarmListAdapter);

        //전화중일 때 처리
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {

                switch (state) {
                    case TelephonyManager.CALL_STATE_RINGING:
                        Log.d(TAG, " Incoming call: " + incomingNumber);
                        AlarmKlaxon.stop(AlarmAlertActivity.this);
                        break;

                    case TelephonyManager.CALL_STATE_IDLE:
                        Log.d(TAG, " Call State IDLE");
                        AlarmKlaxon.start(AlarmAlertActivity.this, null, false);
                        break;
                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };

        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        //Alarm start
        AlarmKlaxon.start(AlarmAlertActivity.this, null, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        alarmActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        AlarmAlertWakeLock.releaseCpuLock();
    }

    @Override
    protected void onDestroy() {
        AlarmKlaxon.stop(AlarmAlertActivity.this);
        super.onDestroy();
    }

    //Back button lock
    @Override
    public void onBackPressed() {
        if (!alarmActive) {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.button_snooze)
    public void snooze() {
        Log.d(TAG, " snooze()");

        //Todo: Alarm 정지
        alarmActive = false;

        //Todo: Alarm 5분후에 다시 등록


//        AlarmKlaxon.stop(AlarmAlertActivity.this);
        this.finish();
    }

    @OnClick(R.id.button_take)
    public void take() {
        Log.d(TAG, " take()");

        //Todo: Alarm 정지
        alarmActive = false;
//        AlarmKlaxon.stop(AlarmAlertActivity.this);

        this.finish();

        //Todo: 약 복용 체크
    }

    @OnClick(R.id.button_cancel)
    public void cancel() {
        Log.d(TAG, " cancel()");

        //Todo: Alarm 정지
        alarmActive = false;
//        AlarmKlaxon.stop(AlarmAlertActivity.this);

        this.finish();
    }
}
