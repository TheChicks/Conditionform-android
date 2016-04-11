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

    public PillSearchListAdapter(Context mContext, ArrayList<PillSearchItem> mPillSearchItemList) {
        this.mContext = mContext;
        this.mPillSearchItemList = mPillSearchItemList;
    }

    public void initData() {
        for (int i = 0; i < 10; i++) {
            addItem(new PillSearchItem("URL", "아스피린 정 50mg", 123456789));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mPillSearchItemList.size();
    }

    public void addItem(PillSearchItem pillSearchItem) {
        mPillSearchItemList.add(pillSearchItem);
    }

}
