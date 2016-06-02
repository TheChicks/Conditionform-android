package com.thechicks.conditionform.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.database.ConditionformDao;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.ui.history.HistoryFragment;
import com.thechicks.conditionform.ui.home.EventDateChange;
import com.thechicks.conditionform.ui.home.EventDosageCheckUpdate;
import com.thechicks.conditionform.ui.home.HomeFragment;
import com.thechicks.conditionform.ui.home.RegistAutoDialog;
import com.thechicks.conditionform.ui.regist.RegistManualActivity;
import com.thechicks.conditionform.ui.search.SearchFragment;
import com.thechicks.conditionform.ui.settings.SettingsFragment;
import com.thechicks.conditionform.ui.statistics.StatisticsFragment;
import com.thechicks.conditionform.util.AsyncHandler;
import com.thechicks.conditionform.util.Constants;
import com.thechicks.conditionform.util.TimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements RegistAutoDialog.RegistAutoDialogListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.appbar_normal)
    AppBarLayout mAppbarNormal;

    @Bind(R.id.appbar_search)
    AppBarLayout mAppbarSearch;

    @Bind(R.id.appbar_home)
    AppBarLayout mAppbarHome;

    @Bind(R.id.toolbar_normal)
    Toolbar mToolbarNormal;

    @Bind(R.id.toolbar_search)
    Toolbar mToolbarSearch;

    @Bind(R.id.toolbar_home)
    Toolbar mToolbarHome;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout clRoot;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.navigationView)
    NavigationView mNavigationView;

    ActionBarDrawerToggle mActionBarDrawerToggle;

    @Bind(R.id.textView_date_toDay)
    TextView tvDateToday;  //오늘 날짜 display

    @Bind(R.id.fab_menu_open)
    FloatingActionButton fabMenuOpen;

    @Bind(R.id.fab_menu_register_auto)
    FloatingActionButton fabRegisterAuto;

    @Bind(R.id.fab_menu_register_manual)
    FloatingActionButton fabRegisterManual;

    @Bind(R.id.relative_fab_menu_register_auto)
    RelativeLayout rlFabMenuRegisterAuto;

    @Bind(R.id.relative_fab_menu_register_manual)
    RelativeLayout rlFabMenuRegisterManual;

    @Bind(R.id.trans_bg)
    FrameLayout flTransBg;

    @Bind(R.id.fab_menu)
    FrameLayout flFabMenu;

    //Animations
    Animation animShowFabMenuRegisterAuto;
    Animation animHideFabMenuRegisterAuto;
    Animation animShowFabMenuRegisterManual;
    Animation animHideFabMenuRegisterManual;
    Animation animGrow;
    Animation animShrink;

    //Save the fab's active status
    //false -> fab = close
    //true -> fab = open
    private boolean fabStatus = false;

    //시간 관리용
    int currentDisplayYear;
    int currentDisplayMonth;
    int currentDisplayDay;
    long currentDayTimestamp;

    FragmentManager fm;

    ConditionformDao conditionformDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbarHome);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            ab.setTitle("");
        }

        statusBarColorChange(true);  //상태바 색상 변경

        setupDrawerContent(mNavigationView);

        //Animate the Hamburger Icon
        mActionBarDrawerToggle = setupDrawerToggle(mToolbarHome);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        setupToday();

        fm = getSupportFragmentManager();

        //초기 화면 지정
        Fragment fragmnet = fm.findFragmentById(R.id.container_fragment);
        if (fragmnet == null) {
            HomeFragment homeFragment = HomeFragment.newInstance(currentDayTimestamp);

            fm.beginTransaction()
                    .add(R.id.container_fragment, homeFragment)
                    .commit();
        }

        animationLoad();

        conditionformDao = new ConditionformDao(MainActivity.this);
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

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Disease> diseaseList = conditionformDao.findDiseaseByDate(currentDayTimestamp);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventDateChange(diseaseList, currentDayTimestamp));
                    }
                });

            }
        });
    }

    //상태바 색상 변경
    @TargetApi(21)
    private void statusBarColorChange(boolean isHome) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isHome) {
                //Todo: 현재 시간에 따라 상태바 색상 변경
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.color_gradient_lunch_end));
            } else {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            }
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private ActionBarDrawerToggle setupDrawerToggle(Toolbar toolbar) {
        return new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    public void selectDrawerItem(MenuItem menuItem) {

        //체크되어 있다면 현재화면이므로 바꿀 필요없다.
        if (menuItem.isChecked()) {
            mDrawerLayout.closeDrawers();
            return;
        }

        Fragment fragment = fm.findFragmentById(R.id.container_fragment);
        Fragment newFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_home_fragment:
                if (fragment instanceof HomeFragment) {
                    return;
                }
                newFragment = HomeFragment.newInstance(currentDayTimestamp);
                mAppbarNormal.setVisibility(View.GONE);
                mAppbarSearch.setVisibility(View.GONE);
                mAppbarHome.setVisibility(View.VISIBLE);
                this.setSupportActionBar(mToolbarHome);
                getSupportActionBar().setTitle("");
                mDrawerLayout.removeDrawerListener(mActionBarDrawerToggle);
                mActionBarDrawerToggle = setupDrawerToggle(mToolbarHome);
                fabMenuOpen.show();
                statusBarColorChange(true);
                break;
            case R.id.nav_history_fragment:
                if (fragment instanceof HistoryFragment) {
                    return;
                }
                newFragment = HistoryFragment.newInstance();
                mAppbarNormal.setVisibility(View.VISIBLE);
                mAppbarSearch.setVisibility(View.GONE);
                mAppbarHome.setVisibility(View.GONE);
                this.setSupportActionBar(mToolbarNormal);
                getSupportActionBar().setTitle(menuItem.getTitle());
                mDrawerLayout.removeDrawerListener(mActionBarDrawerToggle);
                mActionBarDrawerToggle = setupDrawerToggle(mToolbarNormal);
                fabMenuOpen.hide();
                if (fabStatus) {
                    hideFab();
                    shrinkFlTransBg();
                    fabStatus = false;
                }
                statusBarColorChange(false);
                break;
//            case R.id.nav_statistics_fragment:
//                if (fragment instanceof StatisticsFragment) {
//                    return;
//                }
//                newFragment = StatisticsFragment.newInstance();
//                mAppbarNormal.setVisibility(View.VISIBLE);
//                mAppbarSearch.setVisibility(View.GONE);
//                mAppbarHome.setVisibility(View.GONE);
//                this.setSupportActionBar(mToolbarNormal);
//                getSupportActionBar().setTitle(menuItem.getTitle());
//                mDrawerLayout.removeDrawerListener(mActionBarDrawerToggle);
//                mActionBarDrawerToggle = setupDrawerToggle(mToolbarNormal);
//                fabMenuOpen.hide();
//                if (fabStatus) {
//                    hideFab();
//                    shrinkFlTransBg();
//                    fabStatus = false;
//                }
//                statusBarColorChange(false);
//                break;
            case R.id.nav_search_fragment:
                if (fragment instanceof SearchFragment) {
                    return;
                }
                newFragment = SearchFragment.newInstance();
                mAppbarNormal.setVisibility(View.GONE);
                mAppbarSearch.setVisibility(View.VISIBLE);
                mAppbarHome.setVisibility(View.GONE);
                this.setSupportActionBar(mToolbarSearch);
                getSupportActionBar().setTitle(menuItem.getTitle());
                mDrawerLayout.removeDrawerListener(mActionBarDrawerToggle);
                mActionBarDrawerToggle = setupDrawerToggle(mToolbarSearch);
                fabMenuOpen.hide();
                if (fabStatus) {
                    hideFab();
                    shrinkFlTransBg();
                    fabStatus = false;
                }
                statusBarColorChange(false);
                break;
            case R.id.nav_settings_fragment:
                if (fragment instanceof SettingsFragment) {
                    return;
                }
                newFragment = SettingsFragment.newInstance();
                mAppbarNormal.setVisibility(View.VISIBLE);
                mAppbarSearch.setVisibility(View.GONE);
                mAppbarHome.setVisibility(View.GONE);
                this.setSupportActionBar(mToolbarNormal);
                getSupportActionBar().setTitle(menuItem.getTitle());
                mDrawerLayout.removeDrawerListener(mActionBarDrawerToggle);
                mActionBarDrawerToggle = setupDrawerToggle(mToolbarNormal);
                fabMenuOpen.hide();
                if (fabStatus) {
                    hideFab();
                    shrinkFlTransBg();
                    fabStatus = false;
                }
                statusBarColorChange(false);
                break;
        }

        if (newFragment != null) {
            // Insert the fragment by replacing any existing fragment
            fm.beginTransaction().replace(R.id.container_fragment, newFragment).commit();
        }

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                Log.d(TAG, " home");
                return true;
//            case R.id.action_settings:
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarDrawerToggle.syncState();
    }

    //런타임에서 설정 변경시 호출
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Pass any configuration change to the drawer toggles
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    public static final int REQUEST_CODE_CAMERA_CAPTURE = 2000;
    public static final int REQUEST_CODE_GALLERY = 2001;

    //암시적 인텐트로 갤러리 호출
    @Override
    public void onClickGallery() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK);
        intentGallery.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intentGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentGallery, REQUEST_CODE_GALLERY);
    }

    //암시적 인텐트로 카메라 호출
    @Override
    public void onClickCamera() {
        Intent intentCameraCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCameraCapture, REQUEST_CODE_CAMERA_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_REGIST_MANUAL:
                if (resultCode == Activity.RESULT_OK) {
                    Snackbar snackbar = Snackbar.make(clRoot, "등록 성공!", Snackbar.LENGTH_SHORT);
                    ((TextView) snackbar
                            .getView()
                            .findViewById(android.support.design.R.id.snackbar_text))
                            .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(clRoot, "등록 실패! 다시 등록해주세요~", Snackbar.LENGTH_SHORT);
                    ((TextView) snackbar
                            .getView()
                            .findViewById(android.support.design.R.id.snackbar_text))
                            .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                    snackbar.show();
                }
                break;
            case REQUEST_CODE_CAMERA_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    //사진 Uri 인텐트로 전송
                    Intent intent = new Intent(MainActivity.this, RegistAutoActivity.class);
                    intent.setData(data.getData());  //사진 Uri 넘김
                    startActivity(intent);
                }
                break;
            case REQUEST_CODE_GALLERY:
                if (resultCode == Activity.RESULT_OK) {
                    //사진 Uri 인텐트로 전송
                    Intent intent = new Intent(MainActivity.this, RegistAutoActivity.class);
                    intent.setData(data.getData());  //사진 Uri 넘김
                    startActivity(intent);
                }
                break;
        }
    }

    @Subscribe
    public void onEvent(final EventDosageCheckUpdate eventDosageCheckUpdate) {
        // DB update. 현재날짜와 id를 가지고

        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                Disease disease = new Disease();
                disease.setId(eventDosageCheckUpdate.getDiseaseId());
                disease.setDosageType(eventDosageCheckUpdate.getDosageType());

                if (disease.getDosageType() == Constants.DOSAGE_TYPE_EVERYHOUR) {
                    disease.setDosageCurrnt(eventDosageCheckUpdate.getDosageCurrent());
                } else {
                    disease.setTakeWakeup(eventDosageCheckUpdate.isTakeWakeup());
                    disease.setTakeMorning(eventDosageCheckUpdate.isTakeMorning());
                    disease.setTakeLunch(eventDosageCheckUpdate.isTakeLunch());
                    disease.setTakeEvening(eventDosageCheckUpdate.isTakeEvening());
                    disease.setTakeSleep(eventDosageCheckUpdate.isTakeSleep());
                }

                Log.e(TAG, " " + eventDosageCheckUpdate.getDiseaseId() + " " + eventDosageCheckUpdate.isTakeWakeup()
                        + " " + eventDosageCheckUpdate.isTakeMorning() + " " + eventDosageCheckUpdate.isTakeLunch()
                        + " " + eventDosageCheckUpdate.isTakeEvening() + " " + eventDosageCheckUpdate.isTakeSleep());

                // DB 갱신
                if (conditionformDao.updateTakeHistory(currentDayTimestamp, disease)) {

                    // View 갱신
                    final ArrayList<Disease> diseaseList = conditionformDao.findDiseaseByDate(currentDayTimestamp);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar snackbar = Snackbar.make(clRoot, "수정됨", Snackbar.LENGTH_SHORT);

                            ((TextView) snackbar
                                    .getView()
                                    .findViewById(android.support.design.R.id.snackbar_text))
                                    .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                            snackbar.show();

                            EventBus.getDefault().post(new EventDateChange(diseaseList, currentDayTimestamp));
                        }
                    });
                }
            }
        });
    }

    private void animationLoad() {
        animShowFabMenuRegisterAuto = AnimationUtils.loadAnimation(this, R.anim.show_fab_menu_register_auto);
        animHideFabMenuRegisterAuto = AnimationUtils.loadAnimation(this, R.anim.hide_fab_menu_register_auto);
        animShowFabMenuRegisterManual = AnimationUtils.loadAnimation(this, R.anim.show_fab_menu_register_manual);
        animHideFabMenuRegisterManual = AnimationUtils.loadAnimation(this, R.anim.hide_fab_menu_register_manual);
        animGrow = AnimationUtils.loadAnimation(this, R.anim.grow_color);
        animShrink = AnimationUtils.loadAnimation(this, R.anim.shrink_color);
    }

    public void setupToday() {
        final Calendar c = Calendar.getInstance();
        currentDayTimestamp = TimeUtils.getTodayUnixTimeStamp();

        currentDisplayYear = TimeUtils.timestampToYear(currentDayTimestamp);
        currentDisplayMonth = TimeUtils.timestampToMonth(currentDayTimestamp);
        currentDisplayDay = TimeUtils.timestampToDay(currentDayTimestamp);

        Log.e(TAG, currentDisplayYear + "년 " + currentDisplayMonth + "월 " + currentDisplayDay + "일");
        Log.e(TAG, " " + currentDayTimestamp);

        tvDateToday.setText(TimeUtils.unixTimeStampToStringDateMonthDay(currentDayTimestamp));
    }

    //이전 날짜로 이동
    @OnClick(R.id.imageView_date_previous)
    public void onClickDatePrevious() {

        currentDayTimestamp = TimeUtils.getYesterdayUnixTimeStamp(currentDayTimestamp);
        currentDisplayYear = TimeUtils.timestampToYear(currentDayTimestamp);
        currentDisplayMonth = TimeUtils.timestampToMonth(currentDayTimestamp);
        currentDisplayDay = TimeUtils.timestampToDay(currentDayTimestamp);

        tvDateToday.setText(TimeUtils.unixTimeStampToStringDateMonthDay(currentDayTimestamp));

        //데이터 로드
        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Disease> diseaseList = conditionformDao.findDiseaseByDate(currentDayTimestamp);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventDateChange(diseaseList, currentDayTimestamp));
                    }
                });
            }
        });
    }

    //다음 날짜로 이동
    @OnClick(R.id.imageView_date_next)
    public void onClickDateNext() {

        currentDayTimestamp = TimeUtils.getTomorrowUnixTimeStamp(currentDayTimestamp);
        currentDisplayYear = TimeUtils.timestampToYear(currentDayTimestamp);
        currentDisplayMonth = TimeUtils.timestampToMonth(currentDayTimestamp);
        currentDisplayDay = TimeUtils.timestampToDay(currentDayTimestamp);

        tvDateToday.setText(TimeUtils.unixTimeStampToStringDateMonthDay(currentDayTimestamp));

        //데이터 로드
        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Disease> diseaseList = conditionformDao.findDiseaseByDate(currentDayTimestamp);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventDateChange(diseaseList, currentDayTimestamp));
                    }
                });
            }
        });
    }

    //DatePicker 표시하고 넘어온 Date로 변화
    @OnClick(R.id.textView_date_toDay)
    public void onClickDateToday() {
        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                currentDisplayYear = year;
                currentDisplayMonth = monthOfYear + 1;
                currentDisplayDay = dayOfMonth;

                Log.e(TAG, currentDisplayYear + "년 " + currentDisplayMonth + "월 " + currentDisplayDay + "일");

                currentDayTimestamp = TimeUtils.getDayTimeStamp(currentDisplayYear, currentDisplayMonth, currentDisplayDay);

                tvDateToday.setText(TimeUtils.unixTimeStampToStringDateMonthDay(currentDayTimestamp));

                //데이터 로드
                AsyncHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        final ArrayList<Disease> diseaseList = conditionformDao.findDiseaseByDate(currentDayTimestamp);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                EventBus.getDefault().post(new EventDateChange(diseaseList, currentDayTimestamp));
                            }
                        });
                    }
                });
            }
        }, currentDisplayYear, currentDisplayMonth - 1, currentDisplayDay).show();
    }

    @OnClick(R.id.fab_menu_open)
    public void onClickFabMenuOpen() {

        if (fabStatus) {
//            //Close FAB menu
            hideFab();
            Log.e(TAG, " hide");
            shrinkFlTransBg();
            fabStatus = false;
        } else {
//            //Display FAB menu
            expandFab();
            Log.e(TAG, " show");
            growFlTransBg();
            fabStatus = true;
        }
    }

    //Todo: 이벤트가 안날라온다. FrameLayout 영역문제. 애니메이션으로 커지는 영역은 실 영역이 아니다.
    @OnClick(R.id.trans_bg)
    public void onClickFlTransBg() {
        if (fabStatus) {
            hideFab();
            shrinkFlTransBg();
        }
    }

    private void shrinkFlTransBg() {

        flTransBg.startAnimation(animShrink);

//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)flTransBg.getLayoutParams();
//        layoutParams.width = 10;
//        layoutParams.height = 10;
//        layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
//        layoutParams.setMargins(fabMargin, fabMargin, fabMargin, fabMargin);
//        flTransBg.setLayoutParams(layoutParams);
    }

    private void growFlTransBg() {

        flTransBg.startAnimation(animGrow);

//        CoordinatorLayout.LayoutParams layoutParams =  (CoordinatorLayout.LayoutParams)flTransBg.getLayoutParams();
//        layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
//        layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT;
//        flTransBg.setLayoutParams(layoutParams);
    }

    //자동 등록 화면으로 연결
    @OnClick(R.id.fab_menu_register_auto)
    public void onClickFabMenuRegisterAuto() {

        RegistAutoDialog registAutoDialog = RegistAutoDialog.newInstance();
//        registAutoDialog.setTargetFragment(this, 0);
        registAutoDialog.show(fm, "fragment_regist_auto");

        if (fabStatus) {
            hideFab();
            shrinkFlTransBg();
            fabStatus = false;
        }
    }

    public static final int REQUEST_CODE_REGIST_MANUAL = 1002;

    //직접 등록 화면으로 연결
    @OnClick(R.id.fab_menu_register_manual)
    public void onClickFabMenuRegisterManual() {
        Intent intent = new Intent(MainActivity.this, RegistManualActivity.class);
        startActivityForResult(intent, REQUEST_CODE_REGIST_MANUAL);

        if (fabStatus) {
            hideFab();
            shrinkFlTransBg();
            fabStatus = false;
        }
    }

    private void expandFab() {

        FrameLayout.LayoutParams lpFabMenu1 = (FrameLayout.LayoutParams) rlFabMenuRegisterAuto.getLayoutParams();
        lpFabMenu1.bottomMargin += (int) (rlFabMenuRegisterAuto.getHeight() * 1.2);
        rlFabMenuRegisterAuto.setLayoutParams(lpFabMenu1);
        rlFabMenuRegisterAuto.startAnimation(animShowFabMenuRegisterAuto);
        fabRegisterAuto.setClickable(true);

        FrameLayout.LayoutParams lpFabMenu2 = (FrameLayout.LayoutParams) rlFabMenuRegisterManual.getLayoutParams();
        lpFabMenu2.bottomMargin += (int) (rlFabMenuRegisterManual.getHeight() * 2.3);
        rlFabMenuRegisterManual.setLayoutParams(lpFabMenu2);
        rlFabMenuRegisterManual.startAnimation(animShowFabMenuRegisterManual);
        fabRegisterManual.setClickable(true);

    }

    private void hideFab() {

        FrameLayout.LayoutParams lpFabMenu1 = (FrameLayout.LayoutParams) rlFabMenuRegisterAuto.getLayoutParams();
        lpFabMenu1.bottomMargin -= (int) (rlFabMenuRegisterAuto.getHeight() * 1.2);
        rlFabMenuRegisterAuto.setLayoutParams(lpFabMenu1);
        rlFabMenuRegisterAuto.startAnimation(animHideFabMenuRegisterAuto);
        fabRegisterAuto.setClickable(false);

        FrameLayout.LayoutParams lpFabMenu2 = (FrameLayout.LayoutParams) rlFabMenuRegisterManual.getLayoutParams();
        lpFabMenu2.bottomMargin -= (int) (rlFabMenuRegisterManual.getHeight() * 2.3);
        rlFabMenuRegisterManual.setLayoutParams(lpFabMenu2);
        rlFabMenuRegisterManual.startAnimation(animHideFabMenuRegisterManual);
        fabRegisterManual.setClickable(false);


    }
}
