package com.thechicks.conditionform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PillDetailsActivity extends AppCompatActivity {

    @Bind(R.id.IvPill)
    ImageView ivPill;

    @Bind(R.id.tvPillName)
    TextView tvPillName;

    @Bind(R.id.tvCategoryWelfare)
    TextView tvCategoryWelfare;

    @Bind(R.id.tvManufactureAssort)
    TextView tvManufactureAssort;

    @Bind(R.id.tvAssortment)
    TextView tvAssortment;

    @Bind(R.id.tvInsuranceCode)
    TextView tvInsuranceCode;

    @Bind(R.id.tvPregnantRating)
    TextView tvPregnantRating;

    @Bind(R.id.tvAgeProhibit)
    TextView tvAgeProhibit;

    @Bind(R.id.tvShapeInfoAppearance)
    TextView tvShapeInfoAppearance;

    @Bind(R.id.tvShapeInfoFormulation)
    TextView tvShapeInfoFormulation;

    @Bind(R.id.tvShapeInfoShape)
    TextView tvShapeInfoShape;

    @Bind(R.id.tvShapeInfoColor)
    TextView tvShapeInfoColor;

    @Bind(R.id.tvShapeInfoIdMark)
    TextView tvShapeInfoIdMark;

    @Bind(R.id.tvIngredientInfo)
    TextView tvIngredientInfo;

    @Bind(R.id.tvStoragintMethod)
    TextView tvStoragintMethod;

    @Bind(R.id.tvEfficacy)
    TextView tvEfficacy;

    @Bind(R.id.tvDosage)
    TextView tvDosage;

    @Bind(R.id.tvPrecaution)
    TextView tvPrecaution;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_details);

        ButterKnife.bind(this);

        // 데이터 받아서 표시
        Intent intent = getIntent();

        Pill pill = (Pill)intent.getSerializableExtra("pill");

        mToolbar.setTitle(pill.getKoName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Glide.with(this)
                .load(pill.getImageUrl())
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(ivPill);


    }
}
