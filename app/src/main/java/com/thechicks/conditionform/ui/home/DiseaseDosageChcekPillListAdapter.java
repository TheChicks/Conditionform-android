package com.thechicks.conditionform.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thechicks.conditionform.data.model.Pill;

import java.util.ArrayList;

/**
 * Created by opklnm102 on 2016-05-30.
 */
public class DiseaseDosageChcekPillListAdapter extends RecyclerView.Adapter<DiseaseDosageCheckPillViewHolder> {

    private final Context mContext;
    private ArrayList<Pill> mPillArrayList;

    public DiseaseDosageChcekPillListAdapter(Context context) {
        mContext = context;
        mPillArrayList = new ArrayList<>();
    }

    @Override
    public DiseaseDosageCheckPillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DiseaseDosageCheckPillViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(DiseaseDosageCheckPillViewHolder holder, int position) {
        Pill pill = mPillArrayList.get(position);
        holder.bind(pill);
    }

    @Override
    public int getItemCount() {
        return mPillArrayList.size();
    }

    public void addItem(Pill item, int position) {
        mPillArrayList.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mPillArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public ArrayList<Pill> getPillArrayList() {
        return mPillArrayList;
    }

    public void setPillArrayList(ArrayList<Pill> pillArrayList) {
        mPillArrayList = pillArrayList;
        notifyDataSetChanged();
    }
}
