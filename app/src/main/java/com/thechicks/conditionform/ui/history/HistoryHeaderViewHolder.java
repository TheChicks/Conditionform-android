package com.thechicks.conditionform.ui.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thechicks.conditionform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-04-24.
 */
public class HistoryHeaderViewHolder extends RecyclerView.ViewHolder {


    @Bind(R.id.ll_content)
    LinearLayout llContent;

    @Bind(R.id.textView_date)
    TextView tvDate;

    View mView;

    public static HistoryHeaderViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_history_header, parent, false);
        return new HistoryHeaderViewHolder(itemView);
    }

    private HistoryHeaderViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

        ButterKnife.bind(this, mView);
    }

    public void bind(String head) {

        tvDate.setText(head);

    }
}
