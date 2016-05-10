package com.thechicks.conditionform.ui.regist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thechicks.conditionform.data.model.TimeItem;
import com.thechicks.conditionform.util.TimeUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-05-09.
 */
public class RegistManualTimeAdapter extends RecyclerView.Adapter<RegistManualTimeViewHolder> {

    private final Context mContext;
    private final ArrayList<TimeItem> mTimeItemArrayList;

    private OnListItemClickListener mOnListItemClickListener;

    public void setOnListItemClickListener(OnListItemClickListener listener){
        mOnListItemClickListener = listener;
    }

    public interface OnListItemClickListener{
        void onListItemRemove(int position);
        void onListItemChange(int position);
    }


    public RegistManualTimeAdapter(Context context){
        this.mContext = context;
        mTimeItemArrayList = new ArrayList<>();
        initData();
    }

    private void initData(){
        //Todo: default로 설정된 식사시간으로 3개 추가
        TimeItem timeItem1 = new TimeItem(TimeUtils.getCurrentUnixTimeStamp());
        addItem(timeItem1, getItemCount());

        TimeItem timeItem2 = new TimeItem(TimeUtils.getCurrentUnixTimeStamp());
        addItem(timeItem2, getItemCount());

        TimeItem timeItem3 = new TimeItem(TimeUtils.getCurrentUnixTimeStamp());
        addItem(timeItem3, getItemCount());
    }

    @Override
    public RegistManualTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RegistManualTimeViewHolder.newInstance(parent, mOnListItemClickListener);
    }

    @Override
    public void onBindViewHolder(RegistManualTimeViewHolder holder, int position) {

        TimeItem item = mTimeItemArrayList.get(position);

        holder.bind(item);
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

    public ArrayList<TimeItem> getTimeItemArrayList() {
        return mTimeItemArrayList;
    }
}
