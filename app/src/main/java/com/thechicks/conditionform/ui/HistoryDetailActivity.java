package com.thechicks.conditionform.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.database.ConditionformDao;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.data.model.History;
import com.thechicks.conditionform.data.model.Pill;
import com.thechicks.conditionform.data.model.TimeItem;
import com.thechicks.conditionform.data.remote.BackendHelper;
import com.thechicks.conditionform.ui.detail.PillDetailsActivity;
import com.thechicks.conditionform.ui.regist.RegistManualPillAdapter;
import com.thechicks.conditionform.ui.regist.RegistManualTimeAdapter;
import com.thechicks.conditionform.util.AsyncHandler;
import com.thechicks.conditionform.util.Constants;
import com.thechicks.conditionform.util.PreferencesUtils;
import com.thechicks.conditionform.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryDetailActivity extends AppCompatActivity {

    public static final String TAG = HistoryDetailActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.imageView_label)
    ImageView ivLabel;

    @Bind(R.id.textView_disease_name)
    TextView tvDiseaseName;

    @Bind(R.id.textView_date_start)
    TextView tvDateStart;

    @Bind(R.id.textView_date_end)
    TextView tvDateEnd;

    @Bind(R.id.recyclerView_pill)
    RecyclerView rvPill;

    @Bind(R.id.recyclerView_time)
    RecyclerView rvTime;

    @Bind(R.id.textView_dosage_type)
    TextView tvDosageType;

    @Bind(R.id.linearLayout_dosage_type_interval)
    LinearLayout llTypeInterval;

    @Bind(R.id.linearLayout_dosage_type_normal)
    LinearLayout llTypeNormal;

    @Bind(R.id.textView_time_interval)
    TextView tvTimeInterval;

    @Bind(R.id.textView_time_start)
    TextView tvTimeStart;

    @Bind(R.id.textView_time_count)
    TextView tvTimeCount;

    HistoryDetailPillAdapter mHistoryDetailPillAdapter;
    RegistManualTimeAdapter mRegistManualTimeAdapter;

    int diseaseId;  //쿼리할 Disease id

    ConditionformDao conditionformDao;

    BackendHelper mBackendHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent receiveIntent = getIntent();
        diseaseId = receiveIntent.getIntExtra("diseaseId", 0);

        conditionformDao = new ConditionformDao(this);

        mBackendHelper = BackendHelper.getInstance();

        mHistoryDetailPillAdapter = new HistoryDetailPillAdapter(this);
        mHistoryDetailPillAdapter.setOnListItemClickListener(new HistoryDetailPillAdapter.OnListItemClickListener() {
            @Override
            public void onListItemClick(Pill pill) {
                //약품 검색
                Call<JsonArray> call = mBackendHelper.getPillInformation(pill.getKoName());
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        JsonArray jaRoot = response.body();

                        Log.e(TAG, " onResponse()");

                        if (jaRoot != null) {

                            Log.e(TAG, " " + jaRoot);
                            Pill pill = new Gson().fromJson(jaRoot.get(0), Pill.class);

                            Intent intent = new Intent(HistoryDetailActivity.this, PillDetailsActivity.class);
                            intent.putExtra("pill", pill);
                            startActivity(intent);
                        } else {
                            Log.e(TAG, "jaRoot null");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e(TAG, " Throwable is " + t);
                    }
                });
            }
        });

        rvPill.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvPill.setAdapter(mHistoryDetailPillAdapter);

        mRegistManualTimeAdapter = new RegistManualTimeAdapter(this, false);
        //Todo: CheckBox enabled

        rvTime.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvTime.setAdapter(mRegistManualTimeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                final Disease disease = conditionformDao.findDiseaseById(diseaseId);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Todo: Data set
                        tvDiseaseName.setText(disease.getName());
                        ((GradientDrawable) ivLabel.getBackground()).setColor(Color.parseColor(disease.getColor()));
                        tvDateStart.setText(TimeUtils.unixTimeStampToStringDateYearMonthDay(disease.getDateStart()));
                        tvDateEnd.setText(TimeUtils.unixTimeStampToStringDateYearMonthDay(disease.getDateEnd()));

                        int dosageType = disease.getDosageType();

                        switch (dosageType) {
                            case Constants.DOSAGE_TYPE_EVERYDAY:
                                tvDosageType.setText("매일");
                                break;
                            case Constants.DOSAGE_TYPE_TWODAY:
                                tvDosageType.setText("2일마다");
                                break;
                            case Constants.DOSAGE_TYPE_THREEDAY:
                                tvDosageType.setText("3일마다");
                                break;
                            case Constants.DOSAGE_TYPE_EVERYHOUR:
                                tvDosageType.setText("시간마다");
                                break;
                        }

                        mHistoryDetailPillAdapter.setPillArrayList(disease.getPillArrayList());

                        switch (dosageType) {
                            case Constants.DOSAGE_TYPE_EVERYDAY:
                            case Constants.DOSAGE_TYPE_TWODAY:
                            case Constants.DOSAGE_TYPE_THREEDAY:
                                llTypeNormal.setVisibility(View.VISIBLE);
                                llTypeInterval.setVisibility(View.GONE);

                                //시간 enabled set
                                TimeItem timeWakeUp = new TimeItem(PreferencesUtils.getPreferencesLong(HistoryDetailActivity.this, Constants.PREF_TIME_WAKEUP), disease.isEnabledWakeup());
                                TimeItem timeMorning = new TimeItem(PreferencesUtils.getPreferencesLong(HistoryDetailActivity.this, Constants.PREF_TIME_MORNING), disease.isEnabledMorning());
                                TimeItem timeLunch = new TimeItem(PreferencesUtils.getPreferencesLong(HistoryDetailActivity.this, Constants.PREF_TIME_LUNCH), disease.isEnabledLunch());
                                TimeItem timeEvening = new TimeItem(PreferencesUtils.getPreferencesLong(HistoryDetailActivity.this, Constants.PREF_TIME_EVENING), disease.isEnabledEvening());
                                TimeItem timeSleep = new TimeItem(PreferencesUtils.getPreferencesLong(HistoryDetailActivity.this, Constants.PREF_TIME_SLEEP), disease.isEnabledSleep());

                                ArrayList<TimeItem> timeItemArrayList = new ArrayList<TimeItem>();
                                timeItemArrayList.add(timeWakeUp);
                                timeItemArrayList.add(timeMorning);
                                timeItemArrayList.add(timeLunch);
                                timeItemArrayList.add(timeEvening);
                                timeItemArrayList.add(timeSleep);
                                mRegistManualTimeAdapter.setTimeItemArrayList(timeItemArrayList);

                                break;
                            case Constants.DOSAGE_TYPE_EVERYHOUR:
                                llTypeNormal.setVisibility(View.GONE);
                                llTypeInterval.setVisibility(View.VISIBLE);
                                tvTimeInterval.setText(String.format("%d시간", disease.getTimeInterval()));
                                tvTimeStart.setText(disease.getTimeStartHour() + ":" + disease.getTimeStartMinute());
                                tvTimeCount.setText(String.format("%d회", disease.getDosageTotal()));
                        }
                    }
                });
            }
        });
    }

    @OnClick(R.id.button_confirm)
    public void onClickConfirm() {
        finish();
    }

    @OnClick(R.id.button_update)
    public void onClickUpdate() {
        //Todo: 수정
    }

    @OnClick(R.id.button_delete)
    public void onClickDelete() {
        //Todo: 삭제
    }
}
