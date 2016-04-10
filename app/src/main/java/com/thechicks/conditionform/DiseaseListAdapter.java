package com.thechicks.conditionform;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Dong on 2016-04-09.
 */
public class DiseaseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_NORMAL = 0;
    public static final int VIEW_TYPE_INTERVAL = 1;

    private final Context mContext;
    private final ArrayList<Disease> mDiseaseList;

    public DiseaseListAdapter(Context context) {
        mContext = context;
        mDiseaseList = new ArrayList<>();

        initData();
    }

    public void initData() {
        for (int i = 0; i < 10; i++) {
            addItem(new Disease(), i);
        }
    }

    //Todo: 객체의 뷰타입에 따라 리턴되게 수정
    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? VIEW_TYPE_NORMAL : VIEW_TYPE_INTERVAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return DiseaseNormalViewHolder.newInstance(parent);
            case VIEW_TYPE_INTERVAL:
                return DiseaseIntervalViewHolder.newInstance(parent);
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
