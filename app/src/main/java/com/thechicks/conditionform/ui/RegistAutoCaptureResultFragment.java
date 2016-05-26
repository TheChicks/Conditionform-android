package com.thechicks.conditionform.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.JsonArray;
import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.remote.BackendHelper;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistAutoCaptureResultFragment extends Fragment {

    public static final String TAG = RegistAutoCaptureResultFragment.class.getSimpleName();

    @Bind(R.id.imageView_capture)
    ImageView ivCapture;

    Uri captureUri;

    protected static BackendHelper sBackendHelper;

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

        if (sBackendHelper == null) {
            sBackendHelper = BackendHelper.getInstance();
        }
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

        captureUri = receiveIntent.getData();  // intent에서 Uri 추출

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), captureUri);
            ivCapture.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final int REQUEST_CODE_CROP = 2002;

    @OnClick(R.id.button_capture_edit)
    public void onClickCaptureEdit() {
        //Todo: 편집 앱 실행

        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(captureUri, "image/*");

        //set crop properties
        cropIntent.putExtra("crop", "true");
        //crop한 이미지를 저장할 때 200x200 크기로 저장
//        intent.putExtra("outputX", 200);  //crop한 이미지의 x축 크기
//        intent.putExtra("outputY", 200);  //crop한 이미지의 y축 크기
//        intent.putExtra("aspectX", 2);  //crop 박스의 x축 비율
//        intent.putExtra("aspectY", 1);  //crop 박스의 y축 비율
        cropIntent.putExtra("scale", true);

        //retrieve data in return. 사용하면 번들 용량 제한으로 크기가 큰 이미지는 안넘어온다.
//        cropIntent.putExtra("return-data", true);
        startActivityForResult(cropIntent, REQUEST_CODE_CROP);
    }

    @OnClick(R.id.button_capture_repeat)
    public void onClickCaptureRepeat() {
        //Todo: 다시찍기

    }

    @OnClick(R.id.button_capture_confirm)
    public void onClickCaptureConfirm() {
        //Todo: 파일로 만들어 서버에 전송

        // file by uri
        File file = new File(getPathFromUri(captureUri));

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("prescription", file.getName(), requestFile);

        Call<JsonArray> call = sBackendHelper.getOcrResult(body);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.d(TAG, " Upload success");

                //Todo: json 파싱해서 OcrResultFragmnet로 넘김
            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e(TAG, " Throwable is " + t);
            }
        });
    }

    //Uri에서 실제 파일이 저장된 path를 추출
    public String getPathFromUri(Uri uri) {

        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);

        String path = "";

        if (cursor != null) {
            cursor.moveToNext();
            path = cursor.getString(cursor.getColumnIndex("_data"));
            cursor.close();
        }
        return path;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode){
            case REQUEST_CODE_CROP:

                captureUri = data.getData();  //Uri 추출
                Log.e(TAG, " crop " + captureUri);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), captureUri);
                    ivCapture.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
