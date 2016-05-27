package com.thechicks.conditionform.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.remote.BackendHelper;

import java.io.File;
import java.io.FileOutputStream;
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

    Uri mCaptureUri;

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

        mCaptureUri = receiveIntent.getData();  // intent에서 Uri 추출

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mCaptureUri);
            ivCapture.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final int REQUEST_CODE_CROP = 2002;

    @OnClick(R.id.button_capture_edit)
    public void onClickCaptureEdit() {
        //Todo: 편집 앱 실행

//        Intent cropIntent = new Intent("com.android.camera.action.CROP");
//        cropIntent.setDataAndType(mCaptureUri, "image/*");
//
//        //set crop properties
//        cropIntent.putExtra("crop", "true");
//        //crop한 이미지를 저장할 때 200x200 크기로 저장
////        intent.putExtra("outputX", 200);  //crop한 이미지의 x축 크기
////        intent.putExtra("outputY", 200);  //crop한 이미지의 y축 크기
////        intent.putExtra("aspectX", 2);  //crop 박스의 x축 비율
////        intent.putExtra("aspectY", 1);  //crop 박스의 y축 비율
//        cropIntent.putExtra("scale", true);
//
//        //retrieve data in return. 사용하면 번들 용량 제한으로 크기가 큰 이미지는 안넘어온다.
////        cropIntent.putExtra("return-data", true);
//        startActivityForResult(cropIntent, REQUEST_CODE_CROP);

        //Start Crop image activity
        startCropImageActivity(mCaptureUri);
    }

    @OnClick(R.id.button_capture_repeat)
    public void onClickCaptureRepeat() {
        //Todo: 다시찍기

    }

    @OnClick(R.id.button_capture_confirm)
    public void onClickCaptureConfirm(){
        //Todo: 파일로 만들어 서버에 전송

        FileOutputStream fos = null;

        try {
            //Crop한 이미지의 캐시위치의 Uri가 오는데 이걸 비트맵으로 변환
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mCaptureUri);
//            ivCapture.setImageBitmap(bitmap);

            //임시 디렉토리에 파일로 변환
            File cacheFileDir = new File(getActivity().getCacheDir(), "temp");
            if (!cacheFileDir.exists()){
                cacheFileDir.mkdir();  //존재하지 않으면 생성
            }

            File imageFileName = new File(cacheFileDir, "pillCheck-" + System.currentTimeMillis() + ".jpg");

            fos = new FileOutputStream(imageFileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();

            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFileName);

            MultipartBody.Part body = MultipartBody.Part.createFormData("prescription", imageFileName.getName(), requestFile);

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

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        // file by uri
//        File file = new File(getPathFromUri(mCaptureUri));

        // create RequestBody instance from file
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part body = MultipartBody.Part.createFormData("prescription", file.getName(), requestFile);

//        Call<JsonArray> call = sBackendHelper.getOcrResult(body);
//        call.enqueue(new Callback<JsonArray>() {
//            @Override
//            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
//                Log.d(TAG, " Upload success");
//
//                //Todo: json 파싱해서 OcrResultFragmnet로 넘김
//            }
//            @Override
//            public void onFailure(Call<JsonArray> call, Throwable t) {
//                Log.e(TAG, " Throwable is " + t);
//            }
//        });
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

                mCaptureUri = data.getData();  //Uri 추출
                Log.d(TAG, " crop " + mCaptureUri);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mCaptureUri);
                    ivCapture.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            //Todo: 삭제
            case CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE:  //handle result of pick image chooser
                Log.d(TAG, " PICK_IMAGE_CHOOSER_REQUEST_CODE");

                Uri imageUri = CropImage.getPickImageResultUri(getActivity(), data);

                // For API >= 23 we need to check specifically that we have permissions to read external storage.
                boolean requirePermissions = false;
                if (CropImage.isReadExternalStoragePermissionsRequired(getActivity(), imageUri)){
                    // request permissions and handle the result in onRequestPermissionsResult()
                    requirePermissions = true;
                    mCaptureUri = imageUri;
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                }else {
                    // no permissions required or already grunted, can start crop image activity
                    startCropImageActivity(imageUri);
                }
                break;

            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:  // handle result of CropImageActivity

                Log.d(TAG, " CROP_IMAGE_ACTIVITY_REQUEST_CODE");
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                mCaptureUri = result.getUri();
                Log.d(TAG, " " + mCaptureUri);
                ivCapture.setImageURI(mCaptureUri);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(mCaptureUri != null && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            // required permission granted, start crop image activity
            startCropImageActivity(mCaptureUri);
        }else {
            Toast.makeText(getActivity(), "Cancelling, required permissions are not granted", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Start crop image activity for the given image.
     */
    private void startCropImageActivity(Uri imageUri){
        Log.d(TAG, " startCropImageActivity");
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON_TOUCH)  //터치시에만 가이드 라인
                .setCropShape(CropImageView.CropShape.RECTANGLE)  //Crop 영역 사각형
                .setFixAspectRatio(false)  //Crop 영역 비율 FREE
                .setAutoZoomEnabled(true)  //Crop 영역 조절시 Zoom 활성화
                .setMaxZoom(8)   //Max Zoom Level 설정. default=4
                .setShowCropOverlay(true)  //Crop 영역 OverLay. default=true
                .start(getActivity(), RegistAutoCaptureResultFragment.this);
    }
}
