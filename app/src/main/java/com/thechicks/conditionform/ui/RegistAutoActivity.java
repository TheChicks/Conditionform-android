package com.thechicks.conditionform.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.thechicks.conditionform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistAutoActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    FragmentManager fm;

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


    }
}
