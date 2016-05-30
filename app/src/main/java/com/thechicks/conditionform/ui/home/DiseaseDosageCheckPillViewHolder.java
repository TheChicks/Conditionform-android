package com.thechicks.conditionform.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.Pill;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by opklnm102 on 2016-05-30.
 */
public class DiseaseDosageCheckPillViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.textView_pill_name)
    TextView tvPillName;

    public static DiseaseDosageCheckPillViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_dosage_check_pill, parent, false);
        return new DiseaseDosageCheckPillViewHolder(itemView);
    }

    private DiseaseDosageCheckPillViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Pill pill) {
        tvPillName.setText(pill.getKoName());
    }
}
