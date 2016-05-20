package com.thechicks.conditionform.ui.regist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thechicks.conditionform.data.model.TimeItem;
import com.thechicks.conditionform.util.Constants;
import com.thechicks.conditionform.util.PreferencesUtils;
import com.thechicks.conditionform.util.TimeUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-05-09.
 */
public class RegistManualTimeAdapter extends RecyclerView.Adapter<RegistManualTimeViewHolder> {

    private final Context mContext;
    private final ArrayList<TimeItem> mTimeItemArrayList;

    private OnListItemClickListener mOnListItemClickListener;

    public void setOnListItemClickListener(OnListItemClickListener listener) {
        mOnListItemClickListener = listener;
    }

    public interface OnListItemClickListener {
        void onListItemCheck(int position, boolean isChecked);

        void onListItemChange(int position);
    }


    public RegistManualTimeAdapter(Context context) {
        this.mContext = context;
        mTimeItemArrayList = new ArrayList<>();
        initData();
    }

    private void initData() {
        //default로 설정된 시간 5개로 고정하고 활성시킬지 안시킬지 결정하게 하자

        TimeItem timeWakeUp = new TimeItem(PreferencesUtils.getPreferencesLong(mContext, Constants.PREF_TIME_WAKEUP));
        addItem(timeWakeUp, getItemCount());

        TimeItem timeMorning = new TimeItem(PreferencesUtils.getPreferencesLong(mContext, Constants.PREF_TIME_MORNING));
        addItem(timeMorning, getItemCount());

        TimeItem timeLunch = new TimeItem(PreferencesUtils.getPreferencesLong(mContext, Constants.PREF_TIME_LUNCH));
        addItem(timeLunch, getItemCount());

        TimeItem timeEvening = new TimeItem(PreferencesUtils.getPreferencesLong(mContext, Constants.PREF_TIME_EVENING));
        addItem(timeEvening, getItemCount());

        TimeItem timeSleep = new TimeItem(PreferencesUtils.getPreferencesLong(mContext, Constants.PREF_TIME_SLEEP));
        addItem(timeSleep, getItemCount());
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
}
