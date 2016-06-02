package com.thechicks.conditionform.alert;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thechicks.conditionform.data.model.AlarmItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-23.
 */
public class AlarmListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private ArrayList<AlarmItem> mAlarmItems;

    public AlarmListAdapter(Context context) {
        mContext = context;
        mAlarmItems = new ArrayList<>();

        initData();
    }

    public void initData() {
        for (int i = 0; i < 1; i++) {
            if (i % 2 == 0)
                addItem(new AlarmItem("감기", "#FF2C49A3"), i);
            else
                addItem(new AlarmItem("혈압", "#FFF4FF22"), i);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AlarmViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlarmItem alarmItem = mAlarmItems.get(position);
        ((AlarmViewHolder) holder).bind(alarmItem);
    }

    @Override
    public int getItemCount() {
        return mAlarmItems.size();
    }

    public void addItem(AlarmItem alarmItem, int position) {
        mAlarmItems.add(position, alarmItem);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mAlarmItems.remove(position);
        notifyItemRemoved(position);
    }
}
