package com.thechicks.conditionform.ui.settings;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.util.Constants;
import com.thechicks.conditionform.util.PreferencesUtils;
import com.thechicks.conditionform.util.TimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 설정 화면
 */
public class SettingsFragment extends Fragment {

    public static final String TAG = SettingsFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Bind(R.id.textView_time_wakeUp)
    TextView tvTimeWakeUp;     // 일어나서

    @Bind(R.id.textView_time_morning)
    TextView tvTimeMorning;     // 아침시간

    @Bind(R.id.textView_time_lunch)
    TextView tvTimeLunch;         // 점심시간

    @Bind(R.id.textView_time_evening)
    TextView tvTimeEvening;     // 저녁시간

    @Bind(R.id.textView_time_sleep)
    TextView tvTimeSleep;     // 잠자기전

    @Bind(R.id.switch_sound)
    Switch swSound;

    @Bind(R.id.switch_vibrate)
    Switch swVibrate;

    String morningTime;
    String dayTime;
    String eveningTime;

    String sHour;
    String sMinute;

    int mHour;      //시간
    int mMinute;    //분

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Log.d("onCreate ", TAG);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstancesState) {
        super.onViewCreated(view, savedInstancesState);

//        morningTime = tvMorningtime.getText().toString();
//        dayTime = tvLunchtime.getText().toString();
//        eveningTime = tvEveningtime.getText().toString();
//
//        mHour = 0;
//        mMinute = 0;
//        sHour = "";
//        sMinute = "";

        long timeWakeUp = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_WAKEUP);
        long timeMorning = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_MORNING);
        long timeLunch = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_LUNCH);
        long timeEvening = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_EVENING);
        long timeSleep = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_SLEEP);

        tvTimeWakeUp.setText(TimeUtils.unixTimeStampToStringTime(timeWakeUp));
        tvTimeMorning.setText(TimeUtils.unixTimeStampToStringTime(timeMorning));
        tvTimeLunch.setText(TimeUtils.unixTimeStampToStringTime(timeLunch));
        tvTimeEvening.setText(TimeUtils.unixTimeStampToStringTime(timeEvening));
        tvTimeSleep.setText(TimeUtils.unixTimeStampToStringTime(timeSleep));

        swVibrate.setChecked(PreferencesUtils.getPreferencesBoolean(getActivity(), Constants.PREF_VIBRATE));
        swSound.setChecked(PreferencesUtils.getPreferencesBoolean(getActivity(), Constants.PREF_SOUND));

        swVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferencesUtils.setPreferences(getActivity(), Constants.PREF_VIBRATE, isChecked);
            }
        });

        swSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferencesUtils.setPreferences(getActivity(), Constants.PREF_SOUND, isChecked);
            }
        });
    }

    @OnClick(R.id.textView_time_wakeUp)
    public void onClickTimeWakeUp() {
//        new TimePickerDialog(getContext(), mTimeSetListener, mHour, mMinute, true).show();

        long timeWakeUp = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_WAKEUP);

        if (timeWakeUp == 0) {
            timeWakeUp = TimeUtils.getCurrentUnixTimeStamp();
        }

        int hour = TimeUtils.timestampToHour(timeWakeUp);
        int minute = TimeUtils.timestampToMinute(timeWakeUp);

        new TimePickerDialog(getContext(), wTimeSetListener, hour, minute, true).show();
    }

    @OnClick(R.id.textView_time_morning)
    public void onClickTimeMorning() {
//        new TimePickerDialog(getContext(), mTimeSetListener, mHour, mMinute, true).show();

        long timeMorning = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_MORNING);

        if (timeMorning == 0) {
            timeMorning = TimeUtils.getCurrentUnixTimeStamp();
        }

        int hour = TimeUtils.timestampToHour(timeMorning);
        int minute = TimeUtils.timestampToMinute(timeMorning);

        new TimePickerDialog(getContext(), mTimeSetListener, hour, minute, true).show();
    }

    @OnClick(R.id.textView_time_lunch)
    public void onClickTimeLunch() {

//        new TimePickerDialog(getContext(), lTimeSetListener, mHour, mMinute, true).show();

        long timeLunch = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_LUNCH);

        if (timeLunch == 0) {
            timeLunch = TimeUtils.getCurrentUnixTimeStamp();
        }

        int hour = TimeUtils.timestampToHour(timeLunch);
        int minute = TimeUtils.timestampToMinute(timeLunch);

        new TimePickerDialog(getContext(), lTimeSetListener, hour, minute, true).show();
    }

    @OnClick(R.id.textView_time_evening)
    public void onClickTimeEvening() {

        long timeEvening = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_EVENING);

        if (timeEvening == 0) {
            timeEvening = TimeUtils.getCurrentUnixTimeStamp();
        }

        int hour = TimeUtils.timestampToHour(timeEvening);
        int minute = TimeUtils.timestampToMinute(timeEvening);

        new TimePickerDialog(getContext(), eTimeSetListener, hour, minute, true).show();
    }

    @OnClick(R.id.textView_time_sleep)
    public void onClickTimeSleep() {

        long timeSleep = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_SLEEP);

        if (timeSleep == 0) {
            timeSleep = TimeUtils.getCurrentUnixTimeStamp();
        }

        int hour = TimeUtils.timestampToHour(timeSleep);
        int minute = TimeUtils.timestampToMinute(timeSleep);

        new TimePickerDialog(getContext(), sTimeSetListener, hour, minute, true).show();
    }

    TimePickerDialog.OnTimeSetListener wTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            long selectedTime = TimeUtils.getHourMinuteUnixTimeStamp(hourOfDay, minute);

