package com.thechicks.conditionform.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thechicks.conditionform.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistAutoOcrResultFragment extends Fragment {

    @Bind(R.id.recyclerView_ocr_result)
    RecyclerView rvOcrResult;

    public RegistAutoOcrResultFragment() {
        // Required empty public constructor
    }

    public static RegistAutoOcrResultFragment newInstance(String param1, String param2) {
        RegistAutoOcrResultFragment fragment = new RegistAutoOcrResultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regist_auto_ocr_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @OnClick(R.id.button_cancel)
    public void onClickCancel() {

    }

    @OnClick(R.id.button_confirm)
    public void onClickConfirm() {

    }
}
