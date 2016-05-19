package com.thechicks.conditionform.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.data.model.Pill;
import com.thechicks.conditionform.util.Constants;

import java.util.ArrayList;

/**
 * Created by Dong on 2016-04-09.
 */
public class DiseaseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_NORMAL = 0;  //식사마다
    public static final int VIEW_TYPE_INTERVAL = 1;  //시간마다

    private final Context mContext;
    private final ArrayList<Disease> mDiseaseList;

    //item click event
    private OnListItemClickListener mOnListItemClickListener;

    public interface OnListItemClickListener{
        void onListItemClick(Disease disease);
    }

    public void setOnListItemClickListener(OnListItemClickListener listener){
        mOnListItemClickListener = listener;
    }

    public DiseaseListAdapter(Context context) {
        mContext = context;
        mDiseaseList = new ArrayList<>();

//        initData();
    }

    //dummy data
    public void initData() {
        for (int i = 0; i < 20; i++) {
            ArrayList<Pill> pills = new ArrayList<>();

            if (i % 2 == 0) {
//                addItem(new Disease("#7f62de", "감기", R.mipmap.ic_launcher, pills, 33333333, 333333, Disease.DOSAGE_TYPE_EVERYDAY, true, true, true, true, true, true, true, false, 2, 2, 8), i);
            } else {
//                addItem(new Disease("#2c90d7", "안약", R.mipmap.ic_launcher, pills, 33333333, 333333, Disease.DOSAGE_TYPE_EVERYHOUR, true, true, true, true, true, true, true, false, 2, 2, 8), i);
            }
//            addItem(new Disease("#554344", "몸살", R.drawable.ic_add_black_24dp, pills, 33333333, 333333, VIEW_TYPE_NORMAL, true, true, true, false, false,true,false,false,3,2,1), i);
//            addItem(new Disease("#554344", "배탈", R.mipmap.ic_launcher, pills, 33333333, 333333, VIEW_TYPE_NORMAL, true, true, false, false, true,false,false,false,3,2,1), i);
//            addItem(new Disease("#554344", "흑흑", R.drawable.ic_add_black_24dp, pills, 33333333, 333333, VIEW_TYPE_NORMAL, false, false, true, false, false,false,false,false,3,2,1), i);
        }
    }

    //객체가 가진 뷰타입에 따라 리턴
    @Override
    public int getItemViewType(int position) {
        switch (mDiseaseList.get(position).getDosageType()){
            case Constants.DOSAGE_TYPE_EVERYDAY:
                return VIEW_TYPE_NORMAL;
            case Constants.DOSAGE_TYPE_TWODAY:
                return VIEW_TYPE_NORMAL;
            case Constants.DOSAGE_TYPE_THREEDAY:
                return VIEW_TYPE_NORMAL;
            case Constants.DOSAGE_TYPE_EVERYHOUR:
                return VIEW_TYPE_INTERVAL;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return DiseaseNormalViewHolder.newInstance(parent, mOnListItemClickListener);
            case VIEW_TYPE_INTERVAL:
                return DiseaseIntervalViewHolder.newInstance(parent, mOnListItemClickListener);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Disease disease = mDiseaseList.get(position);

        if (holder instanceof DiseaseNormalViewHolder) {
            ((DiseaseNormalViewHolder) holder).bind(disease);
        } else {
            ((DiseaseIntervalViewHolder) holder).bind(disease);
        }
    }

    @Override
    public int getItemCount() {
        return mDiseaseList.size();
    }

    public void addItem(Disease disease, int position) {
        mDiseaseList.add(position, disease);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mDiseaseList.remove(position);
        notifyItemRemoved(position);
    }
}