//            mHour = hourOfDay;
//            mMinute = minute;

//            if(mHour >= 0 && mHour < 10) {
//                sHour = "0" + Integer.toString(mHour);
//            }
//            else{
//                sHour = Integer.toString(mHour);
//            }
//
//            if(mMinute >= 0 && mMinute < 10) {
//                sMinute = "0" + Integer.toString(mMinute);
//            }
//            else{
//                sMinute = Integer.toString(mMinute);
//            }
//            tvDaytime.setText(sHour+":"+sMinute);

            PreferencesUtils.setPreferences(getActivity(), Constants.PREF_TIME_WAKEUP, selectedTime);
            tvTimeWakeUp.setText(TimeUtils.unixTimeStampToStringTime(selectedTime));
        }
    };

    TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            long selectedTime = TimeUtils.getHourMinuteUnixTimeStamp(hourOfDay, minute);

//            mHour = hourOfDay;
//            mMinute = minute;

//            if(mHour >= 0 && mHour < 10) {
//                sHour = "0" + Integer.toString(mHour);
//            }
//            else{
//                sHour = Integer.toString(mHour);
//            }
//
//            if(mMinute >= 0 && mMinute < 10) {
//                sMinute = "0" + Integer.toString(mMinute);
//            }
//            else{
//                sMinute = Integer.toString(mMinute);
//            }
//            tvDaytime.setText(sHour+":"+sMinute);

            PreferencesUtils.setPreferences(getActivity(), Constants.PREF_TIME_MORNING, selectedTime);
            tvTimeMorning.setText(TimeUtils.unixTimeStampToStringTime(selectedTime));
        }
    };

    TimePickerDialog.OnTimeSetListener lTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            mHour = hourOfDay;
//            mMinute = minute;
//
//            if(mHour >= 0 && mHour < 10) {
//                sHour = "0" + Integer.toString(mHour);
//            }
//            else{
//                sHour = Integer.toString(mHour);
//            }
//
//            if(mMinute >= 0 && mMinute < 10) {
//                sMinute = "0" + Integer.toString(mMinute);
//            }
//            else{
//                sMinute = Integer.toString(mMinute);
//            }
//            tvDaytime.setText(sHour+":"+sMinute);

            long selectedTime = TimeUtils.getHourMinuteUnixTimeStamp(hourOfDay, minute);

            PreferencesUtils.setPreferences(getActivity(), Constants.PREF_TIME_LUNCH, selectedTime);
            tvTimeLunch.setText(TimeUtils.unixTimeStampToStringTime(selectedTime));
        }
    };

    TimePickerDialog.OnTimeSetListener eTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            mHour = hourOfDay;
//            mMinute = minute;
//
//            if(mHour >= 0 && mHour < 10) {
//                sHour = "0" + Integer.toString(mHour);
//            }
//            else{
//                sHour = Integer.toString(mHour);
//            }
//
//            if(mMinute >= 0 && mMinute < 10) {
//                sMinute = "0" + Integer.toString(mMinute);
//            }
//            else{
//                sMinute = Integer.toString(mMinute);
//            }
//            tvEveningtime.setText(sHour+":"+sMinute);

            long selectedTime = TimeUtils.getHourMinuteUnixTimeStamp(hourOfDay, minute);

            PreferencesUtils.setPreferences(getActivity(), Constants.PREF_TIME_EVENING, selectedTime);
            tvTimeEvening.setText(TimeUtils.unixTimeStampToStringTime(selectedTime));
        }
    };

    TimePickerDialog.OnTimeSetListener sTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            mHour = hourOfDay;
//            mMinute = minute;
//
//            if(mHour >= 0 && mHour < 10) {
//                sHour = "0" + Integer.toString(mHour);
//            }
//            else{
//                sHour = Integer.toString(mHour);
//            }
//
//            if(mMinute >= 0 && mMinute < 10) {
//                sMinute = "0" + Integer.toString(mMinute);
//            }
//            else{
//                sMinute = Integer.toString(mMinute);
//            }
//            tvEveningtime.setText(sHour+":"+sMinute);

            long selectedTime = TimeUtils.getHourMinuteUnixTimeStamp(hourOfDay, minute);

            PreferencesUtils.setPreferences(getActivity(), Constants.PREF_TIME_SLEEP, selectedTime);
            tvTimeSleep.setText(TimeUtils.unixTimeStampToStringTime(selectedTime));
        }
    };
}
