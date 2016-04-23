package com.thechicks.conditionform;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-11.
 */
public class PillSearchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context mContext;
    private final ArrayList<PillSearchItem> mPillSearchItemList;

    //item click event
    private OnListItemClickListener mOnListItemClickListener;
    public interface OnListItemClickListener{
        void onListItemClick(PillSearchItem pillSearchItem);
    }

    public void setOnListItemClickListener(OnListItemClickListener listener){
        mOnListItemClickListener = listener;
    }

    public PillSearchListAdapter(Context context) {
        mContext = context;
        mPillSearchItemList = new ArrayList<>();

        initData();
    }

    public void initData() {
        for (int i = 0; i < 10; i++) {
            addItem(new PillSearchItem("URL", "아스피린 정 50mg", 123456789));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mPillSearchItemList.get(position).getPillNumber();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PillSearchViewHolder.newInstance(parent, mOnListItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PillSearchItem pillSearchItem = mPillSearchItemList.get(position);
        ((PillSearchViewHolder)holder).bind(pillSearchItem);
    }

    @Override
    public int getItemCount() {
        return mPillSearchItemList.size();
    }

    public void addItem(PillSearchItem pillSearchItem) {
        mPillSearchItemList.add(pillSearchItem);
    }

}
