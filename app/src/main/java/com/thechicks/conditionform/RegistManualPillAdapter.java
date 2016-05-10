package com.thechicks.conditionform;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-27.
 */
public class RegistManualPillAdapter extends RecyclerView.Adapter<RegistManualPillViewHolder> {

    private final Context mContext;
    private final ArrayList<Pill> mPillArrayList;
    private OnListItemRemoveListener mOnListItemRemoveListener;

    public RegistManualPillAdapter(Context context){
        this.mContext = context;
        mPillArrayList = new ArrayList<Pill>();
    }

    public void setOnListItemRemoveListener(OnListItemRemoveListener listener){
        mOnListItemRemoveListener = listener;
    }

    public interface OnListItemRemoveListener{
        void onListItemRemove(int position);
    }

    @Override
    public RegistManualPillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RegistManualPillViewHolder.newInstance(parent, mOnListItemRemoveListener);
    }

    @Override
    public void onBindViewHolder(final RegistManualPillViewHolder holder, final int position) {

        Pill item = mPillArrayList.get(position);

        //Todo: item remove시 원하는 position의 아이템 삭제 안됨. 마지막꺼만 삭제된다.
        holder.bind(item);
        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });
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
}