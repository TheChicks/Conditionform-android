package com.thechicks.conditionform.ui.settings;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.thechicks.conditionform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 설정 화면
 */
public class SettingsFragment extends Fragment {

    public static final String TAG = SettingsFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Bind(R.id.tvMorningtime)
    TextView tvMorningtime;     // 아침시간

    @Bind(R.id.tvDaytime)
    TextView tvDaytime;         // 점심시간

    @Bind(R.id.tvEveningtime)
    TextView tvEveningtime;     // 저녁시간

    @Bind(R.id.swSound)
    Switch swSound;

    @Bind(R.id.swVibrate)
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

    // TODO: Rename and change types and number of parameters
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
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstancesState){
        super.onViewCreated(view, savedInstancesState);

        morningTime = tvMorningtime.getText().toString();
        dayTime = tvDaytime.getText().toString();
        eveningTime = tvEveningtime.getText().toString();

        mHour=0;
        mMinute=0;
        sHour="";
        sMinute="";

        tvMorningtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), mTimeSetListener, mHour, mMinute, false).show();
            }
        });

        tvDaytime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), dTimeSetListener, mHour, mMinute, false).show();
            }
        });

        tvEveningtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), eTimeSetListener, mHour, mMinute, false).show();
            }
        });
    }

    TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;

            if(mHour >= 0 && mHour < 10) {
                sHour = "0" + Integer.toString(mHour);
            }
            else{
                sHour = Integer.toString(mHour);
            }

            if(mMinute >= 0 && mMinute < 10) {
                sMinute = "0" + Integer.toString(mMinute);
            }
            else{
                sMinute = Integer.toString(mMinute);
            }
            tvMorningtime.setText(sHour+":"+sMinute);
        }
    };

    TimePickerDialog.OnTimeSetListener dTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;

            if(mHour >= 0 && mHour < 10) {
                sHour = "0" + Integer.toString(mHour);
            }
            else{
                sHour = Integer.toString(mHour);
            }

            if(mMinute >= 0 && mMinute < 10) {
                sMinute = "0" + Integer.toString(mMinute);
            }
            else{
                sMinute = Integer.toString(mMinute);
            }
            tvDaytime.setText(sHour+":"+sMinute);
        }
    };

    TimePickerDialog.OnTimeSetListener eTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;

            if(mHour >= 0 && mHour < 10) {
                sHour = "0" + Integer.toString(mHour);
            }
            else{
                sHour = Integer.toString(mHour);
            }

            if(mMinute >= 0 && mMinute < 10) {
                sMinute = "0" + Integer.toString(mMinute);
            }
            else{
                sMinute = Integer.toString(mMinute);
            }
            tvEveningtime.setText(sHour+":"+sMinute);
        }
    };
}
