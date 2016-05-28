package com.thechicks.conditionform.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.OcrResult;
import com.thechicks.conditionform.data.remote.BackendHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistAutoActivity extends AppCompatActivity {

    public static final String TAG = RegistAutoActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    FragmentManager fm;

    protected static BackendHelper sBackendHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_auto);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.container_fragment);
        if (fragment == null) {
            RegistAutoCaptureResultFragment captureResultFragment = RegistAutoCaptureResultFragment.newInstance();

            fm.beginTransaction()
                    .add(R.id.container_fragment, captureResultFragment)
                    .commit();
        }

        if (sBackendHelper == null) {
            sBackendHelper = BackendHelper.getInstance();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    //이미지를 서버에 전송
    @Subscribe
    public void ocrStart(EventOcrStart eventOcrStart) {

        File imageFileName = eventOcrStart.getImageFileName();

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFileName);

        MultipartBody.Part body = MultipartBody.Part.createFormData("prescription", imageFileName.getName(), requestFile);

        Call<JsonArray> call = sBackendHelper.getOcrResult(body);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.d(TAG, " Upload success");

                //json 파싱해서 OcrResultFragmnet로 넘김
                JsonArray jaRoot = response.body();

                if (jaRoot != null) {

                    Log.e(TAG, " " + jaRoot);

                    final ArrayList<OcrResult> ocrResultArrayList = new ArrayList<OcrResult>();

                    for (int i = 0; i < jaRoot.size(); i++) {
                        OcrResult ocrResult = new Gson().fromJson(jaRoot.get(i), OcrResult.class);
                        ocrResultArrayList.add(ocrResult);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //결과를 이벤트 날림
                            EventBus.getDefault().post(new EventOcrResult(ocrResultArrayList));
                        }
                    });

                    //fragment 전환
                    Fragment fragment = fm.findFragmentById(R.id.container_fragment);
                    if (fragment instanceof RegistAutoCaptureResultFragment) {

                        fragment = RegistAutoOcrResultFragment.newInstance();
                        fm.beginTransaction()
                                .replace(R.id.container_fragment, fragment)
                                .commit();
                    }

                } else {
                    Log.e(TAG, "jaRoot null");
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e(TAG, " Throwable is " + t);
            }
        });
    }
}
