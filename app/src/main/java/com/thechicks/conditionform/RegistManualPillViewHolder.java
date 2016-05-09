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
public class RegistManualPillViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.textView_pill_name)
    TextView tvKoName;

    @Bind(R.id.imageView_pill_remove)
    ImageView ivRemove;

    View mView;

    RegistManualPillAdapter.OnListItemRemoveListener mListener;

    public static RegistManualPillViewHolder newInstance(ViewGroup parent, RegistManualPillAdapter.OnListItemRemoveListener listener) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_regist_pill, parent, false);
        return new RegistManualPillViewHolder(itemView, listener);
    }

    private RegistManualPillViewHolder(View itemView, RegistManualPillAdapter.OnListItemRemoveListener listener) {
        super(itemView);

        mView = itemView;
        mListener = listener;

        ButterKnife.bind(this, mView);
    }

    public void bind(final Pill pill) {

        tvKoName.setText(pill.getKoName());

//        ivRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mListener != null) {
//                    mListener.onListItemRemove(pill);
//                }
//            }
//        });
    }
}