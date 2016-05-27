package com.thechicks.conditionform.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thechicks.conditionform.data.model.Pill;
import com.thechicks.conditionform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PillDetailsActivity extends AppCompatActivity {

    public static final String TAG = PillDetailsActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.imageView_pill)
    ImageView ivPill;  //이미지

    @Bind(R.id.textView_pill_name)
    TextView tvPillName;  //약이름한글(영문)

    @Bind(R.id.textView_ingredient)
    TextView tvIngredient;  //성분명

    @Bind(R.id.textView_assortment)
    TextView tvAssortment;  //전문/일반 //구분

    @Bind(R.id.textView_unitarinessOrComplexness)
    TextView tvUnitarinessOrComplexness;   //단일/복합

    @Bind(R.id.textView_manufactureAssortment)
    TextView tvManufactureAssortment;   //제조/수입사

    @Bind(R.id.textView_seller)
    TextView tvSeller;   //판매사

    @Bind(R.id.textView_formulation)
    TextView tvFormulation;   //제형

    @Bind(R.id.textView_takingRoute)
    TextView tvTakingRoute;   //투여경로

    @Bind(R.id.textView_koreaFoodAndDrugAdministrationCategory)
    TextView tvKoreaFoodAndDrugAdministrationCategory;   //식약처 분류

    @Bind(R.id.textView_insuranceCode)
    TextView tvIinsuranceCode;   //보험코드

    @Bind(R.id.textView_combinationTaboo)
    TextView tvCombinationTaboo;   //병용금기

    @Bind(R.id.textView_ageTaboo)
    TextView tvAgeTaboo;   //연령금기

    @Bind(R.id.textView_pregnantTaboo)
    TextView tvPregnantTaboo;   //임부금기

    @Bind(R.id.textView_oldManCaution)
    TextView tvOldManCaution;   //노인주의

    @Bind(R.id.textView_volumeAndTreatmentPeriodCaution)
    TextView tvVolumeAndTreatmentPeriodCaution;   //용량/투여기간주의

    @Bind(R.id.textView_divisionCaution)
    TextView tvDivisionCaution;   //분할주의

    @Bind(R.id.textView_bloodDonationProhibition)
    TextView tvBloodDonationProhibition;   //헌혈금지

    @Bind(R.id.textView_shapeInfo)
    TextView tvShapeInfo;   //성상

    @Bind(R.id.textView_packingUnit)
    TextView tvPackingUnit;   //포장단위

    @Bind(R.id.textView_storagintMethod)
    TextView tvStoragintMethod;   //저장방법

    @Bind(R.id.textView_efficacy)
    TextView tvEfficacy;   //효능효과

    @Bind(R.id.textView_dosage)
    TextView tvDosage;   //용법용량

    @Bind(R.id.textView_precaution)
    TextView tvPrecaution;   //사용상 주의사항

    @Bind(R.id.textView_medicationGuide)
    TextView tvMedicationGuide;   //복약지도


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_details);

        ButterKnife.bind(this);

        // 데이터 받아서 표시
        Intent receivceIntent = getIntent();

        Pill pill = (Pill) receivceIntent.getSerializableExtra("pill");

        mToolbar.setTitle(pill.getKoName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setInformation(pill);
    }

    private void setInformation(Pill pill) {

        Glide.with(this)
                .load(pill.getImageUrl())
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(ivPill);

        tvPillName.setText(String.format("%s(%s)", pill.getKoName(), pill.getEnName()));
        tvIngredient.setText(pill.getIngredient());
        tvAssortment.setText(pill.getAssortment());
        tvUnitarinessOrComplexness.setText(pill.getUnitarinessOrComplexness());
        tvManufactureAssortment.setText(pill.getManufactureAssortment());
        tvSeller.setText(pill.getSeller());
        tvFormulation.setText(pill.getFormulation());
        tvTakingRoute.setText(pill.getTakingRoute());
        tvKoreaFoodAndDrugAdministrationCategory.setText(pill.getKoreaFoodAndDrugAdministrationCategory());
        tvIinsuranceCode.setText(pill.getInsuranceCode());
        tvCombinationTaboo.setText(pill.getCombinationTaboo());
        tvAgeTaboo.setText(pill.getAgeTaboo());
        tvPregnantTaboo.setText(pill.getPregnantTaboo());
        tvOldManCaution.setText(pill.getOldManCaution());
        tvVolumeAndTreatmentPeriodCaution.setText(pill.getVolumeAndTreatmentPeriodCaution());
        tvDivisionCaution.setText(pill.getDivisionCaution());
        tvBloodDonationProhibition.setText(pill.getBloodDonationProhibition());
        tvShapeInfo.setText(pill.getShapeInfo());
        tvPackingUnit.setText(pill.getPackingUnit());
        tvStoragintMethod.setText(pill.getStoragintMethod());
        tvEfficacy.setText(pill.getEfficacy());
        tvDosage.setText(pill.getDosage());
        tvPrecaution.setText(pill.getPrecaution());
        tvMedicationGuide.setText(pill.getMedicationGuide());
    }

    //Back Button event handle
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
