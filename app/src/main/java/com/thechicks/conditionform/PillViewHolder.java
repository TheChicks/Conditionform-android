package com.thechicks.conditionform;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-04-27.
 */
public class PillViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.pill_name)
    TextView tvTitle;
    @Bind(R.id.pill_remove)
    ImageView rmButton;

    View mView;
    PillAdapter.OnListItemClickListener mListener;

    public PillViewHolder(View itemView){
        super(itemView);
    }
    public static PillViewHolder newInstance(ViewGroup parent, PillAdapter.OnListItemClickListener listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_regist_pill_list, parent, false);
        return new PillViewHolder(itemView, listener);
    }

    private PillViewHolder(View itemView, PillAdapter.OnListItemClickListener listener) {
        super(itemView);

        mView = itemView;
        mListener = listener;

        ButterKnife.bind(this, mView);
    }
    public void bind(final MyPill mPill) {
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onListItemClick(mPill);
                }
            }
        });
    }
}