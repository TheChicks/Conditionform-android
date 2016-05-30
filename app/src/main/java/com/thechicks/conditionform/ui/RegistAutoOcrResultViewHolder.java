package com.thechicks.conditionform.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.OcrResult;
import com.thechicks.conditionform.data.model.Pill;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by opklnm102 on 2016-05-20.
 */
public class RegistAutoOcrResultViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.editText_pill_insuranceCode)
    EditText etPillInsuranceCode;

    @Bind(R.id.editText_pill_name)
    EditText etPillName;

    @Bind(R.id.editText_dosage_total)
    EditText etDosageTotal;

    @Bind(R.id.editText_dosage_one_time)
    EditText etDosageOneTime;

    @Bind(R.id.editText_dosage_total_days)
    EditText etDosageTotalDays;

    public static RecyclerView.ViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_ocr_result, parent, false);
        return new RegistAutoOcrResultViewHolder(itemView);
    }

    public RegistAutoOcrResultViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(OcrResult ocrResult) {

        Pill pill = ocrResult.getPill();

        etPillName.setText(pill.getKoName());
        etPillInsuranceCode.setText(pill.getInsuranceCode());
        etDosageTotal.setText(String.valueOf(ocrResult.getDosageTotal()));
        etDosageOneTime.setText(String.valueOf(ocrResult.getDosageOneTime()));
        etDosageTotalDays.setText(String.valueOf(ocrResult.getDosageTotalDays()));

        //Todo: 데이터 수정시 다시 바인드
        //Todo: 리스너 달기


    }
}
