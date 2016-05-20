package com.thechicks.conditionform.ui.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thechicks.conditionform.data.model.Pill;

import java.util.ArrayList;

/**
 * Created by opklnm102 on 2016-04-11.
 */
public class PillSearchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context mContext;
    private ArrayList<Pill> mPillArrayList;

    //item click event
    private OnListItemClickListener mOnListItemClickListener;

    public interface OnListItemClickListener{
        void onListItemClick(Pill pill);
    }

    public void setOnListItemClickListener(OnListItemClickListener listener){
        mOnListItemClickListener = listener;
    }

    public PillSearchListAdapter(Context context) {
        mContext = context;
        mPillArrayList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PillSearchViewHolder.newInstance(parent, mOnListItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Pill pill = mPillArrayList.get(position);
        ((PillSearchViewHolder)holder).bind(pill);
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

    public void setData(ArrayList<Pill> data){
        mPillArrayList = data;
        notifyDataSetChanged();
    }
}
