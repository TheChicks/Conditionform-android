package com.thechicks.conditionform;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-27.
 */
public class PillAdapter extends RecyclerView.Adapter<PillViewHolder> {
    private final Context context;
    private final ArrayList<MyPill> items;
    private OnListItemClickListener mOnListItemClickListener;

    public PillAdapter(Context context){
        this.context = context;
        items = new ArrayList<MyPill>();
    }

    public void setOnListItemClickListener(OnListItemClickListener listener){
        mOnListItemClickListener = listener;
    }

    public void addItem(String mName, int position){
        MyPill addInfo = new MyPill();
        addInfo.setName(mName);

        items.add(position, addInfo);
        notifyItemInserted(position);
    }
    public void removeItem(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }
    public interface OnListItemClickListener{
        void onListItemClick(MyPill myPill);
    }
    @Override
    public PillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.items_regist_pill_list, parent, false);

        return PillViewHolder.newInstance(parent, mOnListItemClickListener);
    }

    @Override
    public void onBindViewHolder(final PillViewHolder holder, int position) {
        holder.tvTitle.setText(items.get(position).getName());
        holder.rmButton.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                removeItem(holder.getPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}