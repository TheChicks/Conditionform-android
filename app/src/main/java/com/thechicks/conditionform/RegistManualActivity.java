package com.thechicks.conditionform;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Administrator on 2016-04-25.
 */
public class RegistManualActivity extends AppCompatActivity {
    static final int DATE_DIALOG_START = 1;
    static final int DATE_DIALOG_END = 2;
    String startDate, endDate;
    TextView start, end;
    private int mYear, mMonth, mDay;
    private int startYear, startMonth, startDay;
    private int endYear, endMonth, endDay;
    static int pillIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_manual);

        final RecyclerView pillRecyclerView = (RecyclerView)findViewById(R.id.pills);
        final PillAdapter pillAdapter = new PillAdapter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pillRecyclerView.setLayoutManager(layoutManager);
        pillRecyclerView.setAdapter(pillAdapter);

        Button pillAdd = (Button)findViewById(R.id.add_pill);
        pillAdd.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.edit_pill_name);
                pillAdapter.addItem(et.getText().toString(), 0);
                et.setText("");
                pillIndex++;
            }
        });

        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DATE);

        start = (TextView) findViewById(R.id.start);
        start.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_START);
                updateStartDate();
            }
        });

        end = (TextView) findViewById(R.id.end);
        end.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_END);
                updateEndDate();
            }
        });
    }

    private void updateStartDate() {
        startDate = startYear + "/" + (startMonth + 1) + "/" + startDay;
        start.setText(startDate);
    }

    private void updateEndDate() {
        endDate = endYear + "/" + (endMonth + 1) + "/" + endDay;
        end.setText(endDate);
    }

    private DatePickerDialog.OnDateSetListener mDateSetStartListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    startYear = year;
                    startMonth = monthOfYear;
                    startDay = dayOfMonth;
                    updateStartDate();
                }
            };
    private DatePickerDialog.OnDateSetListener mDateSetEndListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    endYear = year;
                    endMonth = monthOfYear;
                    endDay = dayOfMonth;
                    updateEndDate();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_START:
                return new DatePickerDialog(this, mDateSetStartListener, mYear, mMonth, mDay);
            case DATE_DIALOG_END:
                return new DatePickerDialog(this, mDateSetEndListener, mYear, mMonth, mDay);
        }
        return null;
    }
}