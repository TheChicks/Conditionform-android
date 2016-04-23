package com.thechicks.conditionform;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-23.
 */
public class AlarmListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final ArrayList<AlarmItem> mAlarmItems;

    public AlarmListAdapter(Context context) {
        mContext = context;
        mAlarmItems = new ArrayList<>();

        initData();
    }

    public void initData() {
        addItem(new AlarmItem("감기", "#FF2C49A3"));
        addItem(new AlarmItem("혈압", "#FFF4FF22"));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AlarmViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlarmItem alarmItem = mAlarmItems.get(position);
        ((AlarmViewHolder)holder).bind(alarmItem);
    }

    @Override
    public int getItemCount() {
        return mAlarmItems.size();
    }

    public void addItem(AlarmItem alarmItem) {
        mAlarmItems.add(alarmItem);
    }
}