package com.thechicks.conditionform;

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
public class AlarmViewHolder extends RecyclerView.ViewHolder{
    @Bind(R.id.tvSkicknessName)
    TextView tvSkicknessName;

    @Bind(R.id.imIconColor)
    ImageView imIconColor;

    GradientDrawable gradientDrawable;
    View mView;

    public static RecyclerView.ViewHolder newInstance(ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item_cardview, parent, false);
        return new AlarmViewHolder(itemView);
    }

    private AlarmViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
        gradientDrawable = new GradientDrawable();  //border 그리기용
        ButterKnife.bind(this, mView);
    }

    public void bind(AlarmItem alarmItem) {
        //ivPillImg.setImageResource(Integer.parseInt(pillSearchItem.getImage())); // 이미지 URL 경로 문자열
        tvSkicknessName.setText(alarmItem.getIllness());
        imIconColor.setBackgroundColor(Integer.parseInt(alarmItem.getColor()));
    }
}
