package com.thechicks.conditionform;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.thebluealliance.spectrum.SpectrumDialog;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016-04-25.
 */
public class RegistManualActivity extends AppCompatActivity {

    public static final String TAG = RegistManualActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.imageView_label)
    ImageView ivLabel;

    @Bind(R.id.editText_disease_name)
    EditText etDiseaseName;

    @Bind(R.id.editText_pill_name)
    EditText etPillName;

    @Bind(R.id.textView_date_start)
    TextView tvDateStart;

    @Bind(R.id.textView_date_end)
    TextView tvDateEnd;

    @Bind(R.id.recyclerView_pill)
    RecyclerView rvPill;

    @Bind(R.id.recyclerView_time)
    RecyclerView rvTime;

    @Bind(R.id.spinner_dosage_type)
    Spinner spinnerDosageType;

    @Bind(R.id.linearLayout_dosage_type_interval)
    LinearLayout llTypeInterval;

    @Bind(R.id.linearLayout_dosage_type_normal)
    LinearLayout llTypeNormal;

    @Bind(R.id.spinner_time_interval)
    Spinner spinnerTimeInterval;

    @Bind(R.id.textView_time_start)
    TextView tvTimeStart;

    @Bind(R.id.spinner_time_count)
    Spinner spinnerTimeCount;

    RegistManualPillAdapter mRegistManualPillAdapter;
    RegistManualTimeAdapter mRegistManualTimeAdapter;

    ArrayAdapter<CharSequence> spinnerDosageTypeAdapter;
    ArrayAdapter<CharSequence> spinnerTimeIntervalAdapter;
    ArrayAdapter<CharSequence> spinnerTimeCountAdapter;

    //시간 관리용
    int currentDisplayYear;
    int currentDisplayMonth;
    int currentDisplayDay;
    long currentDayTimestamp;

    //시작 날짜
    int startYear;
    int startMonth;
    int startDay;
    long startDateTimestamp;

    //끝 날짜
    int endYear;
    int endMonth;
    int endDay;
    long endDateTimestamp;

    int dosageTypeIndex;  //복용 타입
    int timeInterval;  //시간 간격
    int dosageCountTotal;  //반복 횟수
    long timeStart;  //시작 시간

    //label color
    String strLabelColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_manual);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
