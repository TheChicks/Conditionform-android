package com.thechicks.conditionform.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thechicks.conditionform.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                finish();

                //스플래시 자체가 서서히 사라지는 애니메이션
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, SPLASH_TIME_OUT);
    }
}
