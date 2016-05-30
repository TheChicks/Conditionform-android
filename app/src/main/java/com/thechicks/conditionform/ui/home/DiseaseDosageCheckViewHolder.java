package com.thechicks.conditionform.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.TimeItem;
import com.thechicks.conditionform.util.TimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by opklnm102 on 2016-05-30.
 */
public class DiseaseDosageCheckViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.imageView_time_type)
    ImageView ivType;

    @Bind(R.id.textView_time)
    TextView tvTime;

    @Bind(R.id.checkBox_dosage)
    CheckBox cbDosage;

    DiseaseDosageCheckAdapter.OnListItemClickListener mListener;

    public static DiseaseDosageCheckViewHolder newInstance(ViewGroup parent, DiseaseDosageCheckAdapter.OnListItemClickListener listener) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_dosage_check, parent, false);
        return new DiseaseDosageCheckViewHolder(itemView, listener);
    }

    public DiseaseDosageCheckViewHolder(View itemView, DiseaseDosageCheckAdapter.OnListItemClickListener listener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        mListener = listener;
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

        cbDosage.setOnCheckedChangeListener(null);
        cbDosage.setChecked(timeItem.isEnabled());
        cbDosage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
