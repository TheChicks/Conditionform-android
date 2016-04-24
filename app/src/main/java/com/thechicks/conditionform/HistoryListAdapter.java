package com.thechicks.conditionform;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-24.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_NORMAL = 0;  //내역
    public static final int VIEW_TYPE_HEADER = 1;  //헤더

    private final Context mContext;
    private final ArrayList<HistoryItem> mHistoryItems;

    //item click event
    private OnListItemClickListener mOnListItemClickListener;

    public interface OnListItemClickListener{
        void onListItemClick(HistoryItem historyItem);
    }

    public void setOnListItemClickListener(OnListItemClickListener listener){
        mOnListItemClickListener = listener;
    }

    public HistoryListAdapter(Context context) {
        mContext = context;
        mHistoryItems = new ArrayList<>();

        initData();
    }

    //dummy data
    public void initData() {

    }

    //Todo: 헤더인지 판단할 것
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_TYPE_HEADER:
                return HistoryHeaderViewHolder.newInstance(parent);
            case VIEW_TYPE_NORMAL:
                return HistoryNormalViewHolder.newInstance(parent, mOnListItemClickListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        HistoryItem historyItem = mHistoryItems.get(position);


    }

    @Override
    public int getItemCount() {
        return mHistoryItems.size();
    }

    public void addItem(HistoryItem historyItem, int position) {
        mHistoryItems.add(position, historyItem);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mHistoryItems.remove(position);
        notifyItemRemoved(position);
    }
}
