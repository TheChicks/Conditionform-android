package com.thechicks.conditionform.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.Pill;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by opklnm102 on 2016-05-24.
 */
public class HistoryDetailPillViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.textView_pill_name)
    TextView tvKoName;

    @Bind(R.id.imageView_pill_detail)
    ImageView ivDetail;

    View mView;

    HistoryDetailPillAdapter.OnListItemClickListener mListener;

    public static HistoryDetailPillViewHolder newInstance(ViewGroup parent, HistoryDetailPillAdapter.OnListItemClickListener listener) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_history_detail_pill, parent, false);
        return new HistoryDetailPillViewHolder(itemView, listener);
    }

    private HistoryDetailPillViewHolder(View itemView, HistoryDetailPillAdapter.OnListItemClickListener listener) {
        super(itemView);

        mView = itemView;
        mListener = listener;

        ButterKnife.bind(this, mView);
    }

    public void bind(final Pill pill) {

        tvKoName.setText(pill.getKoName());

        ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onListItemClick(pill);
                }
            }
        });
    }
}
