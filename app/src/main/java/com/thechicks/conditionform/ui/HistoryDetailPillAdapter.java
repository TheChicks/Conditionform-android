package com.thechicks.conditionform.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thechicks.conditionform.data.model.Pill;

import java.util.ArrayList;

/**
 * Created by opklnm102 on 2016-05-24.
 */
public class HistoryDetailPillAdapter extends RecyclerView.Adapter<HistoryDetailPillViewHolder> {

    private final Context mContext;
    private ArrayList<Pill> mPillArrayList;

    private OnListItemClickListener mOnListItemClickListener;

    public void setOnListItemClickListener(OnListItemClickListener listener) {
        mOnListItemClickListener = listener;
    }

    public interface OnListItemClickListener {
        void onListItemClick(Pill pill);
    }

    public HistoryDetailPillAdapter(Context context) {
        mContext = context;
        mPillArrayList = new ArrayList<>();
    }


    @Override
    public HistoryDetailPillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HistoryDetailPillViewHolder.newInstance(parent, mOnListItemClickListener);
    }

    @Override
    public void onBindViewHolder(HistoryDetailPillViewHolder holder, int position) {
        Pill pill = mPillArrayList.get(position);

        holder.bind(pill);
    }

    @Override
    public int getItemCount() {
        return mPillArrayList.size();
    }

    public void addItem(Pill pill, int position) {
        mPillArrayList.add(position, pill);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mPillArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public ArrayList<Pill> getPillArrayList() {
        return mPillArrayList;
    }

    public void setPillArrayList(ArrayList<Pill> pillArrayList) {
        mPillArrayList = pillArrayList;
        notifyDataSetChanged();
    }
}

