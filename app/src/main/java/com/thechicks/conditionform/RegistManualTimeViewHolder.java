package com.thechicks.conditionform;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016-05-09.
 */
public class RegistManualTimeViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.imageView_time_type)
    ImageView ivType;

    @Bind(R.id.textView_time)
    TextView tvTime;

    @Bind(R.id.imageView_time_remove)
    ImageView ivRemove;

    View mView;

    RegistManualTimeAdapter.OnListItemRemoveListener mListener;

    long mCurrentTime;
    int mHour;
    int mMinute;

    public static RegistManualTimeViewHolder newInstance(ViewGroup parent, RegistManualTimeAdapter.OnListItemRemoveListener listener) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_regist_time, parent, false);
        return new RegistManualTimeViewHolder(itemView, listener);
    }

    public RegistManualTimeViewHolder(View itemView, RegistManualTimeAdapter.OnListItemRemoveListener listener) {
        super(itemView);

        mView = itemView;
        mListener = listener;

        ButterKnife.bind(this, mView);
    }

    public void bind(TimeItem timeItem) {
        tvTime.setText(TimeUtils.UnixTimeStampToStringTime(timeItem.getTime()));

        switch (timeItem.getType()) {
            case 0:
                ivType.setImageResource(R.drawable.sunrising);
                break;
            case 1:
                ivType.setImageResource(R.drawable.sun);
                break;
            case 2:
                ivType.setImageResource(R.drawable.moon);
                break;
            case 3:
                ivType.setImageResource(R.mipmap.ic_launcher);
                break;
        }

        mCurrentTime = TimeUtils.getCurrentUnixTimeStamp();
        mHour = TimeUtils.timestampToHour(mCurrentTime);
        mMinute = TimeUtils.timestampToMinute(mCurrentTime);

        //Todo: timepicker 표시 시간 저장
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(mView.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMinute = minute;
                        mCurrentTime = TimeUtils.getHourMinuteUnixTimeStamp(mHour, mMinute);
                        tvTime.setText(TimeUtils.UnixTimeStampToStringTime(mCurrentTime));
                    }
                }, mHour, mMinute, true).show();
            }
        });

//        ivRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mListener != null){
//                    mListener.onListItemRemove();
//                }
//            }
//        });
    }
}
