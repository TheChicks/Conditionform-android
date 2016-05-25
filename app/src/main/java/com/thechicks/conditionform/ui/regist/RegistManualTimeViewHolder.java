package com.thechicks.conditionform.ui.regist;

import android.app.TimePickerDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.TimeItem;
import com.thechicks.conditionform.util.TimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-05-09.
 */
public class RegistManualTimeViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.imageView_time_type)
    ImageView ivType;

    @Bind(R.id.textView_time)
    TextView tvTime;

    @Bind(R.id.checkBox_time)
    CheckBox cbEnabled;

    View mView;

    RegistManualTimeAdapter.OnListItemClickListener mListener;

    public static RegistManualTimeViewHolder newInstance(ViewGroup parent, RegistManualTimeAdapter.OnListItemClickListener itemClickListener, boolean cbTimeClickable) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_regist_time, parent, false);
        return new RegistManualTimeViewHolder(itemView, itemClickListener, cbTimeClickable);
    }

    public RegistManualTimeViewHolder(View itemView, RegistManualTimeAdapter.OnListItemClickListener itemClickListener, boolean cbTimeClickable) {
        super(itemView);

        mView = itemView;

        mListener = itemClickListener;

        ButterKnife.bind(this, mView);

        cbEnabled.setClickable(cbTimeClickable);
    }

    public void bind(final TimeItem timeItem) {
        tvTime.setText(TimeUtils.unixTimeStampToStringTime(timeItem.getTime()));

        switch (timeItem.getType()) {
            case 0:  //일어나서
                ivType.setImageResource(R.mipmap.ic_launcher);
                break;
            case 1:  //아침
                ivType.setImageResource(R.drawable.sunrising);
                break;
            case 2:  //점심
                ivType.setImageResource(R.drawable.sun);
                break;
            case 3:  //저녁
                ivType.setImageResource(R.drawable.moon);
                break;
            case 4:  //잠자기전
                ivType.setImageResource(R.mipmap.ic_launcher);
                break;
        }

        tvTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                long currentTime = timeItem.getTime();
                int hour = TimeUtils.timestampToHour(currentTime);
                int minute = TimeUtils.timestampToMinute(currentTime);

                new TimePickerDialog(mView.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        long selectedTime = TimeUtils.getHourMinuteUnixTimeStamp(hourOfDay, minute);
                        tvTime.setText(TimeUtils.unixTimeStampToStringTime(selectedTime));
                        timeItem.setTime(selectedTime);

                        if (mListener != null) {
                            //시간 변경을 Adapter에 알린다.
                            mListener.onListItemChange(getAdapterPosition());
                        }
                    }
                }, hour, minute, true).show();
            }
        });

        //http://stackoverflow.com/questions/27070220/recycleview-notifydatasetchanged-illegalstateexception/37305564#37305564
        cbEnabled.setOnCheckedChangeListener(null);
        cbEnabled.setChecked(timeItem.isEnabled());
        cbEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mListener != null) {
                    timeItem.setEnabled(isChecked);
                    //체크박스 변경을 Adapter에 알린다.
                    mListener.onListItemCheck(getAdapterPosition());
                }
            }
        });
    }
}
