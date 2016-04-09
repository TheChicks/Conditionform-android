package com.thechicks.conditionform;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;

/**
 * Created by Dong on 2016-04-09.
 */
public class DiseaseViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.ll_head)
    LinearLayout llHead;

    @Bind(R.id.ll_content)
    LinearLayout llContent;

    @Bind(R.id.imageView_label)
    ImageView ivLabel;

    @Bind(R.id.textView_label)
    TextView tvLabel;

    @Bind(R.id.imageView_morning)
    ImageView ivMorning;

    @Bind(R.id.imageView_lunch)
    ImageView ivLunch;

    @Bind(R.id.imageView_dinner)
    ImageView ivDinner;

    @Bind(R.id.imageView_sleep)
    ImageView ivSleep;

    @BindDimen(R.dimen.disease_item_border_line)
    int border;

    GradientDrawable gradientDrawable;

    View mView;

    public DiseaseViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

        gradientDrawable = new GradientDrawable();

        ButterKnife.bind(this, mView);
    }

    public void bind(Disease disease) {
        //Todo: 이미지 표시 여부 결정
        //Todo: 레이블 이미지, 텍스트 셋


        //Todo: Border line 셋
        gradientDrawable.setStroke(border, Color.parseColor("#449911"));
        llContent.setBackground(gradientDrawable);

    }
}
