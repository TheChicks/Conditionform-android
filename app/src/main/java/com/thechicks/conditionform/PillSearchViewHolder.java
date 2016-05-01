package com.thechicks.conditionform;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by opklnm102 on 2016-04-11.
 */
public class PillSearchViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.ivPillImg)
    ImageView ivPillImg;

    @Bind(R.id.tvPillName)
    TextView tvPillName;

    @Bind(R.id.tvPillNumber)
    TextView tvPillNumber;

    GradientDrawable gradientDrawable;

    View mView;

    PillSearchListAdapter.OnListItemClickListener mListener;

    public static RecyclerView.ViewHolder newInstance(ViewGroup parent, PillSearchListAdapter.OnListItemClickListener listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item_cardview, parent, false);
        return new PillSearchViewHolder(itemView, listener);
    }

    private PillSearchViewHolder(View itemView, PillSearchListAdapter.OnListItemClickListener listener) {
         super(itemView);

         mView = itemView;
         gradientDrawable = new GradientDrawable();  //border 그리기용

         mListener = listener;

         ButterKnife.bind(this, mView);
     }

    public void bind(final Pill pill) {

        Glide.with(mView.getContext())
                .load(pill.getImageUrl())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(ivPillImg);

        tvPillName.setText(pill.getKoName());
        tvPillNumber.setText(pill.getInsuranceCode());

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onListItemClick(pill);
                }
            }
        });
    }
}
