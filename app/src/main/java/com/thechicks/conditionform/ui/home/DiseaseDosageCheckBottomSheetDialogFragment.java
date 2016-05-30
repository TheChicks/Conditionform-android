package com.thechicks.conditionform.ui.home;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.data.model.Pill;
import com.thechicks.conditionform.data.model.TimeItem;
import com.thechicks.conditionform.util.Constants;
import com.thechicks.conditionform.util.PreferencesUtils;
import com.thechicks.conditionform.util.TimeUtils;

import java.util.ArrayList;

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

    @Bind(R.id.textView_date_start)
    TextView tvDateStart;

    @Bind(R.id.textView_date_end)
    TextView tvDateEnd;

    @Bind(R.id.recyclerView_dosage_check)
    RecyclerView rvDosageCheck;

    @Bind(R.id.recyclerView_pill)
    RecyclerView rvPill;

    @Bind(R.id.textView_pill_list_title)
    TextView tvPillListTitle;

    @Bind(R.id.view_line)
    View viewLine;

    DiseaseDosageCheckAdapter mDiseaseDosageCheckAdapter;
    DiseaseDosageChcekPillListAdapter mDiseaseDosageChcekPillListAdapter;

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
        Log.e(TAG, " onCreateView");
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
        Log.e(TAG, " onViewCreated");

        //레이블 색 적용
        llLabel.setBackgroundColor(Color.parseColor(mDisease.getColor()));
        tvPillListTitle.setTextColor(Color.parseColor(mDisease.getColor()));
        viewLine.setBackgroundColor(Color.parseColor(mDisease.getColor()));

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

        //복용여부
        mDiseaseDosageCheckAdapter = new DiseaseDosageCheckAdapter(getActivity());
        mDiseaseDosageCheckAdapter.setOnListItemClickListener(new DiseaseDosageCheckAdapter.OnListItemClickListener() {
            @Override
            public void onListItemCheck(int position) {
                mDiseaseDosageCheckAdapter.notifyItemChanged(position);
            }
        });

        ArrayList<TimeItem> timeItemArrayList = new ArrayList<>();
        if (mDisease.isEnabledWakeup()) {
            TimeItem timeWakeUp = new TimeItem(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_WAKEUP));
            timeItemArrayList.add(timeWakeUp);
        }
        if (mDisease.isEnabledMorning()) {
            TimeItem timeMorning = new TimeItem(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_MORNING));
            timeItemArrayList.add(timeMorning);
        }
        if (mDisease.isEnabledLunch()) {
            TimeItem timeLunch = new TimeItem(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_LUNCH));
            timeItemArrayList.add(timeLunch);
        }
        if (mDisease.isEnabledEvening()) {
            TimeItem timeEvening = new TimeItem(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_EVENING));
            timeItemArrayList.add(timeEvening);
        }
        if (mDisease.isEnabledSleep()) {
            TimeItem timeSleep = new TimeItem(PreferencesUtils.getPreferencesLong(getActivity(), Constants.PREF_TIME_SLEEP));
            timeItemArrayList.add(timeSleep);
        }
        mDiseaseDosageCheckAdapter.setTimeItemArrayList(timeItemArrayList);

        rvDosageCheck.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvDosageCheck.setHasFixedSize(true);
        rvDosageCheck.setAdapter(mDiseaseDosageCheckAdapter);
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

    //Todo: 복용 정보 DB에 저장하고 hide.
    //DB에 저장하는 이벤트 발행
    @OnClick(R.id.button_confirm)
    public void onClickConfirm() {
        dismiss();
    }

    // dialog hide
    @OnClick(R.id.button_cancel)
    public void onClickCancel() {
        dismiss();
    }
}
