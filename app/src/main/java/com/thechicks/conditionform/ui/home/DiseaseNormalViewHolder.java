package com.thechicks.conditionform.ui.home;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.R;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;

/**
 * Created by Dong on 2016-04-09.
 */
public class DiseaseNormalViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.ll_head)
    LinearLayout llHead;

    @Bind(R.id.ll_content)
    LinearLayout llContent;

    @Bind(R.id.imageView_label)
    ImageView ivLabel;

    @Bind(R.id.textView_label)
    TextView tvLabel;

    @Bind(R.id.checkBox_morning)
    CheckBox cbMorning;

    @Bind(R.id.checkBox_lunch)
    CheckBox cbLunch;

    @Bind(R.id.checkBox_dinner)
    CheckBox cbDinner;

    @Bind(R.id.checkBox_sleep)
    CheckBox cbSleep;

    @BindDimen(R.dimen.disease_item_border_line)
    int border;

    GradientDrawable gradientDrawable;

    View mView;

    DiseaseListAdapter.OnListItemClickListener mListener;

    //팩토리 메소드로 생성자를 제한. layout을 뷰홀더에서 알 수 있게
    public static DiseaseNormalViewHolder newInstance(ViewGroup parent, DiseaseListAdapter.OnListItemClickListener listener) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_disease_normal, parent, false);
        return new DiseaseNormalViewHolder(itemView, listener);
    }

    private DiseaseNormalViewHolder(View itemView, DiseaseListAdapter.OnListItemClickListener listener) {
        super(itemView);

        mView = itemView;
        gradientDrawable = new GradientDrawable();  //border 그리기용

        mListener = listener;

        ButterKnife.bind(this, mView);
    }

    public void bind(final Disease disease) {

        //레이블 이미지
        ivLabel.setImageResource(disease.getImg());

        //레이블 텍스트
        tvLabel.setText(disease.getName());

        //레이블 색 적용
        llHead.setBackgroundColor(Color.parseColor(disease.getColor()));
        gradientDrawable.setStroke(border, Color.parseColor(disease.getColor()));
        llContent.setBackground(gradientDrawable);

        //Todo: CheckBox 리스너 제거,
        //이미지 표시 여부 결정
        //약 먹은거 체크
        if (disease.isShowMorning()) {  //표시
            cbMorning.setVisibility(View.VISIBLE);

            cbMorning.setChecked(disease.isTakeMorning());
            cbMorning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    disease.setTakeMorning(isChecked);
                }
            });
        } else {  //표시X
            cbMorning.setVisibility(View.GONE);
        }

        if (disease.isShowLunch()) {  //표시
            cbLunch.setVisibility(View.VISIBLE);
            cbLunch.setChecked(disease.isTakeLunch());
            cbLunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    disease.setTakeLunch(isChecked);
                }
            });
        } else {  //표시X
            cbLunch.setVisibility(View.GONE);
        }

        if (disease.isShowEvening()) {  //표시
            cbDinner.setVisibility(View.VISIBLE);
            cbDinner.setChecked(disease.isTakeEvening());
            cbDinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    disease.setTakeEvening(isChecked);
                }
            });
        } else {  //표시X
            cbDinner.setVisibility(View.GONE);
        }

        if (disease.isShowSleep()) {  //표시
            cbSleep.setVisibility(View.VISIBLE);
            cbSleep.setChecked(disease.isTakeSleep());
            cbSleep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    disease.setTakeSleep(isChecked);
                }
            });
        } else {  //표시X
            cbSleep.setVisibility(View.GONE);
        }

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onListItemClick(disease);
                }
            }
        });

    }
}
