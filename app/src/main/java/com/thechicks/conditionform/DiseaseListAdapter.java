package com.thechicks.conditionform;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2016-04-09.
 */
public class DiseaseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_disease, parent, false);
        return new DiseaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Disease disease = mDiseaseList.get(position);
        ((DiseaseViewHolder) holder).bind(disease);
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
