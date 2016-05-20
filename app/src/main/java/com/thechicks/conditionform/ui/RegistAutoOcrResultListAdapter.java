package com.thechicks.conditionform.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thechicks.conditionform.data.model.OcrResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by opklnm102 on 2016-05-20.
 */
public class RegistAutoOcrResultListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private List<OcrResult> mOcrResultList;

    public RegistAutoOcrResultListAdapter(Context context) {
        mContext = context;
        mOcrResultList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RegistAutoOcrResultViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        OcrResult ocrResult = mOcrResultList.get(position);
        ((RegistAutoOcrResultViewHolder) holder).bind(ocrResult);
    }

    @Override
    public int getItemCount() {
        return mOcrResultList.size();
    }

    public void addItem(OcrResult ocrResult, int position) {
        mOcrResultList.add(position, ocrResult);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mOcrResultList.remove(position);
        notifyItemRemoved(position);
    }

    public void setData(ArrayList<OcrResult> data) {
        mOcrResultList = data;
        notifyDataSetChanged();
    }
}
