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

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_cardview, parent, false);
        return new PillSearchViewHolder(itemView, listener);
    }

    private PillSearchViewHolder(View itemView, PillSearchListAdapter.OnListItemClickListener listener) {
         super(itemView);

         mView = itemView;
         gradientDrawable = new GradientDrawable();  //border 그리기용
         mListener = listener;
         ButterKnife.bind(this, mView);
     }

    public void bind(PillSearchItem pillSearchItem) {
        //ivPillImg.setImageResource(Integer.parseInt(pillSearchItem.getImage())); // 이미지 URL 경로 문자열
        tvPillName.setText(pillSearchItem.getPillName());
        tvPillNumber.setText(Integer.toString(pillSearchItem.getPillNumber()));
    }
}
