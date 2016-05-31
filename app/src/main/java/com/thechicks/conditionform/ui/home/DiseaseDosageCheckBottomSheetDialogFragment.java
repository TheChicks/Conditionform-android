package com.thechicks.conditionform.ui.home;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.data.model.Pill;
import com.thechicks.conditionform.data.model.TimeItem;
import com.thechicks.conditionform.util.Constants;
import com.thechicks.conditionform.util.PreferencesUtils;
import com.thechicks.conditionform.util.TimeUtils;
import com.thechicks.conditionform.util.ViewUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Locale;

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

    @Bind(R.id.textView_dosage_type_interval_title)
    TextView tvDosageTypeIntervalTitle;

    @Bind(R.id.view_dosage_type_interval_line)
    View vDosageTypeIntervalLine;

    @Bind(R.id.linearLayout_dosage_type_interval)
    LinearLayout llDosageTypeInterval;  // type에 따라 visible

    @Bind(R.id.textView_time_interval)
    TextView tvTimeInterval;

    @Bind(R.id.textView_time_start)
    TextView tvTimeStart;

    @Bind(R.id.textView_time_count)
    TextView tvTimeCount;

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

    @Bind(R.id.checkBox_dosage_wakeup)
    CheckBox cbDosageWakeup;

    @Bind(R.id.checkBox_dosage_morning)
    CheckBox cbDosageMorning;

    @Bind(R.id.checkBox_dosage_lunch)
    CheckBox cbDosageLunch;

    @Bind(R.id.checkBox_dosage_evening)
    CheckBox cbDosageEvening;

    @Bind(R.id.checkBox_dosage_sleep)
    CheckBox cbDosageSleep;

    @Bind(R.id.textView_pill_list_title)
    TextView tvPillListTitle;

    @Bind(R.id.view_pill_list_line)
    View vPillListLine;

    @Bind(R.id.recyclerView_pill)
    RecyclerView rvPill;

    @Bind(R.id.button_confirm)
    Button btnConfirm;

    @Bind(R.id.button_cancel)
    Button btnCancel;

    DiseaseDosageChcekPillListAdapter mDiseaseDosageChcekPillListAdapter;

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
        tvPillListTitle.setTextColor(Color.parseColor(strLabelColor));
        vPillListLine.setBackgroundColor(Color.parseColor(strLabelColor));
        btnConfirm.setTextColor(Color.parseColor(strLabelColor));
        ((GradientDrawable) btnConfirm.getBackground()).setStroke(ViewUtils.dpToPixel(getContext(), 1), Color.parseColor(strLabelColor));
        btnCancel.setTextColor(Color.parseColor(strLabelColor));
        ((GradientDrawable) btnCancel.getBackground()).setStroke(ViewUtils.dpToPixel(getContext(), 1), Color.parseColor(strLabelColor));
        tvDosageDateTitle.setTextColor(Color.parseColor(strLabelColor));
        vDosageDateLine.setBackgroundColor(Color.parseColor(strLabelColor));
        tvDosageTypeIntervalTitle.setTextColor(Color.parseColor(strLabelColor));
        vDosageTypeIntervalLine.setBackgroundColor(Color.parseColor(strLabelColor));
        tvDosageTypeNormalTitle.setTextColor(Color.parseColor(strLabelColor));
        vDosageTypeNormalLine.setBackgroundColor(Color.parseColor(strLabelColor));

        //레이블 이미지
        ivLabel.setImageResource(mDisease.getImg());

        //레이블 텍스트
        tvLabel.setText(mDisease.getName());

        //날짜
        tvDateStart.setText(TimeUtils.unixTimeStampToStringDateYearMonthDay(mDisease.getDateStart()));
        tvDateEnd.setText(TimeUtils.unixTimeStampToStringDateYearMonthDay(mDisease.getDateEnd()));

        //약목록
        mDiseaseDosageChcekPillListAdapter = new DiseaseDosageChcekPillListAdapter(getActivity());
        mDiseaseDosageChcekPillListAdapter.setPillArrayList(mDisease.getPillArrayList());
        rvPill.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvPill.setHasFixedSize(true);
        rvPill.setAdapter(mDiseaseDosageChcekPillListAdapter);

        if (mDisease.getDosageType() == Constants.DOSAGE_TYPE_EVERYHOUR) {   //시간 마다
            llDosageTypeInterval.setVisibility(View.VISIBLE);
            llDosageTypeNormal.setVisibility(View.GONE);

            tvTimeInterval.setText(String.format("%d시간", mDisease.getTimeInterval()));
            tvTimeStart.setText(mDisease.getTimeStartHour() + ":" + mDisease.getTimeStartMinute());
            tvTimeCount.setText(String.format("%d회", mDisease.getDosageTotal()));
        } else {  //식사시간 마다
            llDosageTypeInterval.setVisibility(View.GONE);
            llDosageTypeNormal.setVisibility(View.VISIBLE);

            //복용여부
            if (mDisease.isEnabledWakeup()) {
                rlDosageCheckWakeup.setVisibility(View.VISIBLE);
                tvTimeWakeup.setText(TimeUtils.unixTimeStampToStringTime(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_WAKEUP)));
            } else {
                rlDosageCheckWakeup.setVisibility(View.GONE);
            }
            if (mDisease.isEnabledMorning()) {
                rlDosageCheckMorning.setVisibility(View.VISIBLE);
                tvTimeMorning.setText(TimeUtils.unixTimeStampToStringTime(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_MORNING)));
            } else {
                rlDosageCheckWakeup.setVisibility(View.GONE);
            }
            if (mDisease.isEnabledLunch()) {
                rlDosageCheckLunch.setVisibility(View.VISIBLE);
                tvTimeLunch.setText(TimeUtils.unixTimeStampToStringTime(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_LUNCH)));
            } else {
                rlDosageCheckWakeup.setVisibility(View.GONE);
            }
            if (mDisease.isEnabledEvening()) {
                rlDosageCheckEvening.setVisibility(View.VISIBLE);
                tvTimeEvening.setText(TimeUtils.unixTimeStampToStringTime(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_EVENING)));
            } else {
                rlDosageCheckWakeup.setVisibility(View.GONE);
            }
            if (mDisease.isEnabledSleep()) {
                rlDosageCheckSleep.setVisibility(View.VISIBLE);
                tvTimeSleep.setText(TimeUtils.unixTimeStampToStringTime(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_SLEEP)));
            } else {
                rlDosageCheckWakeup.setVisibility(View.GONE);
            }
            cbDosageWakeup.setChecked(mDisease.isTakeWakeup());
            cbDosageMorning.setChecked(mDisease.isTakeMorning());
            cbDosageLunch.setChecked(mDisease.isTakeLunch());
            cbDosageEvening.setChecked(mDisease.isTakeEvening());
            cbDosageSleep.setChecked(mDisease.isTakeSleep());
        }

        Log.e(TAG, " " + mDisease.isTakeWakeup() + " " + mDisease.isTakeMorning() + " " + mDisease.isTakeLunch() + " " + mDisease.isTakeEvening() + " " + mDisease.isTakeSleep());
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
        EventBus.getDefault().post(new EventDosageCheckUpdate(
                mDisease.getId(),
                cbDosageWakeup.isChecked(),
                cbDosageMorning.isChecked(),
                cbDosageLunch.isChecked(),
                cbDosageEvening.isChecked(),
                cbDosageSleep.isChecked()
        ));
        dismiss();
    }

    // dialog hide
    @OnClick(R.id.button_cancel)
    public void onClickCancel() {
        dismiss();
    }
}
