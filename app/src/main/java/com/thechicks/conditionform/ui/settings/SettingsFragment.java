package com.thechicks.conditionform.ui.settings;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import butterknife.OnCheckedChanged;
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

    @Bind(R.id.textView_time_morning)
    TextView tvMorningtime;     // 아침시간

    @Bind(R.id.textView_time_lunch)
    TextView tvLunchtime;         // 점심시간

    @Bind(R.id.textView_time_evening)
    TextView tvEveningtime;     // 저녁시간

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

        long mTime = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_MORNING);
        long lTime = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_LUNCH);
        long eTime = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_EVENING);

        tvMorningtime.setText(TimeUtils.unixTimeStampToStringTime(mTime));
        tvLunchtime.setText(TimeUtils.unixTimeStampToStringTime(lTime));
        tvEveningtime.setText(TimeUtils.unixTimeStampToStringTime(eTime));

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

    @OnClick(R.id.textView_time_morning)
    public void onClickMorningTime() {
//        new TimePickerDialog(getContext(), mTimeSetListener, mHour, mMinute, true).show();

        long morningTime = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_MORNING);

        if (morningTime == 0) {
            morningTime = TimeUtils.getCurrentUnixTimeStamp();
        }

        int hour = TimeUtils.timestampToHour(morningTime);
        int minute = TimeUtils.timestampToMinute(morningTime);

        new TimePickerDialog(getContext(), mTimeSetListener, hour, minute, true).show();
    }

    @OnClick(R.id.textView_time_lunch)
    public void onClickLunchTime() {

//        new TimePickerDialog(getContext(), dTimeSetListener, mHour, mMinute, true).show();

        long lunchTime = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_LUNCH);

        if (lunchTime == 0) {
            lunchTime = TimeUtils.getCurrentUnixTimeStamp();
        }

        int hour = TimeUtils.timestampToHour(lunchTime);
        int minute = TimeUtils.timestampToMinute(lunchTime);

        new TimePickerDialog(getContext(), dTimeSetListener, hour, minute, true).show();
    }

    @OnClick(R.id.textView_time_evening)
    public void onClickEveningTime() {

        long eveningTime = PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_EVENING);

        if (eveningTime == 0) {
            eveningTime = TimeUtils.getCurrentUnixTimeStamp();
        }

        int hour = TimeUtils.timestampToHour(eveningTime);
        int minute = TimeUtils.timestampToMinute(eveningTime);

        new TimePickerDialog(getContext(), eTimeSetListener, hour, minute, true).show();
    }

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
            tvMorningtime.setText(TimeUtils.unixTimeStampToStringTime(selectedTime));
        }
    };

    TimePickerDialog.OnTimeSetListener dTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
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
            tvLunchtime.setText(TimeUtils.unixTimeStampToStringTime(selectedTime));
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
            tvEveningtime.setText(TimeUtils.unixTimeStampToStringTime(selectedTime));
        }
    };
}
