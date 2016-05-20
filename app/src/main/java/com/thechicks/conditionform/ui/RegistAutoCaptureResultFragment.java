package com.thechicks.conditionform.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thechicks.conditionform.R;

import java.io.IOException;

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

        Intent receiveIntent = getActivity().getIntent();

        Uri captureUri = receiveIntent.getData();  //

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), captureUri);
            ivCapture.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.button_capture_edit)
    public void onClickCaptureEdit() {
        //Todo: 편집 앱 실행

    }

    @OnClick(R.id.button_capture_repeat)
    public void onClickCaptureRepeat() {
        //Todo: 다시찍기

    }

    @OnClick(R.id.button_capture_confirm)
    public void onClickCaptureConfirm() {
        //Todo: 파일로 만들어 서버에 전송





    }
}
