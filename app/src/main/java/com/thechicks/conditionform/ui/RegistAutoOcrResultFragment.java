package com.thechicks.conditionform.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.OcrResult;
import com.thechicks.conditionform.ui.regist.RegistManualActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistAutoOcrResultFragment extends Fragment {

    @Bind(R.id.recyclerView_ocr_result)
    RecyclerView rvOcrResult;

    RegistAutoOcrResultListAdapter mRegistAutoOcrResultListAdapter;

    public RegistAutoOcrResultFragment() {
        // Required empty public constructor
    }

    public static RegistAutoOcrResultFragment newInstance() {
        RegistAutoOcrResultFragment fragment = new RegistAutoOcrResultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regist_auto_ocr_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvOcrResult.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvOcrResult.setHasFixedSize(true);

        mRegistAutoOcrResultListAdapter = new RegistAutoOcrResultListAdapter(getActivity());
        rvOcrResult.setAdapter(mRegistAutoOcrResultListAdapter);
    }

    @Subscribe
    public void ocrResult(EventOcrResult eventOcrResult) {
        mRegistAutoOcrResultListAdapter.setData(eventOcrResult.getOcrResults());
    }

    //Todo: 메인으로 이동
    @OnClick(R.id.button_cancel)
    public void onClickCancel() {
        getActivity().finish();
    }

    //Todo: 알람등록 화면으로 이동
    @OnClick(R.id.button_confirm)
    public void onClickConfirm() {
        ArrayList<OcrResult> ocrResultArrayList = mRegistAutoOcrResultListAdapter.getOcrResultList();
        Intent intent = new Intent(getActivity(), RegistManualActivity.class);
        intent.putExtra("ocrResult", ocrResultArrayList);
        startActivity(intent);
    }
}
