package com.thechicks.conditionform;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;

/**
 * Created by Dong on 2016-04-09.
 */
public class DiseaseIntervalViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.ll_head)
    LinearLayout llHead;

    @Bind(R.id.ll_content)
    LinearLayout llContent;

    @Bind(R.id.imageView_label)
    ImageView ivLabel;

    @Bind(R.id.textView_label)
    TextView tvLabel;

    @Bind(R.id.textView_time_interval)
    TextView tvTimeInterval;

    @Bind(R.id.textView_dosage_current)
    TextView tvDosageCurrent;

    @Bind(R.id.textView_dosage_total)
    TextView tvDosageTotal;

    @BindDimen(R.dimen.disease_item_border_line)
    int border;

    GradientDrawable gradientDrawable;

    View mView;

    DiseaseListAdapter.OnListItemClickListener mListener;

    public static DiseaseIntervalViewHolder newInstance(ViewGroup parent, DiseaseListAdapter.OnListItemClickListener listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_disease_interval, parent, false);
        return new DiseaseIntervalViewHolder(itemView, listener);
    }

    private DiseaseIntervalViewHolder(View itemView, DiseaseListAdapter.OnListItemClickListener listener) {
        super(itemView);

        mView = itemView;

        gradientDrawable = new GradientDrawable();

        mListener = listener;

        ButterKnife.bind(this, mView);
    }

    public void bind(final Disease disease) {

        //레이블 이미지
        ivLabel.setImageResource(disease.getImg());

        //레이블 텍스트
        tvLabel.setText(disease.getName());

        //레이블 색 적용
        llHead.setBackgroundColor(Color.parseColor(disease.getColor()));
        gradientDrawable.setStroke(border, Color.parseColor(disease.getColor()));
        llContent.setBackground(gradientDrawable);

        tvTimeInterval.setText(disease.getTimeInterval() + "시간씩");
        tvDosageCurrent.setText(String.valueOf(disease.getDosageCurrnt()));
        tvDosageTotal.setText(String.valueOf(disease.getDosageTotal()));

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onListItemClick(disease);
                }
            }
        });
    }
}
