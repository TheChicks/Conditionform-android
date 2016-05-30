package com.thechicks.conditionform.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thechicks.conditionform.data.model.TimeItem;

import java.util.ArrayList;

/**
 * Created by opklnm102 on 2016-05-30.
 */
public class DiseaseDosageCheckAdapter extends RecyclerView.Adapter<DiseaseDosageCheckViewHolder> {

    private final Context mContext;
    private ArrayList<TimeItem> mTimeItemArrayList;

    private OnListItemClickListener mOnListItemClickListener;

    public void setOnListItemClickListener(OnListItemClickListener listener) {
        mOnListItemClickListener = listener;
    }

    public interface OnListItemClickListener {
        void onListItemCheck(int position);
    }

    public DiseaseDosageCheckAdapter(Context context) {
        mContext = context;
        mTimeItemArrayList = new ArrayList<>();
    }

    @Override
    public DiseaseDosageCheckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DiseaseDosageCheckViewHolder.newInstance(parent, mOnListItemClickListener);
    }

    @Override
    public void onBindViewHolder(DiseaseDosageCheckViewHolder holder, int position) {
        TimeItem item = mTimeItemArrayList.get(position);

        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mTimeItemArrayList.size();
    }

    public void addItem(TimeItem item, int position) {
        mTimeItemArrayList.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mTimeItemArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public ArrayList<TimeItem> getTimeItemArrayList() {
        return mTimeItemArrayList;
    }

    public void setTimeItemArrayList(ArrayList<TimeItem> timeItemArrayList) {
        mTimeItemArrayList = timeItemArrayList;
        notifyDataSetChanged();
    }
}
