package com.thechicks.conditionform.ui.home;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.ui.DosageCheckButton;
import com.thechicks.conditionform.util.Constants;
import com.thechicks.conditionform.util.PreferencesUtils;
import com.thechicks.conditionform.util.TimeUtils;
import com.thechicks.conditionform.util.ViewUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DiseaseDosageCheckBottomSheetDialogFragment extends BottomSheetDialogFragment {

    public static final String TAG = DiseaseDosageCheckBottomSheetDialogFragment.class.getCanonicalName();

    private static final String ARG_PARAM_DISEASE = "param_disease";

    private Disease mDisease;

    @Bind(R.id.linearLayout_label)
    LinearLayout llLabel;

    @Bind(R.id.imageView_label)
    ImageView ivLabel;

    @Bind(R.id.textView_label)
    TextView tvLabel;

    @Bind(R.id.textView_dosage_date_title)
    TextView tvDosageDateTitle;

    @Bind(R.id.view_dosage_date_line)
    View vDosageDateLine;

    @Bind(R.id.textView_date_start)
    TextView tvDateStart;

    @Bind(R.id.textView_date_end)
    TextView tvDateEnd;

    @Bind(R.id.linearLayout_dosage_type_interval)
    LinearLayout llDosageTypeInterval;  // type에 따라 visible

    @Bind(R.id.textView_dosage_interval_title)
    TextView tvDosageIntervalTitle;

    @Bind(R.id.view_dosage_interval_line)
    View vDosageIntervalLine;

    @Bind(R.id.textView_time_interval)
    TextView tvTimeInterval;

    @Bind(R.id.textView_dosage_start_title)
    TextView tvDosageStartTitle;

    @Bind(R.id.view_dosage_start_line)
    View vDosageStartLine;

    @Bind(R.id.textView_time_start)
    TextView tvTimeStart;

    @Bind(R.id.textView_dosage_type_interval_title)
    TextView tvDosageTypeIntervalTitle;

    @Bind(R.id.view_dosage_type_interval_line)
    View vDosageTypeIntervalLine;

    @Bind(R.id.textView_time_count_current)
    TextView tvTimeCountCurrent;

    @Bind(R.id.textView_time_count_total)
    TextView tvTimeCountTotal;

    @Bind(R.id.textView_dosage_type_normal_title)
    TextView tvDosageTypeNormalTitle;

    @Bind(R.id.view_dosage_type_normal_line)
    View vDosageTypeNormalLine;

    @Bind(R.id.linearLayout_dosage_type_normal)
    LinearLayout llDosageTypeNormal;  // type에 따라 visible

    @Bind(R.id.relativeLayout_dosage_check_wakeup)
    RelativeLayout rlDosageCheckWakeup;

    @Bind(R.id.relativeLayout_dosage_check_morning)
    RelativeLayout rlDosageCheckMorning;

    @Bind(R.id.relativeLayout_dosage_check_lunch)
    RelativeLayout rlDosageCheckLunch;

    @Bind(R.id.relativeLayout_dosage_check_evening)
    RelativeLayout rlDosageCheckEvening;

    @Bind(R.id.relativeLayout_dosage_check_sleep)
    RelativeLayout rlDosageCheckSleep;

    @Bind(R.id.textView_time_wakeup)
    TextView tvTimeWakeup;

    @Bind(R.id.textView_time_morning)
    TextView tvTimeMorning;

    @Bind(R.id.textView_time_lunch)
    TextView tvTimeLunch;

    @Bind(R.id.textView_time_evening)
    TextView tvTimeEvening;

    @Bind(R.id.textView_time_sleep)
    TextView tvTimeSleep;

    @Bind(R.id.dosageCheckButton_wakeup)
    DosageCheckButton dcbtnWakeup;

    @Bind(R.id.dosageCheckButton_morning)
    DosageCheckButton dcbtnMorning;

    @Bind(R.id.dosageCheckButton_lunch)
    DosageCheckButton dcbtnLunch;

    @Bind(R.id.dosageCheckButton_evening)
    DosageCheckButton dcbtnEvening;

    @Bind(R.id.dosageCheckButton_sleep)
    DosageCheckButton dcbtnSleep;

    @Bind(R.id.button_confirm)
    Button btnConfirm;

    @Bind(R.id.button_cancel)
    Button btnCancel;

    String strLabelColor;

    public DiseaseDosageCheckBottomSheetDialogFragment() {
    }

    public static DiseaseDosageCheckBottomSheetDialogFragment newInstance(Disease disease) {
        DiseaseDosageCheckBottomSheetDialogFragment fragment = new DiseaseDosageCheckBottomSheetDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_DISEASE, disease);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDisease = (Disease) getArguments().getSerializable(ARG_PARAM_DISEASE);
        }
    }

    // 2번째 호출
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disease_dosage_check_bottom_sheet_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    // 3번째 호출
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        strLabelColor = mDisease.getColor();

        //레이블 색 적용
        llLabel.setBackgroundColor(Color.parseColor(strLabelColor));
        tvDosageDateTitle.setTextColor(Color.parseColor(strLabelColor));
        vDosageDateLine.setBackgroundColor(Color.parseColor(strLabelColor));
        tvDosageTypeIntervalTitle.setTextColor(Color.parseColor(strLabelColor));
        vDosageTypeIntervalLine.setBackgroundColor(Color.parseColor(strLabelColor));
        tvDosageTypeNormalTitle.setTextColor(Color.parseColor(strLabelColor));
        vDosageTypeNormalLine.setBackgroundColor(Color.parseColor(strLabelColor));
        tvDosageIntervalTitle.setTextColor(Color.parseColor(strLabelColor));
        vDosageIntervalLine.setBackgroundColor(Color.parseColor(strLabelColor));
        tvDosageStartTitle.setTextColor(Color.parseColor(strLabelColor));
        vDosageStartLine.setBackgroundColor(Color.parseColor(strLabelColor));
        btnConfirm.setTextColor(Color.parseColor(strLabelColor));
        ((GradientDrawable) btnConfirm.getBackground()).setStroke(ViewUtils.dpToPixel(getContext(), 1), Color.parseColor(strLabelColor));
        btnCancel.setTextColor(Color.parseColor(strLabelColor));
        ((GradientDrawable) btnCancel.getBackground()).setStroke(ViewUtils.dpToPixel(getContext(), 1), Color.parseColor(strLabelColor));

        //레이블 이미지
        ivLabel.setImageResource(mDisease.getImg());

        //레이블 텍스트
        tvLabel.setText(mDisease.getName());

        //날짜
        tvDateStart.setText(TimeUtils.unixTimeStampToStringDateYearMonthDay(mDisease.getDateStart()));
        tvDateEnd.setText(TimeUtils.unixTimeStampToStringDateYearMonthDay(mDisease.getDateEnd()));

        //복용 현황
        if (mDisease.getDosageType() == Constants.DOSAGE_TYPE_EVERYHOUR) {   //시간 마다
            llDosageTypeInterval.setVisibility(View.VISIBLE);
            llDosageTypeNormal.setVisibility(View.GONE);

            tvTimeInterval.setText(String.format("%d시간", mDisease.getTimeInterval()));
            tvTimeStart.setText(mDisease.getTimeStartHour() + ":" + mDisease.getTimeStartMinute());
            tvTimeCountCurrent.setText(String.format("%d", mDisease.getDosageCurrnt()));
            tvTimeCountTotal.setText(String.format("%d", mDisease.getDosageTotal()));
        } else {  //식사시간 마다
            llDosageTypeInterval.setVisibility(View.GONE);
            llDosageTypeNormal.setVisibility(View.VISIBLE);

            //복용여부
            if (mDisease.isEnabledWakeup()) {
                rlDosageCheckWakeup.setVisibility(View.VISIBLE);
                tvTimeWakeup.setText(TimeUtils.unixTimeStampToStringTime(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_WAKEUP)));
                dcbtnWakeup.setColorChecked(strLabelColor);
                dcbtnWakeup.setChecked(mDisease.isTakeWakeup());
            } else {
                rlDosageCheckWakeup.setVisibility(View.GONE);
            }
            if (mDisease.isEnabledMorning()) {
                rlDosageCheckMorning.setVisibility(View.VISIBLE);
                tvTimeMorning.setText(TimeUtils.unixTimeStampToStringTime(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_MORNING)));
                dcbtnMorning.setColorChecked(strLabelColor);
                dcbtnMorning.setChecked(mDisease.isTakeMorning());
            } else {
                rlDosageCheckMorning.setVisibility(View.GONE);
            }
            if (mDisease.isEnabledLunch()) {
                rlDosageCheckLunch.setVisibility(View.VISIBLE);
                tvTimeLunch.setText(TimeUtils.unixTimeStampToStringTime(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_LUNCH)));
                dcbtnLunch.setColorChecked(strLabelColor);
                dcbtnLunch.setChecked(mDisease.isTakeLunch());
            } else {
                rlDosageCheckLunch.setVisibility(View.GONE);
            }
            if (mDisease.isEnabledEvening()) {
                rlDosageCheckEvening.setVisibility(View.VISIBLE);
                tvTimeEvening.setText(TimeUtils.unixTimeStampToStringTime(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_EVENING)));
                dcbtnEvening.setColorChecked(strLabelColor);
                dcbtnEvening.setChecked(mDisease.isTakeEvening());
            } else {
                rlDosageCheckEvening.setVisibility(View.GONE);
            }
            if (mDisease.isEnabledSleep()) {
                rlDosageCheckSleep.setVisibility(View.VISIBLE);
                tvTimeSleep.setText(TimeUtils.unixTimeStampToStringTime(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_SLEEP)));
                dcbtnSleep.setColorChecked(strLabelColor);
                dcbtnSleep.setChecked(mDisease.isTakeSleep());
            } else {
                rlDosageCheckSleep.setVisibility(View.GONE);
            }
        }

        Log.e(TAG, " " + mDisease.isTakeWakeup() + " " + mDisease.isTakeMorning() + " " + mDisease.isTakeLunch() + " " + mDisease.isTakeEvening() + " " + mDisease.isTakeSleep());
    }

    //복용 체크 버튼 리스너
    @OnClick({R.id.dosageCheckButton_wakeup, R.id.dosageCheckButton_morning, R.id.dosageCheckButton_lunch, R.id.dosageCheckButton_evening, R.id.dosageCheckButton_sleep})
    public void onClickDosageCheck(View view) {

        switch (view.getId()) {
            case R.id.dosageCheckButton_wakeup:
                dcbtnWakeup.setChecked(!dcbtnWakeup.isChecked());
                break;
            case R.id.dosageCheckButton_morning:
                dcbtnMorning.setChecked(!dcbtnMorning.isChecked());
                break;
            case R.id.dosageCheckButton_lunch:
                dcbtnLunch.setChecked(!dcbtnLunch.isChecked());
                break;
            case R.id.dosageCheckButton_evening:
                dcbtnEvening.setChecked(!dcbtnEvening.isChecked());
                break;
            case R.id.dosageCheckButton_sleep:
                dcbtnSleep.setChecked(!dcbtnSleep.isChecked());
                break;
        }
    }

    @OnClick({R.id.button_time_count_minus, R.id.button_time_count_plus})
    public void onClickTimeCount(View view) {
        switch (view.getId()) {
            case R.id.button_time_count_minus:
                if (mDisease.getDosageCurrnt() > 0) {
                    mDisease.setDosageCurrnt(mDisease.getDosageCurrnt() - 1);
                    tvTimeCountCurrent.setText(String.format("%d", mDisease.getDosageCurrnt()));
                } else {
                    Toast.makeText(getActivity(), "최소치 입니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_time_count_plus:
                if (mDisease.getDosageCurrnt() < mDisease.getDosageTotal()) {
                    mDisease.setDosageCurrnt(mDisease.getDosageCurrnt() + 1);
                    tvTimeCountCurrent.setText(String.format("%d", mDisease.getDosageCurrnt()));
                } else {
                    Toast.makeText(getActivity(), "최대치 입니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //BottomSheet의 행동에 따른 콜백 리스너
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(View bottomSheet, float slideOffset) {
        }
    };

    // 1번 호출
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = View.inflate(getContext(), R.layout.fragment_disease_dosage_check_bottom_sheet_dialog, null);
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetCallback);
        }
    }

    // 복용 정보 DB에 저장하는 이벤트 발행 and dialog hide
    @OnClick(R.id.button_confirm)
    public void onClickConfirm() {

        if (mDisease.getDosageType() == Constants.DOSAGE_TYPE_EVERYHOUR) {
            EventBus.getDefault().post(new EventDosageCheckUpdate(
                    mDisease.getId(),
                    mDisease.getDosageType(),
                    mDisease.getDosageCurrnt()));
        } else {
            EventBus.getDefault().post(new EventDosageCheckUpdate(
                    mDisease.getId(),
                    mDisease.getDosageType(),
                    dcbtnWakeup.isChecked(),
                    dcbtnMorning.isChecked(),
                    dcbtnLunch.isChecked(),
                    dcbtnEvening.isChecked(),
                    dcbtnSleep.isChecked()));
        }
        dismiss();
    }

    // dialog hide
    @OnClick(R.id.button_cancel)
    public void onClickCancel() {
        dismiss();
    }
}