//            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
//            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mRegistManualPillAdapter = new RegistManualPillAdapter(this);
        mRegistManualPillAdapter.setOnListItemClickListener(new RegistManualPillAdapter.OnListItemClickListener() {
            @Override
            public void onListItemRemove(int position) {
                mRegistManualPillAdapter.removeItem(position);
            }

            @Override
            public void onListItemChange(int position) {
                mRegistManualPillAdapter.notifyItemChanged(position);
            }
        });
        rvPill.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        rvPill.setAdapter(mRegistManualPillAdapter);

        setupToday();

        spinnerDosageType.setPrompt("복용 타입을 선택해주세요.");
        spinnerDosageTypeAdapter = ArrayAdapter.createFromResource(this, R.array.dosage_type,
                android.R.layout.simple_spinner_item);
        spinnerDosageTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDosageType.setAdapter(spinnerDosageTypeAdapter);
        spinnerDosageType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            boolean initSpinner;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!initSpinner) {
                    initSpinner = true;
                    return;
                }
                Toast.makeText(RegistManualActivity.this, spinnerDosageTypeAdapter.getItem(position) + "fd", Toast.LENGTH_SHORT).show();

                //Spinner index로 타입 저장
                dosageTypeIndex = position;

                if (dosageTypeIndex == spinnerDosageTypeAdapter.getCount() - 1) {
                    llTypeNormal.setVisibility(View.GONE);
                    llTypeInterval.setVisibility(View.VISIBLE);
                } else {
                    llTypeNormal.setVisibility(View.VISIBLE);
                    llTypeInterval.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerTimeInterval.setPrompt("시간 간격을 선택해주세요.");
        spinnerTimeIntervalAdapter = ArrayAdapter.createFromResource(this, R.array.dosage_time_interval,
                android.R.layout.simple_spinner_item);
        spinnerTimeIntervalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimeInterval.setAdapter(spinnerTimeIntervalAdapter);
        spinnerTimeInterval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            boolean initSpinner;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!initSpinner) {
                    initSpinner = true;
                    return;
                }

                timeInterval = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerTimeCount.setPrompt("반복 횟수를 선택해주세요.");
        spinnerTimeCountAdapter = ArrayAdapter.createFromResource(this, R.array.dosage_time_count,
                android.R.layout.simple_spinner_item);
        spinnerTimeCountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimeCount.setAdapter(spinnerTimeCountAdapter);
        spinnerTimeCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            boolean initSpinner = false;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!initSpinner) {
                    initSpinner = true;
                    return;
                }

                dosageCountTotal = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mRegistManualTimeAdapter = new RegistManualTimeAdapter(this);
        mRegistManualTimeAdapter.setOnListItemClickListener(new RegistManualTimeAdapter.OnListItemClickListener() {
            @Override
            public void onListItemRemove(int position) {
                mRegistManualTimeAdapter.removeItem(position);
            }

            @Override
            public void onListItemChange(int position) {
                mRegistManualTimeAdapter.notifyItemChanged(position);
            }
        });
        rvTime.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        rvTime.setAdapter(mRegistManualTimeAdapter);

        Log.e(TAG, " " + TimeUtils.getCurrentUnixTimeStamp());

        strLabelColor = "#ffffff";
        ((GradientDrawable) ivLabel.getBackground()).setColor(Color.parseColor(strLabelColor));  //컬러 설정

        dosageTypeIndex = 0;
        timeInterval = 0;
        dosageCountTotal = 0;
        timeStart = TimeUtils.getCurrentUnixTimeStamp();
    }

    public void setupToday() {
        final Calendar calendar = Calendar.getInstance();
        currentDayTimestamp = TimeUtils.getTodayUnixTimeStamp();

        currentDisplayYear = TimeUtils.timestampToYear(currentDayTimestamp);
        currentDisplayMonth = TimeUtils.timestampToMonth(currentDayTimestamp);
        currentDisplayDay = TimeUtils.timestampToDay(currentDayTimestamp);

        Log.e(TAG, currentDisplayYear + "년 " + currentDisplayMonth + "월 " + currentDisplayDay + "일");
    }

    @OnClick(R.id.imageView_label)
    public void onClickLabel() {
        //Todo: custom color picker로 change

        new SpectrumDialog.Builder(RegistManualActivity.this)
                .setColors(R.array.label_colors)
                .setSelectedColorRes(R.color.color_1)
                .setDismissOnColorSelected(false)
                .setOutlineWidth(2)
                .setOnColorSelectedListener(new SpectrumDialog.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(boolean positiveResult, @ColorInt int color) {
                        if (positiveResult) {

                            strLabelColor = "#" + Integer.toHexString(color).toUpperCase();
                            ((GradientDrawable) ivLabel.getBackground()).setColor(Color.parseColor(strLabelColor));  //컬러 설정

                            Toast.makeText(RegistManualActivity.this, "Color selected: " + strLabelColor, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegistManualActivity.this, "Dialog cancelled", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).build().show(getSupportFragmentManager(), "label_color");
    }

    @OnClick(R.id.button_pill_add)
    public void onClickPillAdd() {
        Pill pill = new Pill();
        pill.setKoName(etPillName.getText().toString());

        mRegistManualPillAdapter.addItem(pill, mRegistManualPillAdapter.getItemCount());
        etPillName.setText("");
    }

    @OnClick(R.id.button_time_add)
    public void onClickTimeAdd() {
        TimeItem timeItem = new TimeItem(TimeUtils.getCurrentUnixTimeStamp());

        mRegistManualTimeAdapter.addItem(timeItem, mRegistManualTimeAdapter.getItemCount());
    }

    @OnClick(R.id.textView_time_start)
    public void onClickTimeStart() {

        int hour = TimeUtils.timestampToHour(timeStart);
        int minute = TimeUtils.timestampToMinute(timeStart);

        new TimePickerDialog(RegistManualActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeStart = TimeUtils.getHourMinuteUnixTimeStamp(hourOfDay, minute);
                tvTimeStart.setText(TimeUtils.unixTimeStampToStringTime(timeStart));
            }
        }, hour, minute, true).show();
    }

    @OnClick(R.id.textView_date_start)
    public void onClickDateStart() {

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                startYear = year;
                startMonth = monthOfYear + 1;
                startDay = dayOfMonth;

                Log.e(TAG, startYear + "년 " + startMonth + "월 " + startDay + "일");

                startDateTimestamp = TimeUtils.getDayTimeStamp(startYear, startMonth, startDay);
                tvDateStart.setText(TimeUtils.unixTimeStampToStringDateYearMonthDay(startDateTimestamp));
            }
        }, currentDisplayYear, currentDisplayMonth - 1, currentDisplayDay).show();
    }

    @OnClick(R.id.textView_date_end)
    public void onClickDateEnd() {

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                endYear = year;
                endMonth = monthOfYear + 1;
                endDay = dayOfMonth;

                Log.e(TAG, endYear + "년 " + endMonth + "월 " + endDay + "일");

                endDateTimestamp = TimeUtils.getDayTimeStamp(endYear, endMonth, endDay);
                Log.e(TAG, " " + endDateTimestamp);
                tvDateEnd.setText(TimeUtils.unixTimeStampToStringDateYearMonthDay(endDateTimestamp));
            }
        }, currentDisplayYear, currentDisplayMonth - 1, currentDisplayDay).show();
    }

    @OnClick(R.id.button_cancel)
    public void onClickCancel() {
        finish();
    }

    @OnClick(R.id.button_confirm)
    public void onClickConfirm() {

        //모델객체에 저장
        Disease disease = new Disease();
        disease.setColor(strLabelColor);
        disease.setImg(R.mipmap.ic_launcher);
        disease.setName(etDiseaseName.getText().toString());
        disease.setPillArrayList(mRegistManualPillAdapter.getPillArrayList());
        disease.setDateStart(startDateTimestamp);
        disease.setDateEnd(endDateTimestamp);

        switch (dosageTypeIndex) {
            case 0:  //매일
                disease.setDosageType(Disease.DOSAGE_TYPE_EVERYDAY);
                disease.setTimeItems(mRegistManualTimeAdapter.getTimeItemArrayList());
                break;
            case 1:  //2일마다
                disease.setDosageType(Disease.DOSAGE_TYPE_TWODAY);
                disease.setTimeItems(mRegistManualTimeAdapter.getTimeItemArrayList());
                break;
            case 2:  //3일마다
                disease.setDosageType(Disease.DOSAGE_TYPE_THREEDAY);
                disease.setTimeItems(mRegistManualTimeAdapter.getTimeItemArrayList());
                break;
            case 3:  //시간마다
                disease.setDosageType(Disease.DOSAGE_TYPE_EVERYHOUR);
                disease.setTimeStartHour(TimeUtils.timestampToHour(timeStart));
                disease.setTimeStartMinute(TimeUtils.timestampToMinute(timeStart));
                disease.setTimeInterval(timeInterval);
                disease.setDosageTotal(dosageCountTotal);
                break;
        }

        //Todo: DB에 저장

        //Todo: 알람 등록

        Log.d(TAG, " " + disease);

//        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}