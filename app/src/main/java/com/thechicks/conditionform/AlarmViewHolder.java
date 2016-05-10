package com.thechicks.conditionform;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-04-23.
 */
public class AlarmViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.textView_label)
    TextView tvLabel;

    @Bind(R.id.imageView_label)
    ImageView ivLabel;

    View mView;

    public static RecyclerView.ViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_alarm, parent, false);
        return new AlarmViewHolder(itemView);
    }

    private AlarmViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

        ButterKnife.bind(this, mView);
    }

    public void bind(AlarmItem alarmItem) {

        tvLabel.setText(alarmItem.getIllness());  //  병명 설정
//        ivLabel.setImageResource(Integer.parseInt(alarmItem.getImage())); // 이미지 설정
        ((GradientDrawable) ivLabel.getBackground()).setColor(Color.parseColor(alarmItem.getColor()));  //컬러 설정
    }
}
