package com.thechicks.conditionform.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thechicks.conditionform.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistAutoCaptureResultFragment extends Fragment {

    @Bind(R.id.imageView_capture)
    ImageView ivCapture;

    public RegistAutoCaptureResultFragment() {
        // Required empty public constructor
    }

    public static RegistAutoCaptureResultFragment newInstance() {
        RegistAutoCaptureResultFragment fragment = new RegistAutoCaptureResultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regist_auto_capture_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.button_capture_edit)
    public void onClickCaptureEdit(){

    }

    @OnClick(R.id.button_capture_repeat)
    public void onClickCaptureRepeat(){

    }

    @OnClick(R.id.button_capture_confirm)
    public void onClickCaptureConfirm(){

    }
}
