package com.thechicks.conditionform;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-05-09.
 */
public class RegistManualTimeAdapter extends RecyclerView.Adapter<RegistManualTimeViewHolder> {

    private final Context mContext;
    private final ArrayList<TimeItem> mTimeItemArrayList;

    private OnListItemRemoveListener mOnListItemRemoveListener;

    public void setOnListItemRemoveListener(OnListItemRemoveListener listener){
        mOnListItemRemoveListener = listener;
    }

    public interface OnListItemRemoveListener{
        void onListItemRemove(int position);
    }

    public RegistManualTimeAdapter(Context context){
        this.mContext = context;
        mTimeItemArrayList = new ArrayList<>();
    }

    @Override
    public RegistManualTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RegistManualTimeViewHolder.newInstance(parent, mOnListItemRemoveListener);
    }

    @Override
    public void onBindViewHolder(RegistManualTimeViewHolder holder, final int position) {

        TimeItem item = mTimeItemArrayList.get(position);

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
        return mTimeItemArrayList.size();
    }

    public void addItem(TimeItem item, int position){
        mTimeItemArrayList.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        mTimeItemArrayList.remove(position);
        notifyItemRemoved(position);
    }
}
