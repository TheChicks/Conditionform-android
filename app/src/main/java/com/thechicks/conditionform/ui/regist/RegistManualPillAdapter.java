package com.thechicks.conditionform.ui.regist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thechicks.conditionform.data.model.Pill;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-27.
 */
public class RegistManualPillAdapter extends RecyclerView.Adapter<RegistManualPillViewHolder> {

    private final Context mContext;
    private final ArrayList<Pill> mPillArrayList;

    private OnListItemClickListener mOnListItemClickListener;

    public void setOnListItemClickListener(OnListItemClickListener listener){
        mOnListItemClickListener = listener;
    }

    public interface OnListItemClickListener{
        void onListItemRemove(int position);
        void onListItemChange(int position);
    }

    public RegistManualPillAdapter(Context context){
        this.mContext = context;
        mPillArrayList = new ArrayList<Pill>();
    }

    @Override
    public RegistManualPillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RegistManualPillViewHolder.newInstance(parent, mOnListItemClickListener);
    }

    @Override
    public void onBindViewHolder(RegistManualPillViewHolder holder, int position) {

        Pill item = mPillArrayList.get(position);

        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mPillArrayList.size();
    }

    public void addItem(Pill pill, int position){
        mPillArrayList.add(position, pill);
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        mPillArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public ArrayList<Pill> getPillArrayList() {
        return mPillArrayList;
    }
}