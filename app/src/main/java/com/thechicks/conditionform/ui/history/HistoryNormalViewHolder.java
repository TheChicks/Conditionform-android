package com.thechicks.conditionform.ui.history;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.util.TimeUtils;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-04-24.
 */
public class HistoryNormalViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.ll_head)
    LinearLayout llHead;

    @Bind(R.id.ll_content)
    LinearLayout llContent;

    @Bind(R.id.imageView_label)
    ImageView ivLabel;

    @Bind(R.id.textView_label)
    TextView tvLabel;

    @Bind(R.id.textView_dosage_type)
    TextView tvDosageType;

    @Bind(R.id.textView_dosage_date_start)
    TextView tvDosageDateStart;

    @Bind(R.id.textView_dosage_date_end)
    TextView tvDosageDateEnd;

    @BindDimen(R.dimen.disease_item_border_line)
    int border;

    GradientDrawable gradientDrawable;

    View mView;

    HistoryListAdapter.OnListItemClickListener mListener;

    public static HistoryNormalViewHolder newInstance(ViewGroup parent, HistoryListAdapter.OnListItemClickListener listener){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_history_normal, parent, false);
        return new HistoryNormalViewHolder(itemView, listener);
    }

    private HistoryNormalViewHolder(View itemView, HistoryListAdapter.OnListItemClickListener listener) {
        super(itemView);

        mView = itemView;
        gradientDrawable = new GradientDrawable();  //border 그리기용

        mListener = listener;

        ButterKnife.bind(this, mView);
    }

    public void bind(final History history){

        //레이블 이미지
        ivLabel.setImageResource(history.getImg());

        //레이블 텍스트
        tvLabel.setText(history.getName());

        //레이블 색 적용
        llHead.setBackgroundColor(Color.parseColor(history.getColor()));
        gradientDrawable.setStroke(border, Color.parseColor(history.getColor()));
        llContent.setBackground(gradientDrawable);

         tvDosageType.setText(history.getStrDosageType());

         tvDosageDateStart.setText(TimeUtils.unixTimeStampToStringDateYearMonthDay(history.getDateStart()));

         tvDosageDateEnd.setText(TimeUtils.unixTimeStampToStringDateYearMonthDay(history.getDateEnd()));

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onListItemClick(history);
                }
            }
        });
    }
}
